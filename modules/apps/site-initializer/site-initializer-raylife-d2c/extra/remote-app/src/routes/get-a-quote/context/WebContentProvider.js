/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {
	createContext,
	useCallback,
	useContext,
	useEffect,
	useState,
} from 'react';
import {useCustomEvent} from '../../../common/hooks/useCustomEvent';
import usePrevious from '../../../common/hooks/usePrevious';
import {DEVICES} from '../../../common/utils/constants';
import {Liferay} from '../../../common/utils/liferay';
import TipContainerModal from '../components/tip-container-modal';
import {useTriggerContext} from '../hooks/useTriggerContext';
import {
	getContentTemplates,
	getRenderedContent,
	getStructuredContentFolders,
	getStructuredContents,
} from '../services/WebContent';
import {AppContext} from './AppContextProvider';

const siteGroupId = Liferay.ThemeDisplay.getSiteGroupId();
const WEB_CONTENT_NAME = 'Tip';
const WebContentContext = createContext();

const getHeadlessFirstItemId = async (headlessFn) => {
	try {
		const {
			data: {
				items: [firstItem],
			},
		} = await headlessFn();

		return firstItem?.id;
	}
	catch (error) {
		return null;
	}
};

const WebContentProvider = ({children}) => {
	const {
		state: {
			dimensions: {deviceSize},
		},
	} = useContext(AppContext);
	const [dispatchEvent] = useCustomEvent();
	const {clearState, selectedTrigger} = useTriggerContext();
	const [context, setContext] = useState();
	const previousDeviceSize = usePrevious(deviceSize);
	const [webContentModal, setWebContentModal] = useState({
		html: '',
		show: false,
	});
	const isMobileDevice = deviceSize !== DEVICES.DESKTOP;

	const getInitialData = async () => {
		const [{value: tipFolderId}, {value: tipTemplateId}] =
			await Promise.allSettled([
				getHeadlessFirstItemId(() =>
					getStructuredContentFolders(
						siteGroupId,
						`?filter=name eq '${WEB_CONTENT_NAME}'`
					)
				),
				getHeadlessFirstItemId(() =>
					getContentTemplates(
						siteGroupId,
						`?filter=contains(name, '${WEB_CONTENT_NAME}')`
					)
				),
			]);

		if (!tipFolderId) {
			return console.warn('Raylife TIP Folder not found');
		}

		if (!tipTemplateId) {
			return console.warn('Raylife TIP Template not found');
		}

		const {data: structuredContents} =
			await getStructuredContents(tipFolderId);

		setContext({
			structuredContents: structuredContents.items,
			tipFolderId,
			tipTemplateId,
		});
	};

	const prepareHtmlElement = (html) => {
		const container = document.createElement('div');

		container.innerHTML = html;
		container.querySelector('#dismiss').remove();

		setWebContentModal({html: container.innerHTML, show: true});
	};

	const dispatchCustomEvent = useCallback(
		async (data, event) => {
			if (isMobileDevice) {
				const structuredContent = context.structuredContents.find(
					({friendlyUrlPath}) => friendlyUrlPath === data.templateName
				);

				if (structuredContent?.id) {
					const {data: html} = await getRenderedContent(
						structuredContent.id,
						context.tipTemplateId
					);

					return prepareHtmlElement(html);
				}
			}

			dispatchEvent(data, event);
		},

		// eslint-disable-next-line react-hooks/exhaustive-deps
		[isMobileDevice, context]
	);

	const onClose = useCallback(() => {
		document.getElementById('tip').classList.add('hide');

		setWebContentModal({html: '', show: false});

		clearState();
	}, [clearState]);

	useEffect(() => {
		if (selectedTrigger && deviceSize !== previousDeviceSize) {
			onClose();
		}
	}, [deviceSize, selectedTrigger, previousDeviceSize, onClose]);

	useEffect(() => {
		if (isMobileDevice && !context) {
			getInitialData();
		}
	}, [isMobileDevice, context]);

	return (
		<WebContentContext.Provider value={[context, dispatchCustomEvent]}>
			<TipContainerModal
				isMobile={deviceSize === DEVICES.PHONE}
				onClose={onClose}
				webContentModal={webContentModal}
			/>

			{children}
		</WebContentContext.Provider>
	);
};

export default WebContentProvider;

export {WebContentContext};
