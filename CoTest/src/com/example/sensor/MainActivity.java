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
	GridView gvInfo;// 创建GridView对象
	// 定义字符串数组，存储系统功能
	String[] titles = new String[] { "电灯", "温度", "湿度", "烟雾", "二氧化碳",
			"系统设置", "电机", "退出" };
	// 定义int数组，存储功能对应的图标
	int[] images = new int[] { R.drawable.addoutaccount,
			R.drawable.addinaccount, R.drawable.outaccountinfo,
			R.drawable.inaccountinfo, R.drawable.showinfo, R.drawable.sysset,
			R.drawable.accountflag, R.drawable.exit };

	// 声明各种图片按钮
	private ImageButton IP_IButton;// 配置按钮
	private ImageButton Smoke_IButton;
	private ImageButton Lamp_IButton;// 开关灯按钮
	private ImageButton Temperature_IButton;// 温度按钮
	private ImageButton Humidity_IButton;// shidu按钮
	private ImageButton Light_IButton;// 光照按钮
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
		gvInfo = (GridView) findViewById(R.id.gvInfo);// 获取布局文件中的gvInfo组件
		pictureAdapter adapter = new pictureAdapter(titles, images, this);// 创建pictureAdapter对象
		gvInfo.setAdapter(adapter);// 为GridView设置数据源
		gvInfo.setOnItemClickListener(new OnItemClickListener() {// 为GridView设置项单击事件
		mySQLite = new MySQLite(this, "managedb", 1);
		mySQLite1 = new MySQLite(this, "managedb", 1);
		mySQLite2 = new MySQLite(this, "managedb", 1);
		mySQLite3 = new MySQLite(this, "managedb", 1);
		mySQLite5 = new MySQLite(this, "managedb", 1);
		// 得到按钮控件对象
		
	   // this.Motordriver_IButton.setOnClickListener(new View.OnClickListener()
		
		IP_IButton = (ImageButton) findViewById(R.id.setting);
		Motordriver_IButton = (ImageButton) findViewById(R.id.motor_button);
		Lamp_IButton = (ImageButton) findViewById(R.id.openCloseLamp);
		Temperature_IButton = (ImageButton) findViewById(R.id.tem_hud_button);
		Humidity_IButton=(ImageButton) findViewById(R.id.humidity_button);
		Light_IButton= (ImageButton) findViewById(R.id.light_button);
		Smoke_IButton= (ImageButton) findViewById(R.id.smoke_button);
		Co_IButton=(ImageButton) findViewById(R.id.co2_button);
		
		// 为开关灯图片按钮添加监听器
		Lamp_IButton.setOnClickListener(new OnClickListener() {
			// 点击按钮跳转到灯控制界面
			@Override
			public void onClick(View arg0) {
				// 创建Intent对象，传入源Activity和目的Activity的类对象
				intent = new Intent(MainActivity.this, LampActivity.class);
				startActivity(intent);
				 

			}
		});
		Light_IButton.setOnClickListener(new OnClickListener() {
			// 点击按钮跳转到温度信息界面
			@Override
			public void onClick(View arg0) {
				intent = new Intent(MainActivity.this,
						LightActivity.class);
				startActivity(intent);
			}
		});
		Humidity_IButton.setOnClickListener(new OnClickListener() {
			// 点击按钮跳转到温度信息界面
			@Override
			public void onClick(View arg0) {
				intent = new Intent(MainActivity.this,
						HumidityActivity.class);
				startActivity(intent);
			}
		});
		// 为温度按钮添加监听器
		Temperature_IButton.setOnClickListener(new OnClickListener() {
			// 点击按钮跳转到温度信息界面
			@Override
			public void onClick(View arg0) {
				intent = new Intent(MainActivity.this,
						TemperatureActivity.class);
				startActivity(intent);
			}
		});

		// 为配置按钮添加监听器
		IP_IButton.setOnClickListener(new OnClickListener() {
			// 点击按钮直接跳转到配置界面
			@Override
			public void onClick(View arg0) {
				// 创建Intent对象，传入源Activity和目的Activity的类对象
				intent = new Intent(MainActivity.this, SettingActivity.class);
				startActivity(intent);

			}
		});
		// 为配置按钮添加监听器
				Smoke_IButton.setOnClickListener(new OnClickListener() {
					// 点击按钮直接跳转到配置界面
					@Override
					public void onClick(View arg0) {
						// 创建Intent对象，传入源Activity和目的Activity的类对象
						intent = new Intent(MainActivity.this, SmokeActivity.class);
						startActivity(intent);

					}
				});
				// 为配置按钮添加监听器
				Motordriver_IButton.setOnClickListener(new OnClickListener() {
					// 点击按钮直接跳转到配置界面
					@Override
					public void onClick(View arg0) {
						// 创建Intent对象，传入源Activity和目的Activity的类对象
					intent = new Intent(MainActivity.this, MotordriverActivity.class);
						startActivity(intent);

					}
				});
				Co_IButton.setOnClickListener(new OnClickListener() {
					// 点击按钮跳转到温度信息界面
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
	GridView gvInfo;// 创建GridView对象
	// 定义字符串数组，存储系统功能
	String[] titles = new String[] { "二氧化碳", "湿度", "电灯", "风扇",
			"系统设置", "温度", "瓦斯浓度","退出" };
	// 定义int数组，存储功能对应的图标
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
		gvInfo = (GridView) findViewById(R.id.gvInfo);// 获取布局文件中的gvInfo组件
		pictureAdapter adapter = new pictureAdapter(titles, images, this);// 创建pictureAdapter对象
		gvInfo.setAdapter(adapter);// 为GridView设置数据源
		gvInfo.setOnItemClickListener(new OnItemClickListener() {// 为GridView设置项单击事件
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = null;// 创建Intent对象
				switch (arg2) {
				case 0:
					intent = new Intent(MainActivity.this, CoActivity.class);// 使用AddOutaccount窗口初始化Intent
					startActivity(intent);// 打开AddOutaccount
					break;
				case 1:
					intent = new Intent(MainActivity.this, HumidityActivity.class);// 使用AddInaccount窗口初始化Intent
					startActivity(intent);// 打开AddInaccount
					break;
				case 2:
					intent = new Intent(MainActivity.this, LampActivity.class);// 使用Outaccountinfo窗口初始化Intent
					startActivity(intent);// 打开Outaccountinfo
					break;
	
				case 3:
					intent = new Intent(MainActivity.this,MotordriverActivity.class);// 使用Showinfo窗口初始化Intent
					startActivity(intent);// 打开Showinfo
					break;
				case 4:
					intent = new Intent(MainActivity.this, SettingActivity.class);// 使用Sysset窗口初始化Intent
					startActivity(intent);// 打开Sysset
					break;
				case 5:
					intent = new Intent(MainActivity.this, TemperatureActivity.class);// 使用Accountflag窗口初始化Intent
					startActivity(intent);// 打开Accountflag
					break;
				case 6:
					intent = new Intent(MainActivity.this, SmokeActivity.class);// 使用Accountflag窗口初始化Intent
					startActivity(intent);// 打开Accountflag
					break;
				case 7:
					finish();// 关闭当前Activity
				}
			}
		});
	}
}

class pictureAdapter extends BaseAdapter// 创建基于BaseAdapter的子类
{
	private LayoutInflater inflater;// 创建LayoutInflater对象
	private List<Picture> pictures;// 创建List泛型集合

	// 为类创建构造函数
	public pictureAdapter(String[] titles, int[] images, Context context) {
		super();
		pictures = new ArrayList<Picture>();// 初始化泛型集合对象
		inflater = LayoutInflater.from(context);// 初始化LayoutInflater对象
		for (int i = 0; i < images.length; i++)// 遍历图像数组
		{
			Picture picture = new Picture(titles[i], images[i]);// 使用标题和图像生成Picture对象
			pictures.add(picture);// 将Picture对象添加到泛型集合中
		}
	}

	@Override
	public int getCount() {// 获取泛型集合的长度
		if (null != pictures) {// 如果泛型集合不为空
			return pictures.size();// 返回泛型长度
		} else {
			return 0;// 返回0
		}
	}

	@Override
	public Object getItem(int arg0) {
		return pictures.get(arg0);// 获取泛型集合指定索引处的项
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;// 返回泛型集合的索引
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder viewHolder;// 创建ViewHolder对象
		if (arg1 == null)// 判断图像标识是否为空
		{
			arg1 = inflater.inflate(R.layout.gvitem, null);// 设置图像标识
			viewHolder = new ViewHolder();// 初始化ViewHolder对象
			viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);// 设置图像标题
			viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);// 设置图像的二进制值
			arg1.setTag(viewHolder);// 设置提示
		} else {
			viewHolder = (ViewHolder) arg1.getTag();// 设置提示
		}
		viewHolder.title.setText(pictures.get(arg0).getTitle());// 设置图像标题
		viewHolder.image.setImageResource(pictures.get(arg0).getImageId());// 设置图像的二进制值
		return arg1;// 返回图像标识
	}
}

class ViewHolder// 创建ViewHolder类
{
	public TextView title;// 创建TextView对象
	public ImageView image;// 创建ImageView对象
}

class Picture// 创建Picture类
{
	private String title;// 定义字符串，表示图像标题
	private int imageId;// 定义int变量，表示图像的二进制值

	public Picture()// 默认构造函数
	{
		super();
	}

	public Picture(String title, int imageId)// 定义有参构造函数
	{
		super();
		this.title = title;// 为图像标题赋值
		this.imageId = imageId;// 为图像的二进制值赋值
	}

	public String getTitle() {// 定义图像标题的可读属性
		return title;
	}

	public void setTitle(String title) {// 定义图像标题的可写属性
		this.title = title;
	}

	public int getImageId() {// 定义图像二进制值的可读属性
		return imageId;
	}

	public void setimageId(int imageId) {// 定义图像二进制值的可写属性
		this.imageId = imageId;
	}
}
