package com.mageddo.adv32_arm_usecase;


import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

import java.net.NetworkInterface;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NetworkRegistry {

  public static final String DNS_SERVER_ATTR = "NameServer";

  /**
   * @param networkId something like {ab01ba7a-f236-4f47-933f-46b48affecd4}
   * @see https://stackoverflow.com/a/17819465/2979435
   * https://stackoverflow.com/questions/62289/read-write-to-windows-registry-using-java
   */
  public static List<String> findStaticDnsServers(String networkId) {
    final String str = findNetworkStringValue(networkId, DNS_SERVER_ATTR);
    return Stream
      .of(str.split(","))
      .collect(Collectors.toList());
  }


  public static Set<String> findNetworksIds() {
    return Stream.of(Advapi32Util.registryGetKeys(
        WinReg.HKEY_LOCAL_MACHINE,
        "SYSTEM\\CurrentControlSet\\Services\\Tcpip\\Parameters\\Interfaces"
      ))
      .collect(Collectors.toSet());
  }


  public static NetworkInterface findNetworkInterface(String networkId) {
    final String r = findIpAddress(networkId);
    final String r2 = findDhcpIpAddress(networkId);
    final List<String> r3 = findStaticDnsServers(networkId);
    System.out.println(r);
    System.out.println(r2);
    System.out.println(r3);
    return null;
  }

  public static String findDhcpIpAddress(String networkId) {
    return findNetworkStringValue(networkId, "DhcpIPAddress");
  }

  public static String findIpAddress(String networkId) {
    return findNetworkFirstArrValue(networkId, "IPAddress");
  }

  public static void updateDnsServer(String networkId, List<String> dnsServer) {
   final String key = buildKey(networkId);
    Advapi32Util.registrySetStringValue(WinReg.HKEY_LOCAL_MACHINE, key, DNS_SERVER_ATTR, String.join(",", dnsServer));
  }

  private static String findNetworkFirstArrValue(final String networkId, final String property) {
    return findFirstOrNull(Advapi32Util.registryGetStringArray(WinReg.HKEY_LOCAL_MACHINE, buildKey(networkId), property));
  }

  private static String findNetworkStringValue(final String networkId, final String property) {
    return Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, buildKey(networkId), property);
  }

  private static String findFirstOrNull(final String[] arr) {
    return arr == null ? null : Stream.of(arr)
      .findFirst()
      .orElse(null);
  }

  private static String buildKey(String networkId) {
    return String.format("SYSTEM\\CurrentControlSet\\Services\\Tcpip\\Parameters\\Interfaces\\%s", networkId);
  }

}
