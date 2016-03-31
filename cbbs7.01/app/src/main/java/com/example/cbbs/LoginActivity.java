package com.example.cbbs;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class LoginActivity extends Activity {
	Button bt;
	EditText et_pwd,et_name;
	TextView textView5;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		et_name=(EditText) this.findViewById(R.id.et_user);
		et_pwd=(EditText) this.findViewById(R.id.et_pwd);
		textView5=(TextView) this.findViewById(R.id.textView5);
		bt=(Button) this.findViewById(R.id.bt_login);
		
        bt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 //Intent intent=new Intent(LoginActivity.this,MainActivity.class);
				 //startActivity(intent);
				String name=et_name.getText().toString().trim();
				String pwd=et_pwd.getText().toString().trim();
				Log.i("Tag",name);
				Log.i("Tag",pwd);
				getLogin(name,pwd);
			}
		});
    }
	
	public void getLogin(final String name,final String pwd)
	{
		new Thread(new Runnable(){
			public void run(){
				String URL="http://10.0.2.2:11125/Service1.asmx";  
				String Namespace = "http://tempuri.org/";
				String method= "getLogin"; 
			    String soapAction= "http://tempuri.org/" + method;  
				//1
				SoapObject requst=new SoapObject(Namespace, method);
				requst.addProperty("name",name);
				requst.addProperty("pwd", pwd);
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
				//textView5.append(msg.obj.toString() + "\n");
			String s="true";			
			if(msg.obj.toString().equals(s)){
				Intent intent=new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				textView5.append(msg.obj.toString() + "登陆成功"+"\n");
			}else{
				textView5.append(msg.obj.toString() + "登陆失败"+"\n");
			}
			
		}		
	};
	
}
