package com.risk.applied;

import java.util.Vector;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

public  class Upr implements Parcelable {
	
	
	//table fields(7) 
	private  int id_;
	private  String title_;
	private  int pid_;
	private  int day_;//день тренировки
	private  int count_;//кол-во повторений
	private  int podhod_;
	private  int weight_;
	
	
	private  db bd_;
	

	public Upr(db bd,int id, String title, int pid, int day, int count, int podhod, int weight) {
		// TODO Auto-generated constructor stub
		id_ 	= id;
		title_ 	= title;
		pid_	= pid;
		day_ 	= day;
		count_ 	= count;
		podhod_ = podhod;
		weight_ = weight;
		
		bd_=bd;
		//System.out.println("UPR CREATED");
	}
	
	private Upr(Parcel in) {
		
		id_ = in.readInt();
		title_ = in.readString();
		pid_ = in.readInt();
		day_ = in.readInt();
		count_ = in.readInt();
		podhod_ = in.readInt();
		weight_ = in.readInt();
	}
	
	
	
	public Upr(db bd){
		bd_=bd;
	}
	
	public  void saveUpr(){
		ContentValues cv = new ContentValues();
		SQLiteDatabase condb = bd_.getWritableDatabase();  
        
		System.out.println("Я в классе упр");
        cv.put(db.COL_ID, id_);
        cv.put(db.COL_UPR_TITLE, title_);
        cv.put(db.COL_UPR_PID, pid_);
        cv.put(db.COL_UPR_DAY, day_);
        cv.put(db.COL_UPR_COUNT, count_);
        cv.put(db.COL_UPR_PODHOD, podhod_);
        cv.put(db.COL_UPR_WEIGHT, weight_);
        condb.insert(db.TABLE_UPRS, null, cv);
		condb.close();
	}
	
	
	//GETTERS
	
	public  int getId(){
		return id_;
	}
	
	
	public  String getTitle(){
		return title_;
	}
	
	
	public  int getPid(){
		return pid_;
	}
	
	
	public  int getDay(){
		return day_;
	}
	
	
	public  int getCount(){
		return count_;
	}
	
	
	public  int getPodhod(){
		return podhod_;
	}
	
	
	public  int getWeight(){
		return weight_;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public static final Parcelable.Creator<Upr> CREATOR = new Parcelable.Creator<Upr>() {
		 
	    public Upr createFromParcel(Parcel in) {
	      return new Upr(in);
	    }

		@Override
		public Upr[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Upr[size];
			
		}
	 };
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(id_);
		dest.writeString(title_);
		dest.writeInt(pid_);
		dest.writeInt(day_);
		dest.writeInt(count_);
		dest.writeInt(podhod_);
		dest.writeInt(weight_);
		
		
	}
	
}