package com.testmyspanish.persistence.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.testmyspanish.activity.MainActivity;
import com.testmyspanish.model.Exam;
import com.testmyspanish.persistence.FeedReaderContract;

public class ExamDAO {

	public static Exam readRandomExam() {
    	SQLiteDatabase db = MainActivity.DB_HELPER.getWritableDatabase();

    	// Define a projection that specifies which columns from the database
    	// you will actually use after this query.
    	String[] projection = {
    	    FeedReaderContract.FeedExam.COLUMN_NAME_NUMBER_OF_QUESTIONS
    	    };

    	Cursor cursor = db.query(
    	    FeedReaderContract.FeedExam.TABLE_NAME,  // The table to query
    	    projection,                               // The columns to return
    	    "",                                // The columns for the WHERE clause
    	    null,                            // The values for the WHERE clause
    	    null,                                     // don't group the rows
    	    null,                                     // don't filter by row groups
    	    "RANDOM()",                                 // The sort order
    	    "1"											// limit
    	    );

    	cursor.moveToFirst();

    	Integer numerOfQuestions = cursor.getInt(
        	    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedExam.COLUMN_NAME_NUMBER_OF_QUESTIONS)
        	);

    	return new Exam(numerOfQuestions);
    }
}
