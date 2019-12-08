DNS resolution is currently not working on alpine and scratch docker images at least, it works great on a
debian docker container or in a normal linux distro like 
Ubuntu or Debian

```
./native-image.sh
Exception in thread "main" java.net.UnknownHostException: google.com: System error
	at com.oracle.svm.jni.JNIJavaCallWrappers.jniInvoke_VA_LIST:Ljava_net_UnknownHostException_2_0002e_0003cinit_0003e_00028Ljava_lang_String_2_00029V(JNIJavaCallWrappers.java:0)
	at java.net.Inet4AddressImpl.lookupAllHostAddr(Inet4AddressImpl.java)
	at java.net.InetAddress$PlatformNameService.lookupAllHostAddr(InetAddress.java:929)
	at java.net.InetAddress.getAddressesFromNameService(InetAddress.java:1515)
	at java.net.InetAddress$NameServiceAddresses.get(InetAddress.java:848)
	at java.net.InetAddress.getAllByName0(InetAddress.java:1505)
	at java.net.InetAddress.getAllByName(InetAddress.java:1364)
	at java.net.InetAddress.getAllByName(InetAddress.java:1298)
	at com.mageddo.graalvm.dns.resolution.Application.main(Application.java:9)
```

This is because graal requires full `libc`, see [this issue][1] for details

[1]: https://github.com/oracle/graal/issues/1151
