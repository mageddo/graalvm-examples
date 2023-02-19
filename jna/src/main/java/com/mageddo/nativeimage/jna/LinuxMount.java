package com.mageddo.nativeimage.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
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
    final var mtabPath = "/etc/mtab";
    final var mountFile = CLibrary.INSTANCE.fopen(mtabPath, "r");
    if (mountFile == null) {
      throw new UncheckedIOException(new IOException("File not exists: " + mtabPath));
    }
    MountContent.ByReference mtent;
    final var mounts = new ArrayList<MountContent>();
    while ((mtent = CLibrary.INSTANCE.getmntent(mountFile)) != null) {
      mounts.add(mtent);
    }
    return mounts;
  }

  public interface CLibrary extends Library {

    CLibrary INSTANCE = Native.loadLibrary("c", CLibrary.class);

    int gethostname(byte[] name, int nameLength);

    Pointer fopen(String name, String mode);

    MountContent.ByReference getmntent(Pointer FILE);
  }

  public static class MountContent extends Structure {

    public String mnt_fsname;
    public String mnt_dir;
    public String mnt_type;
    public String mnt_opts;
    public int mnt_freq;
    public int mnt_passno;

    @Override
    protected List getFieldOrder() {
      List<String> fieds = new ArrayList<>();
      for (final Field f : MountContent.class.getDeclaredFields()) {
        if (!f.isSynthetic())
          fieds.add(f.getName());
      }
      return fieds;
    }

    public static class ByReference extends MountContent implements Structure.ByReference {
    }

    public static class ByValue extends MountContent implements Structure.ByValue {
    }
  }
}
