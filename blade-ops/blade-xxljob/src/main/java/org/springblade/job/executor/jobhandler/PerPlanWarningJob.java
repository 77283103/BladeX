package org.springblade.job.executor.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;


@Log4j2
@Component
public class PerPlanWarningJob {



	@XxlJob("perPlanWarningHandler")
	public ReturnT<String> PerPlanWarningHandler(String param) throws Exception {
		log.info("启动履约计划预警扫描任务");
		return ReturnT.SUCCESS;
	}


}
