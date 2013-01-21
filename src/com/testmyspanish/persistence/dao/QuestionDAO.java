package com.testmyspanish.persistence.dao;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.testmyspanish.activity.MainActivity;
import com.testmyspanish.model.Answer;
import com.testmyspanish.model.Question;
import com.testmyspanish.persistence.FeedReaderContract;

public class QuestionDAO {

	public static Question readRandomQuestion() {
    	SQLiteDatabase db = MainActivity.DB_HELPER.getWritableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    FeedReaderContract.FeedQuestion._ID,
    	    FeedReaderContract.FeedQuestion.COLUMN_NAME_QUESTION,
    	    FeedReaderContract.FeedQuestion.COLUMN_NAME_CORRECT_ANSWER_ID
    	    };
    	
    	Cursor cursor = db.query(
    	    FeedReaderContract.FeedQuestion.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    "",                                // The columns for the WHERE clause
    	    null,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    "RANDOM()",                                 // The sort order
    	    "1"											// limit
    	    );
   
    	cursor.moveToFirst();

    	Long questionId = cursor.getLong(
        	    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedQuestion._ID)
        	);
    	String question = cursor.getString(
        	    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedQuestion.COLUMN_NAME_QUESTION)
        	);
    	Long answerId = cursor.getLong(
        	    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedQuestion.COLUMN_NAME_CORRECT_ANSWER_ID)
        	);
    	
    	List<Answer> answers = AnswerDAO.readAnswersForQuestion(questionId);
    	return new Question(question, answerId, answers);
    }
}
