package moe.cnkirito.consistenthash.strategy;

import moe.cnkirito.consistenthash.AbstractHashStrategy;
import moe.cnkirito.consistenthash.util.Md5Util;

import java.security.MessageDigest;

/**
 * @author daofeng.xjf
 * @date 2019/2/16
 */
public class KetamaHashStrategy implements AbstractHashStrategy {

    @Override
    public int getHashCode(String origin) {
        byte[] bKey = computeMd5(origin);
        long rv = ((long) (bKey[3] & 0xFF) << 24)
                | ((long) (bKey[2] & 0xFF) << 16)
                | ((long) (bKey[1] & 0xFF) << 8)
                | (bKey[0] & 0xFF);
        return (int) (rv & 0xffffffffL);
    }

    /**
     * Get the md5 of the given key.
     */
    public static byte[] computeMd5(String k) {
        MessageDigest md5;
        try {
            md5 = (MessageDigest) Md5Util.MD5_DIGEST.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("clone of MD5 not supported", e);
        }
        md5.update(k.getBytes());
        return md5.digest();
    }
}
