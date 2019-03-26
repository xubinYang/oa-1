package edu.xubin.util.WechatService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class WechatJSSDKUtil {

    /**
     * @name 中文名称
     * @description 根据页面URL和页面ticket生成接入JS-SDK接入码
     * @time 创建时间:2018年7月23日19:58:05
     * @param url：接入js-sdk的页面地址
     * 			ticket：通过token生成的接入js-sdk的ticket
     * @return 请求返回接入js-sdk所需json对象
     * @author 朱浩
     * @history 修订历史（历次修订内容、修订人、修订时间等）
     */
    public static Map<String, Object> getSignature(String url, String ticket) {
        Map<String, Object> map = new HashMap<>();

        String noncestr = getRandomString(16);
        String timestamp = (int)(System.currentTimeMillis()/1000)+"";
        String sign = "";
        sign += "jsapi_ticket=" + ticket
                + "&noncestr=" + noncestr
                + "&timestamp=" + timestamp
                + "&url=" + url;
        String signature = "";
        try {
            // 指定sha1算法
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(sign.getBytes());
            // 获取字节数组
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            signature = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        map.put("signature", signature);
        map.put("noncestr", noncestr);
        map.put("timestamp", timestamp);
        return map;
    }


    /**
     * @name 中文名称
     * @description 获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
     * @time 创建时间:2018年7月23日14:17:21
     * @param length
     * @return 对应长度的随机字符串
     * @author 朱浩
     * @history 修订历史（历次修订内容、修订人、修订时间等）
     */
    private static String getRandomString(int length) {
        // 随机字符串的随机字符库
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random()
                    * (len - 1))));
        }
        return sb.toString();
    }


}
