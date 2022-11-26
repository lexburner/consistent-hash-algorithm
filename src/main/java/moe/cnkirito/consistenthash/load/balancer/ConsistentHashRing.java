package moe.cnkirito.consistenthash.load.balancer;

import com.google.common.annotations.VisibleForTesting;
import moe.cnkirito.consistenthash.AbstractHashStrategy;
import moe.cnkirito.consistenthash.bo.Server;
import moe.cnkirito.consistenthash.strategy.FnvHashStrategy;
import moe.cnkirito.consistenthash.strategy.HashStratetyFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static moe.cnkirito.consistenthash.load.balancer.constant.HashRingConstant.VIRTUAL_NODE_SIZE;
import static moe.cnkirito.consistenthash.load.balancer.constant.HashRingConstant.VIRTUAL_NODE_SUFFIX;

/**
 * 一致性hash环
 *
 * @author dailj
 * @date 2022/11/25 15:54
 */
public class ConsistentHashRing {
    private static ConsistentHashRing instance;
    private static TreeMap<Integer, Server> virtualNodeRing;
    private static AbstractHashStrategy hashStrategy = HashStratetyFactory.getHashStrategy();

    public static ConsistentHashRing getInstance(List<Server> servers) {
        if (instance == null) {
            synchronized (ConsistentHashRing.class) {
                if (instance == null) {
                    instance = new ConsistentHashRing(servers);
                }
            }
        }
        return instance;
    }

    public Server locate(Integer invocationHashCode) {
        // 向右找到第一个 key
        Map.Entry<Integer, Server> locateEntry = virtualNodeRing.ceilingEntry(invocationHashCode);
        if (locateEntry == null) {
            // 想象成一个环，超过尾部则取第一个 key
            locateEntry = virtualNodeRing.firstEntry();
        }
        return locateEntry.getValue();
    }

    @VisibleForTesting
    protected ConsistentHashRing(List<Server> servers) {
        init(servers);
    }

    @VisibleForTesting
    protected ConsistentHashRing() {}

    private void init(List<Server> servers) {
        virtualNodeRing = buildConsistentHashRing(servers);
    }

    @VisibleForTesting
    protected TreeMap<Integer, Server> buildConsistentHashRing(List<Server> servers) {
        TreeMap<Integer, Server> virtualNodeRing = new TreeMap<>();
        for (Server server : servers) {
            for (int i = 0; i < VIRTUAL_NODE_SIZE; i++) {
                // 新增虚拟节点的方式如果有影响，也可以抽象出一个由物理节点扩展虚拟节点的类
                virtualNodeRing.put(hashStrategy.getHashCode(server.getUrl() + VIRTUAL_NODE_SUFFIX + i), server);
            }
        }
        return virtualNodeRing;
    }
}
