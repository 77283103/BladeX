package org.springblade.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.lettuce.core.dynamic.annotation.Param;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.system.entity.DataSealAuthorityEntity;
import org.springblade.system.vo.DataSealAuthorityRequestVO;

/**
 * DataSealAuthority Mapper 接口
 *
 * @author xhb
 * @date : 2021-04-12 16:51:00
 */
public interface DataSealAuthorityMapper extends BaseMapper<DataSealAuthorityEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param dataSealAuthority
	 * @return
	 */
	IPage<DataSealAuthorityEntity> pageList(IPage<DataSealAuthorityEntity> page, DataSealAuthorityRequestVO dataSealAuthority);

	/**
	 *
	 * @param userId
	 *  @param roleId
	 * @return
	 */
	DataSealAuthorityEntity selectByUserIds(@Param("user_id") String userId,
											@Param("role_id") String roleId);

}
