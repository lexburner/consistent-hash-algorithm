package moe.cnkirito.consistenthash.bo;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class Server {

    private String url;

    public Server() {}

    public Server(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
