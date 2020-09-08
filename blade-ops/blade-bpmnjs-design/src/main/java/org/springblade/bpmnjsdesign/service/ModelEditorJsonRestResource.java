package org.springblade.bpmnjsdesign.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author crh
 */
@Service
public class ModelEditorJsonRestResource implements ModelDataJsonConstants {
    private static final Logger LOG = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 根据生成的ID获取模型流程编辑器
     * @param modelId
     * @return
     */
    public JSONObject getEditorXml(@PathVariable String modelId) {
        JSONObject jsonObject = null;
        Model model = repositoryService.getModel(modelId);
        if (model != null) {
            try {
                if (StringUtils.isNotEmpty(model.getMetaInfo())) {
                    jsonObject = JSON.parseObject(model.getMetaInfo());
                } else {
                    jsonObject = new JSONObject();
                    jsonObject.put(MODEL_NAME, model.getName());
                }
                jsonObject.put(MODEL_ID, model.getId());
                String bpmnXml = "";
                byte[] modelEditorSource = repositoryService.getModelEditorSource(model.getId());
                if(modelEditorSource != null && modelEditorSource.length > 0){
                    bpmnXml = new String(modelEditorSource);
                }
                jsonObject.put("bpmnXml", bpmnXml);
            } catch (Exception e) {
                LOG.error("创建model的json串失败", e);
                throw new RuntimeException("无法读取model信息", e);
            }
        } else {
            LOG.error("创建model的json串失败[{}]", modelId);
            throw new RuntimeException("未找到对应模型信息");
        }
        return jsonObject;
    }
}
