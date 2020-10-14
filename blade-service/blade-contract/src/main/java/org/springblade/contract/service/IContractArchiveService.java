package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.entity.ContractAssessmentEntity;
import org.springblade.contract.vo.ContractArchiveRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 合同归档管理 服务类
 *
 * @author XHB
 * @date : 2020-09-23 18:32:14
 */
public interface IContractArchiveService extends BaseService<ContractArchiveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param archive
	 * @return
	 */
	IPage<ContractArchiveEntity> pageList(IPage<ContractArchiveEntity> page, ContractArchiveEntity archive);

	/**
	 *保存归档信息与修改合同状态
	 * @param contractStatus
	 * @param entity
	 * @return
	 */
	boolean save(String contractStatus, ContractArchiveEntity entity);

	/**
	 * 保存归档信息方法
	 * @param vo
	 */
	void saveArchive(ContractArchiveRequestVO vo);

}
