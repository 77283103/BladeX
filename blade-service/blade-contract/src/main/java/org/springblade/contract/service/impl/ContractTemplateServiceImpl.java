package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.mapper.ContractTemplateMapper;
import org.springblade.contract.service.IContractTemplateService;
import org.springblade.contract.vo.ContractTemplateRequestVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.contract.wrapper.ContractTemplateWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
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

	private IFileClient fileClient;

	private IUserClient userClient;

	private ISysClient sysClient;

	private IDictBizClient bizClient;

	@Override
	public IPage<ContractTemplateResponseVO> pageList(IPage<ContractTemplateEntity> page, ContractTemplateRequestVO template) {
		if (Func.isNotBlank(template.getTemplateStatus())) {
			String[] code = template.getTemplateStatus().split(",");
			template.setCode(Arrays.asList(code));
		}
		page =baseMapper.pageList(page, template);
		IPage<ContractTemplateResponseVO> pages = ContractTemplateWrapper.build().entityPVPage(page);
		List<ContractTemplateResponseVO> records = pages.getRecords();
		List<ContractTemplateResponseVO> recordList = new ArrayList<>();

		for (ContractTemplateResponseVO v : records) {
			/*为每个对象，设置创建者名字和组织名字*/
			v.setUserRealName(userClient.userInfoById(v.getCreateUser()).getData().getRealName());
			v.setUserDepartName(sysClient.getDept(v.getCreateDept()).getData().getDeptName());
			/*判断范本是否为最新范本*/
			if (Func.isEmpty(templateMapper.latestById(v.getId()))) {
				recordList.add(v);
			}
		}
		pages.setRecords(recordList);
		return pages;
	}

	/**
	 * 修改范本状态
	 * @param templateStatus,id
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateTemplateStatus(String templateStatus, Long  id) {
		return templateMapper.updateTemplateStatus(templateStatus, id);
	}

	/**
	 * 返回文件vo
	 * @param id
	 * @return
	 */
	@Override
	public ContractTemplateResponseVO getById(Long id) {
		//查询实体数据
		ContractTemplateEntity templateEntity=baseMapper.selectById(id);
		//统计范本使用率，履约中和疼痛数量，已完成合同数量
		templateEntity.setUsageRate(BigDecimal.valueOf(templateMapper.selectByIdUsageRate(id).doubleValue())
				.divide(BigDecimal.valueOf(templateMapper.selectByIdTemplateCount().doubleValue()),4,ROUND_HALF_EVEN).doubleValue()*100+"%");
		templateEntity.setAuthenticPerformanceCount(templateMapper.selectByIdFulfillingCount(id));
		templateEntity.setCompletedContractCount(templateMapper.selectByIdCompletedCount(id));
		//将实体数据存入vo
		ContractTemplateResponseVO templateResponseVO= ContractTemplateWrapper.build().entityPV(templateEntity);
		//判断vo的文件id是否为空
		if (Func.isNoneBlank(templateResponseVO.getAttachedFiles())){
			//根据文件id查询关联的文件信息
			R<List<FileVO>> result = fileClient.getByIds(templateResponseVO.getAttachedFiles());
			//潘顿查询是否成功
			if (result.isSuccess()){
				//将文件信息set到vo 的 list
				templateResponseVO.setTemplateFileVOList(result.getData());
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
		//返回vo
		return templateResponseVO;
	}
	/**
	 * 根据模板id查询历史版本列表
	 * @param id
	 * @return
	 */
	@Override
	public ContractTemplateResponseVO getByNewId(Long id) {
		List<ContractTemplateEntity> templateEntityList=new ArrayList<>();
		//根据最新版本id查询最新范本数据
		ContractTemplateEntity templateEntity = baseMapper.selectById(id);
		templateEntity.setUsageRate(BigDecimal.valueOf(templateMapper.selectByIdUsageRate(id).doubleValue())
				.divide(BigDecimal.valueOf(templateMapper.selectByIdTemplateCount().doubleValue()),4,ROUND_HALF_EVEN).doubleValue()*100+"%");
		templateEntity.setAuthenticPerformanceCount(templateMapper.selectByIdFulfillingCount(id));
		templateEntity.setCompletedContractCount(templateMapper.selectByIdCompletedCount(id));
		while (templateEntity.getOriginalTemplateId()!=null && templateEntity.getOriginalTemplateId()!=-1) {
			templateEntity = baseMapper.selectById(templateEntity.getOriginalTemplateId());
			templateEntity.setUsageRate(BigDecimal.valueOf(templateMapper.selectByIdUsageRate(templateEntity.getId()).doubleValue())
					.divide(BigDecimal.valueOf(templateMapper.selectByIdTemplateCount().doubleValue()),4,ROUND_HALF_EVEN).doubleValue()*100+"%");
			templateEntity.setAuthenticPerformanceCount(templateMapper.selectByIdFulfillingCount(templateEntity.getId()));
			templateEntity.setCompletedContractCount(templateMapper.selectByIdCompletedCount(templateEntity.getId()));
			templateEntityList.add(templateEntity);
		}
		//将实体数据存入void
		ContractTemplateResponseVO templateResponseVO= ContractTemplateWrapper.build().entityPV(templateEntity);
		templateResponseVO.setTemplateEntityOldVOList(templateEntityList);
		return templateResponseVO;
	}


	private static final Integer TEMPLATE_NUMBER_DIGITS=9;
	private static final Integer TEMPLATE_NUMBER_TENSE=99;
	/**
	 * 新增范本，规范生成范本编号
	 * @param entity
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean save(ContractTemplateEntity entity,String type) {
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
		}else if("REVISION".equals(type)){
			entity.setTemplateNumber(baseMapper.selectById(entity.getOriginalTemplateId()).getTemplateNumber());
		}
		return super.save(entity);
	}

}
