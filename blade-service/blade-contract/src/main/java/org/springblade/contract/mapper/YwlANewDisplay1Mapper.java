package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwlANewDisplay1Entity;
import org.springblade.contract.vo.YwlANewDisplay1RequestVO;

/**
 * 业务类：21.新陈列协议书关联表 Mapper 接口
 *
 * @author kx
 * @date : 2020-12-16 16:42:39
 */
public interface YwlANewDisplay1Mapper extends BaseMapper<YwlANewDisplay1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlANewDisplay1
	 * @return
	 */
	IPage<YwlANewDisplay1Entity> pageList(IPage<YwlANewDisplay1Entity> page, YwlANewDisplay1RequestVO ywlANewDisplay1);

}
