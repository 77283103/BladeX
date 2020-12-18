package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclServiceEntity;
import org.springblade.contract.vo.SclServiceRequestVO;

/**
 * 生产类：物流服务合同（二段仓储+配送） Mapper 接口
 *
 * @author kx
 * @date : 2020-12-18 17:08:03
 */
public interface SclServiceMapper extends BaseMapper<SclServiceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclService
	 * @return
	 */
	IPage<SclServiceEntity> pageList(IPage<SclServiceEntity> page, SclServiceRequestVO sclService);

}
