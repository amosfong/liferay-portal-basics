/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayMultiSelect from '@clayui/multi-select';
import {filesize} from 'filesize';
import {useState} from 'react';

import {UploadedFile} from '../../../../../components/FileList/FileList';
import Form from '../../../../../components/MarketplaceForm';
import UploadLogo from '../../../../../components/UploadLogo/UploadLogo';
import {
	NewAppTypes,
	useNewAppContext,
} from '../../../../../context/NewAppContext';
import {ProductVocabulary} from '../../../../../enums/ProductVocabulary';
import i18n from '../../../../../i18n';
import {getIconSpriteMap} from '../../../../../liferay/constants';
import {getRandomID} from '../../../../../utils/string';

const tooltipInfo = {
	categories: 'tootip',
	description: 'tootip',
	name: 'tootip',
	tags: 'tootip',
};

const Profile = () => {
	const [
		{
			profile: {categories, description, file, name, tags},
			references: {vocabulariesAndCategories},
		},
		dispatch,
	] = useNewAppContext();

	const defaultSourceItems = {
		categories:
			vocabulariesAndCategories[ProductVocabulary.APP_CATEGORY]
				?.categories ?? [],
		tags:
			vocabulariesAndCategories[ProductVocabulary.APP_TAGS]?.categories ??
			[],
	};

	const [multiSelectText, setMultiSelectText] = useState({
		categories: '',
		tags: '',
	});

	const onChange = (event: any) => {
		dispatch({
			payload: {[event.target.name]: event.target.value},
			type: NewAppTypes.SET_PROFILE,
		});
	};

	const onChangeMultiSelect = (event: any) => {
		setMultiSelectText((prevState) => ({
			...prevState,
			[event.target.name]: event.target.value,
		}));
	};

	const handleLogoUpload = (files: FileList) => {
		const _file = files[0];

		const newUploadedFile: UploadedFile = {
			changed: true,
			error: false,
			file: _file,
			fileName: _file.name,
			id: getRandomID(),
			preview: URL.createObjectURL(_file),
			progress: 0,
			readableSize: filesize(_file.size),
			uploaded: true,
		};

		if (file?.id) {
			dispatch({
				payload: file.id,
				type: NewAppTypes.SET_DELETE_IMAGE,
			});
		}

		dispatch({
			payload: {
				file: newUploadedFile,
			},
			type: NewAppTypes.SET_PROFILE,
		});
	};

	const handleDelete = async (id: string) => {
		dispatch({
			payload: id,
			type: NewAppTypes.SET_DELETE_IMAGE,
		});

		dispatch({
			payload: {
				file: undefined,
			},
			type: NewAppTypes.SET_PROFILE,
		});
	};

	const getFilteredItems = (
		selectedItems: {[key: string]: string}[],
		defaultItems: {[key: string]: string}[]
	) =>
		defaultItems?.filter(
			(defaultCategory) =>
				!selectedItems?.some(
					(category) => defaultCategory.value === category.value
				)
		);

	return (
		<div className="new-app-form-profile">
			<h5>App Info</h5>
			<hr />

			<div className="align-items-center d-flex mt-5">
				<UploadLogo
					onDeleteFile={handleDelete}
					onUpload={handleLogoUpload}
					uploadedFile={file}
				/>
			</div>

			<Form.FormControl>
				<Form.Label
					className="mt-5"
					htmlFor="name"
					info={tooltipInfo.name}
					required
				>
					{i18n.translate('name')}
				</Form.Label>

				<Form.Input
					maxLength={50}
					name="name"
					onChange={onChange}
					placeholder="Enter app name"
					value={name}
				/>
			</Form.FormControl>

			<Form.FormControl>
				<Form.Label
					className="mt-5"
					htmlFor="description"
					info={tooltipInfo.description}
					required
				>
					{i18n.translate('description')}
				</Form.Label>

				<Form.Input
					component="textarea"
					maxLength={150}
					name="description"
					onChange={onChange}
					placeholder="Enter app description"
					type="textarea"
					value={description}
				/>
			</Form.FormControl>

			<div className="form-multiselect">
				<Form.FormControl>
					<Form.Label
						className="mt-5"
						htmlFor="categories"
						info={tooltipInfo.categories}
						required
					>
						{i18n.translate('categories')}
					</Form.Label>

					<ClayMultiSelect
						{...{placeholder: 'Select categories'}}
						inputName="description-selector"
						items={categories}
						key={`cat-${categories.length}`}
						onChange={(value: string) =>
							onChangeMultiSelect({
								target: {
									name: 'categories',
									value,
								},
							})
						}
						onItemsChange={(value: {[key: string]: string}[]) =>
							onChange({
								target: {name: 'categories', value},
							})
						}
						sourceItems={getFilteredItems(
							categories,
							defaultSourceItems?.categories
						)}
						spritemap={getIconSpriteMap()}
						value={multiSelectText?.categories}
					/>
				</Form.FormControl>

				<Form.FormControl>
					<Form.Label
						className="mt-5"
						htmlFor="tags"
						info={tooltipInfo.tags}
						required
					>
						{i18n.translate('tags')}
					</Form.Label>

					<ClayMultiSelect
						{...{placeholder: 'Select tags'}}
						inputName="tags-selector"
						items={tags}
						key={`tags-${tags.length}`}
						onChange={(value: string) =>
							onChangeMultiSelect({
								target: {
									name: 'tags',
									value,
								},
							})
						}
						onItemsChange={(value: {[key: string]: string}[]) =>
							onChange({
								target: {name: 'tags', value},
							})
						}
						sourceItems={getFilteredItems(
							tags,
							defaultSourceItems?.tags
						)}
						spritemap={getIconSpriteMap()}
						value={multiSelectText?.tags}
					/>
				</Form.FormControl>
			</div>
		</div>
	);
};

export default Profile;
