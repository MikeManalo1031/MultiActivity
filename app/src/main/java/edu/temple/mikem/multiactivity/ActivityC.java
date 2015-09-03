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

public class ActivityC extends Activity {

    TextView messageTextView;
    Button openActivityAButton;
    Button openActivityBButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        //Associate button and textview objects to layout widgets
        messageTextView = (TextView) findViewById(R.id.activity_c_text);
        openActivityAButton = (Button) findViewById(R.id.activity_c_button1);
        openActivityBButton = (Button) findViewById(R.id.activity_c_button2);

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

                Intent launchActivityA = new Intent(ActivityC.this,ActivityA.class);
                ArrayList<String> dataString = intentDataString;
                //adds data to ArrayList
                dataString.add("opened by Activity C");
                //passes data through intent
                launchActivityA.putStringArrayListExtra("Transfer", dataString);
                startActivity(launchActivityA);
            }
        };
        openActivityAButton.setOnClickListener(openA);

        //Launch an intent that goes to Activity B after the button is pressed
        View.OnClickListener openB = new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent launchActivityB = new Intent(ActivityC.this,ActivityB.class);
                ArrayList<String> dataString = intentDataString;
                dataString.add("opened by Activity C");
                launchActivityB.putStringArrayListExtra("Transfer", dataString);
                startActivity(launchActivityB);
            }
        };
        openActivityBButton.setOnClickListener(openB);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_c, menu);
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
