package org.springblade.abutment.service.impl;


import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.properties.EkpProperties;
import org.springblade.abutment.service.EkpUserDeptService;
import org.springblade.abutment.service.IEkpService;
import org.springblade.abutment.vo.EkpSyncDeptInfoVo;
import org.springblade.abutment.vo.EkpSyncInfoVo;
import org.springblade.abutment.vo.EkpSyncRequestVO;
import org.springblade.abutment.vo.EkpSyncUserInfoVo;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.DigestUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Dept;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EkpUserDeptServiceImpl implements EkpUserDeptService {

	@Autowired
	private EkpProperties ekpProperties;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private IUserClient userClient;
	@Autowired
	private ISysClient sysClient;
	@Autowired
	private IEkpService ekpService;

	private final static String SUCCESS = "success";


	@SneakyThrows
	@Override
	public void synchronizationEkpUserData(EkpSyncRequestVO ekpSyncRequestVO) {
		//获取ekp用户部门数据
		ekpSyncRequestVO.setToken(ekpService.getToken());
		EkpSyncInfoVo ekpSyncInfoVo = getEkpSyncInfo(ekpSyncRequestVO);
//		String json = readFileContent("C:\\Users\\woche\\Desktop\\文档\\ekp_syn_info.txt");
//		EkpSyncInfoVo ekpSyncInfoVo = JsonUtil.parse(json,EkpSyncInfoVo.class);
		if(ekpSyncInfoVo.getMsg().equals(SUCCESS)){
			Map<String,Dept> deptMap = ekpDeptHandle(ekpSyncInfoVo.getOrgList());
			Map<String,User> userMap =  ekpUserHandle(ekpSyncInfoVo.getUserList(),deptMap);
			userDepartHandle(deptMap,userMap,ekpSyncInfoVo.getUserList());
		}
	}


	public EkpSyncInfoVo getEkpSyncInfo(EkpSyncRequestVO ekpSyncRequestVO){
		//设置请求头
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		//设置请求参数
		HttpEntity<String> requestBody = new HttpEntity<String>(JsonUtil.toJson(ekpSyncRequestVO), headers);
		//调用接口
		return restTemplate.postForObject(ekpProperties.getUrl(),requestBody, EkpSyncInfoVo.class);
	}

	/**
	 * 处理ekp用户数据
	 * @param ekpSyncUserInfoVoList
	 * @return
	 */
	public Map<String,User> ekpUserHandle(List<EkpSyncUserInfoVo> ekpSyncUserInfoVoList,Map<String,Dept> deptMap){
		List<User> baldeUserList = new ArrayList<>();
		Map<String,User> userMap = getMapUser();

		if(Func.isEmpty(ekpSyncUserInfoVoList)){
			return userMap;
		}
		ekpSyncUserInfoVoList.forEach(ekpSyncUserInfoVo -> {
			User user = userMap.get(ekpSyncUserInfoVo.getUserId());
			//为空则ekp推送的此用户为新增用户。
			if(null == user){
				user = new User();
				user.setId(new DefaultIdentifierGenerator().nextId(new Object()).longValue());
				user.setRealName(ekpSyncUserInfoVo.getUserName());
				user.setAccount(ekpSyncUserInfoVo.getEmplno());
				user.setPassword(DigestUtil.encrypt(ekpProperties.getPassword()));
				user.setCode(ekpSyncUserInfoVo.getEmplno());
				user.setAssociationId(ekpSyncUserInfoVo.getUserId());
				user.setIsDeleted(0);
			}
			Dept dept = deptMap.get(ekpSyncUserInfoVo.getParentId());
			if(null != dept){
				user.setDeptNm(dept.getDeptNm());
				user.setDeptNo(dept.getDeptNo());
				user.setFactName(dept.getFactName());
				user.setFactNo(dept.getFactNo());
			}
			user.setIsEnable(Integer.parseInt(Func.isEmpty(ekpSyncUserInfoVo.getAvailable())?"0":ekpSyncUserInfoVo.getAvailable()));
			baldeUserList.add(user);
			userMap.put(user.getAssociationId(),user);
		});
		userClient.saveOrUpdateBatch(baldeUserList);
		return userMap;
	}

	/**
	 * 处理ekp部门数据
	 * @param ekpSyncDeptInfoVoList
	 * @return
	 */
	public Map<String,Dept> ekpDeptHandle(List<EkpSyncDeptInfoVo> ekpSyncDeptInfoVoList){
		//
		List<Dept> deptList = new ArrayList<>();
		//db部门数据（key:ekp部门标识,用于查找父节点标识 value：db部门）
		Map<String,Dept> dbDeptMap = getMapDept();
		//ekp部门数据（key:ekp部门标识 value：ekp部门）
		Map<String,EkpSyncDeptInfoVo> ekpDeptMap = new HashMap<>();
		if(Func.isEmpty(ekpSyncDeptInfoVoList)){
			return dbDeptMap;
		}
		//循环ekp同步部门，更新db部门名称，获取ekp新增部门
		for(EkpSyncDeptInfoVo ekpSyncDeptInfoVo:ekpSyncDeptInfoVoList){
			Dept dept = dbDeptMap.get(ekpSyncDeptInfoVo.getOrgId());
			//数据库中不存在代表本次同步数据中存在新增部门
			if(Func.isEmpty(dept)){
				dept = new Dept();
				dept.setId(new DefaultIdentifierGenerator().nextId(new Object()).longValue());
				dept.setDeptName(ekpSyncDeptInfoVo.getOrgName());
				dept.setAssociationId(ekpSyncDeptInfoVo.getOrgId());
				dept.setIsEnable(Integer.parseInt(Func.isEmpty(ekpSyncDeptInfoVo.getAvailable())?"0":ekpSyncDeptInfoVo.getAvailable()));
			}
			dept.setDeptName(ekpSyncDeptInfoVo.getOrgName());
			dbDeptMap.put(dept.getAssociationId(),dept);
			ekpDeptMap.put(ekpSyncDeptInfoVo.getOrgId(),ekpSyncDeptInfoVo);
		}

		//为新增部门生成父级节点表达式及获取父级节点id
		for(String ekpDeptId:ekpDeptMap.keySet()){
			Dept dept = dbDeptMap.get(ekpDeptId);
			if(Func.isEmpty(dept.getParentId())){
				//生成父级节点表达式
				dept.setAncestors(greateAncestors(ekpDeptMap.get(dept.getAssociationId()).getParentId(),dbDeptMap,new StringBuffer("")));
				//生成父级节点标识
				dept.setParentId(dbDeptMap.get(ekpDeptMap.get(dept.getAssociationId()).getParentId()).getId());
			}
			//ekp部门信息部分字段在合同平台不需要。这部分字段有变更也会同步过来。（不影响功能，但会增加更新数据量，且没有必要）
			deptList.add(dept);
		}
		sysClient.saveOrUpdateBatchDept(deptList);
		return dbDeptMap;
	}


	public List<UserDepartEntity> userDepartHandle(Map<String,Dept> deptMap, Map<String,User> userMap,List<EkpSyncUserInfoVo> ekpSyncUserInfoVoList){
		List<UserDepartEntity> userDepartEntityList = new ArrayList<>();
		List<Long> userIds = new ArrayList<>();
		ekpSyncUserInfoVoList.forEach(ekpSyncUserInfoVo -> {
			UserDepartEntity userDepartEntity = new UserDepartEntity();
			User user = userMap.get(ekpSyncUserInfoVo.getUserId());
			Dept dept = deptMap.get(ekpSyncUserInfoVo.getParentId());
			userDepartEntity.setUserId(user.getId());
			//为空说明部门出现同步中出现部门缺失情况
			if(null != dept){
				userDepartEntity.setDeptId(dept.getId());
			}
			//同步用户设置默认角色,默认角色由system自动创建
			userDepartEntity.setRoleId(1270659143136452610L);
			userDepartEntityList.add(userDepartEntity);
			userIds.add(user.getId());
		});
		//清除同步用户所属部门角色关联表旧数据
		userClient.deleteUserDepart(userIds);
		//上千数据新增有延迟，后续操作注意
		sysClient.saveOrUpdateBatchUserDepart(userDepartEntityList);
		return userDepartEntityList;
	}


	/**
	 * 获取db用户数据map
	 * @return
	 */
	private Map<String,User> getMapUser(){
		Map<String,User> deptMap = new HashMap<>();
		R<List<User>> r = userClient.getUserAll();
		r.getData().forEach(user -> {
			deptMap.put(user.getAssociationId(),user);
		});
		return deptMap;
	}


	/**
	 * 获取db部门数据map
	 * @return
	 */
	private Map<String,Dept> getMapDept(){
		Map<String,Dept> deptMap = new HashMap<>();
		R<List<Dept>> r = sysClient.getDeptAll();
		r.getData().forEach(dept -> {
			deptMap.put(dept.getAssociationId(),dept);
		});
		return deptMap;
	}


	/**
	 * 递归生成部门父级节点表达式
	 * @param ekpParentId
	 * @param dbDeptMap
	 * @param ancestors
	 * @return
	 */
	private String greateAncestors(String ekpParentId,Map<String,Dept> dbDeptMap,StringBuffer ancestors){
		Dept parentDept = dbDeptMap.get(ekpParentId);
		if(null == parentDept){
			if(ancestors.length() > 0){
				ancestors.append(",");
			}
			ancestors.append("0");
			return ancestors.toString();
		}
		if(ancestors.length() > 0){
			ancestors.append(",");
		}
		ancestors.append(parentDept.getId());
		String ekpParentIdNext = "null";
		for(String key:dbDeptMap.keySet()){
			if(dbDeptMap.get(key).getId().equals(parentDept.getParentId())){
				ekpParentIdNext = dbDeptMap.get(key).getAssociationId();
			}
		}
		return greateAncestors(ekpParentIdNext,dbDeptMap,ancestors);
	}



	public static String readFileContent(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr);
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}




}
