package com.mageddo.adv32_arm_usecase;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class Main {
  public static void main(String[] args) {
    final String key = "SYSTEM\\CurrentControlSet\\Control\\FileSystem";
    final String value = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, key, "LongPathsEnabled");
    System.out.println(value);
  }
}
