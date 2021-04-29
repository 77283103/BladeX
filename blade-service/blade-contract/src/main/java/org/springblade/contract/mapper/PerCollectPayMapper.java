package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.PerCollectPayEntity;
import org.springblade.contract.vo.PerCollectPayListResponseVO;
import org.springblade.contract.vo.PerCollectPayRequestVO;

/**
 * 履约收付款 Mapper 接口
 *
 * @author chenzy
 * @date : 2021-04-25 10:32:28
 */
public interface PerCollectPayMapper extends BaseMapper<PerCollectPayEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perCollectPay
	 * @return
	 */
	IPage<PerCollectPayEntity> pageList(IPage<PerCollectPayEntity> page, PerCollectPayRequestVO perCollectPay);


	/**
	 * 履约计划信息-收付款列表
	 * @param page
	 * @param perCollectPay
	 * @return
	 */
	IPage<PerCollectPayListResponseVO> perCollectPayList(IPage<PerCollectPayEntity> page, PerCollectPayRequestVO perCollectPay);


}
