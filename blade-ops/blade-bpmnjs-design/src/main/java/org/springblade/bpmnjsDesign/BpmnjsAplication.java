package org.springblade.bpmnjsDesign;

import org.springblade.core.launch.BladeApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO
 *
 * @author gangzi
 * @date 2020/8/315:28
 */
@SpringBootApplication
public class BpmnjsAplication {
	public static void main(String[] args) {
		BladeApplication.run("blade-bpmnjs-design", BpmnjsAplication.class, args);
	}
}
