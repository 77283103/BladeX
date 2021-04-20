package org.springblade.system.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.mapper.DataSealAuthorityMapper;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import  org.springblade.system.entity.DataSealAuthorityEntity;
import  org.springblade.system.wrapper.DataSealAuthorityWrapper;
import  org.springblade.system.service.IDataSealAuthorityService;
import  org.springblade.system.vo.DataSealAuthorityRequestVO;
import  org.springblade.system.vo.DataSealAuthorityResponseVO;


/**
 * DataSealAuthority 控制器
 *
 * @author : xhb
 * @date : 2021-04-12 16:50:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dataSealAuthority")
@Api(value = "DataSealAuthority", tags = "DataSealAuthority")
public class DataSealAuthorityController extends BladeController {

	private IDataSealAuthorityService dataSealAuthorityService;
	private DataSealAuthorityMapper dataSealAuthorityMapper;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入dataSealAuthority")
	public R<DataSealAuthorityResponseVO> detail(@RequestParam Long id) {
		DataSealAuthorityResponseVO detail = dataSealAuthorityService.getById(id);
		return R.data(detail);
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入dataSealAuthority")
	public R<IPage<DataSealAuthorityResponseVO>> list(DataSealAuthorityRequestVO dataSealAuthority, Query query) {
		IPage<DataSealAuthorityEntity> pages = dataSealAuthorityService.pageList(Condition.getPage(query), dataSealAuthority);
		return R.data(DataSealAuthorityWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入dataSealAuthority")
	public R save(@Valid @RequestBody DataSealAuthorityResponseVO dataSealAuthority) {
		DataSealAuthorityEntity entity = new DataSealAuthorityEntity();
		BeanUtil.copy(dataSealAuthority, entity);
		StringBuilder name = new StringBuilder();
		dataSealAuthority.getSealList().forEach(seal->{
			name.append(seal);
			name.append(",");
		});
		name.substring(0, name.length());
		entity.setSeal(name.toString());
		if (Func.isNotEmpty(dataSealAuthorityMapper.selectByUserIds(dataSealAuthority.getUserId(), dataSealAuthority.getRoleId()))){
			return R.data(2,"该用户已经是该角色，请切换或新增角色设置","该用户已经是该角色，请切换或新增角色设置");
		}else {
			dataSealAuthorityService.save(entity);
		}
		return R.data(true);
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入dataSealAuthority")
	public R update(@Valid @RequestBody DataSealAuthorityResponseVO dataSealAuthority) {
	    if (Func.isEmpty(dataSealAuthority.getId())){
            throw new ServiceException("id不能为空");
        }
		DataSealAuthorityEntity entity = new DataSealAuthorityEntity();
		BeanUtil.copy(dataSealAuthority, entity);
		StringBuilder name = new StringBuilder();
		dataSealAuthority.getSealList().forEach(seal->{
			name.append(seal);
			name.append(",");
		});
		name.substring(0, name.length());
		entity.setSeal(name.toString());
		return R.status(dataSealAuthorityService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('datareal:dataSealAuthority:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(dataSealAuthorityService.deleteLogic(Func.toLongList(ids)));
	}

}
