package org.springblade.abutment.service;

import org.springblade.abutment.entity.OrganizationEntity;
import org.springblade.abutment.vo.OrganizationVo;

import java.util.List;

/**
 * <p>
 * 组织及人员信息 服务类
 * </p>
 *
 * @Author gym
 * @since 2020-11-23
 */
public interface IOrganizationService {
    /**
     * 获取组织及人员信息数据
     * @return
     */
    List<OrganizationVo> getOrganizationInfo(OrganizationEntity entity) throws Exception;
	/**
	 * 增量更新组织及人员信息数据
	 */
	List<OrganizationVo> getOrganizationInfoIncrement();
}
