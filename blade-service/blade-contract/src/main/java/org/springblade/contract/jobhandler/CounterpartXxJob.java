package org.springblade.contract.jobhandler;

import cn.hutool.json.JSONUtil;
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
import org.springblade.abutment.vo.OrgParme;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		OrgParme orgParme=new OrgParme();
		//param  填数据的时候是手动更新数据   为空的话就是更新当前时间前一天的数据
		if (Func.isNotEmpty(param)) {
			String[] code = param.split(",");
			List<String> tagP = Arrays.asList(code);
			log.info("启动获取组织及人员增量信息任务:" + Arrays.toString(code) + ":" + param);
			orgParme.setParme(tagP.get(0));
			orgParme.setTag(tagP.get(1));
		}else {
			orgParme.setParme(param);
		}
		log.info("启动相对方增量更新任务");
		CounterpartEntity entity=new CounterpartEntity();
		log.info("相对方模板实体："+JsonUtil.toJson(entity));
		log.info("查看查询时间的参数：" + JSONUtil.toJsonStr(param));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		//param不为空  并且tag为1  标识自动更新 不赋值   为2的时候标识手动更新数据
		if (Func.isNotEmpty(orgParme.getParme()) && "2".equals(orgParme.getTag())) {
			entity.setYyyyMMdd(orgParme.getParme());
		} else if (Func.isNotEmpty(orgParme.getParme()) && "1".equals(orgParme.getTag())) {
			entity.setYyyyMMdd(format.format(calendar.getTime()));
		}
		R<CounterpartVo> vo=abutmentClient.getCounterpart(entity);
		log.info("counterpartVo：" + JsonUtil.toJson(vo));
		if (HttpStatus.OK.value()==vo.getCode()) {
			log.info("获取相对方新增信息集合结果:{}", JsonUtil.toJson(vo.getData().getInsert()));
			log.info("获取相对方更新信息集合结果:{}", JsonUtil.toJson(vo.getData().getUpdate()));
			XxlJobLogger.log("获取相对方新增信息集合结果:"+JsonUtil.toJson(vo.getData().getInsert()));
			XxlJobLogger.log("获取相对方更新信息集合结果:"+JsonUtil.toJson(vo.getData().getUpdate()));
			return new ReturnT<>(vo.getData());
		}
		XxlJobLogger.log(vo.getCode()+":"+vo.getMsg());
		return new ReturnT<>(vo.getCode(),vo.getMsg());

	}
}
