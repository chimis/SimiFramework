package io.simi.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 16/1/9 13:29
 * Updated time: 16/1/9 13:29
 */
public class Utils {

    private static Context context;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    private Utils() {
    }

    public static void initialize(Context application) {
        context = application;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue 像素点
     * @return px
     */
    public static int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     * @param pxValue 像素值
     * @return dp
     */
    public static int px2dp(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 生成随机ID
     * @return ID
     */
    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1;
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    /**
     * 获取当前系统时间戳
     *
     * @return 时间戳
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 随机生成数字字符串
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String randomNumber(int length) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    /**
     * 获取设备唯一标识
     * @return 标识
     */
    public static String deviceID() {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /**
     * MD5加密16位
     * @param string 待加密字符串
     * @return 加密后字符串
     */
    public static String encrypt16MD5(String string) {
        string = encrypt32MD5(string);
        if (string == null) {
            return null;
        }
        return string.substring(8, 24);
    }

    /**
     * MD5加密32位
     * @param string 待加密字符串
     * @return 加密后字符串
     */
    public static String encrypt32MD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (Exception e) {
            return null;
        }
        StringBuilder builder = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                builder.append("0");
            }
            builder.append(Integer.toHexString(b & 0xFF));
        }
        return builder.toString();
    }

}
