Actually is not possible generate native-image binary with okhttp because after
compile and running I'm getting the error below.

```
$ ./native-image.sh
...
[main] INFO com.mageddo.graalvm.okhttp.Application - starting up ....
Exception in thread "main" java.io.UncheckedIOException: javax.net.ssl.SSLException: Could not generate ECDH keypair
	at com.mageddo.graalvm.okhttp.OkHttpContributors.findContributors(OkHttpContributors.java:56)
	at com.mageddo.graalvm.okhttp.Application.main(Application.java:12)
Caused by: javax.net.ssl.SSLException: Could not generate ECDH keypair
	at sun.security.ssl.Alert.createSSLException(Alert.java:133)
	at sun.security.ssl.TransportContext.fatal(TransportContext.java:320)
	at sun.security.ssl.TransportContext.fatal(TransportContext.java:263)
	at sun.security.ssl.TransportContext.fatal(TransportContext.java:258)
	at sun.security.ssl.SSLSocketImpl.handleException(SSLSocketImpl.java:1314)
	at sun.security.ssl.SSLSocketImpl.startHandshake(SSLSocketImpl.java:408)
	at okhttp3.internal.connection.RealConnection.connectTls(RealConnection.java:336)
	at okhttp3.internal.connection.RealConnection.establishProtocol(RealConnection.java:300)
	at okhttp3.internal.connection.RealConnection.connect(RealConnection.java:185)
	at okhttp3.internal.connection.ExchangeFinder.findConnection(ExchangeFinder.java:224)
	at okhttp3.internal.connection.ExchangeFinder.findHealthyConnection(ExchangeFinder.java:108)
	at okhttp3.internal.connection.ExchangeFinder.find(ExchangeFinder.java:88)
	at okhttp3.internal.connection.Transmitter.newExchange(Transmitter.java:169)
	at okhttp3.internal.connection.ConnectInterceptor.intercept(ConnectInterceptor.java:41)
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:142)
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:117)
	at okhttp3.internal.cache.CacheInterceptor.intercept(CacheInterceptor.java:94)
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:142)
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:117)
	at okhttp3.internal.http.BridgeInterceptor.intercept(BridgeInterceptor.java:93)
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:142)
	at okhttp3.internal.http.RetryAndFollowUpInterceptor.intercept(RetryAndFollowUpInterceptor.java:88)
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:142)
	at okhttp3.internal.http.RealInterceptorChain.proceed(RealInterceptorChain.java:117)
	at okhttp3.RealCall.getResponseWithInterceptorChain(RealCall.java:221)
	at okhttp3.RealCall.execute(RealCall.java:81)
	at com.mageddo.graalvm.okhttp.OkHttpContributors.findContributors(OkHttpContributors.java:43)
	... 1 more
Caused by: java.lang.RuntimeException: Could not generate ECDH keypair
	at sun.security.ssl.ECDHKeyExchange$ECDHEPossession.<init>(ECDHKeyExchange.java:117)
	at sun.security.ssl.SSLKeyExchange$T13KeyAgreement.createPossession(SSLKeyExchange.java:575)
	at sun.security.ssl.SSLKeyExchange.createPossessions(SSLKeyExchange.java:88)
	at sun.security.ssl.KeyShareExtension$CHKeyShareProducer.produce(KeyShareExtension.java:263)
	at sun.security.ssl.SSLExtension.produce(SSLExtension.java:532)
	at sun.security.ssl.SSLExtensions.produce(SSLExtensions.java:250)
	at sun.security.ssl.ClientHello$ClientHelloKickstartProducer.produce(ClientHello.java:650)
	at sun.security.ssl.SSLHandshake.kickstart(SSLHandshake.java:515)
	at sun.security.ssl.ClientHandshakeContext.kickstart(ClientHandshakeContext.java:107)
	at sun.security.ssl.TransportContext.kickstart(TransportContext.java:227)
	at sun.security.ssl.SSLSocketImpl.startHandshake(SSLSocketImpl.java:395)
	... 22 more
Caused by: java.security.NoSuchAlgorithmException: EC KeyPairGenerator not available
	at java.security.KeyPairGenerator.getInstance(KeyPairGenerator.java:236)
	at sun.security.ssl.JsseJce.getKeyPairGenerator(JsseJce.java:237)
	at sun.security.ssl.ECDHKeyExchange$ECDHEPossession.<init>(ECDHKeyExchange.java:109)
	... 32 more

``` 
