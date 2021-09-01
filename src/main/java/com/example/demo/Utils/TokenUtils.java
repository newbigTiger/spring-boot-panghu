package com.example.demo.Utils;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;

public class TokenUtils {
    /**
     * 密钥(DES加密和解密过程中，密钥长度都必须是8的倍数)
     */
    private final static String SECRET_KEY = "ucserver";
    /**
     * 加解密统一使用的编码方式
     */
    private final static String ENCODING = "utf-8";
    /**
     * 3DES加密
     *
     * @param plainText 需要加密的字符串
     * @return 返回加密后的参数
     * @throws Exception 抛出异常
     */
    public static String encode(String plainText) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(SECRET_KEY.getBytes());
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        //DES加密和解密过程中，密钥长度都必须是8的倍数
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(dks);
        // using DES in ECB mode
        Cipher cipher = Cipher.getInstance("DES/ECB/pkcs5padding");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, sr);
        // 执行加密操作
        byte[] encryptData = cipher.doFinal(plainText.getBytes(ENCODING));
        return Base64.encode(encryptData);
    }
    /**
     * Base64编码工具类
     */
    public static class Base64 {
        private static final char[] LEGAL_CHARS;

        static {
            LEGAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_".toCharArray();
        }

        public static String encode(byte[] data) {
            int start = 0;
            int len = data.length;
            StringBuilder buf = new StringBuilder(data.length * 3 / 2);

            int end = len - 3;
            int i = start;
            int n = 0;

            while (i <= end) {
                int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 0x0ff) << 8) | (((int) data[i + 2]) & 0x0ff);
                buf.append(LEGAL_CHARS[(d >> 18) & 63]);
                buf.append(LEGAL_CHARS[(d >> 12) & 63]);
                buf.append(LEGAL_CHARS[(d >> 6) & 63]);
                buf.append(LEGAL_CHARS[d & 63]);
                i += 3;
                if (n++ >= 14) {
                    n = 0;
                    buf.append(" ");
                }
            }
            if (i == start + len - 2) {
                int d = ((((int) data[i]) & 0x0ff) << 16) | ((((int) data[i + 1]) & 255) << 8);
                buf.append(LEGAL_CHARS[(d >> 18) & 63]);
                buf.append(LEGAL_CHARS[(d >> 12) & 63]);
                buf.append(LEGAL_CHARS[(d >> 6) & 63]);
                buf.append("=");
            } else if (i == start + len - 1) {
                int d = (((int) data[i]) & 0x0ff) << 16;
                buf.append(LEGAL_CHARS[(d >> 18) & 63]);
                buf.append(LEGAL_CHARS[(d >> 12) & 63]);
                buf.append("==");
            }
            return buf.toString();
        }

        private static int decode(char c) {
            if (c >= 'A' && c <= 'Z') {
                return ((int) c) - 65;
            } else if (c >= 'a' && c <= 'z') {
                return ((int) c) - 97 + 26;
            } else if (c >= '0' && c <= '9') {
                return ((int) c) - 48 + 26 + 26;
            } else {
                switch (c) {
                    case '-':
                        return 62;
                    case '_':
                        return 63;
                    case '=':
                        return 0;
                    default:
                        throw new RuntimeException("unexpected code: " + c);
                }
            }
        }

        /**
         * Decodes the given Base64 encoded String to a new byte array. The byte array holding the decoded data is returned.
         */
        public static byte[] decode(String s) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                decode(s, bos);
            } catch (IOException e) {
                throw new RuntimeException();
            }
            byte[] decodedBytes = bos.toByteArray();
            try {
                bos.close();
            } catch (IOException ex) {
                System.err.println("Error while decoding BASE64: " + ex.toString());
            }
            return decodedBytes;
        }

        private static void decode(String s, OutputStream os) throws IOException {
            int i = 0;
            int len = s.length();
            while (true) {
                while (i < len && s.charAt(i) <= ' ') {
                    i++;
                }
                if (i == len) {
                    break;
                }
                int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6) + (decode(s.charAt(i + 3)));
                os.write((tri >> 16) & 255);
                if (s.charAt(i + 2) == '=') {
                    break;
                }
                os.write((tri >> 8) & 255);
                if (s.charAt(i + 3) == '=') {
                    break;
                }
                os.write(tri & 255);
                i += 4;
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(encode("181ee8952a88f5a57db52587472c3798"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
