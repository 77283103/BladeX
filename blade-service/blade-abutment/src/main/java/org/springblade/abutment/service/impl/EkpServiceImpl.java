package org.springblade.abutment.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.abutment.entity.PushEkpEntity;
import org.springblade.abutment.service.IEkpService;
import org.springblade.abutment.vo.EkpVo;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * <p>
 * 依据查询 服务实现类
 * </p>
 *
 * @Author: gym
 * @Date: 2018-12-20
 */
@Service
public class EkpServiceImpl implements IEkpService {
	private static final Logger log = LoggerFactory.getLogger(EkpServiceImpl.class);

	@Value("${api.ekp.tokenUrl}")
	private String tokenUrl;
	@Value("${api.ekp.ekpUrl}")
	private String ekpUrl;
	@Value("${api.ekp.agencyUrl}")
	private String agencyUrl;
	@Value("${api.ekp.account}")
	private String account;
	@Value("${api.ekp.password}")
	private String password;


	@Override
	public String getToken() {
		JSONObject tokenJson = null;
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String json = "";
		// 参数
		// 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
		JSONObject param = new JSONObject();
		param.set("accounts", this.account);
		param.set("pwd", this.password);
		String paramStr = param.toString();
		StringEntity entity = new StringEntity(paramStr, "UTF-8");
		// 创建Post请求
		HttpPost httpPost = new HttpPost(this.tokenUrl);
		httpPost.setEntity(entity);
		// 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");

		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Post请求
			response = httpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			log.info("响应状态为:" + response.getStatusLine());
			if (responseEntity != null) {
				log.info("响应内容长度为:{}", responseEntity.getContentLength());
				json = EntityUtils.toString(responseEntity);
				tokenJson = JSONUtil.parseObj(json);
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		assert tokenJson != null;
		log.info("result:" + JSONUtil.toJsonStr(tokenJson));
		return tokenJson.getBool("success") ? tokenJson.getStr("tokenInfo") : null;
	}

	@Override
	public EkpVo pushData(PushEkpEntity entity) {
		String paramStr = JSONUtil.toJsonStr(entity);
		log.info("推送的EKP数据:" + JsonUtil.toJson(paramStr));
		JSONObject docInfoJson = null;
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String json = "";
		// 参数
		// 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
		StringEntity entitys = new StringEntity(paramStr, "UTF-8");
		// 创建Post请求
		HttpPost httpPost = new HttpPost(this.ekpUrl);
		httpPost.setEntity(entitys);
		log.info("推送的EKP数据:" + JsonUtil.toJson(httpPost.getEntity()));
		// 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Post请求
			log.info("开始推送ekp数据:" + JsonUtil.toJson(httpPost));
			response = httpClient.execute(httpPost);
			log.info("推送ekp数据结果:" + JsonUtil.toJson(response));
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			if (null != responseEntity) {
				log.info("从响应模型中获取响应实体【getEntity】：" + JSONUtil.toJsonStr(responseEntity));
				json = EntityUtils.toString(responseEntity);
				log.info("从响应模型中获取响应实体，转成jsonString：" + json);
				docInfoJson = JSONUtil.parseObj(json);
				log.info("从响应模型中获取响应实体：" + JSONUtil.toJsonStr(docInfoJson));
				if (Func.isNull(docInfoJson.getStr("errMessage"))) {
					docInfoJson.set("errMessage", " ");
					log.info("从响应模型中获取响应实体-修改格式后：" + JSONUtil.toJsonStr(docInfoJson));
				}
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		assert docInfoJson != null;
		log.info("获取推送EKP的数据返回的JSON数据：" + JSONUtil.toJsonStr(docInfoJson));
		if (Func.isNull(docInfoJson.getStr("ekp_number"))) {
			return docInfoJson.getBool("success") ? new EkpVo(docInfoJson.getStr("docInfo")) : new EkpVo("");
		} else {
			return docInfoJson.getBool("success") ? new EkpVo(docInfoJson.getStr("docInfo"), docInfoJson.getOrDefault("ekp_number","").toString()) : new EkpVo("");
		}
	}

	@Override
	public EkpVo pushAgency(PushEkpEntity entity) throws Exception {
		String paramStr = JSONUtil.toJsonStr(entity);
		log.info("推送的EKP数据:" + JsonUtil.toJson(paramStr));
		JSONObject docInfoJson = null;
		// 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		String json = "";
		// 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
		StringEntity entitys = new StringEntity(paramStr, "UTF-8");
		// 创建Post请求
		HttpPost httpPost = new HttpPost(this.agencyUrl);
		httpPost.setEntity(entitys);
		// 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
		httpPost.setHeader("Content-Type", "application/json;charset=utf8");
		// 响应模型
		CloseableHttpResponse response = null;
		try {
			// 由客户端执行(发送)Post请求
			response = httpClient.execute(httpPost);
			// 从响应模型中获取响应实体
			HttpEntity responseEntity = response.getEntity();
			if (null != responseEntity) {
				json = EntityUtils.toString(responseEntity);
				docInfoJson = JSONUtil.parseObj(json);
			}
			log.info("EKP推送代办成功："+ JSONUtil.toJsonStr(docInfoJson));
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放资源
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		assert docInfoJson != null;
		return "0".equals(docInfoJson.getStr("code")) ? new EkpVo(docInfoJson.getStr("notifyId")) : new EkpVo("");
	}
}
