package com.mageddo.nativeimage.jna;

import com.mageddo.nativeimage.jna.struct.MountContent;
import com.sun.jna.Pointer;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Calling some standard c functions,
 * passing and getting complex structs by reference, working with files and more
 */
public class LinuxMount {

  public static String findHostname() {
    final byte[] hostname = new byte[30];
    final int returnCode = CLibrary.INSTANCE.gethostname(hostname, hostname.length);
    Validate.isTrue(returnCode == 0, "Failed to get hostname");
    return String.format("%s - %d\n", new String(hostname), returnCode);
  }

  public static List<MountContent> findMounts() {
    final String mtabPath = "/etc/mtab";

    if (!Files.exists(Paths.get(mtabPath))) {
      System.err.printf("%s don't exists%n", mtabPath);
      return Collections.emptyList();
    }

    final Pointer mountFile = CLibrary.INSTANCE.fopen(mtabPath, "r");
    if (mountFile == null) {
      throw new UncheckedIOException(new IOException("File not exists: " + mtabPath));
    }
    MountContent.ByReference mtent;
    final List<MountContent> mounts = new ArrayList<>();
    while ((mtent = CLibrary.INSTANCE.getmntent(mountFile)) != null) {
      mounts.add(mtent);
    }
    return mounts;
  }

}
