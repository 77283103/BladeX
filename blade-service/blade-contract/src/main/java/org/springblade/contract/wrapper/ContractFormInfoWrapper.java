package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.enums.ContractStatusEnum;
import org.springblade.contract.excel.importbatchdraft.ContractImportBatchDraftExcel;
import org.springblade.contract.util.IdGenUtil;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;

import java.util.Optional;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:38
 */
public class ContractFormInfoWrapper implements IEntityWrapper<ContractFormInfoEntity, ContractFormInfoRequestVO, ContractFormInfoResponseVO> {

	public static ContractFormInfoWrapper build() {
		return new ContractFormInfoWrapper();
	}

	@Override
	public ContractFormInfoEntity createEntity() {
		return new ContractFormInfoEntity();
	}

	@Override
	public ContractFormInfoRequestVO createQV() {
		return new ContractFormInfoRequestVO();
	}

	@Override
	public ContractFormInfoResponseVO createPV() {
		return new ContractFormInfoResponseVO();
	}

	@Override
	public void selectUserName(ContractFormInfoResponseVO responseVO) {
		responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
		responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
		responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
	}

	public ContractFormInfoEntity createEntityByBatchDraftExcel(ContractImportBatchDraftExcel contractImportBatchDraftExcel){
		//给合同赋值
		ContractFormInfoEntity contractFormInfoEntity = BeanUtil.copy(contractImportBatchDraftExcel,ContractFormInfoEntity.class);
		//自动生成ID
		contractFormInfoEntity.setId(IdGenUtil.generateId().longValue());
		//赋值JSON数据
		contractFormInfoEntity.setJson(contractImportBatchDraftExcel.getJson());
		//赋值合同状态
		contractFormInfoEntity.setContractStatus(ContractStatusEnum.DRAFT.getKey().toString());
		BladeUser user = AuthUtil.getUser();
		//创建合同创建记录信息
		contractFormInfoEntity.setCreateDept((Long) Func.toLongList(user.getDeptId()).iterator().next());
		contractFormInfoEntity.setCreateUser(user.getUserId());
		return contractFormInfoEntity;
	}



}
