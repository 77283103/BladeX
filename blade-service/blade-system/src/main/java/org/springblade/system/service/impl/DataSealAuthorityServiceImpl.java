package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import  org.springblade.system.entity.DataSealAuthorityEntity;
import  org.springblade.system.mapper.DataSealAuthorityMapper;
import  org.springblade.system.service.IDataSealAuthorityService;
import org.springblade.system.vo.DataSealAuthorityResponseVO;
import org.springblade.system.wrapper.DataSealAuthorityWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import  org.springblade.system.vo.DataSealAuthorityRequestVO;

import java.util.Arrays;

/**
 * DataSealAuthority 服务实现类
 *
 * @author xhb
 * @date : 2021-04-12 16:51:01
 */
@AllArgsConstructor
@Service
public class DataSealAuthorityServiceImpl extends BaseServiceImpl<DataSealAuthorityMapper, DataSealAuthorityEntity> implements IDataSealAuthorityService {
private DataSealAuthorityMapper dataSealAuthorityMapper;
	@Override
	public IPage<DataSealAuthorityEntity> pageList(IPage<DataSealAuthorityEntity> page, DataSealAuthorityRequestVO dataSealAuthority) {
		return baseMapper.pageList(page, dataSealAuthority);
	}

	@Override
	public DataSealAuthorityResponseVO getById(Long id) {
		DataSealAuthorityEntity dataSealAuthorityEntity= baseMapper.selectById(id);
		DataSealAuthorityResponseVO responseVO= DataSealAuthorityWrapper.build().entityPV(dataSealAuthorityEntity);
		if (Func.isNotEmpty(dataSealAuthorityEntity)) {
			String[] code = dataSealAuthorityEntity.getSeal().split(",");
			responseVO.setSealList(Arrays.asList(code));
		}
		return responseVO;
	}

	/**
	 * 根据用户id合同角色id查找对应的数据权限
	 * @param id
	 * @param roleId
	 * @return
	 */
	@Override
	public R<DataSealAuthorityResponseVO> getUserId(String id, String roleId) {
		DataSealAuthorityEntity dataSealAuthorityEntity=dataSealAuthorityMapper.selectByUserIds(id,roleId);
		DataSealAuthorityResponseVO responseVO= DataSealAuthorityWrapper.build().entityPV(dataSealAuthorityEntity);
		if (Func.isNotEmpty(dataSealAuthorityEntity)) {
			String[] code = dataSealAuthorityEntity.getSeal().split(",");
			responseVO.setSealList(Arrays.asList(code));
			return R.data(HttpStatus.OK.value(),responseVO,"OK");
		}else {
			return R.data(HttpStatus.NOT_FOUND.value(),null,"该用户拥有合同管理员权限，但未配置管理合同数据");
		}
	}
}
