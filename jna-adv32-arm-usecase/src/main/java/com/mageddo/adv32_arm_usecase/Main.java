package com.mageddo.adv32_arm_usecase;

import com.sun.jna.platform.unix.LibC;

public class Main {

  public static void main(String[] args) {
    System.out.println(LibC.INSTANCE.getenv("PATH"));

    System.out.println(NetworkRegistry.findNetworksIds());

//    final String key = "SYSTEM\\CurrentControlSet\\Control\\FileSystem";
//
//    System.out.println(SecondClass.KERNEL_32);
//
//    final String value = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, key, "LongPathsEnabled");
//    System.out.println(value);
  }
}
