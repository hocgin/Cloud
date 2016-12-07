package in.hocg.app.services;

import com.sun.istack.internal.NotNull;
import in.hocg.app.beans.Setting;
import in.hocg.def.base.service.TableService;
import org.nutz.dao.Cnd;

import java.util.List;

/**
 * Created by hocgin on 16-12-1.
 */
public class SettingService extends TableService<Setting> {

    /**
     * 查询指定tag的所有Setting
     * @param tag
     * @return
     */
    public List<Setting> query(String tag) {
        return query(all().and("tag", "=", tag));
    }

    /**
     * 保存一项设置
     * @param tag 标签
     * @param key 键
     * @param value 值
     * @param description 描述
     */
    public void put(@NotNull String tag, @NotNull String key, String value, @NotNull String description) {
        Setting setting = this.fetch(tag, key);
        if (setting == null) {
            setting = new Setting();
            setting.setTag(tag);
            setting.setKey(key);
        }
        setting.setValue(value);
        setting.setDescription(description);
        save(setting);
    }

    public void put(@NotNull String key, String value, @NotNull String description) {
        this.put(Setting.Tag.Default.name(), key, value, description);
    }

    /**
     * Tag 和 key 共为主键
     * @param tag 标签
     * @param key 键
     * @return
     */
    public Setting fetch(@NotNull String tag, @NotNull String key) {
        Cnd cnd = all().and("tag", "=", tag).and("_key", "=", key);
        return fetch(cnd);
    }
}
