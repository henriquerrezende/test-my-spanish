package com.testmyspanish;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.question_layout);

	    // Create the text view
	    TextView textView = (TextView) findViewById(R.id.question);
	    textView.setText("Generate question here");

//	    OnClickListener radio_listener = new OnClickListener() {
//	        public void onClick(View v) {
//	            onRadioButtonClick(v);
//	        }
//	    };
	    
	    List<String> myStringArray = new ArrayList<String>();
	    myStringArray.add("hello");
	    myStringArray.add("bye");
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.radio_button_layout, R.id.answer_option_radio_button, myStringArray);
	    
	    ListView listView = (ListView) findViewById(R.id.listview);
	    listView.setAdapter(adapter);
	    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    listView.setOnItemClickListener(mMessageClickedHandler); 

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
    
 // Create a message handling object as an anonymous class.
    private OnItemClickListener mMessageClickedHandler = new OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
    	    TextView textView = (TextView) findViewById(R.id.question);
    	    textView.setText("Question answered!");
        }
    };

}