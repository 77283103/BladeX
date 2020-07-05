package org.springblade.bpmnjsdesign;

import org.springblade.core.launch.BladeApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * TODO
 *
 * @author gangzi
 * @date 2020/8/315:28
 */
//@EnableBladeFeign
@SpringCloudApplication
//@SpringBootApplication
public class BpmnjsAplication {
	public static void main(String[] args) {
		BladeApplication.run("blade-bpmnjs-design", BpmnjsAplication.class, args);
	}
}
