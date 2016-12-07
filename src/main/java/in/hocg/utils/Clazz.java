package in.hocg.utils;

import java.lang.reflect.Field;

/**
 * (๑`灬´๑)
 * Created by hocgin on 十一月28  028.
 * 类 | 反射 相关的工具方法
 */
public class Clazz {

    /**
     * 获取对象内的属性
     * @param object
     * @param filedName
     * @return
     */
    public Field getField(Object object, String filedName) {
            Class<?> clazz = object.getClass();
            while (Object.class != clazz) {
                try {
                    Field field = clazz.getDeclaredField(filedName);
                    field.setAccessible(true);
                    return field;
                } catch (NoSuchFieldException ignored) {
                }
                clazz = clazz.getSuperclass();
            }
            return null;
    }


    /**
     * 设置对象内的属性
     * @param object
     * @param filedName
     * @param value
     * @param <T>
     * @return
     */
    public <T> T set(T object, String filedName, Object value) {
        Field field = getField(object, filedName);
        if (field != null) {
            field.setAccessible(true);
            try {
                field.set(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
