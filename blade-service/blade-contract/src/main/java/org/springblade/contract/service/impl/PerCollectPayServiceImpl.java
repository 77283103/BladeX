package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.log4j.Log4j2;
import org.springblade.contract.vo.PerCollectPayListResponseVO;
import org.springblade.contract.vo.PerCollectPayRequestVO;
import org.springblade.contract.vo.PerCollectPayResponseVO;
import org.springblade.contract.wrapper.PerCollectPayWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.PerCollectPayEntity;
import org.springblade.contract.mapper.PerCollectPayMapper;
import org.springblade.contract.service.IPerCollectPayService;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 履约收付款 服务实现类
 *
 * @author chenzy
 * @date : 2021-04-25 10:32:29
 */
@Log4j2
@Service
public class PerCollectPayServiceImpl extends BaseServiceImpl<PerCollectPayMapper, PerCollectPayEntity> implements IPerCollectPayService {

	@Override
	public IPage<PerCollectPayEntity> pageList(IPage<PerCollectPayEntity> page, PerCollectPayRequestVO perCollectPay) {
		return baseMapper.pageList(page, perCollectPay);
	}

	public IPage<PerCollectPayListResponseVO> perCollectPayList(IPage<PerCollectPayEntity> page, PerCollectPayRequestVO perCollectPay){
		return baseMapper.perCollectPayList(page, perCollectPay);
	}


	@Override
	public Boolean addListData(List<PerCollectPayRequestVO> perCollectPayRequestVOList,Long contractId) {
		if(!this.verificationData(perCollectPayRequestVOList,contractId)){
			return false;
		}
		perCollectPayRequestVOList.forEach(perCollectPayRequestVO -> {
			perCollectPayRequestVO.setContractId(contractId);
		});
		List<PerCollectPayEntity>perCollectPayEntityList = PerCollectPayWrapper.build().QVEntityList(perCollectPayRequestVOList);
		return this.saveBatch(perCollectPayEntityList);
	}

	@Override
	public List<PerCollectPayResponseVO> findListByContractId(Long contractId) {
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("contract_id",contractId);
		List<PerCollectPayEntity>perCollectPayEntityList = this.baseMapper.selectByMap(columnMap);
		return Func.isEmpty(perCollectPayEntityList)?new ArrayList<>():PerCollectPayWrapper.build().entityPVList(perCollectPayEntityList);
	}


	public Boolean verificationData(List<PerCollectPayRequestVO> perCollectPayRequestVOList,Long contractId){
		if(Func.isEmpty(contractId)){
			log.error("履约信息-收付款信息入库验证-false，合同标识为空");
			return false;
		}

		if(Func.isEmpty(perCollectPayRequestVOList)){
			log.error("履约信息-收付款信息入库验证-false，实体信息为空:{}", JsonUtil.toJson(perCollectPayRequestVOList));
			return false;
		}

		log.error("履约信息-收付款信息入库验证-true:{}",JsonUtil.toJson(perCollectPayRequestVOList));
		return true;

	}


}
