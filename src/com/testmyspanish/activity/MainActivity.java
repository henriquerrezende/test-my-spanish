package com.testmyspanish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.testmyspanish.R;
import com.testmyspanish.model.Exam;
import com.testmyspanish.persistence.DbHelper;
import com.testmyspanish.persistence.dao.ExamDAO;

public class MainActivity extends Activity {

	public static DbHelper DB_HELPER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DB_HELPER = new DbHelper(this);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void startTest(View view) {
		Intent intent = new Intent(this, ExamActivity.class);

		Exam exam = ExamDAO.readRandomExam();
		Bundle bundle = new Bundle();
		bundle.putSerializable("exam", exam);

		intent.putExtras(bundle);
		startActivity(intent);
	}
}
