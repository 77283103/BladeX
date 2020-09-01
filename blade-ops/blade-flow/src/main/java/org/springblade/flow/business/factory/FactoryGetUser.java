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
	private static Map<String, HandlerGetUser> stringHandlerMap = Maps.newHashMap();

	public static HandlerGetUser getInvokeStrategy(String str){
		return stringHandlerMap.get(str);
	}

	public static void register(String str, HandlerGetUser handlerGetUser){
		if(Func.isEmpty(str) || Func.isEmpty(handlerGetUser)){
			return;
		}
		stringHandlerMap.put(str, handlerGetUser);
	}
}
