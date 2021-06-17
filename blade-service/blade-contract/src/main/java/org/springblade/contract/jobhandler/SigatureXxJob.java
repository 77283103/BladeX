package org.springblade.contract.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.abutment.entity.AsDict;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.AsDictVo;
import org.springblade.contract.entity.ContractSealEntity;
import org.springblade.contract.service.IContractSealService;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.feign.IDictBizClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhbbo
 */
@Log4j2
@AllArgsConstructor
@Component
public class SigatureXxJob {
	private static Logger logger = LoggerFactory.getLogger(SigatureXxJob.class);
	private final IDictBizClient dictService;
	private final IAbutmentClient abutmentClient;
	private final IContractSealService contractSealService;

	/**
	 * 更新添加申请用印单位的单位数据
	 *
	 * @param param
	 * @return boolean
	 * @author jitwxs
	 * @date 2021/6/5 23:10
	 */
	@XxlJob("SigatureXxJob")
	public ReturnT<AsDictVo> submit(String param) throws Exception {
		List<DictBiz> dictBizVOS = dictService.getList("application_seal").getData();
		List<AsDict> as = new ArrayList<>();
		AsDict asDict = new AsDict();
		R<AsDictVo> vo = abutmentClient.querySigatureInfo(asDict);
		if (HttpStatus.OK.value() == vo.getCode() && Func.isNotEmpty(vo.getData())) {
			vo.getData().getAsDicts().forEach(asD -> {
				if (Func.isEmpty(dictService.getKey("application_seal", asD.getFd_factName()).getData())) {
					as.add(asD);
				}
			});
			if (Func.isNotEmpty(as)) {
				for (int i = 0; i <= as.size(); i++) {
					DictBiz d = new DictBiz();
					ContractSealEntity sealEntity=new ContractSealEntity();
					d.setTenantId("000000");
					//父主键
					d.setParentId(1341615089769291777L);
					//字典码
					d.setCode("application_seal");
					//字典名称
					d.setDictValue(as.get(i).getFd_factName());
					d.setSort(i + dictBizVOS.size() + 1);
					//字典值
					d.setDictKey(String.valueOf(i + dictBizVOS.size() + 1));
					//是否已封存
					d.setIsSealed(0);
					//备注(保存企业编号合同单位编号)
					d.setRemark(as.get(i).getFd_factNo() + "," + as.get(i).getFd_taxNo());
					dictService.submit(d);
					sealEntity.setFdSeq(as.get(i).getFd_seq());
					sealEntity.setFdFactno(as.get(i).getFd_factNo());
					sealEntity.setFdFactname(as.get(i).getFd_factName());
					sealEntity.setFdTaxno(as.get(i).getFd_taxNo());
					contractSealService.save(sealEntity);
				}
			}
			XxlJobLogger.log(vo.getMsg() + vo.getData());
			return new ReturnT<>(vo.getData());
		} else {
			XxlJobLogger.log(vo.getCode() + vo.getMsg());
			return new ReturnT<>(vo.getCode(), vo.getMsg());
		}
	}
}
