package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractArchiveEntity;

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
	 * @param archive
	 * @return
	 */
	IPage<ContractArchiveEntity> pageList(IPage<ContractArchiveEntity> page, ContractArchiveEntity archive);

}
