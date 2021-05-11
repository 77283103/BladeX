package org.springblade.abutment.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.entity.DocEntity;
import org.springblade.abutment.service.IDocService;
import org.springblade.abutment.vo.DocVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 依据查询 服务实现类
 * </p>
 *
 * @Author: gym
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class DocServiceImpl implements IDocService {
    @Value("${api.doc.SecurityKey}")
    private String SecurityKey;
    @Value("${api.doc.tokenUrl}")
    private String tokenUrl;
    @Value("${api.doc.docInfoUrl}")
    private String docInfoUrl;
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
        return tokenJson == null ? null : tokenJson.getStr("status").equals("success") ? tokenJson.getStr("token") : null;
    }

    @Override
    public List<DocVo> getDocInfo(DocEntity entity) throws Exception {
        JSONObject docInfoJson = JSONUtil.parseObj(HttpUtil.createPost(this.docInfoUrl).body(JSONUtil.toJsonStr(entity),"application/json").execute().body());
        log.info(docInfoJson.toString());
        return docInfoJson == null ? null : docInfoJson.getStr("status").equals("success") ? docInfoJson.get("docList", List.class) : null;
    }
}
