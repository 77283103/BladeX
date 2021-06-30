package org.springblade.contract.util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author jitwxs
 * @date 2021/6/10 15:09
 */
public class ConversionNuToCh {
	private ConversionNuToCh(){

	}
	/**
	 * 单位进位，中文默认为4位即（万、亿）
	 */
	public static int UNIT_STEP = 4;

	/**
	 * 单位
	 */
	public static String[] CN_UNITS = new String[]{"个", "十", "百", "千", "万", "十",
		"百", "千", "亿", "十", "百", "千", "万"};

	/**
	 * 汉字
	 */
	public static String[] CN_CHARS = new String[]{"零", "一", "二", "三", "四",
		"五", "六", "七", "八", "九"};


	/**
	 * 将阿拉伯数字转换为中文数字123=》一二三
	 *
	 * @param srcNum
	 * @return
	 */
	public static String getCNNum(int srcNum) {
		String desCNNum = "";

		if (srcNum <= 0) {
			desCNNum = "零";
		} else {
			int singleDigit;
			while (srcNum > 0) {
				singleDigit = srcNum % 10;
				desCNNum = String.valueOf(CN_CHARS[singleDigit]) + desCNNum;
				srcNum /= 10;
			}
		}

		return desCNNum;
	}

	/**
	 * 数值转换为中文字符串 如2转化为贰
	 */
	public static String cvt(long num) {
		return cvt(num, false);
	}

	/**
	 * 数值转换为中文字符串(口语化)
	 *
	 * @param num          需要转换的数值
	 * @param isColloquial 是否口语化。例如12转换为'十二'而不是'一十二'。
	 * @return
	 */
	public static String cvt(String num, boolean isColloquial) {
		int integer, decimal;
		StringBuffer strs = new StringBuffer(32);
		String[] splitNum = num.split("\\.");
		//整数部分
		integer = Integer.parseInt(splitNum[0]);
		//小数部分
		decimal = Integer.parseInt(splitNum[1]);
		String[] result_1 = convert(integer, isColloquial);
		for (String str1 : result_1) {
			strs.append(str1);
		}
		//小数部分为0时
		if (decimal == 0) {
			return strs.toString();
		} else {
			//例如5.32，小数部分展示三二，而不是三十二
			String result_2 = getCNNum(decimal);
			strs.append("点");
			strs.append(result_2);
			return strs.toString();
		}
	}

	/**
	 * 对于int,long类型的数据处理
	 * @author jitwxs
	 * @date 2021/6/10 15:13
	 * @param num
	 * @param isColloquial
	 * @return java.lang.String
	 */

	public static String cvt(long num, boolean isColloquial) {
		String[] result = convert(num, isColloquial);
		StringBuffer strs = new StringBuffer(32);
		for (String str : result) {
			strs.append(str);
		}
		return strs.toString();
	}

	/**
	 * 将数值转换为中文
	 *
	 * @param num          需要转换的数值
	 * @param isColloquial 是否口语化。例如12转换为'十二'而不是'一十二'。
	 * @return
	 */
	public static String[] convert(long num, boolean isColloquial) {
		// 10以下直接返回对应汉字
		if (num < 10) {
			// ASCII2int
			return new String[]{CN_CHARS[(int) num]};
		}
		char[] chars = String.valueOf(num).toCharArray();
		// 超过单位表示范围的返回空
		if (chars.length > CN_UNITS.length) {
			return new String[]{};
		}
         // 记录上次单位进位
		boolean isLastUnitStep = false;
		// 创建数组，将数字填入单位对应的位置
		ArrayList<String> cnchars = new ArrayList<String>(chars.length * 2);
		// 从低位向高位循环
		for (int pos = chars.length - 1; pos >= 0; pos--) {
			char ch = chars[pos];
			// ascii2int 汉字
			String cnChar = CN_CHARS[ch - '0'];
			// 对应的单位坐标
			int unitPos = chars.length - pos - 1;
			// 单位
			String cnUnit = CN_UNITS[unitPos];
			// 是否为0
			boolean isZero = (ch == '0');
			// 是否低位为0
			boolean isZeroLow = (pos + 1 < chars.length && chars[pos + 1] == '0');
            // 当前位是否需要单位进位
			boolean isUnitStep = (unitPos >= UNIT_STEP && (unitPos % UNIT_STEP == 0));
            // 去除相邻的上一个单位进位
			if (isUnitStep && isLastUnitStep) {
				int size = cnchars.size();
				cnchars.remove(size - 1);
				// 补0
				if (!CN_CHARS[0].equals(cnchars.get(size - 2))) {
					cnchars.add(CN_CHARS[0]);
				}
			}
            // 单位进位(万、亿)，或者非0时加上单位
			if (isUnitStep || !isZero) {
				cnchars.add(cnUnit);
				isLastUnitStep = isUnitStep;
			}
			// 当前位为0低位为0，或者当前位为0并且为单位进位时进行省略
			if (isZero && (isZeroLow || isUnitStep)) {
				continue;
			}
			cnchars.add(cnChar);
			isLastUnitStep = false;
		}

		Collections.reverse(cnchars);
		// 清除最后一位的0
		int chSize = cnchars.size();
		String chEnd = cnchars.get(chSize - 1);
		if (CN_CHARS[0].equals(chEnd) || CN_UNITS[0].equals(chEnd)) {
			cnchars.remove(chSize - 1);
		}

		// 口语化处理
		if (isColloquial) {
			String chFirst = cnchars.get(0);
			String chSecond = cnchars.get(1);
			// 是否以'一'开头，紧跟'十'
			if (chFirst.equals(CN_CHARS[1]) && chSecond.startsWith(CN_UNITS[1])) {
				cnchars.remove(0);
			}
		}
		return cnchars.toArray(new String[]{});
	}
}
