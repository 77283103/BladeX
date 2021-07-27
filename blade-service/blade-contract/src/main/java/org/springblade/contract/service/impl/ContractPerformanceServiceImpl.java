package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.mapper.ContractCounterpartMapper;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractPerformanceRequestVO;
import org.springblade.contract.vo.ContractPerformanceResponseVO;
import org.springblade.contract.wrapper.ContractPerformanceWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.mapper.ContractPerformanceMapper;
import org.springblade.contract.service.IContractPerformanceService;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.springblade.contract.service.impl.ContractBondPlanServiceImpl.plusDay;

/**
 * 接收/提供服务计划清单 服务实现类
 *
 * @author szw
 * @date : 2020-11-05 17:06:56
 */
@Service
@AllArgsConstructor
public class ContractPerformanceServiceImpl extends BaseServiceImpl<ContractPerformanceMapper, ContractPerformanceEntity> implements IContractPerformanceService {

	private ContractFormInfoMapper formInfoMapper;
	private IContractFormInfoService formInfoService;
	private ContractCounterpartMapper counterpartMapper;
	private ContractPerformanceMapper performanceMapper;

	@SneakyThrows
	@Override
	public IPage<ContractPerformanceResponseVO> pageList(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance) {
		page=baseMapper.pageList(page, contractPerformance);
		IPage<ContractPerformanceResponseVO> pages= ContractPerformanceWrapper.build().entityPVPage(page);
		List<ContractPerformanceResponseVO> records = pages.getRecords();
		List<ContractPerformanceResponseVO> recordList = new ArrayList<>();
		for(ContractPerformanceResponseVO v : records) {
			ContractFormInfoEntity formInfoEntity = formInfoMapper.selectById(v.getContractId());
			if (Func.isNotEmpty(formInfoEntity)) {
				v.setContractFormInfoEntity(formInfoEntity);
				List<ContractCounterpartEntity> counterpartEntity = counterpartMapper.selectByIds(formInfoEntity.getId());
				v.setCounterpartEntityList(counterpartEntity);
				String endTime=plusDay(15,new SimpleDateFormat("yy-MM-dd").format(v.getPlanPayTime()));
				v.setPlanPayTimeEnd(endTime);
			}
			recordList.add(v);
		}
		pages.setRecords(recordList);
		return pages;
	}

	@Override
	public IPage<ContractPerformanceEntity> pageListSerious(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance) {
		page=baseMapper.pageListSerious(page, contractPerformance);
		return page;
	}

	@Override
	public IPage<ContractPerformanceEntity> pageListLong(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance) {
		page=baseMapper.pageListLong(page, contractPerformance);
		return page;
	}

	@Override
	public ContractPerformanceResponseVO getById(Long id) {
		ContractPerformanceEntity performanceEntity=baseMapper.selectById(id);
		ContractPerformanceResponseVO performanceResponseVO= ContractPerformanceWrapper.build().entityPV(performanceEntity);
		ContractFormInfoEntity formInfoEntity = formInfoMapper.selectById(performanceEntity.getContractId());
		if (Func.isNotEmpty(formInfoEntity)) {
			performanceResponseVO.setContractFormInfoEntity(formInfoEntity);
			List<ContractCounterpartEntity> counterpartEntity = counterpartMapper.selectByIds(formInfoEntity.getId());
			performanceResponseVO.setCounterpartEntityList(counterpartEntity);
		}
		return performanceResponseVO;
	}

	@Override
	public void savePerformance(ContractFormInfoRequestVO contractFormInfo) {
		List<ContractPerformanceEntity> performanceEntities=new ArrayList<>();
		//删除履约信息脏数据
		deleteByContractId(contractFormInfo.getId());
		contractFormInfo.getPerformanceList().forEach(performance -> {
			if (Func.isNotEmpty(performance.getId())) {
				performance.setId(null);
			}
			performance.setContractId(contractFormInfo.getId());
			performanceEntities.add(performance);
		});
		saveBatch(performanceEntities);
	}

	@Override
	public void deleteByContractId(Long id) {
		baseMapper.deleteByContractId(id);
	}
}
