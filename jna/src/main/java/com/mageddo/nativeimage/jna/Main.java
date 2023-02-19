package com.mageddo.nativeimage.jna;

public class Main {
  public static void main(String[] args) {
    System.out.println("Aqui começa mais uma história de amor!");

    final var hostname = LinuxMount.findHostname();
    System.out.printf("hostname=%s", hostname);
  }
}
