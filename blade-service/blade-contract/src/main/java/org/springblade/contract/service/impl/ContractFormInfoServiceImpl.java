package org.springblade.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import feign.form.ContentType;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springblade.abutment.entity.CompanyInfoEntity;
import org.springblade.abutment.entity.UploadFileEntity;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.CompanyInfoVo;
import org.springblade.abutment.vo.EkpVo;
import org.springblade.abutment.vo.UploadFileVo;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.*;
import org.springblade.contract.excel.ContractFormInfoImporter;
import org.springblade.contract.excel.ContractFormInfoImporterEx;
import org.springblade.contract.mapper.*;
import org.springblade.contract.service.*;
import org.springblade.contract.util.*;
import org.springblade.contract.vo.*;
import org.springblade.contract.wrapper.*;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.cache.SysCache;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.entity.TemplateFieldJsonEntity;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 服务实现类
 *
 * @author 史智伟//
 * @date : 2020-09-23 18:04:38
 */
@Log4j2
@Service
public class ContractFormInfoServiceImpl extends BaseServiceImpl<ContractFormInfoMapper, ContractFormInfoEntity> implements IContractFormInfoService {
	@Value("${api.file.ftlPath}")
	private String ftlPath;
	//模板路径
	//private String ftlPath;
	//private static final String ftlPath="D:/ftl/";
	//private static final String ftlPath="/ftl/";
	@Autowired
	private IFileClient fileClient;
	@Autowired
	private ISysClient sysClient;
	@Autowired
	private IUserClient userClient;
	@Autowired
	private IDictBizClient bizClient;
	@Autowired
	private IAbutmentClient abutmentClient;
	@Autowired
	private ContractFormInfoMapper contractFormInfoMapper;
	@Autowired
	private ContractCounterpartMapper contractCounterpartMapper;
	@Autowired
	private ContractBondMapper contractBondMapper;
	@Autowired
	private ContractBondPlanMapper contractBondPlanMapper;
	@Autowired
	private ContractAccordingMapper contractAccordingMapper;
	@Autowired
	private ContractPerformanceMapper contractPerformanceMapper;
	@Autowired
	private ContractPerformanceColPayMapper contractPerformanceColPayMapper;
	@Autowired
	private ContractAssessmentMapper contractAssessmentMapper;
	@Autowired
	private ContractArchiveMapper contractArchiveMapper;
	@Autowired
	private ContractArchiveNotMapper contractArchiveNotMapper;
	@Autowired
	private ContractSealUsingInfoMapper sealUsingInfoMapper;
	@Autowired
	private ContractSigningMapper signingMapper;
	@Autowired
	private ContractArchiveNotMapper archiveNotMapper;
	@Autowired
	private ContractRelieveMapper relieveMapper;
	@Autowired
	private ContractTemplateMapper contractTemplateMapper;
	@Resource
	private RedisCacheUtil redisCacheUtil;
	@Autowired
	private ContractChangeMapper changeMapper;
	@Autowired
	private IYwlANewDisplay1Service ywlANewDisplay1Service;
	@Autowired
	private ICglCategorySalesContracts1Service cglCategorySalesContracts1Service;
	@Autowired
	private ICglTheSalesContract1Service cglTheSalesContract1Service;
	@Autowired
	private ICglRawMaterials1Service cglRawMaterials1Service;
	@Autowired
	private IMtlAdaptationContract1Service mtlAdaptationContract1Service;
	@Autowired
	private IMtlAdaptationContract2Service mtlAdaptationContract2Service;
	@Autowired
	private IMtlShootingAndProductionContract1Service mtlShootingAndProductionContract1Service;
	@Autowired
	private IMtlShootingAndProductionContract2Service mtlShootingAndProductionContract2Service;
	@Autowired
	private IMtlShootingAndProductionContract3Service mtlShootingAndProductionContract3Service;
	@Autowired
	private ISclProjectOutsourcing1Service sclProjectOutsourcing1Service;
	@Autowired
	private ISclEquipmentMaintenance1Service sclEquipmentMaintenance1Service;
	@Autowired
	private IMtbProductionContract1Service mtbProductionContract1Service;
	@Autowired
	private IMtbProductionContract2Service mtbProductionContract2Service;
	@Autowired
	private IMtbProductionContract3Service mtbProductionContract3Service;
	@Autowired
	private IMtlVideoProductionContract1Service mtlVideoProductionContract1Service;
	@Autowired
	private IMtlVideoProductionContract2Service mtlVideoProductionContract2Service;
	@Autowired
	private IMtlEditedTheContract1Service mtlEditedTheContract1Service;
	@Autowired
	private IMtlAudioProductionContract1Service mtlAudioProductionContract1Service;
	@Autowired
	private IMtlAudioProductionContract2Service mtlAudioProductionContract2Service;
	@Autowired
	private ISclConstructionProject1Service sclConstructionProject1Service;
	@Autowired
	private ISclConstructionProject2Service sclConstructionProject2Service;
	@Autowired
	private ISclConstructionProject3Service sclConstructionProject3Service;
	@Autowired
	private ICglSalesContract1Service cglSalesContract1Service;
	@Autowired
	private ICglProofingContract1Service cglProofingContract1Service;
	@Autowired
	private IProductOutServiceContract1Service productOutServiceContract1Service;
	@Autowired
	private IProductOutServiceContract2Service productOutServiceContract2Service;
	@Autowired
	private IProductOutServiceContract3Service productOutServiceContract3Service;
	@Autowired
	private IMtbMarketResearchContract1Service iMtbMarketResearchContract1Service;
	@Autowired
	private IDraftContractCounterparService iDraftContractCounterparService;
	private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY = "1332307279915393025";
	private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS = "1332307106157961217";
	private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE = "1332307534161518593";
	private static final Long DICT_BIZ_FINAL_VALUE_CONTRACT_PAY_TYPE = 1323239541401841666L;
	private static final Long DICT_BIZ_FINAL_VALUE_CONTRACT_RECEIVE_TYPE = 1323239469884764161L;
	private static final Integer AMOUNT_RATIO_VALUE = 100;
	private static final String CONTRACT_CHANGE_REVIEW = "10";

	@Override
	public IPage<ContractFormInfoResponseVO> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo) {
		if (!Func.isEmpty(contractFormInfo.getContractStatus())) {
			String[] code = contractFormInfo.getContractStatus().split(",");
			contractFormInfo.setCode(Arrays.asList(code));
		}
		//合同管理员根据长
		if (Func.isNotEmpty(contractFormInfo.getSealNames())) {
			contractFormInfo.setSealNames(contractFormInfo.getSealNames());
		}
		page = baseMapper.pageList(page, contractFormInfo);
		IPage<ContractFormInfoResponseVO> pages = ContractFormInfoWrapper.build().entityPVPage(page);
		List<ContractFormInfoResponseVO> records = pages.getRecords();
		List<ContractFormInfoResponseVO> recordList = new ArrayList<>();

		for (ContractFormInfoResponseVO v : records) {
			/*为每个对象，设置创建者名字和组织名字*/
			v.setUserRealName(userClient.userInfoById(v.getCreateUser()).getData().getRealName());
			v.setUserDepartName(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
			//将多方起草关联的   相对方存入合同分页显示 获取相对方名称
			List<ContractCounterpartEntity> counterpartEntityList = contractCounterpartMapper.selectByIds(v.getId());
			if (Func.isNotEmpty(counterpartEntityList)) {
				v.setCounterpart(counterpartEntityList);
				StringBuilder name = new StringBuilder();
				for (ContractCounterpartEntity counterpartEntity : counterpartEntityList) {
					name.append(counterpartEntity.getName());
					name.append(",");
				}
				name.substring(0, name.length());
				v.setCounterpartName(name.toString());
			}
			//将用印信息存入合同分页 获取用印日期
			ContractSealUsingInfoEntity sealUsingInfoEntity = sealUsingInfoMapper.selectUsingById(v.getId());
			if (Func.isNotEmpty(sealUsingInfoEntity)) {
				v.setSignTime(sealUsingInfoEntity.getSignTime());
				v.setSealInfoEntity(sealUsingInfoEntity);
			}
			//将归档信息存入合同分页  获取归档日期，用印申请人，用印申请单位，用印公司,归档月份
			ContractArchiveEntity archiveEntity = contractArchiveMapper.selectArchiveById(v.getId());
			if (Func.isNotEmpty(archiveEntity)) {
				v.setArchiveMonth(archiveEntity.getArchiveMonth());
				v.setPrintApplicant(archiveEntity.getPrintApplicant());
				v.setPrintCompany(archiveEntity.getPrintCompany());
				v.setContractPrintInitDept(archiveEntity.getContractPrintInitDept());
				v.setArchiveEntity(archiveEntity);
			}
			//将签订信息存入合同分页 获取邮寄日期
			ContractSigningEntity signingEntity = signingMapper.selectSigningById(v.getId());
			if (Func.isNotEmpty(signingEntity)) {
				v.setSignDate(signingEntity.getSignDate());
				v.setContractStartingTime(signingEntity.getContractStartTime());
				v.setContractEndTime(signingEntity.getContractEndTime());
				v.setSigningEntity(signingEntity);
			}
			//将未归档信息存入合同分页  获取未归档原因
			List<ContractArchiveNotEntity> archiveNotEntity = contractArchiveNotMapper.selectArchiveNotById(v.getId());
			if (archiveNotEntity.size() > 0) {
				v.setArchiveNotEntity(archiveNotEntity);
			}
			//计算履约进度,保证金，首付款，服务接受
//				List<ContractBondPlanEntity> bondPlanEntityList=bondPlanMapper.selectByIds(v.getId());
//				v.setBondPlanEntityList(bondPlanEntityList);
//				List<ContractPerformanceEntity> performanceEntityList=contractPerformanceMapper.selectByIds(v.getId());
//				v.setPerformanceList(performanceEntityList);
//				List<ContractPerformanceColPayEntity> performanceColPayEntityList=contractPerformanceColPayMapper.selectByIds(v.getId());
//				v.setPerformanceColPayList(performanceColPayEntityList);
//				if (bondPlanEntityList.size()>0 || performanceEntityList.size()>0 || performanceColPayEntityList.size()>0) {
//					String planSchedule=
//							bondPlanMapper.countOKById(v.getId())+contractPerformanceColPayMapper.countOKById(v.getId())+contractPerformanceMapper.countOKById(v.getId())+
//							"/"+
//							Integer.valueOf(bondPlanEntityList.size()+performanceEntityList.size()+performanceColPayEntityList.size());
//					v.setPlanSchedule(planSchedule);
//				}
			//查询合同文本
			if (Func.isNoneBlank(v.getTextFile())) {
				R<List<FileVO>> result = fileClient.getByIds(v.getTextFile());
				if (result.isSuccess()) {
					v.setTestFileVOList(result.getData());
				}
			}
			//查询合同附件
			if (Func.isNoneBlank(v.getAttachedFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(v.getAttachedFiles());
				if (result.isSuccess()) {
					v.setAttachedFileVOList(result.getData());
				}
			}
			recordList.add(v);
		}
		pages.setRecords(recordList);
		return pages;
	}

	/**
	 * 統計分析查詢列表
	 * statisticsList
	 *
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	@Override
	public IPage<ContractFormInfoEntity> statisticsList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo) {
		//部门合同数量、金额统计表
		if ("deptType".equals(contractFormInfo.getStatisticsType())) {
			page = baseMapper.deptType(page, contractFormInfo);
			List<ContractFormInfoEntity> records = page.getRecords();
			List<ContractFormInfoEntity> recordList = new ArrayList<>();
			for (ContractFormInfoEntity v : records) {
				/*为每个对象，设置创建者名字和组织名字*/
				v.setCreateUserName(userClient.userInfoById(v.getCreateUser()).getData().getRealName());
				v.setCreateDeptName(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
				//将多方起草关联的   相对方存入合同分页显示 获取相对方名称
				List<ContractCounterpartEntity> counterpartEntityList = contractCounterpartMapper.selectByIds(v.getId());
				if (Func.isNotEmpty(counterpartEntityList)) {
					v.setCounterpart(counterpartEntityList);
					StringBuilder name = new StringBuilder();
					for (ContractCounterpartEntity counterpartEntity : counterpartEntityList) {
						name.append(counterpartEntity.getName());
						name.append(",");
					}
					name.substring(0, name.length());
					v.setCounterpartName(name.toString());
					//判断是否为整数字符串
					Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
					BigDecimal payAmountVoidData = contractFormInfoMapper.payTypeAmount(Long.valueOf(pattern.matcher(v.getContractBigCategory()).matches()?v.getContractBigCategory():"1326765392596602881"), DICT_BIZ_FINAL_VALUE_CONTRACT_PAY_TYPE, v.getCreateDept(), contractFormInfo.getYearStart(), contractFormInfo.getYearEnd());
					BigDecimal receiveAmountVoidData = contractFormInfoMapper.payTypeAmount(Long.valueOf(pattern.matcher(v.getContractBigCategory()).matches()?v.getContractBigCategory():"1326765392596602881"), DICT_BIZ_FINAL_VALUE_CONTRACT_RECEIVE_TYPE, v.getCreateDept(), contractFormInfo.getYearStart(), contractFormInfo.getYearEnd());
					v.setPayAmountVoidData(payAmountVoidData);
					v.setReceiveAmountVoidData(receiveAmountVoidData);
				}
				recordList.add(v);
				page.setRecords(recordList);
			}
		}
		//月度合同数量统计表
		if ("monthType".equals(contractFormInfo.getStatisticsType())) {
			page = baseMapper.monthTypeFirm(page, contractFormInfo);
			List<ContractFormInfoEntity> records = page.getRecords();
			List<ContractFormInfoEntity> recordList = new ArrayList<>();
			for (ContractFormInfoEntity v : records) {
				//將根據條件查詢出的年裏面的每個月分數據存入分頁的公司類型
				List<MonthTypeSelect> list = contractFormInfoMapper.monthType(v.getCreateDept(), contractFormInfo.getYearStart());
				List<MonthTypeSelect> monthTypeSelectList = new ArrayList<>();
				for (int j = 1; j <= 12; j++) {
					monthTypeSelectList.add(j - 1, null);
				}
				for (MonthTypeSelect t : list) {
					for (int i = 1; i <= 12; i++) {
						if (i == t.getMouth()) {
							List<ContractFormInfoEntity> formInfoEntityList = new ArrayList<>();
							monthTypeSelectList.set(i - 1, t);
							contractFormInfoMapper.monthByIdInfo(v.getCreateDept(), contractFormInfo.getYearStart(), String.valueOf(i)).forEach(info -> {
								//将多方起草关联的   相对方存入合同分页显示 获取相对方名称
								List<ContractCounterpartEntity> counterpartEntityList = contractCounterpartMapper.selectByIds(info.getId());
								if (Func.isNotEmpty(counterpartEntityList)) {
									info.setCounterpart(counterpartEntityList);
									StringBuilder name = new StringBuilder();
									for (ContractCounterpartEntity counterpartEntity : counterpartEntityList) {
										name.append(counterpartEntity.getName());
										name.append(",");
									}
									name.substring(0, name.length());
									info.setCounterpartName(name.toString());
								}
								formInfoEntityList.add(info);
							});
							t.setContractFormInfoEntityList(formInfoEntityList);
						}
					}
					t.setCompany(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
				}
				v.setMonthTypeSelects(monthTypeSelectList);
				v.setCompany(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
				recordList.add(v);
			}
			page.setRecords(recordList);
		}
		//各类型合同统计表
		if ("eachType".equals(contractFormInfo.getStatisticsType())) {
			page = baseMapper.eachType(page, contractFormInfo);
			List<ContractFormInfoEntity> records = page.getRecords();
			List<ContractFormInfoEntity> recordList = new ArrayList<>();
			for (ContractFormInfoEntity v : records) {
				BigDecimal payAmountVoidData = contractFormInfoMapper.payTypeAmount(Long.valueOf(v.getContractBigCategory()), DICT_BIZ_FINAL_VALUE_CONTRACT_PAY_TYPE, v.getCreateDept(), contractFormInfo.getYearStart(), contractFormInfo.getYearEnd());
				BigDecimal receiveAmountVoidData = contractFormInfoMapper.payTypeAmount(Long.valueOf(v.getContractBigCategory()), DICT_BIZ_FINAL_VALUE_CONTRACT_RECEIVE_TYPE, v.getCreateDept(), contractFormInfo.getYearStart(), contractFormInfo.getYearEnd());
				v.setPayAmountVoidData(payAmountVoidData);
				v.setReceiveAmountVoidData(receiveAmountVoidData);
				recordList.add(v);
			}
			page.setRecords(recordList);
		}

		return page;
	}

	/**
	 * 统计分析分页查询
	 *
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	@Override
	public IPage<ContractFormInfoResponseVO> pageListStatistics(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo) {
		if (Func.isNotBlank(contractFormInfo.getContractBigCategory()) && DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY.equals(contractFormInfo.getContractBigCategory())) {
			page = contractFormInfoMapper.pageListStatisticsType(page, contractFormInfo);
		}
		if (Func.isNotBlank(contractFormInfo.getContractStatus()) && DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS.equals(contractFormInfo.getContractStatus())) {
			page = contractFormInfoMapper.pageListStatisticsStatus(page, contractFormInfo);
		}
		if (Func.isNotBlank(contractFormInfo.getColPayType()) && DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE.equals(contractFormInfo.getColPayType())) {
			page = contractFormInfoMapper.pageListStatisticsColPayType(page, contractFormInfo);
		}
		if (Func.isNotBlank(contractFormInfo.getContractBigCategory()) && !DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY.equals(contractFormInfo.getContractBigCategory())) {
			page = contractFormInfoMapper.pageList(page, contractFormInfo);
		}
		if (Func.isNotBlank(contractFormInfo.getContractStatus()) && !DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS.equals(contractFormInfo.getContractStatus())) {
			String[] code = contractFormInfo.getContractStatus().split(",");
			contractFormInfo.setCode(Arrays.asList(code));
			page = contractFormInfoMapper.pageList(page, contractFormInfo);
		}
		if (Func.isNotBlank(contractFormInfo.getColPayType()) && !DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE.equals(contractFormInfo.getColPayType())) {
			page = contractFormInfoMapper.pageList(page, contractFormInfo);
		}
		//金额查询
		if (Func.isNotEmpty(contractFormInfo.getMinAmount()) || Func.isNotEmpty(contractFormInfo.getMaxAmount())) {
			page = contractFormInfoMapper.pageList(page, contractFormInfo);
		}
		if (!DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY.equals(contractFormInfo.getContractBigCategory())
			&& !DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS.equals(contractFormInfo.getContractStatus()) && !DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE.equals(contractFormInfo.getColPayType())
			|| Func.isNotEmpty(contractFormInfo.getMaxAmount()) || Func.isNotEmpty(contractFormInfo.getMinAmount())) {
			List<ContractFormInfoEntity> records = page.getRecords();
			List<ContractFormInfoEntity> recordList = new ArrayList<>();
			for (ContractFormInfoEntity v : records) {
				if (Func.isNotEmpty(v.getContractBigCategory())) {
					v.setAmountRatio(String.valueOf(
						v.getContractAmount().divide(BigDecimal.valueOf(
							contractFormInfoMapper.getNumAmount(v.getContractBigCategory())), 2, BigDecimal.ROUND_HALF_DOWN).multiply(
							BigDecimal.valueOf(AMOUNT_RATIO_VALUE)) + "%"));
					v.setContractBigCategory(bizClient.getValues("HTDL", Long.valueOf(v.getContractBigCategory())).getData());
				}
				if (Func.isNotEmpty(v.getColPayTerm())) {
					v.setColPayType(bizClient.getValues("col_pay_term", Long.valueOf(v.getColPayTerm())).getData());
				}
				//将签订信息存入合同分页 获取邮寄日期
				ContractSigningEntity signingEntity = signingMapper.selectSigningById(v.getId());
				if (Func.isNotEmpty(signingEntity)) {
					v.setSigningEntity(signingEntity);
				}
				String contractAmount = v.getContractAmount() + "元";
				v.setAmountVoidData(contractAmount);
				recordList.add(v);
			}
			page.setRecords(recordList);
		}
		IPage<ContractFormInfoResponseVO> pages = ContractFormInfoWrapper.build().entityPVPage(page);
		if (DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY.equals(contractFormInfo.getContractBigCategory())
			|| DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS.equals(contractFormInfo.getContractStatus())
			|| DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE.equals(contractFormInfo.getColPayType())) {
			List<ContractFormInfoResponseVO> records = pages.getRecords();
			List<ContractFormInfoResponseVO> recordList = new ArrayList<>();
			for (ContractFormInfoResponseVO v : records) {
				BigDecimal contractAmountSum = BigDecimal.valueOf(contractFormInfoMapper.selectAmountSum());
				v.setAmountRatio(v.getContractAmount().divide(contractAmountSum, 2, BigDecimal.ROUND_HALF_EVEN).multiply(BigDecimal.valueOf(AMOUNT_RATIO_VALUE)) + "%");
				if (DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY.equals(contractFormInfo.getContractBigCategory())) {
					v.setSigningCount(contractFormInfoMapper.selectSigningCount(v.getContractBigCategory()));
					v.setContractBigCategory(v.getDictValue());
				}
				if (DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE.equals(contractFormInfo.getColPayType())) {
					v.setColPayType(v.getDictValue());
				}
				//TODO 金额计算在合同起草时选择币种之后根据币种转换成人民币计算存入数据库
				String contractAmount = v.getContractAmount() + "元";
				v.setAmountVoidData(contractAmount);
				recordList.add(v);
			}
			pages.setRecords(recordList);
		}
		return pages;
	}

	/**
	 * 合同数据批量导入
	 *
	 * @return list
	 */
	@Override
	public void importContractFormInfo(List<ContractFormInfoImporter> data, MultipartFile file, String json, String contractTemplateId, String contractBigCategory, String contractSmallCategory) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		data.forEach(contractFormInfoExcel -> {
			//保存合同数据
			List<ContractFormInfoImporterEx> read2;
			ContractFormInfoEntity contractFormInfoEntity = new ContractFormInfoEntity();
			contractFormInfoEntity.setContractTemplateId(Long.valueOf(contractTemplateId));
			contractFormInfoEntity.setContractBigCategory(contractBigCategory);
			contractFormInfoEntity.setContractSmallCategory(contractSmallCategory);
			contractFormInfoEntity.setContractStatus("10");
			contractFormInfoEntity.setContractSoure("40");
			if (!"无".equals(contractFormInfoExcel.getSealName()) && !"".equals(contractFormInfoExcel.getSealName()) && contractFormInfoExcel.getSealName() != null) {
				contractFormInfoEntity.setSealName(contractFormInfoExcel.getSealName());
			}
			if (!"无".equals(contractFormInfoExcel.getDictValue()) && !"".equals(contractFormInfoExcel.getDictValue()) && contractFormInfoExcel.getDictValue() != null) {
				contractFormInfoEntity.setDictValue(contractFormInfoExcel.getDictValue());
			}
			if (!"无".equals(contractFormInfoExcel.getPersonContract()) && !"".equals(contractFormInfoExcel.getPersonContract()) && contractFormInfoExcel.getPersonContract() != null) {
				contractFormInfoEntity.setPersonContract(contractFormInfoExcel.getPersonContract());
			}
			if (!"无".equals(contractFormInfoExcel.getSealNumber()) && !"".equals(contractFormInfoExcel.getSealNumber()) && contractFormInfoExcel.getSealNumber() != null) {
				contractFormInfoEntity.setSealNumber(Integer.parseInt(contractFormInfoExcel.getSealNumber()));
			}
			if (!"无".equals(contractFormInfoExcel.getContractPeriod()) && !"".equals(contractFormInfoExcel.getContractPeriod()) && contractFormInfoExcel.getContractPeriod() != null) {
				contractFormInfoEntity.setContractPeriod(contractFormInfoExcel.getContractPeriod());
			}
			try {
				if (!"无".equals(contractFormInfoExcel.getStartingTime()) && !"".equals(contractFormInfoExcel.getStartingTime()) && contractFormInfoExcel.getStartingTime() != null) {
					Date parse = simpleDateFormat.parse(contractFormInfoExcel.getStartingTime());
					contractFormInfoEntity.setStartingTime(parse);
				}
				if (!"无".equals(contractFormInfoExcel.getEndTime()) && !"".equals(contractFormInfoExcel.getEndTime()) && contractFormInfoExcel.getEndTime() != null) {
					Date parse = simpleDateFormat.parse(contractFormInfoExcel.getEndTime());
					contractFormInfoEntity.setEndTime(parse);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (!"无".equals(contractFormInfoExcel.getColPayType()) && !"".equals(contractFormInfoExcel.getColPayType()) && contractFormInfoExcel.getColPayType() != null) {
				contractFormInfoEntity.setColPayType(contractFormInfoExcel.getColPayType());
			}
			if (!"无".equals(contractFormInfoExcel.getColPayTerm()) && !"".equals(contractFormInfoExcel.getColPayTerm()) && contractFormInfoExcel.getColPayTerm() != null) {
				contractFormInfoEntity.setColPayTerm(contractFormInfoExcel.getColPayTerm());
			}
			if (!"无".equals(contractFormInfoExcel.getContractAmount()) && !"".equals(contractFormInfoExcel.getContractAmount()) && contractFormInfoExcel.getContractAmount() != null) {
				contractFormInfoEntity.setContractAmount(new BigDecimal(contractFormInfoExcel.getContractAmount()));
			}
			if ("是".equals(contractFormInfoExcel.getExtension()) && contractFormInfoExcel.getExtension() != null) {
				contractFormInfoEntity.setExtension("2");
			} else {
				contractFormInfoEntity.setExtension("1");
			}
			if (!"无".equals(contractFormInfoExcel.getContractForm()) && !"".equals(contractFormInfoExcel.getContractForm()) && contractFormInfoExcel.getContractForm() != null) {
				contractFormInfoEntity.setContractForm(contractFormInfoExcel.getContractForm());
			}
			if (!"无".equals(contractFormInfoExcel.getCounterpartPerson()) && !"".equals(contractFormInfoExcel.getCounterpartPerson()) && contractFormInfoExcel.getCounterpartPerson() != null) {
				contractFormInfoEntity.setCounterpartPerson(contractFormInfoExcel.getCounterpartPerson());
			}
			if (!"无".equals(contractFormInfoExcel.getTelephonePerson()) && !"".equals(contractFormInfoExcel.getTelephonePerson()) && contractFormInfoExcel.getTelephonePerson() != null) {
				contractFormInfoEntity.setTelephonePerson(contractFormInfoExcel.getTelephonePerson());
			}
			if (!"无".equals(contractFormInfoExcel.getEmailPerson()) && !"".equals(contractFormInfoExcel.getEmailPerson()) && contractFormInfoExcel.getEmailPerson() != null) {
				contractFormInfoEntity.setEmailPerson(contractFormInfoExcel.getEmailPerson());
			}
			if (!"无".equals(contractFormInfoExcel.getAddressPerson()) && !"".equals(contractFormInfoExcel.getAddressPerson()) && contractFormInfoExcel.getAddressPerson() != null) {
				contractFormInfoEntity.setAddressPerson(contractFormInfoExcel.getAddressPerson());
			}

            /*if (!"无".equals(contractFormInfoExcel.getYwlMinimum()) && !"".equals(contractFormInfoExcel.getYwlMinimum()) && contractFormInfoExcel.getYwlMinimum() != null) {
                contractFormInfoEntity.setYwlMinimum(contractFormInfoExcel.getYwlMinimum());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlMailbox()) && !"".equals(contractFormInfoExcel.getYwlMailbox()) && contractFormInfoExcel.getYwlMailbox() != null) {
                contractFormInfoEntity.setYwlMailbox(contractFormInfoExcel.getYwlMailbox());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlAgreements()) && !"".equals(contractFormInfoExcel.getYwlAgreements()) && contractFormInfoExcel.getYwlAgreements() != null) {
                contractFormInfoEntity.setYwlAgreements(contractFormInfoExcel.getYwlAgreements());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlPacking()) && !"".equals(contractFormInfoExcel.getYwlPacking()) && contractFormInfoExcel.getYwlPacking() != null) {
                contractFormInfoEntity.setYwlPacking(contractFormInfoExcel.getYwlPacking());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlMode()) && !"".equals(contractFormInfoExcel.getYwlMode()) && contractFormInfoExcel.getYwlMode() != null) {
                contractFormInfoEntity.setYwlMode(contractFormInfoExcel.getYwlMode());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlAcceptance()) && !"".equals(contractFormInfoExcel.getYwlAcceptance()) && contractFormInfoExcel.getYwlAcceptance() != null) {
                contractFormInfoEntity.setYwlAcceptance(contractFormInfoExcel.getYwlAcceptance());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlPaymentMethod()) && !"".equals(contractFormInfoExcel.getYwlPaymentMethod()) && contractFormInfoExcel.getYwlPaymentMethod() != null) {
                contractFormInfoEntity.setYwlPaymentMethod(contractFormInfoExcel.getYwlPaymentMethod());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlNameBank()) && !"".equals(contractFormInfoExcel.getYwlNameBank()) && contractFormInfoExcel.getYwlNameBank() != null) {
                contractFormInfoEntity.setYwlNameBank(contractFormInfoExcel.getYwlNameBank());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlAccountNumber()) && !"".equals(contractFormInfoExcel.getYwlAccountNumber()) && contractFormInfoExcel.getYwlAccountNumber() != null) {
                contractFormInfoEntity.setYwlAccountNumber(contractFormInfoExcel.getYwlAccountNumber());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlDeliveryTimes()) && !"".equals(contractFormInfoExcel.getYwlDeliveryTimes()) && contractFormInfoExcel.getYwlDeliveryTimes() != null) {
                contractFormInfoEntity.setYwlDeliveryTimes(contractFormInfoExcel.getYwlDeliveryTimes());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlDamages()) && !"".equals(contractFormInfoExcel.getYwlDamages()) && contractFormInfoExcel.getYwlDamages() != null) {
                contractFormInfoEntity.setYwlDamages(contractFormInfoExcel.getYwlDamages());
            }
            if (!"无".equals(contractFormInfoExcel.getYwlBreachOfContract()) && !"".equals(contractFormInfoExcel.getYwlBreachOfContract()) && contractFormInfoExcel.getYwlBreachOfContract() != null) {
                contractFormInfoEntity.setYwlBreachOfContract(contractFormInfoExcel.getYwlBreachOfContract());
            }*/
			/*String jsonEx = this.templateDraft(contractFormInfoEntity,json);*/
			/*ContractFormInfoImporter contractFormInfoImporterEx = new ContractFormInfoImporter();*/
			this.save(contractFormInfoEntity);
			if (!"".equals(contractFormInfoEntity.getId()) && contractFormInfoEntity.getId() != null) {
				//在相对方contract_counterpart的表里通过相对方全称查出来对应该相对方id ，相对方id和合同id保存到 contract_counterpart_setting 表中
				if (!"".equals(contractFormInfoExcel.getCounterpartName())) {
					List<ContractCounterpartEntity> contractCounterpartEntities = contractCounterpartMapper.selectByName(contractFormInfoExcel.getCounterpartName());
					if (contractCounterpartEntities.size() > 0) {
						contractFormInfoMapper.deleteCounterpart(contractFormInfoEntity.getId());
						contractFormInfoMapper.saveCounterpart(contractFormInfoEntity.getId(), contractCounterpartEntities);
						contractFormInfoEntity.setCounterpart(contractCounterpartEntities);
						//保存到押金contract_bond表中 contract_bond中也需要保存相对方的id
						//押金是无则不存押金数据及其关系
						if (!"无".equals(contractFormInfoExcel.getIsNotBond())) {
							ContractBondEntity contractBondEntity = new ContractBondEntity();
							contractBondEntity.setIsNotBond(contractFormInfoExcel.getIsNotBond());
							if (!"无".equals(contractFormInfoExcel.getPlanPayAmount()) && !"".equals(contractFormInfoExcel.getPlanPayAmount()) && contractFormInfoExcel.getPlanPayAmount() != null) {
								if (!"有".equals(contractFormInfoExcel.getPlanPayAmount())) {
									contractBondEntity.setPlanPayAmount(new BigDecimal(0));
								} else {
									contractBondEntity.setPlanPayAmount(new BigDecimal(2));
								}
							}
							try {
								if (!"无".equals(contractFormInfoExcel.getPlanPayTime()) && !"".equals(contractFormInfoExcel.getPlanPayTime()) && contractFormInfoExcel.getPlanPayTime() != null) {
									Date parse = simpleDateFormat.parse(contractFormInfoExcel.getPlanPayTime());
									contractBondEntity.setPlanReturnTime(parse);
								}
								if (!"无".equals(contractFormInfoExcel.getPlanReturnTime()) && !"".equals(contractFormInfoExcel.getPlanReturnTime()) && contractFormInfoExcel.getPlanReturnTime() != null) {
									Date parse = simpleDateFormat.parse(contractFormInfoExcel.getPlanReturnTime());
									contractBondEntity.setPlanReturnTime(parse);
								}

							} catch (ParseException e) {
								e.printStackTrace();
							}
							contractBondEntity.setCounterpartId(contractCounterpartEntities.get(0).getId());
							contractBondMapper.insert(contractBondEntity);
							//把保证金保存到合同对象中
							List<ContractBondEntity> list = new ArrayList();
							list.add(contractBondEntity);
							contractFormInfoEntity.setContractBond(list);
							//保存后合同的id和押金的id保存到contract_bond_setting的关联表中
							List<Long> ids = new ArrayList<>();
							ids.add(contractFormInfoEntity.getId());
							contractBondMapper.saveBond(ids, contractBondEntity.getId());
						}
					}
				}
			}
			//处理关联表的数据
			ContractTemplateEntity contractTemplate = contractTemplateMapper.selectById(contractTemplateId);
			ExcelSaveUntil excelSaveUntil = new ExcelSaveUntil();
			excelSaveUntil.excelSave(contractFormInfoEntity, contractFormInfoExcel, contractTemplate, file, json);
			//String jsonEx = this.getjson(json, contractFormInfoEntity);
			//contractFormInfoEntity.setJson(jsonEx);
			//this.saveOrUpdate(contractFormInfoEntity);
		});
	}

	@Override
	public Integer selectSigningCount(String contractBigCategory) {
		return contractFormInfoMapper.selectSigningCount(contractBigCategory);
	}

	//json数据拼接
	public String getjson(String json, ContractFormInfoEntity contractFormInfo) {
		JSONArray objects = new JSONArray();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
		try {
			objects = JSONArray.parseArray(json);
			JSONObject db = (JSONObject) JSONObject.toJSON(contractFormInfo);
			for (int i = 0; i < objects.size(); i++) {
				JSONObject temp = objects.getJSONObject(i);
				String fieldName = temp.getString("fieldName");
				String componentType = temp.getString("componentType");
				if ("datePicker".equals(componentType)) {
					if (fieldName != null) {
						String dbColum = db.getString(fieldName);
						if (dbColum != null) {
							Date d = sdf.parse(dbColum);
							temp.put("fieldValue", sd.format(d));
						}
					}
				} else if ("relationList".equals(componentType)) {
					System.out.println("是的");
				} else {
					if (fieldName != null) {
						String dbColum = db.getString(fieldName);
						if (dbColum != null) {
							temp.put("fieldValue", dbColum);
						}
					}
				}
				objects.set(i, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		json = objects.toJSONString();
		List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
		ContractTemplateEntity contractTemplate = contractTemplateMapper.selectById(contractFormInfo.getContractTemplateId());
		for (TemplateFieldJsonEntity templateField : templateFieldList) {
			if (ContractFormInfoTemplateContract.CONTRACT_ID.equals(templateField.getComponentType())) {
				templateField.setFieldValue(contractFormInfo.getId().toString());
			}
			//合同分类
			if (ContractFormInfoTemplateContract.CONTRACT_BIG_CATEGORY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				if (null != jsonObj) {
					jsonObj.put("template", contractTemplate);
					jsonObj.put("first", contractFormInfo.getContractBigCategory());
					jsonObj.put("second", contractFormInfo.getContractSmallCategory());
					templateField.setSecondSelectDataObject(jsonObj);
				}
			}
			//合同分类
			if (ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				if (null != jsonObj) {
					jsonObj.put("first", contractFormInfo.getColPayType());
					jsonObj.put("second", contractFormInfo.getColPayTerm());
					jsonObj.put("days", contractFormInfo.getDays());
					templateField.setSecondSelectDataObject(jsonObj);
				}
			}
			//处理有字段验证规则数据
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_REQUORED.equals(templateField.getRequired())) {
				List<Object> objectList = JSON.parseArray(templateField.getRequiredData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setRequiredDataList(objectList);
				}
			}
			//处理小类型组件数据
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_SMALL.contains(templateField.getComponentType())) {
				List<Object> objectList = JSON.parseArray(templateField.getDicData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setDicDataList(objectList);
				}
			}
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE.contains(templateField.getComponentType())) {
				if (ContractFormInfoTemplateContract.CONTRACT_ACCORDING.equals(templateField.getRelationCode())) {
					/*保存依据信息*/
					if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getAccording()));
						templateField.setTableDataList(contractFormInfo.getAccording());
					}
				}
				//是关联列表的组件都在这里处理
				if (ContractFormInfoTemplateContract.CONTRACT_COUNTERPART.equals(templateField.getRelationCode())) {
					//字符串对象转成JSONObject
					JSONObject obj = JSON.parseObject(templateField.getTableDataObject());
					//判断相对方是否为空
					if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
						obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART, contractFormInfo.getCounterpart());
					}
					if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
						obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART, contractFormInfo.getContractBond());
					}
					templateField.setTableDataObject(JSONObject.toJSONString(obj));
					templateField.setTableDataObjectList(obj);
				}
				//*保存履约信息
				if (ContractFormInfoTemplateContract.CONTRACT_PERFORMANCE.equals(templateField.getRelationCode())) {
					if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getPerformanceList()));
						templateField.setTableDataList(contractFormInfo.getPerformanceList());
					}
				}
				//*保存履约计划收付款
				if (ContractFormInfoTemplateContract.CONTRACT_PERFORMANCE_COLPAY.equals(templateField.getRelationCode())) {
					if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getPerformanceColPayList()));
						templateField.setTableDataList(contractFormInfo.getPerformanceColPayList());
					}
				}
				//*保存履约计划收付款
				if (ContractFormInfoTemplateContract.CONTRACT_RAW_MATERIALS.equals(templateField.getRelationCode())) {
					if (CollectionUtil.isNotEmpty(contractFormInfo.getRawMaterialsList())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getRawMaterialsList()));
						templateField.setTableDataList(contractFormInfo.getRawMaterialsList());
					}
				}
			}
		}
		return toJSONString(templateFieldList);
	}


	/**
	 * 修改合同状态
	 *
	 * @param contractStatus,id
	 * @param id
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateExportStatus(String contractStatus, Long id) {
		return contractFormInfoMapper.updateExportStatus(contractStatus, id);
	}

	@Override
	public IPage<ContractFormInfoEntity> pageListSealInfo(IPage<ContractFormInfoRequestVO> page, ContractFormInfoRequestVO contractFormInfoRequestVO) {
		return baseMapper.pageListSealInfo(page, contractFormInfoRequestVO);
	}

	/**
	 * 保存相对方信息
	 *
	 * @param vo 提取合同id和相对方id
	 */
	@Override
	public void saveCounterpart(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.deleteCounterpart(vo.getId());
		contractFormInfoMapper.saveCounterpart(vo.getId(), vo.getCounterpart());
	}

	@Override
	public void saveAccording(ContractFormInfoRequestVO vo) {
		//contractFormInfoMapper.saveAccording(vo.getId(),vo.getAccording());
	}

	/**
	 * 保存合同关联用印
	 *
	 * @param vo
	 */
	@Override
	public void saveSeal(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveSeal(vo.getId(), vo.getSeal());
	}

	/**
	 * 保存合同关联评估
	 *
	 * @param vo 提取合同id和评估id
	 */
	@Override
	public void saveAssessment(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveAssessment(vo.getId(), vo.getAssessment());
	}

	/**
	 * 保存合同关联归档
	 *
	 * @param vo 获取对应的合同id和归档id
	 */
	@Override
	public void saveArchive(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveArchive(vo.getId(), vo.getArchive());
	}

	/**
	 * 保存合同关联签订
	 *
	 * @param vo
	 */
	@Override
	public void saveSigning(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveSigning(vo.getId(), vo.getSigning());
	}

	/**
	 * 根据合同id查询关联数据返回到合同vo
	 *
	 * @param id 合同id
	 * @return
	 */
	@Override
	public ContractFormInfoResponseVO getById(Long id) {
		ContractFormInfoResponseVO contractFormInfoResponseVO = new ContractFormInfoResponseVO();
		ContractFormInfoEntity contractFormInfo = contractFormInfoMapper.selectById(id);
		ContractFormInfoEntity changeFormInfoEntity = contractFormInfoMapper.selectByChangeId(id);
		//根据合同ID查询是否有变更的合同信息 如果有则判断其变更合同状态是否为草稿（10） 是则返回变更的合同信息，**编辑变更操作**
		//否则返回原合同（为送审状态返回原合同，并将变更信息返回页面VOList **禁止编辑**）
		if (Func.isNotEmpty(changeFormInfoEntity)) {
			if (CONTRACT_CHANGE_REVIEW.equals(changeFormInfoEntity.getContractStatus())) {
				contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(changeFormInfoEntity);
			} else {
				contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(contractFormInfo);
				//将变更合同的新合同信息SET到VOList中
				List<ContractFormInfoEntity> changList = new ArrayList<>();
				changList.add(changeFormInfoEntity);
				contractFormInfoResponseVO.setFormInfosEntityNewVOList(changList);
			}
		} else {
			contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(contractFormInfo);
		}
		//处理首付款类型的字典值
//		contractFormInfoResponseVO.setColPayTerm(bizClient.getById(Long.parseLong(
//			Func.isNotEmpty(changeFormInfoEntity) &&
//				CONTRACT_CHANGE_REVIEW.equals(changeFormInfoEntity.getContractStatus()) ?
//				changeFormInfoEntity.getColPayTerm() : contractFormInfo.getColPayTerm())).getData().getDictValue());
		//查询申请单位的
		if (Func.isNoneBlank(contractFormInfoResponseVO.getSealName())) {
			String[] sealNameList = contractFormInfoResponseVO.getSealName().split(",");
			contractFormInfoResponseVO.setSealNameList(sealNameList);
		}
		//查询依据
		List<ContractAccordingEntity> contractAccordingList = contractAccordingMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setAccording(contractAccordingList);
		//查询多方起草关联相对方
		List<ContractCounterpartEntity> contractCounterpartList = contractCounterpartMapper.selectByIds(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(contractCounterpartList)) {
			contractFormInfoResponseVO.setCounterpart(contractCounterpartList);
			StringBuilder name = new StringBuilder();
			for (ContractCounterpartEntity counterpartEntity : contractCounterpartList) {
				name.append(counterpartEntity.getName());
				name.append(",");
			}
			name.substring(0, name.length());
			contractFormInfoResponseVO.setCounterpartName(name.toString());
		}
		//查询多方起草关联相对方的身份信息
		List<DraftContractCounterpartEntity> dcc = iDraftContractCounterparService.selectByContractId(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(dcc)) {
			contractFormInfoResponseVO.setDraftContractCounterpartList(dcc);
		}
		//查询保证金
		List<ContractBondEntity> contractBondList = contractBondMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setContractBond(contractBondList);
		//查询履约计划清单
		List<ContractPerformanceEntity> contractPerformanceList = contractPerformanceMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setPerformanceList(contractPerformanceList);
		//查询履约计划收付款
		List<ContractPerformanceColPayEntity> contractPerformanceColPayList = contractPerformanceColPayMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setPerformanceColPayList(contractPerformanceColPayList);
		//查询解除信息
		ContractRelieveEntity relieveEntity = relieveMapper.selectRelieveById(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(relieveEntity)) {
			ContractRelieveResponseVO relieveResponseVO = ContractRelieveWrapper.build().entityPV(relieveEntity);
			if (Func.isNotEmpty(relieveEntity.getSigningBasis())) {
				relieveResponseVO.setAccordingEntity(contractAccordingMapper.selectById(relieveEntity.getSigningBasis()));
			}
			if (Func.isNotBlank(relieveEntity.getTermAgreement())) {
				//判断解除协议是否为空，不为空将查出来返回道VO
				R<List<FileVO>> result = fileClient.getByIds(relieveResponseVO.getTermAgreement());
				if (result.isSuccess()) {
					relieveResponseVO.setTermAgreementFileVOList(result.getData());
				}
			}
			contractFormInfoResponseVO.setRelieveEntity(relieveResponseVO);
		}
		//查询合同评估并保存到合同vo
		ContractAssessmentEntity contractAssessmentEntity = contractAssessmentMapper.selectByAssessmentId(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(contractAssessmentEntity)) {
			ContractAssessmentResponseVO assessmentResponseVO = ContractAssessmentWrapper.build().entityPV(contractAssessmentEntity);
			//评估相关附件
			if (!Func.isEmpty(assessmentResponseVO)) {
				if (Func.isNoneBlank(assessmentResponseVO.getAttachedFiles())) {
					R<List<FileVO>> result = fileClient.getByIds(assessmentResponseVO.getAttachedFiles());
					if (result.isSuccess()) {
						assessmentResponseVO.setAssessmentAttachedVOList(result.getData());
					}
				}
			}
			contractFormInfoResponseVO.setAssessmentEntity(assessmentResponseVO);
		}
		//查询合同未归档原因集合并保存到合同vo
		List<ContractArchiveNotEntity> archiveNotEntity = archiveNotMapper.selectArchiveNotById(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setArchiveNotEntity(archiveNotEntity);
		//查询合同归档并保存到合同vo
		ContractArchiveEntity contractArchiveEntity = contractArchiveMapper.selectArchiveById(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(contractArchiveEntity)) {
			ContractArchiveResponseVO archiveResponseVO = ContractArchiveWrapper.build().entityPV(contractArchiveEntity);
			BladeUser user = AuthUtil.getUser();
			Long userId = Long.valueOf(user.getUserId());
			Long deptId = Long.valueOf(AuthUtil.getDeptId());
			Date now = new Date();
			archiveResponseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
			archiveResponseVO.setCreateDeptName(SysCache.getDeptName(deptId));
			archiveResponseVO.setCreateSystemTime(now);
			contractFormInfoResponseVO.setArchiveEntity(archiveResponseVO);
		}
		//查询合同用印信息保存到合同vo
		if (Func.isEmpty(contractFormInfoResponseVO.getSealInfoEntity())) {
			ContractSealUsingInfoEntity sealUsingInfoEntity = sealUsingInfoMapper.selectUsingById(contractFormInfoResponseVO.getId());
			if (Func.isNotEmpty(sealUsingInfoEntity)) {
				ContractSealUsingInfoResponseVO sealUsingInfoResponseVO = ContractSealUsingInfoWrapper.build().entityPV(sealUsingInfoEntity);
				contractFormInfoResponseVO.setSealInfoEntity(sealUsingInfoResponseVO);
			}
		}
		//查询合同签订信息保存到合同vo
		ContractSigningEntity signingEntity = signingMapper.selectSigningById(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(signingEntity)) {
			ContractSigningResponseVO signingResponseVO = ContractSigningWrapper.build().entityPV(signingEntity);
			contractFormInfoResponseVO.setSigningEntity(signingResponseVO);
		}
		//查询合同文本
		if (Func.isNoneBlank(contractFormInfoResponseVO.getTextFile())) {
			R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getTextFile());
			if (result.isSuccess()) {
				contractFormInfoResponseVO.setTestFileVOList(result.getData());
			}
		}
		//查询合同文本PDF
		if (Func.isNoneBlank(contractFormInfoResponseVO.getTextFilePdf())) {
			R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getTextFilePdf());
			if (result.isSuccess()) {
				contractFormInfoResponseVO.setTestFileVOListPDF(result.getData());
			}
		}
		//查询合同附件
		if (Func.isNoneBlank(contractFormInfoResponseVO.getAttachedFiles())) {
			R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getAttachedFiles());
			if (result.isSuccess()) {
				contractFormInfoResponseVO.setAttachedFileVOList(result.getData());
			}
		}
		/* 查询创建者 */

		if (!Func.isEmpty(contractFormInfoResponseVO.getCreateUser())) {
			/*User user = UserCache.getUser(entity.getCreateUser());*/
			User user = userClient.userInfoById(contractFormInfoResponseVO.getCreateUser()).getData();
			contractFormInfoResponseVO.setUserRealName(user.getRealName());
		}
		/* 查询创建者组织 */
		if (!Func.isEmpty(contractFormInfoResponseVO.getCreateDept())) {
			String dept = sysClient.getDeptName(contractFormInfoResponseVO.getCreateDept()).getData();
			contractFormInfoResponseVO.setUserDepartName(dept);
		}

		//签订文本扫描件
		if (!Func.isEmpty(signingEntity)) {
			if (Func.isNoneBlank(signingEntity.getTextFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(signingEntity.getTextFiles());
				if (result.isSuccess()) {
					contractFormInfoResponseVO.setSigningTextFileVOList(result.getData());
				}
			}
		}
		//签订附件扫描件
		if (!Func.isEmpty(signingEntity)) {
			if (Func.isNoneBlank(signingEntity.getAttachedFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(signingEntity.getAttachedFiles());
				if (result.isSuccess()) {
					contractFormInfoResponseVO.setSigningAttachedFileVOList(result.getData());
				}
			}
		}
		//查询变更信息
		ContractChangeEntity changeEntity = changeMapper.selectById(id);
		if (Func.isNotEmpty(changeEntity)) {
			contractFormInfoResponseVO.setChangeEntity(changeEntity);
			if (Func.isNoneBlank(changeEntity.getSuppleAgreement())) {
				R<List<FileVO>> result = fileClient.getByIds(changeEntity.getSuppleAgreement());
				if (result.isSuccess()) {
					contractFormInfoResponseVO.setSuppleAgreementFileVOList(result.getData());
				}
			}
		}

		return contractFormInfoResponseVO;
	}

	/**
	 * 和同变更历史列表查询
	 *
	 * @param id
	 * @return
	 */
	@Override
	public ContractFormInfoResponseVO getByChangeHistoryId(Long id) {
		List<ContractFormInfoEntity> formInfoEntityList = new ArrayList<>();
		//根据最新版本id查询最新范本数据
		ContractFormInfoEntity formInfoEntity = contractFormInfoMapper.selectById(id);
		List<ContractCounterpartEntity> contractCounterpartList = contractCounterpartMapper.selectByIds(formInfoEntity.getId());
		if (Func.isNotEmpty(contractCounterpartList)) {
			formInfoEntity.setCounterpart(contractCounterpartList);
			StringBuilder name = new StringBuilder();
			for (ContractCounterpartEntity counterpartEntity : contractCounterpartList) {
				name.append(counterpartEntity.getName());
				name.append(",");
			}
			name.substring(0, name.length());
			formInfoEntity.setCounterpartName(name.toString());
		}
		formInfoEntityList.add(formInfoEntity);
		while (Func.isNotEmpty(formInfoEntity.getChangeContractId())) {
			formInfoEntity = contractFormInfoMapper.selectById(formInfoEntity.getChangeContractId());
			//查询多方起草关联相对方
			contractCounterpartList = contractCounterpartMapper.selectByIds(formInfoEntity.getId());
			if (Func.isNotEmpty(contractCounterpartList)) {
				formInfoEntity.setCounterpart(contractCounterpartList);
				StringBuilder name = new StringBuilder();
				for (ContractCounterpartEntity counterpartEntity : contractCounterpartList) {
					name.append(counterpartEntity.getName());
					name.append(",");
				}
				name.substring(0, name.length());
				formInfoEntity.setCounterpartName(name.toString());
			}
			formInfoEntityList.add(formInfoEntity);
		}
		//将实体数据存入vo
		ContractFormInfoResponseVO formInfoResponseVO = ContractFormInfoWrapper.build().entityPV(formInfoEntity);
		formInfoResponseVO.setFormInfosEntityOldVOList(formInfoEntityList);
		return formInfoResponseVO;
	}

	/**
	 * 统计合同下载次数
	 *
	 * @param id                 合同id
	 * @param fileExportCount    下载次数
	 * @param fileExportCategory
	 * @return 返回统计状态
	 */
	@Override
	public boolean textExportCount(Long id, Integer fileExportCount, String fileExportCategory) {
		return contractFormInfoMapper.textExportCount(id, fileExportCount, fileExportCategory);
	}


	/**
	 * 电子签章业务处理
	 *
	 * @param r 合同信息
	 * @return 状态
	 */
	@Override
	public R<ContractFormInfoEntity> SingleSign(R<ContractFormInfoEntity> r) {
		log.info("电子签章业务处理开始:{}",JsonUtil.toJson(r));
		ContractFormInfoEntity entity = r.getData();
		// 上传合同文件 开始
		// 接口是支持批量上传的
		UploadFileEntity uploadFileEntity = new UploadFileEntity();
		//查询合同正文
		File filePDF = null;
		//加编号的合同正文
		File fileBH = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = df.format(new Date());
		//独立起草的pdf处理
		if ("10".equals(entity.getContractSoure()) || "20".equals(entity.getContractSoure())) {
			List<FileVO> fileVO = fileClient.getByIds(entity.getTextFile()).getData();
			String newFileDoc = "";
			String newFilePdf = "";
			String suffix = "";
			//doc转为pdf
			if (CollectionUtil.isNotEmpty(fileVO)) {
				newFileDoc = fileVO.get(0).getLink();
				int index = fileVO.get(0).getName().lastIndexOf(".");
				suffix = fileVO.get(0).getName().substring(index + 1, fileVO.get(0).getName().length());
				//判断是否为pdf文件，pdf文件不需要转换
				if (!"pdf".equals(suffix)) {
					newFilePdf = ftlPath + fileVO.get(0).getName().substring(0, index) + date + ".pdf";
					AsposeWordToPdfUtils.doc2pdf(newFileDoc, newFilePdf);
					filePDF = new File(newFilePdf);
				} else {
					filePDF = new File(ftlPath + fileVO.get(0).getName().substring(0, index) + date + ".pdf");
					//建立输出字节流
					FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(filePDF);
						fos.write(AsposeWordToPdfUtils.getUrlFileData(newFileDoc));
						fos.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		} else {
			filePDF = new File(entity.getFilePDF());
		}
		InputStream in = null;
		try {
			in = new FileInputStream(filePDF);
			String fileId = AsposeWordToPdfUtils.addWaterMak(in, "统一集团", filePDF.getName(), null);
			Thread.sleep(2000);
			String url = AsposeWordToPdfUtils.downloadFile(fileId);
			System.out.println(url);
			//建立输出字节流
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(filePDF);
				fos.write(AsposeWordToPdfUtils.getUrlFileData(url));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//断言 不能为null
			assert fos != null;
			fos.close();
			in.close();
			//处理编号
			List<ContractFormInfoEntity> list = this.selectByContractNumber(entity);
			log.info("开始处理编号:{}",JsonUtil.toJson(list));
			//合同大类
			final String[] FLCode = {null};
			R<List<DictBiz>> HTDL = bizClient.getList("HTDL");
			List<DictBiz> dataBiz = HTDL.getData();
			dataBiz.forEach(bz -> {
				if ((bz.getId().toString()).equals(entity.getContractBigCategory())) {
					FLCode[0] = bz.getRemark();
				}
			});
			log.info("合同大类:{}",JsonUtil.toJson(FLCode));
			//合同用印全称编号
			final String[] GSCode = {null};
			R<List<DictBiz>> seal = bizClient.getList("application_seal");
			log.info("获取到application_seal:{}",JsonUtil.toJson(seal));
			seal.getData().forEach(bz -> {
				if (bz.getDictValue().equals(entity.getSealName())) {
					GSCode[0] = bz.getRemark();
				}
			});
			log.info("合同用印全称编号:{}",JsonUtil.toJson(GSCode));
			//存在合同编号按顺序+1
			log.info("存在合同编号:{}",list.size());
			if (list.size() > 0) {
				entity.setContractNumber(redisCacheUtil.selectTaskNo(list.get(0).getContractNumber(), FLCode[0], GSCode[0]));
			} else {
				entity.setContractNumber(redisCacheUtil.selectTaskNo("", FLCode[0], GSCode[0]));
			}
			String BH = ftlPath + "BH-" + filePDF.getName();
			AsposeWordToPdfUtils.addWaterMark(filePDF.getPath(), BH, entity.getContractNumber());
			fileBH = new File(BH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*R<FileVO> filePDFVO = null;
		try {
			MultipartFile multipartFile = new MockMultipartFile("file", filePDF.getName(),
				ContentType.MULTIPART.toString(), new FileInputStream(fileBH));
			filePDFVO = fileClient.save(multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*//* 上传文件 *//*
		assert filePDFVO != null;
		entity.setTextFilePdf(filePDFVO.getData().getId() + ",");*/
		// 入参是个file文件
		List<File> files = new ArrayList<File>();
		files.add(fileBH);
		uploadFileEntity.setFile(files);
		// 默认是不合并,该上传几个文件就几个文件
		uploadFileEntity.setIsMerge("0");
		// 调用上传方法 另外需要注意 接口里自动进行了获取token的动作, 现在的获取地址和账号密码都是测试的,正式使用需要修改这些内容,修改位置在blade-abutment的resources.application里
		List<UploadFileVo> uploadFileVoList = abutmentClient.uploadFiles(uploadFileEntity).getData();
		//上传合同文件 结束
		//epk流程接口
		entity.setTextFilePdf(uploadFileVoList.get(0).getId());
		MultipartFile multipartFile = null;
		try {
			multipartFile = new MockMultipartFile("file", fileBH.getName(),
				ContentType.MULTIPART.toString(), new FileInputStream(fileBH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* 上传文件 */
		R<FileVO> fileVO = fileClient.save(multipartFile);
		entity.setOtherInformation(fileVO.getData().getLink());
		R<EkpVo> ekpVo = abutmentClient.sendEkpFormPost(entity);
		log.info("ekp调用结果:{}",JsonUtil.toJson(ekpVo));
		if (ekpVo.getCode() == HttpStatus.OK.value()) {
			entity.setRelContractId(ekpVo.getData().getDoc_info());
		} else {
			r.setMsg(ekpVo.getMsg());
			r.setSuccess(false);
			return R.data(2,null,"EKP推送数据超时，操作失败");
		}
		log.info("ekp返回的code:{}",ekpVo.getCode());
		log.info("ekp返回的依据ID:{}",ekpVo.getData().getDoc_info());
		log.info("ekp原依据ID:{}",entity.getRelContractId());
		/*R<EkpVo> ekpVo=new R<EkpVo>();
		ekpVo.setCode(0);
		entity.setRelContractId("123");*/
		r.setData(entity);
		r.setCode(ekpVo.getCode());
		return r;
	}



	/**
	 * 范本起草保存
	 *
	 * @param json 合同模板
	 */
	@Override
	public ContractFormInfoEntity templateDraft(ContractFormInfoEntity contractFormInfo, String json) {
		//把Json对象转成对象
		List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
		for (TemplateFieldJsonEntity templateField : templateFieldList) {
            /*if (ContractFormInfoTemplateContract.CONTRACT_ID.equals(templateField.getComponentType())) {
                templateField.setFieldValue(contractFormInfo.getId().toString());
            }*/
			//处理二级联动组件类型的数据
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_SELECT.equals(templateField.getComponentType())) {
				Object object = JSON.parseObject(templateField.getSecondSelectData(), Object.class);
				if (null != object) {
					templateField.setSecondSelectDataObject(object);
				}
			}
			//处理有字段验证规则数据
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_REQUORED.equals(templateField.getRequired())) {
				List<Object> objectList = JSON.parseArray(templateField.getRequiredData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setRequiredDataList(objectList);
				}
			}
			//处理小类型组件数据
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_SMALL.contains(templateField.getComponentType())) {
				List<Object> objectList = JSON.parseArray(templateField.getDicData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setDicDataList(objectList);
				}
			}
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE.contains(templateField.getComponentType())) {
                /*if (ContractFormInfoTemplateContract.CONTRACT_ACCORDING.equals(templateField.getRelationCode())) {
                    List<ContractAccordingEntity> accordingList = JSON.parseArray(templateField.getTableData(), ContractAccordingEntity.class);
                    *//*保存依据信息*//*
                    if (CollectionUtil.isNotEmpty(accordingList)) {
                        ContractAccordingEntity contractAccording = accordingList.get(0);
                        contractAccording.setContractId(contractFormInfo.getId());
                        //contractAccordingMapper.insert(contractAccording);
                        accordingList.get(0).setId(contractAccording.getId());
                        templateField.setTableData(JSONObject.toJSONString(accordingList));
                        templateField.setTableDataList(accordingList);
                    }
					contractFormInfo.setAccording(accordingList);
                }
                //是关联列表的组件都在这里处理
                if (ContractFormInfoTemplateContract.CONTRACT_COUNTERPART.equals(templateField.getRelationCode())) {
                    //字符串对象转成JSONObject
                    JSONObject obj = JSON.parseObject(templateField.getTableDataObject());
                    //判断相对方是否为空
                    if (!"{}".equals(obj.getString(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART))) {
                        com.alibaba.fastjson.JSONArray counterpartObject = obj.getJSONArray(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART);
                        List<ContractCounterpartEntity> contractCounterpart = JSON.parseArray(counterpartObject.toString(), ContractCounterpartEntity.class);
                        if (contractCounterpart.size() > 0) {
                            contractFormInfoMapper.deleteCounterpart(contractFormInfo.getId());
                            contractFormInfoMapper.saveCounterpart(contractFormInfo.getId(), contractCounterpart);
                            obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART, contractCounterpart);
                        }
						contractFormInfo.setCounterpart(contractCounterpart);
                        //判断保证金是否为空
                        if (!"{}".equals(obj.getString(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_CONTRACTBOND))) {
                            com.alibaba.fastjson.JSONArray contractBondArry = obj.getJSONArray(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_CONTRACTBOND);
                            List<ContractBondEntity> contractBond = JSON.parseArray(contractBondArry.toString(), ContractBondEntity.class);
							contractFormInfo.setContractBond(contractBond);
                            *//*保存保证金信息*//*
                            if (CollectionUtil.isNotEmpty(contractBond)) {
                                List<Long> list = new ArrayList<>();
                                List<ContractBondEntity> bondList = new ArrayList<>();
                                ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
                                //删除保证金库脏数据
                                contractBondService.deleteByContractId(contractFormInfo.getId());
                                //删除保证金履约计划脏数据
                                contractBondPlanService.deleteByContractId(contractFormInfo.getId());
                                for (ContractBondEntity contractBondEntity : contractBond) {
                                    BeanUtil.copy(contractBondEntity, contractBondPlan);
                                    if (Func.isEmpty(contractBondEntity.getId())) {
                                        contractBondService.save(contractBondEntity);
                                    }
                                    bondList.add(contractBondEntity);
                                    //保存保证金履约计划
                                    contractBondPlan.setContractId(contractFormInfo.getId());
                                    contractBondPlan.setId(null);
                                    contractBondPlanService.save(contractBondPlan);
                                    list.add(contractBondEntity.getId());
                                }
                                contractBondService.saveBond(list, contractFormInfo.getId());
                                obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_CONTRACTBOND, bondList);
                            }
                        }
                        templateField.setTableDataObject(JSONObject.toJSONString(obj));
                        templateField.setTableDataObjectList(obj);
                    }
                }
                //*保存履约信息
                if (ContractFormInfoTemplateContract.CONTRACT_PERFORMANCE.equals(templateField.getRelationCode())) {
                    List<ContractPerformanceEntity> performanceList = JSON.parseArray(templateField.getTableData(), ContractPerformanceEntity.class);
                    if (CollectionUtil.isNotEmpty(performanceList)) {
                        performanceMapper.deleteByContractId(contractFormInfo.getId());
                        List<ContractPerformanceEntity> list = new ArrayList<>();
                        performanceList.forEach(performance -> {
                            performance.setContractId(contractFormInfo.getId());
                            contractPerformanceMapper.insert(performance);
                            list.add(performance);
                        });
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*保存履约计划收付款
                if (ContractFormInfoTemplateContract.CONTRACT_PERFORMANCE_COLPAY.equals(templateField.getRelationCode())) {
                    List<ContractPerformanceColPayEntity> performanceColPayList = JSON.parseArray(templateField.getTableData(), ContractPerformanceColPayEntity.class);
                    if (CollectionUtil.isNotEmpty(performanceColPayList)) {
                        performanceColPayMapper.deleteByContractId(contractFormInfo.getId());
                        List<ContractPerformanceColPayEntity> list = new ArrayList<>();
                        performanceColPayList.forEach(performanceColPay -> {
                            performanceColPay.setContractId(contractFormInfo.getId());
                            contractPerformanceColPayMapper.insert(performanceColPay);
                            list.add(performanceColPay);
                        });
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }*/
				//*新陈列协议书关联表
				if (ContractFormInfoTemplateContract.CONTRACT_YWLANEWDISPLAY1.equals(templateField.getRelationCode())) {
					List<YwlANewDisplay1ResponseVO> ywlANewDisplay1List = JSON.parseArray(templateField.getTableData(), YwlANewDisplay1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(ywlANewDisplay1List)) {
						ywlANewDisplay1Service.saveBatchByRefId(contractFormInfo.getId(), ywlANewDisplay1List);
						List<YwlANewDisplay1ResponseVO> list = ywlANewDisplay1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*买卖合同（行销品）关联表
				if (ContractFormInfoTemplateContract.CONTRACT_CGLCATEGORYSALESCONTRACTS1.equals(templateField.getRelationCode())) {
					List<CglCategorySalesContracts1ResponseVO> cglCategorySalesContracts1List = JSON.parseArray(templateField.getTableData(), CglCategorySalesContracts1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglCategorySalesContracts1List)) {
						cglCategorySalesContracts1Service.saveBatchByRefId(contractFormInfo.getId(), cglCategorySalesContracts1List);
						List<CglCategorySalesContracts1ResponseVO> list = cglCategorySalesContracts1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*新增原物料补充协议--买卖合同关联表
				if (ContractFormInfoTemplateContract.CONTRACT_CGLTHESALESCONTRACT1.equals(templateField.getRelationCode())) {
					List<CglTheSalesContract1ResponseVO> cglTheSalesContract1List = JSON.parseArray(templateField.getTableData(), CglTheSalesContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglTheSalesContract1List)) {
						cglTheSalesContract1Service.saveBatchByRefId(contractFormInfo.getId(), cglTheSalesContract1List);
						List<CglTheSalesContract1ResponseVO> list = cglTheSalesContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*原物料-买卖合同联表
				if (ContractFormInfoTemplateContract.CONTRACT_CGLRAWMATERIALS1.equals(templateField.getRelationCode())) {
					List<CglRawMaterials1ResponseVO> cglRawMaterials1List = JSON.parseArray(templateField.getTableData(), CglRawMaterials1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglRawMaterials1List)) {
						cglRawMaterials1Service.saveBatchByRefId(contractFormInfo.getId(), cglRawMaterials1List);
						List<CglRawMaterials1ResponseVO> list = cglRawMaterials1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*原物料-买卖合同联表
				if (ContractFormInfoTemplateContract.CONTRACT_CGLRAWMATERIALS1.equals(templateField.getRelationCode())) {
					List<SclEquipmentMaintenance1ResponseVO> sclEquipmentMaintenance1List = JSON.parseArray(templateField.getTableData(), SclEquipmentMaintenance1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclEquipmentMaintenance1List)) {
						sclEquipmentMaintenance1Service.saveBatchByRefId(contractFormInfo.getId(), sclEquipmentMaintenance1List);
						List<SclEquipmentMaintenance1ResponseVO> list = sclEquipmentMaintenance1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告改编合同 广告完成形式关联表
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlAdaptationContract1ResponseVO> mtlAdaptationContract1List = JSON.parseArray(templateField.getTableData(), MtlAdaptationContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAdaptationContract1List)) {
						mtlAdaptationContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlAdaptationContract1List);
						List<MtlAdaptationContract1ResponseVO> list = mtlAdaptationContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告改编合同 交付数量关联表
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlAdaptationContract2ResponseVO> mtlAdaptationContract1List = JSON.parseArray(templateField.getTableData(), MtlAdaptationContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAdaptationContract1List)) {
						mtlAdaptationContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlAdaptationContract1List);
						List<MtlAdaptationContract2ResponseVO> list = mtlAdaptationContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告拍摄制作合同 广告完成形式关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlShootingAndProductionContract1ResponseVO> mtlShootingAndProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlShootingAndProductionContract1List)) {
						mtlShootingAndProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlShootingAndProductionContract1List);
						List<MtlShootingAndProductionContract1ResponseVO> list = mtlShootingAndProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告拍摄制作合同 广告完成形式关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlShootingAndProductionContract2ResponseVO> mtlShootingAndProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlShootingAndProductionContract2List)) {
						mtlShootingAndProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlShootingAndProductionContract2List);
						List<MtlShootingAndProductionContract2ResponseVO> list = mtlShootingAndProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告拍摄制作合同 广告完成形式关联表3
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT3.equals(templateField.getRelationCode())) {
					List<MtlShootingAndProductionContract3ResponseVO> mtlShootingAndProductionContract3List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract3ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlShootingAndProductionContract3List)) {
						mtlShootingAndProductionContract3Service.saveBatchByRefId(contractFormInfo.getId(), mtlShootingAndProductionContract3List);
						List<MtlShootingAndProductionContract3ResponseVO> list = mtlShootingAndProductionContract3Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*生产项目外包服务合同 关联表
				if (ContractFormInfoTemplateContract.CONTRACT_SCLPROJECTOUTSOURCING1.equals(templateField.getRelationCode())) {
					List<SclProjectOutsourcing1ResponseVO> sclProsjectOutsourcing1List = JSON.parseArray(templateField.getTableData(), SclProjectOutsourcing1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclProsjectOutsourcing1List)) {
						sclProjectOutsourcing1Service.saveBatchByRefId(contractFormInfo.getId(), sclProsjectOutsourcing1List);
						List<SclProjectOutsourcing1ResponseVO> list = sclProjectOutsourcing1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*加工承揽合同（代工合同）关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT1.equals(templateField.getRelationCode())) {
					List<SclConstructionProject1ResponseVO> sclConstructionProject1List = JSON.parseArray(templateField.getTableData(), SclConstructionProject1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclConstructionProject1List)) {
						sclConstructionProject1Service.saveBatchByRefId(contractFormInfo.getId(), sclConstructionProject1List);
						List<SclConstructionProject1ResponseVO> list = sclConstructionProject1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*加工承揽合同（代工合同）关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT2.equals(templateField.getRelationCode())) {
					List<SclConstructionProject2ResponseVO> sclConstructionProject2List = JSON.parseArray(templateField.getTableData(), SclConstructionProject2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclConstructionProject2List)) {
						sclConstructionProject2Service.saveBatchByRefId(contractFormInfo.getId(), sclConstructionProject2List);
						List<SclConstructionProject2ResponseVO> list = sclConstructionProject2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*加工承揽合同（代工合同）关联表3
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT3.equals(templateField.getRelationCode())) {
					List<SclConstructionProject3ResponseVO> sclConstructionProject3List = JSON.parseArray(templateField.getTableData(), SclConstructionProject3ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclConstructionProject3List)) {
						sclConstructionProject3Service.saveBatchByRefId(contractFormInfo.getId(), sclConstructionProject3List);
						List<SclConstructionProject3ResponseVO> list = sclConstructionProject3Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*音频制作合同 关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlAudioProductionContract1ResponseVO> mtlAudioProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlAudioProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAudioProductionContract1List)) {
						mtlAudioProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlAudioProductionContract1List);
						List<MtlAudioProductionContract1ResponseVO> list = mtlAudioProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*音频制作合同 关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlAudioProductionContract2ResponseVO> mtlAudioProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlAudioProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAudioProductionContract2List)) {
						mtlAudioProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlAudioProductionContract2List);
						List<MtlAudioProductionContract2ResponseVO> list = mtlAudioProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*修图合同关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLEDITEDTHECONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlEditedTheContract1ResponseVO> mtlEditedTheContract1List = JSON.parseArray(templateField.getTableData(), MtlEditedTheContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlEditedTheContract1List)) {
						mtlEditedTheContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlEditedTheContract1List);
						List<MtlEditedTheContract1ResponseVO> list = mtlEditedTheContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频制作合同关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlVideoProductionContract1ResponseVO> mtlVideoProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlVideoProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract1List)) {
						mtlVideoProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract1List);
						List<MtlVideoProductionContract1ResponseVO> list = mtlVideoProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频制作合同关联表2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlVideoProductionContract2ResponseVO> mtlVideoProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlVideoProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract2List)) {
						mtlVideoProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract2List);
						List<MtlVideoProductionContract2ResponseVO> list = mtlVideoProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*买卖合同（国内设备购买）关联表1
				if (ContractFormInfoTemplateContract.CONTRACT_CGLSALESCONTRACT1ENTITY.equals(templateField.getRelationCode())) {
					List<CglSalesContract1ResponseVO> cglSalesContract1List = JSON.parseArray(templateField.getTableData(), CglSalesContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglSalesContract1List)) {
						cglSalesContract1Service.saveBatchByRefId(contractFormInfo.getId(), cglSalesContract1List);
						List<CglSalesContract1ResponseVO> list = cglSalesContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*平面广告拍摄制作合同（关联表1）
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtbProductionContract1ResponseVO> mtlVideoProductionContract1List = JSON.parseArray(templateField.getTableData(), MtbProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract1List)) {
						mtbProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract1List);
						List<MtbProductionContract1ResponseVO> list = mtbProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*平面广告拍摄制作合同（关联表2）
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtbProductionContract2ResponseVO> mtlVideoProductionContract2List = JSON.parseArray(templateField.getTableData(), MtbProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract2List)) {
						mtbProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract2List);
						List<MtbProductionContract2ResponseVO> list = mtbProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*平面广告拍摄制作合同（关联表3）
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT3.equals(templateField.getRelationCode())) {
					List<MtbProductionContract3ResponseVO> mtlVideoProductionContract3List = JSON.parseArray(templateField.getTableData(), MtbProductionContract3ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract3List)) {
						mtbProductionContract3Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract3List);
						List<MtbProductionContract3ResponseVO> list = mtbProductionContract3Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*设备维修保养合同(关联表1）
				if (ContractFormInfoTemplateContract.CONTRACT_SCLEQUIOMENTMAINTENANCE1.equals(templateField.getRelationCode())) {
					List<SclEquipmentMaintenance1ResponseVO> sclEquipmentMaintenance1List = JSON.parseArray(templateField.getTableData(), SclEquipmentMaintenance1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclEquipmentMaintenance1List)) {
						sclEquipmentMaintenance1Service.saveBatchByRefId(contractFormInfo.getId(), sclEquipmentMaintenance1List);
						List<SclEquipmentMaintenance1ResponseVO> list = sclEquipmentMaintenance1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*采购类_打样合同书(关联表1）
				if (ContractFormInfoTemplateContract.CONTRACT_CGLPROOFINGCONTRACT1.equals(templateField.getRelationCode())) {
					List<CglProofingContract1ResponseVO> CglProofingContract1List = JSON.parseArray(templateField.getTableData(), CglProofingContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(CglProofingContract1List)) {
						cglProofingContract1Service.saveBatchByRefId(contractFormInfo.getId(), CglProofingContract1List);
						List<CglProofingContract1ResponseVO> list = cglProofingContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*生产项目外包服务合同(关联表1）
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT1.equals(templateField.getRelationCode())) {
					List<ProductOutServiceContract1ResponseVO> productOutServiceContract1ResponseVOList = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(productOutServiceContract1ResponseVOList)) {
						productOutServiceContract1Service.saveBatchByRefId(contractFormInfo.getId(), productOutServiceContract1ResponseVOList);
						List<ProductOutServiceContract1ResponseVO> list = productOutServiceContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*生产项目外包服务合同(关联表2）
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT2.equals(templateField.getRelationCode())) {
					List<ProductOutServiceContract2ResponseVO> productOutServiceContract2ResponseVOList = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(productOutServiceContract2ResponseVOList)) {
						productOutServiceContract2Service.saveBatchByRefId(contractFormInfo.getId(), productOutServiceContract2ResponseVOList);
						List<ProductOutServiceContract2ResponseVO> list = productOutServiceContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*生产项目外包服务合同(关联表3）
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT3.equals(templateField.getRelationCode())) {
					List<ProductOutServiceContract3ResponseVO> productOutServiceContract3ResponseVOList = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract3ResponseVO.class);
					if (CollectionUtil.isNotEmpty(productOutServiceContract3ResponseVOList)) {
						productOutServiceContract3Service.saveBatchByRefId(contractFormInfo.getId(), productOutServiceContract3ResponseVOList);
						List<ProductOutServiceContract3ResponseVO> list = productOutServiceContract3Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
//                //*班车服务合同(关联表1）
//                if (ContractFormInfoTemplateContract.CONTRACT_BUSSERVICECONTRACT1.equals(templateField.getRelationCode())) {
//                    List<BusServiceContract1ResponseVO> busServiceContract1ResponseVOList = JSON.parseArray(templateField.getTableData(), BusServiceContract1ResponseVO.class);
//                    if (CollectionUtil.isNotEmpty(busServiceContract1ResponseVOList)) {
//                        busServiceContract1Service.saveBatchByRefId(contractFormInfo.getId(), busServiceContract1ResponseVOList);
//                        List<BusServiceContract1ResponseVO> list = busServiceContract1Service.selectRefList(contractFormInfo.getId());
//                        templateField.setTableData(JSONObject.toJSONString(list));
//                        templateField.setTableDataList(list);
//                    }
//                }
				//*市调合同（定性+定量）(关联表1）
				if (ContractFormInfoTemplateContract.CONTRAT_IMTBMARKETRESEARCHCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtbMarketResearchContract1ResponseVO> mtbMarketResearchContract1ResponseVOList = JSON.parseArray(templateField.getTableData(), MtbMarketResearchContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtbMarketResearchContract1ResponseVOList)) {
						iMtbMarketResearchContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtbMarketResearchContract1ResponseVOList);
						List<MtbMarketResearchContract1ResponseVO> list = iMtbMarketResearchContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
			}
		}
		contractFormInfo.setJson(toJSONString(templateFieldList));
		return contractFormInfo;
	}

	//替换所有组件里的数据
	public String toJSONString(List<TemplateFieldJsonEntity> templateFieldList) {
		//把数组在转回json字符串
		String json = JSON.toJSONString(templateFieldList);
		//再转成json数组替换里面的数据
		com.alibaba.fastjson.JSONArray jsonArr = JSON.parseArray(json);
		JSONArray jsonArray = new JSONArray();
		JSONArray array = new JSONArray();
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jsonObject2 = jsonArr.getJSONObject(i);
			if (null != (jsonObject2.get("secondSelectDataObject"))) {
				jsonObject2.put("secondSelectData", jsonObject2.get("secondSelectDataObject"));
				jsonObject2.remove("secondSelectDataObject");
			}
			if (null != (jsonObject2.get("tableDataList"))) {
				jsonObject2.put("tableData", jsonObject2.get("tableDataList"));
				jsonObject2.remove("tableDataList");
			}
			if (null != (jsonObject2.get("requiredDataList"))) {
				jsonObject2.put("requiredData", jsonObject2.get("requiredDataList"));
				jsonObject2.remove("requiredDataList");
			}
			if (null != (jsonObject2.get("dicDataList"))) {
				jsonObject2.put("dicData", jsonObject2.get("dicDataList"));
				jsonObject2.remove("dicDataList");
			}
			if (null != (jsonObject2.get("tableDataObjectList"))) {
				jsonObject2.put("tableDataObject", jsonObject2.get("tableDataObjectList"));
				jsonObject2.remove("tableDataObjectList");
			}
			if ("[]".equals(jsonObject2.get("tableData"))) {
				jsonObject2.put("tableData", array);
			}
			//处理下拉多选的值
			if ("selectMany".equals(jsonObject2.get("componentType")) && null != (jsonObject2.get("fieldValue"))) {
				String selectManyString = jsonObject2.get("fieldValue").toString();
				String[] selectManyAry = selectManyString.substring(1, selectManyString.length() - 1).replaceAll("\"", "").split(",");
				jsonObject2.put("fieldValue", selectManyAry);
			}
			jsonArray.add(jsonObject2);
		}
		json = jsonArray.toJSONString();
		return json;
	}

	/**
	 * 合同大类金额
	 *
	 * @return list
	 */
	@Override
	public List<ContractFormInfoEntity> getAmountList() {
		return contractFormInfoMapper.getAmountList();
	}

	/**
	 * 合同大类数量
	 *
	 * @return list
	 */
	@Override
	public List<ContractFormInfoEntity> getNumList() {
		return contractFormInfoMapper.getNumList();
	}

	/**
	 * 動態返回合同選擇的下拉選
	 *
	 * @return list
	 */
	@Override
	public List<ContractFormInfoEntity> getChooseList() {
		return contractFormInfoMapper.getChooseList();
	}

	/**
	 * 查询有编号的合同
	 *
	 * @return list
	 */
	@Override
	public List<ContractFormInfoEntity> selectByContractNumber(ContractFormInfoEntity entity) {
		return contractFormInfoMapper.selectByContractNumber(entity);
	}


	/**
	 * 判断电子签章
	 *
	 * @return list
	 */
	@Override
	public R singleSignIsNot(ContractFormInfoRequestVO contractFormInfo, FileVO fileVO) {
		for (ContractCounterpartEntity counterpart : contractFormInfo.getCounterpart()) {
			// 查查公司有没有申请电子章
			CompanyInfoEntity companyInfoEntity = new CompanyInfoEntity();
			companyInfoEntity.setQueryType("1");
			// 企业信用代码  从相对方里面来  需要修改
			companyInfoEntity.setOrganCode(counterpart.getUnifiedSocialCreditCode());
			// 如果是null的话,说明根本没注册,如果注册了那available是1的话表示有章,0是没章
			CompanyInfoVo companyInfoVo = abutmentClient.queryCompanyInfo(companyInfoEntity).getData();
			if (companyInfoVo == null) {
				companyInfoVo = abutmentClient.queryCompanyInfo(companyInfoEntity).getData();
			}
			//不等于0就是没有电子签章
			if ((!"0".equals(companyInfoVo.getOrganCode())) && ("1".equals(contractFormInfo.getContractForm()))) {
				return R.data(1, counterpart.getName(), counterpart.getName() + "没有电子签章，请选择实体用印");
			}
			if (("0".equals(companyInfoVo.getOrganCode())) && ("2".equals(contractFormInfo.getContractForm()))) {
				return R.data(1, counterpart.getName(), counterpart.getName() + "，有电子签章，请选择电子合同-我司平台");
			}
		}
		String newFileDoc = "";
		String newFilePdf = "";
		String suffix = "";
		File filePDF = null;
		//doc转为pdf
		if (!Func.isEmpty(fileVO)) {
			newFileDoc = fileVO.getLink();
			int index = fileVO.getName().lastIndexOf(".");
			suffix = fileVO.getName().substring(index + 1, fileVO.getName().length());
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = df.format(new Date());
			//判断是否为pdf文件，pdf文件不需要转换
			if (!"pdf".equals(suffix)) {
				//设置日期格式

				newFilePdf = ftlPath + fileVO.getName().substring(0, index) + date + ".pdf";
				AsposeWordToPdfUtils.doc2pdf(newFileDoc, newFilePdf);
				filePDF = new File(newFilePdf);
			} else {
				filePDF = new File(ftlPath + fileVO.getName().substring(0, index) + date + ".pdf");
				//建立输出字节流
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(filePDF);
					fos.write(AsposeWordToPdfUtils.getUrlFileData(newFileDoc));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (fos != null) {
							fos.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			InputStream input = null;
			PDDocument document = null;
			try {
				input = new FileInputStream(filePDF);
				// 新建一个PDF解析器对象
				PDFParser parser = new PDFParser(input);
				parser.parse();
				document = parser.getPDDocument();
				PDFTextStripper stripper = new PDFTextStripper();
				String text = stripper.getText(document);
				int az = text.indexOf("甲方（签章）");
				int bz = text.indexOf("乙方（签章）");
				int ay = text.indexOf("甲方(签章)");
				int by = text.indexOf("乙方(签章)");
				int cz = text.indexOf("甲方(签章）");
				int cy = text.indexOf("乙方(签章）");
				int dz = text.indexOf("甲方（签章)");
				int dy = text.indexOf("乙方（签章)");
				if ((-1 == az || -1 == bz) && (-1 == ay || -1 == by) && (-1 == cz || -1 == cy) && (-1 == dz || -1 == dy) &&
					(-1 == az || -1 == by) && (-1 == az || -1 == cy) && (-1 == az || -1 == dy) &&
					(-1 == bz || -1 == ay) && (-1 == bz || -1 == cz) && (-1 == bz || -1 == dz) &&
					(-1 == ay || -1 == cy) && (-1 == ay || -1 == dy) &&
					(-1 == by || -1 == cz) && (-1 == by || -1 == dz) &&
					(-1 == cz || -1 == dy) && (-1 == cy || -1 == dz)) {
					return R.data(2, "缺失签章关键字", "缺失签章关键字");
				}
				if ((-1 != cz && -1 != cy) || (-1 != dz && -1 != dy) ||
					(-1 != az && -1 != by) || (-1 != az && -1 != cy) || (-1 != az && -1 != dy) ||
					(-1 != bz && -1 != ay) || (-1 != bz && -1 != cz) || (-1 != bz && -1 != dz) ||
					(-1 != ay && -1 != cy) || (-1 != ay && -1 != dy) ||
					(-1 != by && -1 != cz) || (-1 != by && -1 != dz) ||
					(-1 != cz && -1 != dy) && (-1 != cy && -1 != dz)) {
					return R.data(2, "关键字" + "()" + "存在全角半角字符,请检查修改统一", "关键字" + "()" + "存在全角半角字符,请检查修改统一");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (document != null) {
						document.close();
					}
					if (input != null) {
						input.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return R.data(0, "成功", "成功");
		} else {
			return R.data(2, "缺失签章关键字", "缺失签章关键字");
		}
	}

	@Override
	public ContractFormInfoEntity selectByChangeId(Long id) {
		return contractFormInfoMapper.selectByChangeId(id);
	}
}
