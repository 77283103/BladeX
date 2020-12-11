package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtlEditedTheContractRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtlEditedTheContractEntity;
import org.springblade.contract.mapper.MtlEditedTheContractMapper;
import org.springblade.contract.service.IMtlEditedTheContractService;
import org.springframework.stereotype.Service;

/**
 * 媒体类：修图合同 服务实现类
 *
 * @author 媒体类：修图合同
 * @date : 2020-12-10 19:24:50
 */
@Service
public class MtlEditedTheContractServiceImpl extends BaseServiceImpl<MtlEditedTheContractMapper, MtlEditedTheContractEntity> implements IMtlEditedTheContractService {

	@Override
	public IPage<MtlEditedTheContractEntity> pageList(IPage<MtlEditedTheContractEntity> page, MtlEditedTheContractRequestVO mtlEditedTheContract) {
		return baseMapper.pageList(page, mtlEditedTheContract);
	}
}
