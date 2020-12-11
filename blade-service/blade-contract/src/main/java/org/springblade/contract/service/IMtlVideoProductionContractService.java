package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlVideoProductionContractRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtlVideoProductionContractEntity;

/**
 * 媒体类：视频制作合同 服务类
 *
 * @author 媒体类：视频制作合同
 * @date : 2020-12-10 19:31:02
 */
public interface IMtlVideoProductionContractService extends BaseService<MtlVideoProductionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlVideoProductionContract
	 * @return
	 */
	IPage<MtlVideoProductionContractEntity> pageList(IPage<MtlVideoProductionContractEntity> page, MtlVideoProductionContractRequestVO mtlVideoProductionContract);
}
