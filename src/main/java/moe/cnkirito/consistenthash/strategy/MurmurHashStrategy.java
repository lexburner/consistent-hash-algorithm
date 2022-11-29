package moe.cnkirito.consistenthash.strategy;

import moe.cnkirito.consistenthash.AbstractHashStrategy;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author daofeng.xjf
 * @date 2019/2/16
 */
public class MurmurHashStrategy implements AbstractHashStrategy {
    private static final Integer BUFF_SIZE = 8;

    @Override
    public int getHashCode(String origin) {

        ByteBuffer buf = ByteBuffer.wrap(origin.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= BUFF_SIZE) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
             /// for big-endian version, do this first:
             // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }
        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return (int) (h & 0xffffffffL);
    }
}
