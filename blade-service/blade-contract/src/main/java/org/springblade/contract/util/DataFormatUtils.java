package org.springblade.contract.util;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatUtils {

    /**
     * 系统时间转年月日操作
     *@param systemTime 系统时间
     */
    public static String systemTimeFormat(String systemTime) {
		String [] strs = systemTime.split("[T]");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(strs[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String now = new SimpleDateFormat("yyyy年MM月dd日").format(date);
		return now;
	}
}
