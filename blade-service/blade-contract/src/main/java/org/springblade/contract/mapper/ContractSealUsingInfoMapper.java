package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springblade.contract.vo.ContractSealUsingInfoRequestVO;

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
	IPage<ContractSealUsingInfoEntity> pageList(IPage<ContractSealUsingInfoEntity> page, ContractSealUsingInfoRequestVO sealInfo);


	/**
	 * 一对多
	 * 根据合同id再关联表里面查询用印信息
	 * @param id
	 * @return
	 */
	ContractSealUsingInfoEntity selectBySealUsingInfoId(Long id);

	/**
	 * 一对一
	 * 根据合同id查询用印信息
	 */
	ContractSealUsingInfoEntity selectUsingById(Long id);
}
