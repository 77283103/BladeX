package org.springblade.abutment.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.webservice.SoapClient;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.XML;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.service.IOrganizationService;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.system.entity.Dept;
import org.springblade.system.entity.Post;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeaderElement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 组织及人员信息 服务实现类
 * </p>
 *
 * @Author: gym
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class OrganizationServiceImpl implements IOrganizationService {
	@Autowired
	ISysClient sysClient;
	@Autowired
	IUserClient userClient;

    @Value("${api.organization.account}")
    private String account;
    @Value("${api.organization.password}")
    private String password;
    @Value("${api.organization.wsdl}")
    private String wsdl;
    @Value("${api.organization.method}")
    private String method;
    @Value("${api.organization.namespace}")
    private String namespace;

    @Override
    public List<OrganizationVo> getOrganizationInfo(OrganizationEntity entity) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, String>> fieldsList = new ArrayList<Map<String, String>>();
        if(entity.getFieldsName() != null && entity.getFieldsName().size() > 1) {
            for(String fieldName : entity.getFieldsName()) {
                fieldsList.add(this.getFieldMap(fieldName));
            }
        }else {
            fieldsList.add(this.getFieldMap("id"));
            fieldsList.add(this.getFieldMap("email"));
            fieldsList.add(this.getFieldMap("loginName"));
            fieldsList.add(this.getFieldMap("emplno"));
            fieldsList.add(this.getFieldMap("name"));
            fieldsList.add(this.getFieldMap("namePinyin"));
            fieldsList.add(this.getFieldMap("isAvailable"));
            fieldsList.add(this.getFieldMap("orgType"));
            fieldsList.add(this.getFieldMap("parentid"));
            fieldsList.add(this.getFieldMap("alterTime"));
			fieldsList.add(this.getFieldMap("factno"));
			fieldsList.add(this.getFieldMap("factname"));
			fieldsList.add(this.getFieldMap("deptno"));
			fieldsList.add(this.getFieldMap("deptnm"));
			fieldsList.add(this.getFieldMap("laiyuan"));
			fieldsList.add(this.getFieldMap("deptid"));
			fieldsList.add(this.getFieldMap("gradid"));
			fieldsList.add(this.getFieldMap("gradno"));
			fieldsList.add(this.getFieldMap("gradnm"));
			fieldsList.add(this.getFieldMap("thisLeaderid"));
        }
        paramMap.put("select",fieldsList);
        List<Map<String, String>> whereList = new ArrayList<Map<String, String>>();
        if(StrUtil.isNotEmpty(entity.getId())) {
            whereList.add(this.getWhereMap("id", entity.getId()));
        }
        if(StrUtil.isNotEmpty(entity.getEmail())) {
            whereList.add(this.getWhereMap("email", entity.getEmail()));
        }
        if(StrUtil.isNotEmpty(entity.getLoginName())) {
            whereList.add(this.getWhereMap("loginName", entity.getLoginName()));
        }
        if(StrUtil.isNotEmpty(entity.getEmplno())) {
            whereList.add(this.getWhereMap("emplno", entity.getEmplno()));
        }
        if(StrUtil.isNotEmpty(entity.getName())) {
            whereList.add(this.getWhereMap("name", entity.getName()));
        }
        if(StrUtil.isNotEmpty(entity.getNamePinyin())) {
            whereList.add(this.getWhereMap("namePinyin", entity.getNamePinyin()));
        }
        if(StrUtil.isNotEmpty(entity.getIsAvailable())) {
            whereList.add(this.getWhereMap("isAvailable", entity.getIsAvailable()));
        }
        if(StrUtil.isNotEmpty(entity.getOrgType())) {
            whereList.add(this.getWhereMap("orgType", entity.getOrgType()));
        }
        if(StrUtil.isNotEmpty(entity.getParentid())) {
            whereList.add(this.getWhereMap("parentid", entity.getParentid()));
        }
        if(StrUtil.isNotEmpty(entity.getAlterTime())) {
            whereList.add(this.getWhereMap("alterTime", entity.getAlterTime()));
        }
        paramMap.put("where",whereList);
        //使用SoapUI解析WSDL地址，找到WebService方法和参数。
		//新建客户端
        SoapClient client = SoapClient.create(this.wsdl)
        //设置要请求的方法
			.setMethod(method)
        //设置参数，此处自动添加方法的前缀：web
			.setParam("arg0", JSONUtil.toJsonStr(paramMap));
        SOAPHeaderElement element = client.addSOAPHeader(new QName(this.namespace,"RequestSOAPHeader","tns"));
        element.addChildElement("user","tns").addTextNode(this.account);
        element.addChildElement("password","tns").addTextNode(this.password);
        String mesStr = client.send();
        log.info(mesStr);
        JSONObject message =  XML.toJSONObject(mesStr).getJSONObject("soap:Envelope").getJSONObject("soap:Body").getJSONObject("ns1:updateOrganizationResponse");
        JSONObject organizationInfoJson = message != null ? message.getJSONObject("return") : null;
        return organizationInfoJson == null ? null : organizationInfoJson.getStr("status").equals("succeed") ? JSON.parseArray(organizationInfoJson.get("data", List.class).toString() , OrganizationVo.class): null;
    }

    private Map<String, String> getWhereMap(String key, String value) throws Exception {
        Map<String, String> whereMap = new HashMap<String, String>();
        whereMap.put("type",key+"='"+value+"'");
        return whereMap;
    }

    private Map<String, String> getFieldMap(String fieldName) throws Exception {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("type",fieldName);
        return fieldMap;
    }

	/**
	 * 获取组织及人员增量信息
	 *
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public R<List<OrganizationVo>> getOrganizationInfoIncrement() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		OrganizationEntity entity = new OrganizationEntity();
//		entity.setAlterTime(format.format(new Date()));
		entity.setIsAvailable("1");
		List<OrganizationVo> organizationList = null;
		//保存组织机构
		try {
			entity.setOrgType("2");
			organizationList = getOrganizationInfo(entity);
			if (Func.isNull(organizationList)){
				return R.data(404,null,"TAG2:getOrganizationInfo()链接获取信息的方法异常，请检查");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert organizationList != null;
		log.info("上面处理了NULL的判断，所以走到这里说明接口没问题："+JSONUtil.toJsonStr(organizationList));
		if (Func.isNotEmpty(organizationList)) {
			sysClient.saveOrUpdateBatchDept(getOrgListUpdate(organizationList));
		}
		//保存岗位
		try {
			entity.setOrgType("4");
			organizationList = getOrganizationInfo(entity);
			if (Func.isNull(organizationList)){
				return R.data(404,null,"TAG4:getOrganizationInfo()链接获取信息的方法异常，请检查");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Func.isNotEmpty(organizationList)){
			sysClient.saveOrUpdateBatchPost(getPostListUpdate(organizationList));
		}
		//保存个人
		try {
			entity.setOrgType("8");
			organizationList = getOrganizationInfo(entity);
			if (Func.isNull(organizationList)){
				return R.data(404,null,"TAG8:getOrganizationInfo()链接获取信息的方法异常，请检查");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Func.isNotEmpty(organizationList)){
			getPersonListUpdate(organizationList);
		}
		return R.data(200,organizationList,"数据更新成功");
	}


	/**
	 * 增量处理org或dept机构数据
	 *
	 * @param organizationList
	 * @return
	 */
	private List<Dept> getOrgListUpdate(List<OrganizationVo> organizationList) {
		List<Dept> list = new ArrayList<>();
		for (OrganizationVo organizationVo : organizationList) {
			Dept dept = new Dept();
			dept.setIsEnable(organizationVo.getIsAvailable().equals("1") ? 1 : 0);
			dept.setDeptName(organizationVo.getName());
			dept.setPinyinName(organizationVo.getNamePinyin());
			dept.setDeptNm(organizationVo.getDeptnm());
			dept.setDeptNo(organizationVo.getDeptno());
			dept.setFactNo(organizationVo.getFactno());
			dept.setFactName(organizationVo.getFactname());
			dept.setIsDeleted(0);
			dept.setStatus(1);
			dept.setAssociationId(organizationVo.getId());
			//*上级部门*//*
			R<Long> deptIdByAssociationId= sysClient.getDeptIdByAssociationId(organizationVo.getParentid());
			if (deptIdByAssociationId.isSuccess() && Func.isNotEmpty(deptIdByAssociationId.getData())) {
				dept.setParentId(deptIdByAssociationId.getData());
			} else {
				dept.setParentId(0L);
			}
			//*祖籍列表*//*
			if (Func.isNotEmpty(dept.getParentId())) {
				R<String> hierarchy = sysClient.getAncestors(dept.getParentId());
				if (hierarchy.isSuccess()) {
					dept.setAncestors(hierarchy.getData());
				} else {
					dept.setAncestors(StringPool.EMPTY);
				}
			}
			//*根据唯一id查询机构的ID*//*
			deptIdByAssociationId = sysClient.getDeptIdByAssociationId(organizationVo.getId());
			if (deptIdByAssociationId.isSuccess() && Func.isNotEmpty(deptIdByAssociationId.getData())) {
				dept.setId(deptIdByAssociationId.getData());
			}else{
				dept.setId(IdWorker.getId(dept));
			}
			list.add(dept);
		}
		return list;
	}

	/**
	 * 增量处理岗位数据
	 *
	 * @param organizationList
	 * @return
	 */
	private List<Post> getPostListUpdate(List<OrganizationVo> organizationList) {
		List<Post> list = new ArrayList<>();
		for (OrganizationVo organizationVo : organizationList) {
			Post post = new Post();
			post.setIsDeleted(0);
			post.setPostName(organizationVo.getName());
			/*根据Lunid查询岗位的ID*/
			R<Long> postIdByAssociationId = sysClient.getPostIdByAssociationId(organizationVo.getId());
			if (postIdByAssociationId.isSuccess() && Func.isNotEmpty(postIdByAssociationId.getData())) {
				post.setId(postIdByAssociationId.getData());
			}else{
				post.setId(IdWorker.getId(post));
			}
			list.add(post);
		}
		return list;
	}

	/**
	 * 增量处理人员数据
	 *
	 * @param organizationList
	 * @return
	 */
	private List<User> getPersonListUpdate(List<OrganizationVo> organizationList) {
		List<User> list = new ArrayList<>();
		List<UserDepartEntity> userDepartList = new ArrayList<>();
		for (OrganizationVo organizationVo : organizationList) {
			User user = new User();
			UserDepartEntity userDepart = new UserDepartEntity();
			user.setIsEnable(organizationVo.getIsAvailable().equals("1") ? 0 : 1);
			user.setIsDeleted(0);
			user.setPassword(SecureUtil.md5("123456"));
			user.setCode(organizationVo.getEmplno());
			user.setAccount(organizationVo.getLoginName());
			user.setRealName(organizationVo.getName());
			user.setEmail(organizationVo.getEmail());
			user.setFactNo(organizationVo.getFactno());
			user.setFactName(organizationVo.getFactname());
			user.setDeptNo(organizationVo.getDeptno());
			user.setDeptNm(organizationVo.getDeptnm());
			user.setLaiYuan(organizationVo.getLaiyuan());
			/*根据唯一标识获取用户id，因为此时用户已经同步到库中*/
			R<Long> userIdResult = userClient.getUserIdByAssociationId(organizationVo.getId());
			if (userIdResult.isSuccess()) {
				user.setId(userIdResult.getData());
			}else{
				user.setId(IdWorker.getId(user));
			}
			list.add(user);
				/*R<Long> postIdByAssociationId = sysClient.getPostIdByAssociationId(organizationVo.getId());
				if (postIdByAssociationId.isSuccess()) {
					userDepart.setPostId(postIdByAssociationId.getData());
				}*/
			R<Long> deptIdByAssociationId= sysClient.getDeptIdByAssociationId(organizationVo.getParentid());
			if (deptIdByAssociationId.isSuccess() && Func.isNotEmpty(deptIdByAssociationId.getData())) {
				userDepart.setDeptId(deptIdByAssociationId.getData());
			}
			userDepart.setRoleId(1270659143136452610L);
			R<Long> userDepartIdByAssociationId= sysClient.getUserDepartByAssociationId(user.getId());
			if (userDepartIdByAssociationId.isSuccess()) {
				userDepart.setId(userDepartIdByAssociationId.getData());
			}else{
				userDepart.setId(IdWorker.getId(userDepart));
			}
			userDepart.setUserId(user.getId());
			userDepartList.add(userDepart);
		}
		userClient.saveOrUpdateBatch(list);
		sysClient.saveOrUpdateBatchUserDepart(userDepartList);
		return list;
	}
}
