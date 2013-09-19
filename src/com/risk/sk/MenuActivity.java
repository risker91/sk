package com.risk.sk;


import android.app.Activity;
import com.risk.applied.User;
import android.app.Application;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuActivity extends Activity {

//	public static String JsonURL;
	//private static ArrayList<HashMap<String, Object>> myBooks;
	//private static final String FIRST = "firstname";
	//private static final String LAST = "lastname";
	private TextView name;
	//public ListView listView = (ListView) findViewById(R.id.textView1);

	
	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("create menu");
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.main);
		
		name = (TextView) findViewById(R.id.TopName);
		name.setText(User.getUName());
		
	
		
			
		//String json = extras.getString(JsonURL);		
	
	}
	
	public void programm(View view){
		Intent intent = new Intent(MenuActivity.this, ProgrammActivity.class);			
		startActivity(intent);
		
	}
	
	public void begintrain(View view){
		Intent intent = new Intent(MenuActivity.this, BegintrainActivity.class);			
		startActivity(intent);
		
		//Intent intent = new Intent(MenuActivity.this, Newmenu.class);			
		//startActivity(intent);
		
	}
	
	
	public void settings(View view){
		Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);			
		startActivity(intent);
	//	 Intent settingsActivity = new Intent(getBaseContext(),Preferences.class);
	//	 startActivity(settingsActivity);
		
	}
	
	public void onBackPressed()
    {
        moveTaskToBack(true);
        finish();
        System.runFinalizersOnExit(true);
        System.exit(0);
    }



}
