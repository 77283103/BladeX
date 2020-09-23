package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ChangeEntity;

/**
 * 合同变更 Mapper 接口
 *
 * @author szw
 * @date : 2020-09-23 19:24:50
 */
public interface ChangeMapper extends BaseMapper<ChangeEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param change
	 * @return
	 */
	IPage<ChangeEntity> pageList(IPage<ChangeEntity> page, ChangeEntity change);

}
