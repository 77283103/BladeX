package org.springblade.abutment.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.TrackerClient;
import org.springblade.abutment.common.annotation.AutoLog;
import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.service.IOrganizationService;
import org.springblade.abutment.vo.OrgParme;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.system.entity.Dept;
import org.springblade.system.entity.Post;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/organization")
@Api(value = "组织及人员信息")
@AllArgsConstructor
public class OrganizationController {

    IOrganizationService organizationService;

    ISysClient sysClient;

    IUserClient userClient;

	IFileClient fileClient;
	@Autowired
	private TrackerClient trackerClient;
	/**
	 * 获取组织及人员全部信息
	 *
	 * @return
	 */
	@PostMapping("/queryOrganizationWhole")
	@AutoLog
	@ApiOperation(value = "获取组织及人员信息的接口")
	public R<List<OrganizationVo>> queryOrganizationWhole() {
		OrganizationEntity entity = new OrganizationEntity();
		entity.setOrgType("8");
		List<OrganizationVo> organizationList = null;
		try {
			organizationList = organizationService.getOrganizationInfo(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Dept> deptList = new ArrayList<Dept>();
		List<Post> postList = new ArrayList<Post>();
		List<User> userList = new ArrayList<User>();
		if (organizationList.size() > 0) {
			for (OrganizationVo organizationVo : organizationList) {
				switch (organizationVo.getOrgType()) {
					// 部门
					case "2":
						Dept dept = new Dept();
						//*根据唯一id查询机构的ID*//*
						R<Long> deptIdByAssociationId = sysClient.getDeptIdByAssociationId(organizationVo.getId());
						if (deptIdByAssociationId.isSuccess() && Func.isNotEmpty(deptIdByAssociationId.getData())) {
							dept.setId(deptIdByAssociationId.getData());
						}else{
							dept.setId(IdWorker.getId(dept));
						}
						dept.setIsEnable(organizationVo.getIsAvailable().equals("1") ? 1 : 0);
						dept.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
						//dept.setParentId(Long.parseLong(organizationVo.getId()));
						dept.setDeptName(organizationVo.getName());
						dept.setPinyinName(organizationVo.getNamePinyin());
						dept.setDeptNm(organizationVo.getDeptnm());
						dept.setDeptNo(organizationVo.getDeptno());
						dept.setFactNo(organizationVo.getFactno());
						dept.setFactName(organizationVo.getFactname());
						dept.setIsDeleted(0);
						dept.setStatus(1);
						dept.setAssociationId(organizationVo.getId());
						deptList.add(dept);
						break;
					// 岗位
					case "4":
						Post post = new Post();
						R<Long> postIdByAssociationId = sysClient.getPostIdByAssociationId(organizationVo.getId());
						if (postIdByAssociationId.isSuccess() && Func.isNotEmpty(postIdByAssociationId.getData())) {
							post.setId(postIdByAssociationId.getData());
						}else{
							post.setId(IdWorker.getId(post));
						}
						post.setIsDeleted(0);
						post.setStatus(1);
						post.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
						post.setPostName(organizationVo.getName());
						post.setAssociationId(organizationVo.getId());
						postList.add(post);
						break;
					// 个人
					case "8":
						User user = new User();
						R<Long> userIdResult = userClient.getUserIdByAssociationId(organizationVo.getId());
						if (userIdResult.isSuccess()) {
							user.setId(userIdResult.getData());
						}else{
							user.setId(IdWorker.getId(user));
						}
						user.setIsEnable(organizationVo.getIsAvailable().equals("1") ? 2 : 1);
						user.setIsDeleted(0);
						user.setPassword(SecureUtil.md5("111111"));
						user.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
						user.setCode(organizationVo.getEmplno());
						user.setAccount(organizationVo.getLoginName());
						user.setRealName(organizationVo.getName());
						user.setEmail(organizationVo.getEmail());
						user.setAssociationId(organizationVo.getId());
						userList.add(user);
						break;
					default:
				}
			}
			sysClient.saveOrUpdateBatchDept(deptList);
			sysClient.saveOrUpdateBatchPost(postList);
			userClient.saveOrUpdateBatch(userList);
		}
		return R.data(organizationList);
	}
	/**
	 * 获取组织及人员增量信息
	 *
	 * @return
	 */
	@PostMapping("/queryOrganization")
	@AutoLog
	@ApiOperation(value = "获取组织及人员信息的接口")
	public R<List<OrganizationVo>> queryOrganization(OrgParme param) {
		return R.data(organizationService.getOrganizationInfoIncrement(param).getData());
	}
}
