package com.example.sensor;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.widget.Button;
public class SettingActivity extends Activity {
	//声明各编辑框控件
	private EditText editTextIp;
	private EditText editTextPort;
	private EditText editTextUrl;
	private Button btn1;
	//声明确定按钮
	private Button buttonEnsure;
	//声明SharedPreferences对象
	public static SharedPreferences spf;
	
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_setting);
		
		//得到各个控件对象
		editTextIp = (EditText)findViewById(R.id.ip);
		editTextPort =(EditText)findViewById(R.id.port);
		editTextUrl = (EditText)findViewById(R.id.url); 
		buttonEnsure =(Button)findViewById(R.id.settingFinishButton);
		btn1 = (Button) findViewById(R.id.btn1);
		intiView();
		//为确定按钮添加监听器
		buttonEnsure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//获取编辑框中的内容
				String etip = editTextIp.getText().toString();
				String etport =editTextPort.getText().toString();
				String eturl =editTextUrl.getText().toString();
				 
				//取得SharedPreferences.Editor对象
				SharedPreferences.Editor editor =spf.edit();
				//存储内容
				editor.putString("IP", etip);
				editor.putString("Port", etport);
				editor.putString("Url", eturl);
				//提交
				editor.commit()  ;
				
				spf =getSharedPreferences("mydata",Context.MODE_PRIVATE);
				//从mydata文件中相应的键对应的值，为空则初始化为第二个参数的值
				String ip = spf.getString("IP", "");
				String port = spf.getString("Port", "");
				String url = spf.getString("Url", "");
				
				if (ip.equals("")||port.equals("")||url.equals("")){
					Toast.makeText(SettingActivity.this, "配置失败", Toast.LENGTH_SHORT).show()  ;
					
				}else {
					Toast.makeText(SettingActivity.this, "配置成功", Toast.LENGTH_SHORT).show();
			        intent =new Intent(SettingActivity.this,MainActivity.class);
	    		    startActivity(intent);
				  
				}
		
			}
		});
		btn1.setOnClickListener(new View.OnClickListener(){ 
		      @Override 
		      public void onClick(View v){

		          //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。

		          //page1为先前已添加的类，并已在AndroidManifest.xml内添加活动事件(<activity android:name="page1"></activity>),在存放资源代码的文件夹下下， 
		          Intent i = new Intent(SettingActivity.this , Sysset.class);

		          ////启动 
		          startActivity(i); 
		      } 
		});
		
	}
		private void intiView(){
			//取得SharedPreferences对象
			spf =getSharedPreferences("mydata",Context.MODE_PRIVATE);
			//从mydata文件中相应的键对应的值，为空则初始化为第二个参数的值
			  String ip = spf.getString("IP", "");
			  String port = spf.getString("Port", "51000");
			  String url = spf.getString("Url", "http://192.168.1.11:8080/sensorWebservice/services/sensorService");
			  //设置各个控件显示的内容
			  editTextIp.setText(ip);
			  editTextPort.setText(port);
			  editTextUrl.setText(url);
		
	}

}
