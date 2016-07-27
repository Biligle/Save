package com.wgl.savelibrary.prefsUtils;

import android.content.Context;
import android.content.SharedPreferences;

/**简单存取
 * Created by wgl.
 */
public class SimplePrefsUtil {
    // 本APP保存SharedPreferences参数的文件名
    private static final String APP_Prefs_Name = "roadheadline";

    /**
     * 保存字符串
     *
     * @param context
     * @param key
     * @param value
     * @return 保存成功返回true
     */
    public static boolean putString(Context context, String key, String value) {
        return context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE).edit().putString(key, value).commit();
    }

    /**
     * 获取字符串
     *
     * @param context
     * @param key
     * @return 若不存在该key值对应的value，返回null
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * 获取字符串
     *
     * @param context
     * @param key
     * @param defValue
     * @return 若不存在该key值对应的value，返回用户传入的defValue
     */
    public static String getString(Context context, String key, String defValue) {
        return context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE).getString(key, defValue);
    }


    /**
     * 保存int数据
     *
     * @param context
     * @param key
     * @param value
     * @return 保存成功返回true
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 获取int数据
     * @param context
     * @param key
     * @return 不存在返回-1
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * 获取int数据
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * 保存long类型数据
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 获取long类型数据
     *
     * @param context
     * @param key The name of the preference to retrieve
     * @return 不存在返回-1
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * 获取long类型数据
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * 保存float类型数据
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 获取float类型数据
     *
     * @param context
     * @param key
     * @return 不存在返回-1
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * 获取float类型数据
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * 保存boolean类型数据
     *
     * @param context
     * @param key
     * @param value
     * @return
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 获取boolean类型数据
     *
     * @param context
     * @param key
     * @return 不存在返回false
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 获取boolean类型数据
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(APP_Prefs_Name, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }
}
