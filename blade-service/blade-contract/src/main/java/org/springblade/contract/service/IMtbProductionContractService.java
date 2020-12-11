package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtbProductionContractRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtbProductionContractEntity;

/**
 * 媒体类：平面广告拍摄制作合同 服务类
 *
 * @author 王策
 * @date : 2020-12-10 19:30:54
 */
public interface IMtbProductionContractService extends BaseService<MtbProductionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtbProductionContract
	 * @return
	 */
	IPage<MtbProductionContractEntity> pageList(IPage<MtbProductionContractEntity> page, MtbProductionContractRequestVO mtbProductionContract);
}
