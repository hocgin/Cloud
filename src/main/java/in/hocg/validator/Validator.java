package in.hocg.validator;

import com.sun.istack.internal.NotNull;
import in.hocg.validator.tools.StringsUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * (๑`灬´๑)
 * hocgin(admin@hocg.in)
 * --------------------
 * Created 16-11-16.
 * <p>
 * rules格式: 指令:参数,参数|指令:参数,参数
 * <p>
 * 校验方法 Validation.make(request, map rules, map errors, map customAttributes)
 * Validation
 * Validation.replacer() 临时增加
 * Validation.extend() 扩展
 * <p>
 * --- 接口 ---
 * 校验规则接口 validateFoo($attribute, $value, $parameters)
 * 定义占位符  replaceFoo($message, $attribute, $rule, $parameters)
 */
public class Validator implements Cloneable {

    private static Map<String, Validation> GLOBAL_VALIDATIONS = new HashMap<>();
    private Map<String, Validation> localValidations = new HashMap<>();
    private Errors errors;
    private AfterListener afterListener;
    private Map<String, String[]> rules;
    private Map<String, String> messages;
    private Map<String, String> customAttributes;

    /**
     * 装载
     * @param validation
     * @return
     */
    public static Map<String, Validation> load(Map<String, Validation> validation) {
        GLOBAL_VALIDATIONS.putAll(validation);
        return GLOBAL_VALIDATIONS;
    }


    /**
     * 进行校验
     * @param request 校验值
     * @param rules 规则{指令:参数}
     * @param messages 规则对应的模板 {规则:模板}
     * @param customAttributes 最高级别的错误信息
     * @param validations 规则处理器
     * @param listener after监听
     */
    public static Errors makes(@NotNull Map<String, String[]> request,
                               @NotNull Map<String, String[]> rules,
                               Map<String, String> messages,
                               Map<String, String> customAttributes,
                               @NotNull Map<String, Validation> validations,
                               Errors.AfterListener listener) {

        Errors errors = new Errors();
        for (String filedName : rules.keySet()) {
            String[] attributes = rules.get(filedName);
            String[] value = request.get(filedName);
            String[] parameters = null;
            for (String attribute : attributes) {
                Validation validation;
                String dictate;
                if (attribute.contains(":")) {
                    String[] params = attribute.split("\\:", 2);
                    validation = validations.get(dictate = params[0]);
                    if (validation == null) { // 无匹配的 校验器
                        System.out.println(String.format("[%s] 无匹配的校验器", dictate));
                        break;
                    }
                    parameters = validation.parameters(params[1]);
                } else {
                    validation = validations.get(dictate = attribute);
                }
                if (validation != null
                        && !validation.validate(request, filedName, value, parameters)) { // 校验失败
                    String message;
                    if (customAttributes == null
                            || StringsUtils.isEmpty(message = customAttributes.get(filedName))) {
                        String template;
                        if (messages != null
                                && !StringsUtils.isEmpty(template = messages.get(dictate))) {
                            // 模板定义的错误信息
                            message = validation.replace(request, template, filedName, rules, parameters);
                        } else {
                            // 默认错误信息
                            message = validation.error(request, filedName, rules, parameters);
                        }
                    }
                    errors.add(filedName, message);
                }
            }
        }
        if (listener != null) {
            listener.run(errors);
        }
        return errors;
    }

    /**
     * 进行校验
     * @param request 校验值
     * @param rules 规则
     * @param messages 规则对应的模板 {规则:模板}
     * @param customAttributes 最高级别的错误信息
     * @param listener after监听
     */
    public static Errors makes(@NotNull Map<String, String[]> request,
                              @NotNull Map<String, String[]> rules,
                              Map<String, String> messages,
                              Map<String, String> customAttributes,
                              Errors.AfterListener listener) {
        return Validator.makes(request, rules, messages, customAttributes, Validator.GLOBAL_VALIDATIONS,listener);
    }

    /**
     * 进行校验
     * @param request 校验值
     * @param rules 规则
     * @param messages 规则对应的模板 {规则:模板}
     * @param customAttributes 最高级别的错误信息
     */
    public static Errors makes(@NotNull Map<String, String[]> request,
                               @NotNull Map<String, String[]> rules,
                               Map<String, String> messages,
                               Map<String, String> customAttributes) {
        return Validator.makes(request, rules, messages, customAttributes, Validator.GLOBAL_VALIDATIONS, null);
    }

    /**
     * 进行校验
     * @param request 校验值
     * @param rules 规则
     */
    public static Errors makes(@NotNull Map<String, String[]> request,
                               @NotNull Map<String, String[]> rules) {
        return Validator.makes(request, rules, null, null, Validator.GLOBAL_VALIDATIONS, null);
    }


    /**
     *
     * 进行校验 [规则自动进行合并]
     * @param request 校验值
     * @param rules 规则
     * @param messages 规则对应的模板 {规则:模板}
     * @param customAttributes 最高级别的错误信息
     * @return
     */
    public Validator make(@NotNull Map<String, String[]> request,
                          @NotNull Map<String, String[]> rules,
                          @NotNull Map<String, String> messages,
                          @NotNull Map<String, String> customAttributes) {
        rules = addRules(rules).getRules();
        messages = addMessages(messages).getMessages();
        customAttributes = addCustomAttributes(customAttributes).getCustomAttributes();
        Map<String, Validation> validations = new HashMap<>(GLOBAL_VALIDATIONS);
        validations.putAll(localValidations);
        this.errors = Validator.makes(request, rules, messages, customAttributes, validations, null);
        if (this.afterListener != null) {
            this.afterListener.run(this);
        }
        return this;
    }

    /**
     *
     * 进行校验 [规则自动进行合并]
     * @param request 校验值
     * @return
     */
    public Validator make(@NotNull Map<String, String[]> request,
                          @NotNull Map<String, String[]> rules) {
        return this.make(request, rules, new HashMap<>(), new HashMap<>());
    }

    public Map<String, String[]> getRules() {
        return rules;
    }

    public void setRules(Map<String, String[]> rules) {
        this.rules = rules;
    }

    /**
     * 新增一条Rule
     * @param ruleKey
     * @param ruleValue
     * @return
     */
    public Validator addRule(String ruleKey, String[] ruleValue) {
        if (this.rules == null) {
            this.rules = new HashMap<>();
        }
        this.rules.put(ruleKey, ruleValue);
        return this;
    }

    /**
     * 新增多条Rule
     * @param rules
     * @return
     */
    public Validator addRules(Map<String, String[]> rules) {
        if (this.rules == null) {
            this.rules = new HashMap<>();
        }
        for (String key : rules.keySet()) {
            this.rules.put(key, rules.get(key));
        }
        return this;
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }


    /**
     * 新增单条message
     * @param ruleKey
     * @param message
     * @return
     */
    public Validator addMessage(String ruleKey, String message) {
        if (this.messages == null) {
            this.messages = new HashMap<>();
        }
        this.messages.put(ruleKey, message);
        return this;
    }

    /**
     * 新增多条messages
     * @param messages
     * @return
     */
    public Validator addMessages(Map<String, String> messages) {
        if (this.messages == null) {
            this.messages = new HashMap<>();
        }
        for (String key : messages.keySet()) {
            this.messages.put(key, messages.get(key));
        }
        return this;
    }

    public Map<String, String> getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(Map<String, String> customAttributes) {
        this.customAttributes = customAttributes;
    }

    /**
     * 新增单条 customAttribute
     * @param ruleKey
     * @param customAttribute
     * @return
     */
    public Validator addCustomAttribute(String ruleKey, String customAttribute) {
        if (this.customAttributes == null) {
            this.customAttributes = new HashMap<>();
        }
        this.customAttributes.put(ruleKey, customAttribute);
        return this;
    }

    /**
     * 新增多条 customAttributes
     * @param customAttributes
     * @return
     */
    public Validator addCustomAttributes(Map<String, String> customAttributes) {
        if (this.customAttributes == null) {
            this.customAttributes = new HashMap<>();
        }
        for (String key : customAttributes.keySet()) {
            this.customAttributes.put(key, customAttributes.get(key));
        }
        return this;
    }

    /**
     * 全局扩展校验器
     * @param attribute 拦截规则
     * @param validation 拦截器
     * @return
     */
    public Validator extend(String attribute, Validation validation) {
        GLOBAL_VALIDATIONS.put(attribute, validation);
        return this;
    }

    /**
     * 临时扩展校验器
     * @param attribute 拦截规则
     * @param validation 拦截器
     * @return 扩展后的校验器
     * @throws CloneNotSupportedException
     */
    public Validator replacer(String attribute, Validation validation) throws CloneNotSupportedException {
        Validator clone = (Validator) this.clone();
        clone.localValidations.put(attribute, validation);
        return clone;
    }

    /**
     * 钩子方法 校验完成后回调
     * @param listener
     * @return
     */
    public Validator after(AfterListener listener) {
        this.afterListener = listener;
        return this;
    }

    /**
     * 返回错误信息对象
     * @return
     * @throws Exception 需要在make()之后调用
     */
    public Errors errors() throws Exception {
        if (this.errors == null) {
            throw new Exception("Please usa this.make() before.");
        }
        return this.errors;
    }

    interface AfterListener {
        void run(Validator validator);
    }

    /**
     * 是否失败
     * @return
     */
    public boolean fails() {
        return this.errors.fail();
    }

    /**
     * 是否成功
     * @return
     */
    public boolean passes() {
        return this.errors.passed();
    }

    /**
     * 获取所有错误信息
     * @return
     */
    public Map<String, Set<String>> messages() {
        return this.errors.all();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
