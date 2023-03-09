package com.mageddo.nativeimage.jna;

import com.mageddo.nativeimage.jna.stat.Stats;
import com.sun.jna.Platform;
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
        CLibrary.class,
        Stats.class
      );
//      final DynamicProxyRegistry x = ImageSingletons.lookup(DynamicProxyRegistry.class);
//      System.out.println("oiiiiiiiiiiiiiiiii");
//        x.addProxyClass(
//          CLibrary.class,
//          Stats.class
//        );
    }
  }

//  @Override
//  public void beforeCompilation(BeforeCompilationAccess access) {
//    beforeAnalysis(null);
//  }

//  @Override
//  public void beforeUniverseBuilding(BeforeUniverseBuildingAccess access) {
//    beforeAnalysis(null);
//  }
}
