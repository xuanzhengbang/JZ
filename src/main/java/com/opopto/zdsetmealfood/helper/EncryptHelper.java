package com.opopto.zdsetmealfood.helper;

import lombok.extern.apachecommons.CommonsLog;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@CommonsLog
public class EncryptHelper {

    public static String MD5(String password) {

        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();

            for (byte b : result) {
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.fillInStackTrace());
            return "";
        }

    }

    public static String getUid(){
        return "u_" + UUID.randomUUID().toString().replace("-","");
    }

}
