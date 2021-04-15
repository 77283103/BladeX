package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.DraftContractCounterpartEntity;
import org.springblade.contract.vo.ContractCounterpartRequestVO;

import java.util.List;

/**
 * 相对方管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 19:35:04
 */
public interface DraftContractCounterpartMapper extends BaseMapper<DraftContractCounterpartEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param counterpart
	 * @return
	 */
	IPage<DraftContractCounterpartEntity> pageList(IPage<DraftContractCounterpartEntity> page, DraftContractCounterpartEntity counterpart);

	/**
	 * 根据合同ID查询多方的向对方信息
	 * @param id
	 * @return
	 */
	List<DraftContractCounterpartEntity> selectByContractId(@Param("contract_id") Long id);

	/**
	 * 删除合同相对方关联表
	 * @param id 合同id
	 */
	void deleteDraftCounterpart(Long  id);


	/**
	 * 保存合同依据关联表
	 * @param draftList
	 */
	void saveDraftCounterpart(List<DraftContractCounterpartEntity> draftList);
}
