package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlAdaptationContractRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtlAdaptationContractEntity;

/**
 * 媒体类：视频广告改编合同 服务类
 *
 * @author  媒体类：视频广告改编合同
 * @date : 2020-12-10 19:40:35
 */
public interface IMtlAdaptationContractService extends BaseService<MtlAdaptationContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlAdaptationContract
	 * @return
	 */
	IPage<MtlAdaptationContractEntity> pageList(IPage<MtlAdaptationContractEntity> page, MtlAdaptationContractRequestVO mtlAdaptationContract);
}
