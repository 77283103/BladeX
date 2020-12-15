package org.springblade.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.*;
import org.springblade.contract.excel.ContractFormInfoImporter;
import org.springblade.contract.excel.ContractFormInfoImporterEx;
import org.springblade.contract.mapper.*;
import org.springblade.contract.service.*;
import org.springblade.contract.vo.*;
import org.springblade.contract.wrapper.*;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.cache.SysCache;
import org.springblade.system.entity.TemplateFieldJsonEntity;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 服务实现类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:38
 */
@Service
@AllArgsConstructor
public class ContractFormInfoServiceImpl extends BaseServiceImpl<ContractFormInfoMapper, ContractFormInfoEntity> implements IContractFormInfoService {
    private IFileClient fileClient;

    private ISysClient sysClient;

    private IUserClient userClient;

    private IDictBizClient bizClient;

    private ContractFormInfoMapper contractFormInfoMapper;

    private ContractCounterpartMapper contractCounterpartMapper;

    private ContractBondMapper contractBondMapper;

    private ContractAccordingMapper contractAccordingMapper;

    private ContractPerformanceMapper contractPerformanceMapper;

    private ContractPerformanceColPayMapper contractPerformanceColPayMapper;

    private ContractAssessmentMapper contractAssessmentMapper;

    private ContractArchiveMapper contractArchiveMapper;

    private ContractArchiveNotMapper contractArchiveNotMapper;

    private ContractSealUsingInfoMapper sealUsingInfoMapper;

    private ContractSigningMapper signingMapper;

    private ContractArchiveNotMapper archiveNotMapper;

    private ContractRelieveMapper relieveMapper;

    private ContractRawMaterialsMapper contractRawMaterialsMapper;

    private ContractTemplateMapper contractTemplateMapper;

    private ContractChangeMapper changeMapper;
	private IYwlShopRecruitment1Service ywlShopRecruitment1Service;
	private ICglCategorySalesContracts1Service cglCategorySalesContracts1Service;
	private ICglTheSalesContract1Service cglTheSalesContract1Service;
	private ICglRawMaterials1Service cglRawMaterials1Service;
	private ISclEquipmentMaintenance1Service sclEquipmentMaintenance1Service;
	private IMtlAdaptationContract1Service mtlAdaptationContract1Service;
	private IMtlAdaptationContract2Service mtlAdaptationContract2Service;
	private IMtlShootingAndProductionContract1Service mtlShootingAndProductionContract1Service;
	private IMtlShootingAndProductionContract2Service mtlShootingAndProductionContract2Service;
    private ISclProjectOutsourcing1Service sclProjectOutsourcing1Service;

    private IMtlVideoProductionContract1Service mtlVideoProductionContract1Service;
    private IMtlVideoProductionContract2Service mtlVideoProductionContract2Service;
    private IMtlEditedTheContract1Service mtlEditedTheContract1Service;
    private IMtlAudioProductionContract1Service mtlAudioProductionContract1Service;
    private IMtlAudioProductionContract2Service mtlAudioProductionContract2Service;
    private ISclConstructionProject1Service sclConstructionProject1Service;
    private ISclConstructionProject2Service sclConstructionProject2Service;
    private ISclConstructionProject3Service sclConstructionProject3Service;
	private IContractBondPlanService contractBondPlanService;
	private IContractBondService contractBondService;
	private ContractPerformanceMapper performanceMapper;
	private ContractPerformanceColPayMapper performanceColPayMapper;
    private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY = "1332307279915393025";
    private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS = "1332307106157961217";
    private static final String DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE = "1332307534161518593";
    private static final Integer AMOUNT_RATIO_VALUE = 100;
    private static final String CONTRACT_CHANGE_REVIEW = "10";

    @Override
    public IPage<ContractFormInfoResponseVO> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo) {
        String[] code = contractFormInfo.getContractStatus().split(",");
        contractFormInfo.setCode(Arrays.asList(code));
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
            recordList.add(v);
        }
        pages.setRecords(recordList);
        return pages;
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
        if (!DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY.equals(contractFormInfo.getContractBigCategory()) && !DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS.equals(contractFormInfo.getContractStatus())
                && !DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE.equals(contractFormInfo.getColPayType())) {
            List<ContractFormInfoEntity> records = page.getRecords();
            List<ContractFormInfoEntity> recordList = new ArrayList<>();
            for (ContractFormInfoEntity v : records) {
                v.setContractBigCategory(bizClient.getValues("HTDL", Long.valueOf(v.getContractBigCategory())).getData());
                v.setColPayType(bizClient.getValues("col_pay_term", Long.valueOf(v.getColPayTerm())).getData());
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
        if (DICT_BIZ_FINAL_VALUE_CONTRACT_BIG_CATEGORY.equals(contractFormInfo.getContractBigCategory()) || DICT_BIZ_FINAL_VALUE_CONTRACT_STATUS.equals(contractFormInfo.getContractStatus())
                || DICT_BIZ_FINAL_VALUE_CONTRACT_COL_PAY_TYPE.equals(contractFormInfo.getColPayType())) {
            List<ContractFormInfoResponseVO> records = pages.getRecords();
            List<ContractFormInfoResponseVO> recordList = new ArrayList<>();
            for (ContractFormInfoResponseVO v : records) {
                BigDecimal contractAmountSum = BigDecimal.valueOf(contractFormInfoMapper.selectAmountSum());
                v.setAmountRatio(v.getContractAmount().divide(contractAmountSum, 2).multiply(BigDecimal.valueOf(AMOUNT_RATIO_VALUE)) + "%");
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
     * 合同数据导入
     *
     * @return list
     */
    @Override
    public void importContractFormInfo(List<ContractFormInfoImporter> data, List<ContractFormInfoImporterEx> read2, String json, String contractTemplateId, String contractBigCategory, String contractSmallCategory) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        data.forEach(contractFormInfoExcel -> {
            //保存合同数据
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
            if (!"无".equals(contractFormInfoExcel.getYwlMinimum()) && !"".equals(contractFormInfoExcel.getYwlMinimum()) && contractFormInfoExcel.getYwlMinimum() != null) {
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
            }
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
                //原物料一对多的列表保存到contract_raw_materials表中    这个表中需要保存合同的id
                List<ContractRawMaterialsEntity> contractRawMaterialsList = new ArrayList<ContractRawMaterialsEntity>();
                for (ContractFormInfoImporterEx read2Ex : read2) {
                    if (contractFormInfoExcel.getSealName().equals(read2Ex.getYwlFullName()) && contractFormInfoExcel.getCounterpartName().equals(read2Ex.getYwlNameOfOpposite())) {
                        ContractRawMaterialsEntity contractRawMaterialsEntity = new ContractRawMaterialsEntity();
                        if (!"无".equals(contractFormInfoExcel.getYwlFullName()) && !"".equals(contractFormInfoExcel.getYwlFullName()) && contractFormInfoExcel.getYwlFullName() != null) {
                            contractRawMaterialsEntity.setYwlFullName(contractFormInfoExcel.getYwlFullName());
                        }
                        if (!"无".equals(contractFormInfoExcel.getYwlNameOfOpposite()) && !"".equals(contractFormInfoExcel.getYwlNameOfOpposite()) && contractFormInfoExcel.getYwlNameOfOpposite() != null) {
                            contractRawMaterialsEntity.setYwlNameOfOpposite(contractFormInfoExcel.getYwlNameOfOpposite());
                        }
                        if (!"无".equals(contractFormInfoExcel.getYwlSerialNumber()) && !"".equals(contractFormInfoExcel.getYwlSerialNumber()) && contractFormInfoExcel.getYwlSerialNumber() != null) {
                            contractRawMaterialsEntity.setYwlSerialNumber(Integer.valueOf(contractFormInfoExcel.getYwlSerialNumber()));
                        }
                        if (!"无".equals(contractFormInfoExcel.getYwlItemNo()) && !"".equals(contractFormInfoExcel.getYwlItemNo()) && contractFormInfoExcel.getYwlItemNo() != null) {
                            contractRawMaterialsEntity.setYwlItemNo(contractFormInfoExcel.getYwlItemNo());
                        }
                        if (!"无".equals(contractFormInfoExcel.getYwlProductName()) && !"".equals(contractFormInfoExcel.getYwlProductName()) && contractFormInfoExcel.getYwlProductName() != null) {
                            contractRawMaterialsEntity.setYwlProductName(contractFormInfoExcel.getYwlProductName());
                        }
                        if (!"无".equals(contractFormInfoExcel.getYwlSpecifications()) && !"".equals(contractFormInfoExcel.getYwlSpecifications()) && contractFormInfoExcel.getYwlSpecifications() != null) {
                            contractRawMaterialsEntity.setYwlSpecifications(contractFormInfoExcel.getYwlSpecifications());
                        }
                        if (!"无".equals(contractFormInfoExcel.getYwlCompany()) && !"".equals(contractFormInfoExcel.getYwlCompany()) && contractFormInfoExcel.getYwlCompany() != null) {
                            contractRawMaterialsEntity.setYwlCompany(contractFormInfoExcel.getYwlCompany());
                        }
                        if (!"无".equals(contractFormInfoExcel.getYwlPrice()) && !"".equals(contractFormInfoExcel.getYwlPrice()) && contractFormInfoExcel.getYwlPrice() != null) {
                            contractRawMaterialsEntity.setYwlPrice(new BigDecimal(contractFormInfoExcel.getYwlPrice()));
                        }
                        if (!"无".equals(contractFormInfoExcel.getYwlRemarks()) && !"".equals(contractFormInfoExcel.getYwlRemarks()) && contractFormInfoExcel.getYwlRemarks() != null) {
                            contractRawMaterialsEntity.setYwlRemarks(contractFormInfoExcel.getYwlRemarks());
                        }
                        contractRawMaterialsEntity.setContractId(contractFormInfoEntity.getId());
                        contractRawMaterialsMapper.insert(contractRawMaterialsEntity);
                        contractRawMaterialsList.add(contractRawMaterialsEntity);
                    }
                }
                contractFormInfoEntity.setRawMaterialsList(contractRawMaterialsList);
            }
            String jsonEx = this.getjson(json, contractFormInfoEntity);
            contractFormInfoEntity.setJson(jsonEx);
            this.saveOrUpdate(contractFormInfoEntity);
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
        if (Func.isNotEmpty(changeFormInfoEntity)) {
            if (CONTRACT_CHANGE_REVIEW.equals(changeFormInfoEntity.getContractStatus())) {
                contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(changeFormInfoEntity);
            } else {
                contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(contractFormInfo);
            }
        } else {
            contractFormInfoResponseVO = ContractFormInfoWrapper.build().entityPV(contractFormInfo);
        }
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
        //查询独立起草关联相对方
			/*ContractCounterpartEntity counterpartEntity = contractCounterpartMapper.selectByIds(id).get(0);
			contractFormInfoResponseVO.setCounterpartEntity(counterpartEntity);*/
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
        //查询合同附件
        if (Func.isNoneBlank(contractFormInfoResponseVO.getAttachedFiles())) {
            R<List<FileVO>> result = fileClient.getByIds(contractFormInfoResponseVO.getAttachedFiles());
            if (result.isSuccess()) {
                contractFormInfoResponseVO.setAttachedFileVOList(result.getData());
            }
        }
        /* 查询创建者 */
        if (Func.isNoneBlank(contractFormInfoResponseVO.getCreateUser().toString())) {
            /*User user = UserCache.getUser(entity.getCreateUser());*/
            User user = userClient.userInfoById(contractFormInfoResponseVO.getCreateUser()).getData();
            contractFormInfoResponseVO.setUserRealName(user.getRealName());
        }
        /* 查询创建者组织 */
        if (Func.isNoneBlank(contractFormInfoResponseVO.getCreateDept().toString())) {
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
        while (formInfoEntity.getChangeContractId() != null) {
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
     * 范本起草保存
     *
     * @param json 合同模板
     */
    @Override
    public String templateDraft(ContractFormInfoEntity contractFormInfo, String json) {
        //把Json对象转成对象
        List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(json, TemplateFieldJsonEntity.class);
        for (TemplateFieldJsonEntity templateField : templateFieldList) {
            if (ContractFormInfoTemplateContract.CONTRACT_ID.equals(templateField.getComponentType())) {
                templateField.setFieldValue(contractFormInfo.getId().toString());
            }
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
                if (ContractFormInfoTemplateContract.CONTRACT_ACCORDING.equals(templateField.getRelationCode())) {
                    List<ContractAccordingEntity> accordingList = JSON.parseArray(templateField.getTableData(), ContractAccordingEntity.class);
                    /*保存依据信息*/
                    if (CollectionUtil.isNotEmpty(accordingList)) {
                        ContractAccordingEntity contractAccording = accordingList.get(0);
                        contractAccording.setContractId(contractFormInfo.getId());
                        //contractAccordingMapper.insert(contractAccording);
                        accordingList.get(0).setId(contractAccording.getId());
                        templateField.setTableData(JSONObject.toJSONString(accordingList));
                        templateField.setTableDataList(accordingList);
                    }
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
                        //判断保证金是否为空
                        if (!"{}".equals(obj.getString(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_CONTRACTBOND))) {
                            com.alibaba.fastjson.JSONArray contractBondArry = obj.getJSONArray(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_CONTRACTBOND);
                            List<ContractBondEntity> contractBond = JSON.parseArray(contractBondArry.toString(), ContractBondEntity.class);
                            /*if (CollectionUtil.isNotEmpty(contractBond)) {
								ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
                                contractBondMapper.deleteBond(contractFormInfo.getId());
								//删除保证金履约计划脏数据
								contractBondPlanService.deleteByContractId(contractFormInfo.getId());
                                List<Long> list = new ArrayList<>();
                                if (Func.isEmpty(contractFormInfo.getId())) {
                                    contractBondMapper.insert(contractBond.get(0));
                                    contractBond.get(0).setId(contractBond.get(0).getId());
                                    obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_CONTRACTBOND, contractBond);
                                } else {
                                    contractBondMapper.updateById(contractBond.get(0));
                                    obj.put(ContractFormInfoTemplateContract.CONTRACT_COUNTERPART_SUB_CONTRACTBOND, contractBond);
                                }
									*//*list.add(contractBond.get(0).getId());
									contractBondMapper.saveBond(list, contractFormInfo.getId());*//*
                            }*/
							/*保存保证金信息*/
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
										bondList.add(contractBondEntity);
									}
									//保存保证金履约计划
									contractBondPlan.setContractId(contractFormInfo.getId());
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
                }
				//*新陈列协议书关联表
				if (ContractFormInfoTemplateContract.CONTRACT_YWLSHOPRECRUITMENT1.equals(templateField.getRelationCode())) {
					List<YwlShopRecruitment1ResponseVO> ywlShopRecruitment1List = JSON.parseArray(templateField.getTableData(), YwlShopRecruitment1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(ywlShopRecruitment1List)) {
						ywlShopRecruitment1Service.saveBatchByRefId(contractFormInfo.getId(),ywlShopRecruitment1List);
						List<YwlShopRecruitment1ResponseVO> list =ywlShopRecruitment1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*买卖合同（行销品）关联表
				if (ContractFormInfoTemplateContract.CONTRACT_CGLCATEGORYSALESCONTRACTS1.equals(templateField.getRelationCode())) {
					List<CglCategorySalesContracts1ResponseVO> cglCategorySalesContracts1List = JSON.parseArray(templateField.getTableData(), CglCategorySalesContracts1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglCategorySalesContracts1List)) {
						cglCategorySalesContracts1Service.saveBatchByRefId(contractFormInfo.getId(),cglCategorySalesContracts1List);
						List<CglCategorySalesContracts1ResponseVO> list =cglCategorySalesContracts1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*新增原物料补充协议--买卖合同关联表
				if (ContractFormInfoTemplateContract.CONTRACT_CGLTHESALESCONTRACT1.equals(templateField.getRelationCode())) {
					List<CglTheSalesContract1ResponseVO> cglTheSalesContract1List = JSON.parseArray(templateField.getTableData(), CglTheSalesContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglTheSalesContract1List)) {
						cglTheSalesContract1Service.saveBatchByRefId(contractFormInfo.getId(),cglTheSalesContract1List);
						List<CglTheSalesContract1ResponseVO> list =cglTheSalesContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*原物料-买卖合同联表
				if (ContractFormInfoTemplateContract.CONTRACT_CGLRAWMATERIALS1.equals(templateField.getRelationCode())) {
					List<CglRawMaterials1ResponseVO> cglRawMaterials1List = JSON.parseArray(templateField.getTableData(), CglRawMaterials1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(cglRawMaterials1List)) {
						cglRawMaterials1Service.saveBatchByRefId(contractFormInfo.getId(),cglRawMaterials1List);
						List<CglRawMaterials1ResponseVO> list =cglRawMaterials1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*原物料-买卖合同联表
				if (ContractFormInfoTemplateContract.CONTRACT_SCLEQUIOMENTMAINTENANCE1.equals(templateField.getRelationCode())) {
					List<SclEquipmentMaintenance1ResponseVO> sclEquipmentMaintenance1List = JSON.parseArray(templateField.getTableData(), SclEquipmentMaintenance1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(sclEquipmentMaintenance1List)) {
						sclEquipmentMaintenance1Service.saveBatchByRefId(contractFormInfo.getId(),sclEquipmentMaintenance1List);
						List<SclEquipmentMaintenance1ResponseVO> list =sclEquipmentMaintenance1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告改编合同 广告完成形式关联表
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlAdaptationContract1ResponseVO> mtlAdaptationContract1List = JSON.parseArray(templateField.getTableData(), MtlAdaptationContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAdaptationContract1List)) {
						mtlAdaptationContract1Service.saveBatchByRefId(contractFormInfo.getId(),mtlAdaptationContract1List);
						List<MtlAdaptationContract1ResponseVO> list =mtlAdaptationContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告改编合同 交付数量关联表
				if (ContractFormInfoTemplateContract.CONTRACT_MTADAPTATIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlAdaptationContract2ResponseVO> mtlAdaptationContract1List = JSON.parseArray(templateField.getTableData(), MtlAdaptationContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlAdaptationContract1List)) {
						mtlAdaptationContract2Service.saveBatchByRefId(contractFormInfo.getId(),mtlAdaptationContract1List);
						List<MtlAdaptationContract2ResponseVO> list =mtlAdaptationContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告拍摄制作合同 广告完成形式关联表
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
					List<MtlShootingAndProductionContract1ResponseVO> mtlShootingAndProductionContract1List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract1ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlShootingAndProductionContract1List)) {
						mtlShootingAndProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(),mtlShootingAndProductionContract1List);
						List<MtlShootingAndProductionContract1ResponseVO> list =mtlShootingAndProductionContract1Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
				//*视频广告拍摄制作合同 明细关联表
				if (ContractFormInfoTemplateContract.CONTRACT_MTLSHOOTINGANDPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
					List<MtlShootingAndProductionContract2ResponseVO> mtlShootingAndProductionContract2List = JSON.parseArray(templateField.getTableData(), MtlShootingAndProductionContract2ResponseVO.class);
					if (CollectionUtil.isNotEmpty(mtlShootingAndProductionContract2List)) {
						mtlShootingAndProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(),mtlShootingAndProductionContract2List);
						List<MtlShootingAndProductionContract2ResponseVO> list =mtlShootingAndProductionContract2Service.selectRefList(contractFormInfo.getId());
						templateField.setTableData(JSONObject.toJSONString(list));
						templateField.setTableDataList(list);
					}
				}
                //*生产项目外包服务合同 关联表
                if (ContractFormInfoTemplateContract.CONTRACT_SCLPROJECTOUTSOURCING1.equals(templateField.getRelationCode())) {
                    List<SclProjectOutsourcing1ResponseVO> sclProsjectOutsourcing1List = JSON.parseArray(templateField.getTableData(), SclProjectOutsourcing1ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(sclProsjectOutsourcing1List)) {
                        sclProjectOutsourcing1Service.saveBatchByRefId(contractFormInfo.getId(),sclProsjectOutsourcing1List);
                        List<SclProjectOutsourcing1ResponseVO> list =sclProjectOutsourcing1Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*加工承揽合同（代工合同）关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT1.equals(templateField.getRelationCode())) {
                    List<SclConstructionProject1ResponseVO> sclConstructionProject1List = JSON.parseArray(templateField.getTableData(), SclConstructionProject1ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(sclConstructionProject1List)) {
                        sclConstructionProject1Service.saveBatchByRefId(contractFormInfo.getId(),sclConstructionProject1List);
                        List<SclConstructionProject1ResponseVO> list =sclConstructionProject1Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*加工承揽合同（代工合同）关联表2
                if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT2.equals(templateField.getRelationCode())) {
                    List<SclConstructionProject2ResponseVO> sclConstructionProject2List = JSON.parseArray(templateField.getTableData(), SclConstructionProject2ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(sclConstructionProject2List)) {
                        sclConstructionProject2Service.saveBatchByRefId(contractFormInfo.getId(),sclConstructionProject2List);
                        List<SclConstructionProject2ResponseVO> list =sclConstructionProject2Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*加工承揽合同（代工合同）关联表3
                if (ContractFormInfoTemplateContract.CONTRACT_SCLCONSTRUCTIONPROJECT3.equals(templateField.getRelationCode())) {
                    List<SclConstructionProject3ResponseVO> sclConstructionProject3List = JSON.parseArray(templateField.getTableData(), SclConstructionProject3ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(sclConstructionProject3List)) {
                        sclConstructionProject3Service.saveBatchByRefId(contractFormInfo.getId(),sclConstructionProject3List);
                        List<SclConstructionProject3ResponseVO> list =sclConstructionProject3Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*音频制作合同 关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
                    List<MtlAudioProductionContract1ResponseVO> mtlAudioProductionContract1List = JSON.parseArray(templateField.getTableData(),MtlAudioProductionContract1ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(mtlAudioProductionContract1List)) {
                        mtlAudioProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(),mtlAudioProductionContract1List);
                        List<MtlAudioProductionContract1ResponseVO> list =mtlAudioProductionContract1Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*音频制作合同 关联表2
                if (ContractFormInfoTemplateContract.CONTRACT_MTLAUDIOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
                    List<MtlAudioProductionContract2ResponseVO> mtlAudioProductionContract2List = JSON.parseArray(templateField.getTableData(),MtlAudioProductionContract2ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(mtlAudioProductionContract2List)) {
                        mtlAudioProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(),mtlAudioProductionContract2List);
                        List<MtlAudioProductionContract2ResponseVO> list =mtlAudioProductionContract2Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*修图合同关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_MTLEDITEDTHECONTRACT1.equals(templateField.getRelationCode())) {
                    List<MtlEditedTheContract1ResponseVO> mtlEditedTheContract1List = JSON.parseArray(templateField.getTableData(),MtlEditedTheContract1ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(mtlEditedTheContract1List)) {
                        mtlEditedTheContract1Service.saveBatchByRefId(contractFormInfo.getId(),mtlEditedTheContract1List);
                        List<MtlEditedTheContract1ResponseVO> list =mtlEditedTheContract1Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*视频制作合同关联表1
                if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT1.equals(templateField.getRelationCode())) {
                    List<MtlVideoProductionContract1ResponseVO> mtlVideoProductionContract1List = JSON.parseArray(templateField.getTableData(),MtlVideoProductionContract1ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(mtlVideoProductionContract1List)) {
                        mtlVideoProductionContract1Service.saveBatchByRefId(contractFormInfo.getId(),mtlVideoProductionContract1List);
                        List<MtlVideoProductionContract1ResponseVO> list =mtlVideoProductionContract1Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
                //*视频制作合同关联表2
                if (ContractFormInfoTemplateContract.CONTRACT_MTLVIDEOPRODUCTIONCONTRACT2.equals(templateField.getRelationCode())) {
                    List<MtlVideoProductionContract2ResponseVO> mtlVideoProductionContract2List = JSON.parseArray(templateField.getTableData(),MtlVideoProductionContract2ResponseVO.class);
                    if (CollectionUtil.isNotEmpty(mtlVideoProductionContract2List)) {
                        mtlVideoProductionContract2Service.saveBatchByRefId(contractFormInfo.getId(),mtlVideoProductionContract2List);
                        List<MtlVideoProductionContract2ResponseVO> list =mtlVideoProductionContract2Service.selectRefList(contractFormInfo.getId());
                        templateField.setTableData(JSONObject.toJSONString(list));
                        templateField.setTableDataList(list);
                    }
                }
            }
        }
        //toJSONString(templateFieldList);
        return toJSONString(templateFieldList);
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
}
