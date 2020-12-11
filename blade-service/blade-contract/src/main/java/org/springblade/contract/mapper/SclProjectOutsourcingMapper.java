package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProjectOutsourcingEntity;
import org.springblade.contract.vo.SclProjectOutsourcingRequestVO;

/**
 * 生产类：生产项目外包服务合同 Mapper 接口
 *
 * @author kx
 * @date : 2020-12-11 11:03:50
 */
public interface SclProjectOutsourcingMapper extends BaseMapper<SclProjectOutsourcingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclProjectOutsourcing
	 * @return
	 */
	IPage<SclProjectOutsourcingEntity> pageList(IPage<SclProjectOutsourcingEntity> page, SclProjectOutsourcingRequestVO sclProjectOutsourcing);

}
