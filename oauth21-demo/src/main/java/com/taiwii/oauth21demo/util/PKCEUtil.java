package com.taiwii.oauth21demo.util;

import com.nimbusds.jose.util.Base64URL;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class PKCEUtil {

    public static String codeVerifierGenerator() {
        return Base64URL.encode(UUID.randomUUID().toString()).toString();
    }

    public static String codeChallengeGenerator(String codeVerifier) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digestCodeVerifier = messageDigest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
        return Base64URL.encode(digestCodeVerifier).toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //鐢熸垚code_verifier
        String codeVerifier = codeVerifierGenerator();
        //鐢熸垚code_challenge
        String codeChallenge = codeChallengeGenerator(codeVerifier);

        System.out.println("code_verifier:" + codeVerifier);
        System.out.println("code_challenge:" + codeChallenge);
    }
}
