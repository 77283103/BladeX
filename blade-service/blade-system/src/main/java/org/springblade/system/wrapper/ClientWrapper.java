package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.ClientEntity;
import org.springblade.system.vo.ClientVO;

/**
 * 应用管理 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class ClientWrapper extends BaseEntityWrapper<ClientEntity, ClientVO>  {

	public static ClientWrapper build() {
		return new ClientWrapper();
 	}

	@Override
	public ClientVO entityVO(ClientEntity client) {
		ClientVO clientVO = BeanUtil.copy(client, ClientVO.class);

		return clientVO;
	}

}
