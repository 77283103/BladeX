package org.springblade.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.DataSealAuthorityEntity;
import org.springblade.system.vo.DataSealAuthorityRequestVO;
import org.springblade.system.vo.DataSealAuthorityResponseVO;

/**
 * DataSealAuthority 服务类
 *
 * @author xhb
 * @date : 2021-04-12 16:51:01
 */
public interface IDataSealAuthorityService extends BaseService<DataSealAuthorityEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param dataSealAuthority
	 * @return
	 */
	IPage<DataSealAuthorityEntity> pageList(IPage<DataSealAuthorityEntity> page, DataSealAuthorityRequestVO dataSealAuthority);

	/**
	 * 详情查询
	 * @param id
	 * @return
	 */
	DataSealAuthorityResponseVO getById(Long id);

	/**
	 * 根据用户id查询是否数据权限
	 * @param id
	 * @Param roleId
	 * @return
	 */
	R<DataSealAuthorityResponseVO> getUserId(String id, String roleId);
}
