/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Provider} from '@clayui/core';
import {ClayIconSpriteContext} from '@clayui/icon';
import {useLiferayState} from '@liferay/frontend-js-state-web';
import React, {useMemo} from 'react';
import ReactDOM from 'react-dom';

let counter = 0;

/**
 * Wrapper for ReactDOM render that automatically:
 *
 * - Provides commonly-needed context (for example, the Clay spritemap).
 * - Unmounts when portlets are destroyed based on the received
 *   `portletId` value inside `renderData`. If none is passed, the
 *   component will be automatically unmounted before the next navigation.
 *
 * @param {Function|React.Element} renderable Component, or function that returns an Element, to be rendered.
 * @param {object} renderData Data to be passed to the component as props.
 * @param {HTMLElement} container DOM node where the component is to be mounted.
 *
 * The React docs advise not to rely on the render return value, so we
 * don't propagate it.
 *
 * @see https://reactjs.org/docs/react-dom.html#render
 */
export default function render(
	renderable:
		| NonNullable<React.ReactNode>
		| NonNullable<React.ForwardRefExoticComponent<any>>
		| (() => NonNullable<React.ReactNode>),
	renderData: {
		componentId?: string;
		portletId?: string;
		[key: string]: unknown;
	},
	container: Element
) {
	if (!container) {
		return;
	}

	if (!(window.Liferay as any).SPA || (window.Liferay as any).SPA.app) {
		const {portletId} = renderData;

		// Temporary workaround until frontend-icons-web is converted to ESM.
		// We will replace with an import from frontend-icons-web later.

		const spritemap = ((Liferay as any).Icons || {}).spritemap as string;

		let {componentId} = renderData;

		const destroyOnNavigate = !portletId;

		if (!componentId) {
			componentId = `__UNNAMED_COMPONENT__${portletId}__${counter++}`;
		}

		(window.Liferay as any).component(
			componentId,
			{
				destroy: () => {
					container.classList.remove('lfr-tooltip-scope');

					/**
					 * When navigating to another page, this error can be thrown
					 * when a component uses a React portal:
					 *
					 * "Uncaught DOMException: Failed to execute 'removeChild'
					 * on 'Node': The node to be removed is not a child of this
					 * node."
					 *
					 * This is because the contents of the React portal can be
					 * placed in an additional senna surface <div> so when
					 * `container.removeChild(child)` is called, this error is
					 * thrown. (`container` being document.body and `child`
					 * being the portal contents)
					 *
					 * This temporarily catches this error until a better fix
					 * can be found.
					 */
					try {
						ReactDOM.unmountComponentAtNode(container);
					}
					catch (error) {
						if (process.env.NODE_ENV === 'development') {
							console.error(error);
						}
					}
				},
			},
			{
				destroyOnNavigate,
				portletId,
			}
		);

		const Component: React.ElementType =
			typeof renderable === 'function' ||
			(renderable as any).$$typeof === Symbol.for('react.forward_ref')
				? (renderable as any)
				: null;

		container.classList.add('lfr-tooltip-scope');

		if (renderData.hasBodyContent) {
			const children = container.querySelectorAll(
				'.tag-body-content > *'
			);

			if (children.length) {
				renderData.children = children;
			}
		}

		delete renderData.hasBodyContent;

		// eslint-disable-next-line @liferay/portal/no-react-dom-render
		ReactDOM.render(
			<LiferayProvider spritemap={spritemap}>
				{Component ? <Component {...renderData} /> : renderable}
			</LiferayProvider>,
			container
		);
	}
	else {
		(window.Liferay as any).once('SPAReady', () => {
			render(renderable, renderData, container);
		});
	}
}

type Props = {
	children: React.ReactNode;
	spritemap: string;
};

function LiferayProvider({children, spritemap}: Props) {
	const reducedMotion = useMemo(() => {
		return 'user';
	});

	return (
		<Provider reducedMotion={reducedMotion} spritemap={spritemap}>
			<ClayIconSpriteContext.Provider value={spritemap}>
				{children}
			</ClayIconSpriteContext.Provider>
		</Provider>
	);
}
