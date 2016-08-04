package com.wgl.save;

import android.app.Activity;;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wgl.savelibrary.SQLiteUtils.*;
import com.wgl.savelibrary.fileUtils.SDCardBitmapUtil;
import com.wgl.savelibrary.fileUtils.SDCardFileUtil;
import com.wgl.savelibrary.fileUtils.SimpleFileUtil;
import com.wgl.savelibrary.prefsUtils.ModlePrefsUtil;
import com.wgl.savelibrary.prefsUtils.SimplePrefsUtil;

public class MainActivity extends Activity {

    private ImageView iv;
    private EditText et1,et2,et3;
    private TextView tv;
    private SDCardBitmapUtil sdCardBitmapUtil;
    private SimpleFileUtil simpleFileUtil;
    private SDCardFileUtil sdCardFileUtil;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sdCardBitmapUtil = new SDCardBitmapUtil(this);
//        simpleFileUtil = new SimpleFileUtil(this);
        sdCardFileUtil = new SDCardFileUtil(this);
        iv = (ImageView) findViewById(R.id.iv);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        tv = (TextView) findViewById(R.id.tv);
        dbHelper = new DBHelper(this);
        setNormal();
    }

    private void setNormal(){
        Drawable drawable = sdCardBitmapUtil.getDrawable();
        String str1 = SimplePrefsUtil.getString(this,"str1");
        MainModle modle = (MainModle) ModlePrefsUtil.getSharedPrefrencesUtil().getInformation("str2",this);
        Modle[] m = dbHelper.queryAlldata();
//        String file = simpleFileUtil.read();
        String file = sdCardFileUtil.read();
        if(null != drawable){
            //sd卡中取出头像
            iv.setImageDrawable(drawable);
            Toast.makeText(this,"读取图片成功",Toast.LENGTH_SHORT).show();
        }
        if(!"".equals(str1)){
            et1.setText(str1);
            Toast.makeText(this,"读取键值成功",Toast.LENGTH_SHORT).show();
        }
        if(null != modle){
            String str2 = modle.str2;
            et2.setText(str2);
            Toast.makeText(this,"读取实体偏好成功",Toast.LENGTH_SHORT).show();
        }
        if(null != m){
            Modle mo = new Modle();
            String str = "";
            for(int i=0;i<m.length;i++){
                mo = m[i];
                str += mo.key_1+mo.key_2+mo.key_3;
            }
            et3.setText(str);
            Toast.makeText(this,"读取SQLite成功",Toast.LENGTH_SHORT).show();
        }
        if(null != file){
            tv.setText(file);
            Toast.makeText(this,"读取SD文件成功",Toast.LENGTH_SHORT).show();
        }
    }

    public void doClick(View view){
        String str1 = et1.getText().toString();
        String str2 = et2.getText().toString();
        String str3 = et3.getText().toString();
        switch (view.getId()){
            case R.id.bt1:
                SimplePrefsUtil.putString(this,"str1",str1);
                Toast.makeText(this,"保存键值成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt2:
                MainModle mainModle = new MainModle();
                mainModle.str2 = str2;
                ModlePrefsUtil.getSharedPrefrencesUtil().saveInformation("str2",this,mainModle);
                Toast.makeText(this,"保存实体成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt3:
                iv.setImageDrawable(getResources().getDrawable(R.mipmap.a));
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.a);
                sdCardBitmapUtil.savePicture(bitmap);
                Toast.makeText(this,"保存图片成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt4:
                Modle modle = new Modle();
                modle.key_1 = str1;
                modle.key_2 = str2;
                modle.key_3 = str3;
                dbHelper.insert(modle);
                Toast.makeText(this,"保存SQLite成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt5:
//                simpleFileUtil.write(str1+str2+str3);
                sdCardFileUtil.write(str1+str2+str3);
                Toast.makeText(this,"保存SD文件成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
