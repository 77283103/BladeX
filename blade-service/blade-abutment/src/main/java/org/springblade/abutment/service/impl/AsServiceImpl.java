package org.springblade.abutment.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.springblade.abutment.entity.AsDict;
import org.springblade.abutment.service.IAsService;
import org.springblade.abutment.vo.AsDictVo;
import org.springblade.core.log.annotation.ApiLog;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhbbo
 */
@Log4j2
@Service
public class AsServiceImpl implements IAsService {

	@Value("${api.doc.SecurityKey}")
	private String SecurityKey;
	@Value("${api.doc.tokenUrl}")
	private String tokenUrl;
	@Value("${api.doc.signatureUnitUrl}")
	private String signatureUnitUrl;
	@Value("${api.doc.username}")
	private String userName;
	@Value("${api.doc.password}")
	private String password;
	@Override
	public String getToken() throws Exception {
		JSONObject param = new JSONObject();
		param.set("securityKey", this.SecurityKey);
		param.set("username", this.userName);
		param.set("password", this.password);
		JSONObject tokenJson = JSONUtil.parseObj(HttpUtil.createPost(this.tokenUrl).body(param.toString(),"application/json").execute().body());
		log.info(tokenJson.toString());
		return "success".equals(tokenJson.getStr("msg")) ? tokenJson.getStr("token") : null;
	}

	@Override
	@ApiLog("签章单位任务接口的token")
	public R<AsDictVo> getDocInfo(String token) throws Exception {
		JSONObject param = new JSONObject();
		param.set("token", token);
		AsDictVo vo=new AsDictVo();
		List<AsDict> asDicts=new ArrayList<>();
		JSONObject docInfoJson = JSONUtil.parseObj(HttpUtil.createPost(this.signatureUnitUrl).body(param.toString(),"application/json").execute().body());
		log.info(docInfoJson.toString());
		if ("success".equals(docInfoJson.getStr("msg")) && "0".equals(docInfoJson.getStr("code"))){
			asDicts=docInfoJson.get("data", List.class);
			vo.setAsDicts(asDicts);
			return R.data(200,vo,"获取数据成功");
		}else {
			return R.data(HttpStatus.NOT_FOUND.value(),vo,"接口未连接成功");
		}
	}
}
