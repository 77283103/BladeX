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

import org.springblade.contract.entity.*;
import org.springblade.contract.mapper.ContractCounterpartMapper;
import org.springblade.contract.service.*;
import org.springblade.contract.vo.*;
import org.springblade.contract.wrapper.ContractRelieveWrapper;
import org.springblade.contract.wrapper.ContractSealUsingInfoWrapper;
import org.springblade.contract.wrapper.ContractSigningArchiveWrapper;
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
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.user.cache.UserCache;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.wrapper.ContractSigningWrapper;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 合同签订表 控制器
 *
 * @author : liyj
 * @date : 2020-09-23 19:27:05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/signing")
@Api(value = "合同签订表", tags = "合同签订表")
public class ContractSigningController extends BladeController {

    private IDictBizClient bizClient;
    private IContractSealUsingInfoService sealUsingInfoService;
    private IContractSigningService contractSigningService;
    private IContractFormInfoService contractFormInfoService;
    private IContractCounterpartService counterpartService;
    private ContractCounterpartMapper counterpartMapper;
    private IContractSigningArchiveService signingArchiveService;
    private static final String CONTRACT_SIGNING_SAVE_STATUS = "60";
    private static final String CONTRACT_CONTRACT_FORM_VALUE = "1";
    private static final String CONTRACT_ARCHIVE_STATUS = "110";

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入contractSigning")
    @PreAuth("hasPermission('contract:signing:detail')")
    public R<ContractSigningResponseVO> detail(@RequestParam Long id) {
        ContractSigningEntity detail = contractSigningService.getById(id);
        return R.data(ContractSigningWrapper.build().entityPV(detail));
    }

    /**
     * 分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入contractSigning")
    @PreAuth("hasPermission('contract:signing:page')")
    public R<IPage<ContractSigningResponseVO>> list(ContractSigningRequestVO contractSigning, Query query) {
        IPage<ContractSigningEntity> pages = contractSigningService.pageList(Condition.getPage(query), contractSigning);
        return R.data(ContractSigningWrapper.build().entityPVPage(pages));
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增", notes = "传入contractSigning")
    @PreAuth("hasPermission('contract:signing:add')")
    @Transactional(rollbackFor = Exception.class)
    public R<ContractSigningEntity> save(@Valid @RequestBody ContractSigningResponseVO contractSigning) {
        ContractSigningEntity entity = new ContractSigningEntity();
        BeanUtil.copy(contractSigning,entity);
        contractSigningService.save(entity);
        String contractForm = contractFormInfoService.getById(contractSigning.getContractId()).getContractForm();
        //判断合同类型是否为电子合同，是则签订完即可自动归档
        if (CONTRACT_CONTRACT_FORM_VALUE.equals(contractForm)) {
            String contractStatus = CONTRACT_ARCHIVE_STATUS;
            contractFormInfoService.updateExportStatus(contractStatus, contractSigning.getContractId());
        } else {
            String contractStatus = CONTRACT_SIGNING_SAVE_STATUS;
            contractFormInfoService.updateExportStatus(contractStatus, contractSigning.getContractId());
        }
        contractSigning.getSigningArchiveEntityList().forEach(signingArchive -> {
            if (Func.isNotEmpty(signingArchive.getId())){
                signingArchive.setId(null);
            }
            signingArchive.setSigningId(contractSigning.getId());
            signingArchiveService.save(signingArchive);
        });
        return R.data(entity);
    }

    /**
     * 新增归档目录文件
     */
    @PostMapping("/adds")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增", notes = "signingArchive")
    @PreAuth("hasPermission('contract:signing:adds')")
    public R save(@Valid @RequestBody ContractSigningArchiveResponseVO signingArchive) {
        return R.status(signingArchiveService.save(ContractSigningArchiveWrapper.build().PVEntity(signingArchive)));
    }
    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "修改", notes = "传入contractSigning")
    @PreAuth("hasPermission('contract:signing:update')")
    public R update(@Valid @RequestBody ContractSigningResponseVO contractSigning) {
        if (Func.isEmpty(contractSigning.getId())) {
            throw new ServiceException("id不能为空");
        }
        return R.status(contractSigningService.updateById(ContractSigningWrapper.build().PVEntity(contractSigning)));
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @PreAuth("hasPermission('contract:signing:remove')")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(contractSigningService.deleteLogic(Func.toLongList(ids)));
    }

    /**
     * 获取当前用户登录信息
     *
     * @return
     */
    @GetMapping("/logInfo")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "查询登当前录用户信息")
    @PreAuth("hasPermission('contract:signing:logInfo')")
    public R<ContractSigningResponseVO> logInfo() {
        ContractSigningResponseVO responseVO = ContractSigningWrapper.build().createPV();
        BladeUser user = AuthUtil.getUser();
        Long userId = Long.valueOf(user.getUserId());
        Long deptId = Long.valueOf(AuthUtil.getDeptId());
        Date now = new Date();
        responseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(deptId));
        return R.data(responseVO);
    }

    /**
     *
     * 文本打印导出excel
     *
     * @param formInfoEntity
     * @param response
     */
    @PostMapping("/exportTargetDataResult")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "导出", notes = "")
    public void exportTargetDataResult(@RequestBody ContractFormInfoResponseVO formInfoEntity, HttpServletResponse response) {

        if (Func.isNotEmpty(formInfoEntity)) {
            /* 导出文件名称 */
            String fileName = "合同文本信息导出";
            WriteSheet sheet1 = new WriteSheet();
            /* 导出的sheet的名称 */
            sheet1.setSheetName("合同文本信息导出");
            sheet1.setSheetNo(0);
            /* 需要存入的数据 */
            List<Object> data = new ArrayList<>();
            /* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
            /* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
            /* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据 要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
            List<Object> cloumns = new ArrayList<Object>();
            /*合同编号*/
            cloumns.add(formInfoEntity.getContractNumber());
            /*合同名称*/
            cloumns.add(formInfoEntity.getContractName());
            /*所属合同一级分类*/
            cloumns.add(bizClient.getValues("HTDL", Long.valueOf(formInfoEntity.getContractBigCategory())).getData());
            /*所属合同二级分类*/
            cloumns.add(bizClient.getValues("HTDL", Long.valueOf(formInfoEntity.getContractSmallCategory())).getData());
            /*合同相对方名称*/
            StringBuilder name = new StringBuilder();
            for (ContractCounterpartEntity counterpartEntity : formInfoEntity.getCounterpart()) {
                name.append(counterpartEntity.getName());
                name.append(",");
            }
            name.substring(0, name.length());
            cloumns.add(name.toString());
            /* 合同金额 */
            cloumns.add(formInfoEntity.getContractAmount());
            /*币种*/
            cloumns.add(bizClient.getValue("bz", formInfoEntity.getCurrencyCategory()).getData());
            /*审核信息*/
            cloumns.add(formInfoEntity.getSubmitStatus());
            /*完成时间*/
            cloumns.add(formInfoEntity.getCreateTime());
            /*文本导出次数*/
            cloumns.add(formInfoEntity.getFileExportCount()+1);
            data.add(cloumns);
            /* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
            List<List<String>> headList = new ArrayList<List<String>>();
            /* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
            List<String> head = Arrays.asList("合同编号", "合同名称", "合同一级分类", "合同二级分类", "合同向对方", "合同金额", "币种", "审核信息",
                    "完成时间", "文本导出次数");
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


    /**
     * 签订完成导出excel
     *
     * @param formInfoEntity
     * @param response
     */
    @PostMapping("/exportTargetDataResultSigning")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "导出", notes = "")
    public void exportTargetDataResultSigning(@RequestBody ContractFormInfoResponseVO formInfoEntity, HttpServletResponse response) {

        if (Func.isNotEmpty(formInfoEntity)) {
            /* 导出文件名称 */
            String fileName = "合同文本信息导出";
            WriteSheet sheet1 = new WriteSheet();
            /* 导出的sheet的名称 */
            sheet1.setSheetName("合同文本信息导出");
            sheet1.setSheetNo(0);
            /* 需要存入的数据 */
            List<Object> data = new ArrayList<>();
            /* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
            /* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据 要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
            List<Object> cloumns = new ArrayList<Object>();
            /*合同编号*/
            cloumns.add(formInfoEntity.getContractNumber());
            /*合同名称*/
            cloumns.add(formInfoEntity.getContractName());
            /*所属合同一级分类*/
            cloumns.add(bizClient.getValues("HTDL", Long.valueOf(formInfoEntity.getContractBigCategory())).getData());
            /*所属合同二级分类*/
            cloumns.add(bizClient.getValues("HTDL", Long.valueOf(formInfoEntity.getContractSmallCategory())).getData());
            /*合同相对方名称*/
            StringBuilder name = new StringBuilder();
            for (ContractCounterpartEntity counterpartEntity : formInfoEntity.getCounterpart()) {
                name.append(counterpartEntity.getName());
                name.append(",");
            }
            name.substring(0, name.length());
            cloumns.add(name.toString());
            ContractSigningEntity signingEntity=contractSigningService.selectSigningById(formInfoEntity.getId());
            /*承办人*/
            cloumns.add(signingEntity.getManager());
            ContractSealUsingInfoEntity sealUsingInfoEntity=sealUsingInfoService.selectSealUsing(formInfoEntity.getId());
            /*用印时间*/
            cloumns.add(sealUsingInfoEntity.getSignTime());
            /*快递信息*/
            cloumns.add("递交类型:"+bizClient.getValue("submission_type",signingEntity.getSubmissionType()).getData()+"//快递单号:"
                    +signingEntity.getCourierNum()+"//快递公司:"+signingEntity.getCourierCompany()
                    +"//收件人:"+signingEntity.getAddressee()+"//收件人地址:"+signingEntity.getAddress());
            data.add(cloumns);
            /* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
            List<List<String>> headList = new ArrayList<List<String>>();
            /* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
            List<String> head = Arrays.asList("合同编号", "合同名称", "合同一级分类", "合同二级分类", "合同向对方", "承办人", "用印时间", "快递信息");
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
