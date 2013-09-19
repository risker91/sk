package com.risk.sk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;


public class Newmenu extends Activity{


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
	
	
	public void settings(View view){
		
		Intent intent = new Intent(Newmenu.this, Preferences.class);			
		startActivity(intent);
		
		
	}
}