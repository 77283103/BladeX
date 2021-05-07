package org.springblade.job.executor.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.tool.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrganizationJob {
	private static Logger logger = LoggerFactory.getLogger(OrganizationJob.class);
	@Autowired
	private IAbutmentClient iAbutmentClient;
	/**
	 * 获取组织及人员增量信息
	 *
	 * @return
	 */
	@SneakyThrows
	@XxlJob("organizationJob")
	public ReturnT<String> queryOrganization(String param) {
		R<List<OrganizationVo>> organizationVos=iAbutmentClient.getOrganizationInfoIncrement();
		return ReturnT.SUCCESS;
	}
}
