package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.vo.ContractArchiveResponseVO;

/**
 * 合同归档管理 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-23 18:32:15
 */
public class ContractArchiveWrapper extends BaseEntityWrapper<ContractArchiveEntity, ContractArchiveResponseVO>  {

	public static ContractArchiveWrapper build() {
		return new ContractArchiveWrapper();
 	}

	@Override
	public ContractArchiveResponseVO entityVO(ContractArchiveEntity archive) {
		ContractArchiveResponseVO archiveResponseVO = BeanUtil.copy(archive, ContractArchiveResponseVO.class);

		//User createUser = UserCache.getUser(archive.getCreateUser());
		//User updateUser = UserCache.getUser(archive.getUpdateUser());
		//archiveResponseVO.setCreateUserName(createUser.getName());
		//archiveResponseVO.setUpdateUserName(updateUser.getName());

		return archiveResponseVO;
	}

}
