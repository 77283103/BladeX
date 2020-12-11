package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAudioProductionContractEntity;
import org.springblade.contract.vo.MtlAudioProductionContractRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 媒体类：音频制作合同 服务类
 *
 * @author 媒体类：音频制作合同
 * @date : 2020-12-10 19:21:37
 */
public interface IMtlAudioProductionContractService extends BaseService<MtlAudioProductionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAudioProductionContract
	 * @return
	 */
	IPage<MtlAudioProductionContractEntity> pageList(IPage<MtlAudioProductionContractEntity> page, MtlAudioProductionContractRequestVO mtlAudioProductionContract);

}
