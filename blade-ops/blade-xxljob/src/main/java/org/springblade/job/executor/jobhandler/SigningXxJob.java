package org.springblade.job.executor.jobhandler;

import cn.hutool.core.util.StrUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springblade.abutment.entity.DocCreatorEntity;
import org.springblade.abutment.entity.FormValuesEntity;
import org.springblade.abutment.entity.PushEkpEntity;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.EkpVo;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.feign.IContractClient;
import org.springblade.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 合同归档时间较审核完毕时间超过60天（待定），则系统提示合同管理发起追踪。（给合同签订发起人推送消息，让发起人来填写未归档原因）
 * 未归档定时扫描推送消息
 *
 * @author xhbbo
 */
@Component
public class SigningXxJob {

	@Value("${api.ekp.fdTemplateId}")
	private String fdTemplateId;
	@Autowired
	private IContractClient iContractClient;
	@Autowired
	private IAbutmentClient iAbutmentClient;
	private final static String STATUS = "50";

	@XxlJob("signingPostEKP")
	public ReturnT<String> signingNotPostEKP(String param) {
		List<EkpVo> ekpVo = new ArrayList<>();
		//KEP接口传输的入参
		PushEkpEntity pushEkpEntity = new PushEkpEntity();
		List<ContractFormInfoEntity> formInfoEntities = iContractClient.getByStatus(STATUS).getData();
		formInfoEntities.forEach(f -> {
			//此处判断创建时间为送审时间
			int day = differentDaysByMillisecond(f.getCreateTime(), new Date());
			if (day >= 60) {
				EkpVo ekp = null;
				try {
					//docCreator 人员编号
					DocCreatorEntity docCreatorEntity = new DocCreatorEntity();
					docCreatorEntity.setEmplno(f.getPersonCodeContract());
					pushEkpEntity.setDocCreator(docCreatorEntity);
					//fdTemplateId 表单模版ID
					pushEkpEntity.setFdTemplateId(this.fdTemplateId);
					//formValues 主要内容
					FormValuesEntity formValuesEntity = new FormValuesEntity();
					formValuesEntity.setFd_contract_id(f.getId().toString());
					// docSubject 合同主旨
					pushEkpEntity.setDocSubject(f.getContractName());
					//获取TOKEN
					pushEkpEntity.setToken(iAbutmentClient.tokenEkp().getData());
					if (StrUtil.isEmpty(pushEkpEntity.getToken())) {
					}
					//推送数据
					ekp = iAbutmentClient.pushData(pushEkpEntity).getData();
					if (Func.isEmpty(ekp)) {
						ekpVo.add(ekp);
					}
					TimeUnit.SECONDS.sleep(2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return ReturnT.SUCCESS;
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysByMillisecond(Date date1, Date date2) {
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
		return days;
	}
}
