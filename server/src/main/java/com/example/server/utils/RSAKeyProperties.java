package com.example.server.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * A Spring component providing RSA public and private keys.
 * The keys are generated during the initialization of the component.
 */
@Getter
@Setter
@Component
public class RSAKeyProperties {

    /**
     * The RSA public key associated with this component.
     */

    private final RSAPublicKey publicKey;
    /**
     * The RSA private key associated with this component.
     */
    private final RSAPrivateKey privateKey;

    /**
     * Initializes RSAKeyProperties by generating a new RSA key pair.
     * The generated key pair consists of a public key and a private key.
     */
    public RSAKeyProperties() {
        KeyPair keyPair = KeyGeneratorUtility.generateRsaKey();
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }
}
