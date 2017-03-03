package ca.ualberta.cs.lonelytwitter;

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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class EditTweetActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private EditText bodyText;
    private ListView oldTweetsList;
    private enum TweetListOrdering{DATE_ASCENDING, DATE_DESCENDING, TEXT_ASCENDING, TEXT_DESCENDING}
    private ArrayList<Tweet> tweetlist;
    private ArrayAdapter<Tweet> adapter;


    /**
     * Called when the activity is first created
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bodyText = (EditText) findViewById(R.id.body);
        Button saveButton = (Button) findViewById(R.id.save);
        Button clearButton = (Button) findViewById(R.id.clear);
        oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = bodyText.getText().toString();
                text = trimExtraSpaces(text);
                Tweet tweet = new NormalTweet(text);
                tweetlist.add(tweet);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                tweetlist.clear();
                deleteFile("file.save");
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        });

    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();

        adapter = new ArrayAdapter<Tweet>(this,
                R.layout.list_item, tweetlist);
        oldTweetsList.setAdapter(adapter);
    }

    /**
     * Removes extra spaces in the given String
     * @param inputString
     * @return string without extra spaces
     *
     */
    private String trimExtraSpaces(String inputString){
        inputString=inputString.replaceAll("\\s+", " ");
        return inputString;
    }
    /**
     * Loads tweets from file
     * @throws TweetTooLongException if it is too long
     * @exception FileNotFoundException if the file is not created
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson =new Gson();


            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2017-01-26
            tweetlist =  gson.fromJson(in, new TypeToken<ArrayList<NormalTweet>>(){}.getType());
            fis.close();

        } catch (FileNotFoundException e) {
            tweetlist = new ArrayList<Tweet>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    /**
     * Saves the tweets in file in JSON format.
     * @throws FileNotFoundException if folder not exists
     * @exception IOException if
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(tweetlist, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
