package cn.lz.demo.filter.auth.config.handler;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.lz.security.handler.DataEncoder;
import cn.lz.security.log.LzLogger;
import cn.lz.security.log.LzLoggerUtil;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2023 杭州设维信息技术有限公司
 * @date 2023/1/13 16:57
 */
@Component
public class AESDataEncoder implements DataEncoder {
    private final LzLogger logger = LzLoggerUtil.getLzLogger(cn.lz.security.defaults.encoder.AESDataEncoder.class);
    private AES aes;

    @Override
    public void init(Map<String, String> params) {
        String key = params.get("key");
        String iv = params.get("iv");
        aes = new cn.hutool.crypto.symmetric.AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8));
        aes.setIv(iv.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public byte[] encrypt(byte[] bytes) {
        logger.info("响应数据加密");
        final String encryptResult = aes.encryptBase64(bytes);
        return encryptResult.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] decrypt(byte[] encryptData) {
        logger.info("请求数据解密");
        final String encryptResult = aes.decryptStr(Base64.decode(encryptData));
        System.out.println(encryptResult);
        return encryptResult.getBytes(StandardCharsets.UTF_8);
    }
}
