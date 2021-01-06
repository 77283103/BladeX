package org.springblade.abutment.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.webservice.SoapClient;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.XML;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.service.IOrganizationService;
import org.springblade.abutment.vo.OrganizationVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeaderElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织及人员信息 服务实现类
 * </p>
 *
 * @Author: gym
 * @Date: 2018-12-20
 */
@Service
@Slf4j
public class OrganizationServiceImpl implements IOrganizationService {
    @Value("${api.organization.account}")
    private String account;
    @Value("${api.organization.password}")
    private String password;
    @Value("${api.organization.wsdl}")
    private String wsdl;
    @Value("${api.organization.method}")
    private String method;
    @Value("${api.organization.namespace}")
    private String namespace;

    @Override
    public List<OrganizationVo> getOrganizationInfo(OrganizationEntity entity) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        List<Map<String, String>> fieldsList = new ArrayList<Map<String, String>>();
        if(entity.getFieldsName() != null && entity.getFieldsName().size() > 1) {
            for(String fieldName : entity.getFieldsName()) {
                fieldsList.add(this.getFieldMap(fieldName));
            }
        }else {
            fieldsList.add(this.getFieldMap("id"));
            fieldsList.add(this.getFieldMap("email"));
            fieldsList.add(this.getFieldMap("loginName"));
            fieldsList.add(this.getFieldMap("emplno"));
            fieldsList.add(this.getFieldMap("name"));
            fieldsList.add(this.getFieldMap("namePinyin"));
            fieldsList.add(this.getFieldMap("isAvailable"));
            fieldsList.add(this.getFieldMap("orgType"));
            fieldsList.add(this.getFieldMap("parentid"));
            fieldsList.add(this.getFieldMap("alterTime"));
        }
        paramMap.put("select",fieldsList);
        List<Map<String, String>> whereList = new ArrayList<Map<String, String>>();
        if(StrUtil.isNotEmpty(entity.getId())) {
            whereList.add(this.getWhereMap("id", entity.getId()));
        }
        if(StrUtil.isNotEmpty(entity.getEmail())) {
            whereList.add(this.getWhereMap("email", entity.getEmail()));
        }
        if(StrUtil.isNotEmpty(entity.getLoginName())) {
            whereList.add(this.getWhereMap("loginName", entity.getLoginName()));
        }
        if(StrUtil.isNotEmpty(entity.getEmplno())) {
            whereList.add(this.getWhereMap("emplno", entity.getEmplno()));
        }
        if(StrUtil.isNotEmpty(entity.getName())) {
            whereList.add(this.getWhereMap("name", entity.getName()));
        }
        if(StrUtil.isNotEmpty(entity.getNamePinyin())) {
            whereList.add(this.getWhereMap("namePinyin", entity.getNamePinyin()));
        }
        if(StrUtil.isNotEmpty(entity.getIsAvailable())) {
            whereList.add(this.getWhereMap("isAvailable", entity.getIsAvailable()));
        }
        entity.setOrgType("2");
        if(StrUtil.isNotEmpty(entity.getOrgType())) {
            whereList.add(this.getWhereMap("orgType", entity.getOrgType()));
        }
        if(StrUtil.isNotEmpty(entity.getParentid())) {
            whereList.add(this.getWhereMap("parentid", entity.getParentid()));
        }
        if(StrUtil.isNotEmpty(entity.getAlterTime())) {
            whereList.add(this.getWhereMap("alterTime", entity.getAlterTime()));
        }
        paramMap.put("where",whereList);
        SoapClient client = SoapClient.create(this.wsdl).setMethod(method).setParam("arg0", JSONUtil.toJsonStr(paramMap));
        SOAPHeaderElement element = client.addSOAPHeader(new QName(this.namespace,"RequestSOAPHeader","tns"));
        element.addChildElement("user","tns").addTextNode(this.account);
        element.addChildElement("password","tns").addTextNode(this.password);
        String mesStr = client.send();
        log.info(mesStr);
        JSONObject message =  XML.toJSONObject(mesStr).getJSONObject("soap:Envelope").getJSONObject("soap:Body").getJSONObject("ns1:updateOrganizationResponse");
        JSONObject organizationInfoJson = message != null ? message.getJSONObject("return") : null;
        return organizationInfoJson == null ? null : organizationInfoJson.getStr("status").equals("succeed") ? JSON.parseArray(organizationInfoJson.get("data", List.class).toString() , OrganizationVo.class): null;
    }

    private Map<String, String> getWhereMap(String key, String value) throws Exception {
        Map<String, String> whereMap = new HashMap<String, String>();
        whereMap.put("type",key+"='"+value+"'");
        return whereMap;
    }

    private Map<String, String> getFieldMap(String fieldName) throws Exception {
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("type",fieldName);
        return fieldMap;
    }
}
