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

	SharedPreferences pref = SettingActivity.spf; // ����SharedPreferences

	// ���������ַ
	public static String serviceUrl = "";

 

	@SuppressLint("SimpleDateFormat")
	public String getTime() {
		// ����ʱ��
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str = formatter.format(curDate);
		return str;
	}

	public Return GetData(String deviceid, String methodname) {

		try {

			// ��������
		 
			Return retu = null;
			ArrayList<Return> retuArr = null;
			// ������ʽ
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
				// �����ռ�
				String nameSpace = "http://service.sensor.microsec.com";
				// ������õ�WebService������
				String methodName = methodname;
				// ��1��������SoapObject���󣬲�ָ��WebService�������ռ�͵��õķ�����
				SoapObject request = new SoapObject(nameSpace, methodName);
				// ��2��������WebService�����Ĳ���
				request.addProperty("sensor", paraValues);
				// ��3��������SoapSerializationEnvelope���󣬲�ָ��WebService�İ汾
				SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
						SoapEnvelope.VER11);
				// ����bodyOut����
				envelope.bodyOut = request;
				// ��4��������HttpTransportSE���󣬲�ָ��WSDL�ĵ���URL
				HttpTransportSE ht = new HttpTransportSE(serviceUrl);
				// ��5��������WebService
				ht.call(null, envelope);

				if (envelope.getResponse() != null) {
					// ��6����ʹ��getResponse�������WebService�����ķ��ؽ��
					Object object = (Object) envelope.getResponse();

					// return object;
					// �õ����ؽ�� ��Object����ת�����ַ���
					String result = object.toString();
					// �õ�Return����
					// Return retu = new
					// XmlUtil().xmlToBean(result,Return.class);
					// Log.i("����", "���ӳɹ��󷵻ص�ֵ��"+retu.getSuccess());
					Log.i("����", "���ӳɹ��󷵻ص�ֵ��" + result.toString());

					ByteArrayInputStream is = new ByteArrayInputStream(
							result.getBytes("ISO-8859-1"));

					// ��������
					XmlPullParser parser = Xml.newPullParser();
					parser.setInput(is, "UTF-8");
					Log.i("����", "���ӳɹ��󷵻ص�ֵ============��" + parser.toString());
					int event = parser.getEventType();
					while (event != XmlPullParser.END_DOCUMENT) {
						switch (event) {
						case XmlPullParser.START_DOCUMENT:// �ж��Ƿ��ĵ���ʼ�¼�
							retuArr = new ArrayList<Return>();
							break;
						case XmlPullParser.START_TAG:// �ж��Ƿ��ǩԪ�ؿ�ʼ�¼�
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
						case XmlPullParser.END_TAG:// �жϵ�ǰ�¼��Ƿ��Ǳ�ǩԪ�ؽ����¼�
							if ("return".equalsIgnoreCase(parser.getName())) {
								retuArr.add(retu);
							}
							break;
						}
						event = parser.next();// ������һ��Ԫ�ز�������Ӧ�¼�
					}
					if (is != null) {
						is.close();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				Log.e("����", "����URL�����쳣��" + e.toString());
			} finally {

			}
			Log.e("����", "�ɹ���" + retu.getSuccess());
			Log.e("����", "�ɹ���" + retu.getValue());

			return retu;

		} catch (Exception e) {
			System.out.println("����" + e);
			return null;
		}

	}

	public Return GetData(int i, String methodname) {
		// TODO Auto-generated method stub
		return null;
	}

}
