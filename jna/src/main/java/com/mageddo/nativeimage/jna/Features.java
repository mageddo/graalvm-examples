package com.mageddo.nativeimage.jna;

import com.sun.jna.Platform;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeProxyCreation;

public class Features implements Feature {
  @Override
  public void beforeAnalysis(BeforeAnalysisAccess access) {
    // conditional to register the Proxy class because it will initialize it at build
    // so will intialize .so load and it will break the build at a different OS.
    // it's useful when you're creating an application which will be built for different Operation Systems so
    // some JNA classes will be used at Linux and Others in Windows for example
    if (Platform.isLinux()) {
      RuntimeProxyCreation.register(
        com.mageddo.nativeimage.jna.LinuxMount.CLibrary.class,
        com.mageddo.nativeimage.jna.stat.Stats.class
      );
    }
  }
}
