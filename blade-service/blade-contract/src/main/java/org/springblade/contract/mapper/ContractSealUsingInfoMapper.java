package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;

/**
 * 用印名称 Mapper 接口
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
public interface ContractSealUsingInfoMapper extends BaseMapper<ContractSealUsingInfoEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sealInfo
	 * @return
	 */
	IPage<ContractSealUsingInfoEntity> pageList(IPage<ContractSealUsingInfoEntity> page, ContractSealUsingInfoEntity sealInfo);

}
