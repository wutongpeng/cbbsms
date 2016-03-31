package com.example.sensor.tool;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 *
 */
public class MySQLite extends SQLiteOpenHelper {

	public final String CREAT_RABLE_SQL = "create table temperatrue(id integer primary key autoincrement ,data ,time)";
	public final String CREAT_RABLE_SQL1 = "create table humidity(id integer primary key autoincrement ,data ,time)";
	public final String CREAT_RABLE_SQL2 = "create table light(id integer primary key autoincrement ,data ,time)";
	public final String CREAT_RABLE_SQL3 = "create table smoke(id integer primary key autoincrement ,data ,time)";
	public final String CREAT_RABLE_SQL5 = "create table CO2(id integer primary key autoincrement ,data ,time)";



	public MySQLite(Context context, String name, int version) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREAT_RABLE_SQL);
		db.execSQL(CREAT_RABLE_SQL1);
		db.execSQL(CREAT_RABLE_SQL2);
		db.execSQL(CREAT_RABLE_SQL3);
		db.execSQL(CREAT_RABLE_SQL5);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
