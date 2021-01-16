package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.InferiorProductContractEntity;

/**
 * 下脚品买卖合同模板 Mapper 接口
 *
 * @author Wang Pengfei
 * @date : 2021-01-15 15:54:16
 */
public interface InferiorProductContractMapper extends BaseMapper<InferiorProductContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param inferiorProductContract
	 * @return
	 */
	IPage<InferiorProductContractEntity> pageList(IPage<InferiorProductContractEntity> page, InferiorProductContractEntity inferiorProductContract);

}
