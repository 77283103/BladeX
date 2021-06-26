package org.springblade.abutment.service.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springblade.abutment.entity.CounterpartEntity;
import org.springblade.abutment.service.ICounterpartService;
import org.springblade.abutment.vo.CounterpartVo;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.feign.IContractClient;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	@Value("${api.doc.Token}")
	private String tokenUrl;
	@Value("${api.doc.counterpartUrl}")
	private String counterpartUrl;
	@Value("${api.doc.Username}")
	private String userName;
	@Value("${api.doc.Password}")
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
			vo=JSONUtil.toBean(docInfoJson,CounterpartVo.class);
			return R.data(vo);
		}else {
			return R.data(HttpStatus.NOT_FOUND.value(),null,"未查到数据");
		}

	}

	@SneakyThrows
	@Override
	public boolean insOrUp(CounterpartVo counterpartVo) {
		List<ContractCounterpartEntity> listInsert = new ArrayList<>();
		List<ContractCounterpartEntity> listUpdate = new ArrayList<>();
		counterpartVo.getInsert().forEach(i -> {
			ContractCounterpartEntity in = new ContractCounterpartEntity();
			in.setName(i.getCustNm());
			in.setUnifiedSocialCreditCode(i.getBusinessId());
			in.setOrganizationCode(i.getBusinessId());
			listInsert.add(in);
		});
		log.info("新增的数据：" + JsonUtil.toJson(listInsert));
		contractClient.saveBatch(listInsert);
		counterpartVo.getUpdate().forEach(u -> {
			ContractCounterpartEntity up = new ContractCounterpartEntity();
			up.setName(u.getCustNm());
			up.setUnifiedSocialCreditCode(u.getBusinessId());
			up.setOrganizationCode(u.getBusinessId());
			listUpdate.add(up);
		});
		log.info("更新的数据：" + JsonUtil.toJson(listUpdate));
		listUpdate.forEach(l -> {
			List<ContractCounterpartEntity> entityList=contractClient.selectByName(l.getUnifiedSocialCreditCode()).getData();
			if (Func.isNotEmpty(entityList)){
				l.setId(entityList.get(0).getId());
				contractClient.updateById(l);
			}
		});
		return false;
	}
}
