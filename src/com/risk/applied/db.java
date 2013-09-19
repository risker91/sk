package com.risk.applied;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class db extends SQLiteOpenHelper implements BaseColumns {
	
	private static final String DATABASE_NAME = "gym.db";
	private static final int DATABASE_VERSION = 9;
	
	
	public static final String TABLE_USERS	   = "users";
	public static final String TABLE_PROGRAMMS = "programms";
	public static final String TABLE_UPRS	   = "uprs";
	
	
	public static final String COL_ID			= "id";
	//**columns TABLE_UPRS
	public static final String COL_UPR_PID 			= "pid";
	public static final String COL_UPR_TITLE 		= "title";
	public static final String COL_UPR_DAY 			= "day";
	public static final String COL_UPR_COUNT 		= "count";
	public static final String COL_UPR_PODHOD 		= "podhod";
	public static final String COL_UPR_WEIGHT 		= "weight";
	private static final String SQL_DELETE_UPRS		= "DROP TABLE IF EXISTS "+ TABLE_UPRS;
	private static final String SQL_CREATE_UPRS		= "CREATE TABLE "
														+ TABLE_UPRS + " (" + COL_ID + " INTEGER PRIMARY KEY,"
														+ COL_UPR_TITLE +	" VARCHAR(255)," 
														+ COL_UPR_PID   +	" INTEGER(11),"
														+ COL_UPR_DAY   +	" INTEGER(1)," 
														+ COL_UPR_COUNT +	" INTEGER(3)," 
														+ COL_UPR_PODHOD+	" INTEGER(2),"
														+ COL_UPR_WEIGHT+	" FLOAT);";		
			
	
	
	//**columns TABLE_PROGRAMMS
	public static final String COL_PRG_NAME 			= "name";
	public static final String COL_PRG_UID				= "uid";	
	private static final String SQL_DELETE_PROGRAMMS 	= "DROP TABLE IF EXISTS "+ TABLE_PROGRAMMS;
	private static final String SQL_CREATE_PROGRAMMS 	= "CREATE TABLE "
									+ TABLE_PROGRAMMS + " (" + COL_ID + " INTEGER PRIMARY KEY,"
									+ COL_PRG_NAME + " VARCHAR(255)," + COL_PRG_UID + " INTEGER);";	
	
	
	
	
	//columns TABLE_USER
	public static final String COL_USER_LOGIN = "login";
	public static final String COL_USER_FIRSTNAME = "first_name";
	public static final String COL_USER_ISLOGIN   = "is_login";
	public static final String COL_USER_HASH   = "hash";
	public static final String COL_USER_ACTIVEPID   = "active_pid";
	
	
	private static final String SQL_CREATE_USERS = "CREATE TABLE "
			+ TABLE_USERS + " (" + COL_ID + " INTEGER PRIMARY KEY,"
			+ COL_USER_LOGIN + " VARCHAR(64),"
			+ COL_USER_FIRSTNAME + " VARCHAR(64),"
			+ COL_USER_ISLOGIN +" INT(1),"
			+ COL_USER_ACTIVEPID +" INT(11),"
			+ COL_USER_HASH +" VARCHAR(40));";
	
	private static final String SQL_DELETE_USERS = "DROP TABLE IF EXISTS "+ TABLE_USERS;
	

	public db(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_USERS);
		db.execSQL(SQL_CREATE_PROGRAMMS);
		db.execSQL(SQL_CREATE_UPRS);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w("LOG_TAG", "ќбновление базы данных с версии " + oldVersion
				+ " до версии " + newVersion + ", которое удалит все старые данные");
		// ”дал€ем предыдущую таблицу при апгрейде
		db.execSQL(SQL_DELETE_USERS);
		db.execSQL(SQL_DELETE_PROGRAMMS);
		db.execSQL(SQL_DELETE_UPRS);
		// —оздаЄм новый экземпл€р таблицы
		onCreate(db);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
