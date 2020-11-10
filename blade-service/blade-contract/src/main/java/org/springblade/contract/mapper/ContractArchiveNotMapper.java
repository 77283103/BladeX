package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import org.springblade.contract.vo.ContractArchiveNotRequestVO;

/**
 * 未归档原因 Mapper 接口
 *
 * @author 未归档原因
 * @date : 2020-11-09 15:19:17
 */
public interface ContractArchiveNotMapper extends BaseMapper<ContractArchiveNotEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractArchiveNot
	 * @return
	 */
	IPage<ContractArchiveNotEntity> pageList(IPage<ContractArchiveNotEntity> page, ContractArchiveNotRequestVO contractArchiveNot);

	/**
	 *根据合同id查询未归档信息
	 * @param id
	 * @return
	 */
	ContractArchiveNotEntity selectArchiveNotById(Long id);
}
