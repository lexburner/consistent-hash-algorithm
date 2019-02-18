package moe.cnkirito.consistenthash;

import com.google.common.util.concurrent.AtomicLongMap;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class LoadBalanceTest {

    /**
     * 测试分布的离散情况
     */
    @Test
    public void testDistribution() {
        List<Server> servers = new ArrayList<>();
        for (String ip : ips) {
            servers.add(new Server(ip+":8080"));
        }
        LoadBalancer chloadBalance = new ConsistentHashLoadBalancer();
        // 构造 10000 随机请求
        List<Invocation> invocations = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            invocations.add(new Invocation(UUID.randomUUID().toString()));
        }
        // 统计分布
        AtomicLongMap<Server> atomicLongMap = AtomicLongMap.create();
        for (Server server : servers) {
            atomicLongMap.put(server, 0);
        }
        for (Invocation invocation : invocations) {
            Server selectedServer = chloadBalance.select(servers, invocation);
            atomicLongMap.getAndIncrement(selectedServer);
        }
        System.out.println(StatisticsUtil.variance(atomicLongMap.asMap().values().toArray(new Long[]{})));
        System.out.println(StatisticsUtil.standardDeviation(atomicLongMap.asMap().values().toArray(new Long[]{})));
    }

    /**
     * 测试节点新增删除后的变化程度
     */
    @Test
    public void testNodeAddAndRemove() {
        List<Server> servers = new ArrayList<>();
        for (String ip : ips) {
            servers.add(new Server(ip));
        }
        List<Server> serverChanged = servers.subList(0, 80);
        LoadBalancer chloadBalance = new ConsistentHashLoadBalancer();
        // 构造 10000 随机请求
        List<Invocation> invocations = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            invocations.add(new Invocation(UUID.randomUUID().toString()));
        }
        int count = 0;
        for (Invocation invocation : invocations) {
            Server origin = chloadBalance.select(servers, invocation);
            Server changed = chloadBalance.select(serverChanged, invocation);
            if (origin.getUrl().equals(changed.getUrl())) count++;
        }
        System.out.println(count / 10000D);
    }


    static String[] ips = {
            "11.10.172.215",
            "11.10.176.96",
            "11.14.65.34",
            "11.14.64.205",
            "11.14.65.67",
            "11.134.247.206",
            "11.10.173.47",
            "11.14.65.117",
            "11.14.69.32",
            "11.10.173.46",
            "11.14.65.170",
            "11.14.65.159",
            "11.14.65.172",
            "11.14.65.171",
            "11.13.137.50",
            "11.13.129.20",
            "11.14.65.60",
            "11.13.167.124",
            "11.13.137.175",
            "11.14.65.17",
            "11.14.65.79",
            "11.14.65.179",
            "11.13.129.114",
            "11.13.129.123",
            "11.14.64.107",
            "11.14.65.177",
            "11.14.64.254",
            "11.14.65.63",
            "11.13.137.48",
            "11.14.64.235",
            "11.14.65.155",
            "11.13.129.121",
            "11.14.65.142",
            "11.14.69.45",
            "11.10.173.57",
            "11.10.173.54",
            "11.10.185.203",
            "11.10.176.102",
            "11.179.205.41",
            "11.179.206.58",
            "11.179.206.227",
            "11.179.205.71",
            "11.10.176.100",
            "11.179.206.42",
            "11.10.176.140",
            "11.10.173.115",
            "11.10.173.82",
            "11.10.185.105",
            "11.10.176.134",
            "11.179.206.27",
            "11.179.206.190",
            "11.15.246.86",
            "11.15.92.53",
            "11.15.214.36",
            "11.15.180.34",
            "11.14.67.4",
            "11.13.111.15",
            "11.8.239.196",
            "11.10.147.202",
            "11.10.174.220",
            "11.17.110.6",
            "11.14.68.78",
            "11.17.110.108",
            "11.17.110.107",
            "11.21.132.41",
            "11.17.98.170",
            "11.13.166.82",
            "11.17.97.234",
            "11.14.69.38",
            "11.27.62.112",
            "11.27.78.248",
            "11.27.146.130",
            "11.27.122.51",
            "11.27.134.108",
            "11.27.127.67",
            "11.27.134.107",
            "11.23.58.112",
            "11.23.90.169",
            "11.24.58.112",
            "11.24.50.24",
            "11.23.120.8",
            "11.26.228.195",
            "11.26.240.203",
            "11.27.19.252",
            "11.23.91.19",
            "11.17.110.52",
            "11.27.61.119",
            "11.27.85.228",
            "11.224.244.121",
            "11.226.220.49",
            "11.27.0.108",
            "11.8.17.104",
            "11.11.68.168",
            "11.14.65.133",
            "11.134.247.244",
            "11.10.192.114",
            "11.10.192.115",
            "11.10.192.116",
            "11.10.192.117",
            "11.10.192.118"
    };
}
