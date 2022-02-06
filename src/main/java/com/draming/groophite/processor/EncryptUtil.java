package com.draming.groophite.processor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class EncryptUtil {

    /**
     * String in ，encrypt-256 out
     */
    public String SHA256(final String strText) {
        return encrypt(strText, "SHA-256");
    }

    /**
     * String in ，encrypt-512 out
     */
    public String SHA512(final String strText) {
        return encrypt(strText, "SHA-512");
    }

    public String MD5(final String strText){
        return encrypt(strText, "MD5");
    }

    /**
     * encrypt String
     */
    private String encrypt(final String str, final String strType) {
        MessageDigest messageDigest;
        String encodeStr = "";
        if (null == str || str.length() == 0) {
            return encodeStr;
        }
        try {
            messageDigest = MessageDigest.getInstance(strType);
            messageDigest.update(str.getBytes());

            // byte to char
            StringBuilder stringBuffer = new StringBuilder();
            String temp;
            for (byte aByte : messageDigest.digest()) {
                temp = Integer.toHexString(aByte & 0xFF);
                if (temp.length() == 1) {

                    stringBuffer.append("0");
                }
                stringBuffer.append(temp);
            }
            encodeStr = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
}
