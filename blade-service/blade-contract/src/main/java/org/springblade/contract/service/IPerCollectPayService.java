package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.excel.importbatchdraft.PerCollectPayImportBatchDraftExcel;
import org.springblade.contract.vo.PerCollectPayListResponseVO;
import org.springblade.contract.vo.PerCollectPayRequestVO;
import org.springblade.contract.vo.PerCollectPayResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.PerCollectPayEntity;

import java.util.List;

/**
 * 履约收付款 服务类
 *
 * @author chenzy
 * @date : 2021-04-25 10:32:29
 */
public interface IPerCollectPayService extends BaseService<PerCollectPayEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param perCollectPay
	 * @return
	 */
	IPage<PerCollectPayEntity> pageList(IPage<PerCollectPayEntity> page, PerCollectPayRequestVO perCollectPay);


	/**
	 * 履约计划-收付款信息列表
	 * @param page
	 * @param perCollectPay
	 * @return
	 */
	IPage<PerCollectPayListResponseVO> perCollectPayList(IPage<PerCollectPayEntity> page, PerCollectPayRequestVO perCollectPay);


	/**
	 * 添加收付款信息
	 * @param perCollectPayRequestVOList
	 * @return
	 */
	Boolean addListData(List<PerCollectPayRequestVO> perCollectPayRequestVOList,Long contractId);

	/**
	 * 根据合同id获取集合信息
	 * @param contractId
	 * @return
	 */
	List<PerCollectPayResponseVO> findListByContractId(Long contractId);


	/**
	 * 批量保存模板信息
	 * @param perCollectPayImportBatchDraftExcels
	 * @param contractId
	 * @return
	 */
	List<PerCollectPayEntity> saveByBatchDraftExcels(List<PerCollectPayImportBatchDraftExcel> perCollectPayImportBatchDraftExcels,Long contractId);

}
