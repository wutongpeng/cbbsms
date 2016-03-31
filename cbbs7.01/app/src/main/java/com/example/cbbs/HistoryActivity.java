package com.example.cbbs;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.cbbs.control.HttpConnSoap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryActivity extends Activity {
	private ListView listView;
	private SimpleAdapter adapter;
	private Button bt_history;
	private ArrayList<String> arrayList;
	private ArrayList<String> brrayList;
	private ArrayList<String> crrayList;
	private HttpConnSoap Soap = new HttpConnSoap();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		bt_history=(Button) this.findViewById(R.id.bt_History);
		listView =(ListView) this.findViewById(R.id.listView);
		//dbUtil=new Dbutil();
		
		bt_history.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getAllInfo();
			}
		});
	}
		
	public void getAllInfo() {
		
		new Thread(new Runnable(){
			public void run(){				
		        final List<Map<String, String>> data = new ArrayList<Map<String, String>>();				
			    Map map1=new HashMap<String,String>();
				map1.put("Cno", "Cno");
				map1.put("Cname", "Cname");
				map1.put("Cnum", "Cnum");
				data.add(map1);
                System.out.println("date1"+data);				
                arrayList = new ArrayList<String>();
                brrayList = new ArrayList<String>();
                crrayList = new ArrayList<String>();
				arrayList.clear();
				brrayList.clear();
				crrayList.clear();	
				crrayList = Soap.GetWebServre("selectAllCargoInfor", arrayList, brrayList);
                System.out.println("crrayList"+crrayList);							
				for (int j = 0; j < crrayList.size(); j += 3) {
					Map map2=new HashMap<String,String>();
					map2.put("Cno", crrayList.get(j));
					map2.put("Cname", crrayList.get(j + 1));
					map2.put("Cnum", crrayList.get(j + 2));
					data.add(map2);
                    System.out.println("data2"+data);
                    //final List<Map<String, String>> data2 = new ArrayList<Map<String, String>>();
				}
				Message msg=new Message();
			    msg.obj = data;
System.out.print("msg"+msg);
			    handler.sendMessage(msg);
			
		    }			
	        }).start();
	}	
	
	Handler handler=new Handler(){
		public void handleMessage(Message msg){
			super.handleMessage(msg);
            Log.i("Tag-handler-msg",msg.obj.toString());
            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            data = (List<Map<String, String>>) msg.obj;
            
			listView.setVisibility(View.VISIBLE);
			adapter = new SimpleAdapter(
					HistoryActivity.this, 
					data, 
					R.layout.history, 
					new String[] { "Cno", "Cname", "Cnum" }, 
					new int[] { R.id.txt_Cno, R.id.txt_Cname, R.id.txt_Cnum });

			listView.setAdapter(adapter);
		}		
	};
	
	
	
}

