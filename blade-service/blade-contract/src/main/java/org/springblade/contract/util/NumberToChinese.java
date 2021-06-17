package org.springblade.contract.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;

/**
 * 阿拉伯数字转中文大写
 * @author jitwxs
 * @date 2021/6/10 14:56
 */
public class NumberToChinese {
	private NumberToChinese() {
	}

	private static final String CN_ZERO = "零";
	private static final String CN_ONE = "壹";
	private static final String CN_TWO = "贰";
	private static final String CN_THREE = "叁";
	private static final String CN_FOUR = "肆";
	private static final String CN_FIVE = "伍";
	private static final String CN_SIX = "陆";
	private static final String CN_SEVEN = "柒";
	private static final String CN_EIGHT = "捌";
	private static final String CN_NINE = "玖";
	private static final String CN_TEN = "拾";
	private static final String CN_HUNDRED = "佰";
	private static final String CN_THOUSAND = "仟";
	private static final String CN_TEN_THOUSAND = "万";
	private static final String CN_HUNDRED_MILLION = "亿";
	private static final String CN_SYMBOL = "";
	private static final String CN_DOLLAR = "元";
	private static final String CN_TEN_CENT = "角";
	private static final String CN_CENT = "分";
	private static final String CN_INTEGER = "整";
	//最大精度
	private static Integer MAX_PRECISE = 17;


	private static final String[] DIGITS = {CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE};

	private static final String[] RADICES = {CN_SYMBOL, CN_TEN, CN_HUNDRED, CN_THOUSAND};


	private static final String[] BIG_RADICES = {CN_SYMBOL, CN_TEN_THOUSAND, CN_HUNDRED_MILLION, CN_TEN_THOUSAND, CN_HUNDRED_MILLION};

	private static final String[] DECIMALS = {CN_TEN_CENT, CN_CENT};

	/**
	 * 数字的正则表达式
	 */
	private static final String NUMBER_FORMAT = "^\\d+(\\.\\d+)?$";


	/**
	 * 转换数字成大写的中文金额
	 *
	 * @param money 金额
	 * @return
	 */
	public static String toUpperChinese(String money) {

		//排除空格
		money = money.trim();

		if (StringUtils.isEmpty(money)) {
			return "";
		}

		//校验格式
		if (!money.matches(NUMBER_FORMAT)) {
			throw new RuntimeException("非数字,无法转换");
		}


		//这个NumberFormatUtils是我自己写的将
		money = doubleToStr(Double.valueOf(money));

		if (money.length() > MAX_PRECISE) {
			throw new RuntimeException("超出最大精度,无法转换");
		}

//切割数据
		String[] split = money.split("\\.");
		//整数部分转换
		String intPart = getIntPart(split[0]);
		//小数部分转换
		String dotPart = getDotPart(split[1]);

//拼接
		String result = intPart + dotPart;
//如果小数部分为:"",直接在拼接好的字符串拼接整
		if (dotPart.equals(CN_SYMBOL)) {
			result += CN_INTEGER;
		}
//如果整数部分部位"".小数部分长度为2且小数部分以分结尾,需要在这两者之间加个零,比较符合习惯
		if (intPart != CN_SYMBOL && dotPart.length() == 2 && dotPart.endsWith(CN_CENT)) {
			result = intPart + CN_ZERO + dotPart;
		}

		return result;

	}

	/**
	 * 小数部分转换
	 *
	 * @param dotPart
	 * @return
	 */
	private static String getDotPart(String dotPart) {
		//如果小数部分为零的话,直接返回""
		if (Integer.valueOf(dotPart).equals(0)) {
			return CN_SYMBOL;
		}

		String result = "";
		if (dotPart != "") {
			for (int i = 0; i < dotPart.length(); i++) {
				//切割单个字符串
				String d = dotPart.substring(i, i + 1);
				//当为0时,拼接""
				if (d.equals("0")) {
					result += CN_SYMBOL;
				} else {
					//否则,转换成大写中文形式
					result += DIGITS[Integer.valueOf(d)] + DECIMALS[i];

				}
			}
		}
		return result;

	}

	/**
	 * 整数部分转换
	 *
	 * @param intPart
	 * @return
	 */
	private static String getIntPart(String intPart) {

//当整数部分等于0时,返回""
		if (Long.valueOf(intPart).equals(0L)) {
			return CN_SYMBOL;
		}


		String result = "";
		if (Long.parseLong(intPart) > 0) {
			//数字0的计数器
			int zeroCount = 0;
			for (int i = 0; i < intPart.length(); i++) {
				//当前未处理的数字长度
				int p = intPart.length() - i - 1;
				//从头开始取数字
				String d = intPart.substring(i, i + 1);
				//中文读法一般4位一个区间,个~千,万到千万
				//取商,判断它的层级,亿的位置为2,万级为1,万以下为0
				int quotient = p / 4;
				//取模,判断他的位置,个位1,十位2,百位
				int modulus = p % 4;
				if (d.equals("0")) {
					//当当前数字为0时,计数器加一
					zeroCount++;
				} else {
					if (zeroCount > 0) {
						//当计数器>0说明前面几个数字为0,拼接中文零
						result += DIGITS[0];
					}
					//重置计数器
					zeroCount = 0;
					//其他的正常拼接
					result += DIGITS[Integer.parseInt(d)] + RADICES[modulus];
				}
				//当计数器小于5的时候才进来,否则会一直跳过,避免1亿整的时候还会跳入判断添加不必要的中文输入
				if (modulus == 0 && zeroCount < 5) {
					result += BIG_RADICES[quotient];
				}
			}
			result += CN_DOLLAR;
		}

		return result;
	}

	//转换成格式为保留两位小数的字符串
	private static String doubleToStr(Double money) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		String formatStr = decimalFormat.format(money);
		return formatStr;
	}
}
