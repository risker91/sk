package com.risk.applied;

import java.util.ArrayList;

import com.risk.sk.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomArrayList extends ArrayAdapter{
	
	
	private SendPac[] obj;
	private String[] names;
	LayoutInflater lInflater;
	
	
	
	
	public CustomArrayList(Context context, int textViewResourceId,
			SendPac[] objects) {
		
	
		/*	lols = objects;
			
			if(lols.size()<20){
				names = new String[lols.size()];
			for (int i = 0; i < lols.size(); i++) {
				names[i] = lols.get(i).name;
			}
			}
		*/
		
		
		super(context, textViewResourceId, objects);
		obj = objects;
		lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String getItem(int position) {		
		return obj[position].name;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	//	System.out.println("getView()");
		View view = convertView;
	    if (view == null) {
	 
	      view = lInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
	  
	      SendPac p = obj[position];
	      System.out.println("sendp");
	      ((TextView) view.findViewById(android.R.id.text1)).setText(p.name);
	      
	      
	      
	    }
	    System.out.println("return view");
	    return view;
	}

	
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return Integer.parseInt(obj[position].id);		
	}
	/*
	SendPac getSendPac(int position) {
        return ((SendPac) getItem(position));
      }
      */

}
