package org.springblade.abutment.service;

import org.springblade.abutment.vo.EkpSyncInfoVo;
import org.springblade.abutment.vo.EkpSyncRequestVO;

public interface EkpUserDeptService {

	void synchronizationEkpUserData(EkpSyncRequestVO ekpSyncRequestVO);

	EkpSyncInfoVo getEkpSyncInfo(EkpSyncRequestVO ekpSyncRequestVO);
}
