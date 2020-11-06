package org.springblade.cases.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.cases.entity.ContractCaseClosedEntity;
import org.springblade.cases.entity.ContractCaseHandlingEntity;
import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import org.springblade.cases.mapper.ContractCaseClosedMapper;
import org.springblade.cases.mapper.ContractCaseHandlingMapper;
import org.springblade.cases.mapper.ContractCaseRegistrationMapper;
import org.springblade.cases.service.IContractCaseRegistrationService;
import org.springblade.cases.vo.ContractCaseClosedResponseVO;
import org.springblade.cases.vo.ContractCaseHandlingResponseVO;
import org.springblade.cases.vo.ContractCaseRegistrationRequestVO;
import org.springblade.cases.vo.ContractCaseRegistrationResponseVO;
import org.springblade.cases.wrapper.ContractCaseClosedWrapper;
import org.springblade.cases.wrapper.ContractCaseHandlingWrapper;
import org.springblade.cases.wrapper.ContractCaseRegistrationWrapper;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 案件登记表 服务实现类
 *
 * @author xhb
 * @date : 2020-10-30 10:04:57
 */
@Service
@AllArgsConstructor
public class ContractCaseRegistrationServiceImpl extends BaseServiceImpl<ContractCaseRegistrationMapper, ContractCaseRegistrationEntity> implements IContractCaseRegistrationService {

	private ContractFormInfoMapper infoMapper;
	private ContractCaseHandlingMapper handlingMapper;
	private ContractCaseClosedMapper closedMapper;
	private ContractCaseRegistrationMapper registrationMapper;
	@Override
	public IPage<ContractCaseRegistrationEntity> pageList(IPage<ContractCaseRegistrationEntity> page, ContractCaseRegistrationRequestVO contractCaseRegistration) {
		page=baseMapper.pageList(page, contractCaseRegistration);
		IPage<ContractCaseRegistrationResponseVO> pages=ContractCaseRegistrationWrapper.build().entityPVPage(page);
		List<ContractCaseRegistrationResponseVO> records = pages.getRecords();
		List<ContractCaseRegistrationResponseVO> recordList = new ArrayList<>();
		/*为每个对象，设置合同信息*/
		for(ContractCaseRegistrationResponseVO v : records){
			ContractFormInfoEntity infoEntity=infoMapper.selectById(v.getAssociatedContract());
			v.setInfoEntity(infoEntity);
			v.setContractStatus(infoEntity.getContractStatus());
			v.setCurrencyCategory(infoEntity.getCurrencyCategory());
			recordList.add(v);
		}
		pages.setRecords(recordList);
		return page;
	}
	/**
	 * 返回vo
	 * @param id
	 * @return
	 */
	@Override
	public ContractCaseRegistrationResponseVO getById(Long id) {
		ContractCaseRegistrationEntity registrationEntity=baseMapper.selectById(id);
		ContractCaseRegistrationResponseVO registrationResponseVO= ContractCaseRegistrationWrapper.build().entityPV(registrationEntity);
		if (Func.isNotBlank(registrationEntity.getAssociatedContract())){
			ContractFormInfoEntity contractFormInfo=infoMapper.selectById(registrationEntity.getAssociatedContract());
			ContractFormInfoResponseVO formInfoResponseVO=ContractFormInfoWrapper.build().entityVO(contractFormInfo);
			registrationResponseVO.setInfoEntity(formInfoResponseVO);
		}
		//将处理信息返回vo
		if (Func.isEmpty(registrationResponseVO.getHandlingEntity())) {
			ContractCaseHandlingEntity handleEntity = handlingMapper.selectById(id);
			if (Func.isNotEmpty(handleEntity)) {
				ContractCaseHandlingResponseVO handleResponseVO = ContractCaseHandlingWrapper.build().entityPV(handleEntity);
				BladeUser user = AuthUtil.getUser();
				Long userId = Long.valueOf(user.getUserId());
				Long deptId = Long.valueOf(AuthUtil.getDeptId());
				handleResponseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
				handleResponseVO.setCreateDeptName(SysCache.getDeptName(deptId));
				registrationResponseVO.setHandlingEntity(handleResponseVO);
			}
		}
		//将结案信息返回vo
		if (Func.isEmpty(registrationResponseVO.getClosedEntity())) {
			ContractCaseClosedEntity closedEntity = closedMapper.selectById(id);
			if (Func.isNotEmpty(closedEntity)) {
				ContractCaseClosedResponseVO closedResponseVO = ContractCaseClosedWrapper.build().entityPV(closedEntity);
				BladeUser user = AuthUtil.getUser();
				Long userId = Long.valueOf(user.getUserId());
				Long deptId = Long.valueOf(AuthUtil.getDeptId());
				closedResponseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
				closedResponseVO.setCreateDeptName(SysCache.getDeptName(deptId));
				registrationResponseVO.setClosedEntity(closedEntity);
			}
		}
		return registrationResponseVO;
	}

	@Override
	public void updateCaseStatusById(Long id, String caseStatus) {
		registrationMapper.updateCaseStatusById(id,caseStatus);
	}


}
