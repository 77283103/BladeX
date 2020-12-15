package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;
import org.springblade.contract.vo.MtlAdaptationContract1RequestVO;
import org.springblade.contract.vo.MtlAdaptationContract1ResponseVO;
import org.springblade.contract.wrapper.MtlAdaptationContract1Wrapper;
import org.springblade.contract.wrapper.SclEquipmentMaintenance1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.MtlAdaptationContract1Entity;
import org.springblade.contract.mapper.MtlAdaptationContract1Mapper;
import org.springblade.contract.service.IMtlAdaptationContract1Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 媒体类：视频广告改编合同关联表 服务实现类
 *
 * @author 媒体类：视频广告改编合同关联表
 * @date : 2020-12-11 08:36:16
 */
@Service
public class MtlAdaptationContract1ServiceImpl extends BaseServiceImpl<MtlAdaptationContract1Mapper, MtlAdaptationContract1Entity> implements IMtlAdaptationContract1Service, ITableRef<MtlAdaptationContract1Entity> {

	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<MtlAdaptationContract1Entity> pageList(IPage<MtlAdaptationContract1Entity> page, MtlAdaptationContract1RequestVO mtlAdaptationContract1) {
		return baseMapper.pageList(page, mtlAdaptationContract1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<MtlAdaptationContract1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, MtlAdaptationContract1Wrapper.build().PVEntityList(responseVOList),this);
	}

	@Override
	public List<MtlAdaptationContract1ResponseVO> selectRefList(Long refId) {
		List<MtlAdaptationContract1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return MtlAdaptationContract1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, MtlAdaptationContract1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
