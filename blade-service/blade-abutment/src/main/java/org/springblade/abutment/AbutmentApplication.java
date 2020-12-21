package org.springblade.abutment;

import org.springblade.core.cloud.feign.EnableBladeFeign;
import org.springblade.core.launch.BladeApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableBladeFeign
@SpringCloudApplication
public class AbutmentApplication {

	public static void main(String[] args) {
		BladeApplication.run("blade-abutment", AbutmentApplication.class, args);
	}

}
