package org.springblade.contract.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCacheUtil {
	@Resource
	RedisTemplate<String, Object> redisTemplate;
	//设置6自增6位，用于补全操作
	private static final String STR_FORMAT = "000000";

	public  String selectTaskNo(String thisCode) {
		if("".equals(thisCode)){

		}
		Long increment=null;
		String thisDate = thisCode.substring(4, 8);
		//格式：T+yyyymmddHHmiss+6位流水
		StringBuffer sbuffer = new StringBuffer();
		sbuffer.append("T");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String incKey = "T" + dateFormat.format(new Date());
		if(!dateFormat.format(new Date()).equals(thisDate)){
			redisTemplate.delete(incKey);
		}else{
			RedisAtomicLong entityIdCounter = new RedisAtomicLong(incKey, redisTemplate.getConnectionFactory());
			increment = entityIdCounter.getAndIncrement();

		}
		//初始设置过期时间
		/*if ((null == increment || increment.longValue() == 0) && 1 > 0) {
			//设置自增值过期时间，liveTime 过期时间；TimeUnit.DAYS 过期时间单位，我这边设置为天
			entityIdCounter.expire(1, TimeUnit.DAYS);
		}*/
		if (increment == 0) {
			increment = increment + 1;
		} else if (increment > 999999){
			increment = 1L;
		}
		//位数不够，前面补0
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		String no = df.format(increment);
		sbuffer.append(no);
		System.out.println(">>>>>>>>>>>" + sbuffer.toString());
		return sbuffer.toString();
	}
}
