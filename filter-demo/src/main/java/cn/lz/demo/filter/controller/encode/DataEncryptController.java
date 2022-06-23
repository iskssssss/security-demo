package cn.lz.demo.filter.controller.encode;

import cn.lz.demo.filter.model.AccessTokenVO;
import cn.lz.demo.filter.model.R;
import cn.lz.security.annotation.DataEncodeSwitch;
import cn.lz.security.annotation.IncludeInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求数据加密、响应数据加密 接口
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/11/05 11:07
 */
@RestController
@IncludeInterface
@DataEncodeSwitch(responseEncrypt = true, requestEncrypt = true)
@RequestMapping("/dataEncrypt")
@Api(tags = "请求数据加密、响应数据加密 接口")
public class DataEncryptController {

	@PostMapping(value = "/postPriDecrypt", produces = "application/json")
	@ApiOperation(value = "测试接口", notes = "测试接口")
	public R<AccessTokenVO> postPriDecrypt(
			@RequestBody AccessTokenVO accessTokenVO
	) {
		return R.success(accessTokenVO);
	}
}
