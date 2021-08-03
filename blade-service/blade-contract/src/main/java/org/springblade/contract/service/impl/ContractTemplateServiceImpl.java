package org.springblade.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.EkpVo;
import org.springblade.contract.dto.middleground.ContractBody;
import org.springblade.contract.entity.*;
import org.springblade.contract.enums.TemplateCodeEnum;
import org.springblade.contract.mapper.ContractCounterpartMapper;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.mapper.ContractTemplateMapper;
import org.springblade.contract.service.IContractTemplateService;
import org.springblade.contract.vo.CglCategorySalesContracts1ResponseVO;
import org.springblade.contract.vo.ContractTemplateRequestVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.contract.wrapper.ContractTemplateWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.TemplateFieldJsonEntity;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_EVEN;

/**
 * 范本管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-24 13:57:38
 */
@Service
@AllArgsConstructor
public class ContractTemplateServiceImpl extends BaseServiceImpl<ContractTemplateMapper, ContractTemplateEntity> implements IContractTemplateService {

	private ContractTemplateMapper templateMapper;
	private ContractFormInfoMapper formInfoMapper;
	private ContractCounterpartMapper contractCounterpartMapper;
	private IFileClient fileClient;
	private IUserClient userClient;
	private ISysClient sysClient;
	private IDictBizClient bizClient;
	private final IAbutmentClient abutmentClient;

	@Override
	public IPage<ContractTemplateResponseVO> pageList(IPage<ContractTemplateEntity> page, ContractTemplateRequestVO template) {
		if (Func.isNotBlank(template.getTemplateStatus())) {
			String[] code = template.getTemplateStatus().split(",");
			template.setCode(Arrays.asList(code));
		}
		page = baseMapper.pageList(page, template);
		IPage<ContractTemplateResponseVO> pages = ContractTemplateWrapper.build().entityPVPage(page);
		List<ContractTemplateResponseVO> records = pages.getRecords();
		List<ContractTemplateResponseVO> recordList = new ArrayList<>();

		for (ContractTemplateResponseVO v : records) {
			/*为每个对象，设置创建者名字和组织名字*/
			String RealName = userClient.userInfoById(v.getCreateUser()).getData().getRealName();
			if (Func.isEmpty(RealName)) {
				v.setUserRealName(userClient.userInfoById(v.getCreateUser()).getData().getRealName());
			}
			v.setUserRealName(RealName);
			String DeptName = sysClient.getDept(v.getCreateDept()).getData().getDeptName();
			if (Func.isEmpty(DeptName)) {
				v.setUserDepartName(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
			}
			v.setUserDepartName(DeptName);
			v.setUsageRate(BigDecimal.valueOf(templateMapper.selectByIdUsageRate(v.getId()).doubleValue())
				.divide(BigDecimal.valueOf(templateMapper.selectByIdTemplateCount().doubleValue()), 4, ROUND_HALF_EVEN).doubleValue() * 100 + "%");
			v.setAuthenticPerformanceCount(templateMapper.selectByIdFulfillingCount(v.getId()));
			v.setCompletedContractCount(templateMapper.selectByIdCompletedCount(v.getId()));
			v.setUsageRecord(String.valueOf(templateMapper.selectByIdUsageRate(v.getId())));
			//范本所使用过的合同集合
			List<ContractFormInfoEntity> formInfoEntityList = new ArrayList<>();
			if (formInfoMapper.getByIdForm(v.getId()).size() > 0) {
				formInfoMapper.getByIdForm(v.getId()).forEach(form -> {
					StringBuilder name = new StringBuilder();
					for (ContractCounterpartEntity counterpartEntity : contractCounterpartMapper.selectByIds(form.getId())) {
						name.append(counterpartEntity.getName());
						name.append(",");
					}
					name.substring(0, name.length());
					form.setCounterpartName(name.toString());
					formInfoEntityList.add(form);
				});
				v.setFormInfoEntityList(formInfoEntityList);
			}
			if ("NEW".equals(template.getAdditionalPageConditionsTemplate())) {
				/*判断范本是否为最新范本*/
				if (Func.isEmpty(templateMapper.latestById(v.getId()))) {
					recordList.add(v);
				}
			} else if ("ANALYSIS".equals(template.getAdditionalPageConditionsTemplate())) {
				recordList.add(v);
			}
		}
		pages.setRecords(recordList);
		return pages;
	}

	/**
	 * 修改范本状态
	 *
	 * @param templateStatus,id
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateTemplateStatus(String templateStatus, Long id,String enabled) {
		return templateMapper.updateTemplateStatus(templateStatus, id,enabled);
	}

	/**
	 * 范本是否启用
	 *
	 * @param enabled
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateTemplateEnabled(String enabled, Long id) {
		return templateMapper.updateTemplateEnabled(enabled, id);
	}

	/**
	 * 返回文件vo
	 *
	 * @param id
	 * @return
	 */
	@Override
	public ContractTemplateResponseVO getById(Long id) {
		//查询实体数据
		ContractTemplateEntity templateEntity = baseMapper.selectById(id);
		//统计范本使用率，履约中和疼痛数量，已完成合同数量
		templateEntity.setUsageRate(BigDecimal.valueOf(templateMapper.selectByIdUsageRate(id).doubleValue())
			.divide(BigDecimal.valueOf(templateMapper.selectByIdTemplateCount().doubleValue()), 4, ROUND_HALF_EVEN).doubleValue() * 100 + "%");
		templateEntity.setAuthenticPerformanceCount(templateMapper.selectByIdFulfillingCount(id));
		templateEntity.setCompletedContractCount(templateMapper.selectByIdCompletedCount(id));
		//将实体数据存入vo
		ContractTemplateResponseVO templateResponseVO = ContractTemplateWrapper.build().entityPV(templateEntity);
		//判断vo的文件id是否为空
		if (Func.isNoneBlank(templateResponseVO.getAttachedFiles())) {
			//根据文件id查询关联的文件信息
			R<List<FileVO>> result = fileClient.getByIds(templateResponseVO.getAttachedFiles());
			//潘顿查询是否成功
			if (result.isSuccess()) {
				//将文件信息set到vo 的 list
				templateResponseVO.setTemplateFileVOList(result.getData());
			}
		}
		//判断FTL文件ID是否为空
		if (Func.isNoneBlank(templateResponseVO.getTemplateFileId())) {
			//根据文件id查询关联的文件信息
			R<List<FileVO>> result = fileClient.getByIds(templateResponseVO.getTemplateFileId());
			//潘顿查询是否成功
			if (result.isSuccess()) {
				//将文件信息set到vo 的 list
				templateResponseVO.setTemplateFileFTLVOList(result.getData());
			}
		}
		/* 查询创建者 */
		if (Func.isNoneBlank(templateResponseVO.getCreateUser().toString())) {
			User user = userClient.userInfoById(templateResponseVO.getCreateUser()).getData();
			templateResponseVO.setUserRealName(user.getRealName());
		}
		/* 查询创建者组织 */
		if (Func.isNoneBlank(templateResponseVO.getCreateDept().toString())) {
			String dept = sysClient.getDeptName(templateResponseVO.getCreateDept()).getData();
			templateResponseVO.setUserDepartName(dept);
		}
		//范本所使用过的合同集合
		List<ContractFormInfoEntity> formInfoEntityList = new ArrayList<>();
		if (formInfoMapper.getByIdForm(templateResponseVO.getId()).size() > 0) {
			formInfoMapper.getByIdForm(templateResponseVO.getId()).forEach(form -> {
				StringBuilder name = new StringBuilder();
				for (ContractCounterpartEntity counterpartEntity : contractCounterpartMapper.selectByIds(form.getId())) {
					name.append(counterpartEntity.getName());
					name.append(",");
				}
				name.substring(0, name.length());
				form.setCounterpartName(name.toString());
				formInfoEntityList.add(form);
			});
			templateResponseVO.setFormInfoEntityList(formInfoEntityList);
		}
		//返回vo
		return templateResponseVO;
	}

	/**
	 * 根据模板id查询历史版本列表
	 *
	 * @param id
	 * @return
	 */
	@Override
	public ContractTemplateResponseVO getByNewId(Long id) {
		List<ContractTemplateEntity> templateEntityList = new ArrayList<>();
		//根据最新版本id查询最新范本数据
		ContractTemplateEntity templateEntity = baseMapper.selectById(id);
		templateEntity.setUsageRate(BigDecimal.valueOf(templateMapper.selectByIdUsageRate(id).doubleValue())
			.divide(BigDecimal.valueOf(templateMapper.selectByIdTemplateCount().doubleValue()), 4, ROUND_HALF_EVEN).doubleValue() * 100 + "%");
		templateEntity.setAuthenticPerformanceCount(templateMapper.selectByIdFulfillingCount(id));
		templateEntity.setCompletedContractCount(templateMapper.selectByIdCompletedCount(id));
		while (templateEntity.getOriginalTemplateId() != null && templateEntity.getOriginalTemplateId() != -1) {
			templateEntity = baseMapper.selectById(templateEntity.getOriginalTemplateId());
			templateEntity.setUsageRate(BigDecimal.valueOf(templateMapper.selectByIdUsageRate(templateEntity.getId()).doubleValue())
				.divide(BigDecimal.valueOf(templateMapper.selectByIdTemplateCount().doubleValue()), 4, ROUND_HALF_EVEN).doubleValue() * 100 + "%");
			templateEntity.setAuthenticPerformanceCount(templateMapper.selectByIdFulfillingCount(templateEntity.getId()));
			templateEntity.setCompletedContractCount(templateMapper.selectByIdCompletedCount(templateEntity.getId()));
			//范本所使用过的合同集合
			List<ContractFormInfoEntity> formInfoEntityList = new ArrayList<>();
			if (formInfoMapper.getByIdForm(templateEntity.getId()).size() > 0) {
				formInfoMapper.getByIdForm(templateEntity.getId()).forEach(form -> {
					StringBuilder name = new StringBuilder();
					for (ContractCounterpartEntity counterpartEntity : contractCounterpartMapper.selectByIds(form.getId())) {
						name.append(counterpartEntity.getName());
						name.append(",");
					}
					name.substring(0, name.length());
					form.setCounterpartName(name.toString());
					formInfoEntityList.add(form);
				});
				templateEntity.setFormInfoEntityList(formInfoEntityList);
			}
			templateEntityList.add(templateEntity);
		}
		//将实体数据存入void
		ContractTemplateResponseVO templateResponseVO = ContractTemplateWrapper.build().entityPV(templateEntity);
		templateResponseVO.setTemplateEntityOldVOList(templateEntityList);
		return templateResponseVO;
	}


	private static final Integer TEMPLATE_NUMBER_DIGITS = 9;
	private static final Integer TEMPLATE_NUMBER_TENSE = 99;

	/**
	 * 新增范本，规范生成范本编号 注：修订时范本编号与原合同编号相同
	 *
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(ContractTemplateEntity entity, String type) {
		if ("SAVE".equals(type)) {
			String templateType = bizClient.getById(Long.valueOf(entity.getContractBigCategory())).getData().getDictKey() +
				"-" + bizClient.getById(Long.valueOf(entity.getContractSmallCategory())).getData().getDictKey();
			Integer number = templateMapper.selectByIdTemplateNumber(templateType);
			if (Func.isEmpty(number)) {
				entity.setTemplateNumber(templateType + "-00" + 1);
				templateMapper.instertTemplateNumbered(templateType, 1);
			} else {
				templateMapper.updateTemplateNumbered(templateType, number += 1);
				if (number <= TEMPLATE_NUMBER_DIGITS) {
					entity.setTemplateNumber(templateType + "-00" + number);
				} else if (number > TEMPLATE_NUMBER_DIGITS) {
					entity.setTemplateNumber(templateType + "-0" + number);
				} else if (number >= TEMPLATE_NUMBER_TENSE) {
					entity.setTemplateNumber(templateType + "-" + number);
				}
			}
		} else if ("REVISION".equals(type)) {
			entity.setTemplateNumber(baseMapper.selectById(entity.getOriginalTemplateId()).getTemplateNumber());
		}
		return super.save(entity);
	}

	/**
	 * 模板审批
	 * @param entity
	 * @return
	 */
	@Override
	public R<EkpVo> appTemplate(ContractTemplateEntity entity) {
		R<User> user = userClient.userInfoById(AuthUtil.getUserId());
		entity.setAppNumber(String.valueOf(user.getData().getCode()));
		R<EkpVo> ekpVo = abutmentClient.temEkpFormPost(entity);
		if (ekpVo.getCode() == HttpStatus.OK.value()) {
			entity.setAppCode(ekpVo.getData().getDoc_info());
			return R.status(true);
		} else if (ekpVo.getCode() == 2) {
			return R.data(ekpVo.getCode(), null, "当前员工编号在系统中不存在，请换一个试试");
		} else {
			return R.data(ekpVo.getCode(), null, "token获取失败，连接超时");
		}
	}

	/**
	 * 根据范本名称合同范本模板编号筛选是否存在新增重复范本
	 *
	 * @param templateName
	 * @param templateCode
	 * @return
	 */
	@Override
	public List<ContractTemplateEntity> FilterDuplicates(String templateName, String templateCode) {
		List<ContractTemplateEntity> templateEntityList = new ArrayList<>();
		templateMapper.FilterDuplicates(templateName, templateCode).forEach(template -> {
			template.setUsageRate(BigDecimal.valueOf(templateMapper.selectByIdUsageRate(template.getId()).doubleValue())
				.divide(BigDecimal.valueOf(templateMapper.selectByIdTemplateCount().doubleValue()), 4, ROUND_HALF_EVEN).doubleValue() * 100 + "%");
			template.setAuthenticPerformanceCount(templateMapper.selectByIdFulfillingCount(template.getId()));
			template.setCompletedContractCount(templateMapper.selectByIdCompletedCount(template.getId()));
			//范本所使用过的合同集合
			List<ContractFormInfoEntity> formInfoEntityList = new ArrayList<>();
			if (formInfoMapper.getByIdForm(template.getId()).size() > 0) {
				formInfoMapper.getByIdForm(template.getId()).forEach(form -> {
					StringBuilder name = new StringBuilder();
					for (ContractCounterpartEntity counterpartEntity : contractCounterpartMapper.selectByIds(form.getId())) {
						name.append(counterpartEntity.getName());
						name.append(",");
					}
					name.substring(0, name.length());
					form.setCounterpartName(name.toString());
					formInfoEntityList.add(form);
				});
				template.setFormInfoEntityList(formInfoEntityList);
			}
			templateEntityList.add(template);
		});
		return templateEntityList;
	}

	@Override
	public List<ContractBody> templateToMiddlegroundContractBody(Long templateId,String contractJson) {
		if(Func.isEmpty(templateId) || Func.isEmpty(contractJson)){
			return new ArrayList<>();
		}
		List<ContractBody> contractBodies = new ArrayList<>();
		ContractTemplateResponseVO contractTemplate = getById(templateId);
		String templateCode = Func.isNotEmpty(contractTemplate)?contractTemplate.getTemplateCode():"";


		List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(contractJson, TemplateFieldJsonEntity.class);
		String tableJson = "";

		for(TemplateFieldJsonEntity templateFieldJson:templateFieldList){
			if(Func.isNotEmpty(templateFieldJson.getTableData())){
				tableJson = templateFieldJson.getTableData();
				break;
			}
		}

		if(templateCode.equals(TemplateCodeEnum.MMHT_08.getKey())){
			List<CglLowCostHardware1Entity> cglLowCostHardware1Entities = JSON.parseArray(tableJson,CglLowCostHardware1Entity.class);
			cglLowCostHardware1Entities.forEach(cglLowCostHardware1Entity -> {
				ContractBody contractBody = new ContractBody();
				contractBody.setMtrl_no(cglLowCostHardware1Entity.getCglNumber().toString());
				contractBody.setMtrl_name(cglLowCostHardware1Entity.getCglOfTheGoods());
				contractBody.setMtrl_spec(cglLowCostHardware1Entity.getCglSpecifications());
				contractBody.setCondition(null);
				contractBody.setAccount_period_detail(cglLowCostHardware1Entity.getCglPaymentDays());
				contractBody.setDeliver_point(cglLowCostHardware1Entity.getCglCompany());
				contractBody.setUprice(cglLowCostHardware1Entity.getCglUnitPrice());
				contractBody.setUnit_m(cglLowCostHardware1Entity.getCglUnit());
				contractBody.setTaxrate(cglLowCostHardware1Entity.getCglRate());
				contractBody.setQty(cglLowCostHardware1Entity.getCglMount());
				contractBody.setQty_begin(cglLowCostHardware1Entity.getCglMinimumQuantity());
				contractBody.setPrice_e_date(cglLowCostHardware1Entity.getCglStartingTime());
				contractBody.setPrice_b_date(cglLowCostHardware1Entity.getCglEndOfTime());
				contractBodies.add(contractBody);
			});
		}
		if(templateCode.equals(TemplateCodeEnum.MMHT_10.getKey())){
			List<CglRawMaterials1Entity> cglRawMaterials1Entities = JSON.parseArray(tableJson,CglRawMaterials1Entity.class);
			cglRawMaterials1Entities.forEach(cglRawMaterials1Entity -> {
				ContractBody contractBody = new ContractBody();
				contractBody.setMtrl_no(cglRawMaterials1Entity.getCglNumber().toString());
				contractBody.setMtrl_name(cglRawMaterials1Entity.getCglOfTheGoods());
				contractBody.setMtrl_spec(cglRawMaterials1Entity.getCglSpecifications());
				contractBody.setCondition(null);
				contractBody.setAccount_period_detail(cglRawMaterials1Entity.getCglPaymentDays());
				contractBody.setDeliver_point(cglRawMaterials1Entity.getCglCompany());
				contractBody.setUprice(cglRawMaterials1Entity.getCglUnitPrice());
				contractBody.setUnit_m(cglRawMaterials1Entity.getCglUnit());
				contractBody.setTaxrate(cglRawMaterials1Entity.getCglRate());
				contractBody.setQty(cglRawMaterials1Entity.getCglMount());
				contractBody.setQty_begin(cglRawMaterials1Entity.getCglMinimumQuantity());
				contractBody.setPrice_e_date(cglRawMaterials1Entity.getCglStartingTime());
				contractBody.setPrice_b_date(cglRawMaterials1Entity.getCglEndOfTime());
				contractBodies.add(contractBody);
			});
		}
		return contractBodies;
	}
}
