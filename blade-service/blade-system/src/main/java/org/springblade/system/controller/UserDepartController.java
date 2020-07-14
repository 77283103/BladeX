package org.springblade.system.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.service.IUserDepartService;

import java.util.ArrayList;
import java.util.List;


/**
 *  控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/depart")
@Api(value = "身份", tags = "身份")
public class UserDepartController extends BladeController {

	private final IUserDepartService userDepartService;

}
