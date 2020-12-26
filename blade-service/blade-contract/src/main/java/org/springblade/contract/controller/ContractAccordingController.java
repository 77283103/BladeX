package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.abutment.entity.DocEntity;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.DocVo;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.service.IContractAccordingService;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.contract.vo.ContractAccordingResponseVO;
import org.springblade.contract.wrapper.ContractAccordingWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.user.cache.UserCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


/**
 * 合同依据管理 控制器
 *
 * @author : XHB
 * @date : 2020-09-24 14:20:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/according")
@Api(value = "合同依据管理", tags = "合同依据管理")
public class ContractAccordingController extends BladeController {
	private RedisTemplate redisTemplate;
	private IContractAccordingService accordingService;
	private IAbutmentClient abutmentClient;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入according")
	@PreAuth("hasPermission('contract:according:detail')")
	public R<ContractAccordingResponseVO> detail(@RequestParam Long id) {
		ContractAccordingEntity detail = accordingService.getById(id);
		return R.data(ContractAccordingWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入according")
	@PreAuth("hasPermission('contract:according:list')")
	public R<IPage<ContractAccordingResponseVO>> list(ContractAccordingRequestVO according, Query query) {
		IPage<ContractAccordingEntity> pages = accordingService.pageList(Condition.getPage(query), according);
		/*IPage<ContractAccordingEntity> pages = new Page<ContractAccordingEntity>();

		// 查询依据 开始  cc 20201218
		DocEntity docEntity = new DocEntity();
		// 员工编号   问题:到底是用id还是code
		docEntity.setEmplno(UserCache.getUser(AuthUtil.getUserId()).getCode());
		// 依据编号
		docEntity.setDocNumber(according.getFileId());
		// 先默认查所有获取依据总数给分页用，因为oa接口的结果集是没有数据总数的
		List<DocVo> docVos = abutmentClient.queryDocInfo(docEntity).getData();
		// 有数据才进行一系列操作
		if (docVos != null && docVos.size() > 0) {
			// 不从数据库查, 只能手动往里放
			pages.setTotal(docVos.size());
			pages.setSize(query.getSize());
			pages.setCurrent(query.getCurrent());
			// 返回的数据列表, 因为对接程序里的属性字段和需返回的有差异, 需要在这里处理
			List<ContractAccordingEntity> contractAccordingEntityList = new ArrayList<ContractAccordingEntity>();
			// 模拟分页,从所有依据里截取需要的部分
			Integer fromIndex = Integer.parseInt((pages.getSize() * pages.getCurrent()) + "");
			Integer toIndex = Integer.parseInt((pages.getSize() * (pages.getCurrent() + 1)) + "");
			if (fromIndex < docVos.size()) {
				// oa返回的字段只有4个, 其他字段需要根据情况再定
				docVos.subList(fromIndex, toIndex > docVos.size() - 1 ? docVos.size() - 1 : toIndex).forEach(docVo -> {
					ContractAccordingEntity contractAccordingEntity = new ContractAccordingEntity();
					// 文档名称
					contractAccordingEntity.setAccordingName(docVo.getDoc_name());
					// 文档id
					contractAccordingEntity.setFileId(docVo.getDoc_id());
					// 文档编号
					contractAccordingEntity.setCode(docVo.getDoc_number());
					// 文档类型
					contractAccordingEntity.setDocumentType(docVo.getDoc_type());
					contractAccordingEntityList.add(contractAccordingEntity);
				});
				pages.setRecords(contractAccordingEntityList);
			}
		}*/
		// 查询依据 结束

		return R.data(ContractAccordingWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入according")
	@PreAuth("hasPermission('contract:according:add')")
	public R save(@Valid @RequestBody ContractAccordingRequestVO according) {
        ContractAccordingEntity entity = new ContractAccordingEntity();
        BeanUtil.copy(according,entity);
		return R.status(accordingService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入according")
	@PreAuth("hasPermission('contract:according:update')")
	public R update(@Valid @RequestBody ContractAccordingRequestVO according) {
	    if (Func.isEmpty(according.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractAccordingEntity entity = new ContractAccordingEntity();
        BeanUtil.copy(according,entity);
		return R.status(accordingService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:according:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(accordingService.deleteLogic(Func.toLongList(ids)));
	}


	/**
	 * 查询redis的数据
	 */
	@PostMapping("/according")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "查询redis的数据", notes = "传入id")
	public R<String> according(@ApiParam(value = "主键集合", required = true) @RequestParam String id) {
		String j= (String) redisTemplate.opsForValue().get(id);
		redisTemplate.delete(id);
		return R.data(j);
	}
}
