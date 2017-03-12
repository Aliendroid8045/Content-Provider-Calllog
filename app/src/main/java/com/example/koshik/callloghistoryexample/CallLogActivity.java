package com.example.koshik.callloghistoryexample;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class CallLogActivity extends AppCompatActivity {

    ListView callLogListView;
    ArrayList<String> logList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        callLogListView = (ListView) findViewById(R.id.logListView);

        getCallDetails();
    }

    private void getCallDetails() {

        ContentResolver resolver = getContentResolver();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor managedCursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);

        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        managedCursor.moveToFirst();
        logList = new ArrayList<>();
        do {
            String nameCall = managedCursor.getString(name);
            String phNumber = managedCursor.getString(number);
            String callDate = managedCursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            String dir = null;
            int code = Integer.parseInt(managedCursor.getString(type));
            switch (code) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            logList.add(phNumber + "\n" + callDate + "\n" + callDayTime + "\n" + nameCall + "\n" + callDuration + "\n" + dir);
        }
        while (managedCursor.moveToNext());
        managedCursor.close();
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, logList);
        callLogListView.setAdapter(adapter);
    }

}



