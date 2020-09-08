package org.springblade.flow.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.flow.business.mapper.ProcessMapper;
import org.springblade.flow.business.service.IProcessService;
import org.springblade.flow.core.entity.ProcessEntity;
import org.springblade.flow.core.utils.FlowUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程定义信息表 服务实现类
 *
 * @author tianah
 * @date 2020-8-27
 */
@Service
public class ProcessServiceImpl extends BaseServiceImpl<ProcessMapper, ProcessEntity> implements IProcessService {

	@Override
	public IPage<ProcessEntity> pageList(IPage<ProcessEntity> page, ProcessEntity process) {
		return baseMapper.pageList(page, process);
	}

	@Override
	public List<ProcessEntity> getProcessByBusinessType(String businessType) {
		return baseMapper.selectList(Wrappers.<ProcessEntity>query().lambda().eq(ProcessEntity::getBusinessType, businessType));
	}

	@Override
	public List<Map<String, String>> getBeanFields(String businessType) throws ClassNotFoundException {
		List<Map<String, String>> list = new ArrayList<>(16);
		Class<?> clazz = Class.forName(FlowUtil.getBusinessFullClassName(businessType));
		Field[] fields = clazz.getDeclaredFields();
		/*遍历出所有属性返回前台用于下拉选展示*/
		for (int i = 0; i < fields.length; i++) {
			Map<String, String> map = new HashMap<>();
			Field field = fields[i];
			/*获取私有属性*/
			field.setAccessible(true);
			map.put("dictKey",field.getName());
			map.put("dictValue",field.getName());
			list.add(map);
		}
		return list;
	}
}
