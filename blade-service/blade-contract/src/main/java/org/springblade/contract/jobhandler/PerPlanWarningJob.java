package org.springblade.contract.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.log4j.Log4j2;
import org.springblade.contract.service.IPerServiceContentService;
import org.springblade.contract.vo.PerServiceContentResponseVO;
import org.springblade.core.log.logger.BladeLogger;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@Component
public class PerPlanWarningJob {

	@Autowired
	private IPerServiceContentService perServiceContentService;
	@Autowired
	private BladeLogger bladeLogger;

	@XxlJob("perPlanWarningHandler")
	public ReturnT<String> PerPlanWarningHandler(String param) throws Exception {
		bladeLogger.info("","履约预警任务开始");
		List<PerServiceContentResponseVO> perServiceContentList = perServiceContentService.findWarningList();
		bladeLogger.info(Long.toString(Thread.currentThread().getId()),"获取预警信息集合结果:" + JsonUtil.toJson(perServiceContentList));
		return ReturnT.SUCCESS;
	}


}
