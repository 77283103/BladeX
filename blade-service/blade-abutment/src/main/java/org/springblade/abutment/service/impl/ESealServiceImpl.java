package org.springblade.abutment.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springblade.abutment.entity.*;
import org.springblade.abutment.service.IESealService;
import org.springblade.abutment.vo.*;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 电子印章 服务实现类
 * </p>
 *
 * @Author: gym
 * @Date: 2018-12-26
 */
@Service
@Slf4j
public class ESealServiceImpl implements IESealService {
	@Value("${api.eSeal.username}")
	private String username;
	@Value("${api.eSeal.password}")
	private String password;
	@Value("${api.eSeal.tokenUrl}")
	private String tokenUrl;
	@Value("${api.eSeal.orgInfoByCodeUrl}")
	private String orgInfoByCodeUrl;
	@Value("${api.eSeal.orgInfoBySysUidUrl}")
	private String orgInfoBySysUidUrl;
	@Value("${api.eSeal.uploadFileDivideUrl}")
	private String uploadFileDivideUrl;
	@Value("${api.eSeal.uploadFileMergeUrl}")
	private String uploadFileMergeUrl;
	@Value("${api.eSeal.viewUrl}")
	private String viewUrl;
	@Value("${api.eSeal.downloadUrl}")
	private String downloadUrl;
	@Value("${api.eSeal.singleSignUrl}")
	private String singleSignUrl;
	@Value("${api.eSeal.sendSmsUrl}")
	private String sendSmsUrl;
	@Value("${api.eSeal.multiSignUrl}")
	private String multiSignUrl;

	@Override
	public String getToken() throws Exception {
		JSONObject param = new JSONObject();
		param.set("username", this.username);
		param.set("password", this.password);
		JSONObject tokenJson = JSONUtil.parseObj(HttpUtil.createPost(this.tokenUrl).body(param.toString(), "application/json").execute().body());
		log.info(tokenJson.toString());
		return tokenJson == null ? null : tokenJson.getStr("errorCode").equals("0") ? tokenJson.getStr("token") : null;
	}

	@Override
	public CompanyInfoVo getCompanyInfo(String token, CompanyInfoEntity companyInfoEntity) throws Exception {
		JSONObject param = new JSONObject();
		if (companyInfoEntity.getQueryType().equals("1")) {
			param.set("organCode", companyInfoEntity.getOrganCode());
		} else {
			param.set("sysUid", companyInfoEntity.getSysUid());
			param.set("sysType", companyInfoEntity.getSysType());
		}
		log.info("查询的企业相关信息："+JSONUtil.toJsonStr(param));
		JSONObject companyInfoJson = JSONUtil.parseObj(HttpUtil.createPost("1".equals(companyInfoEntity.getQueryType()) ? this.orgInfoByCodeUrl : this.orgInfoBySysUidUrl).body(param.toString(), "application/json").header("token", token).execute().body());
		log.info("电子签章查询结果"+JSONUtil.toJsonStr(companyInfoJson));
		CompanyInfoVo companyInfoVo = new CompanyInfoVo();
		Company company =new Company();
		if ("0".equals(companyInfoJson.get("code").toString())) {
			JSONObject companyInfoJsonCode = (JSONObject) companyInfoJson.get("data");
			JSONObject companyInfoJsonCompany = (JSONObject) companyInfoJsonCode.get("company");
			company = JsonUtil.toPojo(companyInfoJsonCompany,Company.class);
		}else {
			company.setAvailable("0");
		}
		companyInfoVo.setOrganCode(companyInfoJson.get("code").toString());
		companyInfoVo.setCompany(company);
		return companyInfoVo;
	}

	@Override
	public List<UploadFileVo> uploadFiles(String token, UploadFileEntity uploadFilesEntity) {
		HttpPost method = null;
		CloseableHttpClient client = HttpClients.createDefault();
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setCharset(Charset.forName("utf-8"));
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		for (File file : uploadFilesEntity.getFile()) {
			builder.addPart("file", new FileBody(file));
		}
		if (uploadFilesEntity.getIsMerge().equals("1")) {
			method = new HttpPost(this.uploadFileMergeUrl);
			builder.addTextBody("fileName", uploadFilesEntity.getFileName(), ContentType.create("text/plain", "UTF-8"));
		} else {
			method = new HttpPost(this.uploadFileDivideUrl);
		}
		HttpEntity entity = builder.build();
		method.addHeader("token", token);
		method.setEntity(entity);
		CloseableHttpResponse response = null;
		try {
			response = client.execute(method);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject uploadFilesJson = null;
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String info = null;
			try {
				info = EntityUtils.toString(response.getEntity(), Consts.UTF_8.name());
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.info(info);
			uploadFilesJson = JSONUtil.parseObj(info);
		}
        /*for(File file : uploadFilesEntity.getFile()) {
            file.delete();
        }*/
		List<UploadFileVo> uploadFileVoList = null;
		if (uploadFilesJson != null) {
			if (uploadFilesEntity.getIsMerge().equals("0")) {
				JSONArray jsonArray = uploadFilesJson.getJSONArray("fileData");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					if (jsonObject.getStr("errorCode").equals("0")) {
						if (uploadFileVoList == null) {
							uploadFileVoList = new ArrayList<UploadFileVo>();
						}
						uploadFileVoList.add(new UploadFileVo(jsonObject.getStr("id")));
					}
				}
			} else {
				uploadFileVoList = new ArrayList<UploadFileVo>();
				uploadFileVoList.add(new UploadFileVo(uploadFilesJson.getStr("id")));
			}
		}
		System.out.println(uploadFileVoList.size() + "/n" + uploadFileVoList.get(0).getId());

		return uploadFileVoList;
	}

	@Override
	public String readSigned(String token, ReadSignedEntity readSignedEntity) throws Exception {
		String result = null;
		if (readSignedEntity != null) {
			switch (readSignedEntity.getType()) {
				case "1":
					result = viewUrl + "?token=" + token + "&id=" + readSignedEntity.getId();
					break;
				case "2":
					result = downloadUrl + "?token=" + token + "&id=" + readSignedEntity.getId();
					break;
			}
		}
		return result;
	}

	@Override
	public SingleSignVo singleSign(String token, SingleSignEntity singleSignEntity) throws Exception {
		SingleSignVo singleSignVo = null;
		if (singleSignEntity.getSignPos() != null) {
			if (singleSignEntity.getSignType().equals("Key")) {
				singleSignEntity.getSignPos().setPosType(1);
			} else {
				singleSignEntity.getSignPos().setPosType(0);
			}
		}
		JSONObject singleSignJson = JSONUtil.parseObj(HttpUtil.createPost(this.singleSignUrl).body(JSONUtil.toJsonStr(singleSignEntity), "application/json").header("token", token).execute().body());
		if (Func.isNull(singleSignJson)) {
			if (singleSignJson.getJSONObject("signResult") != null) {
				if (singleSignJson.getJSONObject("signResult").getStr("errCode").equals("0")) {
					singleSignVo = new SingleSignVo();
					singleSignVo.setFilePath(singleSignJson.getJSONObject("signResult").getStr("filePath"));
					singleSignVo.setSignServiceId(singleSignJson.getJSONObject("signResult").getStr("signServiceId"));
					singleSignVo.setSignDetailUrl(singleSignJson.getJSONObject("signResult").getStr("signDetailUrl"));
					if (singleSignJson.getJSONObject("autoSignResult") != null) {
						if (singleSignJson.getJSONObject("autoSignResult").getStr("errCode").equals("0")) {
							singleSignVo.setAutoSignServiceId(singleSignJson.getJSONObject("autoSignResult").getStr("signServiceId"));
							singleSignVo.setAutoSignDetailUrl(singleSignJson.getJSONObject("autoSignResult").getStr("signDetailUrl"));
						}
					}
				}
			}
		}
		return singleSignVo;
	}

	@Override
	public boolean sendSms(String token, SendSmsEntity sendSmsEntity) throws Exception {
		boolean result = false;
		JSONObject sendSmsJson = JSONUtil.parseObj(HttpUtil.createPost(this.sendSmsUrl).body(JSONUtil.toJsonStr(sendSmsEntity), "application/json").header("token", token).execute().body());
		if (sendSmsJson != null) {
			if (sendSmsJson.getStr("errCode").equals("0")) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public MultiSignVo multiSign(String token, MultiSignEntity multiSignEntity) throws Exception {
		MultiSignVo multiSignVo = null;
		for (SignParamsEntity signParamsEntity : multiSignEntity.getSignParams()) {
			if (signParamsEntity.getSignPos() != null) {
				if (signParamsEntity.getSignType().equals("Key")) {
					signParamsEntity.getSignPos().setPosType(1);
				} else {
					signParamsEntity.getSignPos().setPosType(0);
				}
			}
		}
		JSONObject multiSignJson = JSONUtil.parseObj(HttpUtil.createPost(this.multiSignUrl).body(JSONUtil.toJsonStr(multiSignVo), "application/json").header("token", token).execute().body());
		if (multiSignJson != null) {
			if (multiSignJson.getJSONObject("successList") != null) {
				if (multiSignJson.getJSONArray("successList").size() > 1) {
					multiSignVo = new MultiSignVo();
					multiSignVo.setSuccessList(multiSignJson.getBean("successList", List.class));
					if (multiSignJson.getJSONObject("failList") != null) {
						if (multiSignJson.getJSONArray("failList").size() > 1) {
							multiSignVo.setFailList(multiSignJson.getBean("failList", List.class));
						}
					}
				}
			}
		}
		return multiSignVo;
	}
}
