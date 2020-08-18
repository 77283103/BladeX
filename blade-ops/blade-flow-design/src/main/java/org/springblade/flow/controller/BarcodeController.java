package org.springblade.flow.controller;

import org.flowable.ui.common.service.exception.BadRequestException;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.rest.app.AbstractModelBpmnResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.flow.service.impl.BarcodeModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录用户模拟返回
 *
 * @author Chill
 */
@RestController
@RequestMapping("/barcode")
public class BarcodeController extends AbstractModelBpmnResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(BarcodeController.class);
	@Autowired
	protected BarcodeModelServiceImpl barcodeModelService;
	@Override
	@RequestMapping(value = {"/rest/models/{processModelId}/bpmn20"},method = {RequestMethod.GET})
	public void getProcessModelBpmn20Xml(HttpServletResponse response, @PathVariable String processModelId) throws IOException {
		//super.getProcessModelBpmn20Xml(response, processModelId);
		if (processModelId == null) {
			throw new BadRequestException("No process model id provided");
		} else {
//			Model model = this.modelService.getModel(processModelId);
			Model model = this.barcodeModelService.getModel(processModelId);
			this.generateBpmn20Xml(response, model);
		}
	}
}
