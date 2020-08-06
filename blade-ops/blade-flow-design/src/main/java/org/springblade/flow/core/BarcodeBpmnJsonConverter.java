package org.springblade.flow.core;

import org.flowable.editor.language.json.converter.BpmnJsonConverter;

/**
 * TODO
 *
 * @author gangzi
 * @date 2020/7/2114:13
 */
public class BarcodeBpmnJsonConverter extends BpmnJsonConverter {
	static {
		convertersToBpmnMap.put(STENCIL_TASK_USER, BarcodeUserTaskJsonConverter.class);
	}
}
