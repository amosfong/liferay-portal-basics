/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.preview.audio.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.processor.AudioProcessorUtil;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.configuration.test.util.ConfigurationTemporarySwapper;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.test.log.LogCapture;
import com.liferay.portal.test.log.LogEntry;
import com.liferay.portal.test.log.LoggerTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Dictionary;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Adolfo Pérez
 */
@RunWith(Arquillian.class)
public class DLAudioFFMPEGAudioConverterTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		UserTestUtil.setUser(TestPropsValues.getUser());

		_group = GroupTestUtil.addGroup();

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getGroupId(), 0);
	}

	@Test
	public void testDoesNotGenerateAudioPreview() throws Exception {
		_withDLAudioFFMPEGAudioConverterConfiguration(
			false,
			() -> {
				FileEntry fileEntry = _createAudioFileEntry("audio.wav");

				Assert.assertFalse(
					AudioProcessorUtil.hasAudio(fileEntry.getFileVersion()));
			});
	}

	@Test
	public void testDoesNotGenerateAudioPreviewIfTheAudioIsCorrupt()
		throws Exception {

		try (LogCapture logCapture = LoggerTestUtil.configureLog4JLogger(
				"com.liferay.document.library.preview.audio.internal." +
					"processor.AudioPreviewableDLProcessor",
				LoggerTestUtil.ERROR)) {

			_withDLAudioFFMPEGAudioConverterConfiguration(
				true,
				() -> {
					FileEntry fileEntry = _createAudioFileEntry(
						"audio_corrupt.wav");

					Assert.assertFalse(
						AudioProcessorUtil.hasAudio(
							fileEntry.getFileVersion()));
				});

			List<LogEntry> logEntries = logCapture.getLogEntries();

			Assert.assertFalse(logEntries.isEmpty());

			for (LogEntry logEntry : logEntries) {
				String logEntryMessage = logEntry.getMessage();

				Assert.assertTrue(
					logEntryMessage.contains("Unable to process"));

				Throwable throwable = logEntry.getThrowable();

				String throwableMessage = throwable.getMessage();

				Assert.assertTrue(
					throwableMessage.contains(
						"java.io.FileNotFoundException") ||
					throwableMessage.contains("ffmpeg"));
			}
		}
	}

	@Test
	public void testGeneratesAudioPreviewIfEnabled() throws Exception {
		_withDLAudioFFMPEGAudioConverterConfiguration(
			true,
			() -> {
				FileEntry fileEntry = _createAudioFileEntry("audio.wav");

				Assert.assertTrue(
					AudioProcessorUtil.hasAudio(fileEntry.getFileVersion()));

				long wavPreviewFileSize = AudioProcessorUtil.getPreviewFileSize(
					fileEntry.getFileVersion(), "mp3");

				Assert.assertTrue(wavPreviewFileSize > 0);
			});
	}

	private FileEntry _createAudioFileEntry(String fileName) throws Exception {
		return DLAppServiceUtil.addFileEntry(
			null, _group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, fileName,
			MimeTypesUtil.getContentType(fileName), "audio",
			StringUtil.randomString(), StringUtil.randomString(),
			StringUtil.randomString(),
			FileUtil.getBytes(getClass(), "dependencies/" + fileName), null,
			null, null, _serviceContext);
	}

	private void _withDLAudioFFMPEGAudioConverterConfiguration(
			boolean enabled, UnsafeRunnable<Exception> unsafeRunnable)
		throws Exception {

		Dictionary<String, Object> dictionary =
			HashMapDictionaryBuilder.<String, Object>put(
				"enabled", enabled
			).build();

		try (ConfigurationTemporarySwapper configurationTemporarySwapper =
				new ConfigurationTemporarySwapper(
					"com.liferay.document.library.preview.audio.internal." +
						"configuration." +
							"DLAudioFFMPEGAudioConverterConfiguration",
					dictionary)) {

			unsafeRunnable.run();
		}
	}

	@DeleteAfterTestRun
	private Group _group;

	private ServiceContext _serviceContext;

}