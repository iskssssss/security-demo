package cn.lz.demo.filter.auth.controller;

import cn.lz.demo.filter.auth.model.LoginCriteria;
import cn.lz.demo.filter.auth.model.R;
import cn.lz.security.annotation.DataEncodeSwitch;
import cn.lz.security.auth.LzLogin;
import cn.lz.security.auth.annotation.AnonymousCheck;
import cn.lz.security.auth.annotation.AuthCheck;
import cn.lz.security.log.LzLogger;
import cn.lz.security.log.LzLoggerUtil;
import cn.lz.security.token.AccessTokenUtil;
import cn.lz.tool.json.JsonUtil;
import cn.lz.tool.jwt.model.AuthDetailsDefault;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/11/02 16:41
 */
@RestController
@RequestMapping("/api/auth")
@Api(tags = "登录相关接口")
public class LoginController {
	private final static LzLogger logger = LzLoggerUtil.getLzLogger(LoginController.class);

	@Value()
	@Bean
	@AnonymousCheck
	@ApiOperation(value = "登录", notes = "登录")
	@PostMapping("/login/login.do")
	@DataEncodeSwitch
	public R<String> login(
			@RequestBody LoginCriteria loginCriteria
	) {
		String username = loginCriteria.getUsername();
		String password = loginCriteria.getPassword();
		if (!("admin".equals(username) && "123456".equals(password)) && !"654321".equals(password)) {
			return R.error("登录失败，账号或密码错误。");
		}
		final String token = LzLogin.login(username);
		logger.info("用户信息：" + loginCriteria.toJson());
		return R.success(token);
	}

	@AuthCheck
	@ApiOperation(value = "登出", notes = "登出")
	@PostMapping("/logout/logout.do")
	public R<String> logout() {
		LzLogin.logout();
		return R.success("登出成功");
	}

	@AuthCheck
	@DataEncodeSwitch
	@ApiOperation(value = "获取当前登录用户信息", notes = "获取当前登录用户信息")
	@GetMapping("/userInfo")
	public R<Map> getMessage() {
		AuthDetailsDefault authDetails = ((AuthDetailsDefault) AccessTokenUtil.getAuthDetails());
		String jsonString = JsonUtil.toJsonString(authDetails);
		Map t = JsonUtil.parseObject(jsonString, Map.class);
		t.remove("sourceClassName");
		return R.success(t);
	}
}
