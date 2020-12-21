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
     * 获取ekp接口的token
     * @return
     */
    String getToken() throws Exception;

    /**
     * 推送数据
     * @return
     */
	EkpVo pushData(PushEkpEntity entity) throws Exception;
}
