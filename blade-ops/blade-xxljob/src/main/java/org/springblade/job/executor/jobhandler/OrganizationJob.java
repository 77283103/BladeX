package org.springblade.job.executor.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.log4j.Log4j2;
import lombok.SneakyThrows;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class OrganizationJob {

//	@Autowired
//	private IAbutmentClient iAbutmentClient;


	/**
	 * 获取组织及人员增量信息
	 *
	 * @return
	 */
	@SneakyThrows
	@XxlJob("organizationJob")
	public ReturnT<String> queryOrganization() {
//		R<List<OrganizationVo>> organizationVos=iAbutmentClient.getOrganizationInfoIncrement();
//		log.info("getOrganizationInfoIncrement：{}", JsonUtil.toJson(organizationVos));
		return ReturnT.SUCCESS;
	}
}
