package org.springblade.contract;
import org.springblade.core.cloud.feign.EnableBladeFeign;
import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;
/**
 *  <类说明>   ： Contract启动器
 * @author    : Mr.Feng
 * @date      : 2020-7-14 16:36
 */
@EnableBladeFeign
@SpringCloudApplication
public class ContractApplication {
	public static void main(String[] args) {
		BladeApplication.run(AppConstant.APPLICATION_CONTRACT_NAME, ContractApplication.class, args);
	}
}

