package com.example.sensor.tool;

import android.database.sqlite.SQLiteDatabase;

/**
 * 对数据库存储，提取数据
 * 
 * @author Administrator
 * 
 */
public class ManageSQL {

	public void insertT(SQLiteDatabase db, String data, String time) {
		try {

			db.execSQL("insert into temperatrue values (null , ? , ? )",
					new String[] { data, time });
			db.execSQL("insert into humidity values (null , ? , ? )",
					new String[] { data, time });
			db.execSQL("insert into light values (null , ? , ? )",
					new String[] { data, time });
			db.execSQL("insert into smoke values (null , ? , ? )",
					new String[] { data, time });
			db.execSQL("insert into CO values (null , ? , ? )",
					new String[] { data, time });
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void DeleteARecord(SQLiteDatabase db, String strRow,
			String strRowValue) {
		try {
			String sql = "delete from  temperatrue  where " + strRow + " = "
					+ strRowValue;
			db.execSQL(sql);
			String sql1 = "delete from  humidity  where " + strRow + " = "
					+ strRowValue;
			db.execSQL(sql1);
			String sql2 = "delete from  light  where " + strRow + " = "
					+ strRowValue;
			db.execSQL(sql2);
			String sql3 = "delete from  smoke  where " + strRow + " = "
					+ strRowValue;
			db.execSQL(sql3);
			String sql5 = "delete from  CO where " + strRow + " = "
					+ strRowValue;
			db.execSQL(sql5);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
