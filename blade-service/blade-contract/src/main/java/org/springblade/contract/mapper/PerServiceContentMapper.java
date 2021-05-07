package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.PerServiceContentEntity;
import org.springblade.contract.vo.PerServiceContentListResponseVO;
import org.springblade.contract.vo.PerServiceContentRequestVO;
import org.springblade.contract.vo.PerServiceContentResponseVO;

import java.util.List;

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

	/**
	 * 履约计划 接收/提供服务清单
	 * @param page
	 * @param perServiceContent
	 * @return
	 */
	IPage<PerServiceContentListResponseVO> serviceContentList(IPage<PerServiceContentEntity> page, PerServiceContentRequestVO perServiceContent);

	/**
	 * 根据合同标识获取详情
	 * @param contractId
	 * @return
	 */
	List<PerServiceContentResponseVO> findInfoByContractId(Long contractId);

	/**
	 * 预警查询（获取超期未更新数据集合）
	 * @return
	 */
	List<PerServiceContentResponseVO> findWarningList();


}
