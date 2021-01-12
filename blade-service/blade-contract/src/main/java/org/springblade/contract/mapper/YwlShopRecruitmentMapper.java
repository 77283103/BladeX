package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwiShopRecruitmentEntity;
import org.springblade.contract.vo.YwlShopRecruitmentRequestVO;

/**
 * 业务类：14.店招合同 Mapper 接口
 *
 * @author szw
 * @date : 2020-12-04 19:04:55
 */
public interface YwlShopRecruitmentMapper extends BaseMapper<YwiShopRecruitmentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlShopRecruitment
	 * @return
	 */
	IPage<YwiShopRecruitmentEntity> pageList(IPage<YwiShopRecruitmentEntity> page, YwlShopRecruitmentRequestVO ywlShopRecruitment);

}
