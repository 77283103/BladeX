package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.PerPlanFinshContentRequestVO;
import org.springblade.contract.vo.PerPlanFinshTimeRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.PerPlanFinshContentEntity;

import java.util.List;

/**
 * 履约计划完成内容 服务类
 *
 * @author chenzy
 * @date : 2021-04-20 16:28:58
 */
public interface IPerPlanFinshContentService extends BaseService<PerPlanFinshContentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perPlanFinshContent
	 * @return
	 */
	IPage<PerPlanFinshContentEntity> pageList(IPage<PerPlanFinshContentEntity> page, PerPlanFinshContentRequestVO perPlanFinshContent);

	/**
	 * 批量添加计划完成内容
	 * @param perPlanFinshContentEntityList
	 * @return
	 */
	Boolean addListData(List<PerPlanFinshContentEntity> perPlanFinshContentEntityList);

	/**
	 * 根据合同标识删除
	 * @param contractId
	 */
	void deleteByContractId(Long contractId);

	/**
	 * 验证计划完成内容数据集合
	 * @param perPlanFinshContentEntityList
	 * @return
	 */
	Boolean verificationByList(List<PerPlanFinshContentEntity> perPlanFinshContentEntityList);

	/**
	 * 根据计划完成时间请求数据生成计划完成内容
	 * @param perPlanFinshTimeRequestVOList
	 * @return
	 */
	List<PerPlanFinshContentEntity> generateListByFinshTimeList(List<PerPlanFinshTimeRequestVO>perPlanFinshTimeRequestVOList);


	/**
	 * 根据计划完成时间请求数据新增计划完成内容
	 * @param perPlanFinshTimeRequestVOList
	 * @return
	 */
	Boolean addPlanContentDataByPlanTimeList(List<PerPlanFinshTimeRequestVO> perPlanFinshTimeRequestVOList);
}
