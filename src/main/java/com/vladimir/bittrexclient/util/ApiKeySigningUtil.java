package com.vladimir.bittrexclient.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class ApiKeySigningUtil {

    private static final String ALGORITHM = "HmacSHA512";

    public static String createNonce(){
        return String.valueOf(System.currentTimeMillis());
    }

    public static String createSign(String uri, String apiSecret){
        byte[] signBytes = calculateSignBytes(uri, apiSecret);
        return bytesToHexString(signBytes);
    }

    private static byte[] calculateSignBytes(String uri, String secret) {

        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), ALGORITHM);
            mac.init(secretKeySpec);
            return mac.doFinal(uri.getBytes());

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }

    private static String bytesToHexString(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}
