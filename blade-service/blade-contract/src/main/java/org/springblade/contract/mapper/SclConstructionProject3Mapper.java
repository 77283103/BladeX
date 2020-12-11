package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProject3Entity;
import org.springblade.contract.vo.SclConstructionProject3RequestVO;

/**
 * 生产类：加工承揽合同（代工合同）关联表3 Mapper 接口
 *
 * @author 生产类：加工承揽合同（代工合同）关联表3
 * @date : 2020-12-11 10:36:43
 */
public interface SclConstructionProject3Mapper extends BaseMapper<SclConstructionProject3Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclConstructionProject3
	 * @return
	 */
	IPage<SclConstructionProject3Entity> pageList(IPage<SclConstructionProject3Entity> page, SclConstructionProject3RequestVO sclConstructionProject3);

}
