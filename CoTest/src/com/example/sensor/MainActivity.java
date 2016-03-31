package com.example.sensor;
/*import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sensor.tool.MySQLite;
import com.example.sensor.R;



public class MainActivity extends Activity {
	GridView gvInfo;// ����GridView����
	// �����ַ������飬�洢ϵͳ����
	String[] titles = new String[] { "���", "�¶�", "ʪ��", "����", "������̼",
			"ϵͳ����", "���", "�˳�" };
	// ����int���飬�洢���ܶ�Ӧ��ͼ��
	int[] images = new int[] { R.drawable.addoutaccount,
			R.drawable.addinaccount, R.drawable.outaccountinfo,
			R.drawable.inaccountinfo, R.drawable.showinfo, R.drawable.sysset,
			R.drawable.accountflag, R.drawable.exit };

	// ��������ͼƬ��ť
	private ImageButton IP_IButton;// ���ð�ť
	private ImageButton Smoke_IButton;
	private ImageButton Lamp_IButton;// ���صư�ť
	private ImageButton Temperature_IButton;// �¶Ȱ�ť
	private ImageButton Humidity_IButton;// shidu��ť
	private ImageButton Light_IButton;// ���հ�ť
	private ImageButton Motordriver_IButton;
	private ImageButton Co_IButton;
	public static MySQLite mySQLite;
	public static MySQLite mySQLite1;
	public static MySQLite mySQLite2;
	public static MySQLite mySQLite3;
	public static MySQLite mySQLite5;
	public static double v;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gvInfo = (GridView) findViewById(R.id.gvInfo);// ��ȡ�����ļ��е�gvInfo���
		pictureAdapter adapter = new pictureAdapter(titles, images, this);// ����pictureAdapter����
		gvInfo.setAdapter(adapter);// ΪGridView��������Դ
		gvInfo.setOnItemClickListener(new OnItemClickListener() {// ΪGridView��������¼�
		mySQLite = new MySQLite(this, "managedb", 1);
		mySQLite1 = new MySQLite(this, "managedb", 1);
		mySQLite2 = new MySQLite(this, "managedb", 1);
		mySQLite3 = new MySQLite(this, "managedb", 1);
		mySQLite5 = new MySQLite(this, "managedb", 1);
		// �õ���ť�ؼ�����
		
	   // this.Motordriver_IButton.setOnClickListener(new View.OnClickListener()
		
		IP_IButton = (ImageButton) findViewById(R.id.setting);
		Motordriver_IButton = (ImageButton) findViewById(R.id.motor_button);
		Lamp_IButton = (ImageButton) findViewById(R.id.openCloseLamp);
		Temperature_IButton = (ImageButton) findViewById(R.id.tem_hud_button);
		Humidity_IButton=(ImageButton) findViewById(R.id.humidity_button);
		Light_IButton= (ImageButton) findViewById(R.id.light_button);
		Smoke_IButton= (ImageButton) findViewById(R.id.smoke_button);
		Co_IButton=(ImageButton) findViewById(R.id.co2_button);
		
		// Ϊ���ص�ͼƬ��ť��Ӽ�����
		Lamp_IButton.setOnClickListener(new OnClickListener() {
			// �����ť��ת���ƿ��ƽ���
			@Override
			public void onClick(View arg0) {
				// ����Intent���󣬴���ԴActivity��Ŀ��Activity�������
				intent = new Intent(MainActivity.this, LampActivity.class);
				startActivity(intent);
				 

			}
		});
		Light_IButton.setOnClickListener(new OnClickListener() {
			// �����ť��ת���¶���Ϣ����
			@Override
			public void onClick(View arg0) {
				intent = new Intent(MainActivity.this,
						LightActivity.class);
				startActivity(intent);
			}
		});
		Humidity_IButton.setOnClickListener(new OnClickListener() {
			// �����ť��ת���¶���Ϣ����
			@Override
			public void onClick(View arg0) {
				intent = new Intent(MainActivity.this,
						HumidityActivity.class);
				startActivity(intent);
			}
		});
		// Ϊ�¶Ȱ�ť��Ӽ�����
		Temperature_IButton.setOnClickListener(new OnClickListener() {
			// �����ť��ת���¶���Ϣ����
			@Override
			public void onClick(View arg0) {
				intent = new Intent(MainActivity.this,
						TemperatureActivity.class);
				startActivity(intent);
			}
		});

		// Ϊ���ð�ť��Ӽ�����
		IP_IButton.setOnClickListener(new OnClickListener() {
			// �����ťֱ����ת�����ý���
			@Override
			public void onClick(View arg0) {
				// ����Intent���󣬴���ԴActivity��Ŀ��Activity�������
				intent = new Intent(MainActivity.this, SettingActivity.class);
				startActivity(intent);

			}
		});
		// Ϊ���ð�ť��Ӽ�����
				Smoke_IButton.setOnClickListener(new OnClickListener() {
					// �����ťֱ����ת�����ý���
					@Override
					public void onClick(View arg0) {
						// ����Intent���󣬴���ԴActivity��Ŀ��Activity�������
						intent = new Intent(MainActivity.this, SmokeActivity.class);
						startActivity(intent);

					}
				});
				// Ϊ���ð�ť��Ӽ�����
				Motordriver_IButton.setOnClickListener(new OnClickListener() {
					// �����ťֱ����ת�����ý���
					@Override
					public void onClick(View arg0) {
						// ����Intent���󣬴���ԴActivity��Ŀ��Activity�������
					intent = new Intent(MainActivity.this, MotordriverActivity.class);
						startActivity(intent);

					}
				});
				Co_IButton.setOnClickListener(new OnClickListener() {
					// �����ť��ת���¶���Ϣ����
					@Override
					public void onClick(View arg0) {
						intent = new Intent(MainActivity.this,
								CoActivity.class);
						startActivity(intent);
					}
				});
	}

}*/
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.sensor.tool.MySQLite;
import java.util.ArrayList;
import java.util.List;

import com.example.sensor.tool.MySQLite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.example.sensor.tool.MySQLite;
public class MainActivity extends Activity {
	public static MySQLite mySQLite;
	public static MySQLite mySQLite1;
	public static MySQLite mySQLite2;
	public static MySQLite mySQLite3;
	public static MySQLite mySQLite5;
	//public static double v;
	private Intent intent;
	GridView gvInfo;// ����GridView����
	// �����ַ������飬�洢ϵͳ����
	String[] titles = new String[] { "������̼", "ʪ��", "���", "����",
			"ϵͳ����", "�¶�", "��˹Ũ��","�˳�" };
	// ����int���飬�洢���ܶ�Ӧ��ͼ��
	int[] images = new int[] { R.drawable.ad,
			R.drawable.ah, R.drawable.as,
			 R.drawable.af, R.drawable.ww,
			R.drawable.ak, R.drawable.ax, R.drawable.a };
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mySQLite = new MySQLite(this, "managedb", 1);
		mySQLite1 = new MySQLite(this, "managedb", 1);
		mySQLite2 = new MySQLite(this, "managedb", 1);
		mySQLite3 = new MySQLite(this, "managedb", 1);
		mySQLite5 = new MySQLite(this, "managedb", 1);
		gvInfo = (GridView) findViewById(R.id.gvInfo);// ��ȡ�����ļ��е�gvInfo���
		pictureAdapter adapter = new pictureAdapter(titles, images, this);// ����pictureAdapter����
		gvInfo.setAdapter(adapter);// ΪGridView��������Դ
		gvInfo.setOnItemClickListener(new OnItemClickListener() {// ΪGridView��������¼�
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = null;// ����Intent����
				switch (arg2) {
				case 0:
					intent = new Intent(MainActivity.this, CoActivity.class);// ʹ��AddOutaccount���ڳ�ʼ��Intent
					startActivity(intent);// ��AddOutaccount
					break;
				case 1:
					intent = new Intent(MainActivity.this, HumidityActivity.class);// ʹ��AddInaccount���ڳ�ʼ��Intent
					startActivity(intent);// ��AddInaccount
					break;
				case 2:
					intent = new Intent(MainActivity.this, LampActivity.class);// ʹ��Outaccountinfo���ڳ�ʼ��Intent
					startActivity(intent);// ��Outaccountinfo
					break;
	
				case 3:
					intent = new Intent(MainActivity.this,MotordriverActivity.class);// ʹ��Showinfo���ڳ�ʼ��Intent
					startActivity(intent);// ��Showinfo
					break;
				case 4:
					intent = new Intent(MainActivity.this, SettingActivity.class);// ʹ��Sysset���ڳ�ʼ��Intent
					startActivity(intent);// ��Sysset
					break;
				case 5:
					intent = new Intent(MainActivity.this, TemperatureActivity.class);// ʹ��Accountflag���ڳ�ʼ��Intent
					startActivity(intent);// ��Accountflag
					break;
				case 6:
					intent = new Intent(MainActivity.this, SmokeActivity.class);// ʹ��Accountflag���ڳ�ʼ��Intent
					startActivity(intent);// ��Accountflag
					break;
				case 7:
					finish();// �رյ�ǰActivity
				}
			}
		});
	}
}

class pictureAdapter extends BaseAdapter// ��������BaseAdapter������
{
	private LayoutInflater inflater;// ����LayoutInflater����
	private List<Picture> pictures;// ����List���ͼ���

	// Ϊ�ഴ�����캯��
	public pictureAdapter(String[] titles, int[] images, Context context) {
		super();
		pictures = new ArrayList<Picture>();// ��ʼ�����ͼ��϶���
		inflater = LayoutInflater.from(context);// ��ʼ��LayoutInflater����
		for (int i = 0; i < images.length; i++)// ����ͼ������
		{
			Picture picture = new Picture(titles[i], images[i]);// ʹ�ñ����ͼ������Picture����
			pictures.add(picture);// ��Picture������ӵ����ͼ�����
		}
	}

	@Override
	public int getCount() {// ��ȡ���ͼ��ϵĳ���
		if (null != pictures) {// ������ͼ��ϲ�Ϊ��
			return pictures.size();// ���ط��ͳ���
		} else {
			return 0;// ����0
		}
	}

	@Override
	public Object getItem(int arg0) {
		return pictures.get(arg0);// ��ȡ���ͼ���ָ������������
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;// ���ط��ͼ��ϵ�����
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder viewHolder;// ����ViewHolder����
		if (arg1 == null)// �ж�ͼ���ʶ�Ƿ�Ϊ��
		{
			arg1 = inflater.inflate(R.layout.gvitem, null);// ����ͼ���ʶ
			viewHolder = new ViewHolder();// ��ʼ��ViewHolder����
			viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);// ����ͼ�����
			viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);// ����ͼ��Ķ�����ֵ
			arg1.setTag(viewHolder);// ������ʾ
		} else {
			viewHolder = (ViewHolder) arg1.getTag();// ������ʾ
		}
		viewHolder.title.setText(pictures.get(arg0).getTitle());// ����ͼ�����
		viewHolder.image.setImageResource(pictures.get(arg0).getImageId());// ����ͼ��Ķ�����ֵ
		return arg1;// ����ͼ���ʶ
	}
}

class ViewHolder// ����ViewHolder��
{
	public TextView title;// ����TextView����
	public ImageView image;// ����ImageView����
}

class Picture// ����Picture��
{
	private String title;// �����ַ�������ʾͼ�����
	private int imageId;// ����int��������ʾͼ��Ķ�����ֵ

	public Picture()// Ĭ�Ϲ��캯��
	{
		super();
	}

	public Picture(String title, int imageId)// �����вι��캯��
	{
		super();
		this.title = title;// Ϊͼ����⸳ֵ
		this.imageId = imageId;// Ϊͼ��Ķ�����ֵ��ֵ
	}

	public String getTitle() {// ����ͼ�����Ŀɶ�����
		return title;
	}

	public void setTitle(String title) {// ����ͼ�����Ŀ�д����
		this.title = title;
	}

	public int getImageId() {// ����ͼ�������ֵ�Ŀɶ�����
		return imageId;
	}

	public void setimageId(int imageId) {// ����ͼ�������ֵ�Ŀ�д����
		this.imageId = imageId;
	}
}
