package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractFormInfoRequestVO;

/**
 *  Mapper 接口
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
public interface ContractFormInfoMapper extends BaseMapper<ContractFormInfoEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractFormInfo
	 * @return
	 */
	IPage<ContractFormInfoEntity> pageList(IPage<ContractFormInfoEntity> page, ContractFormInfoEntity contractFormInfo);

	/**
	 * 用印后修改合同状态
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

}
