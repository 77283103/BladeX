package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.RelieveEntity;

/**
 *  Mapper 接口
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
public interface RelieveMapper extends BaseMapper<RelieveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param relieve
	 * @return
	 */
	IPage<RelieveEntity> pageList(IPage<RelieveEntity> page, RelieveEntity relieve);

}
