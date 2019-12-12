package com.mageddo;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        final var ec = KeyPairGenerator.getInstance("EC");
        ec.initialize(512);
        final var keyPair = ec.generateKeyPair();
        System.out.printf(
            "algorithm=%s public key=%s%n",
            keyPair.getPublic().getAlgorithm(), Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded())
        );
    }
}
