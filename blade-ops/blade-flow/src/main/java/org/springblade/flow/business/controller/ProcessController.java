package org.springblade.flow.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.service.IProcessService;
import org.springblade.flow.business.vo.ProcessRequestVO;
import org.springblade.flow.business.vo.ProcessResponseVO;
import org.springblade.flow.business.wrapper.ProcessWrapper;
import org.springblade.flow.core.entity.ProcessEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 流程定义信息表 控制器
 *
 * @author tianah
 * @date 2020-9-9
 */
@RestController
@AllArgsConstructor
@RequestMapping("/process")
@Api(value = "流程定义信息表", tags = "流程定义信息表")
public class ProcessController extends BladeController {

	private IProcessService processService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入process")
	@PreAuth("hasPermission('flow:process:detail')")
	public R<ProcessResponseVO> detail(@RequestParam Long id) {
		ProcessEntity detail = processService.getById(id);
		return R.data(ProcessWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入process")
	@PreAuth("hasPermission('flow:process:list')")
	public R<IPage<ProcessResponseVO>> list(ProcessEntity process, Query query) {
		IPage<ProcessEntity> pages = processService.pageList(Condition.getPage(query), process);
		return R.data(ProcessWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入process")
	@PreAuth("hasPermission('flow:process:add')")
	public R save(@Valid @RequestBody ProcessRequestVO process) {
		ProcessEntity entity = new ProcessEntity();
		BeanUtil.copy(process, entity);
		return R.status(processService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入process")
	@PreAuth("hasPermission('flow:process:update')")
	public R update(@Valid @RequestBody ProcessRequestVO process) {
		if (Func.isEmpty(process.getId())) {
			throw new ServiceException("id不能为空");
		}
		ProcessEntity entity = new ProcessEntity();
		BeanUtil.copy(process, entity);
		return R.status(processService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('flow:process:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(processService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 根据业务类型反射获取bean的字段及类型
	 */
	@GetMapping("/bean-field")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "获取bean字段及类型", notes = "传入业务类型")
	@PreAuth("hasPermission('flow:process:bean-field')")
	public R<List<Map<String,String>>> getBeanFields(@RequestParam String businessType) {
		try {
			List<Map<String,String>> beanFields = processService.getBeanFields(businessType);
			return R.data(beanFields);
		} catch (ClassNotFoundException e) {
			throw new ServiceException("未获取到类："+businessType);
		}
	}

}
