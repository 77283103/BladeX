package org.springblade.abutment.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springblade.abutment.service.IAsService;
import org.springblade.abutment.vo.AsDictVo;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.feign.IDictBizClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xhbbo
 */
@Log4j2
@Service
@AllArgsConstructor
public class AsServiceImpl implements IAsService {
	private final IDictBizClient dictService;

	/**
	 * 更新添加申请用印单位的单位数据
	 * @author jitwxs
	 * @date 2021/6/5 23:10
	 * @param dict
	 * @return boolean
	 */
	@Override
	public List<DictBiz> submit(AsDictVo dict) {
		List<DictBiz> dictBizVOS=dictService.getList("application_seal").getData();
		List<DictBiz> dictList=new ArrayList<>();
		for(int i=0;i<=dict.getAsDicts().size();i++){
			DictBiz d=new DictBiz();
			d.setParentId(1341615089769291777L);
			d.setCode("application_seal");
			d.setDictValue(dict.getAsDicts().get(i).getDict_value());
			d.setIsSealed(0);
			d.setRemark(dict.getAsDicts().get(i).getApp_n());
			d.setDictKey(String.valueOf(i+dictBizVOS.size()+1));
			dictService.submit(d);
			dictList.add(d);
		}
		return dictList;
	}
}
