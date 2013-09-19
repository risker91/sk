package com.risk.sk;

import com.risk.applied.CustomArrayList;
import com.risk.applied.CustomListViewAdapter;
import com.risk.applied.Programm;
import com.risk.applied.SendPac;
import com.risk.applied.User;
import com.risk.applied.db;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BegintrainActivity extends Activity {
	private String days[];
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.begintrain);
		
		db bd = new db(this);
		Programm current = new Programm(bd, User.getActivePid());
		
		TextView prog_name = (TextView) findViewById(R.id.textView1);
		TextView day = (TextView) findViewById(R.id.textView2);
		
		prog_name.setText(current.getName());
		day.setText("Выберите день программы");
		
		days = new String[current.getCountDays()];
		for (int i = 0; i < current.getCountDays(); i++) {
			days[i] = "День "+(i+1);
		}
		
		
		//SendPac[] days = new SendPac[current.getCountDays()];
		
		
		ListView lv = (ListView) findViewById(R.id.listView1);
		
		SendPac[] pac = current.getNameUprSP();
		
		
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, days);
		lv.setAdapter(adapter);
		
		
		
		
	}
}
