package com.example.server.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Utility class for generating RSA key pairs.
 */
public class KeyGeneratorUtility {
    /**
     * Generate an RSA key pair.
     *
     * @return The generated KeyPair.
     */
    public static KeyPair generateRsaKey() {

        KeyPair keyPair = null;

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyPair;
    }
}
