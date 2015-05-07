package com.praveen.dbsearch;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlHelper extends SQLiteOpenHelper {
	public static final String NAME_ID = "_id";
	public static final String NAME = "name";

	private static final String DATABASE_NAME = "commments.db";
	private static final int DATABASE_VERSION = 1;
	public static final String NAMES = "names";

	private static final String DATABASE_CREATE = "create table "
			+ NAMES + "("
			+ NAME_ID + " integer primary key autoincrement, " 
			+ NAME+ " text not null);";

	public MySqlHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + NAMES);
		onCreate(db);
	}

}
