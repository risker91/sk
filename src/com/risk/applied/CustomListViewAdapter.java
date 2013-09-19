package com.risk.applied;

import java.util.ArrayList;

import com.risk.sk.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListViewAdapter extends BaseAdapter {
	
	private SendPac[] objects;
	Context ctx;
	LayoutInflater lInflater;

	public CustomListViewAdapter(Context context,SendPac[] pac) {
		//super(context, textViewResourceId);
		 ctx = context;
		    objects = pac;
		    lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {		
		 return objects.length;
	}

	@Override
	public Object getItem(int position) {		
		return objects[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = convertView;
	    if (view == null) {
	      view = lInflater.inflate(R.layout.item, parent, false);
	      
	      SendPac p = getSendPac(position);
	      
	      ((TextView) view.findViewById(R.id.textView1)).setText(p.name);
	      ((TextView) view.findViewById(R.id.textView2)).setText(p.id);
	    }
	    
	    return view;
	}

	
	
	SendPac getSendPac(int position) {
        return ((SendPac) getItem(position));
      }

	public void setDropDownViewResource(int simpleSpinnerDropdownItem) {
		
		lInflater.inflate(simpleSpinnerDropdownItem, null);
		
		   //view = lInflater.inflate(simpleSpinnerDropdownItem, parent, false);
		
		
	}
	
	
}
