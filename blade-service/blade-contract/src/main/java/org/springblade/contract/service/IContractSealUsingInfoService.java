package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractSealUsingInfoRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;

/**
 * 用印名称 服务类
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
public interface IContractSealUsingInfoService extends BaseService<ContractSealUsingInfoEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sealInfo
	 * @return
	 */
	IPage<ContractSealUsingInfoEntity> pageList(IPage<ContractSealUsingInfoEntity> page, ContractSealUsingInfoEntity sealInfo);

	/**
	 * 插入数据 修改合同状态
	 * @param entity
	 * @return
	 */
	boolean save(String contractStatus, ContractSealUsingInfoEntity entity);


	/**
	 * 保存归档信息方法
	 * @param vo
	 */
	void saveSeal(ContractSealUsingInfoRequestVO vo);
}
