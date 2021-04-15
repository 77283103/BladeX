package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.DraftContractCounterpartEntity;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 合同销毁 服务类
 *
 * @author szw
 * @date : 2020-11-11 16:37:01
 */
public interface IDraftContractCounterparService extends BaseService<DraftContractCounterpartEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractDestruction
	 * @return
	 */
	IPage<DraftContractCounterpartEntity> pageList(IPage<DraftContractCounterpartEntity> page, DraftContractCounterpartEntity contractDestruction);

	/**
	 * 根据合同ID查询多方的向对方信息
	 * @param id
	 * @return
	 */
	List<DraftContractCounterpartEntity> selectByContractId(Long id);

	/**
	 * 相对方保存方法
	 * @param vo 合同信息
	 * @return
	 */
	void saveDraftContractCounterpart(ContractFormInfoRequestVO vo);
}
