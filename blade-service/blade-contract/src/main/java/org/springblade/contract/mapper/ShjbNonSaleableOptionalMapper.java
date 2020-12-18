package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjbNonSaleableOptionalEntity;
import org.springblade.contract.vo.ShjbNonSaleableOptionalRequestVO;

/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本） Mapper 接口
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）
 * @date : 2020-12-18 16:01:17
 */
public interface ShjbNonSaleableOptionalMapper extends BaseMapper<ShjbNonSaleableOptionalEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param shjbNonSaleableOptional
	 * @return
	 */
	IPage<ShjbNonSaleableOptionalEntity> pageList(IPage<ShjbNonSaleableOptionalEntity> page, ShjbNonSaleableOptionalRequestVO shjbNonSaleableOptional);

}
