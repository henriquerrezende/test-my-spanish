package com.learnspanish;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.learnspanish.FeedReaderContract.FeedQuestion;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.*;

public class FeedQuestionDbHelper extends SQLiteOpenHelper {
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String TAG = "FeedQuestionDbHelper";
	
	private final Context fContext;

	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + FeedReaderContract.FeedQuestion.TABLE_NAME + " (" +
	    FeedReaderContract.FeedQuestion._ID + " INTEGER PRIMARY KEY," +
	    FeedReaderContract.FeedQuestion.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
	    FeedReaderContract.FeedQuestion.COLUMN_NAME_ANSWER_ID + " INTEGER)";
	
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedQuestionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
		fContext = context;
		Log.d(TAG, "FeedQuestionDbHelper instantiated!");
    }
    public void onCreate(SQLiteDatabase db) {
    	Log.d(TAG, "onCreate(");
        db.execSQL(SQL_CREATE_ENTRIES);
        populate(db);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    
    private void populate(SQLiteDatabase db) {
    	//Add default records to animals
        ContentValues values = new ContentValues();                            
        //Get xml resource file
        Resources res = fContext.getResources();
         
        //Open xml file
        XmlResourceParser xml = res.getXml(R.xml.questions_records);
        try
        {
            //Check for end of document
            int eventType = xml.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //Search for record tags
                if ((eventType == XmlPullParser.START_TAG) &&(xml.getName().equals("question"))){
                    //Record tag found, now get values and insert record
                    String title = xml.getAttributeValue(null, FeedQuestion.COLUMN_NAME_TITLE);
                    String color = xml.getAttributeValue(null, FeedQuestion.COLUMN_NAME_ANSWER_ID);
                    values.put(FeedQuestion.COLUMN_NAME_TITLE, title);
                    values.put(FeedQuestion.COLUMN_NAME_ANSWER_ID, color);
                    Log.d(TAG, "Going to insert in db");
                    long result = db.insert(FeedQuestion.TABLE_NAME, null, values); 
                    Log.d(TAG, "" + result);
                }
                eventType = xml.next();
            }
        }
        //Catch errors
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
            //Close the xml file
            xml.close();
        }
    }

}