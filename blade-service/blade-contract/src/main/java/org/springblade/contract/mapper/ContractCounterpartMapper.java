package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.vo.ContractCounterpartRequestVO;

import java.util.List;

/**
 * 相对方管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 19:35:04
 */
public interface ContractCounterpartMapper extends BaseMapper<ContractCounterpartEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param counterpart
	 * @return
	 */
	IPage<ContractCounterpartEntity> pageList(IPage<ContractCounterpartEntity> page, ContractCounterpartRequestVO counterpart);

	/**
	 * 根据合同id查询相对方集合
	 * @param id 合同id
	 * @return
	 */
	List<ContractCounterpartEntity> selectByIds(Long id);

	/**
	 *
	 * @param id
	 * @return
	 */
	ContractCounterpartEntity selectById(Long id);

	/**
	 * 根据相对方名称获取集合
	 * @param unifiedSocialCreditCode 信用代码
	 * @param name
	 * @return
	 */
	List<ContractCounterpartEntity> selectByUnifiedSocialCreditCode(@Param("unifiedSocialCreditCode") String unifiedSocialCreditCode,
												                    @Param("name") String name);

	/**
	 * 根据相对方名称获取集合
	 * @param unifiedSocialCreditCode 信用代码
	 * @return
	 */
	List<ContractCounterpartEntity> selectByName(@Param("unifiedSocialCreditCode") String unifiedSocialCreditCode);
	/**
	 *
	 * @param contractCounterpartEntity 相对方信息
	 */
	void updateByName(ContractCounterpartEntity contractCounterpartEntity);

	/**
	 *
	 *批量新增
	 * @param postList
	 * @return
	 */
	boolean saveBatchPost(@Param("ContractCounterpartList") List<ContractCounterpartEntity> postList);

}
