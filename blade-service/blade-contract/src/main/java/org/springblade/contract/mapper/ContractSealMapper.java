package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.ContractSealEntity;
import org.springblade.contract.vo.ContractSealRequestVO;

import java.util.List;

/**
 * 统一子公司（签章申请单位） Mapper 接口
 *
 * @author xhb
 * @date : 2021-06-16 16:10:59
 */
public interface ContractSealMapper extends BaseMapper<ContractSealEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractSeal
	 * @return
	 */
	IPage<ContractSealEntity> pageList(IPage<ContractSealEntity> page, ContractSealRequestVO contractSeal);
	/**
	 *
	 * @author jitwxs
	 * @date 2021/6/18 11:55
	 * @param fdFactno
	 * @return org.springblade.contract.entity.ContractSealEntity
	 */
	ContractSealEntity selectAllByFdTaxnoAfter(@Param("fdTaxno") String fdFactno);
	/**
	 * 根据合同id查询相对方集合
	 * @param id 合同id
	 * @return
	 */
	List<ContractSealEntity> selectByIds(Long id);
}
