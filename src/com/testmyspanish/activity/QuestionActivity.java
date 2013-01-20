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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.question_layout);

	    Question question = QuestionDAO.readRandomQuestion();

	    // Create the text view
	    TextView textView = (TextView) findViewById(R.id.question);
	    textView.setText(question.getQuestion());
	    
	    RadioGroup answersOptionsGroup = (RadioGroup) findViewById(R.id.answer_options_group);
	    for(Answer answerOption : question.getAnswers()) {
	    	RadioButton answerButton = new RadioButton(this);
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
			RadioButton answerButton = (RadioButton) v;
			answerButton.setTextColor(Color.BLUE);
			TextView textView = (TextView) findViewById(R.id.question);
    	    textView.setText("Question answered!");
		}
	};

	public void nextQuestion(View v) {
		//TODO: Implement Next Question Activity
	}

}