package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.mapper.ContractAccordingMapper;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.IContractAccordingService;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.contract.vo.ContractAccordingResponseVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 合同依据管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-24 14:20:32
 */
@AllArgsConstructor
@Service
public class ContractAccordingServiceImpl extends BaseServiceImpl<ContractAccordingMapper, ContractAccordingEntity> implements IContractAccordingService {

    private final ContractAccordingMapper contractAccordingMapper;
	private final ContractFormInfoMapper contractFormInfoMapper;
	@Autowired
	private RedisTemplate redisTemplate;


	@Override
	public IPage<ContractAccordingResponseVO> pageList(IPage<ContractAccordingResponseVO> page, ContractAccordingRequestVO according) {
		page= baseMapper.pageList(page, according);
		return page;
	}

	@Override
	public void saveAccording(ContractAccordingEntity accordingEntity, Long id) {
		List<ContractAccordingEntity> list = new ArrayList<>();
		List<String> acList = new ArrayList<>();
		List<String> acIdList = new ArrayList<>();
		//根据合同ID查询关联依据  用于处理新增，修改操作所产生的脏数据
		Integer count = contractAccordingMapper.selectByContractIds(id);
		if (count != 0) {
			contractAccordingMapper.deleteAccording(id);
		}
		/********保存新新依据数据到依据库 START*******/
		//根据依据的文件编号查询依据是否已经存在于依据库
		acList.add(accordingEntity.getFileId());
		List<ContractAccordingEntity> ac = contractAccordingMapper.selectByFileId(acList);
		if (Func.isEmpty(ac)) {
			baseMapper.insert(accordingEntity);
			//并把新增的依据set替换到合同依据
			list.add(accordingEntity);
			contractFormInfoMapper.saveAccording(id, list);
		} else {
			ac.forEach(foe -> {
				acIdList.add(foe.getId().toString());
			});
			contractFormInfoMapper.saveAccordingIds(id, acIdList);
		}
		/**********保存新新依据数据到依据库END********/
	}


	@Override
	public void deleteByContractId(Long id) {
		//baseMapper.selectByContractId(id);
		baseMapper.deleteAccording(id);
	}
	@Override
	public ContractAccordingEntity selectAccordingById(Long id) {
		return baseMapper.selectOne(Wrappers.<ContractAccordingEntity>query().lambda().eq(ContractAccordingEntity::getContractId, id).eq(ContractAccordingEntity::getIsDeleted, BladeConstant.DB_NOT_DELETED));
	}

	@Override
	public ContractAccordingEntity selectAccordingByCode(String code) {
		BladeUser user = AuthUtil.getUser();
		String j= String.valueOf(redisTemplate.opsForValue().get(user.getAccount()+"-according"));
		if(!j.equals("null")){
			ContractAccordingEntity contractAccordingEntity = JsonUtil.parse(j,ContractAccordingEntity.class);
			if(code.equals(contractAccordingEntity.getCode())){
				save(contractAccordingEntity);
				return contractAccordingEntity;
			}
		}
		return baseMapper.findListByCode(code);
	}

	@Override
	public void saveByBatchDraft(Long accordingId, List<Long> contractIds) {
		 baseMapper.saveByBatchDraft(accordingId,contractIds);
	}

}
