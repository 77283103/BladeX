package org.springblade.cases;

import org.springblade.core.cloud.feign.EnableBladeFeign;
import org.springblade.core.launch.BladeApplication;
import org.springblade.core.launch.constant.AppConstant;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author xhbbo
 */
@EnableBladeFeign
@SpringCloudApplication
public class CaseApplication {
    public static void main(String[] args) {
        BladeApplication.run(AppConstant.APPLICATION_CASE_NAME, CaseApplication.class, args);
    }
}
