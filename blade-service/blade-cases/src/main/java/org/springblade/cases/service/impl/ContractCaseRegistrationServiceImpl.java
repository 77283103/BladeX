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
import org.springblade.contract.feign.IContractClient;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

	private IContractClient contractClient;
	private ContractCaseHandlingMapper handlingMapper;
	private ContractCaseClosedMapper closedMapper;
	private ContractCaseRegistrationMapper registrationMapper;
	private IFileClient fileClient;
	@Override
	public IPage<ContractCaseRegistrationResponseVO> pageList(IPage<ContractCaseRegistrationEntity> page, ContractCaseRegistrationRequestVO contractCaseRegistration) {
		String[] code = contractCaseRegistration.getCaseStatus().split(",");
		contractCaseRegistration.setCode(Arrays.asList(code));
		page=baseMapper.pageList(page, contractCaseRegistration);
		IPage<ContractCaseRegistrationResponseVO> pages=ContractCaseRegistrationWrapper.build().entityPVPage(page);
		List<ContractCaseRegistrationResponseVO> records = pages.getRecords();
		List<ContractCaseRegistrationResponseVO> recordList = new ArrayList<>();
		/*为每个对象，设置合同信息*/
		for(ContractCaseRegistrationResponseVO v : records){
			List<ContractFormInfoEntity> list =new ArrayList<>();
			ContractFormInfoEntity infoEntity= contractClient.getById(v.getAssociatedContract()).getData();
			list.add(infoEntity);
			if (Func.isNotEmpty(infoEntity)) {
				v.setInfoEntity(list);
				v.setContractStatus(infoEntity.getContractStatus());
				v.setCurrencyCategory(infoEntity.getCurrencyCategory());
				v.setContractStatus(infoEntity.getContractStatus());
				v.setCurrencyCategory(infoEntity.getCurrencyCategory());
			}
			recordList.add(v);
		}
		pages.setRecords(recordList);
		return pages;
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
		if (Func.notNull(registrationEntity.getAssociatedContract())){
			List<ContractFormInfoEntity> list =new ArrayList<>();
			ContractFormInfoResponseVO contractFormInfo=contractClient.getById(registrationEntity.getAssociatedContract()).getData();
			list.add(contractFormInfo);
			registrationResponseVO.setInfoEntity(list);
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
				//查询案件处理附件
				if (Func.isNoneBlank(handleResponseVO.getAttachedFiles())){
					R<List<FileVO>> result = fileClient.getByIds(handleResponseVO.getAttachedFiles());
					if (result.isSuccess()){
						registrationResponseVO.setHandleAttachedFileVOList(result.getData());
					}
				}
				//查询案件处理附件
				if (Func.isNoneBlank(handleResponseVO.getReply())){
					R<List<FileVO>> result = fileClient.getByIds(handleResponseVO.getReply());
					if (result.isSuccess()){
						registrationResponseVO.setHandleReplyVOList(result.getData());
					}
				}
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
				//查询案件处理附件
				if (Func.isNoneBlank(closedResponseVO.getCloseCaseDocument())){
					R<List<FileVO>> result = fileClient.getByIds(closedResponseVO.getCloseCaseDocument());
					if (result.isSuccess()){
						registrationResponseVO.setCloseCaseDocumentVOList(result.getData());
					}
				}
			}
		}
		//查询合同文本
		if (Func.isNoneBlank(registrationResponseVO.getAttachedFiles())){
			R<List<FileVO>> result = fileClient.getByIds(registrationResponseVO.getAttachedFiles());
			if (result.isSuccess()){
				registrationResponseVO.setAttachedFileVOList(result.getData());
			}
		}
		return registrationResponseVO;
	}

	@Override
	public void updateCaseStatusById(Long id, String caseStatus) {
		registrationMapper.updateCaseStatusById(id,caseStatus);
	}


}
