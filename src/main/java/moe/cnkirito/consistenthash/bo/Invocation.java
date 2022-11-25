package moe.cnkirito.consistenthash.bo;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class Invocation {
    private String hashKey;

    public Invocation() {}

    public Invocation(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }
}
