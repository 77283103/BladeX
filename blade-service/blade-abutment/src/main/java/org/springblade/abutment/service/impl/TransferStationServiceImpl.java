package org.springblade.abutment.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springblade.abutment.properties.TransferStationProperties;
import org.springblade.abutment.service.TransferStationService;
import org.springblade.abutment.vo.TransferStationTokenVo;
import org.springblade.contract.dto.middleground.Contract;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class TransferStationServiceImpl implements TransferStationService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TransferStationProperties transferStationProperties;

	@Override
	public R pushContractData(Contract contract) {
		if(Func.isEmpty(contract)){
			return R.fail("合同信息为空!");
		}
		//设置请求头
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		headers.add("Authorization", "Bearer "+getToken());
		//调用中台推送合同接口
		log.info("中台推送合同-开始推送数据:{}",JsonUtil.toJson(contract));
		String res = post(transferStationProperties.getPush_url(),JsonUtil.toJson(contract),headers);
		log.info("中台推送合同-数据推送结果:{}",res);
		return R.data(res);
	}

	@Override
	public String getToken(){
		//设置请求头
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		//构造请求参数
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("username",transferStationProperties.getToken_username());
		paramMap.put("password",transferStationProperties.getToken_password());
		//调用中台获取token接口
		log.info("中台接口-获取token开始:{}",JsonUtil.toJson(paramMap));
		String json = post(transferStationProperties.getToken_url(),JsonUtil.toJson(paramMap),headers);
		TransferStationTokenVo transferStationTokenVo = JsonUtil.parse(json, TransferStationTokenVo.class);
		log.info("中台接口-获取token结果:{}",JsonUtil.toJson(transferStationTokenVo));
		return transferStationTokenVo.getCode() == HttpStatus.OK.value()?transferStationTokenVo.getData():"";
	}



	private String post(String url,String paramJson,HttpHeaders headers){
		//设置请求参数
		HttpEntity<String> requestBody = new HttpEntity<>(paramJson, headers);
		//调用接口
		try{
			return restTemplate.postForObject(url,requestBody,String.class);
		}catch (Exception e){
			log.error("中台接口---调用失败:{}",e.getMessage());
			return "";
		}
	}

}
