package cn.lz.demo.filter.auth.config;

import cn.lz.demo.filter.auth.config.handler.TestEncodeSwitchHandler;
import cn.lz.security.auth.config.AuthConfigurer;
import cn.lz.security.auth.config.AuthConfigurerBuilder;
import cn.lz.security.auth.config.SecurityAuthConfigurerAdapter;
import cn.lz.security.config.CoreConfigurer;
import cn.lz.security.config.CoreConfigurerBuilder;
import cn.lz.security.context.model.BaseRequest;
import cn.lz.security.filter.config.FilterConfigurer;
import cn.lz.security.filter.config.FilterConfigurerBuilder;
import cn.lz.security.fun.LzFilterAuthStrategy;
import cn.lz.security.log.LzLoggerUtil;
import cn.lz.security.tool.context.LzSpringBootContextManager;
import cn.lz.security.tool.context.SpringBootStorage;
import cn.lz.security.tool.mode.LzRequest;
import cn.lz.security.tool.mode.LzResponse;
import cn.lz.tool.http.model.UserAgentInfo;
import cn.lz.tool.jwt.model.AuthDetailsDefault;
import cn.lz.tool.reflect.model.ControllerMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/11/02 16:04
 */
@Configuration
public class AuthSecurityConfig extends SecurityAuthConfigurerAdapter {

	private final TestEncodeSwitchHandler testEncodeSwitchHandler;

	@Autowired
	public AuthSecurityConfig(TestEncodeSwitchHandler testEncodeSwitchHandler) {
		this.testEncodeSwitchHandler = testEncodeSwitchHandler;
	}

	@Override
	protected void config(CoreConfigurerBuilder<CoreConfigurer> coreConfigurer) {
		coreConfigurer.setEncryptSwitchHandler(testEncodeSwitchHandler);
		LzLoggerUtil.info(AuthSecurityConfig.class, "基础信息配置");
	}

	@Override
	protected void auth(AuthConfigurerBuilder<AuthConfigurer> authConfigurer) {
		authConfigurer
				.userDetailsService(userId -> {
					LzLoggerUtil.info(AuthSecurityConfig.class, "用户信息获取");
					AuthDetailsDefault defaultAuthDetails = new AuthDetailsDefault();
					defaultAuthDetails.setId(userId);
					defaultAuthDetails.setId("admin");
					if ("admin".equals(userId)) {
						defaultAuthDetails.setIdentifier("张三");
						return defaultAuthDetails;
					}
					defaultAuthDetails.setIdentifier("李四");
					return defaultAuthDetails;
				});
		LzLoggerUtil.info(AuthSecurityConfig.class, "认证信息配置");
	}

	@Override
	protected void filter(FilterConfigurerBuilder<FilterConfigurer>.FilterUrl filterConfigurer) {
		LzLoggerUtil.info(AuthSecurityConfig.class, "过滤信息配置");
		filterConfigurer
				.addExcludeUrls(
						"/favicon.ico", "/webjars/**", "/doc.html",
						"/swagger-resources", "/v2/api-docs", "/v2/api-docs-ext"
				)
				.addIncludeUrls("/**")
				.and()
				.setFilterBeforeHandler(new LzFilterAuthStrategy() {
					@Override
					public void run(Object... params) {
						LzRequest lzRequest = LzSpringBootContextManager.getRequest();
						ControllerMethod controllerMethod = lzRequest.getControllerMethod();
						Method method = controllerMethod.getMethod();
						LzLoggerUtil.info(getClass(), "请求接口：" + lzRequest.getRequestPath());
						LzLoggerUtil.info(getClass(), "请求方法：" + method.getName());

						SpringBootStorage storage = LzSpringBootContextManager.getStorage();
						UserAgentInfo userAgentInfo = storage.getUserAgentInfo();
						LzLoggerUtil.info(getClass(), "客户端信息：" + userAgentInfo.toJson());
					}
				})
				.setFilterAfterHandler(new LzFilterAuthStrategy() {
					@Override
					public void run(Object... params) {
						SpringBootStorage storage = LzSpringBootContextManager.getStorage();
						LzLoggerUtil.info(getClass(), "本次请求总时间：" + storage.getRequestTime().toString());
					}
				});
	}

	@Override
	protected void initCorsInfo(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowCredentials(true)
				.allowedOrigins("http://192.168.1.173:8085")
				.allowedMethods("GET", "POST", "DELETE", "OPTIONS")
				.allowedHeaders("Origin", "X-Requested-With", "content-Type", "Accept", "Authorization")
				.exposedHeaders("Origin", "X-Requested-With", "content-Type", "Accept", "Authorization")
				.maxAge(3600);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("doc.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}
