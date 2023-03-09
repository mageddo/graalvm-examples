package com.mageddo.nativeimage.jna;

import com.mageddo.nativeimage.jna.struct.MountContent;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface CLibrary extends Library {

  CLibrary INSTANCE = Native.loadLibrary("c", CLibrary.class);

  int gethostname(byte[] name, int nameLength);

  Pointer fopen(String name, String mode);

  MountContent.ByReference getmntent(Pointer FILE);
}
