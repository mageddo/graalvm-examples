package com.mageddo.nativeimage.jna.struct;

import com.sun.jna.Structure;

import java.util.ArrayList;
import java.util.List;

public class MountContent extends Structure {

    public String mnt_fsname;
    public String mnt_dir;
    public String mnt_type;
    public String mnt_opts;
    public int mnt_freq;
    public int mnt_passno;

    @Override
    protected List getFieldOrder() {
      List<String> fieds = new ArrayList<>();
      for (final var f : MountContent.class.getDeclaredFields()) {
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
