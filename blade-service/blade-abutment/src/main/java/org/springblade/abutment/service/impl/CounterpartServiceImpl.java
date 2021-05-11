package org.springblade.abutment.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpHead;
import org.springblade.abutment.entity.CounterpartEntity;
import org.springblade.abutment.service.ICounterpartService;
import org.springblade.abutment.vo.CounterpartVo;
import org.springblade.contract.feign.IContractClient;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 相对方数据实现类
 * @author xhbbo
 */
@Log4j2
@Service
public class CounterpartServiceImpl implements ICounterpartService{
	@Autowired
	private IContractClient contractClient;
	@Value("${api.doc.tokenUrl}")
	private String tokenUrl;
	@Value("${api.doc.counterpartUrl}")
	private String counterpartUrl;
	@Value("${api.doc.username}")
	private String userName;
	@Value("${api.doc.password}")
	private String password;

	/**
	 * 获取toke
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getToken() throws Exception {
		JSONObject param = new JSONObject();
		param.set("username", this.userName);
		param.set("password", this.password);
		JSONObject tokenJson = JSONUtil.parseObj(HttpUtil.createPost(this.tokenUrl).body(param.toString(),"application/json").execute().body());
		log.info(tokenJson.toString());
		return "success".equals(tokenJson.getStr("msg")) ? tokenJson.getStr("token") : null;
	}

	/**
	 * 新增
	 * @param entity 返回数据的vo
	 * @return 返回数据
	 * @throws Exception
	 */
	@Override
	public List<CounterpartEntity> getInsert(CounterpartEntity entity) throws Exception {
		JSONObject docInfoJson = JSONUtil.parseObj(HttpUtil.createPost(this.counterpartUrl).body(JSONUtil.toJsonStr(entity),"application/json").execute().body());
		log.info(docInfoJson.toString());
		return "success".equals(docInfoJson.getStr("msg")) ? docInfoJson.get("insert", List.class) : null;
	}
	/**
	 * 更新
	 * @param entity 返回数据的vo
	 * @return 返回数据
	 * @throws Exception
	 */
	@Override
	public List<CounterpartEntity> getUpdate(CounterpartEntity entity) throws Exception {
		JSONObject docInfoJson = JSONUtil.parseObj(HttpUtil.createPost(this.counterpartUrl).body(JSONUtil.toJsonStr(entity),"application/json").execute().body());
		log.info(docInfoJson.toString());
		return "success".equals(docInfoJson.getStr("msg")) ? docInfoJson.get("update", List.class): null;
	}

	@Override
	public R<CounterpartVo> getInsOrUp(CounterpartEntity entity) throws Exception {
		log.info("查看token是否传进来："+JSONUtil.toJsonStr(entity));
		CounterpartVo vo=new CounterpartVo();
		JSONObject docInfoJson = JSONUtil.parseObj(HttpUtil.createPost(this.counterpartUrl).body(JSONUtil.toJsonStr(entity), "application/json").execute().body());
		log.info(""+docInfoJson.toString());
		if ("success".equals(docInfoJson.getStr("msg"))){
			vo.setInsert(docInfoJson.get("insert", List.class));
			vo.setUpdate(docInfoJson.get("update", List.class));
			return R.data(vo);
		}else {
			return R.data(HttpStatus.NOT_FOUND.value(),null,"未查到数据");
		}

	}

	@SneakyThrows
	@Override
	public boolean insOrUp(CounterpartEntity entity) {

		return false;
	}
}
