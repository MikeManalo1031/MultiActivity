package edu.temple.mikem.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityA extends Activity {

    TextView messageTextView;
    Button openActivityBButton;
    Button openActivityCButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);

        //Associate button and textview objects to layout widgets
        messageTextView = (TextView) findViewById(R.id.activity_a_text);
        openActivityBButton = (Button) findViewById(R.id.activity_a_button1);
        openActivityCButton = (Button) findViewById(R.id.activity_a_button2);

        //Retrieve the ArrayList from intent
        Intent receivedIntent = getIntent();
        final ArrayList<String> intentDataString = receivedIntent.getStringArrayListExtra("Transfer");
        //Parse Arraylist then sets Text to textview
        String text = ArrayParser.parse(intentDataString);
        messageTextView.setText(text);

        //Launch an intent that goes to Activity B after the button is pressed
        View.OnClickListener openB = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent launchActivityB = new Intent(ActivityA.this, ActivityB.class);
                //Add data to ArrayList and pass ArrayList to child
                if (intentDataString != null) {
                    ArrayList<String> dataString = intentDataString;
                    dataString.add("opened by Activity A");
                    launchActivityB.putStringArrayListExtra("Transfer", dataString);
                    startActivity(launchActivityB);
                } else {
                    ArrayList<String> dataString = new ArrayList<>();
                    dataString.add("opened by Activity A");
                    launchActivityB.putStringArrayListExtra("Transfer", dataString);

                }
                startActivity(launchActivityB);
            }
        };
        openActivityBButton.setOnClickListener(openB);

        //Launch an intent that goes to Activity B after button is pressed
        View.OnClickListener openC = new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Add data to ArrayList and pass ArrayList to child
                Intent launchActivityC = new Intent(ActivityA.this, ActivityB.class);
                if (intentDataString != null) {
                    ArrayList<String> dataString = intentDataString;
                    dataString.add("opened by Activity A");
                    launchActivityC.putStringArrayListExtra("Transfer", dataString);
                    startActivity(launchActivityC);
                } else {
                    ArrayList<String> dataString = new ArrayList<>();
                    dataString.add("opened by Activity A");
                    launchActivityC.putStringArrayListExtra("Transfer", dataString);

                }
            }
        };
        openActivityCButton.setOnClickListener(openC);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
