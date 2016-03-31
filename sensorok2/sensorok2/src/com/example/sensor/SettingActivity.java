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
	//�������༭��ؼ�
	private EditText editTextIp;
	private EditText editTextPort;
	private EditText editTextUrl;
	private Button btn1;
	//����ȷ����ť
	private Button buttonEnsure;
	//����SharedPreferences����
	public static SharedPreferences spf;
	
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_setting);
		
		//�õ������ؼ�����
		editTextIp = (EditText)findViewById(R.id.ip);
		editTextPort =(EditText)findViewById(R.id.port);
		editTextUrl = (EditText)findViewById(R.id.url); 
		buttonEnsure =(Button)findViewById(R.id.settingFinishButton);
		btn1 = (Button) findViewById(R.id.btn1);
		intiView();
		//Ϊȷ����ť��Ӽ�����
		buttonEnsure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��ȡ�༭���е�����
				String etip = editTextIp.getText().toString();
				String etport =editTextPort.getText().toString();
				String eturl =editTextUrl.getText().toString();
				 
				//ȡ��SharedPreferences.Editor����
				SharedPreferences.Editor editor =spf.edit();
				//�洢����
				editor.putString("IP", etip);
				editor.putString("Port", etport);
				editor.putString("Url", eturl);
				//�ύ
				editor.commit()  ;
				
				spf =getSharedPreferences("mydata",Context.MODE_PRIVATE);
				//��mydata�ļ�����Ӧ�ļ���Ӧ��ֵ��Ϊ�����ʼ��Ϊ�ڶ���������ֵ
				String ip = spf.getString("IP", "");
				String port = spf.getString("Port", "");
				String url = spf.getString("Url", "");
				
				if (ip.equals("")||port.equals("")||url.equals("")){
					Toast.makeText(SettingActivity.this, "����ʧ��", Toast.LENGTH_SHORT).show()  ;
					
				}else {
					Toast.makeText(SettingActivity.this, "���óɹ�", Toast.LENGTH_SHORT).show();
			        intent =new Intent(SettingActivity.this,MainActivity.class);
	    		    startActivity(intent);
				  
				}
		
			}
		});
		btn1.setOnClickListener(new View.OnClickListener(){ 
		      @Override 
		      public void onClick(View v){

		          //Intent��һ������ʱ�󶨣�run-time binding�����ƣ������ڳ������й���������������ͬ�������

		          //page1Ϊ��ǰ����ӵ��࣬������AndroidManifest.xml����ӻ�¼�(<activity android:name="page1"></activity>),�ڴ����Դ������ļ������£� 
		          Intent i = new Intent(SettingActivity.this , Sysset.class);

		          ////���� 
		          startActivity(i); 
		      } 
		});
		
	}
		private void intiView(){
			//ȡ��SharedPreferences����
			spf =getSharedPreferences("mydata",Context.MODE_PRIVATE);
			//��mydata�ļ�����Ӧ�ļ���Ӧ��ֵ��Ϊ�����ʼ��Ϊ�ڶ���������ֵ
			  String ip = spf.getString("IP", "");
			  String port = spf.getString("Port", "51000");
			  String url = spf.getString("Url", "http://192.168.1.11:8080/sensorWebservice/services/sensorService");
			  //���ø����ؼ���ʾ������
			  editTextIp.setText(ip);
			  editTextPort.setText(port);
			  editTextUrl.setText(url);
		
	}

}
