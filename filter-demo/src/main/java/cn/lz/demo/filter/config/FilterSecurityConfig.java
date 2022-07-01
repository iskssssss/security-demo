package cn.lz.demo.filter.config;

import cn.lz.demo.filter.config.security.filter.TestInterfacesFilter;
import cn.lz.security.LzCoreManager;
import cn.lz.security.arrays.UrlHashSet;
import cn.lz.security.config.CoreConfigurer;
import cn.lz.security.config.CoreConfigurerBuilder;
import cn.lz.security.filter.config.FilterConfigurer;
import cn.lz.security.filter.config.FilterConfigurerBuilder;
import cn.lz.security.filter.config.SecurityFilterConfigurerAdapter;
import cn.lz.security.filter.utils.FilterUtil;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedList;

/**
 * @Version 版权 Copyright(c)2021 LZ
 * @ClassName:
 * @Descripton:
 * @Author: 孔胜
 * @Date: 2021/09/16 14:00
 */
@Configuration
public class FilterSecurityConfig extends SecurityFilterConfigurerAdapter {

	@Resource
	TestInterfacesFilter testInterfacesFilter;

	@Override
	protected void config(CoreConfigurerBuilder<CoreConfigurer> coreConfigurer) {
	}

	@Override
	protected void filter(FilterConfigurerBuilder<FilterConfigurer> filterConfigurer) {
		System.out.println(LzCoreManager.getLzConfig().print());
		filterConfigurer
				.filterConfig()
				.linkInterfacesFilter(
						FilterUtil.accessTokenFilter(),
						FilterUtil.ipFilter(params -> {
							final LinkedList<String> ipList = new LinkedList<>();
							ipList.add("*");
							return ipList;
						}),
						FilterUtil.requestInterfaceFilter(params -> {
							UrlHashSet urlHashSet = new UrlHashSet();
							urlHashSet.add("/**");
							return urlHashSet;
						}),
						testInterfacesFilter
				).setLogBeforeFilterClass(FilterUtil.RequestInterfaceFilter.class);
	}
}
