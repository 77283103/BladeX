package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlEditedTheContractRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.MtlEditedTheContractEntity;

/**
 * 媒体类：修图合同 服务类
 *
 * @author 媒体类：修图合同
 * @date : 2020-12-10 19:24:49
 */
public interface IMtlEditedTheContractService extends BaseService<MtlEditedTheContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlEditedTheContract
	 * @return
	 */
	IPage<MtlEditedTheContractEntity> pageList(IPage<MtlEditedTheContractEntity> page, MtlEditedTheContractRequestVO mtlEditedTheContract);
}
