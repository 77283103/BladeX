package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.log4j.Log4j2;
import org.springblade.contract.entity.PerServiceContentEntity;
import org.springblade.contract.mapper.PerServiceContentMapper;
import org.springblade.contract.service.IPerPlanFinshTimeService;
import org.springblade.contract.service.IPerServiceContentService;
import org.springblade.contract.util.IdGenUtil;

import org.springblade.contract.vo.PerPlanFinshTimeRequestVO;
import org.springblade.contract.vo.PerServiceContentRequestVO;
import org.springblade.contract.wrapper.PerServiceContentWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 履约服务内容 服务实现类
 *
 * @author chenzy
 * @date : 2021-04-20 16:02:06
 */
@Log4j2
@Service
public class PerServiceContentServiceImpl extends BaseServiceImpl<PerServiceContentMapper, PerServiceContentEntity> implements IPerServiceContentService {

	@Autowired
	private IPerPlanFinshTimeService perPlanFinshTimeService;

	@Override
	public IPage<PerServiceContentEntity> pageList(IPage<PerServiceContentEntity> page, PerServiceContentRequestVO perServiceContent) {
		return baseMapper.pageList(page, perServiceContent);
	}

	@Override
	public R addPerData(PerServiceContentRequestVO serviceContentRequestVO,Long contractId) {
		if(Func.isEmpty(contractId)){
			log.error("添加合同履约计划信息-false，合同标识为空");
			return R.status(false);
		}
		if(!this.verificationPerData(serviceContentRequestVO)){
			return R.status(false);
		}
		this.deleteByContractId(serviceContentRequestVO.getContractId());
		serviceContentRequestVO.setId(IdGenUtil.generateId());
		serviceContentRequestVO.setContractId(contractId);
		PerServiceContentEntity perServiceContentEntity = PerServiceContentWrapper.build().QVEntity(serviceContentRequestVO);
		this.save(perServiceContentEntity);
		perPlanFinshTimeService.addListByRequest(serviceContentRequestVO.getPerPlanFinshTimeRequestVOList(),
			serviceContentRequestVO.getContractId(),serviceContentRequestVO.getId());
		return R.success("添加完成");
	}


	@Override
	public void deleteByContractId(Long contractId){
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("contract_id",contractId);
		this.baseMapper.deleteByMap(columnMap);
	}



	public Boolean verificationPerData(PerServiceContentRequestVO serviceContentRequestVO){
		log.info("履约请求信息验证:{}",JsonUtil.toJson(serviceContentRequestVO));
		if(null == serviceContentRequestVO){
			log.error("履约请求信息验证-false,请求信息为空:{}",JsonUtil.toJson(serviceContentRequestVO));
			return false;
		}
		if(Func.isEmpty(serviceContentRequestVO.getPerPlanFinshTimeRequestVOList())){
			log.error("履约请求信息验证-false，缺失计划完成时间:{}", JsonUtil.toJson(serviceContentRequestVO));
			return false;
		}
		for(PerPlanFinshTimeRequestVO perPlanFinshTimeRequestVO:serviceContentRequestVO.getPerPlanFinshTimeRequestVOList()){
			if(Func.isEmpty(perPlanFinshTimeRequestVO.getPerPlanFinshContentRequestVOList())){
				log.error("履约请求信息验证-false，计划完成时间:{}缺失计划完成内容", JsonUtil.toJson(perPlanFinshTimeRequestVO));
				return false;
			}
		}
		return true;
	}



}
