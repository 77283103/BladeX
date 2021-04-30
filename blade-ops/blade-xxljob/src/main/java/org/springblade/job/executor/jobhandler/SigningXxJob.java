package org.springblade.job.executor.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.feign.IContractClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 未归档定时扫描推送消息
 * @author xhbbo
 */
@Component
public class SigningXxJob {

	private static Logger logger = LoggerFactory.getLogger(SampleXxlJob.class);
	@Autowired
	private IContractClient iContractClient;

	@XxlJob("signingPostEKP")
	public ReturnT<String> signingNotPostEKP(){

		return ReturnT.SUCCESS;
	}
}
