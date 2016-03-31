package com.example.sensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.sensor.tool.ManageSQL;
import com.example.sensor.tool.MySQLite;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CarbonActivityManage extends Activity {

	ListView listView;
	MySQLite mySQLite=MainActivity.mySQLite5;
	String data, time;
	Cursor cursor;
	 

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maico);
	
		showData();

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				LinearLayout layout = (LinearLayout) arg1;
				final TextView id = (TextView) layout.getChildAt(0);
				new AlertDialog.Builder(CarbonActivityManage.this)
						.setMessage("确定删除么？")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {

										String strRowValue = id.getText()
												.toString();
										new ManageSQL().DeleteARecord(
											 mySQLite.getReadableDatabase(),
												"id", strRowValue);
										showData();
									}
								})
						.setNegativeButton("取消",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								}).show();

				return false;
			}
		});
	}

	public void showData() {
		List<Map<String, String>> listItemsList = new ArrayList<Map<String, String>>();

		listView = (ListView) findViewById(R.id.listview);
		

		cursor = mySQLite.getReadableDatabase().rawQuery(
				"select  * from temperatrue", null);

		SimpleAdapter adapter = new SimpleAdapter(this, listItemsList,
				R.layout.activity_listview, new String[] { "id", "time0",
						"data0" }, new int[] { R.id.id, R.id.timeview,
						R.id.dataview });
		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", cursor.getString(0));
			map.put("time0", cursor.getString(1));// 数据库中第一列的内容显示在listview的左边
			map.put("data0", cursor.getString(2));// 数据库的第三列内容显示在listview的右边
			listItemsList.add(map);
		}
		listView.setAdapter(adapter);
	}

}
