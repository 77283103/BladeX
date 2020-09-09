package org.springblade.flow.business.factory;

import com.google.common.collect.Maps;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.service.HandlerGetUser;

import java.util.Map;

/**
 * 生成策略类的工厂类
 *
 * @author tah
 * @date 2020-8-20
 */
public class FactoryGetUser {
	/*储存业务类型和策略类*/
	private static Map<String, HandlerGetUser> stringHandlerMap = Maps.newHashMap();

	/**
	 * 传入业务类型生成对应的策略方法
	 *
	 * @param str 策略类型
	 * @return 策略类
	 */
	public static HandlerGetUser getInvokeStrategy(String str){
		return stringHandlerMap.get(str);
	}

	/**
	 * 将业务类型和策略方法注册进map，以备工厂生产时调用
	 *
	 * @param str 策略类型
	 * @param handlerGetUser 策略类
	 */
	public static void register(String str, HandlerGetUser handlerGetUser){
		if(Func.isEmpty(str) || Func.isEmpty(handlerGetUser)){
			return;
		}
		stringHandlerMap.put(str, handlerGetUser);
	}
}
