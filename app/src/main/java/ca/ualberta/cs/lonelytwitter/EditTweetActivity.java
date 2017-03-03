package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class EditTweetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);


    }

    @Override
    protected void onStart(){
        super.onStart();
        Bundle extras = getIntent().getExtras();
        String oldTweet;
        TextView textView = (TextView)findViewById(R.id.textView);

        oldTweet =  extras.getString("StringList");
        textView.setText(oldTweet);
    }
}