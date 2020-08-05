package org.springblade.bpmnjsDesign.service;


import org.flowable.engine.repository.Model;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author yiyoung
 * @date 2020/4/21
 */
public interface ProcessDesignService {

    void createModel(String key, String name, String category, String descp) throws UnsupportedEncodingException;

    List<Model> listModel();

    void deleteModel(String modelId);

    String deployModel(String modelId) throws Exception;
}
