package com.mageddo.nativeimage.jna;

import com.mageddo.nativeimage.jna.stat.LinuxFiles;
import com.mageddo.nativeimage.jna.struct.MountContent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
  public static void main(String[] args) {
    System.out.println("Aqui começa mais uma história de amor!");

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
}
