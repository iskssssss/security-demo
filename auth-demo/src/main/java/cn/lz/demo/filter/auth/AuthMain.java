package cn.lz.demo.filter.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/11/02 15:52
 */
@SpringBootApplication
@ComponentScan("cn.lz.**")
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ControllerAdvice.class)})
public class AuthMain extends SpringBootServletInitializer {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AuthMain.class, args);
		context.start();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AuthMain.class);
	}

}
