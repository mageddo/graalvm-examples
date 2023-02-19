package com.mageddo.nativeimage.jna.stat;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @see https://man7.org/linux/man-pages/man2/lstat.2.html
 */
public interface Stats extends Library {

//  Stats INSTANCE = Native.loadLibrary((Platform.isLinux() ? Platform.C_LIBRARY_NAME : null), Stats.class);
  Stats INSTANCE = Native.loadLibrary("c", Stats.class);


  /**
   * int stat(const char *restrict pathname, struct stat *restrict statbuf);
   */
  int stat(String pathname, Stat.ByReference statbuf);

}
