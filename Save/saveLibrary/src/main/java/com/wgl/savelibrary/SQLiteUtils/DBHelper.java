package com.wgl.savelibrary.SQLiteUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * table默认有21列（包含主键ID）,table里的数据都是Sting类型，
 * 如需改变，需要将Sting转换其他类型
 */
public class DBHelper {
	private  static final String DB_NAME="myDatabase.db";
	private  static final String DB_TABLE="myDatabaseInfo";
	private  static final int DB_VERSION=1;
	
	private  static final String KEY_ID="KEY_ID";
	private  static final String KEY_1="KEY_1";
	private  static final String KEY_2="KEY_2";
	private  static final String KEY_3="KEY_3";
	private  static final String KEY_4="KEY_4";
	private  static final String KEY_5="KEY_5";
	private  static final String KEY_6="KEY_6";
	private  static final String KEY_7="KEY_7";
	private  static final String KEY_8="KEY_8";
	private  static final String KEY_9="KEY_9";
	private  static final String KEY_10="KEY_10";
	private  static final String KEY_11="KEY_11";
	private  static final String KEY_12="KEY_12";
	private  static final String KEY_13="KEY_13";
	private  static final String KEY_14="KEY_14";
	private  static final String KEY_15="KEY_15";
	private  static final String KEY_16="KEY_16";
	private  static final String KEY_17="KEY_17";
	private  static final String KEY_18="KEY_18";
	private  static final String KEY_19="KEY_19";
	private  static final String KEY_20="KEY_20";
	private SQLiteDatabase db=null;
	private final Context context;
	private DBopenHelper dbopenhelper;
	public DBHelper(Context _Context) {
		context=_Context;
		dbopenhelper=new DBopenHelper(context, DB_NAME, null, DB_VERSION);
	}

	/**
	 * 添加一条数据
	 * @param modle
	 * @return
     */
	public long insert(Modle modle){
		long id = -1;
		try {
			db = dbopenhelper.getWritableDatabase();
			ContentValues contentValues=new ContentValues();
			contentValues.put("KEY_1",modle.key_1);
			contentValues.put("KEY_2",modle.key_2);
			contentValues.put("KEY_3",modle.key_3);
			contentValues.put("KEY_4",modle.key_4);
			contentValues.put("KEY_5",modle.key_5);
			contentValues.put("KEY_6",modle.key_6);
			contentValues.put("KEY_7",modle.key_7);
			contentValues.put("KEY_8",modle.key_8);
			contentValues.put("KEY_9",modle.key_9);
			contentValues.put("KEY_10",modle.key_10);
			contentValues.put("KEY_11",modle.key_11);
			contentValues.put("KEY_12",modle.key_12);
			contentValues.put("KEY_13",modle.key_13);
			contentValues.put("KEY_14",modle.key_14);
			contentValues.put("KEY_15",modle.key_15);
			contentValues.put("KEY_16",modle.key_16);
			contentValues.put("KEY_17",modle.key_17);
			contentValues.put("KEY_18",modle.key_18);
			contentValues.put("KEY_19",modle.key_19);
			contentValues.put("KEY_20",modle.key_20);
			id = db.insert(DB_TABLE, null, contentValues);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(db != null){
				db.close();
			}
		Log.e("====","====="+id);
			return id;
	}

	/**
	 * 删除全部数据
	 * @return
     */
	public long deleteAlldata(){
		db = dbopenhelper.getWritableDatabase();
		long id = db.delete(DB_TABLE, null, null);
		if(db != null){
			db.close();
		}
		return id;
	}

	/**
	 * 获取全部数据
	 * @return
     */
	public Modle[] queryAlldata() {
		db = dbopenhelper.getReadableDatabase();
		Cursor results =db.query(DB_TABLE,
				getColumns(), null, null, null, null,  null);
		Modle[] m = null;
		m = ConvertToPeople(results, m);
		if(db != null){
			db.close();
		}
		return m;
	}

	/**
	 * 根据ID获取一条数据
	 * @param id
	 * @return
     */
	public Modle[] queryOnedata(long id) {
		db = dbopenhelper.getReadableDatabase();
		Cursor results=db.query(DB_TABLE, getColumns(), KEY_ID+"="+id, null, null, null, null);
		Modle[] p = null;
		p = ConvertToPeople(results, p);
		if(db != null){
			db.close();
		}
		return p;
	}

	/**
	 * 模糊查询,默认根据"KEY_1"列，作为查询依据
	 * @param name
	 * @return
     */
	public Modle[] queryWhere(String name){
		Modle[] p = null;
		try {
			db = dbopenhelper.getReadableDatabase();
			Cursor results=db.query(DB_TABLE, getColumns(), KEY_1+" LIKE "+"'%"+name+"_%'", null, null, null, null);
			if(results.getCount() != 0){
				p = ConvertToPeople(results, p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(db != null){
				db.close();
			}
		return p;
	}

	@NonNull
	private String[] getColumns() {
		return new String[]{KEY_ID,KEY_1,KEY_2,KEY_3,
				KEY_4,KEY_5,KEY_6,KEY_7,KEY_8,KEY_9,KEY_10,
				KEY_11,KEY_12,KEY_13,KEY_14,KEY_15,KEY_16,KEY_17,KEY_18,
				KEY_19,KEY_20,};
	}

	/**
	 * 精准查询
	 * @param name
	 * @return
     */
		public Modle[] queryName(String name){
			Modle[] m = null;
			try {
				db = dbopenhelper.getReadableDatabase();
				Cursor results=db.query(DB_TABLE, new String[]{KEY_ID,KEY_1,KEY_2,KEY_3,
						KEY_4,KEY_5,KEY_6,KEY_7,KEY_8,KEY_9,KEY_10,KEY_11,KEY_12,KEY_13,
						KEY_14, KEY_15,KEY_16,KEY_17,KEY_18,KEY_19,KEY_20,}, KEY_1+"='"+name+"'", null, null, null, null);
				if(results.getCount() != 0){
					m = ConvertToPeople(results, m);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(db != null){
					db.close();
				}
			return m;
		}

	/**
	 * 根据ID删除一条数据
	 * @param id
	 * @return
     */
	public long deleteOnedata(long id){
		db = dbopenhelper.getWritableDatabase();
		long ids = db.delete(DB_TABLE, KEY_ID+"="+id, null);
		if(db != null){
			db.close();
		}
		return ids;
	}

	/**
	 * 根据ID更新一条数据
	 * @param id
	 * @param modle
     * @return
     */
	public long updataOneData(long id,Modle modle) {
		db = dbopenhelper.getWritableDatabase();
		ContentValues upcontentValues=new ContentValues();
		for(int i = 1; i < 21; i++){
//			upcontentValues.put("KEY_"+i, modle.getKey());
		}
		long ids = -1;
		try {
			ids = db.update(DB_TABLE, upcontentValues, KEY_ID+"="+id, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(db != null){
			db.close();
		}
		return ids;
	}
	private Modle[] ConvertToPeople(Cursor cursor, Modle[] modle){
		int resultCounts=cursor.getCount();
		if(resultCounts==0||!cursor.moveToFirst()){
			return null;
		}
		if(modle == null){
			modle=new Modle[resultCounts];
		}
		for(int i=0;i<resultCounts;i++){
			modle[i]=new Modle();
			modle[i].ID=cursor.getInt(0);
			modle[i].key_1=cursor.getString(cursor.getColumnIndex(KEY_1));
			modle[i].key_2=cursor.getString(cursor.getColumnIndex(KEY_2));
			modle[i].key_3=cursor.getString(cursor.getColumnIndex(KEY_3));
			modle[i].key_4=cursor.getString(cursor.getColumnIndex(KEY_4));
			modle[i].key_5=cursor.getString(cursor.getColumnIndex(KEY_5));
			modle[i].key_6=cursor.getString(cursor.getColumnIndex(KEY_6));
			modle[i].key_7=cursor.getString(cursor.getColumnIndex(KEY_7));
			modle[i].key_8=cursor.getString(cursor.getColumnIndex(KEY_8));
			modle[i].key_9=cursor.getString(cursor.getColumnIndex(KEY_9));
			modle[i].key_10=cursor.getString(cursor.getColumnIndex(KEY_10));
			modle[i].key_11=cursor.getString(cursor.getColumnIndex(KEY_11));
			modle[i].key_12=cursor.getString(cursor.getColumnIndex(KEY_12));
			modle[i].key_13=cursor.getString(cursor.getColumnIndex(KEY_13));
			modle[i].key_14=cursor.getString(cursor.getColumnIndex(KEY_14));
			modle[i].key_15=cursor.getString(cursor.getColumnIndex(KEY_15));
			modle[i].key_16=cursor.getString(cursor.getColumnIndex(KEY_16));
			modle[i].key_17=cursor.getString(cursor.getColumnIndex(KEY_17));
			modle[i].key_18=cursor.getString(cursor.getColumnIndex(KEY_18));
			modle[i].key_19=cursor.getString(cursor.getColumnIndex(KEY_19));
			modle[i].key_20=cursor.getString(cursor.getColumnIndex(KEY_20));
			cursor.moveToNext();
		}
		return modle;
	}

	/**
	 * 数据库工具类
	 * @author wangguoli
	 *
	 */
	private static class DBopenHelper extends SQLiteOpenHelper{
		private  static final String DB_CREATE=
				"create table "+DB_TABLE+"("+KEY_ID+" integer primary key autoincrement, "
						+KEY_1+" text not null , "
						+KEY_2+" text not null , "
						+KEY_3+" text not null , "
						+KEY_4+" text not null , "
						+KEY_5+" text not null , "
						+KEY_6+" text not null , "
						+KEY_7+" text not null , "
						+KEY_8+" text not null , "
						+KEY_9+" text not null , "
						+KEY_10+" text not null , "
						+KEY_11+" text not null , "
						+KEY_12+" text not null , "
						+KEY_13+" text not null , "
						+KEY_14+" text not null , "
						+KEY_15+" text not null , "
						+KEY_16+" text not null , "
						+KEY_17+" text not null , "
						+KEY_18+" text not null , "
						+KEY_19+" text not null , "
						+KEY_20+" text not null ); ";
		public DBopenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			// TODO Auto-generated method stub
			_db.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			_db.execSQL("ORDP TABLE IF EXISTS "+DB_TABLE);
			onCreate(_db);
		}}
}
