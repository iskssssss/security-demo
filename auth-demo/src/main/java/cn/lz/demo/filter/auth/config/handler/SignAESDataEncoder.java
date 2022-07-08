package cn.lz.demo.filter.auth.config.handler;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.*;
import cn.lz.security.defaults.encoder.AESDataEncoder;
import cn.lz.security.handler.DataEncoder;
import cn.lz.tool.core.bytes.ByteUtil;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * TODO
 *
 * @author 孔胜
 * @version 版权 Copyright(c)2022 杭州设维信息技术有限公司
 * @date 2022/7/6 9:58
 */
public class SignAESDataEncoder extends AESDataEncoder implements DataEncoder {
    private Sign sign;

    @Override
    public void init(Map<String, String> params) {
        String publicKey = params.get("publicKey");
        String privateKey = params.get("privateKey");
        RSA rsa = new RSA(privateKey, publicKey);
        sign = SecureUtil.sign(SignAlgorithm.MD2withRSA);
        sign.init(AsymmetricAlgorithm.RSA.getValue(), rsa.getPrivateKey(), rsa.getPublicKey());
        super.init(params);
    }

    @Override
    public byte[] encrypt(byte[] bytes) {
        byte[] encrypt = super.encrypt(bytes);
        String encryptStr = ByteUtil.toObject(encrypt, String.class);
        String sign = this.sign.signHex(encrypt);
        return (sign + "." + encryptStr).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public byte[] decrypt(byte[] bytes) {
        return super.decrypt(bytes);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();

        Sign sign = SecureUtil.sign(SignAlgorithm.MD2withRSA);
        sign.init(AsymmetricAlgorithm.RSA.getValue(), rsa.getPrivateKey(), rsa.getPublicKey());
        String signHex = sign.signHex("123");
        System.out.println(signHex);
        boolean verify = sign.verify("1234".getBytes(StandardCharsets.UTF_8), HexUtil.decodeHex(signHex));
        System.out.println(verify);

        String encrypt2 = rsa.encryptBase64("123", KeyType.PrivateKey);
        System.out.println("私钥加密：" + encrypt2);
        System.out.println("公钥解密：" + rsa.decryptStr(encrypt2, KeyType.PublicKey));
        //System.out.println("私钥解密：" + rsa.decryptStr(encrypt2, KeyType.PrivateKey));

        String encrypt = rsa.encryptBase64("123", KeyType.PublicKey);
        System.out.println("公钥加密：" + encrypt);
        System.out.println("私钥解密：" + rsa.decryptStr(encrypt, KeyType.PrivateKey));
        //System.out.println("公钥解密：" + rsa.decryptStr(encrypt, KeyType.PublicKey));
    }
}
