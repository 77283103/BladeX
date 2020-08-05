package org.springblade.bpmnjsDesign.controller;

import com.alibaba.fastjson.JSONObject;
import org.springblade.bpmnjsDesign.service.ModelEditorJsonRestResource;
import org.springblade.bpmnjsDesign.service.ModelSaveRestResource;
import org.springblade.bpmnjsDesign.service.ProcessDesignService;
import org.flowable.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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
//	public void createModel(@RequestParam String keyName, @RequestParam String name, @RequestParam String category, @RequestParam String descp) throws UnsupportedEncodingException {
	public void createModel(String keyName, String name, String category, String descp) throws UnsupportedEncodingException {
		processDesignService.createModel(keyName, name, category, descp);
	}
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
	@GetMapping(value = "/model/deploy")
	public String deploy(@RequestParam(name = "modelId") String modelId) throws Exception {
		return processDesignService.deployModel(modelId);
	}


}
