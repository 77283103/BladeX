package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglTheSalesContract1Entity;
import org.springblade.contract.vo.CglTheSalesContract1RequestVO;

/**
 * 采购类：新增原物料补充协议--买卖合同 Mapper 接口
 *
 * @author 采购类：新增原物料补充协议--买卖合同
 * @date : 2020-12-10 18:50:20
 */
public interface CglTheSalesContract1Mapper extends BaseMapper<CglTheSalesContract1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglTheSalesContract1
	 * @return
	 */
	IPage<CglTheSalesContract1Entity> pageList(IPage<CglTheSalesContract1Entity> page, CglTheSalesContract1RequestVO cglTheSalesContract1);

}
