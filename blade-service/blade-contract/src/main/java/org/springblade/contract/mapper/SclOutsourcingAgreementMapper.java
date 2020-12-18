package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclOutsourcingAgreementEntity;
import org.springblade.contract.vo.SclOutsourcingAgreementRequestVO;

/**
 * 生产类：作业外包协议 Mapper 接口
 *
 * @author kx
 * @date : 2020-12-18 16:08:25
 */
public interface SclOutsourcingAgreementMapper extends BaseMapper<SclOutsourcingAgreementEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclOutsourcingAgreement
	 * @return
	 */
	IPage<SclOutsourcingAgreementEntity> pageList(IPage<SclOutsourcingAgreementEntity> page, SclOutsourcingAgreementRequestVO sclOutsourcingAgreement);

}
