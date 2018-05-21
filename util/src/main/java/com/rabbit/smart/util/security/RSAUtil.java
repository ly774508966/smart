package com.rabbit.smart.util.security;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhang on 2017/8/27.
 */
public class RSAUtil {
    public static final String ENCRYPTION_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 生成密钥
     *
     * @return 密钥对
     * @throws Exception 生成异常
     */
    public static Map<String, Object> initKey() throws Exception {
        /* 初始化密钥生成器 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ENCRYPTION_ALGORITHM);
        keyPairGenerator.initialize(1024);

        /* 生成密钥 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put("PublicKey", publicKey);
        keyMap.put("PrivateKey", privateKey);
        return keyMap;
    }

    /**
     * 取得公钥
     *
     * @param keyMap 密钥对
     * @return 公钥
     * @throws Exception 获取异常
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get("PublicKey");
        return Base64Util.encryptBASE64(key.getEncoded());
    }

    /**
     * 取得私钥
     *
     * @param keyMap 密钥对
     * @return 私钥
     * @throws Exception 获取异常
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get("PrivateKey");
        return Base64Util.encryptBASE64(key.getEncoded());
    }

    /**
     * 加密
     *
     * @param data      加密数据
     * @param keyString 加密秘钥
     * @param isPublic  秘钥类型（true公钥，false私钥）
     * @return 加密结果
     * @throws Exception 加密异常
     */
    public static byte[] encrypt(byte[] data, String keyString, boolean isPublic) throws Exception {
        Map<String, Object> keyAndFactoryMap = generateKeyAndFactory(keyString, isPublic);
        KeyFactory keyFactory = getKeyFactory(keyAndFactoryMap);
        Key key = getKey(keyAndFactoryMap);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 解密
     *
     * @param data      解密数据
     * @param keyString 解密秘钥
     * @param isPublic  秘钥类型（true公钥，false私钥）
     * @return 解密结果
     * @throws Exception 解密异常
     */
    public static byte[] decrypt(byte[] data, String keyString, boolean isPublic) throws Exception {
        Map<String, Object> keyAndFactoryMap = generateKeyAndFactory(keyString, isPublic);
        KeyFactory keyFactory = getKeyFactory(keyAndFactoryMap);
        Key key = getKey(keyAndFactoryMap);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    /**
     * 对信息生成数字签名（用私钥）
     *
     * @param data      签名数据
     * @param keyString 私钥
     * @return 签名
     * @throws Exception 签名异常
     */
    public static String sign(byte[] data, String keyString) throws Exception {
        Map<String, Object> keyAndFactoryMap = generateKeyAndFactory(keyString, false);
        Key key = getKey(keyAndFactoryMap);

        PrivateKey privateKey = (PrivateKey) key;

        //用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);

        return Base64Util.encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名（用公钥）
     *
     * @param data      签名数据
     * @param keyString 公钥
     * @param sign      签名
     * @return 签名是否正确
     * @throws Exception 校验异常
     */
    public static boolean verify(byte[] data, String keyString, String sign)
            throws Exception {
        Map<String, Object> keyAndFactoryMap = generateKeyAndFactory(keyString, true);
        Key key = getKey(keyAndFactoryMap);

        PublicKey publicKey = (PublicKey) key;

        // 取公钥匙对象
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(Base64Util.decryptBASE64(sign));
    }

    /**
     * 生成钥匙
     */
    private static Map<String, Object> generateKeyAndFactory(String keyString, boolean isPublic) throws Exception {
        byte[] keyBytes = Base64Util.decryptBASE64(keyString);

        KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPTION_ALGORITHM);
        Key key = null;
        if (isPublic) {
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            key = keyFactory.generatePublic(x509KeySpec);
        } else {
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            key = keyFactory.generatePrivate(pkcs8KeySpec);
        }

        Map<String, Object> keyAndFactoryMap = new HashMap<String, Object>(2);
        keyAndFactoryMap.put("key", key);
        keyAndFactoryMap.put("keyFactory", keyFactory);

        return keyAndFactoryMap;
    }

    /**
     * 从指定对象中获取钥匙
     */
    private static Key getKey(Map<String, Object> map) {
        if (map.get("key") == null) {
            return null;
        }
        return (Key) map.get("key");
    }

    /**
     * 从指定对象中获取钥匙工厂
     */
    private static KeyFactory getKeyFactory(Map<String, Object> map) {
        if (map.get("keyFactory") == null) {
            return null;
        }
        return (KeyFactory) map.get("keyFactory");
    }
}
