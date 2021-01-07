package org.springblade.abutment.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.common.annotation.AutoLog;
import org.springblade.abutment.entity.ContractSubmit;
import org.springblade.abutment.entity.OrganizationEntity;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return R.data(contractClient.saveContractFormInfo(id,status));
	}
}
