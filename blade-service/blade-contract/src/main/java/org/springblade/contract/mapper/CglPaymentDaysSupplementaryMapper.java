package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.CglPaymentDaysSupplementaryEntity;
import org.springblade.contract.vo.CglPaymentDaysSupplementaryRequestVO;

/**
 * 采购类：账期补充协议--买卖合同 Mapper 接口
 *
 * @author 王策
 * @date : 2020-12-10 19:21:46
 */
public interface CglPaymentDaysSupplementaryMapper extends BaseMapper<CglPaymentDaysSupplementaryEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglPaymentDaysSupplementary
	 * @return
	 */
	IPage<CglPaymentDaysSupplementaryEntity> pageList(IPage<CglPaymentDaysSupplementaryEntity> page, CglPaymentDaysSupplementaryRequestVO cglPaymentDaysSupplementary);

}
