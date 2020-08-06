package org.springblade.bpmnjsdesign.service;


import org.flowable.engine.repository.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	String deployModel2(String modelId, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
