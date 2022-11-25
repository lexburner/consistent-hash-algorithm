package moe.cnkirito.consistenthash.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dailj
 * @date 2022/11/25 14:21
 */
public class Md5Util {

    public static final MessageDigest MD5_DIGEST;

    static {
        try {
            MD5_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
    }
}
