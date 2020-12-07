package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwlShopRecruitment1Entity;
import org.springblade.contract.vo.YwlShopRecruitment1RequestVO;

/**
 * 业务类：21.新陈列协议书关联表 Mapper 接口
 *
 * @author szw
 * @date : 2020-12-06 13:51:40
 */
public interface YwlShopRecruitment1Mapper extends BaseMapper<YwlShopRecruitment1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlShopRecruitment1
	 * @return
	 */
	IPage<YwlShopRecruitment1Entity> pageList(IPage<YwlShopRecruitment1Entity> page, YwlShopRecruitment1RequestVO ywlShopRecruitment1);

}
