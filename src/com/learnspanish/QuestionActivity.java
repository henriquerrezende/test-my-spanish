package com.learnspanish;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TextView;

public class QuestionActivity extends Activity {
	
	private static final String TAG = "QuestionActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

	    super.onCreate(savedInstanceState);
	    
    	FeedQuestionDbHelper dbHelper = new FeedQuestionDbHelper(this);

	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    	    
	    Question question= readFirstQuestion(dbHelper);

	    textView.setText(question.getTitle());

	    // Set the text view as the activity layout
	    setContentView(textView);
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