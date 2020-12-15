package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject2Entity;
import org.springblade.contract.vo.SclConstructionProject2RequestVO;

/**
 * 生产类：加工承揽合同（代工合同）关联表2 Mapper 接口
 *
 * @author 生产类：加工承揽合同（代工合同）关联表2
 * @date : 2020-12-11 10:22:08
 */
public interface SclConstructionProject2Mapper extends BaseMapper<SclConstructionProject2Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclConstructionProject2
	 * @return
	 */
	IPage<SclConstructionProject2Entity> pageList(IPage<SclConstructionProject2Entity> page, SclConstructionProject2RequestVO sclConstructionProject2);

}
