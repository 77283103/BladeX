package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.mapper.ContractSigningMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IContractSigningService;
import org.springblade.contract.vo.ContractSigningRequestVO;
import org.springblade.contract.vo.ContractSigningResponseVO;
import org.springblade.contract.wrapper.ContractSigningWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 合同签订表 服务实现类
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
@Service
@AllArgsConstructor
public class ContractSigningServiceImpl extends BaseServiceImpl<ContractSigningMapper, ContractSigningEntity> implements IContractSigningService {

	private IContractFormInfoService contractFormInfoService;

	private ContractFormInfoMapper contractFormInfoMapper;

	private IFileClient fileClient;



	@Override
	public IPage<ContractSigningEntity> pageList(IPage<ContractSigningEntity> page, ContractSigningRequestVO signing) {
		return baseMapper.pageList(page, signing);
	}

	@Override
	public boolean save(String contractStatus, ContractSigningEntity entity) {
		contractFormInfoService.updateExportStatus(contractStatus,entity.getContractId());
		if(baseMapper.insert(entity) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 保存签订关联
	 * @param vo
	 */
	@Override
	public void saveSigning(ContractSigningRequestVO vo) {
     contractFormInfoMapper.saveSigning(vo.getContractId(),vo.getId());
	}

	/**
	 * 合同id查询签订信息
	 * @param id
	 * @return
	 */
	@Override
	public ContractSigningResponseVO selectSigningById(Long id) {
		ContractSigningEntity signingEntity=baseMapper.selectSigningById(id);
		ContractSigningResponseVO signingResponseVO = ContractSigningWrapper.build().entityPV(signingEntity);
		//签订文本扫描件
		if (!Func.isEmpty(signingEntity)) {
			if (Func.isNoneBlank(signingEntity.getTextFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(signingEntity.getTextFiles());
				if (result.isSuccess()) {
					signingResponseVO.setSigningTextFileVOList(result.getData());
				}
			}
		}
		//签订附件扫描件
		if (!Func.isEmpty(signingEntity)) {
			if (Func.isNoneBlank(signingEntity.getAttachedFiles())) {
				R<List<FileVO>> result = fileClient.getByIds(signingEntity.getAttachedFiles());
				if (result.isSuccess()) {
					signingResponseVO.setSigningAttachedFileVOList(result.getData());
				}
			}
		}
		return signingResponseVO;
	}
}
