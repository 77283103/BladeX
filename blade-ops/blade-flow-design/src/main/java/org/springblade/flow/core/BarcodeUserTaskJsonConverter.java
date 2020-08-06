package org.springblade.flow.core;

import com.fasterxml.jackson.databind.JsonNode;
import org.flowable.bpmn.model.BaseElement;
import org.flowable.bpmn.model.ExtensionAttribute;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.UserTask;
import org.flowable.editor.language.json.converter.BaseBpmnJsonConverter;
import org.flowable.editor.language.json.converter.UserTaskJsonConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author gangzi
 * @date 2020/7/2114:16
 */
public class BarcodeUserTaskJsonConverter extends UserTaskJsonConverter {
	public static void fillBpmnTypes(Map<Class<? extends BaseElement>,Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap){
		convertersToJsonMap.put(UserTask.class,BarcodeUserTaskJsonConverter.class);
	}
	@Override
	protected FlowElement convertJsonToElement(JsonNode elementNode, JsonNode modelNode, Map<String, JsonNode> shapeMap) {
		FlowElement flowElement = super.convertJsonToElement(elementNode, modelNode, shapeMap);
		if(flowElement instanceof UserTask){
			UserTask userTask = (UserTask) flowElement;
			// 获取自定义的属性变量
			String barcodeExtId = getPropertyValueAsString("barcode_ext_id", elementNode);
			Map<String, List<ExtensionAttribute>> stringListMap = new HashMap<>();
			ExtensionAttribute ea = ExtensionAttributeUtils.generate("barcodeExtId", barcodeExtId);
			stringListMap.put("barcode--ext", Arrays.asList(ea));
			userTask.setAttributes(stringListMap);
		}

		return flowElement;
	}
}
