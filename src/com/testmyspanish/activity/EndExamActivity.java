package com.testmyspanish.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.testmyspanish.R;
import com.testmyspanish.model.Exam;
import com.testmyspanish.persistence.dao.ExamDAO;

public class EndExamActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_exam_layout);

		Bundle bundle = getIntent().getExtras();
		Exam exam = (Exam) bundle.getSerializable("exam");

		TextView finalScore = (TextView) findViewById(R.id.finalScore);
		finalScore.setText("Score: " + exam.getActualScore() + "/" + exam.getTotalScore());
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
