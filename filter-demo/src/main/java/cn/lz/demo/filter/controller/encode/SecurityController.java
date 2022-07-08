package cn.lz.demo.filter.controller.encode;

import cn.lz.demo.filter.model.R;
import cn.lz.security.LzCoreManager;
import cn.lz.security.annotation.DataEncodeSwitch;
import cn.lz.security.annotation.ExcludeInterface;
import cn.lz.security.handler.DataEncoder;
import cn.lz.tool.core.bytes.ByteUtil;
import cn.lz.tool.encrypt.model.SwPublicKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * 加解密相关接口
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2021 LZ
 * @date 2021/11/05 10:48
 */
@RestController
@RequestMapping("/security")
@Api(tags = "加解密相关接口")
public class SecurityController {

    @GetMapping("/publicKey/decrypt")
    @DataEncodeSwitch(responseEncrypt = false)
    @ApiOperation(value = "解密", notes = "解密")
    @ApiImplicitParam(paramType = "query", dataType = "String", name = "encryptText", value = "密文", required = true)
    public R<String> decrypt(
            @RequestParam("encryptText") String encryptText
    ) {
        DataEncoder dataEncryptHandler = LzCoreManager.getDataEncryptHandler();
        final byte[] decrypt = dataEncryptHandler.decrypt(encryptText.getBytes(StandardCharsets.UTF_8));
        return R.success(ByteUtil.toObject(decrypt).toString());
    }

    @ExcludeInterface
    @GetMapping("/publicKey/encrypt")
    @DataEncodeSwitch(responseEncrypt = false)
    @ApiOperation(value = "加密", notes = "加密")
    @ApiImplicitParam(paramType = "query", dataType = "String", name = "decryptText", value = "铭文", required = true)
    public R<Object> encrypt(
            @RequestParam("decryptText") String decryptText
    ) {
        DataEncoder dataEncryptHandler = LzCoreManager.getDataEncryptHandler();
        final byte[] decrypt = dataEncryptHandler.encrypt(decryptText.getBytes(StandardCharsets.UTF_8));
        return R.success(ByteUtil.toObject(decrypt).toString());
    }
}
