package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.InferiorProductContractEntity;

/**
 * 下脚品买卖合同模板 服务类
 *
 * @author Wang Pengfei
 * @date : 2021-01-15 15:54:17
 */
public interface IMinferiorProductContractService extends BaseService<InferiorProductContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param inferiorProductContract
	 * @return
	 */
	IPage<InferiorProductContractEntity> pageList(IPage<InferiorProductContractEntity> page, InferiorProductContractEntity inferiorProductContract);
}
