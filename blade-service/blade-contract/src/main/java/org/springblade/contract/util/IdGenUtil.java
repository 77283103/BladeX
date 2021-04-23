package org.springblade.contract.util;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

public class IdGenUtil {

	private static IdentifierGenerator identifierGenerator = new DefaultIdentifierGenerator();

	public static Long generateId(){
		return identifierGenerator.nextId(new Object()).longValue();
	}

}
