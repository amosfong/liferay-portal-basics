/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import * as d3 from 'd3';
import {openConfirmModal, openToast, sub} from 'frontend-js-web';

import {getAccount} from './data/accounts';
import {getOrganization} from './data/organizations';
import {getUser} from './data/users';
import DndHandler from './utils/DndHandler';
import HighlightHandler from './utils/HighlightHandler';
import MultiSelectHandler from './utils/MultiSelectHandler';
import {
	ACTION_KEYS,
	COUNTER_KEYS_MAP,
	DY,
	INFO_PANEL_MODE_MAP,
	INFO_PANEL_OPEN_EVENT,
	MODEL_TYPE_MAP,
	RECT_SIZES,
	TRANSITIONS_DISABLED,
	TRANSITION_TIME,
	ZOOM_EXTENT,
} from './utils/constants';
import {
	changeNodesParentOrganization,
	formatChild,
	formatItem,
	formatItemName,
	formatRootData,
	getMinWidth,
	hasPermission,
	hideChildren,
	insertAddButtons,
	insertChildrenIntoNode,
	showChildren,
	tree,
} from './utils/index';
import {
	fillAddButtons,
	fillEntityNode,
	getLinkDiagonal,
	printDescription,
} from './utils/paint';

class D3OrganizationChart {
	constructor(
		rootData,
		refs,
		spritemap,
		modalActions,
		namespace,
		nodeMenuActions,
		setSearchDataCountHandler
	) {
		this._currentScale = 1;
		this._dataTree = {};
		this._discoveredNodes = [];
		this._dndHandler = new DndHandler();
		this._handleKeyDown = this._handleKeyDown.bind(this);
		this._handleKeyUp = this._handleKeyUp.bind(this);
		this._handleNodeClick = this._handleNodeClick.bind(this);
		this._handleNodeMouseDown = this._handleNodeMouseDown.bind(this);
		this._handleZoom = this._handleZoom.bind(this);
		this._handleZoomInClick = this._handleZoomInClick.bind(this);
		this._handleZoomOutClick = this._handleZoomOutClick.bind(this);
		this._hideChildrenAndUpdate = this._hideChildrenAndUpdate.bind(this);
		this._highlightHandler = new HighlightHandler();
		this._modalActions = modalActions;
		this._multiSelectHandler = new MultiSelectHandler();
		this._namespace = namespace;
		this._nodeMenuActions = nodeMenuActions;
		this._selectedNodes = new Map();
		this._setSearchDataCountHandler = setSearchDataCountHandler;
		this._spritemap = spritemap;
		this._refs = refs;
		this._rootVisible = false;

		this._addListeners();
		this._initialiseZoomListeners(this._refs);
		this._createChart();
		this._initializeData(formatRootData(rootData));
		this._update(this._root);
	}

	_handleKeyDown(event) {
		if (event.shiftKey) {
			this._multiSelectHandler.updateSelectableItems(
				this._selectedNodes,
				this._nodesGroup
			);
		}
	}

	_handleKeyUp() {
		this._multiSelectHandler.resetSelectableItems();
	}

	_addListeners() {
		document.addEventListener('keydown', this._handleKeyDown);
		document.addEventListener('keyup', this._handleKeyUp);
	}

	addNodes(children, type, parentData) {
		const parentId = parentData.id;

		const formattedChildren = children.map((child) =>
			formatChild(child, type)
		);

		let firstNodeAdded = null;

		this._root.each((d) => {
			if (d.data.id === parentId) {
				const {children} = insertChildrenIntoNode(formattedChildren, d);

				firstNodeAdded = children[children.length - 1];
				this._update(d, false);
			}
		});

		if (firstNodeAdded) {
			this._recenterViewport(firstNodeAdded);
		}
	}

	cleanUp() {
		document.removeEventListener('keydown', this._handleKeyDown);
		document.removeEventListener('keyup', this._handleKeyUp);
	}

	deleteNodes(
		nodesToBeDeleted,
		allNodeInstances = true,
		forceUpdate = true,
		updateCounter = true
	) {
		const propertyToMatch = allNodeInstances
			? 'chartNodeId'
			: 'chartNodeNumber';

		const nodesToBeDeletedIds = new Set(
			nodesToBeDeleted.map((node) => node[propertyToMatch])
		);

		this._root.each((node) => {
			if (nodesToBeDeletedIds.has(node.data[propertyToMatch])) {
				if (updateCounter) {
					const currentQuantity =
						node.parent.data[COUNTER_KEYS_MAP[node.data.type]];

					this.updateNodeContent({
						...node.parent.data,
						[COUNTER_KEYS_MAP[node.data.type]]: currentQuantity - 1,
					});
				}

				if (node.parent.children.length === 1) {
					node.parent.children = null;
					node.parent.data.children = null;
				}
				else {
					const childIndex = node.parent.children.findIndex((d) =>
						nodesToBeDeletedIds.has(d.data[propertyToMatch])
					);

					node.parent.children.splice(childIndex, 1);

					node.parent.data.children = node.parent.children.map(
						(child) => child.data
					);
				}
			}
		});

		if (forceUpdate) {
			this._update(this._root, false, null, true);
		}
	}

	updateNodeContent(nodeData) {
		this._root.each((d) => {
			if (d.data.chartNodeId === nodeData.chartNodeId) {
				d.data = {
					...nodeData,
					chartNodeNumber: d.data.chartNodeNumber,
				};
			}
		});

		const chartItems = this._nodesGroup.selectAll('.chart-item');

		const nodesToBeUpdated = chartItems.filter(
			(chartItem) => chartItem.data.id === nodeData.id
		);

		nodesToBeUpdated.selectAll('.node-title').text(formatItemName);

		nodesToBeUpdated.selectAll('.node-description').remove();

		nodesToBeUpdated.each((d, index, nodes) =>
			printDescription(d, nodes[index], this._spritemap)
		);
	}

	collapseAllNodes() {
		this._currentScale = ZOOM_EXTENT[1];
		this._selectedNodes.clear();

		if (this._rootVisible) {
			hideChildren(this._root);
			this._update(this._root);

			if (this._root._children) {
				this._root._children.forEach((d) =>
					d.descendants().forEach(hideChildren)
				);
			}
		}
		else {
			const sourcesMap = new Map();

			if (!this._root.children) {
				return;
			}

			this._root.children.forEach((child) => {
				const descendants = child.descendants();

				descendants.shift();

				descendants.forEach((descendant) => {
					sourcesMap.set(descendant.data.chartNodeNumber, child);
				});

				hideChildren(child);
			});

			this._update(this._root, true, sourcesMap);

			this._root.children.forEach((child) => {
				if (child._children) {
					child._children.forEach((d) =>
						d.descendants().forEach(hideChildren)
					);
				}
			});
		}
	}

	updateRoot(root) {
		this._initializeData(formatRootData(root, this._rootVisible));
	}

	_hideChildrenAndUpdate(d) {
		hideChildren(d);
		tree(d);
	}

	_initialiseZoomListeners() {
		this._refs.zoomIn.disabled = true;

		this._refs.zoomIn.addEventListener('click', this._handleZoomInClick);
		this._refs.zoomOut.addEventListener('click', this._handleZoomOutClick);
	}

	_handleZoom() {
		this._currentScale = d3.event.transform.k;

		this._handleZoomButtonsState();
		this._zoomHandler.attr('transform', d3.event.transform);
	}

	_handleZoomInClick() {
		this._currentScale = this._currentScale * 2;

		this._handleZoomButtonsState();
		this._animateZoom();
	}

	_handleZoomOutClick() {
		this._currentScale = this._currentScale * 0.5;

		this._handleZoomButtonsState();
		this._animateZoom();
	}

	_handleZoomButtonsState() {
		this._refs.zoomIn.disabled = Boolean(
			this._currentScale === ZOOM_EXTENT[1]
		);
		this._refs.zoomOut.disabled = Boolean(
			this._currentScale === ZOOM_EXTENT[0]
		);
	}

	_animateZoom() {
		this._createTransition();
		this._handleTransition(this.svg).call(
			this._zoom.scaleTo,
			this._currentScale
		);
	}

	_handleNodeClick(event, d) {
		event.stopPropagation();

		if (event.shiftKey && !d.data.selectable) {
			return;
		}

		if (d.data.type === MODEL_TYPE_MAP.user) {
			return this._recenterViewport(d);
		}

		let expanded = true;

		if (this._selectedNodes.has(d.data.chartNodeId)) {
			expanded = false;
		}

		if (!event.shiftKey) {
			this._selectedNodes.clear();
		}

		if (expanded) {
			this._selectedNodes.set(d.data.chartNodeId, d);
		}
		else {
			this._selectedNodes.delete(d.data.chartNodeId);
		}

		if (!d.data.fetched) {
			const getData =
				d.data.type === MODEL_TYPE_MAP.organization
					? getOrganization
					: getAccount;

			return getData(d.data.id)
				.then((rawData) => formatItem(rawData, d.data.type))
				.then((data) => {
					const {children} =
						insertChildrenIntoNode(data.children, d) || {};

					this._storeDataTreeInfo(children);
				})
				.then(() => {
					d.data.fetched = true;

					this._update(d);

					if (event.shiftKey) {
						this._multiSelectHandler.updateSelectableItems(
							this._selectedNodes,
							this._nodesGroup
						);
					}
				});
		}

		if (expanded) {
			showChildren(d);
		}
		else {
			hideChildren(d);
		}

		this._update(d);

		if (event.shiftKey) {
			this._multiSelectHandler.updateSelectableItems(
				this._selectedNodes,
				this._nodesGroup
			);
		}
	}

	_createChart() {
		this._zoom = d3
			.zoom()
			.scaleExtent(ZOOM_EXTENT)
			.on('zoom', this._handleZoom);

		this.svg = d3
			.select(this._refs.svg)
			.on('mousedown', this._nodeMenuActions.close)
			.call(this._zoom);

		this._zoomHandler = this.svg.append('g');

		const dataWrapper = this._zoomHandler
			.append('g')
			.attr('class', 'chart-data-wrapper');

		this._linksGroup = dataWrapper.append('g');
		this._nodesGroup = dataWrapper.append('g');
	}

	_initializeData(initialData) {
		this._root = d3.hierarchy(initialData, (d) => d.children);
		this._root.x0 = DY / 2;
		this._root.y0 = 0;

		if (this._root.children?.length > 1) {
			this.collapseAllNodes();
		}

		if (this._root.children) {
			this._storeDataTreeInfo(this._root.children, true);
		}
	}

	_handleNodeMouseDown(d) {
		d3.event.stopPropagation();
		this._nodeMenuActions.close();

		this._clearDiscoveredNodes(this._discoveredNodes, this._nodesGroup);

		Liferay.fire(`${this._namespace}${INFO_PANEL_OPEN_EVENT}`, {
			data: d.data,
			mode: INFO_PANEL_MODE_MAP.click,
			type: d.data.type,
		});

		if (d.data.type === MODEL_TYPE_MAP.user) {
			this._createTransition();

			this._nodesGroup
				.selectAll('.chart-item')
				.filter((node) => {
					return this._selectedNodes.has(node.data.chartNodeId);
				})
				.classed('selected', false);

			this._selectedNodes.clear();

			this._nodesGroup
				.selectAll('.chart-item')
				.filter((node) => {
					return d.data.chartNodeId === node.data.chartNodeId;
				})
				.classed('selected', true);

			return this._recenterViewport(d);
		}

		if (!hasPermission(d.data, ACTION_KEYS[d.data.type].MOVE)) {
			return this._handleNodeClick(d3.event, d);
		}

		this._highlightHandler.disableHighlight();

		this._dndHandler
			.handleMouseEvent(
				d3.event,
				d,
				this._selectedNodes,
				this._refs.svg,
				this._nodesGroup,
				this._currentScale
			)
			.then(({event, target, type}) => {
				this._highlightHandler.enableHighlight();

				if (type === 'click') {
					return this._handleNodeClick(event, d);
				}

				if (target) {
					const nodesToBeMoved = [];

					if (this._selectedNodes.has(d.data.chartNodeId)) {
						nodesToBeMoved.push(
							...Array.from(this._selectedNodes.values())
						);
					}
					else {
						nodesToBeMoved.push(d);
					}

					const message =
						nodesToBeMoved.length === 1
							? sub(
									Liferay.Language.get(
										'x-will-be-moved-into-x'
									),
									nodesToBeMoved[0].data.name,
									target.data.name
								)
							: sub(
									Liferay.Language.get(
										'x-items-will-be-moved-into-x'
									),
									nodesToBeMoved.length,
									target.data.name
								);

					openConfirmModal({
						message,
						onConfirm: (isConfimed) => {
							if (isConfimed) {
								this._moveNodes(nodesToBeMoved, target);
							}
						},
					});
				}
			});
	}

	_moveNodes(nodes, target) {
		changeNodesParentOrganization(nodes, target).then(
			({fulfilled: fulfilledNodes, rejected: rejectedNodes}) => {
				if (fulfilledNodes.length) {
					const fulfilledNodesData = fulfilledNodes.map(
						(node) => node.data
					);

					fulfilledNodes.forEach((node) => {
						node.parent.data = {
							...node.parent.data,
							[COUNTER_KEYS_MAP[node.data.type]]:
								node.parent.data[
									COUNTER_KEYS_MAP[node.data.type]
								] - 1,
						};

						target.data[COUNTER_KEYS_MAP[node.data.type]] =
							target.data[COUNTER_KEYS_MAP[node.data.type]] + 1;
						this.updateNodeContent(node.parent.data);
					});

					this.updateNodeContent(target.data);

					this.deleteNodes(fulfilledNodesData, false, false, false);

					if (target.data.fetched) {
						const {children} =
							insertChildrenIntoNode(
								fulfilledNodesData,
								target
							) || {};

						this._storeDataTreeInfo(children);
					}

					this._update(target);
				}

				if (rejectedNodes.length) {
					rejectedNodes.forEach((node) => {
						openToast({
							message: sub(
								Liferay.Language.get('x-could-not-be-moved'),
								node.data.name
							),
							type: 'danger',
						});
					});
				}
			}
		);
	}

	_update(source, recenter = true, sourcesMap, showDeleteTransition) {
		insertAddButtons(this._root, this._selectedNodes);
		tree(this._root);

		this._root.eachBefore((d) => {
			d.x0 = d.x;
			d.y0 = d.y;
		});

		this._createTransition();

		if (recenter) {
			this._recenterViewport(source);
		}
		this._updateLinks(source, showDeleteTransition);
		this._updateNodes(source, sourcesMap, showDeleteTransition);
	}

	_createTransition() {
		this.transition = this.svg
			.transition()
			.duration(TRANSITION_TIME)
			.tween(
				'resize',
				window.ResizeObserver
					? null
					: () => () => this.svg.dispatch('toggle')
			);
	}

	_recenterViewport(source) {
		const {height, width} = this._refs.svg.getBoundingClientRect();
		const k = this._currentScale;
		let y0;

		if (source.depth || this._rootVisible) {
			y0 = source.y0 + RECT_SIZES[source.data.type].width / 2;
		}
		else {
			if (!source.children) {
				return;
			}

			y0 = source.children[0].y0 + getMinWidth(source.children) / 2;
		}

		const x = -y0 * k + width / 2;
		const y = -source.x0 * k + height / 2;

		this._handleTransition(this.svg).call(
			this._zoom.transform,
			d3.zoomIdentity.translate(x, y).scale(k)
		);
	}

	_handleTransition(selection) {
		return TRANSITIONS_DISABLED
			? selection
			: selection.transition(this.transition);
	}

	_updateLinks(source, showDeleteTransition) {
		const links = this._root.links();

		const filteredLinks = this._rootVisible
			? links
			: links.filter((d) => d.source.depth);

		const bindedLinks = this._linksGroup
			.selectAll('.chart-link')
			.data(filteredLinks, (d) => d.target.data.chartNodeNumber);

		this.bindedChartLink = bindedLinks
			.enter()
			.append('path')
			.attr('class', 'chart-link')
			.attr('d', (d) => getLinkDiagonal(d, source, 'enter'))
			.attr('opacity', 0);

		this._handleTransition(bindedLinks.merge(this.bindedChartLink))
			.attr('d', (d) => getLinkDiagonal(d, source))
			.attr('opacity', 1);

		this._handleTransition(bindedLinks.exit())
			.remove()
			.attr('d', (d) =>
				getLinkDiagonal(
					d,
					source,
					d.target.data.type === 'add' || showDeleteTransition
						? null
						: 'exit'
				)
			)
			.attr('opacity', 0);
	}

	_updateNodes(source, sourcesMap, showDeleteTransition) {
		const dataNodes = this._root.descendants().reverse();

		const filteredDataNodes = this._rootVisible
			? dataNodes
			: dataNodes.filter((d) => d.depth !== 0);

		this.bindedNodes = this._nodesGroup
			.selectAll('.chart-item')
			.data(filteredDataNodes, (d) => d.data.chartNodeNumber);

		const nodes = this.bindedNodes
			.enter()
			.append('g')
			.attr('opacity', 0)
			.attr('class', (d) => `chart-item chart-item-${d.data.type}`);

		const addButtons = nodes.filter((d) => d.data.type === 'add');

		addButtons.attr(
			'transform',
			(d) => `translate(${d.y},${d.x}) scale(0)`
		);

		fillAddButtons(addButtons, this._spritemap, this._modalActions.open);

		const children = nodes.filter((d) => d.data.type !== 'add');

		children.attr('transform', `translate(${source.y0},${source.x0})`);
		fillEntityNode(children, this._spritemap, this._nodeMenuActions.open);

		children
			.on('mouseenter', (d) => {
				this._highlightHandler.highlight(
					d,
					this._root,
					this._nodesGroup,
					this._linksGroup
				);
			})
			.on('mouseleave', () => this._highlightHandler.removeHighlight())
			.on('mousedown', this._handleNodeMouseDown);

		this._handleTransition(this.bindedNodes.merge(nodes))
			.attr('opacity', 1)
			.attr('transform', (d) =>
				d.data.type !== 'add'
					? `translate(${d.y},${d.x})`
					: `translate(${d.y},${d.x}) scale(1)`
			);

		this.bindedNodes.classed('selected', (d) =>
			this._selectedNodes.has(d.data.chartNodeId)
		);

		this._handleTransition(this.bindedNodes.exit())
			.remove()
			.attr('opacity', 0)
			.attr('transform', (d) => {
				if (showDeleteTransition || d.data.type === 'add') {
					return `translate(${d.y},${d.x}) scale(0)`;
				}

				const target = sourcesMap
					? sourcesMap.get(d.data.chartNodeNumber)
					: source;

				return `translate(${target.y},${target.x})`;
			});
	}

	_clearDiscoveredNodes() {
		this._nodesGroup
			.selectAll('.chart-item.discovered')
			.classed('discovered', false);
		this._discoveredNodes = [];
	}

	_getNodeById(id, type, forceLoad = false) {
		const storedNode = this._dataTree[String(id)];
		if (
			storedNode &&
			(!forceLoad || storedNode.find((element) => element.root))
		) {
			return new Promise((resolve) => {
				resolve(storedNode.map((element) => element.node));
			});
		}

		const getData =
			type === MODEL_TYPE_MAP.organization
				? getOrganization
				: type === MODEL_TYPE_MAP.account
					? getAccount
					: getUser;

		return getData(id)
			.then((rawData) => formatItem(rawData, type))
			.then((data) => {
				data = JSON.parse(JSON.stringify(data));

				if (type === MODEL_TYPE_MAP.organization) {
					const parentIds = [data.parentOrganization?.id] || [];

					return Promise.all(
						parentIds.map((parentId) => {
							if (parentId) {
								return this._getParentNodeById(
									id,
									data,
									parentId,
									MODEL_TYPE_MAP.organization
								);
							}
						})
					);
				}

				if (type === MODEL_TYPE_MAP.account) {
					const parentIds = data.organizationIds || [];

					return Promise.all(
						parentIds.map((parentId) => {
							if (parentId) {
								return this._getParentNodeById(
									id,
									data,
									parentId,
									MODEL_TYPE_MAP.organization
								);
							}
						})
					);
				}

				if (type === MODEL_TYPE_MAP.user) {
					const organizations = data.organizationBriefs || [];
					const accounts = data.accountBriefs || [];

					const parents = organizations
						.map((organization) => {
							return {
								id: organization.id,
								type: MODEL_TYPE_MAP.organization,
							};
						})
						.concat(
							accounts.map((account) => {
								return {
									id: account.id,
									type: MODEL_TYPE_MAP.account,
								};
							})
						);

					return Promise.all(
						parents.map((parent) => {
							if (parent) {
								return this._getParentNodeById(
									id,
									data,
									parent.id,
									parent.type
								);
							}
						})
					);
				}
			});
	}

	_getParentNodeById(id, data, parentId, type) {
		return this._getNodeById(
			parentId,
			type,
			type === MODEL_TYPE_MAP.account
		).then((parentNodes) => {
			parentNodes = parentNodes.filter(Boolean);

			if (!parentNodes) {
				return;
			}

			let currentNode;

			parentNodes.forEach((parentNode) => {
				showChildren(parentNode);

				const node = (parentNode.children || []).find(
					(child) => String(child.data.id) === String(id)
				);

				if (parentNode.children && parentNode.children.length && node) {
					currentNode = node;
					this._storeDataTreeInfo(parentNode.children);
				}
				else {
					const {children} =
						insertChildrenIntoNode([data], parentNode) || {};
					currentNode = children.find(
						(child) => String(child.data.id) === String(id)
					);
					this._storeDataTreeInfo(children);
				}

				if (currentNode) {
					if (!currentNode.children && !currentNode._children) {
						insertChildrenIntoNode(data.children, currentNode);
					}

					currentNode.data.fetched = true;
				}

				parentNode.data.fetched = true;
			});

			return currentNode;
		});
	}

	destroyChart() {
		this.svg.select('*').remove();
	}

	_expandParentNode(node) {
		if (node && node.parent) {
			showChildren(node.parent);

			return this._expandParentNode(node.parent);
		}
	}

	search(id, type) {
		if (!id) {
			this._clearDiscoveredNodes(this._discoveredNodes, this._nodesGroup);

			return;
		}

		if (!type) {
			return;
		}

		this._clearDiscoveredNodes(this._discoveredNodes, this._nodesGroup);

		this._getNodeById(id, type, true).then((nodes = []) => {
			nodes = nodes.filter(Boolean);

			nodes.forEach((node) => {
				if (node) {
					this._expandParentNode(node.parent);

					this._discoveredNodes.push(node.data.chartNodeId);

					this._update(node, false);
				}
			});

			const count = this._showDiscoveredNodes(
				this._discoveredNodes,
				this._nodesGroup
			);

			if (count >= 1) {
				this._currentScale = count === 1 ? 2 : 1;
				this._handleZoomOutClick();

				this._update(nodes[0], true);
			}

			if (this._setSearchDataCountHandler) {
				this._setSearchDataCountHandler(count);
			}
		});
	}

	_showDiscoveredNodes() {
		return this._nodesGroup
			.selectAll('.chart-item')
			.filter((d) => {
				return this._discoveredNodes.indexOf(d.data.chartNodeId) >= 0;
			})
			.classed('discovered', true)
			.size();
	}

	_storeDataTreeInfo(children, root = false) {
		for (const child of children || []) {
			if (!child.data || !child.data.id || child.data.type === 'add') {
				continue;
			}

			if (!this._dataTree[String(child.data.id)]) {
				this._dataTree[String(child.data.id)] = [];
			}

			if (
				!this._dataTree[String(child.data.id)].find((element) => {
					return (
						String(element.node.parent?.data?.id) ===
						String(child.parent?.data?.id)
					);
				})
			) {
				this._dataTree[String(child.data.id)].push({
					node: child,
					root,
				});
			}
		}
	}
}

export default D3OrganizationChart;