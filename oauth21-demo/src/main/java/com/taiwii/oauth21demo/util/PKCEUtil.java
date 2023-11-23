package com.taiwii.oauth21demo.util;

import com.nimbusds.jose.util.Base64URL;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * PKCE码工龄类<br />
 *
 * 实际应用中应该由前端生成
 */
public class PKCEUtil {

    public static String buildCodeVerifier() {
        return Base64URL.encode(UUID.randomUUID().toString()).toString();
    }

    public static String buildCodeChallenge(String codeVerifier) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digestCodeVerifier = messageDigest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
        return Base64URL.encode(digestCodeVerifier).toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String codeVerifier = buildCodeVerifier();
        String codeChallenge = buildCodeChallenge(codeVerifier);

        System.out.println("code_verifier:" + codeVerifier);
        System.out.println("code_challenge:" + codeChallenge);
    }
}
