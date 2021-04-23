package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMultPaymenEntity;
/**
 * 多方相对方收付款 Mapper 接口
 *
 * @author xhb
 * @date : 2021-04-23 17:30:31
 */
public interface ContractMultPaymenMapper extends BaseMapper<ContractMultPaymenEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractMultPaymen
	 * @return
	 */
	IPage<ContractMultPaymenEntity> pageList(IPage<ContractMultPaymenEntity> page, ContractMultPaymenEntity contractMultPaymen);


}
