package org.springblade.abutment.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.entity.CounterpartEntity;
import org.springblade.abutment.service.ICounterpartService;
import org.springblade.abutment.vo.CounterpartVo;
import org.springblade.contract.feign.IContractClient;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xhbbo
 */
@Slf4j
@RestController
@RequestMapping("/counterpart")
@Api(value = "相对方增量信息")
@AllArgsConstructor
public class CounterpartController {
	private final ICounterpartService counterpartService;

	@SneakyThrows
	@PostMapping("/inOrUp")
	@ApiOperation(value = "相对方增量信息")
	public R<String> inOrUp(){
		CounterpartEntity entity=new CounterpartEntity();
		CounterpartVo counterpartVo = new CounterpartVo();
		String token = counterpartService.getToken();
		if (StrUtil.isNotEmpty(token)) {
			counterpartVo.setInsert(counterpartService.getInsert(entity));
			counterpartVo.setUpdate(counterpartService.getUpdate(entity));
		}
		boolean counterpart = counterpartService.insOrUp(counterpartVo);
		if (counterpart){
			return R.data(200,"success","储存成功！");
		} else {
			return R.data(404, null, "存储失败！");
		}
	}
}
