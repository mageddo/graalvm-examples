package com.mageddo;

import sun.security.ssl.SSLSocketFactoryImpl;

import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length != 0){
            new Main().doStuff();
        }
    }

    private void doStuff() throws Exception {
        System.out.print("providers=");
        for (Provider provider : Security.getProviders()) {
            System.out.printf("%s, ", provider.getName());
        }
        System.out.println();

        final KeyPairGenerator ec = KeyPairGenerator.getInstance("EC");
        ec.initialize(512);
        final KeyPair keyPair = ec.generateKeyPair();
        System.out.printf(
            "algorithm=%s, provider=%s, public key=%s%n",
            keyPair.getPublic().getAlgorithm(),
            ec.getProvider(),
            Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded())
        );


//        final byte[] buff = new byte[100];
        try(final SSLSocket socket = (SSLSocket) new SSLSocketFactoryImpl().createSocket("google.com", 443)){
            socket.startHandshake();
//            final int read = socket.getInputStream().read(buff);
//            System.out.println(new String(buff, 0, read));
        }
    }
}
