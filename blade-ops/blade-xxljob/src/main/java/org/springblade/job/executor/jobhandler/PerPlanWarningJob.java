package org.springblade.job.executor.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.log4j.Log4j2;

import org.springblade.contract.feign.IContractClient;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Log4j2
@Component
public class PerPlanWarningJob {

	@Autowired
	private IContractClient contractClient;


	@XxlJob("perPlanWarningHandler")
	public ReturnT<String> PerPlanWarningHandler(String param) throws Exception {
		log.info("启动履约计划预警扫描任务");
		R<ContractFormInfoResponseVO> contractFormInfoResponseVOR = contractClient.getById(122222222222222L);
		log.info("测试获取合同信息:{}",JsonUtil.toJson(contractFormInfoResponseVOR));
		return ReturnT.SUCCESS;
	}


}
