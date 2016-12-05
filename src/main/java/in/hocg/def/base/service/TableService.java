package in.hocg.def.base.service;

import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

/**
 * Created by hocgin on 十一月28  028.
 */
@IocBean(fields = "dao")
public abstract class TableService<T extends BaseTable> extends IdNameEntityService<T> {

    /**
     * 获取Cnd
     * @return
     */
    public Cnd all() {
        return Cnd.NEW();
    }

}
