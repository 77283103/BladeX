package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.vo.ContractTemplateRequestVO;

import java.util.List;

/**
 * 范本管理 Mapper 接口
 *
 * @author XHB
 * @date : 2020-09-24 13:57:36
 */
public interface ContractTemplateMapper extends BaseMapper<ContractTemplateEntity> {

	/**
	 * 分页查询
	 *
	 * @param page
	 * @param template
	 * @return
	 */
	IPage<ContractTemplateEntity> pageList(IPage<ContractTemplateEntity> page, ContractTemplateRequestVO template);


	/**
	 * 批量废弃后修改范本状态
	 *
	 * @param templateStatus
	 * @param ids
	 * @return
	 */
	boolean updateEachTemplateStatus(String templateStatus, String ids);

	/**
	 * 废弃范本后修改范本状态
	 * @param templateStatus
	 * @param id
	 * @return
	 */
	boolean updateTemplateStatus(String templateStatus, Long id,String enabled);
	/**
	 * 更新范本是否启用
	 * @param id
	 * @return
	 */
	boolean updateTemplateEnabled(@Param("enabled") String enabled,
								  @Param("id") Long id);
	/**
	 * 历史版本列表查询
	 * @param id
	 * @return
	 */
    List<ContractTemplateEntity> versionInfo(Long id);

	/**
	 * 根据范本id插叙最新范本信息
	 * @param id
	 * @return
	 */
	ContractTemplateEntity latestById(Long id);

	/**
	 * 新增範本編號
	 * @param type
	 * @param number
	 * @return
	 */
	boolean instertTemplateNumbered(@Param("type") String type, @Param("number") Integer number);

	/**
	 * 修改範本類型的編號存入庫内
	 * @param type
	 * @param number
	 * @return
	 */
	boolean updateTemplateNumbered(@Param("type") String type, @Param("number") Integer number);
	/**
	 * 根據範本編號類型查詢範本編號
	 * @param type
	 * @return
	 */
	Integer selectByIdTemplateNumber(@Param("type") String type);

	/**
	 * 根据范本id查询范本使用率
	 * @param id
	 * @return
	 */
	Integer selectByIdUsageRate(Long id);
	/**
	 * 根据范本id查询范本履约中数量
	 * @param id
	 * @return
	 */
	Integer selectByIdFulfillingCount(Long id);
	/**
	 * 根据范本id查询范本已完成数量
	 * @param id
	 * @return
	 */
	Integer selectByIdCompletedCount(Long id);

	/**
	 * 标准范本总和计数
	 * @return
	 */
	Integer selectByIdTemplateCount();

	/**
	 *根据范本名称合同范本模板编号筛选是否存在新增重复范本
	 * @param templateName
	 * @param templateCode
	 * @return
	 */
	List<ContractTemplateEntity> FilterDuplicates(@Param("templateName") String templateName,
												  @Param("templateCode") String templateCode);

}
