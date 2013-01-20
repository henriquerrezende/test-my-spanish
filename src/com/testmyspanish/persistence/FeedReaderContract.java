package com.testmyspanish.persistence;

import android.provider.BaseColumns;

public class FeedReaderContract {
	
    private FeedReaderContract() {}
	
	public static abstract class FeedQuestion implements BaseColumns {
		public static final String TABLE_NAME = "question";
		public static final String _ID = "questionId";
		public static final String COLUMN_NAME_QUESTION = "question";
		public static final String COLUMN_NAME_CORRECT_ANSWER_ID = "correctAnswerId";
	}   
	
	public static abstract class FeedAnswer implements BaseColumns {
		public static final String TABLE_NAME = "answer";
		public static final String _ID = "answerId";
		public static final String COLUMN_NAME_ANSWER = "answer";
		public static final String COLUMN_NAME_QUESTION_ID = "questionId";
	} 
}
