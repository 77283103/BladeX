package org.springblade.abutment.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.webservice.SoapClient;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.XML;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.service.IOrganizationService;
import org.springblade.abutment.vo.OrgParme;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
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
import java.text.ParseException;
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
	@Autowired
	private BladeLogger logger;

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
		if (entity.getFieldsName() != null && entity.getFieldsName().size() > 1) {
			for (String fieldName : entity.getFieldsName()) {
				fieldsList.add(this.getFieldMap(fieldName));
			}
		} else {
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
			fieldsList.add(this.getFieldMap("ins_date"));
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
		paramMap.put("select", fieldsList);
		List<Map<String, String>> whereList = new ArrayList<Map<String, String>>();
		if (StrUtil.isNotEmpty(entity.getId())) {
			whereList.add(this.getWhereMap("id", entity.getId()));
		}
		if (StrUtil.isNotEmpty(entity.getEmail())) {
			whereList.add(this.getWhereMap("email", entity.getEmail()));
		}
		if (StrUtil.isNotEmpty(entity.getLoginName())) {
			whereList.add(this.getWhereMap("loginName", entity.getLoginName()));
		}
		if (StrUtil.isNotEmpty(entity.getEmplno())) {
			whereList.add(this.getWhereMap("emplno", entity.getEmplno()));
		}
		if (StrUtil.isNotEmpty(entity.getName())) {
			whereList.add(this.getWhereMap("name", entity.getName()));
		}
		if (StrUtil.isNotEmpty(entity.getNamePinyin())) {
			whereList.add(this.getWhereMap("namePinyin", entity.getNamePinyin()));
		}
		if (StrUtil.isNotEmpty(entity.getIsAvailable())) {
			whereList.add(this.getWhereMap("isAvailable", entity.getIsAvailable()));
		}
		if (StrUtil.isNotEmpty(entity.getOrgType())) {
			whereList.add(this.getWhereMap("orgType", entity.getOrgType()));
		}
		if (StrUtil.isNotEmpty(entity.getParentid())) {
			whereList.add(this.getWhereMap("parentid", entity.getParentid()));
		}
		if (StrUtil.isNotEmpty(entity.getAlterTime())) {
			whereList.add(this.getWhereMap("alterTime >", entity.getAlterTime()));
		}
		if (StrUtil.isNotEmpty(entity.getIns_date())) {
			whereList.add(this.getWhereMap("ins_date", entity.getIns_date()));
		}
		paramMap.put("where", whereList);
		//使用SoapUI解析WSDL地址，找到WebService方法和参数。
		//新建客户端
		SoapClient client = SoapClient.create(this.wsdl)
			//设置要请求的方法
			.setMethod(method)
			//设置参数，此处自动添加方法的前缀：web
			.setParam("arg0", JSONUtil.toJsonStr(paramMap));
		SOAPHeaderElement element = client.addSOAPHeader(new QName(this.namespace, "RequestSOAPHeader", "tns"));
		element.addChildElement("user", "tns").addTextNode(this.account);
		element.addChildElement("password", "tns").addTextNode(this.password);
		String mesStr = client.send();
		log.info(mesStr);
		JSONObject message = XML.toJSONObject(mesStr).getJSONObject("soap:Envelope").getJSONObject("soap:Body").getJSONObject("ns1:updateOrganizationResponse");
		JSONObject organizationInfoJson = message != null ? message.getJSONObject("return") : null;
		return organizationInfoJson == null ? null : "succeed".equals(organizationInfoJson.getStr("status")) ? JSON.parseArray(organizationInfoJson.get("data", List.class).toString(), OrganizationVo.class) : null;
	}

	private Map<String, String> getWhereMap(String key, String value) throws Exception {
		Map<String, String> whereMap = new HashMap<String, String>();
		whereMap.put("type", key + "='" + value + "'");
		return whereMap;
	}

	private Map<String, String> getFieldMap(String fieldName) throws Exception {
		Map<String, String> fieldMap = new HashMap<String, String>();
		fieldMap.put("type", fieldName);
		return fieldMap;
	}

	/**
	 * 获取组织及人员增量信息
	 *
	 * @return
	 */
	@SneakyThrows
	@Override
	@Transactional(rollbackFor = Exception.class)
	@ApiLog("组织机构接口获取到的数据的条件")
	public R<List<OrganizationVo>> getOrganizationInfoIncrement(OrgParme param) {
		log.info("查看查询时间的参数：" + JSONUtil.toJsonStr(param));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		OrganizationEntity entity = new OrganizationEntity();
		//param不为空  并且tag为1  标识自动更新 不赋值   为2的时候标识手动更新数据
		if (Func.isNotEmpty(param.getParme()) && "2".equals(param.getTag())) {
			entity.setAlterTime(param.getParme());
		} else if (Func.isNotEmpty(param.getParme()) && "1".equals(param.getTag())) {
			entity.setAlterTime(format.format(calendar.getTime()));
		}
		List<OrganizationVo> organizationList = null;
		//保存组织机构
		try {
			entity.setOrgType("2");
			organizationList = getOrganizationInfo(entity);
			logger.info("元数据dept", JsonUtil.toJson(organizationList));
			log.info("组织机构信息：" + JSONUtil.toJsonStr(organizationList));
			if (Func.isNull(organizationList)) {
				return R.data(404, null, "TAG2:getOrganizationInfo()链接获取信息的方法异常，请检查");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		assert organizationList != null;
		log.info("上面处理了NULL的判断，所以走到这里说明接口没问题：" + JSONUtil.toJsonStr(organizationList));
		if (Func.isNotEmpty(organizationList)) {
			getOrgListUpdate(organizationList);
		}
		//保存岗位
		try {
			entity.setOrgType("4");
			organizationList = getOrganizationInfo(entity);
			logger.info("元数据post", JsonUtil.toJson(organizationList));
			log.info("岗位信息：" + JSONUtil.toJsonStr(organizationList));
			if (Func.isNull(organizationList)) {
				return R.data(404, null, "TAG4:getOrganizationInfo()链接获取信息的方法异常，请检查");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Func.isNotEmpty(organizationList)) {
			getPostListUpdate(organizationList);
		}
		//保存个人
		try {
			entity.setOrgType("8");
			organizationList = getOrganizationInfo(entity);
			logger.info("元数据user", JsonUtil.toJson(organizationList));
			log.info("个人信息：" + JSONUtil.toJsonStr(organizationList));
			if (Func.isNull(organizationList)) {
				return R.data(404, null, "TAG8:getOrganizationInfo()链接获取信息的方法异常，请检查");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Func.isNotEmpty(organizationList)) {
			getPersonListUpdate(organizationList, param);
		}
		return R.data(200, organizationList, "数据更新成功");
	}


	/**
	 * 增量处理org或dept机构数据
	 *
	 * @param organizationList
	 */
	private void getOrgListUpdate(List<OrganizationVo> organizationList) {
		List<Dept> in = new ArrayList<>();
		for (OrganizationVo organizationVo : organizationList) {
			Dept dept = new Dept();
			dept.setIsEnable("1".equals(organizationVo.getIsAvailable()) ? 1 : 0);
			dept.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
			dept.setDeptName(organizationVo.getName());
			dept.setPinyinName(organizationVo.getNamePinyin());
			dept.setDeptNm(organizationVo.getDeptnm());
			dept.setDeptNo(organizationVo.getDeptno());
			dept.setFactNo(organizationVo.getFactno());
			dept.setFactName(organizationVo.getFactname());
			dept.setIsDeleted(0);
			dept.setStatus(1);
			dept.setAssociationId(organizationVo.getId());
			dept.setCreateUser(1374895070913761282L);
			dept.setUpdateUser(1374895070913761282L);
			dept.setCreateDept(1367272379264299021L);
			//*上级部门ID getId()  查询来的是部上级部门的id*//*
			R<Long> deptIdByAssociationId = sysClient.getDeptIdByAssociationId(organizationVo.getParentid());
			if (deptIdByAssociationId.isSuccess() && Func.isNotEmpty(deptIdByAssociationId.getData())) {
				dept.setParentId(deptIdByAssociationId.getData());
			} else {
				dept.setParentId(0L);
			}
			//*祖籍列表  根据ParenId while循环查询相关的父级id  存入Ancestors  祖籍列表字段*//*
			if (Func.isNotEmpty(dept.getParentId())) {
				R<String> hierarchy = sysClient.getAncestors(dept.getParentId());
				if (hierarchy.isSuccess() && Func.isNotEmpty(hierarchy.getData())) {
					dept.setAncestors(hierarchy.getData());
				} else {
					dept.setAncestors(StringPool.EMPTY);
				}
			}
			//*根据唯一id查询机构的ID*   association_id    如果查询存在那就修改   否则保存//*
			deptIdByAssociationId = sysClient.getDeptIdByAssociationId(organizationVo.getId());
			if (deptIdByAssociationId.isSuccess() && Func.isNotEmpty(deptIdByAssociationId.getData())) {
				dept.setId(deptIdByAssociationId.getData());
			} else {
				//自动生成ID
//				dept.setId(IdWorker.getId(dept));
				dept.setId(null);
			}
			in.add(dept);
		}
		logger.info("合同平台处理的部门数据",JsonUtil.toJson(in));
		if (Func.isNotEmpty(in)) {
			in.forEach(dept -> {
				sysClient.saveDept(dept);
			});
		}
	}

	/**
	 * 增量处理岗位数据
	 *
	 * @param organizationList
	 */
	private void getPostListUpdate(List<OrganizationVo> organizationList) {
		List<Post> in = new ArrayList<>();
		for (OrganizationVo organizationVo : organizationList) {
			Post post = new Post();
			post.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
			post.setIsDeleted(0);
			post.setStatus(1);
			post.setAssociationId(organizationVo.getId());
			//岗位ID
			post.setPostCode(organizationVo.getGradid());
			post.setPostName(organizationVo.getName());
			post.setCreateTime(new Date());
			post.setCreateUser(1374895070913761282L);
			post.setUpdateUser(1374895070913761282L);
			post.setCreateDept(1367272379264299021L);
			/*根据Lunid查询岗位的ID*/
			R<Long> postIdByAssociationId = sysClient.getPostIdByAssociationId(organizationVo.getId());
			if (postIdByAssociationId.isSuccess()) {
				post.setId(postIdByAssociationId.getData());
			} else {
				post.setId(IdWorker.getId(post));
//				post.setId(null);
			}
			in.add(post);
		}
		logger.info("合同平台处理的岗位数据",JsonUtil.toJson(in));
		if (Func.isNotEmpty(in)) {
			sysClient.saveOrUpdateBatchPost(in);
		}
	}

	/**
	 * 增量处理人员数据
	 *
	 * @param organizationList
	 */
	private void getPersonListUpdate(List<OrganizationVo> organizationList, OrgParme param) throws ParseException {
		List<UserDepartEntity> inud = new ArrayList<>();
		List<User> in=new ArrayList<>();
		List<User> up=new ArrayList<>();
		Map<String, List<User>> map=new HashMap<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (OrganizationVo organizationVo : organizationList) {
			if (Func.isEmpty(organizationVo.getEmplno()) && Func.isEmpty(organizationVo.getLoginName())) {
				{
					continue;
				}
			}
			User user = new User();
			List<User> inu = new ArrayList<>();
			UserDepartEntity userDepart = new UserDepartEntity();
			user.setIsEnable("1".equals(organizationVo.getIsAvailable()) ? 2 : 1);
			user.setIsDeleted(0);
			user.setStatus(1);
			user.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
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
			user.setAssociationId(organizationVo.getId());
			user.setCreateUser(1374895070913761282L);
			user.setUpdateUser(1374895070913761282L);
			user.setCreateDept(1367272379264299021L);
			user.setCreateTime(new Date());
			/*根据唯一标识获取用户id，因为此时用户已经同步到库中*/
			R<Long> userIdResult = userClient.getUserIdByAssociationId(organizationVo.getId());
			if (userIdResult.isSuccess()) {
				user.setId(userIdResult.getData());
				up.add(user);
			} else {
				user.setId(IdWorker.getId(user));
				in.add(user);
			}
			inu.add(user);
			userClient.saveOrUpdateBatch(inu);
			/**
			 * 用户关联部门和岗位和角色表
			 * @author jitwxs
			 * @date 2021/6/22 11:45
			 */
			//根据唯一ID查询岗位信息  赋值岗位ID
			R<Long> postIdByAssociationId = sysClient.getPostIdByAssociationId(organizationVo.getId());
			if (postIdByAssociationId.isSuccess() ) {
				userDepart.setPostId(postIdByAssociationId.getData());
			}
			//根据父节点ID查询部门信息  赋值部门ID
			R<Long> deptIdByAssociationId = sysClient.getDeptIdByAssociationId(organizationVo.getParentid());
			if (deptIdByAssociationId.isSuccess() && !Objects.equals(deptIdByAssociationId.getData(), 0L)) {
				userDepart.setDeptId(deptIdByAssociationId.getData());
			}
			//根据用户ID查询用户--关联部门和岗位和角色表信息   保存或修改
			R<Long> userDepartIdByAssociationId = sysClient.getUserDepartByAssociationId(user.getId());
			if (userDepartIdByAssociationId.isSuccess() && !Objects.equals(userDepartIdByAssociationId.getData(), 0L)) {
				userDepart.setId(userDepartIdByAssociationId.getData());
				UserDepartEntity ud=sysClient.getUserDepart(user.getId()).getData();
				userDepart.setRoleId(ud.getRoleId());
				userDepart.setPostId(ud.getPostId());
			} else {
//				userDepart.setId(IdWorker.getId(userDepart));
				userDepart.setId(null);
				userDepart.setRoleId(1270659143136452610L);
			}
			userDepart.setUserId(user.getId());
			inud.add(userDepart);
		}
		map.put("新增",in);
		map.put("更新",up);
		logger.info("合同平台处理的用户数据",JsonUtil.toJson(map));
		if (Func.isNotEmpty(inud)) {
			sysClient.saveOrUpdateBatchUserDepart(inud);
		}
	}
}
