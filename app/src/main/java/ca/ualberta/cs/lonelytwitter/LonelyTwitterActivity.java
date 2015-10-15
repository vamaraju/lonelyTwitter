package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private LonelyTwitterActivity activity = this;
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private ListView oldTweetsList;
	private ArrayAdapter<Tweet> adapter;
	private Button saveButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState); // model
		setContentView(R.layout.main); // view

		bodyText = (EditText) findViewById(R.id.body); // model
		saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear); // model
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); // model

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { // controller
				setResult(RESULT_OK); // model
				String text = bodyText.getText().toString(); // model
				tweets.add(new NormalTweet(text)); // controller
				saveInFile(); // model
				// dataObject.saveInFile() // controller
				adapter.notifyDataSetChanged(); // view
			}
		});


		clearButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) { // controller
				tweets.clear(); // controller
				saveInFile(); // model
				adapter.notifyDataSetChanged(); // view
			}
		});


		oldTweetsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(activity, EditTweetActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart(); // view
		loadFromFile(); // model
		adapter = new ArrayAdapter<Tweet>(this,// model
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter); // controller
		adapter.notifyDataSetChanged(); // view
	}

	private void loadFromFile() { // model
		try {
			FileInputStream fis = openFileInput(FILENAME); // model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); // model
			Gson gson = new Gson(); // model
			// https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
			Type arraylistType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); // model
			tweets = gson.fromJson(in, arraylistType); // controller


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>(); // model
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e); // view
		}
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0); // model
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos)); // model
			Gson gson = new Gson(); // model
			gson.toJson(tweets, out); // controller
			out.flush(); // controller
			fos.close(); // controller
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e); // view
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e); // view
		}
	}


	public ArrayList<Tweet> getTweets() {
		return tweets;
	}


	public EditText getBodyText() {
		return bodyText;
	}


	public Button getSaveButton() {
		return saveButton;
	}


	public ListView getOldTweetsList() {
		return oldTweetsList;
	}



}