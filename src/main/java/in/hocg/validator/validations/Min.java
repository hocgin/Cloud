package in.hocg.validator.validations;

import in.hocg.validator.Validation;

import java.util.Map;

/**
 * (๑`灬´๑)
 * hocgin(admin@hocg.in)
 * --------------------
 * Created 16-11-17.
 */
public class Min extends Validation {
    @Override
    public boolean validate(Map<String, String[]> request, String filedName, String[] values, String[] parameters) {
        if (values != null) {
            for (String value : values) {
                if (!_isNumeric(value)) return false;
                if (Long.valueOf(value) >= Long.valueOf(parameters[0])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String error(Map<String, String[]> request, String filedName, Map<String, String[]> rule, String[] parameters) {
        return String.format("%s 不能小于 %s", filedName, parameters[0]);
    }
}
