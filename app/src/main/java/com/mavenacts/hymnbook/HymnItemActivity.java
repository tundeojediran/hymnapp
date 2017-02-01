package com.mavenacts.hymnbook;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HymnItemActivity extends FragmentActivity {

    private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hymn_item);

        actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);

        Bundle arguments = new Bundle();

		arguments.putString(HymnItemFragment.ARG_ITEM_ID, getIntent()
				.getExtras().getString(HymnItemFragment.ARG_ITEM_ID));

		HymnItemFragment fragment = new HymnItemFragment();
		fragment.setArguments(arguments);

		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.hymn_item_layout, fragment, "hymnitemfragment");
		transaction.commit();

//        Intent intent = getIntent();
//        intent.getStringArrayExtra(newcolumn);



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hymn_list, menu);
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			Intent about = new Intent(getApplicationContext(),
					AboutActivity.class);
			startActivity(about);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
