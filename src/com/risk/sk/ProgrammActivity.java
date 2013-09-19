package com.risk.sk;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;


public class ProgrammActivity extends TabActivity{
//	private static final String TAG = SelfContainedTabHost.class.getSimpleName(); 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		host = getTabHost();
	
		Intent intent = new Intent(this, ProgrammListActivity.class);
		host.addTab(host.newTabSpec("two").setIndicator("Скаченные").setContent(intent));
		intent = new Intent(this, ProgrammDownloadActivity.class);
		host.addTab(host.newTabSpec("three").setIndicator("Скачать").setContent(intent));
	}

	@Override
	protected void onResume() {
		super.onResume();
		receiver = new TabChangeReceiver();
		registerReceiver(receiver, new IntentFilter("com.risk.sc"), null, mHandler);
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	};

    protected Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
            	case SWITCH_TAB:
            		Log.i("handler", "using the handler");
                    host.setCurrentTab(msg.arg1);            		
            		break;
            }
        }
    };

    public class TabChangeReceiver extends android.content.BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			int intExtra = intent.getIntExtra("tab", 0);
			Log.i(TabChangeReceiver.class.getSimpleName(), "Recieved broadcast with extra=[" + intExtra + "]");

			mHandler.sendMessage(mHandler.obtainMessage(SWITCH_TAB, intExtra, 0));
		}
	}

	private TabHost host;
	public static final int SWITCH_TAB = 2545;
	protected static TabChangeReceiver receiver;

}