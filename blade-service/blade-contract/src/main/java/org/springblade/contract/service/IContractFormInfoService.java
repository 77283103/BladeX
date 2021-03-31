package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.excel.ContractFormInfoImporter;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.core.tool.api.R;
import org.springblade.resource.vo.FileVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
	 * 統計分析查詢列表
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> statisticsList(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo);

	/**
	 * 统计分析分页查询
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoResponseVO> pageListStatistics(IPage<ContractFormInfoEntity> page, ContractFormInfoRequestVO contractFormInfo);
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
	 * 根据合同id查询变更原合同历史版本列表
	 * @param id
	 * @return
	 */
	ContractFormInfoResponseVO getByChangeHistoryId(Long id);
	/**
	 *统计合同导出次数
	 * @param id 合同id
	 * @param fileExportCount 下载次数
	 * @param fileExportCategory 下载状态
	 */
	boolean textExportCount(Long id,Integer fileExportCount,String  fileExportCategory);

	/**
	 * 电子签章业务处理
	 *
	 * @param entity            合同信息
	 * @return 返回统计状态
	 */
	ContractFormInfoEntity SingleSign(ContractFormInfoEntity entity);

	/**
	 *范本起草保存
	 * @param contractFormInfoEntity 合同模板
	 */
	ContractFormInfoEntity templateDraft(ContractFormInfoEntity contractFormInfoEntity,String json);

	/**
	 * 合同大类金额
	 * @return list
	 */
	List<ContractFormInfoEntity> getAmountList();

	/**
	 * 合同大类数量
	 * @return list
	 */
	List<ContractFormInfoEntity> getNumList();

	/**
	 * 動態返回合同選擇的下拉選
	 * @return list
	 */
	List<ContractFormInfoEntity> getChooseList();
	/**
	 * 导入合同数据
	 * @param data
	 */
	void importContractFormInfo(List<ContractFormInfoImporter> data, MultipartFile file, String json, String contractTemplateId, String contractBigCategory, String contractSmallCategory);

	/**
	 * 根据合同类型查询已签订合同数量
	 * @param contractBigCategory
	 * @return
	 */
	Integer selectSigningCount(String contractBigCategory);


	/**
	 * 查询有编号的合同
	 * @param entity
	 * @return
	 */
	List<ContractFormInfoEntity>  selectByContractNumber(ContractFormInfoEntity entity);

	/**
	 * 判断电子签章
	 * @param entity
	 * @param file
	 * @return
	 */
	R singleSignIsNot(ContractFormInfoRequestVO entity, FileVO file);

	/**
	 * 变更合同详情
	 * @param id
	 * @return
	 */
	ContractFormInfoEntity selectByChangeId(Long id);
}
