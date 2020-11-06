package org.springblade.cases.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.cases.entity.ContractCaseClosedEntity;
import org.springblade.cases.vo.ContractCaseClosedRequestVO;

/**
 * 案件结案 Mapper 接口
 *
 * @author xhb
 * @date : 2020-10-30 10:03:18
 */
public interface ContractCaseClosedMapper extends BaseMapper<ContractCaseClosedEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractCaseClosed
	 * @return
	 */
	IPage<ContractCaseClosedEntity> pageList(IPage<ContractCaseClosedEntity> page, ContractCaseClosedRequestVO contractCaseClosed);

	/**
	 * 结案关联
	 * @param id
	 * @return
	 */
	ContractCaseClosedEntity selectById(Long id);
}
