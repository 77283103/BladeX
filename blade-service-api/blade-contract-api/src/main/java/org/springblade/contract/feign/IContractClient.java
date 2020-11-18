package org.springblade.contract.feign;

import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.system.feign.ISysClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign接口类
 *
 * @author xhb
 */
@FeignClient(
        value = AppConstant.APPLICATION_CONTRACT_NAME,
        fallback = IContractFallback.class
)
public interface IContractClient {

    String API_PREFIX = "/client";
    String CONTRACT = API_PREFIX + "/contractFormInfo";


    /**
     * 获取合同信息
     * @param id
     * @return
     */
    @GetMapping(CONTRACT)
    R<ContractFormInfoResponseVO> getById(@RequestParam("id") Long id);
}
