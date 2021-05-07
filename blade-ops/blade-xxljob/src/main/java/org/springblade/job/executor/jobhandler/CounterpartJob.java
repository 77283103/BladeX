package org.springblade.job.executor.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.abutment.entity.CounterpartEntity;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author xhbbo
 */
@Component
public class CounterpartJob {
	@Autowired
	private IAbutmentClient abutmentClient;
	private static Logger logger = LoggerFactory.getLogger(CounterpartJob.class);
	/**
	 * 1、简单任务示例（Bean模式）
	 */
	@XxlJob("inOrUp")
	public ReturnT<String> inOrUp(String param) throws Exception {
		CounterpartEntity entity=new CounterpartEntity();
		abutmentClient.getCounterpart(entity);
		XxlJobLogger.log("XXL-JOB, Hello World.");

		for (int i = 0; i < 5; i++) {
			XxlJobLogger.log("beat at:" + i);
			TimeUnit.SECONDS.sleep(2);
		}
		return ReturnT.SUCCESS;
	}
}
