package org.springblade.abutment.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.common.annotation.AutoLog;
import org.springblade.abutment.entity.ContractSubmit;
import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.service.IESealService;
import org.springblade.abutment.service.IOrganizationService;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.feign.IContractClient;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.Dept;
import org.springblade.system.entity.Post;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * ekp API控制器
 * </p>
 *
 * @Author gym
 * @since 2020-11-23
 */
@Slf4j
@RestController
@RequestMapping("/ekp")
@Api(value = "ekp API控制器")
@AllArgsConstructor
public class EkpController {
	IContractClient contractClient;
	IESealService eSealService;
	/**
	 * epk通过后返回给合同平台合同状态
	 * @return
	 */
	@PostMapping("/submit")
	@AutoLog
	@ApiOperation(value = "epk通过后返回给合同平台合同状态接口")
	public R queryOrganization(@RequestBody ContractSubmit contractSubmit) {
		String status=contractSubmit.getSubmitStatus();
		Long id=Long.valueOf(contractSubmit.getContractId());
		log.info("epk通过后返回给合同平台合同状态接口[返回的合同状态为]"+id+"执行时间为"+new Date());
		return R.data(contractClient.saveContractFormInfo(id,status));
	}


	/**
	 * 获取token
	 */
	@GetMapping("/eToken")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "获取token", notes = "")
	public R<String> eToken() {
		String token=null;
		try {
			token = eSealService.getToken();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return R.data(token);
	}

}
