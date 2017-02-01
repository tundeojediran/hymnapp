package com.mavenacts.hymnbook;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mavenacts.hymnbook.adapter.DatabaseAdapter;

/**
 * Created by danieltunde on 26-February-2015.
 */
public class SearchFragment extends ListFragment {

    public static final String QUERY = "";
    private DatabaseAdapter db;
    private SimpleCursorAdapter dataAdapter;
    private Context mContext;
    private Cursor data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_hymn, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        // data attributes
        mContext = getActivity();

        // create new DBAdapter
        db = new DatabaseAdapter(mContext);
        db.open();

        String query = (String) getArguments().get(SearchFragment.QUERY);

        if (getArguments().containsKey(QUERY)){

            try {

                data = db.searchHymn(query);

                // The desired columns to be bound
                String[] columns = new String[] { DatabaseAdapter.HYMN_ID,
                        DatabaseAdapter.HYMN_TITLE };

                // the XML defined views which the data will be bound to
                int[] to = new int[] { R.id.hymn_no, R.id.title,

                };

                // create the adapter using the cursor pointing to the desired data
                // as well as the layout information
                dataAdapter = new SimpleCursorAdapter(mContext,
                        R.layout.hymn_list_item, data, columns, to, 0);
                setListAdapter(dataAdapter);

            } catch (Exception e) {
                // TODO: handle exception

                e.printStackTrace();

            }

        }


        db.close();


    }

}


