package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.DistServiceContractEntity;

/**
 * 配送服务合同 Mapper 接口
 *
 * @author 王策
 * @date : 2021-01-18 17:24:26
 */
public interface DistServiceContractMapper extends BaseMapper<DistServiceContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param distributionServiceContract
	 * @return
	 */
	IPage<DistServiceContractEntity> pageList(IPage<DistServiceContractEntity> page, DistServiceContractEntity distributionServiceContract);

}
