package cn.lz.demo.filter.config.security.handler;

import cn.lz.security.context.model.BaseRequest;
import cn.lz.security.context.model.BaseResponse;
import cn.lz.security.defaults.EncodeSwitchHandlerDefault;
import cn.lz.tool.core.string.StringUtil;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/11/08 10:51
 */
@Component
public class TestEncodeSwitchHandler extends EncodeSwitchHandlerDefault {

	@Override
	public boolean isDecrypt(BaseRequest<?> request, BaseResponse<?> response) {
		//System.out.println("TestEncryptSwitchHandler.decrypt(...)");
		final String decrypt = request.getHeader("encrypt");
		if (StringUtil.isEmpty(decrypt)) {
			return false;
		}
		return decrypt.intern() == "true";
	}

	@Override
	public boolean isEncrypt(BaseRequest<?> request, BaseResponse<?> response) {
		//System.out.println("TestEncryptSwitchHandler.encrypt(...)");
		final String encrypt = request.getHeader("encrypt");
		if (StringUtil.isEmpty(encrypt)) {
			return false;
		}
		return encrypt.intern() == "true";
	}
}
