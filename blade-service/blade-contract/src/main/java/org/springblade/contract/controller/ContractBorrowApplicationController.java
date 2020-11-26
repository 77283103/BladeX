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

import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Charsets;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.SysCache;
import org.springblade.system.entity.Dept;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.entity.UserDepart;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.wrapper.ContractBorrowApplicationWrapper;
import org.springblade.contract.service.IContractBorrowApplicationService;
import org.springblade.contract.vo.ContractBorrowApplicationRequestVO;
import org.springblade.contract.vo.ContractBorrowApplicationResponseVO;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 借阅申请 控制器
 *
 * @author : xhb
 * @date : 2020-10-30 09:27:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractBorrowApplication")
@Api(value = "借阅申请", tags = "借阅申请")
public class ContractBorrowApplicationController extends BladeController {

    private IDictBizClient dictBizClient;
    private IContractBorrowApplicationService contractBorrowApplicationService;
    private static final String BORROW_APPLICATION_STATUS = "10";

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入contractBorrowApplication")
    @PreAuth("hasPermission('application:contractBorrowApplication:detail')")
    public R<ContractBorrowApplicationResponseVO> detail(@RequestParam Long id) {
        ContractBorrowApplicationEntity detail = contractBorrowApplicationService.getById(id);
        return R.data(ContractBorrowApplicationWrapper.build().entityPV(detail));
    }

    /**
     * 分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入contractBorrowApplication")
    @PreAuth("hasPermission('application:contractBorrowApplication:page')")
    public R<IPage<ContractBorrowApplicationResponseVO>> list(ContractBorrowApplicationRequestVO contractBorrowApplication, Query query) {
        IPage<ContractBorrowApplicationEntity> pages = contractBorrowApplicationService.pageList(Condition.getPage(query), contractBorrowApplication);
        return R.data(ContractBorrowApplicationWrapper.build().entityPVPage(pages));
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入contractBorrowApplication")
    @PreAuth("hasPermission('application:contractBorrowApplication:add')")
    public R save(@Valid @RequestBody ContractBorrowApplicationResponseVO contractBorrowApplication) {
        String borrowStatus = BORROW_APPLICATION_STATUS;
        contractBorrowApplication.setBorrowStatus(borrowStatus);
        return R.status(contractBorrowApplicationService.save(ContractBorrowApplicationWrapper.build().PVEntity(contractBorrowApplication)));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入contractBorrowApplication")
    @PreAuth("hasPermission('application:contractBorrowApplication:update')")
    public R update(@Valid @RequestBody ContractBorrowApplicationResponseVO contractBorrowApplication) {
        if (Func.isEmpty(contractBorrowApplication.getId())) {
            throw new ServiceException("id不能为空");
        }
        return R.status(contractBorrowApplicationService.updateById(ContractBorrowApplicationWrapper.build().PVEntity(contractBorrowApplication)));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @PreAuth("hasPermission('application:contractBorrowApplication:remove')")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(contractBorrowApplicationService.deleteLogic(Func.toLongList(ids)));
    }


    /**
     * 当前登录用户信息
     */
    @GetMapping("/logInfo")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "插叙登当前录用户信息")
    @PreAuth("hasPermission('application:contractBorrowApplication:logInfo')")
    public R<ContractBorrowApplicationResponseVO> logInfo() {
        ContractBorrowApplicationResponseVO responseVO = ContractBorrowApplicationWrapper.build().createPV();
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
     /**
     * 导出excel
     * @param applicationEntityList
     * @param response
     */
    @PostMapping("/exportTargetDataResult")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "导出", notes = "")
    public void exportTargetDataResult(@RequestBody List<ContractBorrowApplicationEntity> applicationEntityList, HttpServletResponse response) {

        if(CollectionUtil.isNotEmpty(applicationEntityList)){
            /* 导出文件名称 */
            String  fileName = "合同借阅信息导出";
            WriteSheet sheet1 = new WriteSheet();
            /* 导出的sheet的名称 */
            sheet1.setSheetName("合同借阅信息导出");
            sheet1.setSheetNo(0);
            sheet1.getAutomaticMergeHead();
            sheet1.getAutoTrim();
            sheet1.getColumnWidthMap();
            /* 需要存入的数据 */
            List<List<Object>> data = new ArrayList<>();
            /* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
            for(ContractBorrowApplicationEntity applicationEntity:applicationEntityList){
				/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据
				  要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
                List<Object> cloumns = new ArrayList<Object>();
                /*借阅人*/
                cloumns.add(applicationEntity.getApplicant());
                /*借阅部门*/
                cloumns.add(applicationEntity.getApplicationDepartment());
                /*资料类型*/
                cloumns.add(dictBizClient.getValue("data_type",applicationEntity.getDataType()).getData());
                /*借阅周期（起）*/
                cloumns.add(applicationEntity.getBorrowCycleStart());
                /*借阅周期（止）*/
                cloumns.add(applicationEntity.getBorrowCycleEnd());
                /*借阅方式*/
                cloumns.add(dictBizClient.getValue("borrow_mode",applicationEntity.getBorrowMode()).getData());
                /*事由*/
                cloumns.add(applicationEntity.getExplanation());
                /*进度*/
                cloumns.add(applicationEntity.getBorrowSchedule());
                /*借阅状态*/
                cloumns.add(dictBizClient.getValue("borrow_status",applicationEntity.getBorrowStatus()).getData());
                data.add(cloumns);
            }
            /* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
            List<List<String>> headList = new ArrayList<List<String>>();
            /* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
            List<String> head = Arrays.asList("借阅人", "借阅部门", "资料类型","借阅周期（起）", "借阅周期（止）", "借阅方式","事由","进度","借阅状态");
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
}
