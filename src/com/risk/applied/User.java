package com.risk.applied;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User {

	private static int uid_;
	private static String username_;
	private static String firstname_;
	private static String hash_;
	private static int islogin_;
	private static int activepid_;	
	private static db bd_;
	private static String[] programms_;
	
	User (db MyDb, String login, String firstname, int uid, String hash){
		System.out.println("UUUUSSSSEEERRR");	    
		SQLiteDatabase condb = MyDb.getWritableDatabase();
		if(!checkUser(login)) createUser(login, firstname, uid, hash);
		MyDb.close();
	}
	
	public User(db bd){
	//	String sql = "SELECT COUNT(*) FROM users WHERE login_is=1;";
		bd_ = bd;
	    SQLiteDatabase condb = bd_.getWritableDatabase();
		Cursor c = condb.query(db.TABLE_USERS, null, db.COL_USER_ISLOGIN+"=?", new String[] {"1"}, null, null, null);
		
		  if (c.getCount() == 0) {
		    	islogin_ = 0;
		    	username_ = null;
		    	firstname_ = null;
		    	hash_ = null;
		    	activepid_ = 0;
		    	
		    	
		      } else {
			c.moveToFirst();
			this.uid_		 = c.getInt(c.getColumnIndex(db.COL_ID));
	    	this.username_	 = c.getString(c.getColumnIndex(db.COL_USER_LOGIN));
	    	this.firstname_	 = c.getString(c.getColumnIndex(db.COL_USER_FIRSTNAME));
	    	this.islogin_ 	 = c.getInt(c.getColumnIndex(db.COL_USER_ISLOGIN));
	    	this.hash_ 		 = c.getString(c.getColumnIndex(db.COL_USER_HASH));
	    	this.activepid_  = c.getInt(c.getColumnIndex(db.COL_USER_ACTIVEPID));
		      }
		  c.close();
		  condb.close();
		
		
	}
	
	public static void createUser(String login, String firstname,int uid, String hash){
		
		  SQLiteDatabase condb = bd_.getWritableDatabase();
		  
		  if(checkUser(login)) return ;
			  
		  ContentValues cv = new ContentValues();
		  
		  	  cv.put(db.COL_ID, uid);
	          cv.put(db.COL_USER_LOGIN, login);
	          cv.put(db.COL_USER_FIRSTNAME, firstname);
	          cv.put(db.COL_USER_ISLOGIN, 1);
	          cv.put(db.COL_USER_HASH, hash);
	          cv.put(db.COL_USER_ACTIVEPID, 0);
	          condb.insert(db.TABLE_USERS, null, cv);
	          condb.close();
	          
	          username_  = login;
	          hash_  = hash;
	          uid_  = uid;
	          firstname_ = firstname;
	          islogin_   = 1;
	          activepid_ = 0;
		
	}
	
	private static boolean  checkUser( String login){
		
		SQLiteDatabase condb = bd_.getWritableDatabase();
	
	    Cursor c = condb.query(db.TABLE_USERS, null, "login=?", new String[] {login}, null, null, null);
	    
	    if (c.getCount() == 0) {
	    	System.out.println("нет таких записей");	 
	    	islogin_ = 0;
	      } else {
		c.moveToFirst();
    	username_	 = c.getString(c.getColumnIndex(db.COL_USER_LOGIN));
    	firstname_	 = c.getString(c.getColumnIndex(db.COL_USER_FIRSTNAME));
    	islogin_ 	 = c.getInt(c.getColumnIndex(db.COL_USER_ISLOGIN));
    	
		    	if(islogin_==0) {    		
		    		ContentValues args = new ContentValues();
			    	args.put(db.COL_USER_ISLOGIN, 1);
			    	condb.update(db.TABLE_USERS, args, db.COL_USER_LOGIN + "=" + username_ , null);
		    	}
		    	c.close();
		    	return true;
	    
	      }
	    c.close();
	   return false;
	}
	
	public static void setActivePid(String prog){
		
		Programm prg = new Programm(bd_, prog);
		SQLiteDatabase condb = bd_.getWritableDatabase();
		ContentValues args = new ContentValues();
		
	   	args.put(db.COL_USER_ACTIVEPID, prg.getId());
	   	condb.update(db.TABLE_USERS, args, db.COL_ID+ "=" + uid_ , null);
	   	
	   	condb.close();
		
		
	}
	
	
	public static SendPac[] getProgramm(){
		
		 SQLiteDatabase condb = bd_.getWritableDatabase();
		 Cursor c = condb.query(db.TABLE_PROGRAMMS, null, db.COL_PRG_UID+"=?", new String[] {Integer.toString(uid_)}, null, null, null);
		String[] res = new String[c.getCount()];
		int i=0;
		
		//ArrayList<SendPac> list = new ArrayList<SendPac>();
		SendPac[] list = new SendPac[c.getCount()];
		if (c.moveToFirst()) {
	        
			int titleIndex = c.getColumnIndex(db.COL_PRG_NAME);
			int idIndex = c.getColumnIndex(db.COL_ID);
	        
	        do {
	        	list[i] =  new SendPac(c.getInt(idIndex), c.getString(titleIndex)) ;
	        	System.out.println(c.getString(titleIndex));
	        	i++;
	        } while (c.moveToNext());
	        
		} else System.out.println("Programm NOT FOUND");
		
		return list;
	}
	
	
	public static String getUName(){
		return username_;
	}
	
	public static int isLogin(){
		return islogin_;
	}
	
	public static int getActivePid(){
		return activepid_;
	}
	
	public static int getUid(){
		return uid_;
	}
	
	public static String getHash(){
		return hash_;
	}
	
	public void setUName(String name){
		username_ = name;
	}
	
	

}
