package in.hocg.utils;

import java.util.Random;

/**
 * Created by hocgin on 16-12-9.
 */
public class Generate {
	private static final String ALPHABET = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
	private static final int BASE = ALPHABET.length();
	private static final Random R = new Random();
	
	/**
	 * 随机生成指定位数的随机数
	 * @param length
	 * @return
	 */
	public static String code(int length) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < length; i++) {
			str.insert(0, ALPHABET.charAt(R.nextInt(BASE)));
		}
		return str.toString();
	}
	
}
