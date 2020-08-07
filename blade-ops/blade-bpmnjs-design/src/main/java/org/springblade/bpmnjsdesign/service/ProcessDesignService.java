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
	/**
	 * 创建模板
	 * @param key
	 * @param name
	 * @param category
	 * @param descp
	 * @throws UnsupportedEncodingException
	 */
    void createModel(String key, String name, String category, String descp) throws UnsupportedEncodingException;

	/**
	 * 获取模板列表
	 * @return
	 */
    List<Model> listModel();

	/**
	 * 删除模板
	 * @param modelId
	 */
    void deleteModel(String modelId);

	/**
	 * 部署模板
	 * @param modelId
	 * @return
	 * @throws Exception
	 */
    String deployModel(String modelId) throws Exception;

	/**
	 * 下载模板xml文件
	 * @param modelId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	String downLoadXml(String modelId, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
