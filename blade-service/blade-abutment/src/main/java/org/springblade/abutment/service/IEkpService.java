package org.springblade.abutment.service;

import org.springblade.abutment.entity.PushEkpEntity;
import org.springblade.abutment.vo.EkpVo;

/**
 * <p>
 * ekp数据交互 服务类
 * </p>
 *
 * @Author cc
 * @since 2020-12-4
 */
public interface IEkpService {

	/**
	 * 获取ekp接口的token(用于测试，解耦)
	 * @return
	 */
	String getToken(String account,String password,String tokenUrl) throws Exception;

    /**
     * 获取ekp接口的token
     * @return
     */
    String getToken() throws Exception;

    /**
     * 推送数据
     * @return
     */
	EkpVo pushData(PushEkpEntity entity) throws Exception;

	/**
	 * 推送代办
	 * @return
	 */
	EkpVo pushAgency(PushEkpEntity entity) throws Exception;
}
