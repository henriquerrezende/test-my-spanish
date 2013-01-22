package com.testmyspanish.persistence;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.testmyspanish.R;
import com.testmyspanish.persistence.FeedReaderContract.FeedAnswer;
import com.testmyspanish.persistence.FeedReaderContract.FeedExam;
import com.testmyspanish.persistence.FeedReaderContract.FeedQuestion;

public class DbHelper extends SQLiteOpenHelper {
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String TAG = "DbHelper";
	
	private final Context fContext;

	private static final String SQL_CREATE_EXAMS =
			"CREATE TABLE " + FeedReaderContract.FeedExam.TABLE_NAME + " (" +
					FeedReaderContract.FeedExam._ID + " INTEGER PRIMARY KEY," +
					FeedReaderContract.FeedExam.COLUMN_NAME_NUMBER_OF_QUESTIONS + " INTEGER)";

	private static final String SQL_CREATE_QUESTIONS =
	    "CREATE TABLE " + FeedReaderContract.FeedQuestion.TABLE_NAME + " (" +
	    FeedReaderContract.FeedQuestion._ID + " INTEGER PRIMARY KEY," +
	    FeedReaderContract.FeedQuestion.COLUMN_NAME_QUESTION + TEXT_TYPE + COMMA_SEP +
	    FeedReaderContract.FeedQuestion.COLUMN_NAME_CORRECT_ANSWER_ID + " INTEGER)";

	private static final String SQL_CREATE_ANSWERS =
		"CREATE TABLE " + FeedReaderContract.FeedAnswer.TABLE_NAME + " (" +
		FeedReaderContract.FeedAnswer._ID + " INTEGER PRIMARY KEY," +
		FeedReaderContract.FeedAnswer.COLUMN_NAME_ANSWER + TEXT_TYPE + COMMA_SEP +
		FeedReaderContract.FeedAnswer.COLUMN_NAME_QUESTION_ID + " INTEGER)";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
		fContext = context;
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_EXAMS);
        db.execSQL(SQL_CREATE_QUESTIONS);
        db.execSQL(SQL_CREATE_ANSWERS);
        populate(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
    private void populate(SQLiteDatabase db) {
    	populateExams(db);
    	populateQuestions(db);
    	populateAnswers(db);
    }

	private void populateAnswers(SQLiteDatabase db) {
		ContentValues values = new ContentValues();                            
        Resources res = fContext.getResources();
         
        XmlResourceParser xml = res.getXml(R.xml.answers_records);
        try
        {
            int eventType = xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if ((eventType == XmlPullParser.START_TAG) &&(xml.getName().equals("answer"))){
                    String answer = xml.getAttributeValue(null, FeedAnswer.COLUMN_NAME_ANSWER);
                    String questionId = xml.getAttributeValue(null, FeedAnswer.COLUMN_NAME_QUESTION_ID);
                    values.put(FeedAnswer.COLUMN_NAME_ANSWER, answer);
                    values.put(FeedAnswer.COLUMN_NAME_QUESTION_ID, questionId);
                    db.insert(FeedAnswer.TABLE_NAME, null, values); 
                }
                eventType = xml.next();
            }
        }
        catch (XmlPullParserException e)
        {       
            Log.e(TAG, e.getMessage(), e);
        }
        catch (IOException e)
        {
            Log.e(TAG, e.getMessage(), e);
             
        }           
        finally
        {           
            xml.close();
        }
		
	}

	private void populateQuestions(SQLiteDatabase db) {
        ContentValues values = new ContentValues();                            
        Resources res = fContext.getResources();
         
        XmlResourceParser xml = res.getXml(R.xml.questions_records);
        try
        {
            int eventType = xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if ((eventType == XmlPullParser.START_TAG) &&(xml.getName().equals("question"))){
                	String question = xml.getAttributeValue(null, FeedQuestion.COLUMN_NAME_QUESTION);
                	String correctAnswerId = xml.getAttributeValue(null, FeedQuestion.COLUMN_NAME_CORRECT_ANSWER_ID);
                    values.put(FeedQuestion.COLUMN_NAME_QUESTION, question);
                    values.put(FeedQuestion.COLUMN_NAME_CORRECT_ANSWER_ID, correctAnswerId);
                    db.insert(FeedQuestion.TABLE_NAME, null, values); 
                }
                eventType = xml.next();
            }
        }
        catch (XmlPullParserException e)
        {       
            Log.e(TAG, e.getMessage(), e);
        }
        catch (IOException e)
        {
            Log.e(TAG, e.getMessage(), e);
             
        }           
        finally
        {           
            xml.close();
        }
	}

	private void populateExams(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        Resources res = fContext.getResources();

        XmlResourceParser xml = res.getXml(R.xml.exams_records);
        try
        {
            int eventType = xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if ((eventType == XmlPullParser.START_TAG) &&(xml.getName().equals("exam"))){
                	String numberOfQuestions = xml.getAttributeValue(null, FeedExam.COLUMN_NAME_NUMBER_OF_QUESTIONS);
                    values.put(FeedExam.COLUMN_NAME_NUMBER_OF_QUESTIONS, numberOfQuestions);
                    db.insert(FeedExam.TABLE_NAME, null, values);
                }
                eventType = xml.next();
            }
        }
        catch (XmlPullParserException e)
        {
            Log.e(TAG, e.getMessage(), e);
        }
        catch (IOException e)
        {
            Log.e(TAG, e.getMessage(), e);
        }
        finally
        {
            xml.close();
        }
	}
}