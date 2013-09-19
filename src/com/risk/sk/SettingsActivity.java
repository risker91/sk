package com.risk.sk;

import java.util.ArrayList;

import com.risk.applied.CustomArrayList;
import com.risk.applied.CustomListViewAdapter;
import com.risk.applied.SendPac;
import com.risk.applied.User;
import com.risk.applied.db;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;



public class SettingsActivity extends Activity {
	 String[] data = {"one", "two", "three", "four", "five", "four", "five", "four", "five"};
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		db MyDb = new db(this);	
		User MyUser = new User(MyDb);
		
		
		SendPac[] namesP = User.getProgramm();
		
		
		//CustomListViewAdapter adapter = new CustomListViewAdapter(this, namesP);
		CustomArrayList adapter = new CustomArrayList(this, android.R.layout.simple_spinner_item, namesP);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        
	        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	        spinner.setAdapter(adapter);
	        System.out.println(namesP[0]);
	        
	       
	        spinner.setPrompt("Выбор Тренировки");
	        for (int i = 0; i < namesP.length; i++) {
	        	if(User.getActivePid()== Integer.parseInt(namesP[i].id))
	        	spinner.setSelection(i);				
			}
	        
	        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	      @Override
	      public void onItemSelected(AdapterView<?> parent, View view,
	          int position, long id) {
	       
	    	User.setActivePid(spinner.getSelectedItem().toString());  
	        Toast.makeText(getBaseContext(),"id is "+ (int)id, Toast.LENGTH_SHORT).show();
	    	  
	      }
	      @Override
	      public void onNothingSelected(AdapterView<?> arg0) {
	      }
	    });
	    }
	}