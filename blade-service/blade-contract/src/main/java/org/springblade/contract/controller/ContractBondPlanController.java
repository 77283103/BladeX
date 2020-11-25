package org.springblade.contract.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.service.IContractBondPlanService;
import org.springblade.contract.service.IContractBondService;
import org.springblade.contract.vo.ContractBondPlanRequestVO;
import org.springblade.contract.vo.ContractBondPlanResponseVO;
import org.springblade.contract.vo.ContractBondRequestVO;
import org.springblade.contract.vo.ContractBondResponseVO;
import org.springblade.contract.wrapper.ContractBondPlanWrapper;
import org.springblade.contract.wrapper.ContractBondWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Charsets;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictBizClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 保证金 控制器
 *
 * @author : szw
 * @date : 2020-11-04 18:28:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractBondPlan")
@Api(value = "保证金", tags = "保证金")
public class ContractBondPlanController extends BladeController {

	private IContractBondPlanService contractBondService;
	private IDictBizClient bizClient;
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractBond")
	@PreAuth("hasPermission('contractBond:contractBondPlan:detail')")
	public R<ContractBondPlanResponseVO> detail(@RequestParam Long id) {
		ContractBondPlanEntity detail = contractBondService.getById(id);
		return R.data(ContractBondPlanWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractBond")
	@PreAuth("hasPermission('contractBond:contractBondPlan:page')")
	public R<IPage<ContractBondPlanResponseVO>> list(ContractBondPlanRequestVO contractBond, Query query) {
		IPage<ContractBondPlanResponseVO> pages = contractBondService.pageList(Condition.getPage(query), contractBond);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractBond")
	@PreAuth("hasPermission('contractBond:contractBondPlan:add')")
	public R save(@Valid @RequestBody ContractBondPlanResponseVO contractBond) {
		return R.status(contractBondService.save(ContractBondPlanWrapper.build().PVEntity(contractBond)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractBond")
	@PreAuth("hasPermission('contractBond:contractBondPlan:update')")
	public R update(@Valid @RequestBody ContractBondPlanResponseVO contractBond) {
	    if (Func.isEmpty(contractBond.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractBondService.updateById(ContractBondPlanWrapper.build().PVEntity(contractBond)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractBond:contractBondPlan:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractBondService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 导出excel
	 * @param bondPlanEntity
	 * @param response
	 */
	@PostMapping("/exportTargetDataResult")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出", notes = "")
	public void exportTargetDataResult(@RequestBody ContractBondPlanEntity bondPlanEntity, HttpServletResponse response) {

		if (Func.isNotEmpty(bondPlanEntity)) {
			/* 导出文件名称 */
			String fileName = "首付款计划清单-保证金信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("首付款计划清单-保证金信息导出");
			sheet1.setSheetNo(0);
			/* 需要存入的数据 */
			List<Object> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据 要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
			List<Object> cloumns = new ArrayList<Object>();
			/*保证金名称*/
			cloumns.add(bondPlanEntity.getBondName());
			/*有无保证金*/
			cloumns.add(bizClient.getValue("bond",bondPlanEntity.getIsNotBond()).getData());
			/*保证金类别*/
			cloumns.add(bizClient.getValue("bond_type",bondPlanEntity.getType()).getData());
			/*关联合同数量*/
			cloumns.add(bondPlanEntity.getContractNumber());
			/*币种*/
			cloumns.add(bizClient.getValue("bz",bondPlanEntity.getCurrencyCategory()).getData());
			/*计划缴纳时间*/
			cloumns.add(bondPlanEntity.getPlanPayTime());
			/*计划缴纳金额*/
			cloumns.add(bondPlanEntity.getPlanPayAmount());
			/*实际缴纳时间*/
			cloumns.add(bondPlanEntity.getActualPayTime());
			/*实际缴纳金额*/
			cloumns.add(bondPlanEntity.getActualPayAmount());
			/*计划退回时间*/
			cloumns.add(bondPlanEntity.getPlanReturnTime());
			/*计划缴纳金额*/
			cloumns.add(bondPlanEntity.getPlanReturnAmount());
			/*实际退回时间*/
			cloumns.add(bondPlanEntity.getActualReturnTime());
			/*实际退回金额*/
			cloumns.add(bondPlanEntity.getActualReturnAmount());
			data.add(cloumns);
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("保证金名称", "有无保证金", "保证金类别", "关联合同数量", "币种", "计划缴纳时间",
					"计划缴纳金额", "实际缴纳时间","实际缴纳金额","计划退回时间","计划缴纳金额","实际退回时间","实际退回金额");
			/* 为了生成一个独立的list对象，所进行的初始化 */
			List<String> head2 = null;
			for (String head1 : head) {
				head2 = new ArrayList<>();
				/* 将表头的数据赋值进入list对象 */
				head2.add(head1);
				/* 将数据赋值进入最终要输出的表头 */
				headList.add(head2);
			}
			try {
				response.setContentType("application/vnd.ms-excel");
				response.setCharacterEncoding(Charsets.UTF_8.name());
				fileName = URLEncoder.encode(fileName, Charsets.UTF_8.name());
				response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
				EasyExcel.write(response.getOutputStream()).head(headList).sheet().doWrite(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
