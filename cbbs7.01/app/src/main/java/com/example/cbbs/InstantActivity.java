package com.example.cbbs;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cbbs.control.HttpConnSoap;

import java.util.ArrayList;


public class InstantActivity extends Activity {
	TextView tvTem,tvHum,tvSmo,tvTime;
	Button btInstantCon;
	private ArrayList<String> arrayList;
	private ArrayList<String> brrayList;
	private ArrayList<String> crrayList;
	private HttpConnSoap Soap = new HttpConnSoap();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instant);
		tvTem=(TextView) this.findViewById(R.id.tv_tem);
		tvHum=(TextView) this.findViewById(R.id.tv_hum);
		tvSmo=(TextView) this.findViewById(R.id.tv_smo);
		tvTime=(TextView) this.findViewById(R.id.tv_instant_time);
		btInstantCon=(Button) this.findViewById(R.id.bt_Instant);
		
		btInstantCon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//getTemperature();
				//getHumidity();
				//getSmoke();
				getInstantInfo();
			}
		});
			
		}
	


	public void getInstantInfo() {

		new Thread(new Runnable(){
			public void run(){
				arrayList = new ArrayList<String>();
				brrayList = new ArrayList<String>();
				crrayList = new ArrayList<String>();
				arrayList.clear();
				brrayList.clear();
				crrayList.clear();
				crrayList = Soap.GetWebServre("selectInstantInfor", arrayList, brrayList);

				Message msg=new Message();
				msg.obj = crrayList;
				//System.out.print("msg"+msg);
				handler.sendMessage(msg);

			}
		}).start();
	}



	Handler handler=new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.i("Tag", msg.obj.toString());

			crrayList = new ArrayList<String>();
			crrayList = (ArrayList<String>) msg.obj;
			for (int j = 0; j < crrayList.size(); j += 3) {

				String tem = crrayList.get(j);
				String hum = crrayList.get(j + 1);
				String smo = crrayList.get(j + 2);

				tvTem.setText(tem.toString() + "\n");
				tvHum.setText(hum.toString() + "\n");
				tvSmo.setText(smo.toString() + "\n");
				tvTime.setText(" ");
				tvTime.setText(Soap.getTime());

				//tvTem.append(msg.obj.toString() + "\n");
			}
			}
		};



}
