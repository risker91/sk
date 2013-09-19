package com.risk.sk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.risk.applied.*;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ProgrammListActivity extends Activity {

	//private String[] programms;
	final ArrayList<String> programms = new ArrayList<String>();
	
public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.listprogramms);
		
	System.out.println( TrainActivity.class);
		
	db bd = new db(this);
	
	
	
	
	
		ListView lv = (ListView)findViewById(R.id.listView1);
		 SQLiteDatabase condb = bd.getWritableDatabase();
		 System.out.println("UID: "+User.getUid());
		 Cursor c = condb.query(db.TABLE_PROGRAMMS, null, db.COL_PRG_UID+"=?", new String[] {Integer.toString(User.getUid())}, null, null, null);
		 
		
		 int i=0;
		
		 
		  if (c.moveToFirst()) {
			 	
		        // определ€ем номера столбцов по имени в выборке
		        int nameIndex = c.getColumnIndex(db.COL_PRG_NAME);
		        

		        do {
		          // получаем значени€ по номерам столбцов и пишем все в лог
		        programms.add(c.getString(nameIndex));
		          // переход на следующую строку 
		          // а если следующей нет (текуща€ - последн€€), то false - выходим из цикла
		        } while (c.moveToNext());
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, programms);
			 lv.setAdapter(adapter);
		}
		  c.close();
		  condb.close();
		  bd.close();
		
		 
		
		  lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
					Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(), Toast.LENGTH_SHORT).show();
					
					 Intent intent = new Intent(ProgrammListActivity.this, TrainActivity.class);	
					 intent.putExtra("name_prg", ((TextView) itemClicked).getText());
					 startActivity(intent);
				}
			});
		  
		  
		
}
	
}