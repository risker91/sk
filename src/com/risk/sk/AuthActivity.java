package com.risk.sk;


import com.risk.applied.*;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AuthActivity extends Activity {

	public EditText login;
	public EditText pass;
	private ProgressDialog dialog;
	private InputStream is;
	public static String st;
	MenuActivity url;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		db MyDb = new db(this);	
		User MyUser = new User(MyDb);
		
		System.out.println("name is "+MyUser.getUName());
		if(MyUser.getUName()!=null){			
			
			Intent intent = new Intent(AuthActivity.this, MenuActivity.class);			
			startActivity(intent);
		}
		
		setContentView(R.layout.auth);		
		Button btn = (Button) findViewById(R.id.button1);	
		
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				login = (EditText) findViewById(R.id.editText1);
				pass  = (EditText) findViewById(R.id.editText2);
				new RequestTask().execute(getResources().getString(R.string.ip_server)+"/auth");
			}
		});
	}
	
	public  boolean auth(String result ){
		
		System.out.println("[auth]name is "+User.getUName());
		
		try {
			
			
			JSONObject json = new JSONObject(result);
		
			if(json.getString("type").toString().equals("error"))  return false;
			
			User.createUser(json.getString("username").toString(), json.getString("firstname").toString(), json.getInt("uid"), json.getString("hash").toString());
				
				if(User.isLogin() == 1) {				
				Intent intent = new Intent(AuthActivity.this, MenuActivity.class);			
				startActivity(intent);
			}
			
			//User user =new User(MyDb, json.getString("username").toString(), json.getString("first_name").toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
			return false;
	}
	

	class RequestTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {

			try {
				
				DefaultHttpClient hc = new DefaultHttpClient();
				ResponseHandler<String> res = new BasicResponseHandler();
				
				HttpPost postMethod = new HttpPost(params[0]);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);				
				nameValuePairs.add(new BasicNameValuePair("login", login.getText().toString()));				
				nameValuePairs.add(new BasicNameValuePair("pass", pass.getText().toString()));				
				postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));				
				
				String response = hc.execute(postMethod, res);
					
				if(auth(response))
				{
					Intent intent = new Intent(AuthActivity.this, MenuActivity.class);
					startActivity(intent);
					
				} else
				{
					//Error AUTH
				}
				
			} catch (Exception e) {
				System.out.println("Exp=" + e);
			}
			return null;
		}
		

		@Override
		protected void onPostExecute(String result) {

			dialog.dismiss();
				System.out.println("LOGIN>>>>>>>>"+login.getText().toString());
				System.out.println("PASS>>>>>>>>"+pass.getText().toString());
			
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
						
			dialog = new ProgressDialog(AuthActivity.this);
			dialog.setMessage("Авторизация...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			dialog.show();
			super.onPreExecute();
		}
	}
}