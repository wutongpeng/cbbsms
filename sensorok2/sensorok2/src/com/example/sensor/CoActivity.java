package com.example.sensor;

import com.example.sensor.model.Return;
import com.example.sensor.tool.ManageSQL;
import com.example.sensor.tool.MySQLite;
import com.example.sensor.ShareDataActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CoActivity extends Activity {
	// 变量声明
	TextView textViewTemp;// 显示温度
	TextView textViewTime;// 显示采集时间
	EditText editTextDeviceId;// 唯一识别号
	Button buttonRead, buttonTempreturn;// 温度采集按钮
	private Button bt1;
	SharedPreferences pref; // 声明SharedPreferences
	private Intent intent;

	Return retu = new Return();
	private ShareDataActivity shareData;

	// private String deviceId="01";

	private String methodname ="getCO2";
	private Handler handler;
	
	// 声明网络地址
	public static String serviceUrl = "";

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.co);
		// ShareDataActivity.getVersion();
		// 得到各控件对象
	  final ManageSQL manageSQL=new ManageSQL();
		textViewTemp = (TextView) findViewById(R.id.TemEdit);
		textViewTime = (TextView) findViewById(R.id.temDateEdit);
		buttonRead = (Button) findViewById(R.id.TemReading);
		//buttonTempreturn = (Button) findViewById(R.id.temReturn);
		bt1 =(Button) findViewById(R.id.coManage);
		// editTextDeviceId = (EditText) findViewById(R.id.TemDeviceId);
		//

		shareData = new ShareDataActivity();
		handler = new Handler() {

			@SuppressLint("HandlerLeak")
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stu
				// Bundle bundle = msg.getData();
				// String data = bundle.getString("data");
				retu = (Return) msg.obj;
				if (retu == null) {
					Toast.makeText(CoActivity.this, "采集信息失败",
							Toast.LENGTH_SHORT).show();
				} else {
					textViewTemp.setText(retu.getValue() + "‰");
					 
					MySQLite mySQLite = MainActivity.mySQLite5; 
					manageSQL.insertT(mySQLite.getReadableDatabase(), retu.getValue() + "‰(CO₂)", shareData.getTime());
					textViewTime.setText(shareData.getTime());
				}
				super.handleMessage(msg);
			}
		};
	bt1.setOnClickListener(new View.OnClickListener()
	{
		public void onClick(View v){

        //Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。

        //page1为先前已添加的类，并已在AndroidManifest.xml内添加活动事件(<activity android:name="page1"></activity>),在存放资源代码的文件夹下下， 
        Intent i = new Intent(CoActivity.this , CoActivityManage.class);

        ////启动 
        startActivity(i); 
    } 
});

		// 为温度采集按钮添加监听器
		buttonRead.setOnClickListener(new OnClickListener() {
			 

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String deviceId = "09";

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						retu = shareData.GetData(deviceId, methodname);

						if (retu == null) {

							Message retuMessage = handler.obtainMessage();// 得到消息队列

							Bundle bundle = new Bundle();
							bundle.putString("data", "ERROR");// 把他想想成map
							retuMessage.setData(bundle);// 把数据放进去
							retuMessage.sendToTarget();// 发送到handerlaer

							return;
						} else {
							String dateString = retu.getValue();
							Message retuMessage = handler.obtainMessage();// 得到消息队列
							retuMessage.obj = retu;
							Bundle bundle = new Bundle();// 可以存储比较大类型 的数据
							bundle.putString("data", dateString);// 把他想想成map
							retuMessage.setData(bundle);// 把数据放进去
							retuMessage.sendToTarget();// 发送到handerlaer

							Log.e("hander", "线程开始了" + "retuMessage:"
									+ retuMessage + "retu：" + dateString);
						}

					}
				});
				thread.start( );
			}
		});

		// 为返回按钮添加
	/*	buttonTempreturn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 点击返回按钮则返回到主界面
				intent = new Intent(CoActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});*/
	}

}
