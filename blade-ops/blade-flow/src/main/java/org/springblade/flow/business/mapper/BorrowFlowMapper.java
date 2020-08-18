package org.springblade.flow.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.flow.core.entity.BorrowFlowEntity;

/**
 * 借阅 Mapper 接口
 *
 * @author Liu Meng
 */
public interface BorrowFlowMapper extends BaseMapper<BorrowFlowEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param borrowFlow
	 * @return
	 */
	IPage<BorrowFlowEntity> pageList(@Param("page") IPage<BorrowFlowEntity> page, @Param("borrowFlow")BorrowFlowEntity borrowFlow);

}
