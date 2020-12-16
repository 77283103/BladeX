package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.YwlANewDisplay1RequestVO;
import org.springblade.contract.vo.YwlANewDisplay1ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.YwlANewDisplay1Entity;

import java.util.List;

/**
 * 业务类：21.新陈列协议书关联表 服务类
 *
 * @author kx
 * @date : 2020-12-16 16:42:39
 */
public interface IYwlANewDisplay1Service extends BaseService<YwlANewDisplay1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlANewDisplay1
	 * @return
	 */
	IPage<YwlANewDisplay1Entity> pageList(IPage<YwlANewDisplay1Entity> page, YwlANewDisplay1RequestVO ywlANewDisplay1);

	void saveBatchByRefId(Long refId, List<YwlANewDisplay1ResponseVO> responseVOList);

	List<YwlANewDisplay1ResponseVO> selectRefList(Long refId);
}
