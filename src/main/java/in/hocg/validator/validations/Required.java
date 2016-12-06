package in.hocg.validator.validations;

import in.hocg.validator.Validation;

import java.util.Map;

/**
 * (๑`灬´๑)
 * hocgin(admin@hocg.in)
 * --------------------
 * Created 16-11-17.
 */
public class Required extends Validation {

    @Override
    public boolean validate(Map<String, String[]> request, String filedName, String[] value, String[] parameters) {
        return !(value == null || value.length == 0);
    }

    @Override
    public String error(Map<String, String[]> request, String filedName, Map<String, String[]> rule, String[] parameters) {
        return String.format("%s 不能为空", filedName);
    }
}
