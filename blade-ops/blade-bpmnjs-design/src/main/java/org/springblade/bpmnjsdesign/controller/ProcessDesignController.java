package org.springblade.bpmnjsdesign.controller;

import com.alibaba.fastjson.JSONObject;
import org.springblade.bpmnjsdesign.service.ModelEditorJsonRestResource;
import org.springblade.bpmnjsdesign.service.ModelSaveRestResource;
import org.springblade.bpmnjsdesign.service.ProcessDesignService;
import org.flowable.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * TODO
 *
 * @author gangzi
 * @date 2020/7/3013:20
 */
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "true")
@RestController
@RequestMapping("/workflow_bpmnjs")
public class ProcessDesignController {
	@Autowired
	private ProcessDesignService processDesignService;
	@Autowired
	private ModelSaveRestResource modelSaveRestResource;
	@Autowired
	private ModelEditorJsonRestResource modelEditorJsonRestResource;
	/**
	 * 创建模型
	 */
	@RequestMapping(value = "/model/insert", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void createModel(String keyName, String name, String category, String descp) throws UnsupportedEncodingException {
		processDesignService.createModel(keyName, name, category, descp);
	}

	/**
	 * 查询模型
	 */
	@RequestMapping(value = "/model/list", method = RequestMethod.GET)
	public List<Model> listModel() {
		List<Model> listModel = processDesignService.listModel();
		return  listModel;
	}

	/**
	 * 保存模型
	 */
	@RequestMapping(value = "/model/{modelId}/xml/save", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public void saveModelXml(@PathVariable String modelId, @RequestBody MultiValueMap<String, String> values) {
		modelSaveRestResource.saveModelXml(modelId, values);
	}

	/**
	 * 根据生成的ID删除模型
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/deleteModel")
	public void flowDelete(@RequestParam(name = "modelId") String modelId){
		processDesignService.deleteModel(modelId);
	}

	/**
	 * 根据生成的ID获取模型流程编辑器
	 * @param modelId
	 * @return
	 */
	@RequestMapping(value = "/model/{modelId}/xml", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public JSONObject getEditorXml(@PathVariable String modelId) {
		return modelEditorJsonRestResource.getEditorXml(modelId);
	}

	/**
	 * 根据生成的ID部署模型
	 * @param modelId
	 * @return
	 */
	// 下载xml文件方法
	@GetMapping(value = "/model/deploy")
	public String deploy(@RequestParam(name = "modelId") String modelId,HttpServletRequest request,HttpServletResponse response) throws Exception {
//		return processDesignService.deployModel(modelId);
		return processDesignService.deployModel2(modelId, request, response);
	}
	//===================test========================
	@GetMapping(value = "/testmodel/savexml")
	public String testSavemodelXml() throws Exception {
		String modelId = "3fea7fff-d7b0-11ea-88cf-c83dd4689c4d";
		String values = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:flowable=\"http://flowable.org/bpmn\" xmlns:barcode=\"http://flowable.org/bpmn/barcode\" id=\"sample-diagram\" targetNamespace=\"http://www.flowable.org/processdef\" xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd\">\n  <process id=\"Process_1\" isExecutable=\"true\" process_namespace=\"\" process_id=\"Process_1\">\n    <startEvent id=\"StartEvent_0mbhjvm\" name=\"开始\">\n      <outgoing>SequenceFlow_1dqzg2c</outgoing>\n    </startEvent>\n    <userTask id=\"UserTask_one\" flowable:assignee=\"${UserTask_one}\" barcode:job=\"job1\" barcode:role=\"role1\" barcode:people=\"people1\">\n      <incoming>SequenceFlow_1dqzg2c</incoming>\n      <outgoing>SequenceFlow_1w786rh</outgoing>\n    </userTask>\n    <sequenceFlow id=\"SequenceFlow_1dqzg2c\" sourceRef=\"StartEvent_0mbhjvm\" targetRef=\"UserTask_one\" />\n    <userTask id=\"UserTask_two\" flowable:assignee=\"${UserTask_two}\" barcode:job=\"job2\" barcode:role=\"role2\" barcode:people=\"people2\">\n      <incoming>SequenceFlow_1w786rh</incoming>\n      <outgoing>SequenceFlow_1bto6j3</outgoing>\n    </userTask>\n    <sequenceFlow id=\"SequenceFlow_1w786rh\" sourceRef=\"UserTask_one\" targetRef=\"UserTask_two\" />\n    <endEvent id=\"EndEvent_0kame3v\" name=\"完成\">\n      <incoming>SequenceFlow_1bto6j3</incoming>\n    </endEvent>\n    <sequenceFlow id=\"SequenceFlow_1bto6j3\" sourceRef=\"UserTask_two\" targetRef=\"EndEvent_0kame3v\" />\n  </process>\n  <bpmndi:BPMNDiagram id=\"BPMNDiagram_1\">\n    <bpmndi:BPMNPlane id=\"BPMNPlane_1\" bpmnElement=\"Process_1\">\n      <bpmndi:BPMNShape id=\"StartEvent_0mbhjvm_di\" bpmnElement=\"StartEvent_0mbhjvm\">\n        <omgdc:Bounds x=\"292\" y=\"82\" width=\"36\" height=\"36\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"299\" y=\"125\" width=\"22\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNShape id=\"UserTask_1qjzhu4_di\" bpmnElement=\"UserTask_one\">\n        <omgdc:Bounds x=\"380\" y=\"60\" width=\"100\" height=\"80\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"SequenceFlow_1dqzg2c_di\" bpmnElement=\"SequenceFlow_1dqzg2c\">\n        <omgdi:waypoint x=\"328\" y=\"100\" />\n        <omgdi:waypoint x=\"380\" y=\"100\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNShape id=\"UserTask_1bbs1ef_di\" bpmnElement=\"UserTask_two\">\n        <omgdc:Bounds x=\"540\" y=\"60\" width=\"100\" height=\"80\" />\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"SequenceFlow_1w786rh_di\" bpmnElement=\"SequenceFlow_1w786rh\">\n        <omgdi:waypoint x=\"480\" y=\"100\" />\n        <omgdi:waypoint x=\"540\" y=\"100\" />\n      </bpmndi:BPMNEdge>\n      <bpmndi:BPMNShape id=\"EndEvent_0kame3v_di\" bpmnElement=\"EndEvent_0kame3v\">\n        <omgdc:Bounds x=\"702\" y=\"82\" width=\"36\" height=\"36\" />\n        <bpmndi:BPMNLabel>\n          <omgdc:Bounds x=\"709\" y=\"125\" width=\"22\" height=\"14\" />\n        </bpmndi:BPMNLabel>\n      </bpmndi:BPMNShape>\n      <bpmndi:BPMNEdge id=\"SequenceFlow_1bto6j3_di\" bpmnElement=\"SequenceFlow_1bto6j3\">\n        <omgdi:waypoint x=\"640\" y=\"100\" />\n        <omgdi:waypoint x=\"702\" y=\"100\" />\n      </bpmndi:BPMNEdge>\n    </bpmndi:BPMNPlane>\n  </bpmndi:BPMNDiagram>\n</definitions>\n";
		modelSaveRestResource.saveModelXml2(modelId, values);
		return "success";
	}
	@GetMapping(value = "/testmodel/deploy")
	public String testdeploy(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String modelId = "3fea7fff-d7b0-11ea-88cf-c83dd4689c4d";
		return processDesignService.deployModel2(modelId, request, response);
	}
	//===================test========================
}
