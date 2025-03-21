/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.io;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;

import java.io.IOException;
import java.io.OutputStream;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class OutputStreamWriterTest {

	@ClassRule
	public static final CodeCoverageAssertor codeCoverageAssertor =
		CodeCoverageAssertor.INSTANCE;

	@Test
	public void testClose() throws IOException {

		// Normal close

		MarkerOutputStream markerOutputStream = new MarkerOutputStream();

		try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
				markerOutputStream)) {

			Assert.assertFalse(markerOutputStream._closed);

			outputStreamWriter.close();

			Assert.assertTrue(markerOutputStream._closed);

			markerOutputStream._closed = false;

			try {
				outputStreamWriter.write(0);

				Assert.fail();
			}
			catch (IOException ioException) {
				Assert.assertEquals("Stream closed", ioException.getMessage());
			}
		}

		Assert.assertFalse(markerOutputStream._closed);

		// Exception close

		final IOException ioException1 = new IOException();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			new UnsyncByteArrayOutputStream() {

				@Override
				public void close() throws IOException {
					throw ioException1;
				}

			});

		// First close

		try {
			outputStreamWriter.close();

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException2, ioException1);
		}

		// Second close to check first close indeed changed the state

		outputStreamWriter.close();
	}

	@Test
	public void testConstructor() {
		DummyOutputStream dummyOutputStream = new DummyOutputStream();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			dummyOutputStream);

		Assert.assertSame(
			dummyOutputStream, _getOutputStream(outputStreamWriter));

		Assert.assertSame(
			StringPool.DEFAULT_CHARSET_NAME, outputStreamWriter.getEncoding());
		Assert.assertEquals(
			_getDefaultOutputBufferSize(),
			_getOutputBufferSize(outputStreamWriter));
		Assert.assertFalse(_isAutoFlush(outputStreamWriter));

		outputStreamWriter = new OutputStreamWriter(dummyOutputStream, null);

		Assert.assertSame(
			dummyOutputStream, _getOutputStream(outputStreamWriter));
		Assert.assertSame(
			StringPool.DEFAULT_CHARSET_NAME, outputStreamWriter.getEncoding());
		Assert.assertEquals(
			_getDefaultOutputBufferSize(),
			_getOutputBufferSize(outputStreamWriter));
		Assert.assertFalse(_isAutoFlush(outputStreamWriter));

		String encoding = "US-ASCII";

		outputStreamWriter = new OutputStreamWriter(
			dummyOutputStream, encoding);

		Assert.assertSame(
			dummyOutputStream, _getOutputStream(outputStreamWriter));
		Assert.assertSame(encoding, outputStreamWriter.getEncoding());
		Assert.assertEquals(
			_getDefaultOutputBufferSize(),
			_getOutputBufferSize(outputStreamWriter));
		Assert.assertFalse(_isAutoFlush(outputStreamWriter));

		outputStreamWriter = new OutputStreamWriter(
			dummyOutputStream, encoding, true);

		Assert.assertSame(
			dummyOutputStream, _getOutputStream(outputStreamWriter));
		Assert.assertSame(encoding, outputStreamWriter.getEncoding());
		Assert.assertEquals(
			_getDefaultOutputBufferSize(),
			_getOutputBufferSize(outputStreamWriter));
		Assert.assertTrue(_isAutoFlush(outputStreamWriter));

		outputStreamWriter = new OutputStreamWriter(
			dummyOutputStream, encoding, 32);

		Assert.assertSame(
			dummyOutputStream, _getOutputStream(outputStreamWriter));
		Assert.assertSame(encoding, outputStreamWriter.getEncoding());
		Assert.assertEquals(2, _getInputCharBufferSize(outputStreamWriter));
		Assert.assertEquals(32, _getOutputBufferSize(outputStreamWriter));
		Assert.assertFalse(_isAutoFlush(outputStreamWriter));

		outputStreamWriter = new OutputStreamWriter(
			dummyOutputStream, encoding, 32, true);

		Assert.assertSame(
			dummyOutputStream, _getOutputStream(outputStreamWriter));
		Assert.assertSame(encoding, outputStreamWriter.getEncoding());
		Assert.assertEquals(2, _getInputCharBufferSize(outputStreamWriter));
		Assert.assertEquals(32, _getOutputBufferSize(outputStreamWriter));
		Assert.assertTrue(_isAutoFlush(outputStreamWriter));

		try {
			new OutputStreamWriter(dummyOutputStream, encoding, 3, true);

			Assert.fail();
		}
		catch (IllegalArgumentException illegalArgumentException) {
			Assert.assertEquals(
				"Output buffer size 3 is less than 4",
				illegalArgumentException.getMessage());
		}
	}

	@Test
	public void testFlush() throws IOException {
		MarkerOutputStream markerOutputStream = new MarkerOutputStream();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			markerOutputStream);

		Assert.assertFalse(markerOutputStream._flushed);

		outputStreamWriter.flush();

		Assert.assertTrue(markerOutputStream._flushed);

		outputStreamWriter.write('a');

		outputStreamWriter.flush();

		Assert.assertTrue(markerOutputStream._flushed);
	}

	@Test
	public void testFlushEncoder() throws IOException {
		MarkerOutputStream markerOutputStream = new MarkerOutputStream();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			markerOutputStream);

		// Flush encoder overflow

		final AtomicInteger flushCounter = new AtomicInteger();

		ReflectionTestUtil.setFieldValue(
			outputStreamWriter, "_charsetEncoder",
			new CharsetEncoderWrapper(
				ReflectionTestUtil.getFieldValue(
					outputStreamWriter, "_charsetEncoder")) {

				@Override
				protected CoderResult implFlush(ByteBuffer out) {
					int count = flushCounter.getAndIncrement();

					if (count == 0) {
						return CoderResult.OVERFLOW;
					}

					return super.implFlush(out);
				}

			});

		outputStreamWriter.close();

		Assert.assertEquals(2, flushCounter.get());

		// Flush encoder error

		outputStreamWriter = new OutputStreamWriter(markerOutputStream);

		ReflectionTestUtil.setFieldValue(
			outputStreamWriter, "_charsetEncoder",
			new CharsetEncoderWrapper(
				ReflectionTestUtil.getFieldValue(
					outputStreamWriter, "_charsetEncoder")) {

				@Override
				protected CoderResult implFlush(ByteBuffer out) {
					return CoderResult.malformedForLength(1);
				}

			});

		try {
			outputStreamWriter.close();
		}
		catch (MalformedInputException malformedInputException) {
			Assert.assertEquals(1, malformedInputException.getInputLength());
		}
	}

	@Test
	public void testWriteCharArray() throws IOException {
		_testWriteCharArray(false);
		_testWriteCharArray(true);
	}

	@Test
	public void testWriteError() throws IOException {
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			new DummyOutputStream(), "US-ASCII");

		CharsetEncoder charsetEncoder = ReflectionTestUtil.getFieldValue(
			outputStreamWriter, "_charsetEncoder");

		charsetEncoder.onUnmappableCharacter(CodingErrorAction.REPORT);

		try {
			outputStreamWriter.write("测试");

			Assert.fail();
		}
		catch (UnmappableCharacterException unmappableCharacterException) {
			Assert.assertEquals(
				1, unmappableCharacterException.getInputLength());
		}
	}

	@Test
	public void testWriteInt() throws IOException {
		MarkerOutputStream markerOutputStream = new MarkerOutputStream();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			markerOutputStream);

		outputStreamWriter.write('a');

		outputStreamWriter.flush();

		Assert.assertEquals((byte)'a', markerOutputStream._bytes[0]);
		Assert.assertEquals(1, markerOutputStream._length);
		Assert.assertEquals(0, markerOutputStream._offset);
	}

	@Test
	public void testWriteIntUnicodeSurrogatePair() throws IOException {

		// writeInt + writeInt

		_testUnicodeSurrogatePair(
			(outputStreamWriter, surrogatePair) -> {
				outputStreamWriter.write(surrogatePair[0]);
				outputStreamWriter.write(surrogatePair[1]);
			});

		// writeInt + writeCharArray

		_testUnicodeSurrogatePair(
			(outputStreamWriter, surrogatePair) -> {
				outputStreamWriter.write(surrogatePair[0]);
				outputStreamWriter.write(new char[] {surrogatePair[1]});
			});

		// writeCharArray + writeInt

		_testUnicodeSurrogatePair(
			(outputStreamWriter, surrogatePair) -> {
				outputStreamWriter.write(new char[] {surrogatePair[0]});
				outputStreamWriter.write(surrogatePair[1]);
			});

		// writeCharArray + writeCharArray

		_testUnicodeSurrogatePair(
			(outputStreamWriter, surrogatePair) -> {
				outputStreamWriter.write(new char[] {surrogatePair[0]});
				outputStreamWriter.write(new char[] {surrogatePair[1]});
			});
	}

	@Test
	public void testWriteString() throws IOException {
		_testWriteString(false);
		_testWriteString(true);
	}

	@Test
	public void testWriteWithExceptionThrownFromOutputStream()
		throws IOException {

		IOException ioException1 = new IOException();

		AtomicBoolean throwIOException = new AtomicBoolean();

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream() {

				@Override
				public void write(byte[] bytes, int offset, int length) {
					if (throwIOException.get()) {
						ReflectionUtil.throwException(ioException1);
					}

					super.write(bytes, offset, length);
				}

			};

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			unsyncByteArrayOutputStream, "UTF-8", 4, true);

		// Fill up the ByteBuffer

		throwIOException.set(true);

		try {
			outputStreamWriter.write('a');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		try {
			outputStreamWriter.write('b');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		try {
			outputStreamWriter.write('c');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		try {
			outputStreamWriter.write('d');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		// Fill up the CharBuffer

		try {
			outputStreamWriter.write('e');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		try {
			outputStreamWriter.write('f');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		// No space in buffer, discard

		try {
			outputStreamWriter.write('g');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		try {
			outputStreamWriter.write('h');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		Assert.assertEquals("", unsyncByteArrayOutputStream.toString());

		try {
			outputStreamWriter.flush();

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		Assert.assertEquals("", unsyncByteArrayOutputStream.toString());

		// Recovered, data within buffer size has been preserved

		throwIOException.set(false);

		outputStreamWriter.write('i');

		Assert.assertEquals("abcdefi", unsyncByteArrayOutputStream.toString());

		// Cut in between surrogate pair

		unsyncByteArrayOutputStream.reset();

		char[] surrogatePair = Character.toChars(0x2363A);

		Assert.assertEquals(
			Arrays.toString(surrogatePair), 2, surrogatePair.length);

		throwIOException.set(true);

		// Fill up the ByteBuffer

		try {
			outputStreamWriter.write("abcd".toCharArray());

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		// Fill up the CharBuffer

		try {
			outputStreamWriter.write('e');

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		try {
			outputStreamWriter.write(surrogatePair[0]);

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		// No space in buffer, discard

		try {
			outputStreamWriter.write(surrogatePair[1]);

			Assert.fail();
		}
		catch (IOException ioException2) {
			Assert.assertSame(ioException1, ioException2);
		}

		throwIOException.set(false);

		// New write must discard leftover surrogate char

		outputStreamWriter.write('f');

		Assert.assertEquals("abcdef", unsyncByteArrayOutputStream.toString());
	}

	private int _getDefaultOutputBufferSize() {
		return ReflectionTestUtil.getFieldValue(
			OutputStreamWriter.class, "_DEFAULT_OUTPUT_BUFFER_SIZE");
	}

	private int _getInputCharBufferSize(OutputStreamWriter outputStreamWriter) {
		CharBuffer inputCharBuffer = ReflectionTestUtil.getFieldValue(
			outputStreamWriter, "_inputCharBuffer");

		return inputCharBuffer.capacity();
	}

	private int _getOutputBufferSize(OutputStreamWriter outputStreamWriter) {
		ByteBuffer outputBuffer = ReflectionTestUtil.getFieldValue(
			outputStreamWriter, "_outputByteBuffer");

		return outputBuffer.capacity();
	}

	private OutputStream _getOutputStream(
		OutputStreamWriter outputStreamWriter) {

		return ReflectionTestUtil.getFieldValue(
			outputStreamWriter, "_outputStream");
	}

	private boolean _isAutoFlush(OutputStreamWriter outputStreamWriter) {
		return ReflectionTestUtil.getFieldValue(
			outputStreamWriter, "_autoFlush");
	}

	private void _testUnicodeSurrogatePair(
			SurrogatePairConsumer surrogatePairConsumer)
		throws IOException {

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			unsyncByteArrayOutputStream, "UTF-8");

		char[] surrogatePair = Character.toChars(0x2363A);

		Assert.assertEquals(
			Arrays.toString(surrogatePair), 2, surrogatePair.length);

		surrogatePairConsumer.accept(outputStreamWriter, surrogatePair);

		outputStreamWriter.flush();

		String decodedString = new String(
			unsyncByteArrayOutputStream.toByteArray(), "UTF-8");

		Assert.assertArrayEquals(surrogatePair, decodedString.toCharArray());
	}

	private void _testWriteCharArray(boolean autoFlush) throws IOException {
		final AtomicBoolean flushed = new AtomicBoolean();

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream() {

				@Override
				public void flush() {
					flushed.set(true);
				}

			};

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			unsyncByteArrayOutputStream, "US-ASCII", 4, autoFlush);

		outputStreamWriter.write("abcdefg".toCharArray(), 1, 5);

		Assert.assertFalse(flushed.get());

		if (!autoFlush) {
			outputStreamWriter.flush();

			Assert.assertTrue(flushed.get());

			flushed.set(false);
		}

		Assert.assertArrayEquals(
			new byte[] {'b', 'c', 'd', 'e', 'f'},
			unsyncByteArrayOutputStream.toByteArray());

		unsyncByteArrayOutputStream.reset();

		outputStreamWriter = new OutputStreamWriter(
			unsyncByteArrayOutputStream, "US-ASCII", autoFlush);

		outputStreamWriter.write("abc".toCharArray());

		Assert.assertFalse(flushed.get());

		if (!autoFlush) {
			outputStreamWriter.flush();

			Assert.assertTrue(flushed.get());

			flushed.set(false);
		}

		Assert.assertArrayEquals(
			new byte[] {'a', 'b', 'c'},
			unsyncByteArrayOutputStream.toByteArray());
	}

	private void _testWriteString(boolean autoFlush) throws IOException {
		final AtomicBoolean flushed = new AtomicBoolean();

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream() {

				@Override
				public void flush() {
					flushed.set(true);
				}

			};

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
			unsyncByteArrayOutputStream, "US-ASCII", 4, autoFlush);

		outputStreamWriter.write("abcdefg", 1, 5);

		Assert.assertFalse(flushed.get());

		if (!autoFlush) {
			outputStreamWriter.flush();

			Assert.assertTrue(flushed.get());

			flushed.set(false);
		}

		Assert.assertArrayEquals(
			new byte[] {'b', 'c', 'd', 'e', 'f'},
			unsyncByteArrayOutputStream.toByteArray());

		unsyncByteArrayOutputStream.reset();

		outputStreamWriter = new OutputStreamWriter(
			unsyncByteArrayOutputStream, "US-ASCII", 4, autoFlush);

		outputStreamWriter.write("abcdefg", 1, 5);

		Assert.assertFalse(flushed.get());

		if (!autoFlush) {
			outputStreamWriter.flush();

			Assert.assertTrue(flushed.get());

			flushed.set(false);
		}

		Assert.assertArrayEquals(
			new byte[] {'b', 'c', 'd', 'e', 'f'},
			unsyncByteArrayOutputStream.toByteArray());

		unsyncByteArrayOutputStream.reset();

		outputStreamWriter = new OutputStreamWriter(
			unsyncByteArrayOutputStream, "US-ASCII", autoFlush);

		outputStreamWriter.write("abc");

		Assert.assertFalse(flushed.get());

		if (!autoFlush) {
			outputStreamWriter.flush();

			Assert.assertTrue(flushed.get());

			flushed.set(false);
		}

		Assert.assertArrayEquals(
			new byte[] {'a', 'b', 'c'},
			unsyncByteArrayOutputStream.toByteArray());
	}

	private static class CharsetEncoderWrapper extends CharsetEncoder {

		@Override
		public boolean canEncode(char c) {
			return _charsetEncoder.canEncode(c);
		}

		@Override
		public boolean canEncode(CharSequence cs) {
			return _charsetEncoder.canEncode(cs);
		}

		@Override
		public boolean isLegalReplacement(byte[] replacement) {
			return true;
		}

		@Override
		public CodingErrorAction malformedInputAction() {
			return _charsetEncoder.malformedInputAction();
		}

		@Override
		public CodingErrorAction unmappableCharacterAction() {
			return _charsetEncoder.unmappableCharacterAction();
		}

		@Override
		protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
			return ReflectionTestUtil.invoke(
				_charsetEncoder, "encodeLoop",
				new Class<?>[] {CharBuffer.class, ByteBuffer.class}, in, out);
		}

		private CharsetEncoderWrapper(CharsetEncoder charsetEncoder) {
			super(
				charsetEncoder.charset(), charsetEncoder.averageBytesPerChar(),
				charsetEncoder.maxBytesPerChar(), charsetEncoder.replacement());

			_charsetEncoder = charsetEncoder;
		}

		private final CharsetEncoder _charsetEncoder;

	}

	private static class MarkerOutputStream extends OutputStream {

		@Override
		public void close() {
			_closed = true;
		}

		@Override
		public void flush() {
			_flushed = true;
		}

		@Override
		public void write(byte[] bytes, int offset, int length) {
			_bytes = bytes;
			_offset = offset;
			_length = length;
		}

		@Override
		public void write(int b) {
		}

		private byte[] _bytes;
		private boolean _closed;
		private boolean _flushed;
		private int _length;
		private int _offset;

	}

	private interface SurrogatePairConsumer {

		public void accept(
				OutputStreamWriter outputStreamWriter, char[] surrogatePair)
			throws IOException;

	}

}