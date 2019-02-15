package moe.cnkirito.consistenthash;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public interface HashStrategy {

    int getHashCode(String origin);

}
