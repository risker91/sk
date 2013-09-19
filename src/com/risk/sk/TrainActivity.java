package com.risk.sk;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.risk.applied.*;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class TrainActivity extends FragmentActivity {

				private String name_pr;
				ViewPager pager;
				PagerAdapter pagerAdapter;
				static int PAGE_COUNT = 3;
				public Programm prg;
				
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.train);
		db bd = new db(this);
		ListView lv = (ListView)findViewById(R.id.listView1);
		//TextView titleProgramm = (TextView)findViewById(R.id.textView1);
		name_pr = getIntent().getStringExtra("name_prg");
	//	titleProgramm.setText(name_pr);
		
		
		
		// HashMap<String, String> hm = new HashMap<String, String>();
		  //  String[] day1 = {"Турник", "Присед"};
		 //   String[] day2 = {"Брусья", "Жим"};
		  //  String[] day3 = {"Присед", "Становая"};
		    
		 //   dd.add(day1);
		 //   dd.add(day2);
		 //   dd.add(day3);
		    
		   
		
	
		Programm prg = new Programm(bd, name_pr);
	
		PAGE_COUNT = prg.getCountDays();
		String[] uprs = new String[prg.uprs.length];
		
		Vector<ArrayList<String>> dd = new Vector();
		ArrayList<String> day1 = new ArrayList<String>();
		ArrayList<String> day2 = new ArrayList<String>();
		ArrayList<String> day3 = new ArrayList<String>();
		
		for (int i = 0; i < prg.uprs.length; i++) {	
			
			switch (prg.uprs[i].getDay()) {
			
			case 1: day1.add(prg.uprs[i].getTitle());
						break;
						
			case 2: day2.add(prg.uprs[i].getTitle());
			break;
			
			case 3: day3.add(prg.uprs[i].getTitle());
			break;	
			}
			dd.add(day1);
			dd.add(day2);
			dd.add(day3);
		//	uprs[i] = prg.uprs[i].getTitle();
		}
		
		    pager = (ViewPager) findViewById(R.id.pager);
		    pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), dd, prg);
		    pager.setAdapter(pagerAdapter);
		
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {

		      @Override
		      public void onPageSelected(int position) {
		        Log.d("test", "onPageSelected, position = " + position);
		      }

		      @Override
		      public void onPageScrolled(int position, float positionOffset,
		          int positionOffsetPixels) {
		      }

		      @Override
		      public void onPageScrollStateChanged(int state) {
		      }
		    });
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, uprs);
		//lv.setAdapter(adapter);
		
/*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
			
				//Toast.makeText(getApplicationContext(), hm.get("id"), Toast.LENGTH_SHORT).show();
				 Intent intent = new Intent(TrainActivity.this, UprinfoActivity.class);		
				 intent.putExtra(Upr.class.getCanonicalName(), prg.uprs[(int)id]);
				 startActivity(intent);
				
				
			}
		});
		
		*/
		
		
	
		
		
		
	}
	
	
	  private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		  
		  Vector<ArrayList<String>> days;
		  Programm ppr;

	    public MyFragmentPagerAdapter(FragmentManager fm, Vector<ArrayList<String>> ddd, Programm lol) {
	      super(fm);
	      days = ddd;  
	      ppr = lol;
	    }

	    @Override
	    public Fragment getItem(int position) {
	      return PageFragment.newInstance(position, days, ppr);
	    }

	    @Override
	    public int getCount() {
	      return PAGE_COUNT;
	    }
	    
	    @Override
	    public CharSequence getPageTitle(int position) {
	      return "Day " + (position+1);
	    }

	  }
}