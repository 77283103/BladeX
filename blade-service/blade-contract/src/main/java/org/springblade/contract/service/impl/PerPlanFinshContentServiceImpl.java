package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.log4j.Log4j2;
import org.springblade.contract.vo.PerPlanFinshContentRequestVO;
import org.springblade.contract.vo.PerPlanFinshTimeRequestVO;
import org.springblade.contract.wrapper.PerPlanFinshContentWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.PerPlanFinshContentEntity;
import org.springblade.contract.mapper.PerPlanFinshContentMapper;
import org.springblade.contract.service.IPerPlanFinshContentService;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 履约计划完成内容 服务实现类
 *
 * @author chenzy
 * @date : 2021-04-20 16:28:58
 */
@Log4j2
@Service
public class PerPlanFinshContentServiceImpl extends BaseServiceImpl<PerPlanFinshContentMapper, PerPlanFinshContentEntity> implements IPerPlanFinshContentService {

	@Override
	public IPage<PerPlanFinshContentEntity> pageList(IPage<PerPlanFinshContentEntity> page, PerPlanFinshContentRequestVO perPlanFinshContent) {
		return baseMapper.pageList(page, perPlanFinshContent);
	}

	@Override
	public Boolean addPlanContentDataByPlanTimeList(List<PerPlanFinshTimeRequestVO> perPlanFinshTimeRequestVOList){
		return this.saveBatch(this.generateListByFinshTimeList(perPlanFinshTimeRequestVOList));
	}


	@Override
	public Boolean addListData(List<PerPlanFinshContentEntity> perPlanFinshContentEntityList) {
		//验证数据是否符合保存规则
		if(!this.verificationByList(perPlanFinshContentEntityList)){
			return false;
		}
		this.deleteByContractId(this.getContractIdByList(perPlanFinshContentEntityList));
		return this.saveBatch(perPlanFinshContentEntityList);
	}

	@Override
	public void deleteByContractId(Long contractId){
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("contract_id",contractId);
		baseMapper.deleteByMap(columnMap);
	}

	@Override
	public Boolean verificationByList(List<PerPlanFinshContentEntity> perPlanFinshContentEntityList){
		//保存规则，
		if(Func.isEmpty(perPlanFinshContentEntityList)){
			log.error("验证履约计划完成内容-false,计划完成内容为空:{}", JsonUtil.toJson(perPlanFinshContentEntityList));
			return false;
		}

		for(PerPlanFinshContentEntity perPlanFinshContentEntity:perPlanFinshContentEntityList){
			if(Func.isEmpty(perPlanFinshContentEntity.getContractId())){
				log.error("验证履约计划完成内容-false,合同标识为空:{}", JsonUtil.toJson(perPlanFinshContentEntity));
				return false;
			}
			if(Func.isEmpty(perPlanFinshContentEntity.getPlanFinshTimeId())){
				log.error("验证履约计划完成内容-false,计划完成时间标识为空:{}", JsonUtil.toJson(perPlanFinshContentEntity));
				return false;
			}
		}
		return true;
	}

	@Override
	public List<PerPlanFinshContentEntity> generateListByFinshTimeList(List<PerPlanFinshTimeRequestVO> perPlanFinshTimeRequestVOList) {
		//收集组装数据--集合
		List<PerPlanFinshContentRequestVO>generateList = new ArrayList<>();
		//循环计划完成时间集合，获取每个计划完成时间中的计划完成内容数据并赋计划完成时间关联值
		perPlanFinshTimeRequestVOList.forEach(perPlanFinshTimeRequestVO -> {
			List<PerPlanFinshContentRequestVO> perPlanFinshContentRequestVOList =
				Optional.ofNullable(perPlanFinshTimeRequestVO.getPerPlanFinshContentRequestVOList()).orElse(new ArrayList<>());
			perPlanFinshContentRequestVOList.forEach(perPlanFinshContentRequestVO -> {
				perPlanFinshContentRequestVO.setPlanFinshTimeId(perPlanFinshTimeRequestVO.getId());
				perPlanFinshContentRequestVO.setContractId(perPlanFinshTimeRequestVO.getContractId());
				//赋值后的计划完成内容数据统一放入组转数据集合
				generateList.add(perPlanFinshContentRequestVO);
			});
		});
		//转换组装数据集合并返回
		return PerPlanFinshContentWrapper.build().QVEntityList(generateList);
	}

	/**
	 * list中获取合同标识
	 * @return
	 */
	private Long getContractIdByList(List<PerPlanFinshContentEntity> perPlanFinshContentEntityList){
		for(PerPlanFinshContentEntity perPlanFinshContentEntity:perPlanFinshContentEntityList){
			return perPlanFinshContentEntity.getContractId();
		}
		return 0L;
	}
}
