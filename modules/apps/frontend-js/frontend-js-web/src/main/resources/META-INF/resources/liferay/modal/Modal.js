/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import ClayModal, {useModal} from '@clayui/modal';
import {render} from '@liferay/frontend-js-react-web';
import classNames from 'classnames';
import PropTypes from 'prop-types';
import React, {useCallback, useEffect, useRef, useState} from 'react';

import './Modal.scss';
import delegate from '../delegate/delegate.es';
import navigate from '../util/navigate.es';

const Modal = ({
	bodyComponent,
	bodyHTML,
	buttons,
	center,
	className,
	containerProps = {
		className: 'cadmin',
	},
	contentComponent: ContentComponent,
	customEvents,
	disableAutoClose,
	disableHeader,
	footerCssClass,
	headerCssClass,
	headerHTML,
	height,
	id,
	iframeBodyCssClass,
	iframeProps = {},
	onClose,
	onOpen,
	role = 'dialog',
	size,
	status,
	title,
	url,
	zIndex,
}) => {
	const [loading, setLoading] = useState(true);

	const {observer, onOpenChange, open} = useModal({
		onClose: () => processClose(),
	});

	useEffect(() => {
		onOpenChange(true);
	}, [onOpenChange]);

	const eventHandlersRef = useRef([]);

	const processClose = useCallback(() => {
		if (!open) {
			return;
		}

		onOpenChange(false);

		document.body.classList.remove('modal-open');

		const eventHandlers = eventHandlersRef.current;

		eventHandlers.forEach((eventHandler) => {
			eventHandler.detach();
		});

		eventHandlers.splice(0, eventHandlers.length);

		if (onClose) {
			onClose();
		}
	}, [eventHandlersRef, onClose, onOpenChange, open]);

	const onButtonClick = ({formId, onClick, type}) => {
		const submitForm = (form) => {
			if (form.requestSubmit) {
				form.requestSubmit();
			}
			else {
				const accepted = form.dispatchEvent(
					new Event('submit', {cancelable: true})
				);

				if (accepted) {
					form.submit();
				}
			}
		};

		if (type === 'cancel') {
			processClose();
		}
		else if (url && type === 'submit') {
			const iframe = document.querySelector('.liferay-modal iframe');

			if (iframe) {
				const iframeDocument = iframe.contentWindow.document;

				const forms = iframeDocument.querySelectorAll('form');

				if (
					forms.length !== 1 &&
					process.env.NODE_ENV === 'development'
				) {
					console.warn('There should be one form within a modal.');
				}

				if (formId) {
					const form = iframeDocument.getElementById(formId);

					if (form) {
						submitForm(form);
					}
				}
				else if (forms.length >= 1) {
					submitForm(forms[0]);
				}
			}
		}

		if (onClick) {
			onClick({processClose});
		}
	};

	const Body = ({component: BodyComponent, html}) => {
		const bodyRef = useRef();

		useEffect(() => {
			if (html) {
				const fragment = document
					.createRange()
					.createContextualFragment(html);

				bodyRef.current.innerHTML = '';

				bodyRef.current.appendChild(fragment);
			}

			if (onOpen) {
				onOpen({container: bodyRef.current, processClose});
			}
		}, [html]);

		return (
			<div className="liferay-modal-body" ref={bodyRef}>
				{BodyComponent && <BodyComponent closeModal={processClose} />}
			</div>
		);
	};

	useEffect(() => {
		const eventHandlers = eventHandlersRef.current;

		if (customEvents) {
			customEvents.forEach((customEvent) => {
				if (customEvent.name && customEvent.onEvent) {
					const eventHandler = Liferay.on(
						customEvent.name,
						(event) => {
							customEvent.onEvent(event);
						}
					);

					eventHandlers.push(eventHandler);
				}
			});
		}

		const closeEventHandler = Liferay.on('closeModal', (event) => {
			if (event.id && id && event.id !== id) {
				return;
			}

			processClose();

			if (event.redirect) {
				navigate(event.redirect);
			}
		});

		eventHandlers.push(closeEventHandler);

		return () => {
			eventHandlers.forEach((eventHandler) => {
				eventHandler.detach();
			});

			eventHandlers.splice(0, eventHandlers.length);
		};
	}, [customEvents, eventHandlersRef, id, onClose, onOpen, processClose]);

	return (
		<>
			{open && (
				<ClayModal
					center={center}
					className={classNames('liferay-modal', className)}
					containerProps={{...containerProps}}
					disableAutoClose={disableAutoClose}
					id={id}
					observer={observer}
					role={role}
					size={url && !size ? 'full-screen' : size}
					status={status}
					zIndex={zIndex}
				>
					{ContentComponent ? (
						<ContentComponent closeModal={processClose} />
					) : (
						<>
							{!disableHeader && (
								<ClayModal.Header className={headerCssClass}>
									{headerHTML ? (
										<div
											dangerouslySetInnerHTML={{
												__html: headerHTML,
											}}
										></div>
									) : (
										title
									)}

									{loading && (
										<span className="sr-only">
											- {Liferay.Language.get('loading')}
										</span>
									)}
								</ClayModal.Header>
							)}

							<div
								className={classNames('modal-body', {
									'modal-body-iframe': url,
								})}
								style={{
									height,
								}}
							>
								{url && (
									<>
										{loading && <ClayLoadingIndicator />}

										<StatusMessage loading={loading} />

										<Iframe
											iframeBodyCssClass={
												iframeBodyCssClass
											}
											iframeProps={{
												id:
													(id && `${id}_iframe_`) ||
													'modalIframe',
												...iframeProps,
											}}
											onOpen={onOpen}
											processClose={processClose}
											title={title}
											updateLoading={(loading) => {
												setLoading(loading);
											}}
											url={url}
										/>
									</>
								)}

								{bodyHTML && <Body html={bodyHTML} />}

								{bodyComponent && (
									<Body component={bodyComponent} />
								)}
							</div>

							{buttons && (
								<ClayModal.Footer
									className={footerCssClass}
									last={
										<ClayButton.Group spaced>
											{buttons.map(
												(
													{
														displayType,
														formId,
														id,
														label,
														onClick,
														type,
														...otherProps
													},
													index
												) => (
													<ClayButton
														displayType={
															displayType
														}
														id={id}
														key={index}
														onClick={() => {
															onButtonClick({
																formId,
																onClick,
																type,
															});
														}}
														type={
															type === 'cancel'
																? 'button'
																: type
														}
														{...otherProps}
													>
														{label}
													</ClayButton>
												)
											)}
										</ClayButton.Group>
									}
								/>
							)}
						</>
					)}
				</ClayModal>
			)}
		</>
	);
};

const StatusMessage = ({loading}) => {
	const [showMessage, setShowMessage] = useState(true);

	useEffect(() => {
		if (!loading) {
			setTimeout(() => setShowMessage(false), 1000);
		}
	}, [loading]);

	return showMessage ? (
		<span className="sr-only" role="status">
			{!loading && Liferay.Language.get('loaded')}
		</span>
	) : null;
};

const openModal = (props) => {
	if (
		props &&
		props.url &&
		props.bodyHTML &&
		process.env.NODE_ENV === 'development'
	) {
		console.warn(
			'url and bodyHTML props are both set. bodyHTML will be ignored. Please use one or another.'
		);
	}

	// Mount in detached node; Clay will take care of appending to `document.body`.
	// See: https://github.com/liferay/clay/blob/master/packages/clay-shared/src/Portal.tsx

	render(Modal, props, document.createElement('div'));
};

const openPortletModal = ({
	containerProps,
	footerCssClass,
	headerCssClass,
	iframeBodyCssClass,
	onClose,
	portletSelector,
	subTitle,
	title,
	url,
}) => {
	const portlet = document.querySelector(portletSelector);

	if (portlet && url) {
		const titleElement =
			portlet.querySelector('.portlet-title') ||
			portlet.querySelector('.portlet-title-default');

		if (titleElement) {
			if (portlet.querySelector('#cpPortletTitle')) {
				const titleTextElement = titleElement.querySelector(
					'.portlet-title-text'
				);

				if (titleTextElement) {
					title = `${titleTextElement.outerHTML} - ${title}`;
				}
			}
			else {
				title = `${titleElement.textContent} - ${title}`;
			}
		}

		let headerHTML;

		if (subTitle) {
			headerHTML = `${title}<div class="portlet-configuration-subtitle small"><span class="portlet-configuration-subtitle-text">${subTitle}</span></div>`;
		}

		openModal({
			containerProps,
			footerCssClass,
			headerCssClass,
			headerHTML,
			iframeBodyCssClass,
			onClose,
			title,
			url,
		});
	}
};

/**
 * A utility with API that matches Liferay.Portlet.openWindow. The purpose of
 * this utility is backwards compatibility.
 * @deprecated As of Athanasius (7.3.x), replaced by Liferay.Portlet.openModal
 */
const openPortletWindow = ({bodyCssClass, portlet, uri, ...otherProps}) => {
	openPortletModal({
		iframeBodyCssClass: bodyCssClass,
		portletSelector: portlet,
		url: uri,
		...otherProps,
	});
};

const openSelectionModal = ({
	buttonAddLabel = Liferay.Language.get('add'),
	buttonCancelLabel = Liferay.Language.get('cancel'),
	containerProps,
	customSelectEvent = false,
	getSelectedItemsOnly = true,
	height,
	id,
	iframeBodyCssClass,
	multiple = false,
	onClose,
	onSelect,
	selectEventName,
	selectedData,
	selectedDataCheckboxesDisabled = false,
	size,
	title,
	url,
	zIndex,
}) => {
	const eventHandlers = [];
	let iframeWindowObj;
	let processCloseFn;
	let selectedItem;

	const select = () => {
		if (multiple && !selectedItem) {
			const searchContainer =
				iframeWindowObj.document.querySelector('.searchcontainer');

			if (searchContainer) {
				iframeWindowObj.Liferay.componentReady(searchContainer.id).then(
					(searchContainer) => {
						const allSelectedElements = getSelectedItemsOnly
							? searchContainer.select.getAllSelectedElements()
							: searchContainer.select._getAllElements(false);

						const allSelectedNodes =
							allSelectedElements.getDOMNodes();

						onSelect(
							allSelectedNodes.map((node) => {
								let item = {};

								if (node.value) {
									item.value = node.value;
								}

								if (!getSelectedItemsOnly && node.checked) {
									item.checked = node.checked;
								}

								const row = node.closest('dd, tr, li');

								if (row && Object.keys(row.dataset).length) {
									item = {...item, ...row.dataset};
								}

								return item;
							})
						);

						processCloseFn();
					}
				);
			}
			else {
				processCloseFn();
			}
		}
		else {
			onSelect(selectedItem);

			processCloseFn();
		}
	};

	const iframeProps = {};

	if (selectedData) {
		const ercs = [];
		const ids = [];
		const labels = [];

		selectedData.forEach((item) => {
			const {externalReferenceCode, id, label} = item;

			if (externalReferenceCode) {
				ercs.push(externalReferenceCode);
			}

			if (id) {
				ids.push(id);
			}

			if (label) {
				labels.push(label);
			}
		});

		if (ercs.length) {
			iframeProps['data-selecteditemsercs'] = ercs.join(',');
		}

		if (ids.length) {
			iframeProps['data-selecteditemsids'] = ids.join(',');
		}

		if (labels.length) {
			iframeProps['data-selecteditemslabels'] = labels.join(',');
		}
	}

	openModal({
		buttons: multiple
			? [
					{
						displayType: 'secondary',
						label: buttonCancelLabel,
						type: 'cancel',
					},
					{
						label: buttonAddLabel,
						onClick: select,
					},
				]
			: null,
		containerProps,
		height,
		id: id || selectEventName,
		iframeBodyCssClass,
		iframeProps,
		onClose: () => {
			eventHandlers.forEach((eventHandler) => {
				eventHandler.detach();
			});

			eventHandlers.splice(0, eventHandlers.length);

			if (onClose) {
				onClose();
			}
		},
		onOpen: ({iframeWindow, processClose}) => {
			iframeWindowObj = iframeWindow;
			processCloseFn = processClose;

			const iframeBody = iframeWindow.document.body;

			const itemElements =
				iframeBody.querySelectorAll('.selector-button');

			if (selectedData) {
				const selectedDataSet = new Set(selectedData);

				itemElements.forEach((itemElement) => {
					const itemId =
						itemElement.dataset.entityid ||
						itemElement.dataset.entityname;

					if (selectedDataSet.has(itemId)) {
						itemElement.disabled = true;
						itemElement.classList.add('disabled');
					}
					else {
						itemElement.disabled = false;
						itemElement.classList.remove('disabled');
					}
				});

				if (multiple) {
					for (const row of iframeBody.querySelectorAll(
						'.searchcontainer tr'
					)) {
						const itemId =
							row.dataset.entityid || row.dataset.entityname;

						if (selectedDataSet.has(itemId)) {
							const checkbox = row.querySelector(
								'input[type="checkbox"]'
							);

							if (!checkbox) {
								continue;
							}

							checkbox.checked = true;

							if (selectedDataCheckboxesDisabled) {
								checkbox.disabled = true;
							}
						}
					}
				}
			}

			if (selectEventName) {
				const selectEventHandler = Liferay.on(
					selectEventName,
					(event) => {
						selectedItem = event.data || event;

						if (!multiple) {
							select();
						}
					}
				);

				eventHandlers.push(selectEventHandler);

				if (!customSelectEvent) {
					iframeBody.addEventListener('click', (event) => {
						const delegateTarget =
							event.target?.closest('.selector-button');

						if (delegateTarget) {
							Liferay.fire(
								selectEventName,
								delegateTarget.dataset
							);
						}
					});
				}
			}
		},
		size,
		title,
		url,
		zIndex,
	});
};

const CSS_CLASS_IFRAME_BODY = 'dialog-iframe-popup';

class Iframe extends React.Component {
	constructor(props) {
		super(props);

		this.delegateHandlers = [];

		this.iframeRef = React.createRef();

		const iframeURL = new URL(props.url);

		const namespace = iframeURL.searchParams.get('p_p_id');

		const bodyCssClass =
			props.iframeBodyCssClass || props.iframeBodyCssClass === ''
				? `${CSS_CLASS_IFRAME_BODY} ${props.iframeBodyCssClass}`
				: `cadmin ${CSS_CLASS_IFRAME_BODY}`;

		iframeURL.searchParams.set(`_${namespace}_bodyCssClass`, bodyCssClass);

		this.state = {src: iframeURL.toString()};
	}

	componentWillUnmount() {
		if (this.spaNavigationHandlers) {
			this.spaNavigationHandlers.forEach((handler) => {
				Liferay.detach(handler);
			});
		}

		if (this.delegateHandlers.length) {
			this.delegateHandlers.forEach(({dispose}) => dispose());
			this.delegateHandlers = null;
		}
	}

	onLoadHandler = () => {
		const iframeWindow = this.iframeRef.current.contentWindow;

		this.delegateHandlers.push(
			delegate(
				iframeWindow.document,
				'click',
				'.btn-cancel,.lfr-hide-dialog',
				() => this.props.processClose()
			),
			delegate(iframeWindow.document, 'keydown', 'body', (event) => {
				if (event.key === 'Escape') {
					this.props.processClose();
				}
			})
		);

		iframeWindow.document.body.classList.add(CSS_CLASS_IFRAME_BODY);

		if (iframeWindow.Liferay.SPA) {
			this.spaNavigationHandlers = [
				iframeWindow.Liferay.on('beforeScreenFlip', () => {
					iframeWindow.document.body.classList.add(
						CSS_CLASS_IFRAME_BODY
					);
				}),
			];

			if (this.props.onOpen) {
				this.spaNavigationHandlers.push(
					iframeWindow.Liferay.on('screenFlip', () => {
						this.props.onOpen({
							iframeWindow,
							processClose: this.props.processClose,
						});
					})
				);
			}
		}

		this.props.updateLoading(false);

		iframeWindow.onunload = () => {
			this.props.updateLoading(true);
		};

		Liferay.fire('modalIframeLoaded', {src: this.state.src});

		if (this.props.onOpen) {
			this.props.onOpen({
				iframeWindow,
				processClose: this.props.processClose,
			});
		}
	};

	render() {
		return (
			<iframe
				{...this.props.iframeProps}
				onLoad={this.onLoadHandler}
				ref={this.iframeRef}
				src={this.state.src}
				title={this.props.title}
			/>
		);
	}
}

Modal.propTypes = {
	bodyHTML: PropTypes.string,
	buttons: PropTypes.arrayOf(
		PropTypes.shape({
			displayType: PropTypes.oneOf([
				'danger',
				'info',
				'link',
				null,
				'primary',
				'secondary',
				'success',
				'unstyled',
				'warning',
			]),
			formId: PropTypes.string,
			id: PropTypes.string,
			label: PropTypes.string,
			onClick: PropTypes.func,
			type: PropTypes.oneOf(['cancel', 'submit']),
		})
	),
	center: PropTypes.bool,
	containerProps: PropTypes.object,
	contentComponent: PropTypes.elementType,
	customEvents: PropTypes.arrayOf(
		PropTypes.shape({
			name: PropTypes.string,
			onEvent: PropTypes.func,
		})
	),
	disableHeader: PropTypes.bool,
	headerHTML: PropTypes.string,
	height: PropTypes.string,
	id: PropTypes.string,
	iframeProps: PropTypes.object,
	onClose: PropTypes.func,
	onOpen: PropTypes.func,
	role: PropTypes.string,
	size: PropTypes.oneOf(['full-screen', 'lg', 'md', 'sm']),
	status: PropTypes.string,
	title: PropTypes.string,
	url: PropTypes.string,
};

export {
	Modal,
	openModal,
	openPortletModal,
	openPortletWindow,
	openSelectionModal,
};