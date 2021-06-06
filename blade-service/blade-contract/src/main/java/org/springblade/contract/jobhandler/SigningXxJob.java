package org.springblade.contract.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.EkpVo;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 合同归档时间较审核完毕时间超过60天（待定），则系统提示合同管理发起追踪。（给合同签订发起人推送消息，让发起人来填写未归档原因）
 * 未归档定时扫描推送消息
 *
 * @author xhbbo
 */
@Log4j2
@AllArgsConstructor
@Component
public class SigningXxJob {
	private static Logger logger = LoggerFactory.getLogger(SigningXxJob.class);
	private final IAbutmentClient abutmentClient;
	/**
	 * @param param 形参
	 * @return
	 */
	@XxlJob("pushNotSig")
	public ReturnT<List<EkpVo>> pushNotSig(String param)throws Exception{
		log.info("启动未归档信息扫面任务");
		R<List<EkpVo>> r=abutmentClient.pushNotSig(new ContractFormInfoEntity());
		if (HttpStatus.OK.value()==r.getCode() && Func.isNotEmpty(r.getData())) {
			log.info("获取未归档信息推送返回集合结果:"+JsonUtil.toJson(r.getData()));
			XxlJobLogger.log("获取未归档信息推送返回集合结果:"+JsonUtil.toJson(r.getData()));
			return new ReturnT<>(r.getData());
		}
		XxlJobLogger.log(r.getMsg()+r.getData());
		return new ReturnT<>(r.getCode(),r.getMsg());
	}
}
