package com.wgl.savelibrary.fileUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**SD卡存、取、删图片
 * Created by wgl.
 */
public class SDCardBitmapUtil {
    private Context context;
    public SDCardBitmapUtil(Context context){
        this.context = context;
    }

    public void savePicture(Bitmap bitmap) {
        String pictureName = "/mnt/sdcard/picture.jpg";
        File file = new File(pictureName);
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        if(bos != null){
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取SD卡图片
     * @return
     */
    public Drawable getDrawable(){
        Drawable drawable = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path=Environment.getExternalStorageDirectory().getPath()+ "/picture"+".jpg";
            File mFile=new File(path);
            if(mFile.exists()){
                drawable = Drawable.createFromPath(path);
            }
        }else { // SDCard不存在，使用Toast提示用户
            Toast.makeText(context, "读取失败，SD卡不存在！", Toast.LENGTH_LONG).show();
        }
        return drawable;
    }

    /**
     * 删除SD卡图片和目录
     */
    public void deleteFile() {
        File dirFile = new File("/mnt/sdcard/picture.jpg");
        if(! dirFile.exists()){
            Toast.makeText(context, "sd卡没有图片可删除", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dirFile.isDirectory()) {
            String[] children = dirFile.list();
            for (int i = 0; i < children.length; i++) {
                new File(dirFile, children[i]).delete();
                Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
            }
        }

        dirFile.delete();
        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
    }
}
