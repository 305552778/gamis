package com.xi.gamis.infrastructure;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESUtil {
    private static String key = "1234123412ABCDEF";//偏移量字符串必须是16位 当模式是CBC的时候必须设置偏移量
    private static String iv = "ABCDEF1234123412";//偏移量字符串必须是16位 当模式是CBC的时候必须设置偏移量
    private static String Algorithm = "AES";
    private static String AlgorithmProvider = "AES/CBC/PKCS5Padding"; //算法/模式/补码方式

    public static byte[] generatorKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(Algorithm);
        keyGenerator.init(256);//默认128，获得无政策权限后可为192或256
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static IvParameterSpec getIv() throws UnsupportedEncodingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("utf-8"));
        System.out.println("偏移量："+byteToHexString(ivParameterSpec.getIV()));
        return ivParameterSpec;
    }

    public static byte[] Encrypt(String src) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        byte key[] =AESUtil.key.getBytes("utf-8");
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] cipherBytes = cipher.doFinal(src.getBytes(Charset.forName("utf-8")));
        return cipherBytes;
    }

    public static byte[] Decrypt(String src) throws Exception {
        byte key[] =AESUtil.key.getBytes("utf-8");
        SecretKey secretKey = new SecretKeySpec(key, Algorithm);
        IvParameterSpec ivParameterSpec = getIv();
        Cipher cipher = Cipher.getInstance(AlgorithmProvider);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] hexBytes = hexStringToBytes(src);
        byte[] plainBytes = cipher.doFinal(hexBytes);
        return plainBytes;
    }

    /**
     * 将byte转换为16进制字符串
     * @param src
     * @return
     */
    public static String byteToHexString(byte[] src) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xff;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                sb.append("0");
            }
            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串装换为byte数组
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] b = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return b;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
        //return (byte) AESUtil.iv.indexOf(c);
    }

    public static void main(String[] args) {
        try {
            // byte key[] = generatorKey();
            // 密钥必须是16的倍数
            //byte key[] = "1234567890ABCDEF1234567890ABCDEf".getBytes("utf-8");//hexStringToBytes("0123456789ABCDEF");
            /*byte key[] =AESUtil.key.getBytes("utf-8"); ////hexStringToBytes("0123456789ABCDEF");
            String src = "werty7890";
            System.out.println("密钥:"+AESUtil.key);
            System.out.println("原字符串:"+src);

            String enc = byteToHexString(encrypt(src, key));
            System.out.println("加密："+enc);
            System.out.println("解密："+new String(decrypt(enc, key), "utf-8"));*/

            byte key[] =AESUtil.key.getBytes("utf-8");
            final Base64.Decoder decoder = Base64.getDecoder();
            final String encodedText = "FBC62A33B288C0F178ADDC41DA877A51A39B44FF7E6201E194BDD7741ACE2D5C170C75E02CED74F9D922F6339E0339DD13163DB3515D96B85AE19719DC5FA4611EA5A056F9748AA35AAB3A44F6B28C1DA525BEE7A5ADB0DE082F3AC7E974732C7036EB74EAD919AC6FFF37D7AEEBBFC452F72ACC43260544EBB0D0E77AA1099F2AD7ADD675EC4D4D0D316AB84587BBD81EFEA1C9C307B3BD27AD0710FC55CB81370C047BA83EDABEC48100E10C3106817D65AC78563348F14D685E0C16F86164B871DB4847707F9146947E7A3A5CB5577AD53AF8E57B3C6B12816465C36A181A40326347054BDEB1D161A81C5C114E505CEA3CE2265E72C0FCD7AE1396E2C93D73344FA00FEB3DDFD4B41C4A2ADDF20DFD1EDFE45D2A95E70F435651D1A109A05FEB7B5C5B1BD777119FE544431609E3EED6B556706B5850B6F2E45DEAAEBA73";
            System.out.println("解密："+new String(Decrypt(encodedText), "utf-8"));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
