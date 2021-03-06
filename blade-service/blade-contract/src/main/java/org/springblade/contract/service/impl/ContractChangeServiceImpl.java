package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractChangeEntity;
import org.springblade.contract.mapper.ContractChangeMapper;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.IContractChangeService;
import org.springblade.contract.vo.ContractChangeResponseVO;
import org.springblade.contract.wrapper.ContractChangeWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.resource.feign.IFileClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 合同变更 服务实现类
 *
 * @author szw
 * @date : 2020-09-23 19:24:50
 */
@Service
@AllArgsConstructor
public class ContractChangeServiceImpl extends BaseServiceImpl<ContractChangeMapper, ContractChangeEntity> implements IContractChangeService {

	private IFileClient fileClient;
	private ContractChangeMapper contractChangeMapper;
	private ContractFormInfoMapper formInfoMapper;
	@Override
	public IPage<ContractChangeEntity> pageList(IPage<ContractChangeEntity> page, ContractChangeEntity change) {
		return baseMapper.pageList(page, change);
	}

	@Override
	public void deleteByChangeId(Long id) {
		contractChangeMapper.deleteByChangeId(id);
	}

	@Override
	public boolean updateExportStatus(String contractStatus, Long id) {
		return formInfoMapper.updateExportStatus(contractStatus,id);
	}

	@Override
	public ContractChangeResponseVO getById(Long id) {
		ContractChangeEntity changeEntity=contractChangeMapper.selectById(id);
		ContractChangeResponseVO changeResponseVO= ContractChangeWrapper.build().entityVO(changeEntity);
		return changeResponseVO;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@Override
	public ContractChangeEntity selectByContractIdChangeInfo(Long id){
		return contractChangeMapper.selectById(id);
	}
	/**
	 * 删除上传的文件
	 * @param id
	 */
	@Override
	public boolean deleteByChangeFileId(Long id) {
		ContractChangeEntity changeEntity=baseMapper.selectByFileId(id);
		List<Long> list = new ArrayList<>();
		String [] ids = changeEntity.getSuppleAgreement().split(",");
		for (int i = 0; i <ids.length; i++) {
			list.add(Long.valueOf(ids[i]));
		}
		fileClient.remove(list);
		contractChangeMapper.deleteChangeFileById(id);
		return true;
	}
}
