package com.wgl.savelibrary.prefsUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 实体形式存取
 * Created by wgl.
 */
public class ModlePrefsUtil {
    private SharedPreferences sharedPrefrences;
    private SharedPreferences.Editor editor;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ByteArrayOutputStream outbyte;
    private ByteArrayInputStream inbyte;
    private static ModlePrefsUtil shared;
    private ModlePrefsUtil(){}
    public static ModlePrefsUtil getSharedPrefrencesUtil(){
        if(shared == null){
            shared = new ModlePrefsUtil();
        }
        return shared;
    }

    /**
     * 保存到app的xml文件中
     * @param name:  xml的名字和k值的名字
     * @param context:  **Activity.this
     * @param entity: 实体中的数据
     */
    public void saveInformation(String name, Context context, Object entity) {

        sharedPrefrences = context.getSharedPreferences(name, Activity.MODE_PRIVATE);
        try {
            outbyte = new ByteArrayOutputStream();
            out = new ObjectOutputStream(outbyte);
            out.writeObject(entity);
        }catch (IOException e) {
            e.printStackTrace();
        }

        String value = new String(Base64.encode(outbyte.toByteArray(), 1));
        editor = sharedPrefrences.edit();
        editor.putString(name, value);
        editor.commit();

    }

    /**
     * 取出app的xml数据
     * @param name:  xml的名字和k值的名字
     * @param context:  **Activity.this
     * @return
     */
    public Object getInformation(String name, Context context) {
        sharedPrefrences = context.getSharedPreferences(name, Activity.MODE_PRIVATE);
        Object obj = null;
        String productBase64 = sharedPrefrences.getString(name, ""); // 对Base64格式的字符串进行解码
        inbyte = new ByteArrayInputStream(Base64.decode(productBase64.getBytes(), 1));


        try {
            in = new ObjectInputStream(inbyte);
            obj = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
