package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractAccordingResponseVO;
import org.springblade.contract.vo.ContractSigningRequestVO;
import org.springblade.contract.vo.ContractSigningResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractSigningEntity;

/**
 * 合同签订表 服务类
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
public interface IContractSigningService extends BaseService<ContractSigningEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param signing
	 * @return
	 */
	IPage<ContractSigningEntity> pageList(IPage<ContractSigningEntity> page, ContractSigningRequestVO signing);

	/**
	 * 插入数据
	 * @param contractStatus
	 * @param entity
	 * @return
	 */
	boolean save(String contractStatus, ContractSigningEntity entity);

	/**
	 * 保存签订关联
	 * @param vo 从vo中获取对应id值
	 */
	void saveSigning(ContractSigningRequestVO vo);

	/**
	 * 根据合同id查询签订信息
	 * 一对一
	 * @param id
	 * @return
	 */
	ContractSigningEntity selectSigningById(Long id);
}
