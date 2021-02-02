package org.springblade.contract.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RedisCacheUtil {
	@Resource
	RedisTemplate<String, Object> redisTemplate;
	//设置6自增6位，用于补全操作
	private static final String STR_FORMAT = "0000";

	public  String selectTaskNo(String thisCode,String FLCode,String GSCode) {
		Long increment=null;
		//格式：子公司编号原则代号（取4位）+合同类型（取2位）+年月（取4位）+自然流水根据月度流水（取4位）
		StringBuilder sbuffer = new StringBuilder();
		sbuffer.append(GSCode);
		sbuffer.append(FLCode);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
		sbuffer.append(dateFormat.format(new Date()));
		if("".equals(thisCode)){
			RedisAtomicLong entityIdCounter = new RedisAtomicLong(FLCode+dateFormat.format(new Date()), redisTemplate.getConnectionFactory());
			increment = entityIdCounter.getAndIncrement();
		}else{
			String thisDate = thisCode.substring(4,12);
			if(!(FLCode+dateFormat.format(new Date())).equals(thisDate)){
				redisTemplate.delete(thisDate);
				RedisAtomicLong entityIdCounter = new RedisAtomicLong(FLCode+dateFormat.format(new Date()), redisTemplate.getConnectionFactory());
				increment = entityIdCounter.getAndIncrement();
			}else{
				RedisAtomicLong entityIdCounter = new RedisAtomicLong(thisDate, redisTemplate.getConnectionFactory());
				increment = entityIdCounter.getAndIncrement();
			}
		}
		//初始设置过期时间
		/*if ((null == increment || increment.longValue() == 0) && 1 > 0) {
			//设置自增值过期时间，liveTime 过期时间；TimeUnit.DAYS 过期时间单位，我这边设置为天
			entityIdCounter.expire(1, TimeUnit.DAYS);
		}*/
		if (increment >= 0) {
			increment = increment + 1;
		}
		//位数不够，前面补0
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		String no = df.format(increment);
		sbuffer.append(no);
		System.out.println(">>>>>>>>>>>" + sbuffer.toString());
		return sbuffer.toString();
	}
}
