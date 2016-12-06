package in.hocg.validator.tools;

/**
 * (๑`灬´๑)
 * hocgin(admin@hocg.in)
 * --------------------
 * Created 16-11-16.
 */
public class StringsUtils {

    /**
     * 去除字符串数组 每个字符串的前后空格
     * @param array
     * @return
     */
    public static String[] trimElement(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
        return array;
    }

    /**
     *
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }
}
