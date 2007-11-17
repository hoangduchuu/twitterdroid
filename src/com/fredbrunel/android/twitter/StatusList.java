package com.fredbrunel.android.twitter;

import jtwitter.TwitterConnection;
import jtwitter.TwitterResponse;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.EditText;

public class StatusList extends Activity {	
	
	private TwitterConnection twitter = new TwitterConnection("fbrunel", "wulfgar");
	
	private OnClickListener editTextListener = new OnClickListener() {
		public void onClick(View v) {
			EditText edit = (EditText)v;
			String text = edit.getText().toString();
			try {
				TwitterResponse status = twitter.updateStatus(text);
				edit.setText("");
			} catch (Exception e) {
				// nothing;
			}
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        try {
        	TwitterResponse statuses = twitter.getFriendsTimeline();
        	
        	setContentView(R.layout.main);

            EditText edit = (EditText)findViewById(R.id.message);
            edit.setOnClickListener(editTextListener);
        
        	ListView list = (ListView)findViewById(R.id.list);
            list.setAdapter(new StatusAdapter(this, statuses));

        } catch (Exception e) {
           	Log.e("Crashed", e.getMessage(), e);
        }
    }
}

// [TODO] Unicode does not seem to be recognized (Japanese characters on the public timeline)

/*
  NotificationManager nm = NotificationManager.from(this);
  Notification n = new Notification();
  n.statusBarIcon = R.drawable.twitalert;
  n.statusBarTickerText = "hello android";
  n.statusBarBalloonText = "clicked!";
  //nm.notifyWithText(0, "hello android", NotificationManager.LENGTH_SHORT, n);
  nm.notify(0, n);
*/