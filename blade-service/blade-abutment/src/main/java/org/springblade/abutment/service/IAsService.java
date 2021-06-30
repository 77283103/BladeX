package org.springblade.abutment.service;

import org.springblade.abutment.vo.AsDictVo;
import org.springblade.core.tool.api.R;

/**
 * 用印单位更新接口
 * @author jitwxs
 * @date 2021/6/5 23:02
 */
public interface IAsService {

	/**
	 * 获取用印单位更新接口的token
	 * @return
	 */
	String getToken() throws Exception;

	/**
	 * 获取用印单位更新接口
	 * @return
	 * @param token
	 */
	R<AsDictVo> getDocInfo(String  token) throws Exception;
}
