package com.testmyspanish.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.testmyspanish.R;
import com.testmyspanish.model.Answer;
import com.testmyspanish.model.Exam;
import com.testmyspanish.model.Question;

public class ExamActivity extends Activity {

	private Exam exam;
	private Long correctAnswerId;
	private Toast answerMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			exam = (Exam) bundle.getSerializable("exam");
			getIntent().removeExtra("exam");
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam_layout);

		TextView scoreTextView = (TextView) findViewById(R.id.score);
		scoreTextView.setText("Score: " + exam.getActualScore() + "/" + exam.getTotalScore());

	    Question question = exam.consumeQuestion();
	    if (question != null) {
		    correctAnswerId = question.getCorrectAnswerId();

		    TextView questionTextView = (TextView) findViewById(R.id.question);
		    questionTextView.setText(question.getQuestion());

		    RadioGroup answersOptionsGroup = (RadioGroup) findViewById(R.id.answerOptionsGroup);
		    for(Answer answerOption : question.getAnswers()) {
				RadioButton answerButton = new RadioButton(this);
				answerButton.setId(answerOption.getId());
				answerButton.setText(answerOption.getAnswer());
				answerButton.setOnClickListener(answerButtonClickHandler);
				answersOptionsGroup.addView(answerButton);
		    }

		    answerMessage = Toast.makeText(ExamActivity.this, "", Toast.LENGTH_SHORT);
	    } else {
	    	this.finish();

	    	Bundle endBundle = new Bundle();
	    	endBundle.putSerializable("exam", exam);

			Intent intent = new Intent(this, EndExamActivity.class);
			intent.putExtras(endBundle);

			startActivity(intent);
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private OnClickListener answerButtonClickHandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			RadioButton answerButton = (RadioButton) v;
			if (Long.valueOf(answerButton.getId()).equals(correctAnswerId)) {
				exam.setActualScore(exam.getActualScore() + 1);
				TextView scoreTextView = (TextView) findViewById(R.id.score);
				scoreTextView.setText("Score: " + exam.getActualScore() + "/" + exam.getTotalScore());

				answerButton.setTextColor(Color.GREEN);
				answerMessage.setText("Question answered correctly!");
				answerMessage.show();
			} else {
				answerButton.setTextColor(Color.RED);
				answerMessage.setText("Question answered wrong!");
				answerMessage.show();
			}

		    disableAnswerButtons(answerButton);
		}

		private void disableAnswerButtons(View radioButtonView) {
			RadioGroup answersOptionsGroup = (RadioGroup) radioButtonView.getParent();
		    Integer radioButtonCount = answersOptionsGroup.getChildCount();

		    for (int i=0; i<radioButtonCount; i++){
				answersOptionsGroup.getChildAt(i).setEnabled(false);
		    }
		}
	};

	public void nextQuestion(View v) {
		answerMessage.cancel();
		this.onCreate(null);
	}
}
