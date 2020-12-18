package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.vo.ContractAccordingRequestVO;

import java.util.List;

/**
 * 合同依据管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-24 14:20:31
 */
public interface ContractAccordingMapper extends BaseMapper<ContractAccordingEntity> {

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param according
	 * @return
	 */
	IPage<ContractAccordingEntity> pageList(IPage<ContractAccordingEntity> page, ContractAccordingRequestVO according);

	/**
	 * 根据合同id查询查询相关依据信息
	 *
	 * @param id
	 * @return
	 */
	List<ContractAccordingEntity> selectByIds(Long id);



	/**
	 * 根据合同id查询查询相关依据信息
	 *
	 * @param id
	 * @return
	 */
	void deleteAccording(Long id);


}
