package com.testmyspanish;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.question_layout);

	    FeedQuestionDbHelper dbHelper = new FeedQuestionDbHelper(this);
	    Question question= readFirstQuestion(dbHelper);

	    // Create the text view
	    TextView textView = (TextView) findViewById(R.id.question);
	    textView.setText(question.getTitle());
	    
	    List<String> answer_options = new ArrayList<String>();
	    answer_options.add("hello");
	    answer_options.add("bye");
	    
	    RadioGroup answers_options_group = (RadioGroup) findViewById(R.id.answer_options_group);
	    for(String answer_option : answer_options) {
	    	RadioButton answer_button = new RadioButton(this);
	    	answer_button.setText(answer_option);
	    	answer_button.setOnClickListener(answerButtonClickHandler);
	    	answers_options_group.addView(answer_button);
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
	
    private Question readFirstQuestion(FeedQuestionDbHelper dbHelper) {
    	
    	SQLiteDatabase db = dbHelper.getWritableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    FeedReaderContract.FeedQuestion._ID,
    	    FeedReaderContract.FeedQuestion.COLUMN_NAME_TITLE,
    	    FeedReaderContract.FeedQuestion.COLUMN_NAME_ANSWER_ID
    	    };
    	
    	Cursor cursor = db.query(
    	    FeedReaderContract.FeedQuestion.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    "",                                // The columns for the WHERE clause
    	    null,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    null,                                 // The sort order
    	    "1"											// limit
    	    );
   
    	cursor.moveToFirst();

    	String itemTitle = cursor.getString(
        	    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedQuestion.COLUMN_NAME_TITLE)
        	);
    	int itemAnswerId = cursor.getInt(
        	    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedQuestion.COLUMN_NAME_ANSWER_ID)
        	);
    	
    	return new Question(itemTitle, itemAnswerId);
    }
}