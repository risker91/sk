package com.risk.sk;

import java.util.ArrayList;
import java.util.HashMap;
import com.risk.applied.*;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class UprinfoActivity extends Activity {

	private ArrayList<HashMap<String, String>> mypr;
	private HashMap<String, String> hm;
	//private ArrayAdapter<String> adapter;
	private SimpleAdapter adapter;
	private ListView lv;
	ArrayList prog = new ArrayList();
	
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.upr);
		lv = (ListView)findViewById(R.id.uprinfo);
		Upr myUpr = (Upr) getIntent().getParcelableExtra(Upr.class.getCanonicalName());
		Log.d("myd", myUpr.getTitle());
		
		
		mypr = new ArrayList<HashMap<String,String>>();
		hm = new HashMap<String, String>();		
		hm.put("type", "Название:");
        hm.put("val", myUpr.getTitle());
        mypr.add(hm);
        hm = new HashMap<String, String>();		
        hm.put("type", "Подходов:");
        hm.put("val", Integer.toString(myUpr.getPodhod()));
        mypr.add(hm);
        hm = new HashMap<String, String>();		
        hm.put("type", "Повторений:");
        hm.put("val", Integer.toString(myUpr.getCount()));
        mypr.add(hm);
		
		
        SimpleAdapter adapter = new SimpleAdapter(UprinfoActivity.this, 
                mypr, 
                R.layout.uprinfo,
                new String[]{"type", "val"},
				new int[]{  R.id.text1,  R.id.text2});    //ссылка на объект отображающий текст
        lv.setAdapter(adapter);	
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		
	}
	
	
	
	

}
