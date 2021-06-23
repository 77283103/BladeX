package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractFileDownloadLogEntity;
import org.springblade.contract.vo.ContractFileDownloadLogRequestVO;
import org.springblade.contract.vo.ContractFileDownloadLogResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springframework.stereotype.Component;


/**
 * 合同文件日志 包装类,返回视图层所需的字段
 *
 * @author wpf
 * @date : 2021-06-23 10:30:38
 */
@Component
public class ContractFileDownloadLogWrapper implements IEntityWrapper<ContractFileDownloadLogEntity, ContractFileDownloadLogRequestVO, ContractFileDownloadLogResponseVO> {

	public static ContractFileDownloadLogWrapper build() {
		return new ContractFileDownloadLogWrapper();
 	}

    @Override
	public ContractFileDownloadLogEntity createEntity() {
		return new ContractFileDownloadLogEntity();
	}

	@Override
	public ContractFileDownloadLogRequestVO createQV() {
		return new ContractFileDownloadLogRequestVO();
	}

	@Override
	public ContractFileDownloadLogResponseVO createPV() {
		return new ContractFileDownloadLogResponseVO();
	}

    @Override
    public void selectUserName(ContractFileDownloadLogResponseVO responseVO) {
        responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
