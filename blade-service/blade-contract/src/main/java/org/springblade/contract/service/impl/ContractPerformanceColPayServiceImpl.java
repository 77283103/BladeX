package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.feign.IContractClient;
import org.springblade.contract.mapper.ContractCounterpartMapper;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractPerformanceColPayRequestVO;
import org.springblade.contract.vo.ContractPerformanceColPayResponseVO;
import org.springblade.contract.vo.ContractPerformanceResponseVO;
import org.springblade.contract.wrapper.ContractPerformanceColPayWrapper;
import org.springblade.contract.wrapper.ContractPerformanceWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springblade.contract.mapper.ContractPerformanceColPayMapper;
import org.springblade.contract.service.IContractPerformanceColPayService;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.springblade.contract.service.impl.ContractBondPlanServiceImpl.plusDay;

/**
 * 收付款计划清单-收付款 服务实现类
 *
 * @author szw
 * @date : 2020-11-05 17:07:02
 */
@Service
@AllArgsConstructor
public class ContractPerformanceColPayServiceImpl extends BaseServiceImpl<ContractPerformanceColPayMapper, ContractPerformanceColPayEntity> implements IContractPerformanceColPayService {
	private ContractFormInfoMapper formInfoMapper;
	private IContractFormInfoService formInfoService;
	private ContractCounterpartMapper counterpartMapper;
	private ContractPerformanceColPayMapper performanceColPayMapper;
	private IContractClient contractClient;
	@SneakyThrows
	@Override
	public IPage<ContractPerformanceColPayResponseVO> pageList(IPage<ContractPerformanceColPayEntity> page, ContractPerformanceColPayRequestVO contractPerformanceColPay) {
		page=baseMapper.pageList(page,contractPerformanceColPay);
		IPage<ContractPerformanceColPayResponseVO> pages= ContractPerformanceColPayWrapper.build().entityPVPage(page);
		List<ContractPerformanceColPayResponseVO> records = pages.getRecords();
		List<ContractPerformanceColPayResponseVO> recordList = new ArrayList<>();
		for(ContractPerformanceColPayResponseVO v : records) {
			ContractFormInfoEntity formInfoEntity = formInfoMapper.selectById(v.getContractId());
			if (Func.isNotEmpty(formInfoEntity)) {
				v.setContractFormInfoEntity(formInfoEntity);
				List<ContractCounterpartEntity> counterpartEntity = counterpartMapper.selectByIds(formInfoEntity.getId());
				v.setCounterpartEntityList(counterpartEntity);
				String endTime=plusDay(15, new SimpleDateFormat("yy-MM-dd").format(v.getPlanPayTime()));
				v.setPlanPayTimeEnd(endTime);
			}
		}
		return pages;
	}

	@Override
	public IPage<ContractPerformanceColPayEntity> pageListSerious(IPage<ContractPerformanceColPayEntity> page, ContractPerformanceColPayRequestVO contractPerformanceColPay) {
		page=baseMapper.pageListSerious(page,contractPerformanceColPay);
		return page;
	}

	@Override
	public IPage<ContractPerformanceColPayEntity> pageListLong(IPage<ContractPerformanceColPayEntity> page, ContractPerformanceColPayRequestVO contractPerformanceColPay) {
		page=baseMapper.pageListLong(page,contractPerformanceColPay);
		return page;
	}

	@Override
	public ContractPerformanceColPayResponseVO getById(Long id) {
		ContractPerformanceColPayEntity performanceColPayEntity=baseMapper.selectById(id);
		ContractPerformanceColPayResponseVO performanceColPayResponseVO=ContractPerformanceColPayWrapper.build().entityPV(performanceColPayEntity);
		ContractFormInfoEntity formInfoEntity = formInfoMapper.selectById(performanceColPayEntity.getContractId());
		if (Func.isNotEmpty(formInfoEntity)) {
			performanceColPayEntity.setContractFormInfoEntity(formInfoEntity);
			List<ContractCounterpartEntity> counterpartEntity = counterpartMapper.selectByIds(formInfoEntity.getId());
			performanceColPayEntity.setCounterpartEntityList(counterpartEntity);
		}
		return performanceColPayResponseVO;
	}

	@Override
	public ContractPerformanceColPayEntity savePerformanceColPay(ContractPerformanceColPayEntity contractPerformanceColPay) {
		performanceColPayMapper.deleteByContractId(contractPerformanceColPay.getContractId());
		performanceColPayMapper.insert(contractPerformanceColPay);
		return contractPerformanceColPay;
	}

	@Override
	public void deleteByContractId(Long id) {
		baseMapper.deleteByContractId(id);
	}
}
