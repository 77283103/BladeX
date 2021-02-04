package org.springblade.contract.util;

import org.springblade.core.tool.utils.Func;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatUtils {

	private static final String str="null";
    /**
     * 系统时间转年月日操作
     *@param systemTime 系统时间
     */
    public static String systemTimeFormat(String systemTime) {
    	if(str==systemTime || Func.isBlank(systemTime)){
			return "__";
		}else{
			systemTime = systemTime.replace("Z", " UTC");
			//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = format.parse(systemTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String now = new SimpleDateFormat("yyyy年MM月dd日").format(date);
			return now;
		}
	}

	/**
	 * 格林尼治时间转年月日操作
	 *@param systemTime 系统时间
	 */
	public static String GLNZTimeFormat(String systemTime) {
		if(str==systemTime || Func.isBlank(systemTime)){
			return "__";
		}else{
			String format1 = null;
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(systemTime);
				format1 = format.format(date);
			} catch (Exception ignored) {
			}
			return systemTimeFormat(format1);
		}
	}


	/**
	 * 格林尼治时间转年月日操作
	 *@param systemTime 系统时间
	 */
	public static String GLNZTimeFormatBar(String systemTime) {
		if(str==systemTime || Func.isBlank(systemTime)){
			return "__";
		}else{
			String format1 = null;
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date(systemTime);
				format1 = format.format(date);
			} catch (Exception ignored) {
			}
			return format1;
		}
	}
	/**
	 * 系统时间转时分操作
	 *@param systemTime 系统时间
	 */
	public static String systemTimeFormatH(String systemTime) {
		if(str==systemTime || Func.isBlank(systemTime)){
			return "__";
		}else{
			systemTime = systemTime.replace("Z", " UTC");
			//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date = null;
			try {
				date = format.parse(systemTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String now = new SimpleDateFormat("HH时mm分").format(date);
			return now;
		}
	}

	/**
	 * 时分转格林尼治时间
	 *@param systemTime 系统时间
	 */
	public static Date SFFormatGLNZ(String systemTime) {
		Date date = null;
		if (!str.equals(systemTime) && !Func.isBlank(systemTime)) {
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			try {
				date = format.parse(systemTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
}
