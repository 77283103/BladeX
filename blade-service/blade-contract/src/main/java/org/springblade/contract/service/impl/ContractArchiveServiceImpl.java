package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import org.springblade.contract.service.IContractArchiveNotService;
import org.springblade.contract.vo.ContractArchiveNotResponseVO;
import org.springblade.contract.vo.ContractArchiveResponseVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.wrapper.ContractArchiveWrapper;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.mapper.ContractArchiveMapper;
import org.springblade.contract.service.IContractArchiveService;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractArchiveRequestVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 合同归档 服务实现类
 *
 * @author 合同归档
 * @date : 2020-11-05 09:41:39
 */
@Service
@AllArgsConstructor
public class ContractArchiveServiceImpl extends BaseServiceImpl<ContractArchiveMapper, ContractArchiveEntity> implements IContractArchiveService {

	private IContractArchiveNotService archiveNotService;

	@Override
	public IPage<ContractArchiveResponseVO> pageList(IPage<ContractArchiveEntity> page, ContractArchiveRequestVO contractArchive) {
		page=baseMapper.pageList(page, contractArchive);
		IPage<ContractArchiveResponseVO> pages = ContractArchiveWrapper.build().entityPVPage(page);
		List<ContractArchiveResponseVO> records = pages.getRecords();
		List<ContractArchiveResponseVO> recordList = new ArrayList<>();
		records.forEach(archive ->{
			List<ContractArchiveNotResponseVO> archiveNotResponseVO=archiveNotService.getOldById(archive.getId());
			archiveNotResponseVO.forEach(archiveNot ->{
				archive.setNotArchiveReason(archiveNot.getNotArchiveReason());
			});
			recordList.add(archive);
		});
		pages.setRecords(recordList);
		return pages;
	}

	@Override
	public ContractArchiveResponseVO getById(Long id) {
		ContractArchiveEntity archiveEntity=baseMapper.selectById(id);
		ContractArchiveResponseVO archiveResponseVO= ContractArchiveWrapper.build().entityPV(archiveEntity);
		return archiveResponseVO;
	}


}
