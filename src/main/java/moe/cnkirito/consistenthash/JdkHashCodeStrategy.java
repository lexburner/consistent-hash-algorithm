package moe.cnkirito.consistenthash;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class JdkHashCodeStrategy implements HashStrategy {

    @Override
    public int getHashCode(String origin) {
        return origin.hashCode();
    }

}
