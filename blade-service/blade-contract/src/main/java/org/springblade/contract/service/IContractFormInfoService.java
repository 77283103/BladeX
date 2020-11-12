package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractSigningRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.system.vo.TemplateRequestVO;

/**
 *  服务类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
public interface IContractFormInfoService extends BaseService<ContractFormInfoEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoResponseVO> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo);

	/**
	 * 修改合同状态
	 * @param contractStatus,id
	 * @return
	 */
	boolean updateExportStatus(String contractStatus,Long id);

	/**
	 * 用印分页查询
	 * @param page
	 * @param contractFormInfoRequestVO
	 * @return
	 */
	IPage<ContractFormInfoEntity> pageListSealInfo(IPage<ContractFormInfoRequestVO> page, ContractFormInfoRequestVO contractFormInfoRequestVO);


	/**
	 * 相对方保存方法
	 * @param vo 合同信息
	 * @return
	 */
	void saveCounterpart(ContractFormInfoRequestVO vo);

	/**
	 * 依据保存方法
	 * @param vo 合同信息
	 * @return
	 */
	void saveAccording(ContractFormInfoRequestVO vo);

	/**
	 * 保存合同用印关联id数据方法
	 * @param vo
	 */
	void saveSeal(ContractFormInfoRequestVO vo);

	/**
	 * 合同评估保存方法
	 * @param vo
	 */
	void saveAssessment(ContractFormInfoRequestVO vo);


	/**
	 * 合同归档保存方法
	 * @param vo 获取对应的合同id和归档id属性
	 */
	void saveArchive(ContractFormInfoRequestVO vo);

	/**
	 * 合同签订保存方法
	 * @param vo 从合同vo里面获取对应id数据
	 */
	void saveSigning(ContractFormInfoRequestVO vo);

	/**
	 * 合同详情
	 * @param id 合同id
	 * @return
	 */
	ContractFormInfoResponseVO getById(Long id);

	/**
	 *统计合同导出次数
	 * @param id 合同id
	 * @param fileExportCount 下载次数
	 * @param fileExportCategory 下载状态
	 */
	boolean textExportCount(Long id,Integer fileExportCount,String  fileExportCategory);


	/**
	 *范本起草保存
	 * @param template 合同模板
	 */
	ContractFormInfoEntity templateDraft(TemplateRequestVO template);
}
