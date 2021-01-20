package org.springblade.abutment.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import feign.form.ContentType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springblade.abutment.common.annotation.AutoLog;
import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.service.IOrganizationService;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.IntegerPool;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.dto.UserDepartDTO;
import org.springblade.system.entity.Dept;
import org.springblade.system.entity.Post;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.entity.UserDepart;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springblade.core.cache.constant.CacheConstant.SYS_CACHE;

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
	/*@PostMapping("/queryOrganization")
	@AutoLog
	@ApiOperation(value = "获取组织及人员信息的接口")
	public R<List<OrganizationVo>> queryOrganization() {
		OrganizationEntity entity = new OrganizationEntity();
		entity.setOrgType("2");
		List<OrganizationVo> organizationList = null;
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
		if (organizationList.size() > 0) {
			for (OrganizationVo organizationVo : organizationList) {
				switch (organizationVo.getOrgType()) {
					// 部门
					case "2":
						Dept dept = new Dept();
						dept.setIsEnable(organizationVo.getIsAvailable().equals("1") ? 0 : 1);
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
						post.setIsDeleted(0);
						post.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
						post.setPostName(organizationVo.getName());
						post.setAssociationId(organizationVo.getId());
						postList.add(post);
						break;
					// 个人
					case "8":
						User user = new User();
						user.setIsEnable(organizationVo.getIsAvailable().equals("1") ? 1 : 0);
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
				}

			}
			sysClient.saveOrUpdateBatchDept(deptList);
			//sysClient.saveOrUpdateBatchPost(postList);
			//userClient.saveOrUpdateBatch(userList);
		}
		return R.data(organizationList);
	}*/
	/**
	 * 获取组织及人员增量信息
	 *
	 * @return
	 */
	@PostMapping("/fastFile")
	@AutoLog
	@ApiOperation(value = "获取组织及人员信息的接口")
	public R fastFile() throws IOException, MyException {

		List<FileVO> fileVOs=fileClient.getByIds("1351469422220513281").getData();
		for(FileVO fileVO:fileVOs){
			// 开始上传fastDFS服务器
			NameValuePair[] nvp = new NameValuePair[5];
			int index = fileVO.getName().lastIndexOf(".");
			String fileSuffix=fileVO.getName().substring(index+1);
			nvp[0] = new NameValuePair("fdFileName", fileVO.getName());//文件名称
			nvp[1] = new NameValuePair("fileSuffix", fileSuffix);//文件后缀
			nvp[2] = new NameValuePair("fdKey", "");//文件key？？
			nvp[3] = new NameValuePair("fdFileSize", fileVO.getFileSizes());//文件大小
			nvp[4] = new NameValuePair("fileType", "");//文件类型
			//3.创建trackerServer
			TrackerServer trackerServer = null;
			try {
				trackerServer = trackerClient.getConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 4、创建一个 StorageServer 的引用，值为 null
			StorageServer storageServer = null;
			// 5、创建一个 StorageClient 对象，需要两个参数 TrackerServer 对象、StorageServer 的引用
			StorageClient storageClient = new StorageClient(trackerServer, storageServer);
			InputStream in =null;
			BufferedInputStream bin =null;
			ByteArrayOutputStream baos = null;
			BufferedOutputStream bout =null;
			byte[] bytes=null;
			try {
				URL url = new URL(fileVO.getLink());
				URLConnection conn = url.openConnection();
				in = conn.getInputStream();
				bin = new  BufferedInputStream(in);
				baos = new ByteArrayOutputStream();
				bout = new BufferedOutputStream(baos);
				byte[] buffer = new byte[1024];
				int len = bin.read(buffer);
				while(len != -1){
					bout.write(buffer, 0, len);
					len = bin.read(buffer);
				}
				//刷新此输出流并强制写出所有缓冲的输出字节
				bout.flush();
				bytes = baos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String[] fileIds = storageClient.upload_file(bytes, fileSuffix, nvp); // 上传
			System.out.println(fileIds);
		}
		return R.success("ceshi");
	}
	/**
	 * 获取组织及人员增量信息
	 *
	 * @return
	 */
	@PostMapping("/queryOrganization")
	@AutoLog
	@ApiOperation(value = "获取组织及人员信息的接口")
	public R<List<OrganizationVo>> queryOrganization() {
		OrganizationEntity entity = new OrganizationEntity();
		List<OrganizationVo> organizationList = null;
		//保存组织机构
		/*try {
			entity.setOrgType("2");
			organizationList = organizationService.getOrganizationInfo(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//sysClient.saveOrUpdateBatchDept(getOrgListUpdate(organizationList));
		//保存岗位
		/*try {
			entity.setOrgType("4");
			organizationList = organizationService.getOrganizationInfo(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		/*sysClient.saveOrUpdateBatchPost(getPostListUpdate(organizationList));*/
		//保存个人
		try {
			entity.setOrgType("8");
			organizationList = organizationService.getOrganizationInfo(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getPersonListUpdate(organizationList);
		return R.data(organizationList);
	}
    /**
     * 获取组织及人员信息
     *
     * @return
     *//*
    @PostMapping("/queryOrganization")
    @AutoLog
    @ApiOperation(value = "获取组织及人员信息的接口")
    public R<List<OrganizationVo>> queryOrganization() {
        OrganizationEntity entity = new OrganizationEntity();
        List<OrganizationVo> organizationList = null;
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
        if (organizationList.size() > 0) {
            for (OrganizationVo organizationVo : organizationList) {
                Map<String, String> map = new HashMap();
                System.out.println(organizationVo.getOrgType());
                if (Integer.parseInt(organizationVo.getOrgType()) >= 30) {
                    organizationVo.setOrgType("3");
                }
                map.put("id", IdUtil.createSnowflake(1, Long.parseLong(organizationVo.getOrgType())).nextIdStr());
                map.put("orgType", organizationVo.getOrgType());
                idMap.put(organizationVo.getId(), map);
                switch (organizationVo.getOrgType()) {
                    // 部门
                    case "2":
                        Dept dept = new Dept();
                        dept.setIsEnable(organizationVo.getIsAvailable().equals("1") ? 1 : 0);
                        dept.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
                        dept.setParentId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
                        dept.setId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
                        dept.setDeptName(organizationVo.getName());
                        dept.setPinyinName(organizationVo.getNamePinyin());
                        dept.setIsDeleted(0);
                        dept.setStatus(1);
                        deptList.add(dept);
                        sysClient.saveOrUpdateBatchDept(deptList);
                        break;
                    // 岗位
                    case "4":
                        Post post = new Post();
                        post.setIsDeleted(0);
                        post.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
                        post.setCreateDept(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
                        post.setId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
                        post.setPostName(organizationVo.getName());
                        postList.add(post);
                        sysClient.saveOrUpdateBatchPost(postList);
                        break;
                    // 个人
                    case "8":
                        User user = new User();
                        user.setId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
                        user.setIsEnable(organizationVo.getIsAvailable().equals("1") ? 1 : 0);
                        user.setIsDeleted(0);
                        user.setPassword(SecureUtil.md5("123456"));
                        user.setUpdateTime(DateUtil.parse(organizationVo.getAlterTime(), "yyyy-MM-dd HH:mm:ss"));
                        user.setCode(organizationVo.getEmplno());
                        user.setAccount(organizationVo.getLoginName());
                        user.setRealName(organizationVo.getName());
                        user.setEmail(organizationVo.getEmail());
                        userList.add(user);
                        userClient.saveOrUpdateBatch(userList);
                        *//*UserDepartEntity userDepart = new UserDepartEntity();
                        userDepart.setUserId(user.getId());
                        if ("2".equals(idMap.get(organizationVo.getId()).get("orgType"))) {
                            userDepart.setDeptId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
                        }
                        if ("4".equals(idMap.get(organizationVo.getId()).get("orgType"))) {
                            userDepart.setPostId(Long.parseLong(idMap.get(organizationVo.getId()).get("id")));
                        }
                        userDepartList.add(userDepart);
                        userClient.saveOrUpdateBatchDepart(userDepartList);*//*
                        break;
                }

            }
        }
        return R.data(organizationList);
    }*/


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
				list.add(dept);
			}
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
				list.add(post);
			}
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
				Long userid = null;
				R<Long> userIdResult = userClient.getUserIdByAssociationId(organizationVo.getId());
				if (userIdResult.isSuccess()) {
					userid = userIdResult.getData();
				}
				user.setId(userid);
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
				Long userDepartId = null;
				R<Long> userDepartIdByAssociationId= sysClient.getUserDepartByAssociationId(userid);
				if (userDepartIdByAssociationId.isSuccess()) {
					userDepartId = userDepartIdByAssociationId.getData();
				}
				userDepart.setId(userDepartId);
				userDepart.setUserId(userid);
				userDepartList.add(userDepart);
		}
		userClient.saveOrUpdateBatch(list);
		sysClient.saveOrUpdateBatchUserDepart(userDepartList);
		return list;
	}



}
