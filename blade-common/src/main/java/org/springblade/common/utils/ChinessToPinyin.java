package org.springblade.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转拼音用Util
 * @author tah
 */
public class ChinessToPinyin {
	private static final HanyuPinyinOutputFormat DEFAULT_FORMATTER =  getFormatter(HanyuPinyinCaseType.LOWERCASE, HanyuPinyinToneType.WITHOUT_TONE, HanyuPinyinVCharType.WITH_U_AND_COLON);

	public static String deptToPinyin(String deptName, String fullName){
		return cn2Pinyin(deptName) + getFirstSpell(deptName) + cn2Pinyin(fullName) + getFirstSpell(fullName);
	}

	/**
	 * 汉字转拼音快速模式
	 * @param chinese 汉字
	 */
	public static String cn2Pinyin(String chinese){
		return cn2Pinyin(chinese,DEFAULT_FORMATTER,true,"");
	}

	/**
	 * 汉字转成拼音
	 * @param chinese 汉字
	 * @param formatter 格式化
	 * @param division 是否增加分割
	 * @param divisionStr 分隔符
	 */
	private static String cn2Pinyin(String chinese, HanyuPinyinOutputFormat formatter, boolean division, String divisionStr) {
		StringBuffer buffer = new StringBuffer();
		char[] arr = chinese.toCharArray();
		for (char anArr : arr) {
			/* 如果是ASCII 字符或者是标点符号，就忽略*/
			if (anArr <= 128 ) {
				buffer.append(anArr);
			} else {
				buffer.append(pinyin(anArr, formatter));
				if (division) {
					buffer.append(divisionStr);
				}
			}
		}
		return buffer.toString();
	}

	public static String getFirstSpell(String chinese) {
		StringBuffer pybf = new StringBuffer();
		char[] arr = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 128) {
				try {
					String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if (temp != null) {
						pybf.append(temp[0].charAt(0));
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pybf.append(arr[i]);
			}
		}
		return pybf.toString().replaceAll("\\W", "").trim();
	}

	/**
	 * 格式化
	 */
	private static HanyuPinyinOutputFormat getFormatter(HanyuPinyinCaseType caseType, HanyuPinyinToneType toneType, HanyuPinyinVCharType charType){
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(caseType);
		format.setToneType(toneType);
		format.setVCharType(charType);
		return format;
	}
	/**
	 * 转拼音
	 * @param c 被转字符
	 * @param format 格式化
	 */
	private static String pinyin(char c, HanyuPinyinOutputFormat format) {
		try {
			String pinyinStr = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
			return pinyinStr;
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
			return "";
		}
	}
}
