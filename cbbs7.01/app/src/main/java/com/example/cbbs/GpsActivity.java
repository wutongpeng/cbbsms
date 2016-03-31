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

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;



public class GpsActivity extends Activity {
	TextView tvGps,tvTime;
	Button btCon;

	private HttpConnSoap Soap = new HttpConnSoap();
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		
		tvGps=(TextView) this.findViewById(R.id.tv_gps);
		tvTime=(TextView) this.findViewById(R.id.tv_time);
		btCon=(Button) this.findViewById(R.id.bt_gps);
		
		
		btCon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getGps();
				
			}
		});
		
	}
	


	public void getGps()
	{
		new Thread(new Runnable(){
			public void run(){
				String URL="http://10.0.2.2:11125/Service1.asmx";  
				String Namespace = "http://tempuri.org/";
				String method= "getGps"; 
			    String soapAction= "http://tempuri.org/getGps" ; 
				//1
				SoapObject requst=new SoapObject(Namespace, method);
				//requst.addProperty("mobileCode",p);
				//requst.addProperty("userID", "");
				//2
				SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER10);
				envelope.dotNet=true;
				envelope.setOutputSoapObject(requst);  
				//3
				HttpTransportSE ht=new HttpTransportSE(URL);
				envelope.bodyOut=requst;
				//5
				try{
					ht.call(soapAction, envelope);
					//6
					String result;
					if(envelope.getResponse() != null){
						SoapObject object=(SoapObject) envelope.bodyIn;
						Log.i("Tag", object.toString());
						result = object.getProperty(0).toString();
					}else{
						result = "无返回值";
					}
				    Message msg=new Message();
				    msg.obj = result;
				    handler.sendMessage(msg);
				}catch (IOException e){
					e.printStackTrace();
				}catch (XmlPullParserException e){
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	
	Handler handler=new Handler(){
		public void handleMessage(Message msg){
			super.handleMessage(msg);
		
			Log.i("Tag",msg.obj.toString());
			tvGps.append(msg.obj.toString() + "\n");
			tvTime.append(Soap.getTime());
		}		
	};
	
}



