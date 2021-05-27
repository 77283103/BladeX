package org.springblade.contract.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @author xhbbo
 */
@Log4j2
@AllArgsConstructor
@Component
public class OrganizationJob {
	private static Logger logger = LoggerFactory.getLogger(OrganizationJob.class);
	private final IAbutmentClient iAbutmentClient;
	/**
	 * 获取组织及人员增量信息
	 *
	 * @return
	 */
	@SneakyThrows
	@XxlJob("organizationJob")
	public ReturnT<List<OrganizationVo>> queryOrganization(String param) {
		log.info("启动获取组织及人员增量信息任务");
		R<List<OrganizationVo>> organizationVos=iAbutmentClient.getOrganizationInfoIncrement();
		if (HttpStatus.OK.value()==organizationVos.getCode()){
			log.info("组织机构人员信息:"+ JsonUtil.toJson(organizationVos.getData()));
		}
		XxlJobLogger.log(organizationVos.getMsg()+organizationVos.getData());
		return new ReturnT<>(organizationVos.getCode(),organizationVos.getMsg());
	}
}
