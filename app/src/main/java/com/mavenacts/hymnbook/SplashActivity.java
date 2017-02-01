package com.mavenacts.hymnbook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		TextView appname = (TextView) findViewById(R.id.appname);
		Typeface custom_font = Typeface.createFromAsset(getAssets(),
				"fonts/kabob.ttf");
		appname.setTypeface(custom_font);

		Thread logoTimer = new Thread() {
			public void run() {
				try {
					int logoTimer = 0;
					while (logoTimer < 3000) {// in milliseconds
						sleep(100);
						logoTimer = logoTimer + 100;
					}
					Intent i = new Intent(getApplicationContext(),
							HymnListActivity.class);
					startActivity(i);

				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
				}
			}
		};
		logoTimer.start();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

}
