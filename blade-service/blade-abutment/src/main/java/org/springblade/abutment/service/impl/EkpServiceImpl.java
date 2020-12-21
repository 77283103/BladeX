package org.springblade.abutment.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.entity.PushEkpEntity;
import org.springblade.abutment.service.IEkpService;
import org.springblade.abutment.vo.EkpVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
public class EkpServiceImpl implements IEkpService {
    @Value("${api.ekp.tokenUrl}")
    private String tokenUrl;
    @Value("${api.ekp.ekpUrl}")
    private String ekpUrl;
    @Value("${api.ekp.account}")
    private String account;
    @Value("${api.ekp.password}")
    private String password;


    @Override
    public String getToken() throws Exception {
        JSONObject param = new JSONObject();
        param.set("accounts", this.account);
        param.set("pwd", this.password);
        String paramStr = param.toString();
        log.info("params:"+paramStr);
        JSONObject tokenJson = JSONUtil.parseObj(HttpUtil.createPost(this.tokenUrl).body(paramStr,"application/json").execute().body());
        log.info("result:"+tokenJson.toString());
        return tokenJson == null ? null : tokenJson.getBool("success") ? tokenJson.getStr("tokenInfo") : null;
    }

    @Override
    public EkpVo pushData(PushEkpEntity entity) throws Exception {
        String paramStr = JSONUtil.toJsonStr(entity);
        log.info("params:"+paramStr);
        JSONObject docInfoJson = JSONUtil.parseObj(HttpUtil.createPost(this.ekpUrl).body(paramStr,"application/json").execute().body());
        log.info("result:"+docInfoJson.toString());
        return docInfoJson == null ? null : docInfoJson.getBool("success") ? new EkpVo(docInfoJson.getStr("docInfo")) : null;
    }
}
