package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ArchiveEntity;

/**
 * 合同归档管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-23 18:32:13
 */
public interface ArchiveMapper extends BaseMapper<ArchiveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param archive
	 * @return
	 */
	IPage<ArchiveEntity> pageList(IPage<ArchiveEntity> page, ArchiveEntity archive);

}
