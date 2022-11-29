package moe.cnkirito.consistenthash.load.balancer;


import moe.cnkirito.consistenthash.bo.Invocation;
import moe.cnkirito.consistenthash.LoadBalanceable;
import moe.cnkirito.consistenthash.bo.Server;
import moe.cnkirito.consistenthash.util.Md5Util;

import java.security.MessageDigest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class KetamaConsistentHashLoadBalancer implements LoadBalanceable {
    private static final Integer BASE_SIZE = 4;
    private static final int VIRTUAL_NODE_SIZE = 12;
    private static final String VIRTUAL_NODE_SUFFIX = "-";

    @Override
    public Server select(List<Server> servers, Invocation invocation) {
        long invocationHashCode = getHashCode(invocation.getHashKey());
        TreeMap<Long, Server> ring = buildConsistentHashRing(servers);
        return locate(ring, invocationHashCode);
    }

    private Server locate(TreeMap<Long, Server> ring, Long invocationHashCode) {
        // 向右找到第一个 key
        Map.Entry<Long, Server> locateEntry = ring.ceilingEntry(invocationHashCode);
        if (locateEntry == null) {
            // 想象成一个环，超过尾部则取第一个 key
            locateEntry = ring.firstEntry();
        }
        return locateEntry.getValue();
    }

    private TreeMap<Long, Server> buildConsistentHashRing(List<Server> servers) {
        TreeMap<Long, Server> virtualNodeRing = new TreeMap<>();
        for (Server server : servers) {
            for (int i = 0; i < VIRTUAL_NODE_SIZE / BASE_SIZE; i++) {
                byte[] digest = computeMd5(server.getUrl() + VIRTUAL_NODE_SUFFIX + i);
                for (int h = 0; h < BASE_SIZE; h++) {
                    Long k = ((long) (digest[3 + h * BASE_SIZE] & 0xFF) << 24)
                            | ((long) (digest[2 + h * BASE_SIZE] & 0xFF) << 16)
                            | ((long) (digest[1 + h * BASE_SIZE] & 0xFF) << 8)
                            | (digest[h * 4] & 0xFF);
                    virtualNodeRing.put(k, server);

                }
            }
        }
        return virtualNodeRing;
    }

    private long getHashCode(String origin) {
        byte[] bKey = computeMd5(origin);
        return ((long) (bKey[3] & 0xFF) << 24)
                | ((long) (bKey[2] & 0xFF) << 16)
                | ((long) (bKey[1] & 0xFF) << 8)
                | (bKey[0] & 0xFF);
    }

    private static byte[] computeMd5(String k) {
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
