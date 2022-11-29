package moe.cnkirito.consistenthash.strategy;

import moe.cnkirito.consistenthash.AbstractHashStrategy;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class JdkHashCodeStrategy implements AbstractHashStrategy {

    @Override
    public int getHashCode(String origin) {
        return origin.hashCode();
    }

}
