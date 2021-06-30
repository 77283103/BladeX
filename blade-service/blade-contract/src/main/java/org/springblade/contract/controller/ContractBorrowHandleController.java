package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.EkpVo;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.service.IContractBorrowApplicationService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.wrapper.ContractBorrowHandleWrapper;
import org.springblade.contract.service.IContractBorrowHandleService;
import org.springblade.contract.vo.ContractBorrowHandleRequestVO;
import org.springblade.contract.vo.ContractBorrowHandleResponseVO;

import java.beans.Transient;


/**
 * 借阅处理 控制器
 *
 * @author : xhb
 * @date : 2020-10-30 09:28:16
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/contractBorrowHandle")
@Api(value = "借阅处理", tags = "借阅处理")
public class ContractBorrowHandleController extends BladeController {

    private final IContractBorrowHandleService contractBorrowHandleService;
    private final IContractBorrowApplicationService contractBorrowApplicationService;
    private static final String BORROW_HANDLE_STATUS = "20";
	private final IAbutmentClient abutmentClient;
    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入contractBorrowHandle")
    @PreAuth("hasPermission('handling:contractBorrowHandle:detail')")
    public R<ContractBorrowHandleResponseVO> detail(@RequestParam Long id) {
        ContractBorrowHandleEntity detail = contractBorrowHandleService.getById(id);
        return R.data(ContractBorrowHandleWrapper.build().entityPV(detail));
    }

    /**
     * 分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入contractBorrowHandle")
    @PreAuth("hasPermission('handling:contractBorrowHandle:page')")
    public R<IPage<ContractBorrowHandleResponseVO>> list(ContractBorrowHandleRequestVO contractBorrowHandle, Query query) {
        IPage<ContractBorrowHandleEntity> pages = contractBorrowHandleService.pageList(Condition.getPage(query), contractBorrowHandle);
        return R.data(ContractBorrowHandleWrapper.build().entityPVPage(pages));
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入contractBorrowHandle")
    @PreAuth("hasPermission('handling:contractBorrowHandle:add')")
	@Transactional(rollbackFor = Exception.class)
    public R save(@Valid @RequestBody ContractBorrowHandleResponseVO contractBorrowHandle) {
    	ContractBorrowApplicationEntity applicationEntity= contractBorrowApplicationService.getById(contractBorrowHandle.getHandleId());
		ContractBorrowHandleEntity entity = new ContractBorrowHandleEntity();
		BeanUtil.copy(contractBorrowHandle, entity);
		contractBorrowApplicationService.updateBorrowStatusById(Long.valueOf(contractBorrowHandle.getHandleId()), BORROW_HANDLE_STATUS);
		log.info("借阅申请处理信息：" + entity);
		applicationEntity.setHandleEntity(entity);
		R<EkpVo> ekpVo = abutmentClient.borEkpFormPost(applicationEntity);
		log.info("ekp调用结果:" + JsonUtil.toJson(ekpVo));
		if (ekpVo.getCode() == HttpStatus.OK.value()) {
			log.info("ekp返回值code" + ekpVo.getData().getDoc_info());
			return R.status(contractBorrowHandleService.save(entity));
		} else if (ekpVo.getCode() == 2) {
			return R.data(ekpVo.getCode(), null, "当前员工编号在系统中不存在，请换一个试试");
		} else {
			return R.data(ekpVo.getCode(), null, "token获取失败，连接超时");
		}

    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入contractBorrowHandle")
    @PreAuth("hasPermission('handling:contractBorrowHandle:update')")
    public R update(@Valid @RequestBody ContractBorrowHandleResponseVO contractBorrowHandle) {
        if (Func.isEmpty(contractBorrowHandle.getId())) {
            throw new ServiceException("id不能为空");
        }
        return R.status(contractBorrowHandleService.updateById(ContractBorrowHandleWrapper.build().PVEntity(contractBorrowHandle)));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @PreAuth("hasPermission('handling:contractBorrowHandle:remove')")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(contractBorrowHandleService.deleteLogic(Func.toLongList(ids)));
    }

}
