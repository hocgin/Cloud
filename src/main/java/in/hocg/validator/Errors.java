package in.hocg.validator;

import java.util.*;

/**
 * (๑`灬´๑)
 * hocgin(admin@hocg.in)
 * --------------------
 * Created 16-11-16.
 */
public class Errors {

    private Map<String, Set<String>> errors = new HashMap<>();

    /**
     * 返回所有错误信息
     * @return
     */
    public Map<String, Set<String>> all() {
        return this.errors;
    }

    /**
     * 是否验证成功
     * @return
     */
    public boolean passed() {
        return errors.isEmpty();
    }

    /**
     * 是否验证失败
     * @return
     */
    public boolean fail() {
        return !errors.isEmpty();
    }

    /**
     * 加入一条错误信息
     * @param filedName
     * @param message
     */
    public void add(String filedName, String message) {
        Set<String> messages = errors.get(filedName);
        if (messages == null) {
            messages = new HashSet<>();
        }
        messages.add(message);
        this.errors.put(filedName, messages);
    }

    /**
     * 获取指定字段名的第一条错误信息
     * @param filedName
     * @return
     */
    public String first(String filedName) {
        Set<String> errorSet = get(filedName);
        return errorSet != null && !errorSet.isEmpty() ? errorSet.iterator().next() : null;
    }

    /**
     * 判断某字段是否有错误信息
     * @param filedName
     * @return
     */
    public boolean has(String filedName) {
        Set<String> errorSet = get(filedName);
        return errorSet != null && !errorSet.isEmpty();
    }

    /**
     * 获取某字段的所有错误列表
     * @param filedName
     * @return
     */
    public Set<String> get(String filedName) {
        return this.errors.get(filedName);
    }


    public interface AfterListener {
        void run(Errors errors);
    }
}
