package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.SclEquipmentMaintenance1RequestVO;
import org.springblade.contract.vo.SclEquipmentMaintenance1ResponseVO;
import org.springblade.contract.wrapper.SclEquipmentMaintenance1Wrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;
import org.springblade.contract.mapper.SclEquipmentMaintenance1Mapper;
import org.springblade.contract.service.ISclEquipmentMaintenance1Service;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 生产类：设备维修保养合同(关联表） 服务实现类
 *
 * @author kx
 * @date : 2020-12-11 10:59:52
 */
@Service
public class SclEquipmentMaintenance1ServiceImpl extends BaseServiceImpl<SclEquipmentMaintenance1Mapper, SclEquipmentMaintenance1Entity> implements ISclEquipmentMaintenance1Service, ITableRef<SclEquipmentMaintenance1Entity> {
	/**
	 * 合同id字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<SclEquipmentMaintenance1Entity> pageList(IPage<SclEquipmentMaintenance1Entity> page, SclEquipmentMaintenance1RequestVO sclEquipmentMaintenance1) {
		return baseMapper.pageList(page, sclEquipmentMaintenance1);
	}

	@Override
	public void saveBatchByRefId(Long refId, List<SclEquipmentMaintenance1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, SclEquipmentMaintenance1Wrapper.build().PVEntityList(responseVOList),this);

	}

	@Override
	public List<SclEquipmentMaintenance1ResponseVO> selectRefList(Long refId) {
		List<SclEquipmentMaintenance1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return SclEquipmentMaintenance1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, SclEquipmentMaintenance1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
