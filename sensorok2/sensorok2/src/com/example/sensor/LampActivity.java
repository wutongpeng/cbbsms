package com.example.sensor;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParser;

import com.example.sensor.model.Return;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class LampActivity extends Activity {
	// 声明各控件
	private TextView tv1, lampDate;
	private ImageView imageView;
	EditText editTextDeviceId;
	private Button buttonopenLamp, buttoncloseLamp, buttonlampreturn;

	SharedPreferences pref; // 声明SharedPreferences
	// 跳转
	private Intent intent;

	// 声明网络地址
	public static String serviceUrl = "";
	private Handler handler;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lamp);

		pref = SettingActivity.spf;

		// 得到个控件
		tv1 = (TextView) findViewById(R.id.lampStatus);// 灯的状态
		lampDate = (TextView) findViewById(R.id.lampDate);// 操作时间
		imageView = (ImageView) findViewById(R.id.lampPictrue);// 灯的显示图标片
		buttonopenLamp = (Button) findViewById(R.id.openLamp);// 开灯按钮
		buttoncloseLamp = (Button) findViewById(R.id.closeLamp);// 关灯操作
		buttonlampreturn = (Button) findViewById(R.id.lampreturn);// 返回按钮
	 
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.obj.equals("true") && msg.arg1 == 1) {
					tv1.setText("开");
					imageView.setImageResource(R.drawable.openedlamp);
					buttonopenLamp.setClickable(false);// 开灯按钮设置为：不可点击
					buttoncloseLamp.setVisibility(View.VISIBLE);// 关灯按钮显示：可见
					buttoncloseLamp.setClickable(true);// 关灯按钮设置为：可点击操作
					lampDate.setText(getTime());
					Toast.makeText(getApplicationContext(), "成功",
							Toast.LENGTH_SHORT).show();
				} else if (msg.obj.equals("true") && msg.arg1 == 0) {
					tv1.setText("关");
					imageView.setImageResource(R.drawable.closedlamp);
					buttoncloseLamp.setClickable(false);// 关灯按钮设置为：不可点击
					buttonopenLamp.setVisibility(View.VISIBLE);// 开灯按钮显示：可见
					buttonopenLamp.setClickable(true);// 开灯按钮设置为：可点击操作
					lampDate.setText(getTime());
				}

				else {
					Toast.makeText(getApplicationContext(), "失败",
							Toast.LENGTH_SHORT).show();
				}
				super.handleMessage(msg);
			}
		};

		// 为返回按钮添加
		/*buttonlampreturn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 点击返回按钮则返回到主界面
				intent = new Intent(LampActivity.this, MainActivity.class);
				startActivity(intent);
				 

			}
		});*/

	}

	/**
	 * 开灯事件
	 * 
	 * @param v
	 */
	public void clickOpenLamp(View v) {
		try {
			if (pref.getString("IP", "") == null
					|| pref.getString("IP", "").equals("")) {
				Toast.makeText(getApplicationContext(), "请先配置",
						Toast.LENGTH_SHORT).show();
				return;
			}
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					String deviceId = "65";
					String operationContent = "1";
					String reString = SetSwitch(operationContent, deviceId);
					Message message = handler.obtainMessage();
					message.obj = reString;
					message.arg1 = 1;
					message.sendToTarget();
				} 
			});
			thread.start();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 关灯事件
	 * 
	 * @param v
	 */
	public void clickCloseLamp(View v) {
		try {

			if (pref.getString("IP", "") == null
					|| pref.getString("IP", "").equals("")) {
				Toast.makeText(getApplicationContext(), "请先配置",
						Toast.LENGTH_SHORT).show();
				return;
			}
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					String deviceId = "65";
					String operationContent = "2";
					String reString = SetSwitch(operationContent, deviceId);
					Message message = handler.obtainMessage();
					message.obj = reString;
					message.sendToTarget();
				}
			});
			thread.start();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@SuppressLint("SimpleDateFormat")
	public String getTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy/MM/dd hh:mm:ss");
		String dd = simpleDateFormat.format(new Date());
		return dd;
	}

	// 开关灯设置操作 ​
	public String SetSwitch(String str, String devId) {

		// 声明变量

		Return retu = null;
		ArrayList<Return> retuArr = null;
		// 声明时间

		// 参数样式
		String ip = pref.getString("IP", "");
		String port = pref.getString("Port", "");
		String deviceId = devId;
		String connectType = "1";
		String connectParameter = "";
		String operationType = "2";
		String operationContent = str;
		String paraValues = "<sensor>" + "<deviceId>" + deviceId
				+ "</deviceId>" + "<connectType>" + connectType
				+ "</connectType>" + "<ip>" + ip + "</ip>" + "<port>" + port
				+ "</port>" + "<connectParameter>" + connectParameter
				+ "</connectParameter>" + "<operationType>" + operationType
				+ "</operationType>" + "<operationContent>" + operationContent
				+ "</operationContent>" + "</sensor>";

		try {
			serviceUrl = pref.getString("Url", "");
			// 命名空间
			String nameSpace = "http://service.sensor.microsec.com";
			// 定义调用的WebService方法名
			String methodName = "getOrSetSwitch";
			// 第1步：创建SoapObject对象，并指定WebService的命名空间和调用的方法名
			SoapObject request = new SoapObject(nameSpace, methodName);
			// 第2步：设置WebService方法的参数
			request.addProperty("sensor", paraValues);
			// 第3步：创建SoapSerializationEnvelope对象，并指定WebService的版本
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			// 设置bodyOut属性
			envelope.bodyOut = request;
			// 第4步：创建HttpTransportSE对象，并指定WSDL文档的URL
			HttpTransportSE ht = new HttpTransportSE(serviceUrl);
			// 第5步：调用WebService
			ht.call(null, envelope);

			if (envelope.getResponse() != null) {
				// 第6步：使用getResponse方法获得WebService方法的返回结果
				Object object = (Object) envelope.getResponse();

				// 得到返回结果 ，Object类型转换成字符串
				String result = object.toString();

				Log.i("测试", "连接成功后返回的值：" + result.toString());

				ByteArrayInputStream is = new ByteArrayInputStream(
						result.getBytes("ISO-8859-1"));

				// 声明变量解析器

				XmlPullParser parser = Xml.newPullParser();
				parser.setInput(is, "UTF-8");
				Log.i("测试", "连接成功后返回的值============：" + parser.toString());
				int event = parser.getEventType();
				while (event != XmlPullParser.END_DOCUMENT) {
					switch (event) {
					case XmlPullParser.START_DOCUMENT:// 判断是否文档开始事件
						retuArr = new ArrayList<Return>();
						break;
					case XmlPullParser.START_TAG:// 判断是否标签元素开始事件
						if ("return".equalsIgnoreCase(parser.getName())) {
							retu = new Return();
						} else if (retu != null) {
							if ("success".equalsIgnoreCase(parser.getName())) {
								retu.setSuccess(parser.nextText());
							}
						}
						break;
					case XmlPullParser.END_TAG:// 判断当前事件是否是标签元素结束事件
						if ("return".equalsIgnoreCase(parser.getName())) {
							retuArr.add(retu);
						}
						break;
					}
					event = parser.next();// 进入下一个元素并触发相应事件
				}
				if (is != null) {
					is.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("测试", "连接URL产生异常：" + e.toString());
		} finally {

		}

		Log.i("测试", "连接失败：" + retu.getSuccess().toString());
		String returnString = retu.getSuccess().toString().trim();

		return returnString;
	}

}
