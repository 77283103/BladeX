package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclLogisticsServiceEntity;
import org.springblade.contract.vo.SclLogisticsServiceRequestVO;

/**
 * 生产类：物流服务合同（二段配送） Mapper 接口
 *
 * @author kx
 * @date : 2020-12-18 17:17:41
 */
public interface SclLogisticsServiceMapper extends BaseMapper<SclLogisticsServiceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclLogisticsService
	 * @return
	 */
	IPage<SclLogisticsServiceEntity> pageList(IPage<SclLogisticsServiceEntity> page, SclLogisticsServiceRequestVO sclLogisticsService);

}
