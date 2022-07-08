package cn.lz.demo.test;

import cn.lz.demo.filter.FilterMain;
//import cn.lz.sdk.generate.GenManager;
//import cn.lz.sdk.generate.model.gen.GenInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 生成测试
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 杭州设维信息技术有限公司
 * @date 2021/11/11 15:39
 */
//@Import(SpringGenConfigurer.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FilterMain.class)
public class DemoTest {

	@Test
	public void test() {
		// 生成sdk项目
//		final GenInfo genInfo = GenManager.gen();
//		System.out.println(genInfo.toJson());
	}
}
