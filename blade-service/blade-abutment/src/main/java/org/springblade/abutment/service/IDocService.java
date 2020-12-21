package org.springblade.abutment.service;

import org.springblade.abutment.entity.DocEntity;
import org.springblade.abutment.vo.DocVo;

import java.util.List;

/**
 * <p>
 * 依据查询 服务类
 * </p>
 *
 * @Author gym
 * @since 2020-11-23
 */
public interface IDocService {
    /**
     * 获取依据接口的token
     * @return
     */
    String getToken() throws Exception;

    /**
     * 获取依据接口的依据数据
     * @return
     */
    List<DocVo> getDocInfo(DocEntity entity) throws Exception;
}
