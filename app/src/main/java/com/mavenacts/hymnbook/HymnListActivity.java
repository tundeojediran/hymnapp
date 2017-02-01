package com.mavenacts.hymnbook;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
//import android.app.FragmentTransaction;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.mavenacts.hymnbook.adapter.TabsPagerAdapter;


public class HymnListActivity extends FragmentActivity implements
		ActionBar.TabListener, hymnFragment.Callbacks,
		appendixFragment.Callbacks{

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private int tabIndex;
    private SearchView searchView;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hymn_list);



		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		//actionBar.setHomeButtonEnabled(false);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		Tab tabArray = actionBar.newTab();
		tabArray.setText(R.string.tab_one);
		tabArray.setTabListener(this);
		actionBar.addTab(tabArray);
		tabArray = actionBar.newTab();
		tabArray.setText(R.string.tab_two);
		tabArray.setTabListener(this);
		actionBar.addTab(tabArray);

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});





	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		viewPager.setCurrentItem(arg0.getPosition());

		tabIndex = arg0.getPosition();

	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	public static final String EXTRA_ROWID = "rowid";

	@Override
	public void onItemSelected(String id) {


        switch (tabIndex) {
            case 0:
                Intent detailIntent = new Intent(this, HymnItemActivity.class);

                detailIntent.putExtra(HymnItemFragment.ARG_ITEM_ID, id);
                startActivity(detailIntent);
                break;
            case 1:
                Intent detailIntent2 = new Intent(this, AppendixItemActivity.class);

                detailIntent2.putExtra(AppendixItemFragment.ARG_ITEM_ID, id);
                startActivity(detailIntent2);
                break;

        }

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);

		return true;

	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//
//            case R.id.action_search:
//                Intent search = new Intent(getApplicationContext(),
//                        SearchResultsActivity.class);
//                startActivity(search);
//
//                return true;
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//	}



}


