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

public class SmokeActivity extends Activity {
	// ��������
	TextView textViewTemp;// ��ʾ�¶�
	TextView textViewTime;// ��ʾ�ɼ�ʱ��
	EditText editTextDeviceId;// Ψһʶ���
	Button buttonRead, buttonTempreturn;// �¶Ȳɼ���ť

	SharedPreferences pref; // ����SharedPreferences
	private Intent intent;

	Return retu = new Return();
	private ShareDataActivity shareData;

	// private String deviceId="01";

	private String methodname = "getSmoke";
	private Handler handler;
	private Button TemButton;
	// ���������ַ
	public static String serviceUrl = "";

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.somke);
		// ShareDataActivity.getVersion();
		// �õ����ؼ�����
	  final ManageSQL manageSQL=new ManageSQL();
		textViewTemp = (TextView) findViewById(R.id.TemEdit);
		textViewTime = (TextView) findViewById(R.id.temDateEdit);
		buttonRead = (Button) findViewById(R.id.TemReading);
		//buttonTempreturn = (Button) findViewById(R.id.temReturn);
		TemButton =   (Button) findViewById(R.id.temManage);
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
					Toast.makeText(SmokeActivity.this, "�ɼ���Ϣʧ��",
							Toast.LENGTH_SHORT).show();
				} else {
					textViewTemp.setText(retu.getValue() + "ppm");
					 
					MySQLite mySQLite = MainActivity.mySQLite3; 
					manageSQL.insertT(mySQLite.getReadableDatabase(), retu.getValue() + "ppm(����)", shareData.getTime());
					textViewTime.setText(shareData.getTime());
				}
				super.handleMessage(msg);
			}
		};
		TemButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				intent = new Intent(SmokeActivity.this,
						SmokeActivityManage.class);
				startActivity(intent);

			}
		});

		// Ϊ�¶Ȳɼ���ť���Ӽ�����
		buttonRead.setOnClickListener(new OnClickListener() {
			 

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final String deviceId = "08";

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						retu = shareData.GetData(deviceId, methodname);

						if (retu == null) {

							Message retuMessage = handler.obtainMessage();// �õ���Ϣ����

							Bundle bundle = new Bundle();
							bundle.putString("data", "ERROR");// ���������map
							retuMessage.setData(bundle);// �����ݷŽ�ȥ
							retuMessage.sendToTarget();// ���͵�handerlaer

							return;
						} else {
							String dateString = retu.getValue();
							Message retuMessage = handler.obtainMessage();// �õ���Ϣ����
							retuMessage.obj = retu;
							Bundle bundle = new Bundle();// ���Դ洢�Ƚϴ����� ������
							bundle.putString("data", dateString);// ���������map
							retuMessage.setData(bundle);// �����ݷŽ�ȥ
							retuMessage.sendToTarget();// ���͵�handerlaer

							Log.e("hander", "�߳̿�ʼ��" + "retuMessage:"
									+ retuMessage + "retu��" + dateString);
						}

					}
				});
				thread.start( );
			}
		});

		// Ϊ���ذ�ť����
		/*buttonTempreturn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ������ذ�ť�򷵻ص�������
				intent = new Intent(SmokeActivity.this,
						MainActivity.class);
				startActivity(intent);
			}
		});*/
	}

}