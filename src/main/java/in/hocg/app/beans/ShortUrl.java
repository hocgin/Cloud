package in.hocg.app.beans;

import in.hocg.def.base.bean.SoftDeleted;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 */
@Table("t_short_urls")
public class ShortUrl extends SoftDeleted {

    @Column
    @Comment("4位数及以上,英文/数字/某些符号")
    private String code;

    @Column
    @Comment("记录的url")
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
