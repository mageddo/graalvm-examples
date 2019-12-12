package com.mageddo;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        System.out.print("providers=");
        for (Provider provider : Security.getProviders()) {
            System.out.printf("%s, ", provider.getName());
        }
        System.out.println();

        final var ec = KeyPairGenerator.getInstance("EC");
        ec.initialize(512);
        final var keyPair = ec.generateKeyPair();
        System.out.printf(
            "algorithm=%s, provider=%s, public key=%s%n",
            keyPair.getPublic().getAlgorithm(),
            ec.getProvider(),
            Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded())
        );
    }
}
