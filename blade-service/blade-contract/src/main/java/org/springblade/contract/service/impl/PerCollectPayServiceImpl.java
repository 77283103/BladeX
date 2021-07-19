package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.log4j.Log4j2;
import org.springblade.contract.excel.importbatchdraft.PerCollectPayImportBatchDraftExcel;
import org.springblade.contract.vo.PerCollectPayListResponseVO;
import org.springblade.contract.vo.PerCollectPayRequestVO;
import org.springblade.contract.vo.PerCollectPayResponseVO;
import org.springblade.contract.wrapper.PerCollectPayWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.PerCollectPayEntity;
import org.springblade.contract.mapper.PerCollectPayMapper;
import org.springblade.contract.service.IPerCollectPayService;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictBizClient;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private IDictBizClient dictBizClient;


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
		this.deleteByContractId(contractId);
		List<PerCollectPayEntity>perCollectPayEntityList = new ArrayList<>();
		perCollectPayRequestVOList.forEach(perCollectPayRequestVO -> {
			PerCollectPayEntity perCollectPayEntity = PerCollectPayWrapper.build().QVEntity(perCollectPayRequestVO);
			perCollectPayEntity.setId(null);
			perCollectPayEntity.setContractId(contractId);
			perCollectPayEntity.setBusinessIds(perCollectPayRequestVO.generateBusinessIds());
			perCollectPayEntityList.add(perCollectPayEntity);
		});
		return this.saveBatch(perCollectPayEntityList);
	}

	@Override
	public List<PerCollectPayResponseVO> findListByContractId(Long contractId) {
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("contract_id",contractId);
		List<PerCollectPayEntity>perCollectPayEntityList = this.baseMapper.selectByMap(columnMap);
		return Func.isEmpty(perCollectPayEntityList)?new ArrayList<>():PerCollectPayWrapper.build().entityPVList(perCollectPayEntityList);
	}

	@Override
	public List<PerCollectPayEntity> saveByBatchDraftExcels(List<PerCollectPayImportBatchDraftExcel> perCollectPayImportBatchDraftExcels, Long contractId) {
		List<PerCollectPayEntity> perCollectPayEntityList = new ArrayList<>();
		if(Func.isNotEmpty(perCollectPayImportBatchDraftExcels)){
			perCollectPayImportBatchDraftExcels.forEach(perCollectPayImportBatchDraftExcel -> {
				//将模板类中字典值根据value转换为key
				perCollectPayImportBatchDraftExcel.setContractTranType(
				dictBizClient.getDictByCodeValue("transaction_type",perCollectPayImportBatchDraftExcel.getContractTranType(),false).getData()
				);
				PerCollectPayEntity perCollectPayEntity = BeanUtil.copy(perCollectPayImportBatchDraftExcel,PerCollectPayEntity.class);
				perCollectPayEntity.setContractId(contractId);
				perCollectPayEntityList.add(perCollectPayEntity);
			});
		}
		this.deleteByContractId(contractId);
		this.saveBatch(perCollectPayEntityList);
		return perCollectPayEntityList;
	}


	public void deleteByContractId(Long contractId){
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("contract_id",contractId);
		baseMapper.deleteByMap(columnMap);
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
