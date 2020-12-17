package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.mapper.ContractCounterpartMapper;
import org.springblade.contract.service.IContractCounterpartService;
import org.springblade.contract.vo.ContractCounterpartRequestVO;
import org.springblade.contract.vo.ContractCounterpartResponseVO;
import org.springblade.contract.wrapper.ContractCounterpartWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 相对方管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-23 19:35:06
 */
@Service
@AllArgsConstructor
public class ContractCounterpartServiceImpl extends BaseServiceImpl<ContractCounterpartMapper, ContractCounterpartEntity> implements IContractCounterpartService {

	private IFileClient fileClient;

	@Override
	public IPage<ContractCounterpartEntity> pageList(IPage<ContractCounterpartEntity> page, ContractCounterpartRequestVO counterpart) {
		page=baseMapper.pageList(page, counterpart);
		return page;
	}

	/**
	 * 重写向对方vo方法返回附件 包装返回视图层
	 * @param id
	 * @return
	 */
	@Override
	public ContractCounterpartResponseVO getById(Long id) {
		ContractCounterpartEntity counterpartEntity=baseMapper.selectById(id);
		ContractCounterpartResponseVO counterpartResponseVO= ContractCounterpartWrapper.build().entityVO(counterpartEntity);
		//查询依据附件
		//@Func.isNoneBlank判断是否全为非空字符串
		if (Func.isNoneBlank(counterpartEntity.getAttachedFiles())){
			R<List<FileVO>> result = fileClient.getByIds(counterpartEntity.getAttachedFiles());
			if (result.isSuccess()){
				counterpartResponseVO.setCounterpartFilesVOList(result.getData());
			}
		}
		return counterpartResponseVO;
	}
}
