package org.springblade.system.wrapper;

import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.vo.DataSealAuthorityResponseVO;
import org.springblade.system.vo.DataSealAuthorityRequestVO;
import org.springblade.system.entity.DataSealAuthorityEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * DataSealAuthority 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2021-04-12 16:51:02
 */
@Component
public class DataSealAuthorityWrapper implements IEntityWrapper<DataSealAuthorityEntity, DataSealAuthorityRequestVO, DataSealAuthorityResponseVO> {

	public static DataSealAuthorityWrapper build() {
		return new DataSealAuthorityWrapper();
 	}

    @Override
	public DataSealAuthorityEntity createEntity() {
		return new DataSealAuthorityEntity();
	}

	@Override
	public DataSealAuthorityRequestVO createQV() {
		return new DataSealAuthorityRequestVO();
	}

	@Override
	public DataSealAuthorityResponseVO createPV() {
		return new DataSealAuthorityResponseVO();
	}

    @Override
    public void selectUserName(DataSealAuthorityResponseVO responseVO) {
    }
}
