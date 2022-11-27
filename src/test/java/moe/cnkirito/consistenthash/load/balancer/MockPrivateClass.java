package moe.cnkirito.consistenthash.load.balancer;

/**
 * @author dailj
 * @date 2022/11/28 22:09
 */
public class MockPrivateClass {

    private MockPrivateClass() {}

    public String mockPrivateFunc() {
        return  privateFunc();
    }

    private String privateFunc() {
        return "private func";
    }
}
