package moe.cnkirito.consistenthash;

import java.util.zip.CRC32;

/**
 * @author daofeng.xjf
 * @date 2019/2/16
 */
public class CRCHashStrategy implements HashStrategy {
    @Override
    public int getHashCode(String origin) {
        CRC32 crc32 = new CRC32();
        crc32.update(origin.getBytes());
        return (int) ((crc32.getValue() >> 16) & 0x7fff & 0xffffffffL);
    }
}
