package com.testmyspanish.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.testmyspanish.R;
import com.testmyspanish.model.Answer;
import com.testmyspanish.model.Question;
import com.testmyspanish.persistence.dao.QuestionDAO;

public class QuestionActivity extends Activity {

	private Long correctAnswerId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.question_layout);

	    Question question = QuestionDAO.readRandomQuestion();
	    correctAnswerId = question.getCorrectAnswerId();

	    // Create the text view
	    TextView textView = (TextView) findViewById(R.id.question);
	    textView.setText(question.getQuestion());
	    
	    RadioGroup answersOptionsGroup = (RadioGroup) findViewById(R.id.answer_options_group);
	    for(Answer answerOption : question.getAnswers()) {
	    	RadioButton answerButton = new RadioButton(this);
	    	answerButton.setId(answerOption.getId());
	    	answerButton.setText(answerOption.getAnswer());
	    	answerButton.setOnClickListener(answerButtonClickHandler);
	    	answersOptionsGroup.addView(answerButton);
	    }
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private OnClickListener answerButtonClickHandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			TextView textView = (TextView) findViewById(R.id.question);

			RadioButton answerButton = (RadioButton) v;
			if (Long.valueOf(answerButton.getId()) == correctAnswerId) {
				answerButton.setTextColor(Color.GREEN);
				textView.setText("Question answered correctly!");
			} else {
				answerButton.setTextColor(Color.RED);
				textView.setText("Question answered wrong!");
			}
		}
	};

	public void nextQuestion(View v) {
		recreate();
	}

}