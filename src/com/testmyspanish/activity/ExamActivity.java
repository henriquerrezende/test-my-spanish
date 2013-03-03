package com.testmyspanish.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.testmyspanish.R;

public class ExamActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam_layout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void nextQuestion(View v) {
		FragmentManager fragmentManager = getFragmentManager();
	    FragmentTransaction transaction = fragmentManager.beginTransaction();
//	    setContentView(R.layout.exam_layout);
	    transaction.remove(fragmentManager.findFragmentById(R.id.questionFragment));
	    transaction.add(R.id.questionFragment, new QuestionFragment());
//	    transaction.replace(R.id.questionFragment, new QuestionFragment());
	    transaction.addToBackStack(null);
	    transaction.commit();
//		recreate();
	}
}
