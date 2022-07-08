package cn.lz.demo.filter.auth.config.handler;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.lz.security.LzCoreManager;
import cn.lz.security.auth.login.CaptchaHandler;
import cn.lz.security.auth.model.CaptchaModel;
import cn.lz.security.context.LzContext;
import cn.lz.security.exception.auth.CaptchaErrorException;
import cn.lz.tool.core.ObjectUtil;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试验证码处理器
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
 * @date 2022/7/6 11:27
 */
public class TestCaptchaHandler implements CaptchaHandler {
    private static final Map<String, String> captchaMap = new ConcurrentHashMap<>();

    @Override
    public void check(Object key, Object value) throws SecurityException {
        String s = captchaMap.get(key.toString());
        if (!ObjectUtil.equals(s, value)) {
            throw new CaptchaErrorException();
        }
    }

    @Override
    public CaptchaModel create(String key, String code) {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(200, 100);
        Image image = circleCaptcha.createImage(code);
        CaptchaModel captchaModel = new CaptchaModel();
        captchaModel.setKey(key);
        captchaModel.setValue(image);
        captchaMap.put(key, code);
        return captchaModel;
    }
}
