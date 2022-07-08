//package cn.lz.demo.test;
//
//import cn.lz.sdk.generate.config.GenConfigurer;
//import cn.lz.sdk.generate.config.GenConfigurerAdapter;
//import cn.lz.sdk.generate.config.GenConfigurerBuilder;
//import cn.lz.sdk.generate.handler.IImportClassHandler;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
///**
// * 生成器配置
// *
// * @author 孔胜
// * @version 版权 Copyright(c)2021 杭州设维信息技术有限公司
// * @date 2021/11/15 09:56
// */
//@Configuration
//public class SpringGenConfigurer extends GenConfigurerAdapter {
//
//	@Override
//	public void controllerScanPaths(List<String> controllerScanPathList) {
//		controllerScanPathList.add("cn.lz.demo.filter.controller");
//	}
//
//	@Override
//	public void config(GenConfigurerBuilder<GenConfigurer> genConfigurer) {
//		final GenConfigurer gc = genConfigurer
//				// 项目保存路径
//				.setSavePath("E:\\临时文件\\代码生成测试\\2022年6月23日")
//				// token存放标识
//				.setTokenSaveName("FILTER_TOKEN")
//				// token请求地址
//				.setTokenGetInterfacePath("/auth/genAccessToken")
//				// 项目信息
//				.setProjectGenInfo("cn.lz.sdk.demo", "gen-demo-sdk", "栗子鸡")
//				// 导入类处理器
//				.setImportClassHandler(new IImportClassHandler() {
//					@Override
//					public boolean isCustomClass(Class<?> aClass) {
//						return aClass.getName().startsWith("cn.lz");
//					}
//				}).end();
//	}
//}
