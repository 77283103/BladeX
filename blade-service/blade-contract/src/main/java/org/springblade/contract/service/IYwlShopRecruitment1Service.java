package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.YwlShopRecruitment1RequestVO;
import org.springblade.contract.vo.YwlShopRecruitment1ResponseVO;
import org.springblade.contract.wrapper.YwlShopRecruitment1Wrapper;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.YwlShopRecruitment1Entity;

import java.util.List;

/**
 * 业务类：21.新陈列协议书关联表 服务类
 *
 * @author szw
 * @date : 2020-12-06 13:51:41
 */
public interface IYwlShopRecruitment1Service extends BaseService<YwlShopRecruitment1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlShopRecruitment1
	 * @return
	 */
	IPage<YwlShopRecruitment1Entity> pageList(IPage<YwlShopRecruitment1Entity> page, YwlShopRecruitment1RequestVO ywlShopRecruitment1);

	void saveBatchByRefId(Long refId, List<YwlShopRecruitment1ResponseVO> responseVOList);

	List<YwlShopRecruitment1ResponseVO> selectRefList(Long refId);
}
