package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.BusServiceContractEntity;

/**
 * 班车服务合同 Mapper 接口
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:25:16
 */
public interface BusServiceContractMapper extends BaseMapper<BusServiceContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param busServiceContract
	 * @return
	 */
	IPage<BusServiceContractEntity> pageList(IPage<BusServiceContractEntity> page, BusServiceContractEntity busServiceContract);

}
