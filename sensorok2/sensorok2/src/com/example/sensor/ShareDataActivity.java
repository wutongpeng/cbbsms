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

import android.annotation.SuppressLint; 
import android.content.SharedPreferences;
 
import android.util.Log;
import android.util.Xml; 

import com.example.sensor.model.Return;

public class ShareDataActivity {

	SharedPreferences pref = SettingActivity.spf; // 声明SharedPreferences

	// 声明网络地址
	public static String serviceUrl = "";

 

	@SuppressLint("SimpleDateFormat")
	public String getTime() {
		// 声明时间
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	public Return GetData(String deviceid, String methodname) {

		try {

			// 声明变量
		 
			Return retu = null;
			ArrayList<Return> retuArr = null;
			// 参数样式
			String ip = pref.getString("IP", "");
			String port = pref.getString("Port", "");
			String deviceId = deviceid;
			String connectType = "1";
			String connectParameter = "";
			String paraValues = "<sensor>" + "<deviceId>" + deviceId
					+ "</deviceId>" + "<connectType>" + connectType
					+ "</connectType>" + "<ip>" + ip + "</ip>" + "<port>"
					+ port + "</port>" + "<connectParameter>"
					+ connectParameter + "</connectParameter>" + "</sensor>";

			try {
				// serviceUrl
				// ="http://192.168.1.26:8080/sensorWebservice/services/sensorService";
				serviceUrl = pref.getString("Url", "");
				// 命名空间
				String nameSpace = "http://service.sensor.microsec.com";
				// 定义调用的WebService方法名
				String methodName = methodname;
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

					// return object;
					// 得到返回结果 ，Object类型转换成字符串
					String result = object.toString();
					// 得到Return对象
					// Return retu = new
					// XmlUtil().xmlToBean(result,Return.class);
					// Log.i("测试", "连接成功后返回的值："+retu.getSuccess());
					Log.i("测试", "连接成功后返回的值：" + result.toString());

					ByteArrayInputStream is = new ByteArrayInputStream(
							result.getBytes("ISO-8859-1"));

					// 声明变量
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
								/**
								 * <return> <sucess>false</sucess>
								 * <value>22</value> </return>
								 */

								retu = new Return();
							} else if (retu != null) {
								if ("success"
										.equalsIgnoreCase(parser.getName())) {
									retu.setSuccess(parser.nextText());
								} else if ("value".equalsIgnoreCase(parser
										.getName())) {
									retu.setValue(parser.nextText());
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
			Log.e("测试", "成功：" + retu.getSuccess());
			Log.e("测试", "成功：" + retu.getValue());

			return retu;

		} catch (Exception e) {
			System.out.println("测试" + e);
			return null;
		}

	}

	public Return GetData(int i, String methodname) {
		// TODO Auto-generated method stub
		return null;
	}

}
