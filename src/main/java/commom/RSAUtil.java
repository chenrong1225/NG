package commom;





import android.util.Base64;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


//import android.util.Log;

public class RSAUtil {

    private static String KEY_ALGORITHM = "RSA";
    private static String CHARSET_NAME = "utf-8";

    public static final String PUBLIC_KEY_STRING = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4PmaL1qzKXjgwLPx8w1vbOuyQp6gxDqRaayVir+vuobqLJnQIGvVZz3EsIXMOzYCZyg8B9+mzC773pcwwgshNRK6mxt3UcsOv2TbB7ey7gJYWmc/Fe3hTwBLmXxofeesEIby1+QTexcpNMmedPw109UPA15i88UyZWzpPjP2gIwIDAQAB";

    public static final String PRIVATE_KEY_STRING = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALg+ZovWrMpeODAs/HzDW9s67JCnqDEOpFprJWKv6+6huosmdAga9VnPcSwhcw7NgJnKDwH36bMLvvelzDCCyE1ErqbG3dRyw6/ZNsHt7LuAlhaZz8V7eFPAEuZfGh956wQhvLX5BN7Fyk0yZ50/DXT1Q8DXmLzxTJlbOk+M/aAjAgMBAAECgYBre/PlFqsdMBRFfqkZu/qljd2ekkCbxff5y9I2nXzdbTpfnWJJrDiAWXWjkiwGcbrZfTh+JHwQ+QRCg1X44wMomnGXL4d7op9Rd1wyNy0ansC7oy9k1spLe29APegpGRsVNif6P00oIb9iOQSC4+VZ7ppZeSIM2UvEpxbd/N4IiQJBAOuPJBVlTB8S4geSvvh2A62MvSDeq6LmHEZfuprHJebzf4USYX6+yTpO/yE2nVpQBr30GagPEmehQqQZri5U9p0CQQDIO06lZoozdtFgY/FCZyNpyHiumgPxXWDgk2NjQmH5FvxwK6t3qOJgVJ+jpLd+9l9mCgXoYoQaBQW+gLNzPdW/AkBbLSmjWnUvZei1U7i1hUCBMYOXMHkw8W6qUq1w8XqbPU7znBy+qWxMNH2ORox7g5RIfC5eHY9bK1EXIZWI+3wFAkEAgMKisHpgJPgjbHjYjHsGqt/fu2gPYQdLZR9iqlHl7uh5LSjn+ur/qDLC9djWpy6H6nZHlkjD6neQWhEpVNJOwQJAR9aBo4uQfg1SQzyCID3dXhEHRukIcXXjOCI4Dycpmz+p/GL1GRqqSBgQR9kRnHvtH2Qd29/m543jkZEsV0Bw9g==";


    public String contest(HashMap<String, Object> map) {
    	String test="";

    	for (Map.Entry<String, Object> entry : map.entrySet()) {
    		test+=entry.getKey()+"=";
			test+=entry.getValue()+"&";
		}
    	test = test.substring(0,test.length() - 1);
    	System.out.println(test);
    	return test ;
    }


    /**
     * 本地生成密钥demo
     */
    public static void initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        //Log.e("rsaPublicKey", getKeyString(rsaPublicKey));
       // Log.e("rsaPrivateKey", getKeyString(rsaPrivateKey));
    }


    /**
     * 根据key获取字符串
     */
    public static String getKeyString(Key key) throws Exception {
        return encryptBASE64(key.getEncoded());
    }


    /**
     * 将base64编码后的公钥字符串转成PublicKey实例
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = decode(publicKey.getBytes(), Base64.NO_WRAP);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将base64编码后的私钥字符串转成PrivateKey实例
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = decode(privateKey.getBytes(), Base64.NO_WRAP);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }


    /**
     * 编码返回字符串
     */
    private static String encryptBASE64(byte[] key) throws Exception {
        return Base64.encodeToString(key, Base64.NO_WRAP);
    }


    /**
     * 使用RSA签名demo
     */
    public static String signWithRSA(String content) throws Exception {
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initSign(getPrivateKey(PRIVATE_KEY_STRING));
        signature.update(content.getBytes(CHARSET_NAME));
        byte[] signed = signature.sign();
        return encryptBASE64(signed);
    }

    /**
     * 使用RSA验签demo
     */
    public static boolean checkSignWithRSA(String content, String sign) throws Exception {
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initVerify(getPublicKey(PUBLIC_KEY_STRING));
        signature.update(content.getBytes(CHARSET_NAME));
        return signature.verify(decode(sign, Base64.NO_WRAP));
    }





}
