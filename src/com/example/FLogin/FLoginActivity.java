package com.example.FLogin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class FLoginActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("Dear Mr. Dahlstroem,\n\nI am an Android application created by Stefan Fodor.\nI just want to say 'Hello World'!\n\nBest Regards,\nA java code");
        setContentView(tv);
    }
}