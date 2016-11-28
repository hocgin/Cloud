package in.hocg.def.base.bean;

import in.hocg.app.bean.ShortUrl;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
public class Main {
    public static void main(String[] args) {
        ShortUrl shortUrl = new ShortUrl();
        Object id = null;
        shortUrl.set("id", "666");
        id = shortUrl.get("id");
        System.out.println(id);
    }
}
