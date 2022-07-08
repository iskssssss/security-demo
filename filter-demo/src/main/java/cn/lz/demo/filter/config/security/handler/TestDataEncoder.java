package cn.lz.demo.filter.config.security.handler;

import cn.lz.security.defaults.AESDataEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 测试加解密
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/11/10 16:43
 */
//@Component
public class TestDataEncoder extends AESDataEncoder {
    @Override
    public void init(Map<String, String> params) {
        super.init(params);
    }

    @Override
    public byte[] encrypt(byte[] bytes) {
        return super.encrypt(bytes);
    }

    @Override
    public byte[] decrypt(byte[] encryptData) {
        return super.decrypt(encryptData);
    }
}
