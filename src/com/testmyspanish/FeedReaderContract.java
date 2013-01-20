package com.testmyspanish;

import android.provider.BaseColumns;

public class FeedReaderContract {
	
    private FeedReaderContract() {}
	
	public static abstract class FeedQuestion implements BaseColumns {
		public static final String TABLE_NAME = "question";
		public static final String _ID = "questionId";
		public static final String COLUMN_NAME_TITLE = "title";
		public static final String COLUMN_NAME_ANSWER_ID = "answerId";
		public static final String COLUMN_NAME_NULLABLE = "null";
	}    
}
