package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.DraftContractCounterpartEntity;
import org.springblade.contract.mapper.DraftContractCounterpartMapper;
import org.springblade.contract.service.IDraftContractCounterparService;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 相对方管理  服务实现类
 *
 * @author XHB
 * @date : 2021-01-18 17:24:27
 */
@Service
@AllArgsConstructor
public class DraftContractCounterparServiceImpl extends BaseServiceImpl<DraftContractCounterpartMapper, DraftContractCounterpartEntity> implements IDraftContractCounterparService {
	private final DraftContractCounterpartMapper draftContractCounterpartMapper;

	@Override
	public IPage<DraftContractCounterpartEntity> pageList(IPage<DraftContractCounterpartEntity> page, DraftContractCounterpartEntity contractDestruction) {
		return baseMapper.pageList(page, contractDestruction);
	}

	@Override
	public List<DraftContractCounterpartEntity> selectByContractId(Long id) {
		return draftContractCounterpartMapper.selectByContractId(id);
	}

	@Override
	public void saveDraftContractCounterpart(ContractFormInfoRequestVO vo) {
		draftContractCounterpartMapper.deleteDraftCounterpart(vo.getId());
		draftContractCounterpartMapper.saveDraftCounterpart(vo.getDraftContractCounterpartList());
	}

}
