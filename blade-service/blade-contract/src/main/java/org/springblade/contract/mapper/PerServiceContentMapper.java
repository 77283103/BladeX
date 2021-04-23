package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.PerServiceContentEntity;
import org.springblade.contract.vo.PerServiceContentRequestVO;

/**
 * 履约服务内容 Mapper 接口
 *
 * @author chenzy
 * @date : 2021-04-20 16:02:05
 */
public interface PerServiceContentMapper extends BaseMapper<PerServiceContentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perServiceContent
	 * @return
	 */
	IPage<PerServiceContentEntity> pageList(IPage<PerServiceContentEntity> page, PerServiceContentRequestVO perServiceContent);

}
