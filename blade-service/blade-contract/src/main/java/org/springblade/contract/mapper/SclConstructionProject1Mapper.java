package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject1Entity;
import org.springblade.contract.vo.SclConstructionProject1RequestVO;

/**
 * 生产类：加工承揽合同（代工合同）关联表 Mapper 接口
 *
 * @author 生产类：加工承揽合同（代工合同）关联表
 * @date : 2020-12-11 10:10:13
 */
public interface SclConstructionProject1Mapper extends BaseMapper<SclConstructionProject1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclConstructionProject1
	 * @return
	 */
	IPage<SclConstructionProject1Entity> pageList(IPage<SclConstructionProject1Entity> page, SclConstructionProject1RequestVO sclConstructionProject1);

}
