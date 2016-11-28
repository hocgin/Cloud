package in.hocg.app.bean;

import in.hocg.def.base.bean.SoftDeleted;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@Table("short_url")
public class ShortUrl extends SoftDeleted {

    @Column
    private String code;
    @Column
    private String url;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
