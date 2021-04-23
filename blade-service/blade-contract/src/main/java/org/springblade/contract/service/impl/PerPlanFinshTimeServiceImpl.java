package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.service.IPerPlanFinshContentService;
import org.springblade.contract.util.IdGenUtil;
import org.springblade.contract.vo.PerPlanFinshTimeRequestVO;
import org.springblade.contract.wrapper.PerPlanFinshTimeWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.PerPlanFinshTimeEntity;
import org.springblade.contract.mapper.PerPlanFinshTimeMapper;
import org.springblade.contract.service.IPerPlanFinshTimeService;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 履约计划完成时间 服务实现类
 *
 * @author chenzy
 * @date : 2021-04-20 16:34:52
 */
@Service
public class PerPlanFinshTimeServiceImpl extends BaseServiceImpl<PerPlanFinshTimeMapper, PerPlanFinshTimeEntity> implements IPerPlanFinshTimeService {

	@Autowired
	private IPerPlanFinshContentService perPlanFinshContentService;


	@Override
	public IPage<PerPlanFinshTimeEntity> pageList(IPage<PerPlanFinshTimeEntity> page, PerPlanFinshTimeRequestVO perPlanFinshTime) {
		return baseMapper.pageList(page, perPlanFinshTime);
	}

	@Override
	public Boolean addListByRequest(List<PerPlanFinshTimeRequestVO> planFinshTimeRequestVOList, Long contractId,Long serviceContentId) {
		if(Func.isEmpty(planFinshTimeRequestVOList)){
			return false;
		}
		//根据合同标识删除数据
		this.deleteByContractId(contractId);
		//设置计划时间关联合同标识
		planFinshTimeRequestVOList.forEach(perPlanFinshTimeRequestVO -> {
			//生成id
			perPlanFinshTimeRequestVO.setId(IdGenUtil.generateId());
			//设置合同id
			perPlanFinshTimeRequestVO.setContractId(contractId);
			//设置履约服务内容id
			perPlanFinshTimeRequestVO.setServiceContentId(serviceContentId);
		});
		List<PerPlanFinshTimeEntity>perPlanFinshTimeEntities = PerPlanFinshTimeWrapper.build().QVEntityList(planFinshTimeRequestVOList);
		perPlanFinshContentService.addPlanContentDataByPlanTimeList(planFinshTimeRequestVOList);
		return this.saveBatch(perPlanFinshTimeEntities);
	}


	public void deleteByContractId(Long contractId){
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("contract_id",contractId);
		baseMapper.deleteByMap(columnMap);
	}

}
