package moe.cnkirito.consistenthash;

import moe.cnkirito.consistenthash.bo.Invocation;
import moe.cnkirito.consistenthash.bo.Server;

import java.util.List;

/**
 * @author daofeng.xjf
 * @date 2019/2/16
 */
public interface LoadBalanceable {

    /**
     * 根据数据的hash值在一致性hash环上选择第一个服务器
     *
     * @param servers 服务器列表
     * @param invocation 包含数据hash值的请求
     * @return 选择到的服务器
     */
    Server select(List<Server> servers, Invocation invocation);
}
