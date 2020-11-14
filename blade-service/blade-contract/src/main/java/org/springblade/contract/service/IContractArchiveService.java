package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.vo.ContractArchiveRequestVO;

/**
 * 合同归档 服务类
 *
 * @author 合同归档
 * @date : 2020-11-05 09:41:39
 */
public interface IContractArchiveService extends BaseService<ContractArchiveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractArchive
	 * @return
	 */
	IPage<ContractArchiveEntity> pageList(IPage<ContractArchiveEntity> page, ContractArchiveRequestVO contractArchive);

    /**
    * @Description: 合同归档信息excel 导出
    * @Param:
    * @return:
    * @Author: lm
    * @Date: 2020/11/14 13:58
    */
    void exportTargetDataResult();
}
