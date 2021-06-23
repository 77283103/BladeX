package org.springblade.contract.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.contract.entity.*;
import org.springblade.contract.mapper.ContractCounterpartMapper;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.mapper.ContractTemplateMapper;
import org.springblade.contract.service.*;
import org.springblade.contract.vo.ContractArchiveNotResponseVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.system.cache.SysCache;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 合同Feign实现类
 *
 * @author Chill
 */
@Slf4j
@ApiIgnore
@RestController
public class ContractClient implements IContractClient {
	@Autowired
	private IFileClient fileClient;
	@Autowired
	private IAbutmentClient abutmentClient;
	@Autowired
	private IContractArchiveNotService notService;
	@Autowired
	private IContractFormInfoService formInfoService;
	@Autowired
	private ContractFormInfoMapper contractFormInfoMapper;
	@Autowired
	private IContractTemplateService templateService;
	@Autowired
	private ContractTemplateMapper templateMapper;
	@Autowired
	private IContractSigningService contractSigningService;
	@Autowired
	private IContractSealUsingInfoService sealUsingInfoService;
	@Autowired
	private IContractCounterpartService iContractCounterpartService;
	@Autowired
	private ContractCounterpartMapper counterpartMapper;
	@Autowired
	private IContractCounterpartService counterpartService;
	//模板路径
	private static final String ftlPath = "D:/ftl/";
	@Value("${api.signing.day.fifteen}")
	private Integer signingDaysFifteen;
	@Value("${api.signing.day.fortyfive}")
	private Integer signingDaysFortyFive;
	@Value("${api.signing.day.zero}")
	private Integer signingDaysZero;
	@Value("${api.signing.day.estimate}")
	private Integer estimate;

	@Override
	public R<Boolean> saveBatch(List<ContractCounterpartEntity> listInsert) {
		return R.data(counterpartService.saveBatch(listInsert));
	}

	@Override
	public R<Boolean> saveOrUpdate(List<ContractCounterpartEntity> list) {
		return R.data(counterpartService.saveOrUpdateBatch(list));
	}

	@Override
	public R<Boolean> updateById(ContractCounterpartEntity updateCounterpart) {
		return R.data(counterpartService.saveOrUpdate(updateCounterpart));
	}

	@Override
	public R<List<ContractCounterpartEntity>> selectByName(String unifiedSocialCreditCode) {
		return R.data(counterpartMapper.selectByName(unifiedSocialCreditCode));
	}

	@Override
	@GetMapping(CONTRACT)
	public R<ContractFormInfoResponseVO> getById(Long id) {
		return R.data(formInfoService.getById(id));
	}

	@Override
	@GetMapping(STATUS)
	public R<List<ContractFormInfoEntity>> getByStatus(String status) {
		List<ContractFormInfoEntity> infoEntityList = new ArrayList<>();
		List<ContractFormInfoEntity> formInfoEntities = formInfoService.getByStatus(status);
		if (Func.isNull(formInfoEntities) || Func.isEmpty(formInfoEntities)) {
			return R.data(200, formInfoEntities, "暂无数据推送");
		}
		//查询出用印申请通过未归档的合同信息  根据超期时间分别提示不同信息内容
		//情况一：   正常超期提示
		//情况二：   合同续约的合同超期提示
		formInfoEntities.forEach(ls -> {
			ContractArchiveNotResponseVO archiveNot = notService.getLastById(ls.getId());
			//查询是否填写未归档信息 没有
			if (Func.isEmpty(archiveNot) || Func.isNull(archiveNot)) {
				int day = differentDaysByMillisecond(ls.getUpdateTime(), new Date());
				if (day >= this.signingDaysFifteen) {
					//超期十五天
					ls.setFilePerson(String.valueOf(this.signingDaysFifteen));
					infoEntityList.add(ls);
				} else if (day > this.signingDaysFortyFive) {
					//超期四十五天
					ls.setFilePerson(String.valueOf(this.signingDaysFortyFive));
					infoEntityList.add(ls);
				}
			} else {
				//未归档信息填写时间与计划归档时间
				int archDay = differentDaysByMillisecond(archiveNot.getCreateTime(), archiveNot.getEstimateArchiveDate());
				//未归档信息填写时间与当前时间
				int notDay = differentDaysByMillisecond(archiveNot.getCreateTime(), new Date());
				//判断是否超过计划归档时间，是则添加到预警提示信息列表
				if (notDay >= archDay) {
					//超期预计归档时期
					ls.setFilePerson(String.valueOf(this.estimate));
					infoEntityList.add(ls);
				}
			}
		});
		return R.data(200, infoEntityList, "需要推送的数据");
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}

	@Override
	@GetMapping(CHOOSE)
	public R<List<ContractFormInfoEntity>> getChooseList() {
		return R.data(formInfoService.getChooseList());
	}

	@Override
	@PostMapping(TEMPLATE_UPDATE)
	public R<ContractTemplateResponseVO> templateUpdate(TemplateEntity entity) {
		ContractTemplateEntity templateFieldEntity = new ContractTemplateEntity();
		QueryWrapper<ContractTemplateEntity> queryWrapper = Condition.getQueryWrapper(templateFieldEntity)
			.eq("template_code", entity.getTemplateCode())
			.eq("is_deleted", 0);
			/*.eq("template_status","10")
			.or().eq("template_status","40");*/
		List<ContractTemplateEntity> list = templateService.list(queryWrapper);
		for (ContractTemplateEntity v : list) {
			if (Func.isEmpty(templateMapper.latestById(v.getId()))) {
				//模板生成后修改成带使用  用作审批使用
				v.setTemplateStatus("10");
				v.setJson(entity.getJson());
				templateService.updateById(v);
			}
		}
		return null;
	}

	@SneakyThrows
	@Override
	@GetMapping(CONTRACT_SAVE)
	public R saveContractFormInfo(Long id, String status) {
		ContractFormInfoEntity contractFormInfo = contractFormInfoMapper.selectById(id);
		ContractSigningEntity entity = new ContractSigningEntity();
		ContractSealUsingInfoEntity sealUsingInfoEntity = new ContractSealUsingInfoEntity();
		if (Func.isEmpty(contractFormInfo)) {
			return R.fail("合同信息不存在");
		}
		//审批通过
		if ("30".equals(status)) {
			log.info("审批状态为25说明为驳回，30说明为审批通过，140说明为起草废除,此节点状态为：" + status);
			if ("1".equals(contractFormInfo.getContractForm())) {
				log.info("合同形式为1表示为电子签章-我司平台，审批通过后直接转到已归档节点（待结案），至此合同形式为：" + contractFormInfo.getContractForm());
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String date = df.format(new Date());
				//关联合同ID
				entity.setContractId(contractFormInfo.getId());
				sealUsingInfoEntity.setRefContractId(contractFormInfo.getId());
				//签订时间 用印时间
				entity.setSignDate(new Date());
				sealUsingInfoEntity.setSignTime(new Date());
				//合同起止时间
				entity.setContractStartTime(contractFormInfo.getStartingTime());
				entity.setContractEndTime(contractFormInfo.getEndTime());
				//用印申请人 部门
				sealUsingInfoEntity.setSignPerson(contractFormInfo.getPersonContract());
				sealUsingInfoEntity.setManager(contractFormInfo.getCreateUserName());
				sealUsingInfoEntity.setManageDept(contractFormInfo.getCreateDeptName());
				sealUsingInfoEntity.setManageUnit(contractFormInfo.getCreateDeptName());
				//备注
				entity.setRemark("电子合同-我司用印");
				sealUsingInfoEntity.setSignRemark("电子合同-我司用印");
				//递交方式
				entity.setSubmissionType(" ");
				//收件人
				entity.setAddressee(" ");
				contractSigningService.save(entity);
				sealUsingInfoService.save(sealUsingInfoEntity);
				log.info("并创建保存对应合同的归档信息：" + entity.getContractId());
				contractFormInfo.setContractStatus("60");
			} else if ("3".equals(contractFormInfo.getContractForm())) {
				log.info("合同形式为3表示为电子合同-对方平台，审批通过后直接转到用印节点（需要手动归档），至此合同形式为：" + contractFormInfo.getContractForm());
				sealUsingInfoEntity.setRefContractId(contractFormInfo.getId());
				sealUsingInfoEntity.setSignTime(new Date());
				//用印申请人 部门
				sealUsingInfoEntity.setSignPerson(contractFormInfo.getPersonContract());
				sealUsingInfoEntity.setManager(contractFormInfo.getCreateUserName());
				sealUsingInfoEntity.setManageDept(contractFormInfo.getCreateDeptName());
				sealUsingInfoEntity.setManageUnit(contractFormInfo.getCreateDeptName());
				sealUsingInfoEntity.setSignRemark("电子合同-我司用印");
				sealUsingInfoService.save(sealUsingInfoEntity);
				contractFormInfo.setContractStatus("50");
			} else {
				log.info("合同形式为2，4表示为实体签章-我司不用电子印/实体签章-我司用电子印，审批通过后转到印节，至此合同形式为：" + contractFormInfo.getContractForm());
				contractFormInfo.setContractStatus("30");
				//实体合同需要第一次预警时机：合同用印审批流通过后即发出代办于申请人系统
				contractFormInfo.setFilePerson(String.valueOf(this.signingDaysZero));
				//更新合同信息的审核时间
				formInfoService.updateById(contractFormInfo);
				log.info("审批通过后查看合同的修改时间是否更新："+formInfoService);
				//使用更新后的合同信息的审核时间来计算预警时间和预警内容
				abutmentClient.pushNotSig(contractFormInfo);
			}
		} else {
			contractFormInfo.setContractStatus(status);
		}
		formInfoService.saveOrUpdate(contractFormInfo);
		return R.success("审核成功");
	}

	@Override
	@GetMapping(TEMPLATE_GET_ID)
	public R<ContractTemplateEntity> getByTemplateId(Long id) {
		ContractTemplateEntity templateFieldEntity = templateService.getById(id);
		return R.data(templateFieldEntity);
	}

	/**
	 * epk返回给合同平台未归档信息
	 * @param id 合同ID
	 * @param estimateArchiveDate 计划完成时间
	 * @param notArchiveReason 未归档原因
	 * @return org.springblade.core.tool.api.R
	 * @author jitwxs
	 * @date 2021/6/16 11:28
	 */
	@Override
	@GetMapping(NOT_ARCHIVE_SAVE)
	public R saverArchiveNot(Long id, Date estimateArchiveDate, String notArchiveReason) {
		ContractFormInfoEntity contractFormInfo = contractFormInfoMapper.selectById(id);
		if (Func.isEmpty(contractFormInfo)) {
			return R.fail("合同信息不存在");
		}
		ContractArchiveNotEntity notEntity=new ContractArchiveNotEntity();
		List<ContractCounterpartEntity> contractCounterpartList = counterpartMapper.selectByIds(contractFormInfo.getId());
		if (Func.isNotEmpty(contractCounterpartList)) {
			contractFormInfo.setCounterpart(contractCounterpartList);
			StringBuilder name = new StringBuilder();
			for (ContractCounterpartEntity counterpartEntity : contractCounterpartList) {
				name.append(counterpartEntity.getName());
				name.append(",");
			}
			name.substring(0, name.length());
			contractFormInfo.setCounterpartName(name.toString());
		}
		notEntity.setOtherCompanyName(contractFormInfo.getCounterpartName());
		notEntity.setContractId(contractFormInfo.getId());
		notEntity.setContractNumber(contractFormInfo.getContractNumber());
		if (Func.isNotEmpty(contractFormInfo.getSealInfoEntity())){
			notEntity.setPrintDate(contractFormInfo.getSealInfoEntity().getSignTime());
		}else {
			notEntity.setPrintDate(new Date());
		}
		notEntity.setEstimateArchiveDate(estimateArchiveDate);
		notEntity.setArchiveReason(notArchiveReason);
		notEntity.setRemark("选填EKP返回给合同平台未归档信息");
		notEntity.setManager(contractFormInfo.getPersonContract());
		notEntity.setManageDept(Optional.ofNullable(UserCache.getUser(contractFormInfo.getUpdateUser())).orElse(new User()).getRealName());
		notEntity.setManageUnit(SysCache.getDeptName(contractFormInfo.getCreateDept()));
		notEntity.setManageDate(new Date());
		notService.save(notEntity);
		return R.success("提交成功");
	}
}
