package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclConstructionProjectEntity;
import org.springblade.contract.vo.SclConstructionProjectRequestVO;

/**
 * 生产类：加工承揽合同（代工合同） Mapper 接口
 *
 * @author 生产类：加工承揽合同（代工合同）
 * @date : 2020-12-11 09:52:21
 */
public interface SclConstructionProjectMapper extends BaseMapper<SclConstructionProjectEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclConstructionProject
	 * @return
	 */
	IPage<SclConstructionProjectEntity> pageList(IPage<SclConstructionProjectEntity> page, SclConstructionProjectRequestVO sclConstructionProject);

}
