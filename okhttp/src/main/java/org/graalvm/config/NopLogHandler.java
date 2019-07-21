package org.graalvm.config;

import org.graalvm.nativeimage.LogHandler;
import org.graalvm.nativeimage.c.type.CCharPointer;
import org.graalvm.word.UnsignedWord;

public class NopLogHandler implements LogHandler {
	@Override
	public void log(CCharPointer bytes, UnsignedWord length) {
		// nop
	}

	@Override
	public void flush() {
		// nop
	}

	@Override
	public void fatalError() {
		// nop
	}
}
