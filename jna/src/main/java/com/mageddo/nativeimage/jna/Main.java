package com.mageddo.nativeimage.jna;

import com.mageddo.nativeimage.jna.stat.LinuxFiles;
import com.mageddo.nativeimage.jna.struct.MountContent;
import com.sun.jna.Library;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class Main {
  public static void main(String[] args) throws Exception {

    findFunc();

    System.out.println("Aqui começa mais uma história de amor!");

    System.out.printf("> Is Linux: %b%n", Platform.isLinux());
    System.out.printf("> C library name: %s%n", Platform.C_LIBRARY_NAME);
    System.out.println("-------------------------");
    System.out.println();

    System.out.println("> Hostname Find");
    final String hostname = LinuxMount.findHostname();
    System.out.printf("hostname=%s", hostname);

    System.out.println("------------------------------");
    System.out.println();

    System.out.println("> Linux Mounts Find");
    final MountContent firstMount = LinuxMount.findMounts()
      .stream()
      .findFirst()
      .orElse(null);
    System.out.printf("First Mount Found: %s%n", firstMount);

    System.out.println("------------------------------");
    System.out.println();

    final Path sockFile = Paths.get("/var/run/docker.sock");
    if (Files.exists(sockFile)) {
      final boolean socket = LinuxFiles.isUnixSocket(sockFile);
      System.out.printf("> \"%s\" is socket: %s%n", sockFile, socket);
      System.out.println("------------------------------");
      System.out.println();
    }

  }

  private static void findFunc() {
    try {

      Library.Handler handler = new Library.Handler("c", Library.class, Collections.emptyMap());
      final NativeLibrary nativeLibrary = handler.getNativeLibrary();

      try {
        final Method m = NativeLibrary.class.getDeclaredMethod("getSymbolAddress", String.class);
        m.setAccessible(true);
        System.out.printf("stat2=%s%n", m.invoke(nativeLibrary, "stat"));
      } catch (Exception e) {
        System.err.println("m1 failed: " + e.getMessage());
      }
      try {
        System.out.printf("stat2=%s%n", nativeLibrary.getFunction("stat64"));
      } catch (Exception e) {
        System.err.println("m2 failed: " + e.getMessage());
      }
      System.out.println("----------------------------------------");
      System.out.println();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
