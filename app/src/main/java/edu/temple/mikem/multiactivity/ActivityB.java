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

public class ActivityB extends Activity {

    TextView messageTextView;
    Button openActivityAButton;
    Button openActivityCButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        //Associate button and textview objects to layout widgets
        messageTextView = (TextView) findViewById(R.id.activity_b_text);
        openActivityAButton = (Button) findViewById(R.id.activity_b_button1);
        openActivityCButton = (Button) findViewById(R.id.activity_b_button2);

        //Retrieve the ArrayList from intent
        Intent receivedIntent = getIntent();
        final ArrayList<String> intentDataString = receivedIntent.getStringArrayListExtra("Transfer");
        //Parse Arraylist then sets Text to textview
        String text = ArrayParser.parse(intentDataString);
        messageTextView.setText(text);

        //Launch an intent that goes to Activity A after the button is pressed
        View.OnClickListener openA = new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent launchActivityA = new Intent(ActivityB.this,ActivityA.class);
                ArrayList<String> dataString = intentDataString;
                //adds data to ArrayList
                dataString.add("opened by Activity B");
                //passes data through intent
                launchActivityA.putStringArrayListExtra("Transfer", dataString);
                startActivity(launchActivityA);
            }
        };
        openActivityAButton.setOnClickListener(openA);

        //Launch an intent that goes to Activity C after the button is pressed
        View.OnClickListener openC = new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent launchActivityC = new Intent(ActivityB.this,ActivityC.class);
                ArrayList<String> dataString = intentDataString;
                //adds data to ArrayList
                dataString.add("opened by Activity B");
                //passes data through intent
                launchActivityC.putStringArrayListExtra("Transfer", dataString);
                startActivity(launchActivityC);
            }
        };
        openActivityCButton.setOnClickListener(openC);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_b, menu);
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
