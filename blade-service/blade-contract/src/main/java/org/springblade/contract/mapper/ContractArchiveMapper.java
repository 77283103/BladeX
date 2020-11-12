package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.entity.ContractAssessmentEntity;
import org.springblade.contract.vo.ContractArchiveRequestVO;

/**
 * 合同归档管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 18:32:13
 */
public interface ContractArchiveMapper extends BaseMapper<ContractArchiveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractArchive
	 * @return
	 */
	IPage<ContractArchiveEntity> pageList(IPage<ContractArchiveEntity> page, ContractArchiveRequestVO contractArchive);


	/**
	 * 根据关联表查询合同id查询关联评估信息
	 * @param id
	 * @return
	 */
	ContractArchiveEntity selectByArchiveId(Long id);

	/**
	 * 一对一合同id查询归档信息
	 * @param id
	 * @return
	 */
	ContractArchiveEntity selectArchiveById(Long id);
}
