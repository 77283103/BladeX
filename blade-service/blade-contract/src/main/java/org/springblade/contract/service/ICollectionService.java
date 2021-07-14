package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CollectionEntity;
import org.springblade.contract.vo.CollectionRequestVO;
import org.springblade.contract.vo.CollectionResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 收款明细
 *
 * @author szw
 * @date : 2020-11-04 18:28:11
 */
public interface ICollectionService extends BaseService<CollectionEntity> {

	/**
	 * 分页查询
	 * 一般超期
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<CollectionResponseVO> pageList(IPage<CollectionEntity> page, CollectionRequestVO contractBond);

	List<CollectionEntity> getByIdList(Long id);

	/**
	 * 删除合同收款明细关联表
	 * @param id 合同id
	 */
	void deleteCounterpart(Long  id);
	/**
	 * 删除合同收款明细关联表
	 * @param id 合同id
	 */
	void deleteContractId(Long  id);

}
