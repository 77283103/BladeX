package org.springblade.contract.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.MtbProductionContract3RequestVO;
import org.springblade.contract.vo.MtbProductionContract3ResponseVO;
import org.springblade.contract.entity.MtbProductionContract3Entity;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 包装类,返回视图层所需的字段
 *
 * @author 韩杨
 * @date : 2021-01-21 11:27:02
 */
@Component
public class MtbProductionContract3Wrapper implements IEntityWrapper<MtbProductionContract3Entity, MtbProductionContract3RequestVO, MtbProductionContract3ResponseVO> {

	public static MtbProductionContract3Wrapper build() {
		return new MtbProductionContract3Wrapper();
 	}

    @Override
	public MtbProductionContract3Entity createEntity() {
		return new MtbProductionContract3Entity();
	}

	@Override
	public MtbProductionContract3RequestVO createQV() {
		return new MtbProductionContract3RequestVO();
	}

	@Override
	public MtbProductionContract3ResponseVO createPV() {
		return new MtbProductionContract3ResponseVO();
	}

	@Override
	public void selectUserName(MtbProductionContract3ResponseVO responseVO) {
		responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
		responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
		responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
	}

	@Override
	public MtbProductionContract3RequestVO entityQV(MtbProductionContract3Entity entity) {
		return null;
	}

	@Override
	public MtbProductionContract3ResponseVO entityPV(MtbProductionContract3Entity entity) {
		return null;
	}

	@Override
	public MtbProductionContract3Entity QVEntity(MtbProductionContract3RequestVO requestVO) {
		return null;
	}

	@Override
	public MtbProductionContract3Entity PVEntity(MtbProductionContract3ResponseVO responseVO) {
		return null;
	}

	@Override
	public List<MtbProductionContract3RequestVO> entityQVList(List<MtbProductionContract3Entity> list) {
		return null;
	}

	@Override
	public List<MtbProductionContract3ResponseVO> entityPVList(List<MtbProductionContract3Entity> list) {
		return null;
	}

	@Override
	public List<MtbProductionContract3Entity> QVEntityList(List<MtbProductionContract3RequestVO> list) {
		return null;
	}

	@Override
	public List<MtbProductionContract3Entity> PVEntityList(List<MtbProductionContract3ResponseVO> list) {
		return null;
	}

	@Override
	public IPage<MtbProductionContract3ResponseVO> entityPVPage(IPage<MtbProductionContract3Entity> page) {
		return null;
	}
}
