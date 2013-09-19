package com.risk.sk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



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

import com.risk.applied.Programm;
import com.risk.applied.Upr;
import com.risk.applied.User;
import com.risk.applied.db;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ProgrammDownloadActivity extends Activity {

	/**
	 * @param args
	 */
	
	
	//private ArrayList<HashMap<String, String>> names;
	public static String names;
	private String prg;
	private ArrayList<HashMap<String, String>> mypr;
	private HashMap<String, String> hm;
	//private ArrayAdapter<String> adapter;
	private SimpleAdapter adapter;
	ArrayList prog = new ArrayList();
	private ProgressDialog dialog;
	private db bd;
	private ListView lv;
	private static final String BOOKKEY = "id";    // Главное название, большими буквами
    private static final String PRICEKEY = "name";
	
	
	public void onCreate(Bundle savedInstanceState) {

				super.onCreate(savedInstanceState);		
		setContentView(R.layout.listoftrains);
		lv  = (ListView)findViewById(R.id.list1);
		//adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, prog);
		mypr = new ArrayList<HashMap<String,String>>();
	/*	hm = new HashMap<String, String>();
		hm.put(BOOKKEY, "1");
        hm.put(PRICEKEY, "руслан");
        mypr.add(hm);
        SimpleAdapter adapter = new SimpleAdapter(DownloadtrainsActivity.this, 
                mypr, 
                R.layout.list1, new String[]{ // массив названий
                BOOKKEY,         //верхний текст
                PRICEKEY,        //нижний теккт
                }, new int[]{    //массив форм
                R.id.text1,      //наш id TextBox'a в list.xml
                R.id.text2});    //ссылка на объект отображающий текст
		lv.setAdapter(adapter);	
		lv.setChoiceMode(lv.CHOICE_MODE_SINGLE);
		*/
		
		bd = new db(this);
		new getProgramms().execute(getResources().getString(R.string.ip_server)+"/programm/getall");
		
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		//new RequestTask().execute("http://192.168.0.53/programm/getall", );
		
		/*
		try {
			JSONObject json;
			json = new JSONObject(prg);
			JSONArray urls = json.getJSONArray("data");
			ArrayList prog = new ArrayList();
			
			  for (int i = 0; i < urls.length(); i++) {
			    	
			     /* HashMap<String, String> hm;
                  hm = new HashMap<String, String>();
                  hm.put(urls.getJSONObject(i).getString("id").toString(), urls.getJSONObject(i).getString("name").toString());
                  mypr.add(hm);
                  
				 prog.add(urls.getJSONObject(i).getString("name").toString());
			     }
			  
		
			  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, prog);
			  lv.setAdapter(adapter);
			  
			  
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	    
	
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
			//	Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(), Toast.LENGTH_SHORT).show();
				//lv.getItemAtPosition((int)id);
				hm = new HashMap<String, String>();
				hm = mypr.get((int)id);
				//System.out.println(hm.get("name"));
				//Toast.makeText(getApplicationContext(), hm.get("id"), Toast.LENGTH_SHORT).show();
				
				
				String query = hm.get("id");
				
				Programm prgm = new Programm(bd,Integer.parseInt(hm.get("id")), hm.get("name"));
				prgm.save();
				
				new RequestTask().execute("http://192.168.0.53/programm/getp", query);
		
				
			}
		});
		
		
	}
	
	
	public void saveProgramm(){
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

	
	
	

	
	class getProgramms extends AsyncTask<String, String, ArrayList> {

		@Override
		protected ArrayList doInBackground(String... params) {

			try {
				
				DefaultHttpClient hc = new DefaultHttpClient();
				ResponseHandler<String> res = new BasicResponseHandler();
				
				HttpPost postMethod = new HttpPost(params[0]);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);				
				nameValuePairs.add(new BasicNameValuePair("hash", User.getHash()));
				nameValuePairs.add(new BasicNameValuePair("uid", Integer.toString(User.getUid())));
				postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));				
				
				String response = hc.execute(postMethod, res);
				
				
				
				System.out.println(response);
			    JSONObject json = new JSONObject(response);
				JSONArray urls = json.getJSONArray("data");
				
				
				  for (int i = 0; i < urls.length(); i++) {
				    	
				     
					// prog.add(urls.getJSONObject(i).getString("name").toString());
					 hm = new HashMap<String, String>();
	                 hm.put("name", urls.getJSONObject(i).getString("name").toString());
	                 hm.put("id", urls.getJSONObject(i).getString("id").toString());
	                 mypr.add(hm);
					 
				     }
				  
			
				  return mypr;

				
				
				
				
			} catch (Exception e) {
				System.out.println("[getprogramms]Exp=" + e);
			}
			return null;
		}
		

		@Override
		protected void onPostExecute(ArrayList result) {

			dialog.dismiss();
				//System.out.println("Получили");
			
			SimpleAdapter adapter = new SimpleAdapter(ProgrammDownloadActivity.this, 
	                mypr, 
	                R.layout.list1, new String[]{ // массив названий
					PRICEKEY, 
					BOOKKEY,      //верхний текст
	                        //нижний теккт
	                }, new int[]{    //массив форм
	                R.id.text1,      //наш id TextBox'a в list.xml
	                R.id.text2});    //ссылка на объект отображающий текст
			lv.setAdapter(adapter);	
			lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
						
			dialog = new ProgressDialog(ProgrammDownloadActivity.this);
			dialog.setMessage("Загрузка данных...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			dialog.show();
			
			super.onPreExecute();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class RequestTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {

			try {
			//	System.out.println("PARAMS_>>>>>>>>"+params[0]);
				DefaultHttpClient hc = new DefaultHttpClient();
				ResponseHandler<String> res = new BasicResponseHandler();				
				HttpPost postMethod = new HttpPost(params[0]);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);				
				nameValuePairs.add(new BasicNameValuePair("query", params[1] ));	
				postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));				
				
				String response = hc.execute(postMethod, res);
				
			System.out.println("DATA IS+>>>>>>"+response.toString());
				
			    
				
				
				
				
				
				
					
					JSONObject json = new JSONObject(response);
					
					JSONArray urls = json.getJSONArray("data");
				
				
					 for (int i = 0; i < urls.length(); i++) {
						
						 Upr upr1 = new Upr(bd,
								 			urls.getJSONObject(i).getInt("id"),
								 			urls.getJSONObject(i).getString("name").toString(), 
								 			urls.getJSONObject(i).getInt("programm_id"),
								 			urls.getJSONObject(i).getInt("day"),
								 			urls.getJSONObject(i).getInt("count"),
								 			urls.getJSONObject(i).getInt("podhod"),
								 			urls.getJSONObject(i).getInt("weight"));
						 upr1.saveUpr();
						// System.out.println(urls.getJSONObject(i).getString("name").toString());
					     }
					 
					 Intent intent = new Intent(ProgrammDownloadActivity.this, ProgrammActivity.class);					 
					 startActivity(intent);
				
				
			} catch (Exception e) {
				System.out.println("Exp=" + e);
			}
			return null;
		}
		

		@Override
		protected void onPostExecute(String result) {

			dialog.dismiss();
			
				
				
			
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
						
			dialog = new ProgressDialog(ProgrammDownloadActivity.this);
			dialog.setMessage("Скачиваю...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			dialog.show();
			
			super.onPreExecute();
		}
	}
}
