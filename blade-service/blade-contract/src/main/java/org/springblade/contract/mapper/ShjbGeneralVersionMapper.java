package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ShjbGeneralVersionEntity;
import org.springblade.contract.vo.ShjbGeneralVersionRequestVO;

/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本）） Mapper 接口
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
 * @date : 2020-12-18 16:02:25
 */
public interface ShjbGeneralVersionMapper extends BaseMapper<ShjbGeneralVersionEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param shjbGeneralVersion
	 * @return
	 */
	IPage<ShjbGeneralVersionEntity> pageList(IPage<ShjbGeneralVersionEntity> page, ShjbGeneralVersionRequestVO shjbGeneralVersion);

}
