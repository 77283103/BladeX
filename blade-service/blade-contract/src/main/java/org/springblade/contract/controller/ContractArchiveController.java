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

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Sheet;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.service.IContractArchiveNotService;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.*;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.SysCache;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.user.cache.UserCache;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.wrapper.ContractArchiveWrapper;
import org.springblade.contract.service.IContractArchiveService;
import org.springblade.contract.vo.ContractArchiveRequestVO;
import org.springblade.contract.vo.ContractArchiveResponseVO;

import java.util.Date;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;


/**
 * 合同归档 控制器
 *
 * @author : 合同归档
 * @date : 2020-11-05 09:41:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/archive")
@Api(value = "合同归档", tags = "合同归档")
public class ContractArchiveController extends BladeController {

	private IDictBizClient dictBizClient;
	private IContractArchiveService contractArchiveService;
	private IContractArchiveNotService contractArchiveNotService;
	private IContractFormInfoService contractFormInfoService;
	private static final String CONTRACT_ARCHIVE_SAVE_STATUS="110";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractArchive")
	@PreAuth("hasPermission('contract:archive:detail')")
	public R<ContractArchiveResponseVO> detail(@RequestParam Long id) {
		ContractArchiveEntity detail = contractArchiveService.getById(id);
		return R.data(ContractArchiveWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractArchive")
	@PreAuth("hasPermission('contract:archive:page')")
	public R<IPage<ContractArchiveResponseVO>> list(ContractArchiveRequestVO contractArchive, Query query) {
		IPage<ContractArchiveEntity> pages = contractArchiveService.pageList(Condition.getPage(query), contractArchive);
		return R.data(ContractArchiveWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增归档
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractArchive")
	@PreAuth("hasPermission('contract:archive:add')")
	public R save(@Valid @RequestBody ContractArchiveResponseVO contractArchive) {
		String contractStatus=CONTRACT_ARCHIVE_SAVE_STATUS;
		contractFormInfoService.updateExportStatus(contractStatus,contractArchive.getContractId());
		return R.status(contractArchiveService.save(ContractArchiveWrapper.build().PVEntity(contractArchive)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractArchive")
	@PreAuth("hasPermission('contract:archive:update')")
	public R update(@Valid @RequestBody ContractArchiveResponseVO contractArchive) {
	    if (Func.isEmpty(contractArchive.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractArchiveService.updateById(ContractArchiveWrapper.build().PVEntity(contractArchive)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:archive:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractArchiveService.deleteLogic(Func.toLongList(ids)));
	}
	/**
	 * 获取当前用户登录信息
	 * @return
	 */
	@GetMapping("/logInfo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询登当前录用户信息")
	@PreAuth("hasPermission('contract:archive:logInfo')")
	public R<ContractArchiveResponseVO> logInfo() {
		ContractArchiveResponseVO responseVO = ContractArchiveWrapper.build().createPV();
		BladeUser user = AuthUtil.getUser();
		Long userId = Long.valueOf(user.getUserId());
		Long deptId = Long.valueOf(AuthUtil.getDeptId());
		Date now = new Date();
		responseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
		responseVO.setCreateDeptName(SysCache.getDeptName(deptId));
		responseVO.setCreateSystemTime(now);
		return R.data(responseVO);
	}

	/**
	 * 导出excel
	 * @param formInfoEntityList
	 * @param response
	 */
	@PostMapping("/exportTargetDataResult")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出", notes = "")
	public void exportTargetDataResult(@RequestBody List<ContractFormInfoResponseVO> formInfoEntityList, HttpServletResponse response) {
		if(CollectionUtil.isNotEmpty(formInfoEntityList)){
			 /* 导出文件名称 */
			String  fileName = "合同归档信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("合同归档信息导出");
			sheet1.setSheetNo(0);

			/* 需要存入的数据 */
			List<List<Object>> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			for(ContractFormInfoResponseVO contractFormInfoEntity:formInfoEntityList){
				/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据
				  要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
				List<Object> cloumns = new ArrayList<Object>();
				/* 合同编号*/
				cloumns.add(contractFormInfoEntity.getContractNumber());
                 /* 合同名称 */
				cloumns.add(contractFormInfoEntity.getContractName());
				/* 合同一级分类 */
				cloumns.add(dictBizClient.getValues("HTDL",Long.valueOf(contractFormInfoEntity.getContractBigCategory())).getData());
				/* 合同二级分类 */
				cloumns.add(dictBizClient.getValues("HTDL",Long.valueOf(contractFormInfoEntity.getContractSmallCategory())).getData());
				/*合同相对方名称*/
				StringBuilder name=new  StringBuilder();
				for(ContractCounterpartEntity counterpartEntity:contractFormInfoEntity.getCounterpart()){
					name.append(counterpartEntity.getName());
					name.append(",");
				}
				name.substring(0,name.length());
				cloumns.add(name.toString());
				/* 合同金额 */
				cloumns.add(contractFormInfoEntity.getContractAmount());
				/* 合同状态*/
				cloumns.add(dictBizClient.getValue("contractStatus",contractFormInfoEntity.getContractStatus()).getData());
				/*归档月份*/
				cloumns.add(contractFormInfoEntity.getArchiveMonth());
				/*合同期限起始时间*/
				cloumns.add(contractFormInfoEntity.getContractStartingTime());
				/*合同期限截止时间*/
				cloumns.add(contractFormInfoEntity.getContractEndTime());
				/*审核完成日期*/
				cloumns.add(contractFormInfoEntity.getCreateTime());
				/*邮寄日期*/
				cloumns.add(contractFormInfoEntity.getSignDate());
				/*份数*/
				cloumns.add(contractFormInfoEntity.getShare());
				/*用印日期*/
				cloumns.add(contractFormInfoEntity.getSignTime());
				/*用印申请人*/
				cloumns.add(contractFormInfoEntity.getPrintApplicant());
				/*用印申请单位*/
				cloumns.add(contractFormInfoEntity.getContractPrintInitDept());
				/*用印公司*/
				cloumns.add(contractFormInfoEntity.getPrintCompany());
				data.add(cloumns);
			}
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("合同编号","合同名称","合同一级分类","合同二级分类","合同相对方名称","合同金额","合同状态","归档月份","合同期限起始时间","合同期限截止时间","审核完成日期","邮寄日期","份数","用印日期","用印申请人", "用印申请单位", "用印公司");
			/* 为了生成一个独立的list对象，所进行的初始化 */
			List<String>  head2 =null;
			for( String head1:head){
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
			 }catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 导出excel
	 * @param formInfoEntityList
	 * @param response
	 */
	@PostMapping("/exportTargetDataResultBu")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出", notes = "")
	public void exportTargetDataResultBu(@RequestBody List<ContractFormInfoResponseVO> formInfoEntityList, HttpServletResponse response) {
		if (CollectionUtil.isNotEmpty(formInfoEntityList)) {
			/* 导出文件名称 */
			String fileName = "合同未归档信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("合同未归档信息导出");
			sheet1.setSheetNo(0);
			/* 需要存入的数据 */
			List<List<Object>> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			for (ContractFormInfoResponseVO contractFormInfoEntity : formInfoEntityList) {
				/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据
				  要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
				List<Object> cloumns = new ArrayList<Object>();
				/* 合同编号*/
				cloumns.add(contractFormInfoEntity.getContractNumber());
				/* 合同名称 */
				cloumns.add(contractFormInfoEntity.getContractName());
				/* 合同一级分类 */
				cloumns.add(dictBizClient.getValues("HTDL", Long.valueOf(contractFormInfoEntity.getContractBigCategory())).getData());
				/* 合同二级分类 */
				cloumns.add(dictBizClient.getValues("HTDL", Long.valueOf(contractFormInfoEntity.getContractSmallCategory())).getData());
				/*合同相对方名称*/
				StringBuilder name = new StringBuilder();
				for (ContractCounterpartEntity counterpartEntity : contractFormInfoEntity.getCounterpart()) {
					name.append(counterpartEntity.getName());
					name.append(",");
				}
				name.substring(0, name.length());
				cloumns.add(name.toString());
				/* 合同金额 */
				cloumns.add(contractFormInfoEntity.getContractAmount());
				/*用印日期*/
				cloumns.add(contractFormInfoEntity.getSignTime());
				/*经办人*/
				StringBuilder manager=new StringBuilder();
				for (ContractArchiveNotEntity archiveNotEntity:contractFormInfoEntity.getArchiveNotEntity()) {
					manager.append(archiveNotEntity.getManager());
					manager.append(",");
				}
				manager.substring(0,manager.length());
				cloumns.add(manager.toString());
				/*经办部门*/
				StringBuilder managerUnit = new StringBuilder();
				for (ContractArchiveNotEntity archiveNotEntity : contractFormInfoEntity.getArchiveNotEntity()) {
					managerUnit.append(archiveNotEntity.getManageUnit());
					managerUnit.append(",");
				}
				managerUnit.substring(0, managerUnit.length());
				cloumns.add(managerUnit.toString());
				/* 合同状态*/
				cloumns.add(dictBizClient.getValue("contractStatus", contractFormInfoEntity.getContractStatus()).getData());
				/*未归档原因*/
				StringBuilder notArchiveReason = new StringBuilder();
				for (ContractArchiveNotEntity archiveNotEntity : contractFormInfoEntity.getArchiveNotEntity()) {
					notArchiveReason.append(archiveNotEntity.getNotArchiveReason());
					notArchiveReason.append(",");
				}
				notArchiveReason.substring(0, notArchiveReason.length());
				cloumns.add(notArchiveReason.toString());
				data.add(cloumns);
			}
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("合同编号", "合同名称", "合同一级分类", "合同二级分类", "合同相对方名称", "合同金额", "用印日期", "经办人", "经办部门", "合同状态", "未归档原因");
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
