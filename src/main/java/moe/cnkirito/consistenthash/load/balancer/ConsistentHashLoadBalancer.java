package moe.cnkirito.consistenthash.load.balancer;


import moe.cnkirito.consistenthash.AbstractHashStrategy;
import moe.cnkirito.consistenthash.bo.Invocation;
import moe.cnkirito.consistenthash.LoadBalanceable;
import moe.cnkirito.consistenthash.bo.Server;
import moe.cnkirito.consistenthash.strategy.FnvHashStrategy;
import moe.cnkirito.consistenthash.strategy.HashStratetyFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class ConsistentHashLoadBalancer implements LoadBalanceable {

    private static final AbstractHashStrategy hashStrategy = HashStratetyFactory.getHashStrategy();

    @Override
    public Server select(List<Server> servers, Invocation invocation) {
        int invocationHashCode = hashStrategy.getHashCode(invocation.getHashKey());
        ConsistentHashRing consistentHashRing = ConsistentHashRing.getInstance(servers);
        return consistentHashRing.locate(invocationHashCode);
    }

}
