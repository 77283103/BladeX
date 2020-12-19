package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ActAppDatabasechangeloglockEntity;
import org.springblade.contract.vo.ActAppDatabasechangeloglockRequestVO;

/**
 * 售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1) Mapper 接口
 *
 * @author 售货机类
 * @date : 2020-12-18 16:13:49
 */
public interface ActAppDatabasechangeloglockMapper extends BaseMapper<ActAppDatabasechangeloglockEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param actAppDatabasechangeloglock
	 * @return
	 */
	IPage<ActAppDatabasechangeloglockEntity> pageList(IPage<ActAppDatabasechangeloglockEntity> page, ActAppDatabasechangeloglockRequestVO actAppDatabasechangeloglock);

}
