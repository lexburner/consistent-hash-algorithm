package moe.cnkirito.consistenthash;

/**
 * @author daofeng.xjf
 * @date 2019/2/15
 */
public class Server {

    public Server() {
    }

    public Server(String url) {
        this.url = url;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
