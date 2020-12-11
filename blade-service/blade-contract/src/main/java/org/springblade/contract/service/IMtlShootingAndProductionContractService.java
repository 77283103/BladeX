package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlShootingAndProductionContractRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtlShootingAndProductionContractEntity;

/**
 * 媒体类：视频广告拍摄制作合同 服务类
 *
 * @author 媒体类：视频广告拍摄制作合同
 * @date : 2020-12-10 19:36:05
 */
public interface IMtlShootingAndProductionContractService extends BaseService<MtlShootingAndProductionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlShootingAndProductionContract
	 * @return
	 */
	IPage<MtlShootingAndProductionContractEntity> pageList(IPage<MtlShootingAndProductionContractEntity> page, MtlShootingAndProductionContractRequestVO mtlShootingAndProductionContract);
}
