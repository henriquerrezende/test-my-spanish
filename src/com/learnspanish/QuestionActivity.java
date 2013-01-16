package com.learnspanish;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Create the text view
	    TextView textView = (TextView) findViewById(R.id.question);
	    textView.setText("Generate question here");

	    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.answer_option_group);
//	    OnClickListener radio_listener = new OnClickListener() {
//	        public void onClick(View v) {
//	            onRadioButtonClick(v);
//	        }
//	    };

	    for (int i = 1; i < 4; i++) {
	    	RadioButton button = new RadioButton(this);
		    button.setText("ANSWER_" + i);
//		    button1.setOnClickListener(radio_listener);
		    radioGroup.addView(button);
	    }

	    setContentView(R.layout.question_layout);
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
}