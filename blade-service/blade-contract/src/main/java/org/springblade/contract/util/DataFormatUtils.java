package org.springblade.contract.util;

import org.springblade.core.tool.utils.Func;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatUtils {

    /**
     * 系统时间转年月日操作
     *@param systemTime 系统时间
     */
    public static String systemTimeFormat(String systemTime) {
    	if(Func.isEmpty(systemTime)){
			return systemTime;
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
}
