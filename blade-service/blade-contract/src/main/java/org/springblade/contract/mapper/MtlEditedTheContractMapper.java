package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlEditedTheContractEntity;
import org.springblade.contract.vo.MtlEditedTheContractRequestVO;

/**
 * 媒体类：修图合同 Mapper 接口
 *
 * @author 媒体类：修图合同
 * @date : 2020-12-10 19:24:46
 */
public interface MtlEditedTheContractMapper extends BaseMapper<MtlEditedTheContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param mtlEditedTheContract
	 * @return
	 */
	IPage<MtlEditedTheContractEntity> pageList(IPage<MtlEditedTheContractEntity> page, MtlEditedTheContractRequestVO mtlEditedTheContract);

}
