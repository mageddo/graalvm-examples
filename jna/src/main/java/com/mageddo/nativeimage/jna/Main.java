package com.mageddo.nativeimage.jna;

public class Main {
  public static void main(String[] args) {
    System.out.println("Aqui começa mais uma história de amor!");

    System.out.println("> Hostname Find");
    final var hostname = LinuxMount.findHostname();
    System.out.printf("hostname=%s", hostname);

    System.out.println("------------------------------");
    System.out.println();

    System.out.println("> Linux Mounts Find");
    final var firstMount = LinuxMount.findMounts()
      .stream()
      .findFirst()
      .orElse(null);
    System.out.printf("First Mount Found: %s%n", firstMount);

    System.out.println("------------------------------");
    System.out.println();
  }
}
