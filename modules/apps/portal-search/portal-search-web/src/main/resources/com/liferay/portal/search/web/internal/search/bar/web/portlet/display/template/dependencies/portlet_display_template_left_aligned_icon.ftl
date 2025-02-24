<@liferay_aui.fieldset cssClass="search-bar">
	<@liferay_aui.input
		cssClass="search-bar-empty-search-input"
		name="emptySearchEnabled"
		type="hidden"
		value=searchBarPortletDisplayContext.isEmptySearchEnabled()
	/>

	<div class="input-group ${searchBarPortletDisplayContext.isLetTheUserChooseTheSearchScope()?then("search-bar-scope","search-bar-simple")}">
		<#if searchBarPortletDisplayContext.isLetTheUserChooseTheSearchScope()>
			<div class="input-group-item input-group-item-shrink input-group-prepend">
				<@clay["button"]
					aria\-label="${languageUtil.get(locale, 'search')}"
					cssClass="search-bar-submit-button"
					disabled=true
					displayType="secondary"
					icon="search"
					type="submit"
				/>
			</div>

			<@liferay_aui.select
				cssClass="search-bar-scope-select"
				disabled=true
				label=""
				name=htmlUtil.escape(searchBarPortletDisplayContext.getScopeParameterName())
				title="scope"
				useNamespace=false
				wrapperCssClass="input-group-item input-group-item-shrink input-group-prepend search-bar-search-select-wrapper"
			>
				<@liferay_aui.option
					label="this-site"
					selected=searchBarPortletDisplayContext.isSelectedCurrentSiteSearchScope()
					value=searchBarPortletDisplayContext.getCurrentSiteSearchScopeParameterString()
				/>

				<#if searchBarPortletDisplayContext.isAvailableEverythingSearchScope()>
					<@liferay_aui.option
						label="everything"
						selected=searchBarPortletDisplayContext.isSelectedEverythingSearchScope()
						value=searchBarPortletDisplayContext.getEverythingSearchScopeParameterString()
					/>
				</#if>
			</@>

			<#assign data = {
				"test-id": "searchInput"
			} />

			<@liferay_aui.input
				autoFocus=true
				autocomplete="off"
				cssClass="search-bar-keywords-input"
				data=data
				disabled=true
				label=""
				name=htmlUtil.escape(searchBarPortletDisplayContext.getKeywordsParameterName())
				placeholder=searchBarPortletDisplayContext.getInputPlaceholder()
				title=languageUtil.get(locale, "search")
				type="text"
				useNamespace=false
				value=htmlUtil.escape(searchBarPortletDisplayContext.getKeywords())
				wrapperCssClass="input-group-item input-group-append search-bar-keywords-input-wrapper"
			/>
		<#else>
			<div class="input-group-item search-bar-keywords-input-wrapper">
				<input
					autocomplete="off"
					class="form-control input-group-inset input-group-inset-before search-bar-keywords-input"
					data-qa-id="searchInput"
					disabled=true
					id="${namespace + stringUtil.randomId()}"
					name="${htmlUtil.escape(searchBarPortletDisplayContext.getKeywordsParameterName())}"
					placeholder="${searchBarPortletDisplayContext.getInputPlaceholder()}"
					title="${languageUtil.get(locale, "search")}"
					type="text"
					value="${htmlUtil.escape(searchBarPortletDisplayContext.getKeywords())}"
				/>

				<div class="input-group-inset-item input-group-inset-item-before">
					<@clay["button"]
						aria\-label="${languageUtil.get(locale, 'search')}"
						cssClass="search-bar-submit-button"
						disabled=true
						displayType="unstyled"
						icon="search"
						type="submit"
					/>
				</div>

				<@liferay_aui.input
					name=htmlUtil.escape(searchBarPortletDisplayContext.getScopeParameterName())
					type="hidden"
					value=searchBarPortletDisplayContext.getScopeParameterValue()
				/>
			</div>
		</#if>
	</div>
</@>