package com.mageddo.adv32_arm_usecase;

import com.sun.jna.Platform;
import com.sun.jna.platform.unix.LibC;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeProxyCreation;


public class Features implements Feature {

  @Override
  public void beforeAnalysis(BeforeAnalysisAccess access) {
    // conditional to register the Proxy class because it will initialize it at build
    // so will intialize .so load and it will break the build at a different OS.
    // it's useful when you're creating an application which will be built for different Operational Systems so
    // some JNA classes will be used at Linux and Others in Windows for example
    System.out.println("JNA Sample Feature, OS: " + System.getProperty("os.name"));
    if (Platform.isLinux()) {
      System.out.println("> Linux features");
      RuntimeProxyCreation.register(
        LibC.class
      );
    }
  }
}
