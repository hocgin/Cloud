package in.hocg.validator;

import in.hocg.validator.tools.StringsUtils;

import java.util.Map;

/**
 * (๑`灬´๑)
 * hocgin(admin@hocg.in)
 * --------------------
 * Created 16-11-16.
 */
public abstract class Validation {

    /**
     * 校验
     * @param request 所有需要校验字段
     * @param filedName 校验字段名
     * @param values 值
     * @param parameters 校验方式附加参数
     * @return true表示通过
     */
    public abstract boolean validate(Map<String, String[]> request, String filedName, String[] values, String[] parameters);

    /**
     * 用于对错误信息中占位符替换
     * @param request 所有需要校验字段
     * @param message 错误信息模板
     * @param filedName 校验字段名
     * @param rule 规则
     * @param parameters 校验方式附加参数
     * @return 完整错误信息
     */
    public String replace(Map<String, String[]> request, String message, String filedName, Map<String, String[]> rule, String[] parameters) {
        return message;
    }

    /**
     * 默认错误信息
     * @param request 所有需要校验字段
     * @param filedName 校验字段名
     * @param rule 规则
     * @param parameters 校验方式附加参数
     * @return 完整错误信息
     */
    public abstract String error(Map<String, String[]> request, String filedName, Map<String, String[]> rule, String[] parameters);

    /**
     * 切割参数
     * @param parametersStr
     * @return
     */
    protected String[] parameters(String parametersStr) {
        String[] parameters;
        if (parametersStr.contains(",")) {
            parameters = parametersStr.split("\\,");
            parameters = StringsUtils.trimElement(parameters);
        } else {
            parameters = new String[]{parametersStr.trim()};
        }
        return parameters;
    }

    /**
     * 是否可以转为数字
     * @param str
     * @return
     */
    protected boolean _isNumeric(String str) {
        return str.matches("[0-9]+");
    }


    protected boolean _isMatch(String regex, String value) {
        return value.matches(regex);
    }
}
