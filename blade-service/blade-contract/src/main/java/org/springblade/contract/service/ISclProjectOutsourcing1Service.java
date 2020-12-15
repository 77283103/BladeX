package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProjectOutsourcing1Entity;
import org.springblade.contract.vo.SclProjectOutsourcing1RequestVO;
import org.springblade.contract.vo.SclProjectOutsourcing1ResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 生产类：生产项目外包服务合同 服务类
 *
 * @author kx
 * @date : 2020-12-11 11:05:05
 */
public interface ISclProjectOutsourcing1Service extends BaseService<SclProjectOutsourcing1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclProjectOutsourcing1
	 * @return
	 */
	IPage<SclProjectOutsourcing1Entity> pageList(IPage<SclProjectOutsourcing1Entity> page, SclProjectOutsourcing1RequestVO sclProjectOutsourcing1);

	void saveBatchByRefId(Long refId, List<SclProjectOutsourcing1ResponseVO> responseVOList);

	List<SclProjectOutsourcing1ResponseVO> selectRefList(Long refId);
}
