package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.contract.vo.ContractAccordingResponseVO;

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
	IPage<ContractAccordingResponseVO> pageList(IPage<ContractAccordingResponseVO> page, ContractAccordingRequestVO according);

	/**
	 * 根据合同id查询查询相关依据信息
	 *
	 * @param id
	 * @return
	 */
	Integer selectByContractIds(@Param("id") Long id);

	/**
	 * 根据依据文件编号查询是否已存在的依据
	 *
	 * @param contractId
	 * @return
	 */
	List<ContractAccordingEntity> selectByIds(@Param("contractId") Long contractId);

	/**
	 * 根据依据文件编号查询是否已存在的依据
	 *
	 * @param acList
	 * @return
	 */
	List<ContractAccordingEntity> selectByFileId(@Param("acList") List<String> acList);

	/**
	 * 根据合同id查询删除相关依据信息
	 *
	 * @param id
	 * @return
	 */
	void deleteAccording(@Param("id") Long id);


	/**
	 * 根据code获取集合
	 * @param codes
	 * @return
	 */
	List<ContractAccordingEntity> findListByCodes(List<String> codes);


	/**
	 * 根据code获取依据
	 * @param code
	 * @return
	 */
	ContractAccordingEntity findListByCode(String code);


	/**
	 * 批量起草-保存合同、依据关系数据
	 * @param accordingId
	 * @param contractIds
	 */
	void saveByBatchDraft(Long accordingId, List<Long> contractIds);

}

