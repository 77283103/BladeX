package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwlAnewDisplayEntity;
import org.springblade.contract.vo.YwlANewDisplayRequestVO;

/**
 * 业务类：21.新陈列协议书 Mapper 接口
 *
 * @author szw
 * @date : 2020-12-07 15:37:42
 */
public interface YwlANewDisplayMapper extends BaseMapper<YwlAnewDisplayEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywlANewDisplay
	 * @return
	 */
	IPage<YwlAnewDisplayEntity> pageList(IPage<YwlAnewDisplayEntity> page, YwlANewDisplayRequestVO ywlANewDisplay);

}
