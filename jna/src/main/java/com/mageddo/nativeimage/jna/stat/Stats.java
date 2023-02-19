package com.mageddo.nativeimage.jna.stat;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @see https://man7.org/linux/man-pages/man2/lstat.2.html
 */
public interface Stats extends Library {

  Stats INSTANCE = Native.loadLibrary(Platform.C_LIBRARY_NAME, Stats.class);

  int syscall(int number, Object... args);

  /**
   * Calling stat function using syscall cause stat is not available in libc 2.24
   */
  default int stat(String pathname, Stat statbuf) {
    return this.syscall(4, pathname, statbuf);
  }

}
