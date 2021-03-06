package org.springblade.bpmnjsdesign.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.io.ByteArrayOutputStream;

/**
 * TODO
 *
 * @author gangzi
 * @date 2020/7/2417:04
 */
@Service
public class ModelSaveRestResource implements ModelDataJsonConstants {
    private static final Logger LOG = LoggerFactory
            .getLogger(ModelSaveRestResource.class);

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 保存模型
     */
    public void saveModelXml(String modelId,MultiValueMap<String, String> values) {
        ByteArrayOutputStream outStream = null;
        try {
            Model model = repositoryService.getModel(modelId);
            /* 获取模型信息*/
            ObjectNode modelJson = (ObjectNode) objectMapper
                    .readTree(model.getMetaInfo());
            modelJson.put(MODEL_NAME, model.getName());
            modelJson.put(MODEL_DESCRIPTION, modelJson.get("description"));
            model.setMetaInfo(modelJson.toString());
            /* 版本 */
            model.setVersion(model.getVersion() + 1);
            repositoryService.saveModel(model);
            String bpmnXml = values.getFirst("bpmn_xml");
			bpmnXml = bpmnXml.replaceAll("&lt;","<");
			bpmnXml = bpmnXml.replaceAll("&gt;",">");
            /*直接保持xml文件*/
            repositoryService.addModelEditorSource(model.getId(), bpmnXml.getBytes("utf-8"));
        } catch (Exception e) {
            LOG.error("Error saving model", e);
			throw new RuntimeException("Error saving model", e);
        }
    }
}
