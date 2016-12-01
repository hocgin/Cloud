package in.hocg.def.base.service;

import in.hocg.def.base.bean.BaseTable;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;

/**
 * Created by hocgin on 十一月28  028.
 */
@IocBean
public abstract class BaseService<T extends BaseTable> {
    @Inject
    private Dao dao;
    private ParameterizedType clazz = (ParameterizedType) this.getClass().getGenericSuperclass();

    /**
     * 获取表实体的类
     *
     * @return
     */
    protected Class<T> tableClass() {
        return (Class<T>) (clazz.getActualTypeArguments()[0]);
    }

    /**
     * 获取表实体的表名
     *
     * @return
     */
    protected String tableName() {
        Class<T> clazz = tableClass();
        for (Annotation annotation : clazz.getAnnotations()) {
            if (annotation instanceof Table) {
                return ((Table) annotation).value();
            }
        }
        return clazz.getSimpleName();
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public T fetch(String id) {
        return dao().fetch(tableClass(), id);
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    public T fetch(Integer id) {
        return dao().fetch(tableClass(), id);
    }

    /**
     * 公有Dao
     *
     * @return
     */
    protected Dao dao() {
        return dao;
    }


    /**
     * 获取Cnd
     * @return
     */
    public Cnd all() {
        return Cnd.NEW();
    }

}
