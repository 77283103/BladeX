package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.InferiorProductContractEntity;
import org.springblade.contract.mapper.InferiorProductContractMapper;
import org.springblade.contract.service.IMinferiorProductContractService;
import org.springframework.stereotype.Service;

/**
 * 下脚品买卖合同模板 服务实现类
 *
 * @author Wang Pengfei
 * @date : 2021-01-15 15:54:18
 */
@Service
public class MinferiorProductContractServiceImpl extends BaseServiceImpl<InferiorProductContractMapper, InferiorProductContractEntity> implements IMinferiorProductContractService {

	@Override
	public IPage<InferiorProductContractEntity> pageList(IPage<InferiorProductContractEntity> page, InferiorProductContractEntity inferiorProductContract) {
		return baseMapper.pageList(page, inferiorProductContract);
	}
}
