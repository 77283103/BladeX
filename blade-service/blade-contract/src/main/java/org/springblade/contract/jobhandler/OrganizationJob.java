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
import org.springblade.abutment.vo.EkpSyncRequestVO;
import org.springblade.abutment.vo.OrgParme;
import org.springblade.abutment.vo.OrganizationVo;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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
	public ReturnT<List<OrganizationVo>> queryOrganization(String param){
		OrgParme orgParme=new OrgParme();
		//param  填数据的时候是手动更新数据   为空的话就是更新当前时间前一天的数据
		if (Func.isNotEmpty(param)) {
			String[] code = param.split(",");
			List<String> tagP = Arrays.asList(code);
			log.info("启动获取组织及人员增量信息任务:" + JsonUtil.toJson(tagP) + ":" + param);
			orgParme.setParme(tagP.get(0));
			orgParme.setTag(tagP.get(1));
		}else {
			orgParme.setParme(param);
		}
		log.info("启动获取组织及人员增量信息任务"+JsonUtil.toJson(orgParme));
		R<List<OrganizationVo>> organizationVos=iAbutmentClient.getOrganizationInfoIncrement(orgParme);
		if (HttpStatus.OK.value()==organizationVos.getCode()){
			log.info("组织机构人员信息:"+ JsonUtil.toJson(organizationVos.getData()));
			XxlJobLogger.log(organizationVos.getMsg()+organizationVos.getData());
		}
		XxlJobLogger.log(organizationVos.getCode()+":"+organizationVos.getMsg());
		return new ReturnT<>(organizationVos.getCode(),organizationVos.getMsg());
	}


	@SneakyThrows
	@XxlJob("synEkpUserDepartJob")
	public ReturnT synEkpUserDepart(String param){
		EkpSyncRequestVO ekpSyncRequestVO = JsonUtil.parse(param,EkpSyncRequestVO.class);
		R r = iAbutmentClient.synEkpUserDepart(ekpSyncRequestVO);
		return new ReturnT<>(r.getData());
	}


}
