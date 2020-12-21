package org.springblade.abutment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.common.annotation.AutoLog;
import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.service.IOrganizationService;
import org.springblade.abutment.vo.OrganizationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 组织人员信息 API控制器
 * </p>
 *
 * @Author gym
 * @since 2020-11-23
 */
@Slf4j
@RestController
@RequestMapping("/rest/api")
@Api(value = "组织及人员信息")
public class OrganizationController {
	@Autowired
	IOrganizationService organizationService;

    /**
     * 获取组织及人员信息
	 * @param organizationVo
     * @return
     */
    /*@GetMapping("/queryOrganization")
	@AutoLog
	@ApiOperation(value = "获取组织及人员信息的接口")
	public ResultEntity<List<OrganizationEntity>> queryOrganization(OrganizationVo organizationVo) {
		ResultEntity<List<OrganizationEntity>> result = new ResultEntity<List<OrganizationEntity>>();
		try {
			List<OrganizationEntity> organizationList = null;
			organizationList = organizationService.getOrganizationInfo(organizationVo);

			if(organizationList != null) {
				result.setSuccess(organizationList);
			}else {
				result.setError("获取组织及人员信息失败");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setError("服务程序出错");
		}
		return result;
	}*/
}
