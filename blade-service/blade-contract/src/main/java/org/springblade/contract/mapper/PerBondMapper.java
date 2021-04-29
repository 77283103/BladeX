package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.PerBondEntity;
import org.springblade.contract.vo.PerBondListVo;
import org.springblade.contract.vo.PerBondRequestVO;


/**
 * 履约计划保证金 Mapper 接口
 *
 * @author chenzy
 * @date : 2021-04-27 17:06:21
 */
public interface PerBondMapper extends BaseMapper<PerBondEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perBond
	 * @return
	 */
	IPage<PerBondEntity> pageList(IPage<PerBondEntity> page, PerBondRequestVO perBond);

	/**
	 * 履约计划-保证金信息列表
	 * @param page
	 * @param perBond
	 * @return
	 */
	IPage<PerBondListVo> perBondList(IPage<PerBondEntity> page, PerBondRequestVO perBond);


}
