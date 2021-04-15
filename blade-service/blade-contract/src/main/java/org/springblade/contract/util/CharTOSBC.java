package org.springblade.contract.util;

import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;

/**
 * 字符串全半角转换
 */
public class CharTOSBC {
	public static final char SBC_SPACE = 12288; // 全角空格 12288

	public static final char DBC_SPACE = 32; //半角空格 32

	// ASCII character 33-126 <-> unicode 65281-65374
	public static final char ASCII_START = 33;

	public static final char ASCII_END = 126;

	public static final char UNICODE_START = 65281;

	public static final char UNICODE_END = 65374;

	public static final char DBC_SBC_STEP = 65248; // 全角半角转换间隔

	public static char sbc2dbc(char src){
		if (src == SBC_SPACE) {
			return DBC_SPACE;
		}

		if (src >= UNICODE_START && src <= UNICODE_END) {
			return (char) (src - DBC_SBC_STEP);
		}

		return src;
	}

	/**
	 * Convert from SBC case to DBC case
	 *
	 * @param src
	 * @return DBC case
	 */
	public static String sbc2dbcCase(String src) {
		if (src == null) {
			return null;
		}
		char[] c = src.toCharArray();
		for (int i = 0; i < c.length; i++) {
			c[i] = sbc2dbc(c[i]);
		}
		return new String(c);
	}

	public static char dbc2sbc(char src){
		if (src == DBC_SPACE) {
			return SBC_SPACE;
		}
		if (src <= ASCII_END) {
			return (char) (src + DBC_SBC_STEP);
		}
		return src;
	}

	/**
	 * Convert from DBC case to SBC case.
	 *
	 * @param src
	 * @return SBC case string
	 */
	public static String dbc2sbcCase(String src) {
		if (src == null) {
			return null;
		}

		char[] c = src.toCharArray();
		for (int i = 0; i < c.length; i++) {
			c[i] = dbc2sbc(c[i]);
		}

		return new String(c);
	}

	/**
	 * 转半角的函数(DBC case)<br/><br/>
	 * 全角空格为12288，半角空格为32
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 *
	 * @param input 任意字符串
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				//全角空格为12288，半角空格为32
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				//其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 转全角的方法(SBC case)<br/><br/>
	 * 全角空格为12288，半角空格为32
	 * 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
	 *
	 * @param input 任意字符串
	 * @return 半角字符串
	 */
	public static String ToSBC(String input) {
		//半角转全角：
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * 判断字符串是否为全半角
	 *
	 * @param text
	 * @return
	 */
	public static R<StringBuilder> Matches(String text) {
		char[] chars_test1 = text.toCharArray();
		StringBuilder chars_sbc = new StringBuilder();
		StringBuilder chars_dbc = new StringBuilder();
		for (char c : chars_test1) {
			String temp = String.valueOf(c);
			// 判断是全角字符
			if (temp.matches("[^\\x00-\\xff]")) {
				System.out.println("全角" + temp);
				chars_sbc.append(temp);
			}
			// 判断是半角字符
			else {
				System.out.println("半角 " + temp);
				chars_dbc.append(temp);
			}
		}
		if (Func.isNotEmpty(chars_sbc) && Func.isNotEmpty(chars_dbc)) {

			return R.data(2, chars_sbc, "全角内容，请将其改为半角内容");
		} else {
			return R.data(0, chars_sbc, "成功");
		}
	}
}
