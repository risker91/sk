package com.risk.applied;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Day {
	private db bd_;
	public Upr[] uprs;
	public String day;	
	private int pid;
	
	
	
	private void loadUprs(){
		
		 SQLiteDatabase condb = bd_.getWritableDatabase();
		// System.out.println("ID is "+id_);
		 Cursor c = condb.query(db.TABLE_UPRS, null, db.COL_UPR_PID+"=?", new String[] {Integer.toString(pid)}, null, null, null);
		 
		
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
		        	
		        	i++;
		         
		        } while (c.moveToNext());
		        		  
		} else System.out.println("UPRS NOT FOUND");
		
		
		 

}
}
