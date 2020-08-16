package com.cloud.tool;


import com.cloud.constant.BaseConstants;
import com.cloud.db.bean.UserBean;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author fjj
 * @date 2020/6/26 17:43
 */
public class ToolAES {

    public static UserBean decryptUserBean(UserBean userBean) {
        try {
            userBean.setUserName(Decrypt(userBean.getUserName(), BaseConstants.AES_KEY));
            userBean.setPassWord(Decrypt(userBean.getPassWord(), BaseConstants.AES_KEY));
            return userBean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBean;
    }

    // 加密
    public static String Encrypt(String src, String key) throws Exception {
        if (src == null) return null;
        if (key.length() != 16) return null;
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return new Base64().encodeToString(encrypted);
    }

    // 解密
    public static String Decrypt(String src, String key) throws Exception {
        if (src == null) return null;
        if (key.length() != 16) return null;
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decode = new Base64().decode(src);
        byte[] bytes = cipher.doFinal(decode);
        return new String(bytes, "utf-8");
    }

    public static void main(String[] args) throws Exception {
        String admin = ToolAES.Encrypt("admin", BaseConstants.AES_KEY);
        System.out.println("加密后的字符串：" + admin);
        admin = ToolAES.Decrypt(admin, BaseConstants.AES_KEY);
        System.out.println("解密后的字符串：" + admin);
        String encrypt = ToolAES.Encrypt("123456", BaseConstants.AES_KEY);
        System.out.println("加密后的字符串：" + encrypt);
        encrypt = ToolAES.Decrypt(encrypt, BaseConstants.AES_KEY);
        System.out.println("解密后的字符串：" + encrypt);
    }
}
