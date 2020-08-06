package org.springblade.flow.core;

import org.flowable.bpmn.model.ExtensionAttribute;

/**
 * TODO
 *
 * @author gangzi
 * @date 2020/7/2114:58
 */
public class ExtensionAttributeUtils implements HmXMLContants {
	public static ExtensionAttribute generate(String key ,String val){
		ExtensionAttribute ea= new ExtensionAttribute();
		ea.setNamespace(NEMESPACE);
		ea.setName(key);
		ea.setNamespacePrefix(NEMESPACE_PREFIX);
		ea.setValue(val);
		return ea;
	}
}
