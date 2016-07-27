package com.wgl.savelibrary.fileUtils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**简单读取和写入文件
 * Created by wgl.
 */
public class SimpleFileUtil {
    private Context context;
    public SimpleFileUtil(Context context){
        this.context = context;
    }
    public String read() {
        try {
            FileInputStream inStream = context.openFileInput("message.txt");
            byte[] buffer = new byte[1024];
            int hasRead = 0;
            StringBuilder sb = new StringBuilder();
            while ((hasRead = inStream.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, hasRead));
            }

            inStream.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String msg){
        // 步骤1：获取输入值
        if(msg == null) return;
        try {
            // 步骤2:创建一个FileOutputStream对象,MODE_APPEND追加模式
            FileOutputStream fos = context.openFileOutput("message.txt",
                    context.MODE_APPEND);
            // 步骤3：将获取过来的值放入文件
            fos.write(msg.getBytes());
            // 步骤4：关闭数据流
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
