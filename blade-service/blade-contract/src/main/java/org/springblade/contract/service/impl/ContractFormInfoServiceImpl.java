package org.springblade.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import feign.form.ContentType;
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
import org.springblade.contract.dto.middleground.*;
import org.springblade.contract.entity.*;
import org.springblade.contract.enums.ContractStatusEnum;
import org.springblade.contract.enums.ContractTypeEnum;
import org.springblade.contract.excel.ContractFormInfoImporter;
import org.springblade.contract.excel.ContractFormInfoImporterEx;
import org.springblade.contract.excel.importbatchdraft.ContractImportBatchDraftExcel;
import org.springblade.contract.mapper.*;
import org.springblade.contract.service.*;
import org.springblade.contract.util.AsposeWordToPdfUtils;
import org.springblade.contract.util.ExcelSaveUntil;
import org.springblade.contract.util.RedisCacheUtil;
import org.springblade.contract.util.TemplateExportUntil;
import org.springblade.contract.vo.*;
import org.springblade.contract.wrapper.*;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.cache.SysCache;
import org.springblade.system.entity.*;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
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
 * ???????????????
 *
 * @author ?????????//
 * @date : 2020-09-23 18:04:38
 */
@Log4j2
@Service
public class ContractFormInfoServiceImpl extends BaseServiceImpl<ContractFormInfoMapper, ContractFormInfoEntity> implements IContractFormInfoService {
	@Value("${api.file.ftlPath}")
	private String ftlPath;
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
	private IContractBondPlanService contractBondPlanService;
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
	@Autowired
	private IContractTemplateService templateService;
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
	private ICglLowCostHardware1Service iCglLowCostHardware1Service;
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
	private IContractMmhtxxpf1Service iContractMmhtxxpf1Service;
	@Autowired
	private IContractWbht1Service iContractWbht1Service;
	@Autowired
	private IDraftContractCounterparService iDraftContractCounterparService;
	@Autowired
	private ContractMultPaymenMapper multPaymenMapper;
	@Autowired
	private IContractMultPaymenService contractMultPaymenService;
	@Autowired
	private IDraftContractCounterparService draftContractCounterparService;
	@Autowired
	private DraftContractCounterpartMapper draftContractCounterpartMapper;
	/* ??????service */
	@Autowired
	private ICollectionService iCollectionService;
	@Autowired
	private IPerServiceContentService perServiceContentService;
	@Autowired
	private IContractAccordingService accordingService;
	@Autowired
	private IPerCollectPayService perCollectPayService;
	@Autowired
	private IBusServiceContract1Service busServiceContract1Service;
	@Autowired
	private IContractFileDownloadLogService fileDownloadLogService;
	@Autowired
	private ContractSealMapper contractSealMapper;
	@Autowired
	private IContractBondService contractBondService;
	@Autowired
	private IContractCounterpartService contractCounterpartService;
	@Autowired
	private IContractSealService iContractSealService;
	@Autowired
	private TemplateExportUntil templateExportUntil;

	private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY = "1332307279915393025";
	private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS = "1332307106157961217";
	private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE = "1332307534161518593";
	private static final Long DICT_BIZ_FINAL_VALUE_CONTRACT_PAY_TYPE = 1323239541401841666L;
	private static final Long DICT_BIZ_FINAL_VALUE_CONTRACT_RECEIVE_TYPE = 1323239469884764161L;
	private static final Integer AMOUNT_RATIO_VALUE = 100;

	@Override
	public IPage<ContractFormInfoResponseVO> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo) {
		if (!Func.isEmpty(contractFormInfo.getContractStatus())) {
			String[] code = contractFormInfo.getContractStatus().split(",");
			contractFormInfo.setCode(Arrays.asList(code));
		}
		if (Func.isNotEmpty(contractFormInfo.getCreateUserName())) {
			List<User> user = userClient.getByRealName(contractFormInfo.getCreateUserName()).getData();
			if (Func.isNotEmpty(user)) {
				StringBuilder real = new StringBuilder();
				user.forEach(u -> {
					real.append(u.getId());
					real.append(",");
				});
				real.substring(0, real.length() - 1);
				String[] reaN = real.toString().split(",");
				contractFormInfo.setRealName(Arrays.asList(reaN));
			}
		}
		//????????????????????????
		if (Func.isNotEmpty(contractFormInfo.getSealNames())) {
		} else {
			List<String> stringList = new ArrayList<>();
			contractFormInfo.setSealNames(stringList);
		}
		page = contractFormInfoMapper.pageList(page, contractFormInfo);
		IPage<ContractFormInfoResponseVO> pages = ContractFormInfoWrapper.build().entityPVPage(page);
		List<ContractFormInfoResponseVO> records = pages.getRecords();
		List<ContractFormInfoResponseVO> recordList = new ArrayList<>();
		for (ContractFormInfoResponseVO v : records) {
			/*??????????????????????????????????????????????????????*/
			R<User> user_r = userClient.userInfoById(v.getCreateUser());
			if (user_r.isSuccess()) {
				v.setUserRealName(null == user_r.getData() ? null : user_r.getData().getRealName());
			}
			R<Dept> dept_r = sysClient.getDept(v.getCreateDept());
			if (dept_r.isSuccess()) {
				v.setUserDepartName(null == dept_r.getData() ? null : dept_r.getData().getDeptName());
			}
			//????????????????????????   ????????????????????????????????? ?????????????????????
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
			//????????????????????????????????? ??????????????????
			ContractSealUsingInfoEntity sealUsingInfoEntity = sealUsingInfoMapper.selectUsingById(v.getId());
			if (Func.isNotEmpty(sealUsingInfoEntity)) {
				v.setSignTime(sealUsingInfoEntity.getSignTime());
				v.setSealInfoEntity(sealUsingInfoEntity);
			}
			//?????????????????????????????????  ????????????????????????????????????????????????????????????????????????,????????????
			ContractArchiveEntity archiveEntity = contractArchiveMapper.selectArchiveById(v.getId());
			if (Func.isNotEmpty(archiveEntity)) {
				v.setArchiveMonth(archiveEntity.getArchiveMonth());
				v.setPrintApplicant(archiveEntity.getPrintApplicant());
				v.setPrintCompany(archiveEntity.getPrintCompany());
				v.setContractPrintInitDept(archiveEntity.getContractPrintInitDept());
				v.setArchiveEntity(archiveEntity);
			}
			//????????????????????????????????? ??????????????????
			ContractSigningEntity signingEntity = signingMapper.selectSigningById(v.getId());
			if (Func.isNotEmpty(signingEntity)) {
				v.setSignDate(signingEntity.getSignDate());
				v.setContractStartingTime(signingEntity.getContractStartTime());
				v.setContractEndTime(signingEntity.getContractEndTime());
				v.setSigningEntity(signingEntity);
			} else if ("1".equals(v.getContractForm()) && "60".equals(v.getContractStatus())) {
				v.setArchiveMonth(new SimpleDateFormat("yyyy-MM-dd").format(v.getCreateTime()));
				String col = bizClient.getById("".equals(v.getColPayTerm()) ? 1L : Long.parseLong(v.getColPayTerm())).getData().getDictKey();
				if ("s2".equals(col)) {
					v.setColPayTerm("???");
				} else {
					v.setColPayTerm("???");
				}
			}
			//????????????????????????????????????  ?????????????????????
			List<ContractArchiveNotEntity> archiveNotEntity = contractArchiveNotMapper.selectArchiveNotById(v.getId());
			if (archiveNotEntity.size() > 0) {
				v.setArchiveNotEntity(archiveNotEntity);
			}
			//??????????????????
			if (Func.isNoneBlank(v.getTextFile())) {
				R<List<FileVO>> result = fileClient.getByIds(v.getTextFile());
				if (result.isSuccess()) {
					v.setTestFileVOList(result.getData());
				}
			}
			//??????????????????
			if (Func.isNoneBlank(v.getAttachedFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(v.getAttachedFiles());
				if (result.isSuccess()) {
					v.setAttachedFileVOList(result.getData());
				}
			}
			//????????????????????????????????????????????????
			List<DraftContractCounterpartEntity> dcc = iDraftContractCounterparService.selectByContractId(v.getId());
			if (Func.isNotEmpty(dcc)) {
				List<ContractCounterpartEntity> listAcc = new ArrayList<>();
				dcc.forEach(d -> {
					listAcc.add(contractCounterpartMapper.selectById(d.getCounterpartId()));
				});
				v.setDraftContractCounterpartList(dcc);
				v.setCounterpart(listAcc);
			}
			//??????????????????????????????????????????????????????
			List<ContractCounterpartEntity> acc = contractCounterpartMapper.selectByIds(v.getId());
			if (Func.isNotEmpty(acc)) {
				v.setCounterpart(acc);
			}
			//???????????????
			List<ContractBondEntity> contractBondList = contractBondMapper.selectByIds(v.getId());
			if (Func.isNotEmpty(contractBondList)) {
				v.setContractBond(contractBondList);
			}
			//?????????????????????
			List<ContractBondPlanEntity> contractBondPlanList = contractBondPlanMapper.selectByIds(v.getId());
			if (Func.isNotEmpty(contractBondPlanList)) {
				v.setBondPlanEntityList(contractBondPlanList);
			}
			//?????????????????????
			if (!Func.isEmpty(signingEntity)) {
				if (Func.isNoneBlank(signingEntity.getTextFiles())) {
					R<List<FileVO>> result = fileClient.getByIds(signingEntity.getTextFiles());
					if (result.isSuccess()) {
						v.setSigningTextFileVOList(result.getData());
					}
				}
			}
			//?????????????????????
			if (!Func.isEmpty(signingEntity)) {
				if (Func.isNoneBlank(signingEntity.getAttachedFiles())) {
					R<List<FileVO>> result = fileClient.getByIds(signingEntity.getAttachedFiles());
					if (result.isSuccess()) {
						v.setSigningAttachedFileVOList(result.getData());
					}
				}
			}
			//????????????????????????
			List<ContractFileDownloadLogEntity> fileDownloadLogEntity = fileDownloadLogService.getList(v.getId());
			if (Func.isNotEmpty(fileDownloadLogEntity)) {
				v.setFileDownloadLogEntities(fileDownloadLogEntity);
			}
			if ("30".equals(v.getContractSoure())) {
				List<ContractTemplateEntity> list = new ArrayList<>();
				ContractTemplateEntity templateEntity = templateService.getById(v.getContractTemplateId());
				list.add(templateEntity);
				v.setTemplateEntity(list);
			}
			R<User> user1 = userClient.userByCode("000000", v.getPersonCodeContract());
			if (Func.isNotEmpty(user1.getData())) {
				v.setPersonContractDept(user1.getData().getDeptNm());
			}
			recordList.add(v);
		}
		pages.setRecords(recordList);
		return pages;
	}

	/**
	 * ????????????????????????
	 * statisticsList
	 *
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	@Override
	public IPage<ContractFormInfoEntity> statisticsList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo) {
		//????????????????????????????????????
		if ("deptType".equals(contractFormInfo.getStatisticsType())) {
			page = baseMapper.deptType(page, contractFormInfo);
			List<ContractFormInfoEntity> records = page.getRecords();
			List<ContractFormInfoEntity> recordList = new ArrayList<>();
			for (ContractFormInfoEntity v : records) {
				/*??????????????????????????????????????????????????????*/
				v.setCreateUserName(userClient.userInfoById(v.getCreateUser()).getData().getRealName());
				v.setCreateDeptName(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
				//????????????????????????   ????????????????????????????????? ?????????????????????
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
					//??????????????????????????????
					Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
					BigDecimal payAmountVoidData = contractFormInfoMapper.payTypeAmount(Long.valueOf(pattern.matcher(v.getContractBigCategory()).matches() ? v.getContractBigCategory() : "1326765392596602881"), DICT_BIZ_FINAL_VALUE_CONTRACT_PAY_TYPE, v.getCreateDept(), contractFormInfo.getYearStart(), contractFormInfo.getYearEnd());
					BigDecimal receiveAmountVoidData = contractFormInfoMapper.payTypeAmount(Long.valueOf(pattern.matcher(v.getContractBigCategory()).matches() ? v.getContractBigCategory() : "1326765392596602881"), DICT_BIZ_FINAL_VALUE_CONTRACT_RECEIVE_TYPE, v.getCreateDept(), contractFormInfo.getYearStart(), contractFormInfo.getYearEnd());
					v.setPayAmountVoidData(payAmountVoidData);
					v.setReceiveAmountVoidData(receiveAmountVoidData);
				}
				recordList.add(v);
				page.setRecords(recordList);
			}
		}
		//???????????????????????????
		if ("monthType".equals(contractFormInfo.getStatisticsType())) {
			page = baseMapper.monthTypeFirm(page, contractFormInfo);
			List<ContractFormInfoEntity> records = page.getRecords();
			List<ContractFormInfoEntity> recordList = new ArrayList<>();
			for (ContractFormInfoEntity v : records) {
				//????????????????????????????????????????????????????????????????????????????????????
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
								//????????????????????????   ????????????????????????????????? ?????????????????????
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
		//????????????????????????
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
	 * ????????????????????????
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
		//????????????
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
					v.setAmountRatio(v.getContractAmount().divide(BigDecimal.valueOf(
						contractFormInfoMapper.getNumAmount(v.getContractBigCategory())), 2, BigDecimal.ROUND_HALF_DOWN).multiply(
						BigDecimal.valueOf(AMOUNT_RATIO_VALUE)) + "%");
					v.setContractBigCategory(bizClient.getValues("HTDL", Long.valueOf(v.getContractBigCategory())).getData());
				}
				if (Func.isNotEmpty(v.getColPayTerm())) {
					v.setColPayType(bizClient.getValues("col_pay_term", Long.valueOf(v.getColPayTerm())).getData());
				}
				//????????????????????????????????? ??????????????????
				ContractSigningEntity signingEntity = signingMapper.selectSigningById(v.getId());
				if (Func.isNotEmpty(signingEntity)) {
					v.setSigningEntity(signingEntity);
				}
				String contractAmount = v.getContractAmount() + "???";
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
				//TODO ???????????????????????????????????????????????????????????????????????????????????????????????????
				String contractAmount = v.getContractAmount() + "???";
				v.setAmountVoidData(contractAmount);
				recordList.add(v);
			}
			pages.setRecords(recordList);
		}
		return pages;
	}

	/**
	 * ????????????????????????
	 *
	 * @return list
	 */
	@Override
	public void importContractFormInfo(List<ContractFormInfoImporter> data, MultipartFile file, String json, String contractTemplateId, String contractBigCategory, String contractSmallCategory) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		data.forEach(contractFormInfoExcel -> {
			//??????????????????
			List<ContractFormInfoImporterEx> read2;
			ContractFormInfoEntity contractFormInfoEntity = new ContractFormInfoEntity();
			contractFormInfoEntity.setContractTemplateId(Long.valueOf(contractTemplateId));
			contractFormInfoEntity.setContractBigCategory(contractBigCategory);
			contractFormInfoEntity.setContractSmallCategory(contractSmallCategory);
			contractFormInfoEntity.setContractStatus("10");
			contractFormInfoEntity.setContractSoure("40");
			if (!"???".equals(contractFormInfoExcel.getSealName()) && !"".equals(contractFormInfoExcel.getSealName()) && contractFormInfoExcel.getSealName() != null) {
				contractFormInfoEntity.setSealName(contractFormInfoExcel.getSealName());
			}
			if (!"???".equals(contractFormInfoExcel.getDictValue()) && !"".equals(contractFormInfoExcel.getDictValue()) && contractFormInfoExcel.getDictValue() != null) {
				contractFormInfoEntity.setDictValue(contractFormInfoExcel.getDictValue());
			}
			if (!"???".equals(contractFormInfoExcel.getPersonContract()) && !"".equals(contractFormInfoExcel.getPersonContract()) && contractFormInfoExcel.getPersonContract() != null) {
				contractFormInfoEntity.setPersonContract(contractFormInfoExcel.getPersonContract());
			}
			if (!"???".equals(contractFormInfoExcel.getSealNumber()) && !"".equals(contractFormInfoExcel.getSealNumber()) && contractFormInfoExcel.getSealNumber() != null) {
				contractFormInfoEntity.setSealNumber(Integer.parseInt(contractFormInfoExcel.getSealNumber()));
			}
			if (!"???".equals(contractFormInfoExcel.getContractPeriod()) && !"".equals(contractFormInfoExcel.getContractPeriod()) && contractFormInfoExcel.getContractPeriod() != null) {
				contractFormInfoEntity.setContractPeriod(contractFormInfoExcel.getContractPeriod());
			}
			try {
				if (!"???".equals(contractFormInfoExcel.getStartingTime()) && !"".equals(contractFormInfoExcel.getStartingTime()) && contractFormInfoExcel.getStartingTime() != null) {
					Date parse = simpleDateFormat.parse(contractFormInfoExcel.getStartingTime());
					contractFormInfoEntity.setStartingTime(parse);
				}
				if (!"???".equals(contractFormInfoExcel.getEndTime()) && !"".equals(contractFormInfoExcel.getEndTime()) && contractFormInfoExcel.getEndTime() != null) {
					Date parse = simpleDateFormat.parse(contractFormInfoExcel.getEndTime());
					contractFormInfoEntity.setEndTime(parse);
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (!"???".equals(contractFormInfoExcel.getColPayType()) && !"".equals(contractFormInfoExcel.getColPayType()) && contractFormInfoExcel.getColPayType() != null) {
				contractFormInfoEntity.setColPayType(contractFormInfoExcel.getColPayType());
			}
			if (!"???".equals(contractFormInfoExcel.getColPayTerm()) && !"".equals(contractFormInfoExcel.getColPayTerm()) && contractFormInfoExcel.getColPayTerm() != null) {
				contractFormInfoEntity.setColPayTerm(contractFormInfoExcel.getColPayTerm());
			}
			if (!"???".equals(contractFormInfoExcel.getContractAmount()) && !"".equals(contractFormInfoExcel.getContractAmount()) && contractFormInfoExcel.getContractAmount() != null) {
				contractFormInfoEntity.setContractAmount(new BigDecimal(contractFormInfoExcel.getContractAmount()));
			}
			if ("???".equals(contractFormInfoExcel.getExtension()) && contractFormInfoExcel.getExtension() != null) {
				contractFormInfoEntity.setExtension("2");
			} else {
				contractFormInfoEntity.setExtension("1");
			}
			if (!"???".equals(contractFormInfoExcel.getContractForm()) && !"".equals(contractFormInfoExcel.getContractForm()) && contractFormInfoExcel.getContractForm() != null) {
				contractFormInfoEntity.setContractForm(contractFormInfoExcel.getContractForm());
			}
			if (!"???".equals(contractFormInfoExcel.getCounterpartPerson()) && !"".equals(contractFormInfoExcel.getCounterpartPerson()) && contractFormInfoExcel.getCounterpartPerson() != null) {
				contractFormInfoEntity.setCounterpartPerson(contractFormInfoExcel.getCounterpartPerson());
			}
			if (!"???".equals(contractFormInfoExcel.getTelephonePerson()) && !"".equals(contractFormInfoExcel.getTelephonePerson()) && contractFormInfoExcel.getTelephonePerson() != null) {
				contractFormInfoEntity.setTelephonePerson(contractFormInfoExcel.getTelephonePerson());
			}
			if (!"???".equals(contractFormInfoExcel.getEmailPerson()) && !"".equals(contractFormInfoExcel.getEmailPerson()) && contractFormInfoExcel.getEmailPerson() != null) {
				contractFormInfoEntity.setEmailPerson(contractFormInfoExcel.getEmailPerson());
			}
			if (!"???".equals(contractFormInfoExcel.getAddressPerson()) && !"".equals(contractFormInfoExcel.getAddressPerson()) && contractFormInfoExcel.getAddressPerson() != null) {
				contractFormInfoEntity.setAddressPerson(contractFormInfoExcel.getAddressPerson());
			}

			this.save(contractFormInfoEntity);
			if (Func.isNotEmpty(contractFormInfoEntity.getId()) && contractFormInfoEntity.getId() != null) {
				//????????????contract_counterpart?????????????????????????????????????????????????????????id ????????????id?????????id????????? contract_counterpart_setting ??????
				if (!"".equals(contractFormInfoExcel.getCounterpartName())) {
					List<ContractCounterpartEntity> contractCounterpartEntities = contractCounterpartMapper.selectByName(contractFormInfoExcel.getCounterpartName());
					if (contractCounterpartEntities.size() > 0) {
						contractFormInfoMapper.deleteCounterpart(contractFormInfoEntity.getId());
						contractFormInfoMapper.saveCounterpart(contractFormInfoEntity.getId(), contractCounterpartEntities);
						contractFormInfoEntity.setCounterpart(contractCounterpartEntities);
						//???????????????contract_bond?????? contract_bond??????????????????????????????id
						//?????????????????????????????????????????????
						if (!"???".equals(contractFormInfoExcel.getIsNotBond())) {
							ContractBondEntity contractBondEntity = new ContractBondEntity();
							contractBondEntity.setIsNotBond(contractFormInfoExcel.getIsNotBond());
							if (!"???".equals(contractFormInfoExcel.getPlanPayAmount()) && !"".equals(contractFormInfoExcel.getPlanPayAmount()) && contractFormInfoExcel.getPlanPayAmount() != null) {
								if (!"???".equals(contractFormInfoExcel.getPlanPayAmount())) {
									contractBondEntity.setPlanPayAmount(new BigDecimal(0));
								} else {
									contractBondEntity.setPlanPayAmount(new BigDecimal(2));
								}
							}
							try {
								if (!"???".equals(contractFormInfoExcel.getPlanPayTime()) && !"".equals(contractFormInfoExcel.getPlanPayTime()) && contractFormInfoExcel.getPlanPayTime() != null) {
									Date parse = simpleDateFormat.parse(contractFormInfoExcel.getPlanPayTime());
									contractBondEntity.setPlanReturnTime(parse);
								}
								if (!"???".equals(contractFormInfoExcel.getPlanReturnTime()) && !"".equals(contractFormInfoExcel.getPlanReturnTime()) && contractFormInfoExcel.getPlanReturnTime() != null) {
									Date parse = simpleDateFormat.parse(contractFormInfoExcel.getPlanReturnTime());
									contractBondEntity.setPlanReturnTime(parse);
								}

							} catch (ParseException e) {
								e.printStackTrace();
							}
							contractBondEntity.setCounterpartId(contractCounterpartEntities.get(0).getId());
							contractBondMapper.insert(contractBondEntity);
							//????????????????????????????????????
							List<ContractBondEntity> list = new ArrayList();
							list.add(contractBondEntity);
							contractFormInfoEntity.setContractBond(list);
							//??????????????????id????????????id?????????contract_bond_setting???????????????
							List<Long> ids = new ArrayList<>();
							ids.add(contractFormInfoEntity.getId());
							contractBondMapper.saveBond(ids, contractBondEntity.getId());
						}
					}
				}
			}
			//????????????????????????
			ContractTemplateEntity contractTemplate = contractTemplateMapper.selectById(contractTemplateId);
			ExcelSaveUntil excelSaveUntil = new ExcelSaveUntil();
			excelSaveUntil.excelSave(contractFormInfoEntity, contractFormInfoExcel, contractTemplate, file, json);
		});
	}

	@Override
	public Integer selectSigningCount(String contractBigCategory) {
		return contractFormInfoMapper.selectSigningCount(contractBigCategory);
	}

	//json????????????
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
					System.out.println("??????");
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
			//????????????
			if (ContractFormInfoTemplateContract.CONTRACT_BIG_CATEGORY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				if (null != jsonObj) {
					jsonObj.put("template", contractTemplate);
					jsonObj.put("first", contractFormInfo.getContractBigCategory());
					jsonObj.put("second", contractFormInfo.getContractSmallCategory());
					templateField.setSecondSelectDataObject(jsonObj);
				}
			}
			//????????????
			if (ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				if (null != jsonObj) {
					jsonObj.put("first", contractFormInfo.getColPayType());
					jsonObj.put("second", contractFormInfo.getColPayTerm());
					jsonObj.put("days", contractFormInfo.getDays());
					templateField.setSecondSelectDataObject(jsonObj);
				}
			}
			//?????????????????????????????????
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_REQUORED.equals(templateField.getRequired())) {
				List<Object> objectList = JSON.parseArray(templateField.getRequiredData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setRequiredDataList(objectList);
				}
			}
			//???????????????????????????
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_SMALL.contains(templateField.getComponentType())) {
				List<Object> objectList = JSON.parseArray(templateField.getDicData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setDicDataList(objectList);
				}
			}
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE.contains(templateField.getComponentType())) {
				if (ContractFormInfoTemplateContract.CONTRACT_ACCORDING.equals(templateField.getRelationCode())) {
					/*??????????????????*/
					if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getAccording()));
						templateField.setTableDataList(contractFormInfo.getAccording());
					}
				}
				//??????????????????????????????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_COUNTERPART.equals(templateField.getRelationCode())) {
					//?????????????????????JSONObject
					JSONObject obj = JSON.parseObject(templateField.getTableDataObject());
					//???????????????????????????
					if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
						obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART, contractFormInfo.getCounterpart());
					}
					if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
						obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_COUNTERPART, contractFormInfo.getContractBond());
					}
					templateField.setTableDataObject(JSONObject.toJSONString(obj));
					templateField.setTableDataObjectList(obj);
				}
				//*??????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_PERFORMANCE.equals(templateField.getRelationCode())) {
					if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getPerformanceList()));
						templateField.setTableDataList(contractFormInfo.getPerformanceList());
					}
				}
				//*???????????????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_PERFORMANCE_COLPAY.equals(templateField.getRelationCode())) {
					if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
						templateField.setTableData(JSONObject.toJSONString(contractFormInfo.getPerformanceColPayList()));
						templateField.setTableDataList(contractFormInfo.getPerformanceColPayList());
					}
				}
				//*???????????????????????????
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
	 * ??????????????????
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
	 * ?????????????????????
	 *
	 * @param vo ????????????id????????????id
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveCounterpart(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.deleteCounterpart(vo.getId());
		contractFormInfoMapper.saveCounterpart(vo.getId(), vo.getCounterpart());
	}

	/**
	 * ?????????????????????
	 *
	 * @param vo ????????????id????????????id
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveCollection(ContractFormInfoRequestVO vo) {
		iCollectionService.deleteCounterpart(vo.getId());
		vo.getCollection().forEach(collectionEntity -> {
			collectionEntity.setRefContractId(vo.getId().toString());
			iCollectionService.save(collectionEntity);
		});
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveCollectionMulti(ContractMultPaymenEntity multPaymenEntity, Long contractId) {
		multPaymenEntity.getCollection().forEach(cl -> {
			cl.setRefContractId(multPaymenEntity.getId().toString());
			cl.setRefPaymenId(contractId.toString());
			iCollectionService.save(cl);
		});
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ContractFormInfoRequestVO setMultPaymenEntityList(ContractFormInfoRequestVO contractFormInfo) {
		iCollectionService.deleteContractId(contractFormInfo.getId());
		contractMultPaymenService.deleteMult(contractFormInfo.getId());
		List<ContractMultPaymenEntity> list = new ArrayList<>();
		contractFormInfo.getMultPaymenEntityList().forEach(mult -> {
			ContractMultPaymenEntity contractMultPaymenEntity = new ContractMultPaymenEntity();
			mult.setCurrencyCategory(contractFormInfo.getCurrencyCategory());
			mult.setContractId(contractFormInfo.getId().toString());
			BeanUtil.copy(mult, contractMultPaymenEntity);
			contractMultPaymenService.saveOrUpdate(contractMultPaymenEntity);
			if (CollectionUtil.isNotEmpty(contractMultPaymenEntity.getCollection()) && contractMultPaymenEntity.getCollection().size() > 0) {
				saveCollectionMulti(contractMultPaymenEntity, contractFormInfo.getId());
			}
			list.add(contractMultPaymenEntity);
		});
		contractFormInfo.setMultPaymenEntityList(list);
		return contractFormInfo;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDraftContractCounterpartList(ContractFormInfoRequestVO contractFormInfo) {
		draftContractCounterpartMapper.deleteDraftCounterpart(contractFormInfo.getId());
		ContractSealEntity se = iContractSealService.getByFdNo(contractFormInfo.getSealName());
		contractFormInfo.getDraftContractCounterpartList().forEach(dcl -> {
			if (se.getFdFactname().equals(dcl.getSubsidiaryPerson())) {
				contractFormInfo.setContractRoles(dcl.getCounterpartIdentity());
			}
			dcl.setContractId(contractFormInfo.getId().toString());
			draftContractCounterparService.save(dcl);
		});
	}

	/**
	 * ?????????????????????
	 *
	 * @param vo ????????????id????????????id
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveContractSeal(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.deleteContractSeal(vo.getId());
		contractFormInfoMapper.saveContractSeal(vo.getId(), vo.getContractSeal());
	}

	/**
	 * ????????????????????????
	 *
	 * @param vo ?????????
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveAccording(ContractFormInfoRequestVO vo) {
		List<ContractAccordingEntity> list = new ArrayList<>();
		List<String> acList = new ArrayList<>();
		List<String> acIdList = new ArrayList<>();
		//????????????ID??????????????????  ??????????????????????????????????????????????????????
		Integer count = contractAccordingMapper.selectByContractIds(vo.getId());
		if (count != 0) {
			contractAccordingMapper.clearEmpty();
			contractAccordingMapper.clearDate();
			contractAccordingMapper.deleteAccording(vo.getId());
		}
		/********???????????????????????????????????? START*******/
		//?????????????????????????????????????????????????????????????????????
		vo.getAccording().forEach(acc -> {
			acList.add(acc.getFileId());
		});
		List<ContractAccordingEntity> ac = contractAccordingMapper.selectByFileId(acList);
		if (Func.isEmpty(ac)) {
			accordingService.saveBatch(vo.getAccording());
			//?????????????????????set?????????????????????
			list.addAll(vo.getAccording());
			contractFormInfoMapper.saveAccording(vo.getId(), list);
		} else {
			ac.forEach(foe -> {
				acIdList.add(foe.getId().toString());
			});
			contractFormInfoMapper.saveAccordingIds(vo.getId(), acIdList);
		}
		/**********????????????????????????????????????END********/
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveBondAndPlan(ContractFormInfoRequestVO contractFormInfo) {
		List<Long> list = new ArrayList<>();
		ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
		//???????????????????????????
		contractBondService.deleteByContractId(contractFormInfo.getId());
		//????????????????????????????????????
		contractBondPlanService.deleteByContractId(contractFormInfo.getId());
		for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
			BeanUtil.copy(contractBondEntity, contractBondPlan);
			if (Func.isEmpty(contractBondEntity.getId())) {
				contractBondService.save(contractBondEntity);
			}
			//???????????????????????????
			contractBondPlan.setContractId(contractFormInfo.getId());
			contractBondPlan.setId(null);
			contractBondPlanService.save(contractBondPlan);
			list.add(contractBondEntity.getId());
		}
		contractBondService.saveBond(list, contractFormInfo.getId());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveBondMultiPlan(ContractFormInfoRequestVO contractFormInfo) {
		//????????????ID
		List<Long> list = new ArrayList<>();
		ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
		//???????????????????????????
		contractBondService.deleteByContractId(contractFormInfo.getId());
		//????????????????????????????????????
		contractBondPlanService.deleteByContractId(contractFormInfo.getId());
		for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
			BeanUtil.copy(contractBondEntity, contractBondPlan);
			if (Func.isEmpty(contractBondEntity.getId())) {
				contractBondService.save(contractBondEntity);
			} else {
				contractBondEntity.setId(null);
				contractBondService.save(contractBondEntity);
			}
			//???????????????????????????
			contractBondPlan.setContractId(contractFormInfo.getId());
			contractBondPlan.setId(null);
			contractBondPlanService.save(contractBondPlan);
			list.add(contractBondEntity.getId());
		}
		contractBondService.saveBond(list, contractFormInfo.getId());
	}

	/**
	 * ????????????????????????
	 *
	 * @param vo
	 */
	@Override
	public void saveSeal(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveSeal(vo.getId(), vo.getSeal());
	}

	/**
	 * ????????????????????????
	 *
	 * @param vo ????????????id?????????id
	 */
	@Override
	public void saveAssessment(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveAssessment(vo.getId(), vo.getAssessment());
	}

	/**
	 * ????????????????????????
	 *
	 * @param vo ?????????????????????id?????????id
	 */
	@Override
	public void saveArchive(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveArchive(vo.getId(), vo.getArchive());
	}

	/**
	 * ????????????????????????
	 *
	 * @param vo
	 */
	@Override
	public void saveSigning(ContractFormInfoRequestVO vo) {
		contractFormInfoMapper.saveSigning(vo.getId(), vo.getSigning());
	}

	/**
	 * ????????????id?????????????????????????????????vo
	 *
	 * @param id ??????id
	 * @return
	 */
	@Override
	public ContractFormInfoResponseVO getById(Long id) {
		ContractFormInfoResponseVO contractFormInfoResponseVO = new ContractFormInfoResponseVO();
		ContractFormInfoEntity contractFormInfo = contractFormInfoMapper.selectById(id);
		ContractFormInfoEntity changeFormInfoEntity = contractFormInfoMapper.selectByChangeId(id);
		//????????????ID???????????????????????????????????? ?????????????????????????????????????????????????????????10??? ????????????????????????????????????**??????????????????**
		//???????????????????????????????????????????????????????????????????????????????????????VOList **????????????**???
		if (Func.isNotEmpty(changeFormInfoEntity)) {
			if (ContractStatusEnum.TEMPORARY_STORAGE_STATUS.getKey().toString().equals(changeFormInfoEntity.getContractStatus())) {
				contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(changeFormInfoEntity);
			} else {
				contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(contractFormInfo);
				//?????????????????????????????????SET???VOList???
				List<ContractFormInfoEntity> changList = new ArrayList<>();
				changList.add(changeFormInfoEntity);
				contractFormInfoResponseVO.setFormInfosEntityNewVOList(changList);
			}
		} else {
			contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(contractFormInfo);
		}
		//?????????????????????
		if (Func.isNoneBlank(contractFormInfoResponseVO.getSealName())) {
			String[] sealNameList = contractFormInfoResponseVO.getSealName().split(",");
			contractFormInfoResponseVO.setSealNameList(sealNameList);
		}
		//????????????
		List<ContractAccordingEntity> contractAccordingList = contractAccordingMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setAccording(contractAccordingList);
		//?????????????????????????????????
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
		//????????????????????????????????????????????????
		List<DraftContractCounterpartEntity> dcc = iDraftContractCounterparService.selectByContractId(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(dcc)) {
			contractFormInfoResponseVO.setDraftContractCounterpartList(dcc);
		}
		//???????????????????????????????????????
		if ("20".equals(contractFormInfoResponseVO.getContractSoure())) {
			List<ContractSealEntity> sealEntity = contractSealMapper.selectByIds(contractFormInfoResponseVO.getId());
			if (Func.isNotEmpty(sealEntity)) {
				contractFormInfoResponseVO.setContractSeal(sealEntity);
			}
		}
		//???????????????????????????????????????
		List<ContractBondPlanEntity> contractBondPlanList = contractBondPlanMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setBondPlanEntityList(contractBondPlanList);
		//???????????????
		List<ContractBondEntity> contractBondList = contractBondMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setContractBond(contractBondList);
		//????????????????????????
		List<ContractPerformanceEntity> contractPerformanceList = contractPerformanceMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setPerformanceList(contractPerformanceList);
		//???????????????????????????
		List<ContractPerformanceColPayEntity> contractPerformanceColPayList = contractPerformanceColPayMapper.selectByIds(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setPerformanceColPayList(contractPerformanceColPayList);
		//???????????????????????????????????????
		List<ContractMultPaymenEntity> multPaymenEntityList = multPaymenMapper.selectByMultId(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(multPaymenEntityList)) {
			multPaymenEntityList.forEach(ml -> {
				//????????????????????????
				ml.setCollection(iCollectionService.getByIdList(ml.getId()));
			});
			contractFormInfoResponseVO.setMultPaymenEntityList(multPaymenEntityList);
		}
		//??????????????????
		ContractRelieveEntity relieveEntity = relieveMapper.selectRelieveById(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(relieveEntity)) {
			ContractRelieveResponseVO relieveResponseVO = ContractRelieveWrapper.build().entityPV(relieveEntity);
			if (Func.isNotEmpty(relieveEntity.getSigningBasis())) {
				relieveResponseVO.setAccordingEntity(contractAccordingMapper.selectById(relieveEntity.getSigningBasis()));
			}
			if (Func.isNotBlank(relieveEntity.getTermAgreement())) {
				//???????????????????????????????????????????????????????????????VO
				R<List<FileVO>> result = fileClient.getByIds(relieveResponseVO.getTermAgreement());
				if (result.isSuccess()) {
					relieveResponseVO.setTermAgreementFileVOList(result.getData());
				}
			}
			contractFormInfoResponseVO.setRelieveEntity(relieveResponseVO);
		}
		//????????????????????????????????????vo
		ContractAssessmentEntity contractAssessmentEntity = contractAssessmentMapper.selectByAssessmentId(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(contractAssessmentEntity)) {
			ContractAssessmentResponseVO assessmentResponseVO = ContractAssessmentWrapper.build().entityPV(contractAssessmentEntity);
			//??????????????????
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
		//???????????????????????????????????????????????????vo
		List<ContractArchiveNotEntity> archiveNotEntity = archiveNotMapper.selectArchiveNotById(contractFormInfoResponseVO.getId());
		contractFormInfoResponseVO.setArchiveNotEntity(archiveNotEntity);
		//????????????????????????????????????vo
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
		//???????????????????????????????????????vo
		if (Func.isEmpty(contractFormInfoResponseVO.getSealInfoEntity())) {
			ContractSealUsingInfoEntity sealUsingInfoEntity = sealUsingInfoMapper.selectUsingById(contractFormInfoResponseVO.getId());
			if (Func.isNotEmpty(sealUsingInfoEntity)) {
				ContractSealUsingInfoResponseVO sealUsingInfoResponseVO = ContractSealUsingInfoWrapper.build().entityPV(sealUsingInfoEntity);
				contractFormInfoResponseVO.setSealInfoEntity(sealUsingInfoResponseVO);
			}
		}
		//???????????????????????????????????????vo
		ContractSigningEntity signingEntity = signingMapper.selectSigningById(contractFormInfoResponseVO.getId());
		if (Func.isNotEmpty(signingEntity)) {
			ContractSigningResponseVO signingResponseVO = ContractSigningWrapper.build().entityPV(signingEntity);
			contractFormInfoResponseVO.setSigningEntity(signingResponseVO);
		}
		//??????????????????
		if (Func.isNoneBlank(contractFormInfoResponseVO.getTextFile())) {
			R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getTextFile());
			if (result.isSuccess()) {
				contractFormInfoResponseVO.setTestFileVOList(result.getData());
			}
		}
		//??????????????????PDF
		if (Func.isNoneBlank(contractFormInfoResponseVO.getTextFilePdf())) {
			R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getTextFilePdf());
			if (result.isSuccess()) {
				contractFormInfoResponseVO.setTestFileVOListPDF(result.getData());
			}
		}
		//??????????????????
		if (Func.isNoneBlank(contractFormInfoResponseVO.getAttachedFiles())) {
			R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getAttachedFiles());
			if (result.isSuccess()) {
				contractFormInfoResponseVO.setAttachedFileVOList(result.getData());
			}
		}
		/* ??????????????? */
		if (!Func.isEmpty(contractFormInfoResponseVO.getCreateUser())) {
			/*User user = UserCache.getUser(entity.getCreateUser());*/
			User user = userClient.userInfoById(contractFormInfoResponseVO.getCreateUser()).getData();
			contractFormInfoResponseVO.setUserRealName(user.getRealName());
		}
		/* ????????????????????? */
		if (!Func.isEmpty(contractFormInfoResponseVO.getCreateDept())) {
			String dept = sysClient.getDeptName(contractFormInfoResponseVO.getCreateDept()).getData();
			contractFormInfoResponseVO.setUserDepartName(dept);
		}

		//?????????????????????
		if (!Func.isEmpty(signingEntity)) {
			if (Func.isNoneBlank(signingEntity.getTextFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(signingEntity.getTextFiles());
				if (result.isSuccess()) {
					contractFormInfoResponseVO.setSigningTextFileVOList(result.getData());
				}
			}
		}
		//?????????????????????
		if (!Func.isEmpty(signingEntity)) {
			if (Func.isNoneBlank(signingEntity.getAttachedFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(signingEntity.getAttachedFiles());
				if (result.isSuccess()) {
					contractFormInfoResponseVO.setSigningAttachedFileVOList(result.getData());
				}
			}
		}
		//??????????????????
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
		//????????????????????????
		contractFormInfoResponseVO.setCollection(iCollectionService.getByIdList(contractFormInfoResponseVO.getId()));
		//??????????????????
		contractFormInfoResponseVO.setPerServiceContentList(perServiceContentService.findInfoByContractId(contractFormInfoResponseVO.getId()));
		contractFormInfoResponseVO.setPerCollectPayList(perCollectPayService.findListByContractId(contractFormInfoResponseVO.getId()));

		Contract contract = entityToMiddlegroundContract(contractFormInfoResponseVO);
		log.info("???????????????json:{}", JsonUtil.toJson(contract));
		return contractFormInfoResponseVO;
	}

	/**
	 * ????????????????????????????????????
	 *
	 * @param status ????????????
	 * @return
	 */
	@Override
	public List<ContractFormInfoEntity> getByStatus(String status) {
		return contractFormInfoMapper.selectByStatus(status);
	}

	/**
	 * ??????????????????????????????
	 *
	 * @param id
	 * @return
	 */
	@Override
	public ContractFormInfoResponseVO getByChangeHistoryId(Long id) {
		List<ContractFormInfoEntity> formInfoEntityList = new ArrayList<>();
		//??????????????????id????????????????????????
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
			//?????????????????????????????????
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
		//?????????????????????vo
		ContractFormInfoResponseVO formInfoResponseVO = ContractFormInfoWrapper.build().entityPV(formInfoEntity);
		formInfoResponseVO.setFormInfosEntityOldVOList(formInfoEntityList);
		return formInfoResponseVO;
	}

	/**
	 * ????????????????????????
	 *
	 * @param id              ??????id
	 * @param fileExportCount ????????????
	 */
	@Override
	public void textExportCount(Long id, Integer fileExportCount, Integer fileExportCategory) {
		contractFormInfoMapper.textExportCount(id, fileExportCount, fileExportCategory);
	}

	/**
	 * ???????????????????????? ????????????-????????????   ????????????-?????????????????????
	 */
	@Override
	public R<ContractFormInfoEntity> SingleSignE(R<ContractFormInfoEntity> r) {
		ContractFormInfoEntity contractFormInfoEntity = r.getData();
		UploadFileEntity uploadFileEntity = new UploadFileEntity();
		File fileBH;
		List<File> files = new ArrayList<File>();
		contractFormInfoEntity = this.makeContractN(contractFormInfoEntity);
		List<FileVO> fileVO = fileClient.getByIds(contractFormInfoEntity.getTextFile()).getData();
		for (FileVO f : fileVO) {
			fileBH = contractFileHandle(contractFormInfoEntity, f);
			files.add(fileBH);
		}
		uploadFileEntity.setFile(files);
		// ??????????????????,????????????????????????????????????
		uploadFileEntity.setIsMerge("0");
		// ?????????????????? ?????????????????? ??????????????????????????????token?????????, ???????????????????????????????????????????????????,????????????????????????????????????,???????????????blade-abutment???resources.application???
		List<UploadFileVo> uploadFileVoList = abutmentClient.uploadFiles(uploadFileEntity).getData();
		StringBuilder UploadFileVoId = new StringBuilder();
		uploadFileVoList.forEach(up -> {
			UploadFileVoId.append(up.getId());
			UploadFileVoId.append(",");
		});
		UploadFileVoId.substring(0, UploadFileVoId.length());
		contractFormInfoEntity.setTextFilePdf(UploadFileVoId.toString());
		//??????????????????
		R<EkpVo> ekpVo;
		//??????????????????????????????????????????
		if (ContractTypeEnum.MULTI.getKey().toString().equals(contractFormInfoEntity.getContractSoure())) {
			ekpVo = abutmentClient.sendEkpMultiPost(contractFormInfoEntity);
		} else {
			ekpVo = abutmentClient.sendEkpFormPost(contractFormInfoEntity);
		}
		if (ekpVo.getCode() == HttpStatus.OK.value()) {
			contractFormInfoEntity.setRelContractId(ekpVo.getData().getDoc_info());
			contractFormInfoEntity.setEkpNumber(ekpVo.getData().getEkp_number());
		}
		//????????????
		abutmentClient.transferStationPushContract(entityToMiddlegroundContract(contractFormInfoEntity));
		r.setCode(ekpVo.getCode());
		r.setData(contractFormInfoEntity);
		return r;
	}

	/**
	 * ????????????????????????  -????????????-????????????   ????????????-???????????????
	 */
	@Override
	public R<ContractFormInfoEntity> SingleSign(R<ContractFormInfoEntity> r) {
		ContractFormInfoEntity contractFormInfoEntity = r.getData();
		// ?????????????????? ??????
		UploadFileEntity uploadFileEntity = new UploadFileEntity();
		// ????????????file??????
		List<File> files = new ArrayList<File>();
		contractFormInfoEntity = this.makeContractN(contractFormInfoEntity);
		File filePDF = contractTextFileToPDF(contractFormInfoEntity);
		File fileBH = addWaterMakFileBH(filePDF, contractFormInfoEntity);
		files.add(fileBH);
		uploadFileEntity.setFile(files);
		// ??????????????????,????????????????????????????????????
		uploadFileEntity.setIsMerge("0");
		// ?????????????????? ?????????????????? ??????????????????????????????token?????????, ???????????????????????????????????????????????????,????????????????????????????????????,???????????????blade-abutment???resources.application???
		List<UploadFileVo> uploadFileVoList = abutmentClient.uploadFiles(uploadFileEntity).getData();
		//epk????????????
		contractFormInfoEntity.setTextFilePdf(uploadFileVoList.get(0).getId());
		MultipartFile multipartFile = null;
		try {
			multipartFile = new MockMultipartFile("file", fileBH.getName(),
				ContentType.MULTIPART.toString(), new FileInputStream(fileBH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* ???????????? */
		R<FileVO> fileVO = fileClient.save(multipartFile);
		contractFormInfoEntity.setOtherInformation(fileVO.getData().getLink());
		R<EkpVo> ekpVo = null;
		//??????????????????????????????????????????
		if (ContractTypeEnum.MULTI.getKey().toString().equals(contractFormInfoEntity.getContractSoure())) {
			ekpVo = abutmentClient.sendEkpMultiPost(contractFormInfoEntity);
		} else {
			ekpVo = abutmentClient.sendEkpFormPost(contractFormInfoEntity);
		}
		log.info(ekpVo);
		if (ekpVo.getCode() == HttpStatus.OK.value()) {
			contractFormInfoEntity.setRelContractId(ekpVo.getData().getDoc_info());
			contractFormInfoEntity.setEkpNumber(ekpVo.getData().getEkp_number());
		}
		//????????????
		abutmentClient.transferStationPushContract(entityToMiddlegroundContract(contractFormInfoEntity));
		r.setData(contractFormInfoEntity);
		r.setCode(ekpVo.getCode());
		return r;
	}

	/**
	 * ??????????????????  ???????????????PDF
	 **/
	public File contractTextFileToPDF(ContractFormInfoEntity contractFormInfoEntity) {
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		File filePDF = null;
		if (ContractTypeEnum.INDE.getKey().toString().equals(contractFormInfoEntity.getContractSoure())
			|| ContractTypeEnum.MULTI.getKey().toString().equals(contractFormInfoEntity.getContractSoure())) {
			FileVO fileVOFirst = fileClient.getByIds(contractFormInfoEntity.getTextFile()).getData().get(0);
			String newFileDoc = fileVOFirst.getLink();
			int index = fileVOFirst.getName().lastIndexOf(".");
			String suffix = fileVOFirst.getName().substring(index + 1);
			String newFilePdf = ftlPath + fileVOFirst.getName().substring(0, index) + date + ".pdf";
			if ("pdf".equals(suffix)) {
				filePDF = new File(newFilePdf);
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(filePDF);
					fos.write(AsposeWordToPdfUtils.getUrlFileData(newFileDoc));
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				AsposeWordToPdfUtils.doc2pdf(newFileDoc, newFilePdf);
				filePDF = new File(newFilePdf);
			}
		}
		if (ContractTypeEnum.TEMPLATE.getKey().toString().equals(contractFormInfoEntity.getContractSoure())) {
			filePDF = new File(contractFormInfoEntity.getFilePdf());
		}
		return filePDF;
	}

	/**
	 * ??????????????????ekp????????????  ????????????????????????????????????
	 **/
	public File addWaterMakFileBH(File filePDF, ContractFormInfoEntity contractFormInfoEntity) {
		File fileBH = null;
		InputStream in;
		try {
			in = new FileInputStream(filePDF);
			String fileId = AsposeWordToPdfUtils.addWaterMak(in, "????????????", filePDF.getName(), null);
			Thread.sleep(2000);
			String url = AsposeWordToPdfUtils.downloadFile(fileId);
			//?????????????????????
			FileOutputStream fos;
			fos = new FileOutputStream(filePDF);
			fos.write(AsposeWordToPdfUtils.getUrlFileData(url));
			fos.close();
			in.close();
			String BH = ftlPath + "BH-" + filePDF.getName();
			AsposeWordToPdfUtils.addWaterMark(filePDF.getPath(), BH, contractFormInfoEntity.getContractNumber());
			fileBH = new File(BH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileBH;
	}

	/*********************************************************????????????????????????START****************************************************************/
	@Override
	public void pushBatch(List<ContractFormInfoEntity> infoList) {
		if (CollectionUtil.isEmpty(infoList)) {
			return;
		}
		//inde||??????????????????    template||??????????????????
		List<ContractFormInfoEntity> indeContractFormInfoEntity = new ArrayList<>();
		List<ContractFormInfoEntity> templateContractFormInfoEntity = new ArrayList<>();
		infoList.forEach(infoL -> {
			if (infoL.getContractSoure().equals(ContractTypeEnum.BATCH_INDE.getKey().toString())) {
				indeContractFormInfoEntity.add(infoL);
			} else if (infoL.getContractSoure().equals(ContractTypeEnum.BATCH_TEMPLATE.getKey().toString())) {
				templateContractFormInfoEntity.add(infoL);
			}
		});
		if (Func.isNotEmpty(indeContractFormInfoEntity)) {
			indeBatchPush(indeContractFormInfoEntity);
		}
		if (Func.isNotEmpty(templateContractFormInfoEntity)) {
			templateBatchPush(templateContractFormInfoEntity);
		}
	}

	//??????????????????
	@Async
	public void indeBatchPush(List<ContractFormInfoEntity> indeBatch) {
		//electronic||???????????????????????????  ?????????????????????????????????-????????????   ????????????-?????????????????? ???    entity||????????????-?????????????????????   ????????????-???????????? ?????????????????????
		List<ContractFormInfoEntity> electronicContractFormInfoEntity = new ArrayList<>();
		List<ContractFormInfoEntity> entityContractFormInfoEntity = new ArrayList<>();
		indeBatch.forEach(indeB -> {
			if (indeB.getContractForm().equals(ContractTypeEnum.ELECTRONIC_CONTRACT_WE.getKey().toString()) ||
				indeB.getContractForm().equals(ContractTypeEnum.ENTITY_CONTRACT_WE.getKey().toString())) {
				electronicContractFormInfoEntity.add(indeB);
			} else if (indeB.getContractForm().equals(ContractTypeEnum.ELECTRONIC_CONTRACT_OTHER.getKey().toString()) ||
				indeB.getContractForm().equals(ContractTypeEnum.ENTITY_CONTRACT_OTHER.getKey().toString())) {
				entityContractFormInfoEntity.add(indeB);
			}
		});
		if (Func.isNotEmpty(electronicContractFormInfoEntity)) {
			indeBatchSignE(electronicContractFormInfoEntity);
		}
		if (Func.isNotEmpty(entityContractFormInfoEntity)) {
			indeBatchSign(entityContractFormInfoEntity);
		}
	}

	/**
	 * ??????????????????
	 */
	@Async
	public void templateBatchPush(List<ContractFormInfoEntity> templateBatch) {
		indeBatchSign(templateBatch);
	}

	/**
	 * ???????????? ???????????????????????????????????? ????????????
	 **/
	public void indeBatchSignE(List<ContractFormInfoEntity> indeBatchSignE) {
		indeBatchSignE.forEach(indeE -> {
			try {
				UploadFileEntity uploadFileEntity = new UploadFileEntity();
				File fileBH;
				List<File> files = new ArrayList<>();
				indeE = this.makeContractN(indeE);
				List<FileVO> fileVO = fileClient.getByIds(indeE.getTextFile()).getData();
				for (FileVO file : fileVO) {
					fileBH = contractFileHandle(indeE, file);
					files.add(fileBH);
				}
				uploadFileEntity.setFile(files);
				uploadFileEntity.setIsMerge("0");
				List<UploadFileVo> uploadFileVoList = abutmentClient.uploadFiles(uploadFileEntity).getData();
				StringBuilder name = new StringBuilder();
				uploadFileVoList.forEach(up -> {
					name.append(up.getId());
					name.append(",");
				});
				name.substring(0, name.length());
				indeE.setTextFilePdf(name.toString());
				R<EkpVo> ekpVo = abutmentClient.sendEkpBatchPost(indeE);
				if (ekpVo.getCode() == HttpStatus.OK.value()) {
					indeE.setRelContractId(ekpVo.getData().getDoc_info());
					indeE.setEkpNumber(ekpVo.getData().getEkp_number());
					indeE.setContractStatus(ContractStatusEnum.APPROVAL.getKey().toString());
					contractFormInfoMapper.updateById(indeE);
				}
			} catch (Exception e) {
				indeE.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
				indeE.setYwlSettlement("????????????");
				indeE.setYwlBreachOfContract("3");
				contractFormInfoMapper.updateById(indeE);
				log.info(indeE.getContractName() + "????????????");
			}
		});
	}

	/**
	 * ???????????????????????? ??????????????????????????????  ???????????????
	 **/
	public void indeBatchSign(List<ContractFormInfoEntity> indeBatchSign) {
		indeBatchSign.forEach(inde -> {
			try {
				UploadFileEntity uploadFileEntity = new UploadFileEntity();
				List<File> files = new ArrayList<>();
				File fileBH;
				inde = this.makeContractN(inde);
				List<FileVO> fileVO = fileClient.getByIds(inde.getTextFile()).getData();
				fileBH = contractFileHandle(inde, fileVO.get(0));
				files.add(fileBH);
				uploadFileEntity.setFile(files);
				uploadFileEntity.setIsMerge("0");
				List<UploadFileVo> uploadFileVoList = abutmentClient.uploadFiles(uploadFileEntity).getData();
				inde.setTextFilePdf(uploadFileVoList.get(0).getId());
				MultipartFile multipartFile = null;
				try {
					//?????????mock??????
					multipartFile = new MockMultipartFile("file", fileBH.getName(),
						ContentType.MULTIPART.toString(), new FileInputStream(fileBH));
				} catch (IOException e) {
					e.printStackTrace();
				}
				R<FileVO> fileR = fileClient.save(multipartFile);
				inde.setOtherInformation(fileR.getData().getLink());
				R<EkpVo> ekpVo = abutmentClient.sendEkpBatchPost(inde);
				if (ekpVo.getCode() == HttpStatus.OK.value()) {
					inde.setRelContractId(ekpVo.getData().getDoc_info());
					inde.setEkpNumber(ekpVo.getData().getEkp_number());
					inde.setContractStatus(ContractStatusEnum.APPROVAL.getKey().toString());
					contractFormInfoMapper.updateById(inde);
				}
			} catch (Exception e) {
				inde.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
				inde.setYwlSettlement("????????????");
				inde.setYwlBreachOfContract("3");
				contractFormInfoMapper.updateById(inde);
				log.info(inde.getContractName() + "????????????");
			}
		});
	}

	/*???????????????????????????*/
	public File contractFileHandle(ContractFormInfoEntity indeE, FileVO file) {
		File filePDF;
		File fileBH = null;
		String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String newFileDoc = file.getLink();
		int suffixL = file.getName().length();
		String suffix = file.getName().substring(suffixL - 3, suffixL);
		String newFilePdf = ftlPath + file.getName().substring(0, file.getName().lastIndexOf(".")) + date + ".pdf";
		if ("pdf".equals(suffix)) {
			filePDF = new File(newFilePdf);
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(filePDF);
				fos.write(AsposeWordToPdfUtils.getUrlFileData(newFileDoc));
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			AsposeWordToPdfUtils.doc2pdf(newFileDoc, newFilePdf);
			filePDF = new File(newFilePdf);
		}
		InputStream in;
		try {
			in = new FileInputStream(filePDF);
			String fileId = AsposeWordToPdfUtils.addWaterMak(in, "????????????", filePDF.getName(), null);
			String url = AsposeWordToPdfUtils.downloadFile(fileId);
			FileOutputStream fos = null;
			fos = new FileOutputStream(filePDF);
			fos.write(AsposeWordToPdfUtils.getUrlFileData(url));
			fos.close();
			in.close();
			String BH = ftlPath + "BH-" + filePDF.getName();
			AsposeWordToPdfUtils.addWaterMark(filePDF.getPath(), BH, indeE.getContractNumber());
			fileBH = new File(BH);
			MultipartFile multipartFile = null;
			try {
				multipartFile = new MockMultipartFile("file", fileBH.getName(),
					ContentType.MULTIPART.toString(), new FileInputStream(fileBH));
			} catch (IOException e) {
				e.printStackTrace();
			}
			R<FileVO> fileV = fileClient.save(multipartFile);
			indeE.setOtherInformation(fileV.getData().getLink());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileBH;
	}

	/*********************************************************????????????????????????END****************************************************************/
	/*??????????????????*/
	@Override
	public ContractFormInfoEntity makeContractN(ContractFormInfoEntity entity) {
		ContractFormInfoEntity formInfoEntity = new ContractFormInfoEntity();
		BeanUtil.copy(entity, formInfoEntity);
		/*???????????? ??????*/
		List<ContractFormInfoEntity> list = this.selectByContractNumber(entity);
		log.info("??????????????????:{}", JsonUtil.toJson(list));
		//????????????
		final String[] FLCode = {null};
		R<List<DictBiz>> HTDL = bizClient.getList("HTDL");
		List<DictBiz> dataBiz = HTDL.getData();
		dataBiz.forEach(bz -> {
			if ((bz.getId().toString()).equals(entity.getContractBigCategory())) {
				FLCode[0] = bz.getRemark();
			}
		});
		log.info("????????????:{}", JsonUtil.toJson(FLCode));
		//????????????????????????
		final String[] GSCode = {null};
		R<List<DictBiz>> seal = bizClient.getList("application_seal");
		log.info("?????????application_seal:{}", JsonUtil.toJson(seal));
		if (entity.getContractSoure().equals(ContractTypeEnum.MULTI.getKey().toString())) {
			for (ContractSealEntity cs : entity.getContractSeal()) {
				if (entity.getSealName().equals(cs.getFdTaxno())) {
					GSCode[0] = cs.getFdFactno();
				}
			}
		} else {
			seal.getData().forEach(bz -> {
				if (bz.getDictValue().equals(entity.getSealName())) {
					GSCode[0] = bz.getRemark();
				}
			});
		}
		log.info("????????????????????????:{}", JsonUtil.toJson(GSCode));
		//???????????????????????????+1
		log.info("??????????????????:{}", list.size());
		if (list.size() > 0) {
			formInfoEntity.setContractNumber(redisCacheUtil.selectTaskNo(list.get(0).getContractNumber(), FLCode[0], GSCode[0]));
		} else {
			formInfoEntity.setContractNumber(redisCacheUtil.selectTaskNo("", FLCode[0], GSCode[0]));
		}
		return formInfoEntity;
	}

	/**
	 * ??????????????????
	 *
	 * @param json ????????????
	 */
	@Override
	public ContractFormInfoEntity templateDraft(ContractFormInfoEntity contractFormInfo, String json) {
		//???Json??????????????????
		List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
		for (TemplateFieldJsonEntity templateField : templateFieldList) {
			//???????????????????????????????????????
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_SELECT.equals(templateField.getComponentType())) {
				Object object = JSON.parseObject(templateField.getSecondSelectData(), Object.class);
				if (null != object) {
					templateField.setSecondSelectDataObject(object);
				}
			}
			//?????????????????????????????????
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_REQUORED.equals(templateField.getRequired())) {
				List<Object> objectList = JSON.parseArray(templateField.getRequiredData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setRequiredDataList(objectList);
				}
			}
			//???????????????????????????
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE_SMALL.contains(templateField.getComponentType())) {
				List<Object> objectList = JSON.parseArray(templateField.getDicData(), Object.class);
				if (CollectionUtil.isNotEmpty(objectList)) {
					templateField.setDicDataList(objectList);
				}
			}
			if (ContractFormInfoTemplateContract.COMPONENT_TYPE.contains(templateField.getComponentType())) {
				//*???????????????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_YWLANEWDISPLAY1.equals(templateField.getRelationCode())) {
					List<YwlANewDisplay1ResponseVO> ywlANewDisplay1List = JSON.parseArray(templateField.getTableData(), YwlANewDisplay1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(ywlANewDisplay1List)) {
						ywlANewDisplay1Service.saveBatchByRefId(contractFormInfo.getId(), ywlANewDisplay1List);
						List<YwlANewDisplay1ResponseVO> list = ywlANewDisplay1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*????????????????????????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_CGLCATEGORYSALESCONTRACTS1.equals(templateField.getRelationCode())) {
					List<CglCategorySalesContracts1ResponseVO> cglCategorySalesContracts1List = JSON.parseArray(templateField.getTableData(), CglCategorySalesContracts1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglCategorySalesContracts1List)) {
						cglCategorySalesContracts1Service.saveBatchByRefId(contractFormInfo.getId(), cglCategorySalesContracts1List);
						List<CglCategorySalesContracts1ResponseVO> list = cglCategorySalesContracts1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*???????????????????????????--?????????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_CGLTHESALESCONTRACT1.equals(templateField.getRelationCode())) {
					List<CglTheSalesContract1ResponseVO> cglTheSalesContract1List = JSON.parseArray(templateField.getTableData(), CglTheSalesContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglTheSalesContract1List)) {
						cglTheSalesContract1Service.saveBatchByRefId(contractFormInfo.getId(), cglTheSalesContract1List);
						List<CglTheSalesContract1ResponseVO> list = cglTheSalesContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????-??????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_CGLRAWMATERIALS1.equals(templateField.getRelationCode())) {
					List<CglRawMaterials1ResponseVO> cglRawMaterials1List = JSON.parseArray(templateField.getTableData(), CglRawMaterials1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglRawMaterials1List)) {
						cglRawMaterials1Service.saveBatchByRefId(contractFormInfo.getId(), cglRawMaterials1List);
						List<CglRawMaterials1ResponseVO> list = cglRawMaterials1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*????????????-????????????-?????????
				if (ContractFormInfoTemplateContract.CONTRACT_CGLLOWCOSTHARDWARE1.equals(templateField.getRelationCode())) {
					List<CglLowCostHardware1ResponseVO> cglLowCostHardware1List = JSON.parseArray(templateField.getTableData(), CglLowCostHardware1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglLowCostHardware1List)) {
						iCglLowCostHardware1Service.saveBatchByRefId(contractFormInfo.getId(), cglLowCostHardware1List);
						List<CglLowCostHardware1ResponseVO> list = iCglLowCostHardware1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*???????????????????????? ???????????????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlAdaptationContract1ResponseVO> mtlAdaptationContract1List = JSON.parseArray(templateField.getTableData(), MtlAdaptationContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAdaptationContract1List)) {
						mtlAdaptationContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlAdaptationContract1List);
						List<MtlAdaptationContract1ResponseVO> list = mtlAdaptationContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*???????????????????????? ?????????????????????
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlAdaptationContract2ResponseVO> mtlAdaptationContract1List = JSON.parseArray(templateField.getTableData(), MtlAdaptationContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAdaptationContract1List)) {
						mtlAdaptationContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlAdaptationContract1List);
						List<MtlAdaptationContract2ResponseVO> list = mtlAdaptationContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????????????? ???????????????????????????1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlShootingAndProductionContract1ResponseVO> mtlShootingAndProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlShootingAndProductionContract1List)) {
						mtlShootingAndProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlShootingAndProductionContract1List);
						List<MtlShootingAndProductionContract1ResponseVO> list = mtlShootingAndProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????????????? ???????????????????????????2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlShootingAndProductionContract2ResponseVO> mtlShootingAndProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlShootingAndProductionContract2List)) {
						mtlShootingAndProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlShootingAndProductionContract2List);
						List<MtlShootingAndProductionContract2ResponseVO> list = mtlShootingAndProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????????????? ???????????????????????????3
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT3.equals(templateField.getRelationCode())) {
					List<MtlShootingAndProductionContract3ResponseVO> mtlShootingAndProductionContract3List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract3ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlShootingAndProductionContract3List)) {
						mtlShootingAndProductionContract3Service.saveBatchByRefId(contractFormInfo.getId(), mtlShootingAndProductionContract3List);
						List<MtlShootingAndProductionContract3ResponseVO> list = mtlShootingAndProductionContract3Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????????????? ?????????
				if (ContractFormInfoTemplateContract.CONTRACT_SCLPROJECTOUTSOURCING1.equals(templateField.getRelationCode())) {
					List<SclProjectOutsourcing1ResponseVO> sclProsjectOutsourcing1List = JSON.parseArray(templateField.getTableData(), SclProjectOutsourcing1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclProsjectOutsourcing1List)) {
						sclProjectOutsourcing1Service.saveBatchByRefId(contractFormInfo.getId(), sclProsjectOutsourcing1List);
						List<SclProjectOutsourcing1ResponseVO> list = sclProjectOutsourcing1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????????????????????????????1
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT1.equals(templateField.getRelationCode())) {
					List<SclConstructionProject1ResponseVO> sclConstructionProject1List = JSON.parseArray(templateField.getTableData(), SclConstructionProject1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclConstructionProject1List)) {
						sclConstructionProject1Service.saveBatchByRefId(contractFormInfo.getId(), sclConstructionProject1List);
						List<SclConstructionProject1ResponseVO> list = sclConstructionProject1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????????????????????????????2
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT2.equals(templateField.getRelationCode())) {
					List<SclConstructionProject2ResponseVO> sclConstructionProject2List = JSON.parseArray(templateField.getTableData(), SclConstructionProject2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclConstructionProject2List)) {
						sclConstructionProject2Service.saveBatchByRefId(contractFormInfo.getId(), sclConstructionProject2List);
						List<SclConstructionProject2ResponseVO> list = sclConstructionProject2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????????????????????????????3
				if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT3.equals(templateField.getRelationCode())) {
					List<SclConstructionProject3ResponseVO> sclConstructionProject3List = JSON.parseArray(templateField.getTableData(), SclConstructionProject3ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclConstructionProject3List)) {
						sclConstructionProject3Service.saveBatchByRefId(contractFormInfo.getId(), sclConstructionProject3List);
						List<SclConstructionProject3ResponseVO> list = sclConstructionProject3Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????? ?????????1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlAudioProductionContract1ResponseVO> mtlAudioProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlAudioProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAudioProductionContract1List)) {
						mtlAudioProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlAudioProductionContract1List);
						List<MtlAudioProductionContract1ResponseVO> list = mtlAudioProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????? ?????????2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlAudioProductionContract2ResponseVO> mtlAudioProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlAudioProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAudioProductionContract2List)) {
						mtlAudioProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlAudioProductionContract2List);
						List<MtlAudioProductionContract2ResponseVO> list = mtlAudioProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLEDITEDTHECONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlEditedTheContract1ResponseVO> mtlEditedTheContract1List = JSON.parseArray(templateField.getTableData(), MtlEditedTheContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlEditedTheContract1List)) {
						mtlEditedTheContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlEditedTheContract1List);
						List<MtlEditedTheContract1ResponseVO> list = mtlEditedTheContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*???????????????????????????1
				if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlVideoProductionContract1ResponseVO> mtlVideoProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlVideoProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract1List)) {
						mtlVideoProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract1List);
						List<MtlVideoProductionContract1ResponseVO> list = mtlVideoProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*???????????????????????????2
				if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlVideoProductionContract2ResponseVO> mtlVideoProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlVideoProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract2List)) {
						mtlVideoProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract2List);
						List<MtlVideoProductionContract2ResponseVO> list = mtlVideoProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//MMHT_06
				//*?????????????????????????????????????????????1
				if (ContractFormInfoTemplateContract.CONTRACT_CGLSALESCONTRACT1ENTITY.equals(templateField.getRelationCode())) {
					List<CglSalesContract1ResponseVO> cglSalesContract1List = JSON.parseArray(templateField.getTableData(), CglSalesContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglSalesContract1List)) {
						cglSalesContract1Service.saveBatchByRefId(contractFormInfo.getId(), cglSalesContract1List);
						List<CglSalesContract1ResponseVO> list = cglSalesContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*??????????????????????????????????????????1???
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtbProductionContract1ResponseVO> mtlVideoProductionContract1List = JSON.parseArray(templateField.getTableData(), MtbProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract1List)) {
						mtbProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract1List);
						List<MtbProductionContract1ResponseVO> list = mtbProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*??????????????????????????????????????????2???
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtbProductionContract2ResponseVO> mtlVideoProductionContract2List = JSON.parseArray(templateField.getTableData(), MtbProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract2List)) {
						mtbProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract2List);
						List<MtbProductionContract2ResponseVO> list = mtbProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*??????????????????????????????????????????3???
				if (ContractFormInfoTemplateContract.CONTRACT_MTBPRODUCTIONCONTRACT3.equals(templateField.getRelationCode())) {
					List<MtbProductionContract3ResponseVO> mtlVideoProductionContract3List = JSON.parseArray(templateField.getTableData(), MtbProductionContract3ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlVideoProductionContract3List)) {
						mtbProductionContract3Service.saveBatchByRefId(contractFormInfo.getId(), mtlVideoProductionContract3List);
						List<MtbProductionContract3ResponseVO> list = mtbProductionContract3Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*????????????????????????(?????????1???
				if (ContractFormInfoTemplateContract.CONTRACT_SCLEQUIOMENTMAINTENANCE1.equals(templateField.getRelationCode())) {
					List<SclEquipmentMaintenance1ResponseVO> sclEquipmentMaintenance1List = JSON.parseArray(templateField.getTableData(), SclEquipmentMaintenance1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclEquipmentMaintenance1List)) {
						sclEquipmentMaintenance1Service.saveBatchByRefId(contractFormInfo.getId(), sclEquipmentMaintenance1List);
						List<SclEquipmentMaintenance1ResponseVO> list = sclEquipmentMaintenance1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????_???????????????(?????????1???
				if (ContractFormInfoTemplateContract.CONTRACT_CGLPROOFINGCONTRACT1.equals(templateField.getRelationCode())) {
					List<CglProofingContract1ResponseVO> CglProofingContract1List = JSON.parseArray(templateField.getTableData(), CglProofingContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(CglProofingContract1List)) {
						cglProofingContract1Service.saveBatchByRefId(contractFormInfo.getId(), CglProofingContract1List);
						List<CglProofingContract1ResponseVO> list = cglProofingContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*??????????????????????????????(?????????1???
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT1.equals(templateField.getRelationCode())) {
					List<ProductOutServiceContract1ResponseVO> productOutServiceContract1ResponseVOList = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(productOutServiceContract1ResponseVOList)) {
						productOutServiceContract1Service.saveBatchByRefId(contractFormInfo.getId(), productOutServiceContract1ResponseVOList);
						List<ProductOutServiceContract1ResponseVO> list = productOutServiceContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*??????????????????????????????(?????????2???
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT2.equals(templateField.getRelationCode())) {
					List<ProductOutServiceContract2ResponseVO> productOutServiceContract2ResponseVOList = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(productOutServiceContract2ResponseVOList)) {
						productOutServiceContract2Service.saveBatchByRefId(contractFormInfo.getId(), productOutServiceContract2ResponseVOList);
						List<ProductOutServiceContract2ResponseVO> list = productOutServiceContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*??????????????????????????????(?????????3???
				if (ContractFormInfoTemplateContract.CONTRACT_PRODUCTOUTSERVICECONTRACT3.equals(templateField.getRelationCode())) {
					List<ProductOutServiceContract3ResponseVO> productOutServiceContract3ResponseVOList = JSON.parseArray(templateField.getTableData(), ProductOutServiceContract3ResponseVO.class);
					if (CollectionUtil.isNotEmpty(productOutServiceContract3ResponseVOList)) {
						productOutServiceContract3Service.saveBatchByRefId(contractFormInfo.getId(), productOutServiceContract3ResponseVOList);
						List<ProductOutServiceContract3ResponseVO> list = productOutServiceContract3Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*??????????????????(?????????1???
				if (ContractFormInfoTemplateContract.CONTRACT_BUSSERVICECONTRACT1.equals(templateField.getRelationCode())) {
					List<BusServiceContract1ResponseVO> busServiceContract1ResponseVOList = JSON.parseArray(templateField.getTableData(), BusServiceContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(busServiceContract1ResponseVOList)) {
						busServiceContract1Service.saveBatchByRefId(contractFormInfo.getId(), busServiceContract1ResponseVOList);
						List<BusServiceContract1ResponseVO> list = busServiceContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*?????????????????????+?????????(?????????1???
				if (ContractFormInfoTemplateContract.CONTRAT_IMTBMARKETRESEARCHCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtbMarketResearchContract1ResponseVO> mtbMarketResearchContract1ResponseVOList = JSON.parseArray(templateField.getTableData(), MtbMarketResearchContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtbMarketResearchContract1ResponseVOList)) {
						iMtbMarketResearchContract1Service.saveBatchByRefId(contractFormInfo.getId(), mtbMarketResearchContract1ResponseVOList);
						List<MtbMarketResearchContract1ResponseVO> list = iMtbMarketResearchContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				/**
				 * ?????????  ????????????
				 * TAG-21-05-03
				 * 	??????????????????????????? ?????????1
				 */
				if (ContractFormInfoTemplateContract.CONTRAT_CONTRACTMMHTXXPF1.equals(templateField.getRelationCode())) {
					List<ContractMmhtxxpf1ResponseVO> contractMmhtxxpf1ResponseVOList = JSON.parseArray(templateField.getTableData(), ContractMmhtxxpf1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(contractMmhtxxpf1ResponseVOList)) {
						iContractMmhtxxpf1Service.saveBatchByRefId(contractFormInfo.getId(), contractMmhtxxpf1ResponseVOList);
						List<ContractMmhtxxpf1ResponseVO> list = iContractMmhtxxpf1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				/**
				 * TAG-21-05-24
				 * 	???????????????????????????????????? ?????????1
				 */
				if (ContractFormInfoTemplateContract.CONTRACT_CONTRACTWBHT1.equals(templateField.getRelationCode())) {
					List<ContractWbht1ResponseVO> contractWbht1ResponseVOS = JSON.parseArray(templateField.getTableData(), ContractWbht1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(contractWbht1ResponseVOS)) {
						iContractWbht1Service.saveBatchByRefId(contractFormInfo.getId(), contractWbht1ResponseVOS);
						List<ContractWbht1ResponseVO> list = iContractWbht1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
			}
		}
		contractFormInfo.setJson(toJSONString(templateFieldList));
		return contractFormInfo;
	}

	//??????????????????????????????
	public String toJSONString(List<TemplateFieldJsonEntity> templateFieldList) {
		//??????????????????json?????????
		String json = JSON.toJSONString(templateFieldList);
		//?????????json???????????????????????????
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
			//????????????????????????
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
	 * ??????????????????
	 *
	 * @return list
	 */
	@Override
	public List<ContractFormInfoEntity> getAmountList() {
		return contractFormInfoMapper.getAmountList();
	}

	/**
	 * ??????????????????
	 *
	 * @return list
	 */
	@Override
	public List<ContractFormInfoEntity> getNumList() {
		return contractFormInfoMapper.getNumList();
	}

	/**
	 * ????????????????????????????????????
	 *
	 * @return list
	 */
	@Override
	public List<ContractFormInfoEntity> getChooseList() {
		return contractFormInfoMapper.getChooseList();
	}

	@Override
	public void batchDraftingImport(ContractImportBatchDraftRequest contractImportBatchDraftRequest) {
		//??????????????????
		List<Long> contractIds = new ArrayList<>();
		ContractAccordingEntity contractAccordingEntity = accordingService.selectAccordingByCode(contractImportBatchDraftRequest.getAccordingCode());
		//????????????????????????
		List<ContractImportBatchDraftExcel> contractImportBatchDraftExcels = contractImportBatchDraftRequest.getContractExcelList();
		//???????????????????????????db??????
		if (Func.isNotEmpty(contractImportBatchDraftExcels)) {
			List<ContractFormInfoEntity> contractFormInfoEntityList = new ArrayList<>();
			for (ContractImportBatchDraftExcel contractImportBatchDraftExcel : contractImportBatchDraftExcels) {
				contractImportBatchDraftExcel = setDictValueByBatchDraftExcel(contractImportBatchDraftExcel);
				ContractFormInfoEntity contractFormInfoEntity = ContractFormInfoWrapper.build().createEntityByBatchDraftExcel(contractImportBatchDraftExcel);
				contractFormInfoEntity.setContractListName("");
				contractFormInfoEntityList.add(contractFormInfoEntity);
				/* excel?????????????????????????????????????????????????????????.?????????????????????????????????excel????????????????????? */
				User user = getUserByCode(contractFormInfoEntity.getPersonCodeContract());
				contractFormInfoEntity.setPersonContract(Func.isEmpty(user) ? "" : user.getRealName());
				//???????????????,??????
				List<ContractCounterpartEntity> counterpartEntityList = contractCounterpartService.saveByBatchDraftExcel(contractImportBatchDraftExcel.getContractCounterpartImportBatchDraftExcels(), contractFormInfoEntity.getId());
				//???????????????,??????
				List<ContractBondEntity> contractBondEntityList = contractBondService.saveByBatchDraftExcels(contractImportBatchDraftExcel.getContractBondImportBatchDraftExcels(), contractFormInfoEntity.getId());
				//??????-???????????????,??????
				List<PerCollectPayEntity> perCollectPayEntityList = perCollectPayService.saveByBatchDraftExcels(contractImportBatchDraftExcel.getPerCollectPayImportBatchDraftExcels(), contractFormInfoEntity.getId());
				//????????????id??????
				contractIds.add(contractFormInfoEntity.getId());
			}
			//????????????????????????
			accordingService.saveByBatchDraft(contractAccordingEntity.getId(), contractIds);
			saveBatch(contractFormInfoEntityList);
		}
	}


	@Override
	public void batchDraftingImportUp(ContractFormInfoRequestVO contractFormInfo) {
		//??????????????????
		ContractFormInfoEntity contractFormInfoEntity = BeanUtil.copy(contractFormInfo, ContractFormInfoEntity.class);
		R<ContractFormInfoEntity> r = batchDraftingHandleSignature(contractFormInfoEntity);
		contractFormInfoEntity = r.getData();
		//??????????????????
		contractFormInfoEntity.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
		updateById(contractFormInfoEntity);
		//?????????????????????
		List<ContractCounterpartEntity> contractCouterpartEntities = contractFormInfo.getCounterpart();
		contractCounterpartService.saveSettingListByContractInfoId(contractFormInfoEntity.getId(), contractCouterpartEntities);
		//?????????????????????
		List<ContractBondEntity> contractBondEntities = contractFormInfo.getContractBond();
		contractBondService.saveListByContractInfoId(contractBondEntities, contractFormInfoEntity.getId());
		//????????????-???????????????
		List<PerCollectPayRequestVO> perCollectPayList = contractFormInfo.getPerCollectPayList();
		perCollectPayService.addListData(perCollectPayList, contractFormInfoEntity.getId());
		//????????????-??????????????????
		List<PerServiceContentRequestVO> perServiceContentList = contractFormInfo.getPerServiceContentList();
		perServiceContentService.addPerData(Func.isEmpty(perServiceContentList) ? null : perServiceContentList.get(0), contractFormInfoEntity.getId());
	}

	@Override
	public R<ContractFormInfoEntity> batchDraftingHandleSignature(ContractFormInfoEntity contractFormInfoEntity) {
		//??????-????????????
		FileVO filevo = new FileVO();
		if (contractFormInfoEntity.getContractSoure().equals(ContractTypeEnum.BATCH_SINGLE.getKey().toString())) {
			if (Func.isEmpty(contractFormInfoEntity.getFilePdf())) {
				ContractTemplateResponseVO contractTemplateResponseVO = templateService.getById(contractFormInfoEntity.getContractTemplateId());
				if (null != contractTemplateResponseVO) {
					R<TemplateEntity> r = sysClient.getTemplateByCode(contractTemplateResponseVO.getTemplateCode());
					TemplateEntity templateEntity = r.getData();
					List<TemplateFieldEntity> templateFieldList = JSON.parseArray(templateEntity.getJson(), TemplateFieldEntity.class);
					JSONObject j = new JSONObject();
					for (TemplateFieldEntity templateField : templateFieldList) {
						j.put(templateField.getFieldName(), templateField.getFieldValue());
					}
					filevo = templateExportUntil.templateSave(contractFormInfoEntity, BeanUtil.copy(templateEntity, TemplateRequestVO.class), contractFormInfoEntity.getJson(), j);
					contractFormInfoEntity.setContractStatus("20");
					contractFormInfoEntity.setTextFile(filevo.getId() + ",");
					contractFormInfoEntity.setTextFilePdf(filevo.getId() + ",");
					contractFormInfoEntity.setContractStatus(templateEntity.getBean());
					contractFormInfoEntity.setFilePdf(filevo.getDomain());
				}
			}
		}
		//??????????????????-??????
		if (contractFormInfoEntity.getContractSoure().equals(ContractTypeEnum.BATCH_SINGLE.getKey().toString())) {
			contractFormInfoEntity = SingleSign(R.data(contractFormInfoEntity)).getData();
		}
		//??????????????????-??????
		if (contractFormInfoEntity.getContractSoure().equals(ContractTypeEnum.BATCH_MODEL.getKey().toString())) {
			contractFormInfoEntity = SingleSignE(R.data(contractFormInfoEntity)).getData();
		}
		//????????????????????????(???????????????)
		R singleSignR = singleSignIsNot(BeanUtil.copy(contractFormInfoEntity, ContractFormInfoRequestVO.class), filevo);
		contractFormInfoEntity.setYwlSettlement(singleSignR.getMsg());
		contractFormInfoEntity.setYwlBreachOfContract(String.valueOf(singleSignR.getCode()));
		return R.data(contractFormInfoEntity);
	}

	@Override
	public R middlegroundPushData(ContractFormInfoEntity contractFormInfoEntity) {

		return null;
	}

	/**
	 * ????????????????????????
	 *
	 * @return list
	 */
	@Override
	public List<ContractFormInfoEntity> selectByContractNumber(ContractFormInfoEntity entity) {
		return contractFormInfoMapper.selectByContractNumber(entity);
	}

	/**
	 * ??????????????????
	 *
	 * @return list
	 */
	@Override
	public R singleSignIsNot(ContractFormInfoRequestVO contractFormInfo, FileVO fileVO) {
		Map<Object, Boolean> mapE = new HashMap<>();
		Map<Object, Boolean> mapS = new HashMap<>();
		for (ContractCounterpartEntity counterpart : contractFormInfo.getCounterpart()) {
			// ????????????????????????????????????
			CompanyInfoEntity companyInfoEntity = new CompanyInfoEntity();
			companyInfoEntity.setQueryType("1");
			// ??????????????????  ?????????????????????  ????????????
			companyInfoEntity.setOrganCode(counterpart.getUnifiedSocialCreditCode());
			// ?????????null??????,?????????????????????,??????????????????available???1??????????????????,0?????????
			CompanyInfoVo companyInfoVo = abutmentClient.queryCompanyInfo(companyInfoEntity).getData();
			log.info("TAG01:??????????????????????????????????????????" + JsonUtil.toJson(companyInfoVo));
			if (null == companyInfoVo) {
				companyInfoVo = abutmentClient.queryCompanyInfo(companyInfoEntity).getData();
				log.info("TAG02:??????????????????????????????????????????" + JsonUtil.toJson(companyInfoVo));
			}
			if (null != companyInfoVo) {
				boolean code = "0".equals(companyInfoVo.getOrganCode());
				boolean available = "1".equals(companyInfoVo.getCompany().getAvailable());
				boolean contractForm1 = "1".equals(contractFormInfo.getContractForm());
				boolean contractForm2 = "2".equals(contractFormInfo.getContractForm());
				//?????????0????????????????????????
				if ((!code || !available) && contractForm1) {
					mapS.put(counterpart.getName(), false);
//					return R.data(1, counterpart.getName(), counterpart.getName() + "??????????????????????????????????????????");
				}
				if (code && available && contractForm2) {
					mapE.put(counterpart.getName(), true);
//					return R.data(1, counterpart.getName(), counterpart.getName() + "??????????????????????????????????????????-????????????");
				}
			}
		}
		if (Func.isNotEmpty(contractFormInfo.getContractSeal())) {
			for (ContractSealEntity seal : contractFormInfo.getContractSeal()) {
				// ????????????????????????????????????
				CompanyInfoEntity companyInfoEntity = new CompanyInfoEntity();
				companyInfoEntity.setQueryType("1");
				// ??????????????????  ?????????????????????  ????????????
				companyInfoEntity.setOrganCode(seal.getFdTaxno());
				// ?????????null??????,?????????????????????,??????????????????available???1??????????????????,0?????????
				CompanyInfoVo companyInfoVo = abutmentClient.queryCompanyInfo(companyInfoEntity).getData();
				log.info("TAG01:??????????????????????????????????????????" + JsonUtil.toJson(companyInfoVo));
				if (null == companyInfoVo) {
					companyInfoVo = abutmentClient.queryCompanyInfo(companyInfoEntity).getData();
					log.info("TAG02:??????????????????????????????????????????" + JsonUtil.toJson(companyInfoVo));
				}
				if (null != companyInfoVo) {
					boolean code = "0".equals(companyInfoVo.getOrganCode());
					boolean available = "1".equals(companyInfoVo.getCompany().getAvailable());
					boolean contractForm1 = "1".equals(contractFormInfo.getContractForm());
					boolean contractForm2 = "2".equals(contractFormInfo.getContractForm());
					//?????????0????????????????????????
					if ((!code || !available) && contractForm1) {
						mapS.put(seal.getFdFactname(), false);
//						return R.data(1, seal.getFdFactname(), seal.getFdFactname() + ",??????????????????????????????????????????");
					}
					if (code && available && contractForm2) {
						mapE.put(seal.getFdFactname(), true);
//						return R.data(1, seal.getFdFactname(), seal.getFdFactname() + ",???????????????????????????????????????-????????????");
					}
				}
			}
		}
		if (contractFormInfo.getContractForm().equals(ContractTypeEnum.ELECTRONIC_CONTRACT_WE.getKey().toString())) {
			List<Object> mapSList = new ArrayList<>();
			boolean status = Func.isNotEmpty(mapS);
			if (status) {
				Iterator iter = mapS.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					if (entry.getKey() != null) {
						mapSList.add(entry.getKey());
					}
				}
				return R.data(1, mapSList, JsonUtil.toJson(mapSList) + "??????????????????????????????????????????");
			}
		}
		if (contractFormInfo.getContractForm().equals(ContractTypeEnum.ENTITY_CONTRACT_WE.getKey().toString())) {
			if (mapE.size() == (contractFormInfo.getCounterpart().size() + (Func.isNull(contractFormInfo.getContractSeal())?0:contractFormInfo.getContractSeal().size()))) {
				List<Object> mapEList = new ArrayList<>();
				boolean status = Func.isNotEmpty(mapE);
				if (status) {
					Iterator iter = mapE.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Map.Entry) iter.next();
						if (entry.getKey() != null) {
							mapEList.add(entry.getKey());
						}
					}
					return R.data(1, mapEList, JsonUtil.toJson(mapEList) + ",???????????????????????????????????????-????????????");
				}
			}
		}
		String newFileDoc = "";
		String newFilePdf = "";
		String suffix = "";
		File filePDF = null;
		//doc??????pdf
		if (!Func.isEmpty(fileVO)) {
			newFileDoc = fileVO.getLink();
			int index = fileVO.getName().lastIndexOf(".");
			suffix = fileVO.getName().substring(index + 1);
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = df.format(new Date());
			//???????????????pdf?????????pdf?????????????????????
			if (!"pdf".equals(suffix)) {
				//??????????????????
				newFilePdf = ftlPath + fileVO.getName().substring(0, index) + date + ".pdf";
				AsposeWordToPdfUtils.doc2pdf(newFileDoc, newFilePdf);
				filePDF = new File(newFilePdf);
			} else {
				filePDF = new File(ftlPath + fileVO.getName().substring(0, index) + date + ".pdf");
				//?????????????????????
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
			if ("20".equals(contractFormInfo.getContractSoure())) {
				return judgeKeywordsMulti(filePDF);
			} else {
				return judgeKeywordsIndeOrTemplate(filePDF);
			}
		} else {
			return R.data(2, "??????????????????", "??????????????????");
		}
	}

	/**
	 * ?????????????????????????????????
	 *
	 * @param filePDF
	 * @return org.springblade.core.tool.api.R
	 * @author jitwxs
	 * @date 2021/7/2 16:46
	 */
	public R judgeKeywordsMulti(File filePDF) {
		InputStream input = null;
		PDDocument document = null;
		try {
			input = new FileInputStream(filePDF);
			// ????????????PDF???????????????
			PDFParser parser = new PDFParser(input);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			int az = text.indexOf("??????????????????");
			int bz = text.indexOf("??????????????????");
			int ey = text.indexOf("??????????????????");
			int ay = text.indexOf("??????(??????)");
			int by = text.indexOf("??????(??????)");
			int ez = text.indexOf("??????(??????)");
			int cz = text.indexOf("??????(?????????");
			int cy = text.indexOf("??????(?????????");
			int ex = text.indexOf("??????(?????????");
			int dz = text.indexOf("???????????????)");
			int dy = text.indexOf("???????????????)");
			int eo = text.indexOf("???????????????)");
			if ((-1 == az || -1 == bz || -1 == ey) && (-1 == ay || -1 == by || -1 == ez) && (-1 == cz || -1 == cy || -1 == ex) && (-1 == dz || -1 == dy || -1 == eo) &&
				(-1 == az || -1 == by || -1 == ez) && (-1 == az || -1 == cy || -1 == ez) &&
				(-1 == az || -1 == dy || -1 == ez) && (-1 == bz || -1 == ay || -1 == ez) &&
				(-1 == bz || -1 == cz || -1 == ez) && (-1 == bz || -1 == dz || -1 == ez) &&
				(-1 == ay || -1 == cy || -1 == ez) && (-1 == ay || -1 == dy || -1 == ez) &&
				(-1 == by || -1 == cz || -1 == ez) && (-1 == by || -1 == dz || -1 == ez) &&
				(-1 == cz || -1 == dy || -1 == ez) && (-1 == cy || -1 == dz || -1 == ez) &&
				(-1 == az || -1 == by || -1 == ey) && (-1 == az || -1 == cy || -1 == ey) &&
				(-1 == az || -1 == dy || -1 == ey) && (-1 == bz || -1 == ay || -1 == ey) &&
				(-1 == bz || -1 == cz || -1 == ey) && (-1 == bz || -1 == dz || -1 == ey) &&
				(-1 == ay || -1 == cy || -1 == ey) && (-1 == ay || -1 == dy || -1 == ey) &&
				(-1 == by || -1 == cz || -1 == ey) && (-1 == by || -1 == dz || -1 == ey) &&
				(-1 == cz || -1 == dy || -1 == ey) && (-1 == cy || -1 == dz || -1 == ey) &&
				(-1 == az || -1 == by || -1 == ex) && (-1 == az || -1 == cy || -1 == ex) &&
				(-1 == az || -1 == dy || -1 == ex) && (-1 == bz || -1 == ay || -1 == ex) &&
				(-1 == bz || -1 == cz || -1 == ex) && (-1 == bz || -1 == dz || -1 == ex) &&
				(-1 == ay || -1 == cy || -1 == ex) && (-1 == ay || -1 == dy || -1 == ex) &&
				(-1 == by || -1 == cz || -1 == ex) && (-1 == by || -1 == dz || -1 == ex) &&
				(-1 == cz || -1 == dy || -1 == ex) && (-1 == cy || -1 == dz || -1 == ex) &&
				(-1 == az || -1 == by || -1 == eo) && (-1 == az || -1 == cy || -1 == eo) &&
				(-1 == az || -1 == dy || -1 == eo) && (-1 == bz || -1 == ay || -1 == eo) &&
				(-1 == bz || -1 == cz || -1 == eo) && (-1 == bz || -1 == dz || -1 == eo) &&
				(-1 == ay || -1 == cy || -1 == eo) && (-1 == ay || -1 == dy || -1 == eo) &&
				(-1 == by || -1 == cz || -1 == eo) && (-1 == by || -1 == dz || -1 == eo) &&
				(-1 == cz || -1 == dy || -1 == eo) && (-1 == cy || -1 == dz || -1 == eo)) {
				return R.data(2, "?????????????????????", "?????????????????????");
			}
			if ((-1 != cz && -1 != cy) || (-1 != dz && -1 != dy) ||
				(-1 != az && -1 != by) || (-1 != az && -1 != cy) || (-1 != az && -1 != dy) ||
				(-1 != bz && -1 != ay) || (-1 != bz && -1 != cz) || (-1 != bz && -1 != dz) ||
				(-1 != ay && -1 != cy) || (-1 != ay && -1 != dy) ||
				(-1 != by && -1 != cz) || (-1 != by && -1 != dz) ||
				(-1 != cz && -1 != dy) || (-1 != cy && -1 != dz)) {
				return R.data(2, "?????????" + "()" + "????????????????????????,?????????????????????", "?????????" + "()" + "????????????????????????,?????????????????????");
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
		return R.data(0, "??????", "??????");
	}

	/**
	 * ????????????  ????????????-???????????????????????????
	 *
	 * @param filePDF
	 * @return org.springblade.core.tool.api.R
	 * @author jitwxs
	 * @date 2021/7/2 16:53
	 */
	public R judgeKeywordsIndeOrTemplate(File filePDF) {
		InputStream input = null;
		PDDocument document = null;
		try {
			input = new FileInputStream(filePDF);
			// ????????????PDF???????????????
			PDFParser parser = new PDFParser(input);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			int az = text.indexOf("??????????????????");
			int bz = text.indexOf("??????????????????");
			int ay = text.indexOf("??????(??????)");
			int by = text.indexOf("??????(??????)");
			int cz = text.indexOf("??????(?????????");
			int cy = text.indexOf("??????(?????????");
			int dz = text.indexOf("???????????????)");
			int dy = text.indexOf("???????????????)");
			if ((-1 == az || -1 == bz) && (-1 == ay || -1 == by) && (-1 == cz || -1 == cy) && (-1 == dz || -1 == dy) &&
				(-1 == az || -1 == by) && (-1 == az || -1 == cy) && (-1 == az || -1 == dy) &&
				(-1 == bz || -1 == ay) && (-1 == bz || -1 == cz) && (-1 == bz || -1 == dz) &&
				(-1 == ay || -1 == cy) && (-1 == ay || -1 == dy) &&
				(-1 == by || -1 == cz) && (-1 == by || -1 == dz) &&
				(-1 == cz || -1 == dy) && (-1 == cy || -1 == dz)) {
				return R.data(2, "?????????????????????", "?????????????????????");
			}
			if ((-1 != cz && -1 != cy) || (-1 != dz && -1 != dy) ||
				(-1 != az && -1 != by) || (-1 != az && -1 != cy) || (-1 != az && -1 != dy) ||
				(-1 != bz && -1 != ay) || (-1 != bz && -1 != cz) || (-1 != bz && -1 != dz) ||
				(-1 != ay && -1 != cy) || (-1 != ay && -1 != dy) ||
				(-1 != by && -1 != cz) || (-1 != by && -1 != dz) ||
				(-1 != cz && -1 != dy) || (-1 != cy && -1 != dz)) {
				return R.data(2, "?????????" + "()" + "????????????????????????,?????????????????????", "?????????" + "()" + "????????????????????????,?????????????????????");
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
		return R.data(0, "??????", "??????");
	}

	@Override
	public ContractFormInfoEntity selectByChangeId(Long id) {
		return contractFormInfoMapper.selectByChangeId(id);
	}

	@Override
	public ContractFormInfoEntity saveContractSupplementaryRecording(ContractFormInfoRequestVO contractFormInfo) {
		ContractSigningEntity signing = new ContractSigningEntity();
		ContractSealUsingInfoEntity sealUsingInfoEntity = new ContractSealUsingInfoEntity();
		if (ContractTypeEnum.ELECTRONIC_CONTRACT_WE.getKey().toString().equals(contractFormInfo.getContractForm())) {
			//????????????ID
			signing.setContractId(contractFormInfo.getId());
			sealUsingInfoEntity.setRefContractId(contractFormInfo.getId());
			//???????????? ????????????
			signing.setSignDate(new Date());
			sealUsingInfoEntity.setSignTime(new Date());
			//??????????????????
			signing.setContractStartTime(contractFormInfo.getStartingTime());
			signing.setContractEndTime(contractFormInfo.getEndTime());
			//??????????????? ??????
			signing.setManager(contractFormInfo.getCreateUserName());
			signing.setManageUnit(contractFormInfo.getCreateDeptName());
			sealUsingInfoEntity.setSignPerson(contractFormInfo.getPersonContract());
			sealUsingInfoEntity.setManager(contractFormInfo.getCreateUserName());
			sealUsingInfoEntity.setManageDept(contractFormInfo.getCreateDeptName());
			sealUsingInfoEntity.setManageUnit(contractFormInfo.getCreateDeptName());
			//??????
			signing.setRemark("????????????");
			sealUsingInfoEntity.setSignRemark("????????????");
			//????????????
			signing.setSubmissionType(" ");
			//?????????
			signing.setAddressee(" ");
			//?????????????????????
			signing.setTextFiles(contractFormInfo.getTextFile());
			sealUsingInfoMapper.insert(sealUsingInfoEntity);
			signingMapper.insert(signing);
			//?????????  ????????????
			contractFormInfo.setFilePerson(contractFormInfo.getPersonContract());
			contractFormInfo.setFileTime(new Date());
			contractFormInfo.setContractStatus(ContractStatusEnum.SIGING.getKey().toString());
			contractFormInfo = ContractFormInfoWrapper.build().entityQV(makeContractN(contractFormInfo));
		}
		return contractFormInfo;
	}


	private User getUserByCode(String code) {
		if (Func.isNotEmpty(code)) {
			R<User> user = userClient.userByCode("000000", code);
			if (user.isSuccess()) {
				return Func.isEmpty(user.getData()) ? null : user.getData();
			}
		}
		return null;
	}


	/**
	 * ???????????????????????????value?????????key???id
	 *
	 * @param contractImportBatchDraftExcel
	 * @return ContractImportBatchDraftExcel
	 */
	private ContractImportBatchDraftExcel setDictValueByBatchDraftExcel(ContractImportBatchDraftExcel contractImportBatchDraftExcel) {
		if (Func.isNotEmpty(contractImportBatchDraftExcel)) {
			//????????????
			contractImportBatchDraftExcel.setContractPeriod(this.bizClient.getDictByCodeValue("contract_period",
				contractImportBatchDraftExcel.getContractPeriod(), false).getData());
			//????????????
			contractImportBatchDraftExcel.setContractForm(this.bizClient.getDictByCodeValue("contract_form",
				contractImportBatchDraftExcel.getContractForm(), false).getData());
			//??????
			contractImportBatchDraftExcel.setCurrencyCategory(this.bizClient.getDictByCodeValue("bz",
				contractImportBatchDraftExcel.getCurrencyCategory(), false).getData());
			//?????????
			contractImportBatchDraftExcel.setColPayType(this.bizClient.getDictByCodeValue("col_pay_term",
				contractImportBatchDraftExcel.getColPayType(), true).getData());
			//???????????????
			contractImportBatchDraftExcel.setColPayTerm(this.bizClient.getDictByCodeValue("col_pay_term",
				contractImportBatchDraftExcel.getColPayTerm(), true).getData());
			//??????????????????
			contractImportBatchDraftExcel.setExtension(this.bizClient.getDictByCodeValue("yes_no",
				contractImportBatchDraftExcel.getExtension(), false).getData());
		}
		return contractImportBatchDraftExcel;
	}

	public Contract entityToMiddlegroundContract(ContractFormInfoEntity contractFormInfoEntity) {
		if (null == contractFormInfoEntity) {
			return null;
		}
		//????????????
		Contract contract = new Contract();
		contract.setContract_typr1(contractFormInfoEntity.getContractBigCategory());
		contract.setContract_typr2(contractFormInfoEntity.getContractSmallCategory());
		contract.setB_data(contractFormInfoEntity.getStartingTime());
		contract.setE_data(contractFormInfoEntity.getEndTime());
		contract.setC_apple_date(contractFormInfoEntity.getCreateTime());
		contract.setC_seal_date(null);
		contract.setC_audit_date(null);
		contract.setNo(contractFormInfoEntity.getContractNumber());
		//???????????????
		List<Affiliate> affiliateList = new ArrayList<>();
		if (Func.isNotEmpty(contractFormInfoEntity.getCounterpart())) {
			contractFormInfoEntity.getCounterpart().forEach(contractCounterpartEntity -> {
				Affiliate affiliate = new Affiliate();
				affiliate.setVend_orgcode(contractCounterpartEntity.getUnifiedSocialCreditCode());
				affiliate.setVend_no(contractCounterpartEntity.getOrganizationCode());
				affiliate.setVend_name(contractCounterpartEntity.getName());
				affiliateList.add(affiliate);
			});
		}
		contract.setAffiliates(affiliateList);
		//?????????
		PayCondition payCondition = new PayCondition();
		payCondition.setPay_method(contractFormInfoEntity.getColPayTerm());
		List<PayDetail> payDetails = new ArrayList<>();
		if (Func.isNotEmpty(contractFormInfoEntity.getCollection())) {
			contractFormInfoEntity.getCollection().forEach(collectionEntity -> {
				payCondition.setPeriod_num(collectionEntity.getPeriodNum());
				PayDetail payDetail = new PayDetail();
				payDetail.setPay_condition(collectionEntity.getPayCondition());
				payDetail.setPay_amount(Func.isNotEmpty(collectionEntity.getPayAmount()) ? new BigDecimal(collectionEntity.getPayAmount().toString()) : new BigDecimal(0L));
				payDetail.setPay_date(null);
				payDetail.setAccount_period(collectionEntity.getDays());
				payDetail.setIs_receipt(collectionEntity.getIsReceipt());
				payDetail.setPer(collectionEntity.getPayPer());
				payDetail.setPeriod_idx(collectionEntity.getPeriodIdx());
				payDetails.add(payDetail);
			});
		}
		payCondition.setDetail(payDetails);
		contract.setPay_condition(payCondition);
		//????????????
		BankArea bankArea = new BankArea();
		bankArea.setBank_code(contractFormInfoEntity.getBankCode());
		bankArea.setBank_account_code(contractFormInfoEntity.getBankAccountCode());
		bankArea.setBank_account_name(contractFormInfoEntity.getBankAccountName());
		contract.setBank_area(bankArea);
		//????????????
		List<ContractBody> contractBodyList = templateService.templateToMiddlegroundContractBody(contractFormInfoEntity.getContractTemplateId(), contractFormInfoEntity.getJson());
		contract.setBody(contractBodyList);
		return contract;
	}

}
