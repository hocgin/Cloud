package in.hocg.app.bean;

import com.sun.istack.internal.NotNull;
import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by hocgin on 16-12-1.
 */
@Table("t_settings")
public class Setting extends BaseTable{

    @Column
    @Comment("标志, 决定分组")
    private String tag;

    @Column
    @NotNull
    @Comment("主键， 用于索引")
    private String key;

    @Column
    @Comment("值")
    private String value;

    @Column
    @NotNull
    @Comment("描述")
    private String description;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}