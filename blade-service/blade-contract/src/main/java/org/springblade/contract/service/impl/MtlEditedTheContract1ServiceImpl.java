package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlEditedTheContract1Entity;
import org.springblade.contract.mapper.MtlEditedTheContract1Mapper;
import org.springblade.contract.service.IMtlEditedTheContract1Service;
import org.springblade.contract.vo.MtlEditedTheContract1RequestVO;
import org.springblade.contract.vo.MtlEditedTheContract1ResponseVO;
import org.springblade.contract.wrapper.MtlEditedTheContract1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：修图合同关联表 服务实现类
 *
 * @author 媒体类：修图合同关联表
 * @date : 2020-12-11 05:00:49
 */
@Service
public class MtlEditedTheContract1ServiceImpl extends BaseServiceImpl<MtlEditedTheContract1Mapper, MtlEditedTheContract1Entity> implements IMtlEditedTheContract1Service, ITableRef<MtlEditedTheContract1Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlEditedTheContract1Entity> pageList(IPage<MtlEditedTheContract1Entity> page, MtlEditedTheContract1RequestVO mtlEditedTheContract1) {
		return baseMapper.pageList(page, mtlEditedTheContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlEditedTheContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlEditedTheContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtlEditedTheContract1ResponseVO> selectRefList(Long refId) {
		List<MtlEditedTheContract1Entity> MtlEditedTheContract1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlEditedTheContract1Wrapper.build().entityPVList(MtlEditedTheContract1EntityList);
	}

	@Override
	public void setRefId(Long refId, MtlEditedTheContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
