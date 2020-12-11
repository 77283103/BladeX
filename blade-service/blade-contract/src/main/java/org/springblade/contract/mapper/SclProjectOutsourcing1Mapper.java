package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProjectOutsourcing1Entity;
import org.springblade.contract.vo.SclProjectOutsourcing1RequestVO;

/**
 * 生产类：生产项目外包服务合同 Mapper 接口
 *
 * @author kx
 * @date : 2020-12-11 11:05:05
 */
public interface SclProjectOutsourcing1Mapper extends BaseMapper<SclProjectOutsourcing1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclProjectOutsourcing1
	 * @return
	 */
	IPage<SclProjectOutsourcing1Entity> pageList(IPage<SclProjectOutsourcing1Entity> page, SclProjectOutsourcing1RequestVO sclProjectOutsourcing1);

}
