package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProjectOutsourcing1Entity;
import org.springblade.contract.mapper.SclProjectOutsourcing1Mapper;
import org.springblade.contract.service.ISclProjectOutsourcing1Service;
import org.springblade.contract.vo.SclProjectOutsourcing1RequestVO;
import org.springblade.contract.vo.SclProjectOutsourcing1ResponseVO;
import org.springblade.contract.wrapper.SclProjectOutsourcing1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产类：生产项目外包服务合同 服务实现类
 *
 * @author kx
 * @date : 2020-12-11 11:05:06
 */
@Service
public class SclProjectOutsourcing1ServiceImpl extends BaseServiceImpl<SclProjectOutsourcing1Mapper, SclProjectOutsourcing1Entity> implements ISclProjectOutsourcing1Service, ITableRef<SclProjectOutsourcing1Entity> {
	/**
	 * 合同关联字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<SclProjectOutsourcing1Entity> pageList(IPage<SclProjectOutsourcing1Entity> page, SclProjectOutsourcing1RequestVO sclProjectOutsourcing1) {
		return baseMapper.pageList(page, sclProjectOutsourcing1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<SclProjectOutsourcing1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, SclProjectOutsourcing1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<SclProjectOutsourcing1ResponseVO> selectRefList(Long refId) {
		List<SclProjectOutsourcing1Entity> SclProjectOutsourcing1EntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return SclProjectOutsourcing1Wrapper.build().entityPVList(SclProjectOutsourcing1EntityList);
	}


	@Override
	public void setRefId(Long refId, SclProjectOutsourcing1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
