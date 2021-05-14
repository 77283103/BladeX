package org.springblade.contract.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.abutment.entity.CounterpartEntity;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.CounterpartVo;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

/**
 * @author xhbbo
 */
@Log4j2
@AllArgsConstructor
@Component
public class CounterpartXxJob {
	private static Logger logger = LoggerFactory.getLogger(CounterpartXxJob.class);
	private final IAbutmentClient abutmentClient;
	/**
	 * 1、简单任务示例（Bean模式）
	 */
	@XxlJob("inOrUpXxxJob")
	public ReturnT<CounterpartVo> inOrUp(String param) throws Exception {
		log.info("启动相对方增量更新任务");
		CounterpartEntity entity=new CounterpartEntity();
		log.info("相对方模板实体："+JsonUtil.toJson(entity));
		R<CounterpartVo> vo=abutmentClient.getCounterpart(entity);
		if (HttpStatus.OK.value()==vo.getCode()  && Func.isNotEmpty(vo.getData())) {
			log.info("获取相对方新增信息集合结果:{}", JsonUtil.toJson(vo.getData().getInsert()));
			log.info("获取相对方更新信息集合结果:{}", JsonUtil.toJson(vo.getData().getUpdate()));
			XxlJobLogger.log("获取相对方新增信息集合结果:"+JsonUtil.toJson(vo.getData().getInsert()));
			XxlJobLogger.log("获取相对方更新信息集合结果:"+JsonUtil.toJson(vo.getData().getUpdate()));
			return new ReturnT<>(vo.getData());
		}
		XxlJobLogger.log("获取相对方新增信息集合结果:"+JsonUtil.toJson(vo.getData().getInsert()));
		XxlJobLogger.log("获取相对方更新信息集合结果:"+JsonUtil.toJson(vo.getData().getUpdate()));
		XxlJobLogger.log(vo.getMsg()+vo.getData());
		return new ReturnT<>(vo.getCode(),vo.getMsg());

	}
}
