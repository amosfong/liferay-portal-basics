<div class="c-mb-4 c-mt-4 search-total-label">
	<#if searchContainer.getTotal() == 1>
		${languageUtil.format(locale, "x-result-for-x", [searchContainer.getTotal(), "<strong>" + htmlUtil.escape(searchResultsPortletDisplayContext.getKeywords()) + "</strong>"], false)}
	<#else>
		${languageUtil.format(locale, "x-results-for-x", [searchContainer.getTotal(), "<strong>" + htmlUtil.escape(searchResultsPortletDisplayContext.getKeywords()) + "</strong>"], false)}
	</#if>
</div>

<div class="display-compact">
	<ul class="list-unstyled">
		<#if entries?has_content>
			<#list entries as entry>
				<li class="align-items-center d-flex">
					<div class="c-mb-2 c-mt-2">
						<a class="link-primary single-link" href="${entry.getViewURL()}">
							${entry.getHighlightedTitle()}
						</a>
					</div>

					<#if entry.isAssetRendererURLDownloadVisible()>
						<span
							class="lfr-portal-tooltip c-ml-1"
							title="${languageUtil.format(locale, 'download-x', ['(' + languageUtil.formatStorageSize(entry.getAssetRendererDownloadSize(), locale) + ')'])}"
						>
							<@clay.link
								aria\-label="${languageUtil.format(locale, 'download-x', [entry.getTitle()])}"
								cssClass="link-monospaced link-outline link-outline-borderless link-outline-secondary"
								displayType="secondary"
								href="${entry.getAssetRendererURLDownload()}"
							>
								<@clay.icon symbol="download" />
							</@clay.link>
						</span>
					</#if>
				</li>
			</#list>
		</#if>
	</ul>
</div>