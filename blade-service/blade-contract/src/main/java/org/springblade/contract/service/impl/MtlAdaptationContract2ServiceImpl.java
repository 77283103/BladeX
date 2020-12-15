package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.MtlAdaptationContract1Entity;
import org.springblade.contract.vo.MtlAdaptationContract1ResponseVO;
import org.springblade.contract.vo.MtlAdaptationContract2RequestVO;
import org.springblade.contract.vo.MtlAdaptationContract2ResponseVO;
import org.springblade.contract.wrapper.MtlAdaptationContract1Wrapper;
import org.springblade.contract.wrapper.MtlAdaptationContract2Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtlAdaptationContract2Entity;
import org.springblade.contract.mapper.MtlAdaptationContract2Mapper;
import org.springblade.contract.service.IMtlAdaptationContract2Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：视频广告改编合同关联表2 服务实现类
 *
 * @author 媒体类：视频广告改编合同关联表2
 * @date : 2020-12-11 08:36:47
 */
@Service
public class MtlAdaptationContract2ServiceImpl extends BaseServiceImpl<MtlAdaptationContract2Mapper, MtlAdaptationContract2Entity> implements IMtlAdaptationContract2Service, ITableRef<MtlAdaptationContract2Entity> {
	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlAdaptationContract2Entity> pageList(IPage<MtlAdaptationContract2Entity> page, MtlAdaptationContract2RequestVO mtlAdaptationContract2) {
		return baseMapper.pageList(page, mtlAdaptationContract2);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlAdaptationContract2ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlAdaptationContract2Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtlAdaptationContract2ResponseVO> selectRefList(Long refId) {
		List<MtlAdaptationContract2Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlAdaptationContract2Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, MtlAdaptationContract2Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
