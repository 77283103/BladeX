package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclServiceContractRefrigerationEntity;
import org.springblade.contract.vo.SclServiceContractRefrigerationRequestVO;

/**
 * kx Mapper 接口
 *
 * @author kx
 * @date : 2021-03-15 13:48:16
 */
public interface SclServiceContractRefrigerationMapper extends BaseMapper<SclServiceContractRefrigerationEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param SclServiceContractRefrigeration
	 * @return
	 */
	IPage<SclServiceContractRefrigerationEntity> pageList(IPage<SclServiceContractRefrigerationEntity> page, SclServiceContractRefrigerationRequestVO SclServiceContractRefrigeration);

}
