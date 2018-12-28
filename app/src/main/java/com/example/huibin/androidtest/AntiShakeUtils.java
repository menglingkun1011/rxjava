package com.example.huibin.androidtest;

/**
 *手指防抖动
 */
public class AntiShakeUtils {

    private static long lastTime = 0;
    private static AntiShakeUtils mShakeUtils = new AntiShakeUtils();

    public static AntiShakeUtils newInstance() {
        return mShakeUtils;
    }

    public static boolean isFingerShake(long shakeTime){
        if(System.currentTimeMillis() - lastTime > shakeTime) {
            lastTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}
