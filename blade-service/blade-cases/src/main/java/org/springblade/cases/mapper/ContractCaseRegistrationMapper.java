package org.springblade.cases.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import org.springblade.cases.vo.ContractCaseRegistrationRequestVO;

/**
 * 案件登记表 Mapper 接口
 *
 * @author xhb
 * @date : 2020-10-30 10:04:53
 */
public interface ContractCaseRegistrationMapper extends BaseMapper<ContractCaseRegistrationEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractCaseRegistration
	 * @return
	 */
	IPage<ContractCaseRegistrationEntity> pageList(IPage<ContractCaseRegistrationEntity> page, ContractCaseRegistrationRequestVO contractCaseRegistration);

	/**
	 * 修改案件状态
	 * @param id
	 * @param caseStatus
	 * @return
	 */
	boolean updateCaseStatusById(@Param("id") Long id, @Param("case_status") String caseStatus);
}
