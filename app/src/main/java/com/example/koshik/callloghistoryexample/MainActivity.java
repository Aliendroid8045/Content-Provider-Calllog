package com.example.koshik.callloghistoryexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

//calllog.calls.content_uri
//person name, person phone no., duration of call, date
//call type = 1 for incoming, 2 = outgoing call, 3 = missed call
// add permission
//put all in arrayList--> arrayAdapter-->listView
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void displayCallLog(View view) {
        Intent intent = new Intent(MainActivity.this, CallLogActivity.class);
        startActivity(intent);

    }
}
