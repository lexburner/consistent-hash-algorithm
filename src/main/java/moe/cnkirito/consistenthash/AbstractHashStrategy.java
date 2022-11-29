package moe.cnkirito.consistenthash;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public interface AbstractHashStrategy {

    /**
     * 根据服务器地址标识符生成在一致性hash环上的hash值
     *
     * @param origin 服务器地址标识字符串
     * @return 一致性hash环上的hash值
     */
    int getHashCode(String origin);

}
