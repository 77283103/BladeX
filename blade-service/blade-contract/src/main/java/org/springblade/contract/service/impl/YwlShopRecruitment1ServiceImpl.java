package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.mapper.YwlShopRecruitment1Mapper;
import org.springblade.contract.vo.YwlShopRecruitment1RequestVO;
import org.springblade.contract.entity.YwlShopRecruitment1Entity;
import org.springblade.contract.service.IYwlShopRecruitment1Service;
import org.springblade.contract.vo.YwlShopRecruitment1ResponseVO;
import org.springblade.contract.wrapper.YwlShopRecruitment1Wrapper;
import org.springblade.core.mp.base.BaseService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.mp.support.ITableRef;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 业务类：21.新陈列协议书关联表 服务实现类
 *
 * @author szw
 * @date : 2020-12-06 13:51:41
 */
@Service
public class YwlShopRecruitment1ServiceImpl extends BaseServiceImpl<YwlShopRecruitment1Mapper, YwlShopRecruitment1Entity> implements IYwlShopRecruitment1Service, ITableRef<YwlShopRecruitment1Entity> {
	/**
	 * 新陈列协议书字段
	 */
	private static final String REF_COLUMN_NAME = "contract_id";
	@Override
	public IPage<YwlShopRecruitment1Entity> pageList(IPage<YwlShopRecruitment1Entity> page, YwlShopRecruitment1RequestVO ywlShopRecruitment1) {
		return baseMapper.pageList(page, ywlShopRecruitment1);
	}

	public void saveBatchByRefId(Long refId, List<YwlShopRecruitment1ResponseVO> responseVOList) {
		this.saveBatchByRefId(refId,REF_COLUMN_NAME, YwlShopRecruitment1Wrapper.build().PVEntityList(responseVOList),this);
	}

	public List<YwlShopRecruitment1ResponseVO> selectRefList(Long refId) {
		List<YwlShopRecruitment1Entity> casesAgentEntityList = this.selectRefList(refId, REF_COLUMN_NAME, this);
		return YwlShopRecruitment1Wrapper.build().entityPVList(casesAgentEntityList);
	}

	@Override
	public void setRefId(Long refId, YwlShopRecruitment1Entity entity) {
		entity.setId(null);
		entity.setContractId(refId);
	}
}
