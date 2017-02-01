package com.mavenacts.hymnbook;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;

import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.mavenacts.hymnbook.adapter.DatabaseAdapter;

public class appendixFragment extends ListFragment {

	private DatabaseAdapter db;
	private SimpleCursorAdapter dataAdapter;
	private Context mContext;

	boolean mDualPane;
	int mCurCheckPosition = 0;

	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private Callbacks mCallbacks = sDummyCallbacks;
	private int mActivatedPosition = ListView.INVALID_POSITION;

	public interface Callbacks {
		public void onItemSelected(String id);
	}

	private static Callbacks sDummyCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(String id) {
		}

	};

	public appendixFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater hymn, ViewGroup hymnLayout,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return hymn.inflate(R.layout.activity_hymn, hymnLayout, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

		// data attributes
		mContext = getActivity();

		// create new DBAdapter
		db = new DatabaseAdapter(mContext);
		db.open();

		Cursor c = db.getAppendix();

		// The desired columns to be bound
		String[] columns = new String[] { DatabaseAdapter.APX_ID,
				DatabaseAdapter.APX_TITLE };

		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.hymn_no, R.id.title,

		};

		// create the adapter using the cursor pointing to the desired data
		// as well as the layout information
		dataAdapter = new SimpleCursorAdapter(getActivity(),
				R.layout.hymn_list_item, c, columns, to, 0);
		setListAdapter(dataAdapter);

		db.close();

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		if (!(activity instanceof Callbacks)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (Callbacks) activity;
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		++position;
		String newPosition = String.valueOf(position);

		super.onListItemClick(l, v, position, id);

		mCallbacks.onItemSelected(newPosition);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.hymn_list, menu);
//
//
//    }








//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle bargs) {
//
//        String sort = "SortColumn ASC";
//        String[] grid_columns = new String[] { "ColumnA", "ColumnB", "Etc..." };
//        String grid_whereClause = "ColumnToSearchBy LIKE ?";
//
//        if (!TextUtils.isEmpty(grid_currentQuery)) {
//            return new CursorLoader(getActivity(), DataProvider.CONTENT_URI, grid_columns, grid_whereClause, new String[] { grid_currentQuery + "%" }, sort);
//        }
//
//        return new CursorLoader(getActivity(), db, grid_columns, null, null, sort);
//        return new CursorLoader()
//    }

}
