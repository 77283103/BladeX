package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.CollectionEntity;
import org.springblade.contract.vo.CollectionRequestVO;
import org.springblade.contract.vo.CollectionResponseVO;

import java.util.List;

/**
 * 合同收款明细 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 19:35:04
 */
public interface CollectionMapper extends BaseMapper<CollectionEntity> {

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param counterpart
	 * @return
	 */
	IPage<CollectionResponseVO> pageList(IPage<CollectionEntity> page, CollectionRequestVO counterpart);

	/**
	 * @param contractId 合同ID
	 * @return java.util.List<org.springblade.contract.entity.CollectionEntity>
	 * @author jitwxs
	 * @date 2021/7/8 19:49
	 */
	List<CollectionEntity> selectByIdList(@Param("contractId") Long contractId);
	/**
	 * 删除合同收款明细关联表
	 * @param id 合同id
	 */
	void deleteCounterpart(Long  id);
	/**
	 * 删除合同收款明细关联表
	 * @param id 合同id
	 */
	void deleteContractId(Long  id);

}
