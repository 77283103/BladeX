package org.springblade.flow.service.impl;

import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.ui.modeler.service.ModelServiceImpl;
import org.springblade.flow.core.BarcodeBpmnJsonConverter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author gangzi
 * @date 2020/7/2114:10
 */
@Service("barcodeModelService")
@Primary
public class BarcodeModelServiceImpl extends ModelServiceImpl {
	protected BpmnJsonConverter bpmnJsonConverter = new BarcodeBpmnJsonConverter();
}
