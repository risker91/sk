package com.risk.applied;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Programm {
	
	private db bd_;
	
	private String name_;
	private int uid_;
	private int id_;
	//public Upr[] uprs;
	public Day days[];

	public Programm(db bd,int id, String name) {
		bd_ = bd;
		id_ = id;
		uid_ = User.getUid();
		name_ = name;
	}

	public Programm(db bd,String name) {
		bd_ = bd;
		System.out.println("This is programm constructor");
		SQLiteDatabase condb = bd_.getWritableDatabase();
		   Cursor c = condb.query(db.TABLE_PROGRAMMS, null, db.COL_PRG_NAME+"=?", new String[] {name}, null, null, null);
	    System.out.println("This is programm constructor, get rows: "+c.getCount());
	   
	    try{
	    if (c.moveToFirst()) {
	    				id_ = c.getInt(c.getColumnIndex(db.COL_ID));
		        		uid_ = c.getInt(c.getColumnIndex(db.COL_PRG_UID));
				     	name_ = c.getString(c.getColumnIndex(db.COL_PRG_NAME));
	    }
	    }
	    catch(Exception e){
	    	
	    	e.printStackTrace();
	    }
	    
	  //  this.loadUprs();
	  
	    c.close();
		condb.close();
		
	}
	
	public Programm(db bd,int pid) {
		bd_ = bd;
		System.out.println("This is programm constructor");
		SQLiteDatabase condb = bd_.getWritableDatabase();
		   Cursor c = condb.query(db.TABLE_PROGRAMMS, null, db.COL_ID+"=?", new String[] {Integer.toString(pid)}, null, null, null);
	    System.out.println("This is programm constructor, get rows: "+c.getCount());
	   
	    try{
	    if (c.moveToFirst()) {
	    				id_ = c.getInt(c.getColumnIndex(db.COL_ID));
		        		uid_ = c.getInt(c.getColumnIndex(db.COL_PRG_UID));
				     	name_ = c.getString(c.getColumnIndex(db.COL_PRG_NAME));
	    }
	    }
	    catch(Exception e){
	    	
	    	e.printStackTrace();
	    }
	    
	  //  this.loadUprs();
	  
	    c.close();
		condb.close();
		
	}
	
	
	public void setProgramm(String name){

		name_ = name;	
	}
	
	
	public long save(){
		
		//String buf = (String);
		
		SQLiteDatabase condb = bd_.getWritableDatabase();  
		ContentValues cv = new ContentValues();
		cv.put(db.COL_ID, id_);
		cv.put(db.COL_PRG_NAME, name_);
		cv.put(db.COL_PRG_UID, uid_);
        long id = condb.insert(db.TABLE_PROGRAMMS, null, cv);        
		condb.close();
		return id;
	}
	
	/*
	private void loadUprs(){
		
		 SQLiteDatabase condb = bd_.getWritableDatabase();
		 System.out.println("ID is "+id_);
		 Cursor c = condb.query(db.TABLE_UPRS, null, db.COL_UPR_PID+"=?", new String[] {Integer.toString(id_)}, null, null, null);
		 
		
		 int i=0;
		 uprs = new Upr[c.getCount()];
		
		 
		 if (c.moveToFirst()) {
			 	
		        			 
		        int idIndex = c.getColumnIndex(db.COL_ID);
		        int titleIndex = c.getColumnIndex(db.COL_UPR_TITLE);
		        int pidIndex = c.getColumnIndex(db.COL_UPR_PID);
		        int dayIndex = c.getColumnIndex(db.COL_UPR_DAY);
		        int countIndex = c.getColumnIndex(db.COL_UPR_COUNT);
		        int podhodIndex = c.getColumnIndex(db.COL_UPR_PODHOD);
		        int weightIndex = c.getColumnIndex(db.COL_UPR_WEIGHT);

		        do {
		        	uprs[i] = new Upr(bd_,
        					c.getInt(idIndex),
			        		c.getString(titleIndex),
			        		c.getInt(pidIndex),
			        		c.getInt(dayIndex),
			        		c.getInt(countIndex),
			        		c.getInt(podhodIndex),
			        		c.getInt(weightIndex));
		        	//System.out.println(all[i].getClass());
		        	i++;
		         
		        } while (c.moveToNext());
		        
		    //    System.out.println("1=="+all[0].getTitle());
		      //  System.out.println("2=="+all[1].getTitle());
		        
		} else System.out.println("UPRS NOT FOUND");
		
		
		 

}
	*/
	
	public int getCountDays(){
		
		
		SQLiteDatabase condb = bd_.getWritableDatabase(); 
		
		
	    
		 String[] columns = new String[] {"max(day) as maxday" };
		 String selection = "pid = ?";
		 String[] selectionArgs = new String[] { Integer.toString(id_) };
		 Cursor c = condb.query(db.TABLE_UPRS, columns, selection, selectionArgs, null, null, null);
		 int max=0;
		 if (c != null) {
		      if (c.moveToFirst()) {		        
		        max = c.getInt(c.getColumnIndex("maxday"));		        
		          }
		      }
		      c.close();
		
		return max;
	}
	
	/*
	public SendPac[] getNameUprSP(){
		
		SendPac[] result = new SendPac[uprs.length];
		for (int i = 0; i < uprs.length; i++) {
			result[i] = new SendPac(uprs[i].getId(), uprs[i].getTitle());			
		}
		return result;
	}
	*/
	
	/*
	public Upr findByName(String name){
		
		for (int i = 0; i < uprs.length; i++) {
			
			if(uprs[i].getTitle().equals(name)) return uprs[i];
		}
		
		return null;
	}
	*/
	
	
	public int getId(){
		return id_;	
	}
	
	public String getName(){
		return name_;	
	}
	
	
	//public Upr[] getUprs(){
	//	return uprs;	
	//}
	
}
