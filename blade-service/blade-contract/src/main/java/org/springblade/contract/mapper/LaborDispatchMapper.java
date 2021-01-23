package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.LaborDispatchEntity;

/**
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） Mapper 接口
 *
 * @author wd
 * @date : 2021-01-22 15:16:13
 */
public interface LaborDispatchMapper extends BaseMapper<LaborDispatchEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param laborDispatch
	 * @return
	 */
	IPage<LaborDispatchEntity> pageList(IPage<LaborDispatchEntity> page, LaborDispatchEntity laborDispatch);

}
