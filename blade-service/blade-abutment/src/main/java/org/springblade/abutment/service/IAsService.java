package org.springblade.abutment.service;

import org.springblade.abutment.vo.AsDictVo;
import org.springblade.system.entity.DictBiz;

import java.util.List;

/**
 * 用印单位更新接口
 * @author jitwxs
 * @date 2021/6/5 23:02
 */
public interface IAsService {

	List<DictBiz> submit(AsDictVo dict);
}
