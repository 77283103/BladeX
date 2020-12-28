package org.springblade.abutment.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.common.annotation.AutoLog;
import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.service.IOrganizationService;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.Dept;
import org.springblade.system.entity.Post;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	/**
	 * 获取组织及人员信息
	 * @return
	 */
	@PostMapping("/queryOrganization")
	@AutoLog
	@ApiOperation(value = "获取组织及人员信息的接口")
	public R<List<OrganizationVo>> queryOrganization() {
		OrganizationEntity entity=new OrganizationEntity();
		List<OrganizationVo> organizationList = null;
		/*try {*/
		try {
			organizationList = organizationService.getOrganizationInfo(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Map<String, String>> idMap = new HashMap<String, Map<String, String>>();
			List<Dept> deptList = new ArrayList<Dept>();
			List<Post> postList = new ArrayList<Post>();
			List<User> userList = new ArrayList<User>();
			List<UserDepartEntity> userDepartList = new ArrayList<UserDepartEntity>();
			if(organizationList.size()>0) {
				for(OrganizationVo organizationVo : organizationList) {
					Map<String, String> map = new HashMap();
					System.out.println(organizationVo.getOrgType());
					if(Integer.parseInt(organizationVo.getOrgType())>=30){
						organizationVo.setOrgType("3");
					}
					map.put("id", IdUtil.createSnowflake(1, Long.parseLong(organizationVo.getOrgType())).nextIdStr());
					map.put("orgType", organizationVo.getOrgType());
					idMap.put(organizationVo.getId(), map);
				}
				for(OrganizationVo organizationVo : organizationList) {
					switch(organizationVo.getOrgType()) {
						// 部门
						case "2":
							Dept dept = new Dept();
							dept.setIsEnable(organizationVo.getIsAvailable().equals("1")?1:0);
							dept.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
							dept.setParentId(Long.parseLong(idMap.get(organizationVo.getParentid()).get("id")));
							dept.setId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
							dept.setDeptName(organizationVo.getName());
							dept.setIsDeleted(0);
							deptList.add(dept);
							break;
						// 岗位
						case "4":
							Post post = new Post();
							post.setIsDeleted(0);
							post.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
							post.setCreateDept(Long.parseLong(idMap.get(organizationVo.getParentid()).get("id")));
							post.setId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
							post.setPostName(organizationVo.getName());
							postList.add(post);
							break;
						// 个人
						case "8":
							User user = new User();
							user.setId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
							user.setIsEnable(organizationVo.getIsAvailable().equals("1")?1:0);
							user.setIsDeleted(0);
							user.setPassword(SecureUtil.md5("123456"));
							user.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
							user.setCode(organizationVo.getEmplno());
							user.setAccount(organizationVo.getLoginName());
							user.setRealName(organizationVo.getName());
							user.setEmail(organizationVo.getEmail());
							userList.add(user);
							UserDepartEntity userDepart = new UserDepartEntity();
							userDepart.setUserId(user.getId());
							if (idMap.get(organizationVo.getParentid()).get("orgType").equals("2")) {
								userDepart.setDeptId(Long.parseLong(idMap.get(organizationVo.getParentid()).get("id")));
							}
							if (idMap.get(organizationVo.getParentid()).get("orgType").equals("4")) {
								userDepart.setPostId(Long.parseLong(idMap.get(organizationVo.getParentid()).get("id")));
							}
							userDepartList.add(userDepart);
							break;
					}
					sysClient.saveOrUpdateBatchDept(deptList);
					sysClient.saveOrUpdateBatchPost(postList);
					userClient.saveOrUpdateBatch(userList);
					userClient.saveOrUpdateBatchDepart(userDepartList);
				}
			}
		 /*}catch (Exception e) {
			log.error(e.getMessage());
		}*/
		return R.data(organizationList);
	}
}
