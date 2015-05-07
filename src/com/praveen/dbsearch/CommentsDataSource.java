package com.praveen.dbsearch;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySqlHelper dbHelper;
	private String[] allColumns = { MySqlHelper.NAME_ID,
			MySqlHelper.NAME };

	public CommentsDataSource(Context context) {
		dbHelper = new MySqlHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public name createComment(String comment) {
		ContentValues values = new ContentValues();
		values.put(MySqlHelper.NAME, comment);

		long insertId = database.insert(MySqlHelper.NAMES, null, values);


		Cursor cursor = database.query(MySqlHelper.NAMES,
				allColumns, MySqlHelper.NAME_ID + " = " + insertId, null,
				null, null, null);
		name newComment=null;
		if(cursor==null){
			if(cursor.moveToFirst()){
				newComment = cursorToComment(cursor);
				cursor.close(); 
			}
		}

		return newComment;
	}

	public void deleteComment(name comment) {
		long id = comment.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySqlHelper.NAMES, MySqlHelper.NAME_ID + " = " + id,null);
	}
	public int search(String Name) {

		Cursor cursor = database.query(MySqlHelper.NAMES,
				allColumns, MySqlHelper.NAME + " = '" + Name+"'", null, null, null, null);
		return cursor.getCount();



	}
	public List<name> getAllComments() {
		List<name> comments = new ArrayList<name>();

		Cursor cursor = database.query(MySqlHelper.NAMES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			name comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return comments;
	}

	private name cursorToComment(Cursor cursor) {
		name comment = new name();
		//	comment.setId(cursor.getLong(0));
		comment.setName(cursor.getString(1));
		return comment;
	}
}