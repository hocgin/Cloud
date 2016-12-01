package in.hocg.app.service;

import in.hocg.app.bean.Setting;
import in.hocg.def.base.service.TableService;

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
        return dao().query(tableClass(), all().and("tag", "=", tag));
    }
}
