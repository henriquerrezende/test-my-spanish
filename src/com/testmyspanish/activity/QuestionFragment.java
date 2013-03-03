package com.testmyspanish.activity;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.testmyspanish.R;
import com.testmyspanish.model.Answer;
import com.testmyspanish.model.Question;
import com.testmyspanish.persistence.dao.QuestionDAO;

public class QuestionFragment extends Fragment {

	private Long correctAnswerId;

	public QuestionFragment() {
	}

//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//	    super.onActivityCreated(savedInstanceState);
////	    getActivity().setContentView(R.layout.exam_layout);
//
//	    Question question = QuestionDAO.readRandomQuestion();
//	    correctAnswerId = question.getCorrectAnswerId();
//
//	    // Create the text view
//	    TextView textView = (TextView) getActivity().findViewById(R.id.question);
//	    textView.setText(question.getQuestion());
//
//	    RadioGroup answersOptionsGroup = (RadioGroup) getActivity().findViewById(R.id.answerOptionsGroup);
////	    RadioButton butao = new RadioButton(getActivity().getApplicationContext());
////	    answersOptionsGroup.addView(butao);
//	    for(Answer answerOption : question.getAnswers()) {
//	    	RadioButton answerButton = new RadioButton(getActivity().getApplicationContext());
//	    	answerButton.setId(answerOption.getId());
//	    	answerButton.setText(answerOption.getAnswer());
//	    	answerButton.setOnClickListener(answerButtonClickHandler);
//	    	answersOptionsGroup.addView(answerButton);
//	    }
//	}

//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setRetainInstance(true);
//	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View questionFragmentView = inflater.inflate(R.layout.question_fragment, container, false);

	    Question question = QuestionDAO.readRandomQuestion();
	    correctAnswerId = question.getCorrectAnswerId();

	    TextView questionTextView = (TextView) questionFragmentView.findViewById(R.id.question);
	    questionTextView.setText(question.getQuestion());

	    RadioGroup answersOptionsGroup = (RadioGroup) questionFragmentView.findViewById(R.id.answerOptionsGroup);
	    for(Answer answerOption : question.getAnswers()) {
	    	RadioButton answerButton = new RadioButton(questionFragmentView.getContext());
	    	answerButton.setId(answerOption.getId());
	    	answerButton.setText(answerOption.getAnswer());
	    	answerButton.setOnClickListener(answerButtonClickHandler);
	    	answersOptionsGroup.addView(answerButton);
	    }
	    return questionFragmentView;
	}

	private OnClickListener answerButtonClickHandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			RadioButton answerButton = (RadioButton) v;
			if (Long.valueOf(answerButton.getId()) == correctAnswerId) {
				answerButton.setTextColor(Color.GREEN);
				Toast.makeText(getActivity().getApplicationContext(), "Question answered correctly!", Toast.LENGTH_SHORT).show();
			} else {
				answerButton.setTextColor(Color.RED);
				Toast.makeText(getActivity().getApplicationContext(), "Question answered wrong!", Toast.LENGTH_SHORT).show();
			}

		    disableAnswerButtons(answerButton);
		}

		private void disableAnswerButtons(View radioButtonView) {
			RadioGroup answersOptionsGroup = (RadioGroup) radioButtonView.getParent();
		    Integer radioButtonCount = answersOptionsGroup.getChildCount();

		    for (int i=0; i<radioButtonCount; i++){
		    	answersOptionsGroup.getChildAt(i).setEnabled(false);
		    }
		}
	};

}