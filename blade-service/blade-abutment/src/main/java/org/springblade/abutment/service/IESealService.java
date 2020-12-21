package org.springblade.abutment.service;

import org.springblade.abutment.entity.*;
import org.springblade.abutment.vo.*;

import java.util.List;

/**
 * <p>
 * 电子印章 服务类
 * </p>
 *
 * @Author gym
 * @since 2020-11-26
 */
public interface IESealService {
    /**
     * 获取电子印章接口的token
     * @return
     */
    String getToken() throws Exception;

    /**
     * 获取企业信息
     * @return
     */
    CompanyInfoVo getCompanyInfo(String token, CompanyInfoEntity companyInfoEntity) throws Exception;

    /**
     * 上传文件
     * @return
     */
    List<UploadFileVo> uploadFiles(String token, UploadFileEntity uploadFilesEntity) throws Exception;

    /**
     * 查看合同
     * @return
     */
    String readSigned(String token, ReadSignedEntity readSignedEntity) throws Exception;

    /**
     * 单个合同签章/作废
     * @return
     */
    SingleSignVo singleSign(String token, SingleSignEntity singleSignEntity) throws Exception;

    /**
     * 发送短信验证码
     * @return
     */
    boolean sendSms(String token, SendSmsEntity sendSmsEntity) throws Exception;

    /**
     * 批量合同签章/作废
     * @return
     */
    MultiSignVo multiSign(String token, MultiSignEntity multiSignEntity) throws Exception;
}
