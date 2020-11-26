package org.springblade.contract.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springblade.contract.vo.ContractPerformanceRequestVO;
import org.springblade.contract.vo.ContractPerformanceResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Charsets;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.feign.IDictBizClient;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.wrapper.ContractPerformanceWrapper;
import org.springblade.contract.service.IContractPerformanceService;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 接收/提供服务计划清单 控制器
 *
 * @author : szw
 * @date : 2020-11-05 17:06:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractPerformance")
@Api(value = "接收/提供服务计划清单", tags = "接收/提供服务计划清单")
public class ContractPerformanceController extends BladeController {

	private IContractPerformanceService contractPerformanceService;
	private IDictBizClient bizClient;
	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractPerformance")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:detail')")
	public R<ContractPerformanceResponseVO> detail(@RequestParam Long id) {
		ContractPerformanceEntity detail = contractPerformanceService.getById(id);
		return R.data(ContractPerformanceWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractPerformance")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:page')")
	public R<IPage<ContractPerformanceResponseVO>> list(ContractPerformanceRequestVO contractPerformance, Query query) {
		IPage<ContractPerformanceResponseVO> pages = contractPerformanceService.pageList(Condition.getPage(query), contractPerformance);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractPerformance")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:add')")
	public R save(@Valid @RequestBody ContractPerformanceResponseVO contractPerformance) {
		return R.status(contractPerformanceService.save(ContractPerformanceWrapper.build().PVEntity(contractPerformance)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractPerformance")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:update')")
	public R update(@Valid @RequestBody ContractPerformanceResponseVO contractPerformance) {
	    if (Func.isEmpty(contractPerformance.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractPerformanceService.updateById(ContractPerformanceWrapper.build().PVEntity(contractPerformance)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractPerformanceService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 导出excel
	 * @param performanceEntity
	 * @param response
	 */
	@PostMapping("/exportTargetDataResult")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出", notes = "")
	public void exportTargetDataResult(@RequestBody ContractPerformanceResponseVO performanceEntity, HttpServletResponse response) {

		if (Func.isNotEmpty(performanceEntity)) {
			/* 导出文件名称 */
			String fileName = "接受/提供服务计划清单-信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("接受/提供服务计划清单-信息导出");
			sheet1.setSheetNo(0);
			/* 需要存入的数据 */
			List<Object> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据 要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
			List<Object> cloumns = new ArrayList<Object>();
			/*合同名称*/
			cloumns.add(performanceEntity.getContractFormInfoEntity().getContractName());
			/*合同编号*/
			cloumns.add(performanceEntity.getContractFormInfoEntity().getContractNumber());
			/*相对方名称*/
			StringBuilder name = new StringBuilder();
			for (ContractCounterpartEntity counterpartEntity : performanceEntity.getCounterpartEntityList()) {
				name.append(counterpartEntity.getName());
				name.append(",");
			}
			name.substring(0, name.length());
			cloumns.add(name.toString());
			/*服务内容*/
			cloumns.add(performanceEntity.getName());
			/*交易类型*/
			cloumns.add(bizClient.getValue("transaction_type",performanceEntity.getType()).getData());
			/*计划缴纳时间*/
			cloumns.add(performanceEntity.getPlanPayTime());
			/*计划缴纳金额*/
			cloumns.add(performanceEntity.getPlanPayAmount());
			/*实际缴纳时间*/
			cloumns.add(performanceEntity.getActualPayTime());
			/*实际缴纳金额*/
			cloumns.add(performanceEntity.getActualPayAmount());
			/*计划退回时间*/
			cloumns.add(performanceEntity.getPlanReturnTime());
			/*计划缴纳金额*/
			cloumns.add(performanceEntity.getPlanReturnAmount());
			/*实际退回时间*/
			cloumns.add(performanceEntity.getActualReturnTime());
			/*实际退回金额*/
			cloumns.add(performanceEntity.getActualReturnAmount());
			/*厂别*/
			cloumns.add(performanceEntity.getFactories());
			/*接受条件*/
			cloumns.add(performanceEntity.getAcceptanceConditions());
			/*状态*/
			cloumns.add(performanceEntity.getStatus());
			data.add(cloumns);
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("合同名称", "合同编号", "相对方名称", "服务内容", "交易类型", "计划缴纳时间",
					"计划缴纳金额", "实际缴纳时间","实际缴纳金额","计划退回时间","计划缴纳金额","实际退回时间","实际退回金额",
					"厂别","接受条件","状态");
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
