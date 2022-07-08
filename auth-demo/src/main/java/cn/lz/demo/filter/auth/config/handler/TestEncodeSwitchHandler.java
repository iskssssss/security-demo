package cn.lz.demo.filter.auth.config.handler;

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
		final String decrypt = request.getHeader("encrypt");
		if (StringUtil.isEmpty(decrypt)) {
			return false;
		}
		return decrypt.intern() == "true";
	}

	@Override
	public boolean isEncrypt(BaseRequest<?> request, BaseResponse<?> response) {
		if (super.isEncrypt(request, response)) {
			response.setHeader("encrypt", "true");
			return true;
		}
		return false;
	}
}
