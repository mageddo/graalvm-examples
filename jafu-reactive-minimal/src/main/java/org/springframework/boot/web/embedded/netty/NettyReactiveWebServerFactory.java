package org.springframework.boot.web.embedded.netty;

import org.springframework.boot.web.reactive.server.ConfigurableReactiveWebServerFactory;
import org.springframework.boot.web.server.*;
import org.springframework.http.server.reactive.HttpHandler;

import java.net.InetAddress;
import java.util.Set;

// keep graalvm compiling when netty is not on classpath
public class NettyReactiveWebServerFactory implements ConfigurableReactiveWebServerFactory {
	@Override
	public WebServer getWebServer(HttpHandler httpHandler) {
		return null;
	}

	@Override
	public void setPort(int port) {

	}

	@Override
	public void setAddress(InetAddress address) {

	}

	@Override
	public void setErrorPages(Set<? extends ErrorPage> errorPages) {

	}

	@Override
	public void setSsl(Ssl ssl) {

	}

	@Override
	public void setSslStoreProvider(SslStoreProvider sslStoreProvider) {

	}

	@Override
	public void setHttp2(Http2 http2) {

	}

	@Override
	public void setCompression(Compression compression) {

	}

	@Override
	public void setServerHeader(String serverHeader) {

	}

	@Override
	public void addErrorPages(ErrorPage... errorPages) {

	}
}
