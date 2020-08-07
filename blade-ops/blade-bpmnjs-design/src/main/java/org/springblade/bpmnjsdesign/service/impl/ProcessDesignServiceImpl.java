package org.springblade.bpmnjsdesign.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.editor.constants.ModelDataJsonConstants;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.Model;
import org.springblade.bpmnjsdesign.service.ProcessDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author yiyoung
 * @date 2020/4/21
 */
@Slf4j
@Service
public class ProcessDesignServiceImpl implements ProcessDesignService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 保存模型
     * @param key
     * @param name
     * @param category
     * @param descp
     * @throws UnsupportedEncodingException
     */
    @Override
    public void createModel(String key,String name, String category, String descp) throws UnsupportedEncodingException{
        /*初始化一个空模型*/
        Model model = repositoryService.newModel();
        /*设置一些默认信息*/
        String modelName = name;
        String description = descp;
        int revision = 1;
        String modelKey = key;

        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME,modelName);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(modelName);
        model.setKey(modelKey);
        model.setMetaInfo(modelNode.toString());

        repositoryService.saveModel(model);
        return;
    }

    /**
     * 查询模型
     * @return
     */
    @Override
    public List<Model> listModel() {
        return repositoryService.createModelQuery().list();
    }

    /**
     * 删除模型
     * @param modelId
     */
    @Override
    public void deleteModel(String modelId) {
        repositoryService.deleteModel(modelId);
    }

    private static BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
    /**
     * 部署流程
     * @param modelId
     */
    @Override
    public String deployModel(String modelId) throws Exception {
        /*获取模型*/
		Model modelData = repositoryService.getModel(modelId);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if(null == bytes) {
            return "模型数据为空，请先设计流程并成功保存，再进行发布。";
        }
        /*发布流程*/
        String xmlString = new String(bytes, "UTF-8");
        xmlString = xmlString.replaceAll("process_id","barcode:pri");
        xmlString = xmlString.replaceAll("process_namespace","barcode:prn");
        xmlString = xmlString.replaceAll("multiinstance_type","barcode:mut");
        xmlString = xmlString.replaceAll("multiinstance_condition","barcode:muc");
        String processName = modelData.getName() + ".bpmn20.xml";

        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
				.addString(processName,xmlString)
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);

        return "success";
    }

    @Override
	/* 实现xml下载*/
	public String downLoadXml(String modelId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* 获取模型*/
        Model modelData = repositoryService.getModel(modelId);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if(null == bytes) {
            return "模型数据为空，请先设计流程并成功保存，再进行下载。";
        }
        String xmlString = new String(bytes, "UTF-8");
        xmlString = xmlString.replaceAll("process_id","barcode:pri");
        xmlString = xmlString.replaceAll("process_namespace","barcode:prn");
        xmlString = xmlString.replaceAll("multiinstance_type","barcode:mut");
        xmlString = xmlString.replaceAll("multiinstance_condition","barcode:muc");
		String processName = modelData.getName() + ".bpmn20.xml";
		String filename = processName;
		/* 解决文件名乱码问题，获取浏览器类型，转换对应文件名编码格式，IE要求文件名必须是utf-8,
		firefox要求是iso-8859-1编码
		 */
		String agent = request.getHeader("user-agent");
		String browserType = "FireFox";
		if (agent.contains(browserType)) {
			filename = new String(filename.getBytes("UTF-8"), "iso-8859-1");
		} else {
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		/* 设置下载文件的mineType，告诉浏览器下载文件类型 */
		response.setContentType("text/xml");
		/* 设置一个响应头，无论是否被浏览器解析，都下载*/
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		/* 将要下载的文件内容通过输出流写到浏览器 */
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(xmlString.getBytes());
		outputStream.flush();
		return "";
    }
}
