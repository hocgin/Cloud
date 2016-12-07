package in.hocg.utils;

/**
 * Created by hocgin on 16-12-7.
 * 不好归类的常用工具方法
 */
public class Lang {
	
	/**
	 * exp?true:false;
	 * @param t
	 * @param def
	 * @param <T>
	 * @return
	 */
	public static <T> T ifNull(T t, T def) {
		return t == null ? def : t;
	}
	
	/**
	 * exp?true:false;
	 * @param str
	 * @param def
	 * @return
	 */
	public static String ifEmpty(String str, String def) {
		return StringKit.isEmpty(str) ? def : str;
	}
}
