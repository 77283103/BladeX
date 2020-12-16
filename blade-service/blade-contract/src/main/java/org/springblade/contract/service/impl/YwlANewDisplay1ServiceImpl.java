package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.YwlANewDisplay1RequestVO;
import org.springblade.contract.vo.YwlANewDisplay1ResponseVO;
import org.springblade.contract.wrapper.YwlANewDisplay1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.YwlANewDisplay1Entity;
import org.springblade.contract.mapper.YwlANewDisplay1Mapper;
import org.springblade.contract.service.IYwlANewDisplay1Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务类：21.新陈列协议书关联表 服务实现类
 *
 * @author kx
 * @date : 2020-12-16 16:42:40
 */
@Service
public class YwlANewDisplay1ServiceImpl extends BaseServiceImpl<YwlANewDisplay1Mapper, YwlANewDisplay1Entity> implements IYwlANewDisplay1Service, ITableRef<YwlANewDisplay1Entity> {

	/**
	 * 新陈列协议书字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<YwlANewDisplay1Entity> pageList(IPage<YwlANewDisplay1Entity> page, YwlANewDisplay1RequestVO ywlANewDisplay1) {
		return baseMapper.pageList(page, ywlANewDisplay1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<YwlANewDisplay1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, YwlANewDisplay1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<YwlANewDisplay1ResponseVO> selectRefList(Long refId) {
		List<YwlANewDisplay1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return YwlANewDisplay1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, YwlANewDisplay1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
