package com.praveen.dbsearch;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	EditText edt,edtsearch;
	private CommentsDataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		datasource = new CommentsDataSource(this);
		datasource.open();
	}
	public void addnewdb (View v) {

		edt=(EditText) findViewById(R.id.edt_name);
		Toast.makeText(getApplicationContext(), edt.getText(), Toast.LENGTH_SHORT).show();
		addNewNames(edt.getText().toString());
	}

	public void addNewNames(String name) {
		name n=null;
		n = datasource.createComment(name);

	}
	public void btnview(View v) {

		startActivity(new Intent(this,Viewname.class));

	}
	public void search(View v) {
		datasource = new CommentsDataSource(this);
		datasource.open();
		edtsearch=(EditText) findViewById(R.id.edt_search);

Integer res=datasource.search(edtsearch.getText().toString());
if(res>0)
Toast.makeText(getApplicationContext(), "Name "+edtsearch.getText()+":- is in database", Toast.LENGTH_SHORT).show();
else
	Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_SHORT).show();	
	}
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}

}
