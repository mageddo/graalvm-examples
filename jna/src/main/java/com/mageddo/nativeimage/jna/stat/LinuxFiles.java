package com.mageddo.nativeimage.jna.stat;

import org.apache.commons.lang3.Validate;

import java.nio.file.Path;

/**
 * ./sysdeps/mach/hurd/bits/stat.h:#define	__S_IFSOCK	0140000	// Socket.
 * ./sysdeps/unix/sysv/linux/bits/stat.h:#define	__S_IFMT	0170000	// These bits determine file type
 * <p>
 * ./io/sys/stat.h:#define	__S_ISTYPE(mode, mask)	(((mode) & __S_IFMT) == (mask))
 * ./io/sys/stat.h:# define S_ISSOCK(mode) __S_ISTYPE((mode), __S_IFSOCK)
 */
public class LinuxFiles {

  static int __S_IFSOCK = 0140000;
  static int __S_IFMT = 0170000;

  public static boolean isUnixSocket(Path path) {
    final Stat.ByReference stat = new Stat.ByReference();
    final int res = Stats.INSTANCE.stat64(path.toString(), stat);
    Validate.isTrue(res == 0, "Failed to get file permission details: %s", path);
    return S_ISSOCK(stat.st_mode.intValue());
  }

  static boolean __S_ISTYPE(int mode, int mask) {
    return ((mode) & __S_IFMT) == (mask);
  }

  static boolean S_ISSOCK(int mode) {
    return __S_ISTYPE((mode), __S_IFSOCK);
  }

}
