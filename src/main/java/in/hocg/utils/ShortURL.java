package in.hocg.utils;

/**
 * Created by hocgin on 16-12-9.
 */
public class ShortURL {
	
	private static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
	private static final int BASE = ALPHABET.length();
	
	/**
	 * 生成短链
	 * @param num
	 * @return
	 */
	public static String encode(int num) {
		StringBuilder str = new StringBuilder();
		while (num > 0) {
			str.insert(0, ALPHABET.charAt(num % BASE));
			num = num / BASE;
		}
		return str.toString();
	}
	
	/**
	 * 解析短链
	 * @param str
	 * @return
	 */
	public static int decode(String str) {
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			num = num * BASE + ALPHABET.indexOf(str.charAt(i));
		}
		return num;
	}
}
