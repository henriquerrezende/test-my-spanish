package com.testmyspanish.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.testmyspanish.activity.MainActivity;
import com.testmyspanish.model.Answer;
import com.testmyspanish.persistence.FeedReaderContract;

public class AnswerDAO {

	public static List<Answer> readAnswersForQuestion(Long questionId) {
    	SQLiteDatabase db = MainActivity.DB_HELPER.getWritableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    FeedReaderContract.FeedAnswer._ID,
    	    FeedReaderContract.FeedAnswer.COLUMN_NAME_ANSWER
    	    };
    	
    	String[] conditions = { questionId.toString() };
    	
    	Cursor cursor = db.query(
    	    FeedReaderContract.FeedAnswer.TABLE_NAME,  			   // The table to query
    	    projection,                               			   // The columns to return
    	    FeedReaderContract.FeedAnswer.COLUMN_NAME_QUESTION_ID + " = ?", // The columns for the WHERE clause
    	    conditions,                            				   // The values for the WHERE clause
    	    null,                                                  // don't group the rows
    	    null,                                                  // don't filter by row groups
    	    null,                                                  // The sort order
    	    null								                   // limit
    	    );
   
    	List<Answer> answers = new ArrayList<Answer>();
    	if (cursor.moveToNext()) {
    		do {
    			Integer answerId = cursor.getInt(
    	        	    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedAnswer._ID));
    			String answerString = cursor.getString(
    					cursor.getColumnIndexOrThrow(FeedReaderContract.FeedAnswer.COLUMN_NAME_ANSWER)); 
    			answers.add(new Answer(answerId, answerString));
    		} while (cursor.moveToNext());
		  }
		  if (cursor != null && !cursor.isClosed()) {
		     cursor.close();
		}
    	
    	return answers;
    }
}
