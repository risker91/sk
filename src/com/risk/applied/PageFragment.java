package com.risk.applied;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.risk.sk.R;
import com.risk.sk.TrainActivity;
import com.risk.sk.R.id;
import com.risk.sk.R.layout;
import com.risk.sk.UprinfoActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PageFragment extends Fragment {
  
  static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
  
  int pageNumber;
  Vector<String[]> days;
  int backColor;
  ArrayList<String> con_;
  static Programm ppr;
  
  
  public static PageFragment newInstance(int page, Vector<ArrayList<String>> con, Programm pbr) {
	  ppr = pbr;
    PageFragment pageFragment = new PageFragment();
    Bundle arguments = new Bundle();
    arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
    //ArrayList<String>
  // String[] uprs = con.get(page);
    ArrayList<String> uprs = con.get(page);
   System.out.println("TAG"+uprs);
  //  arguments.putStringArray("con", uprs);
    arguments.putStringArrayList("con", uprs);
    pageFragment.setArguments(arguments);
    return pageFragment;
  }
  
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    con_ 	   = getArguments().getStringArrayList("con");
    System.out.println("TAG2"+ con_);
    
    Random rnd = new Random();
    backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment, null);
    
  
    
    ListView lv = (ListView) view.findViewById(R.id.listView1);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, con_);
    lv.setAdapter(adapter);
    
  //  Upr nn = ppr.findByName("Становая");
  //  if(nn!=null)
	//System.out.println(nn.getTitle());
    
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
		public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
		
			//Toast.makeText(getApplicationContext(), hm.get("id"), Toast.LENGTH_SHORT).show();
			
			
			 Intent intent = new Intent(getActivity(), UprinfoActivity.class);
		//	 intent.putExtra(Upr.class.getCanonicalName(),ppr.findByName((String)((TextView) itemClicked).getText()));
			startActivity(intent);
			
			
		}
	});
    
    
    return view;
  }
}