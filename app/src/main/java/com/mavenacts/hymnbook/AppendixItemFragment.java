package com.mavenacts.hymnbook;

import android.app.ActionBar;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mavenacts.hymnbook.adapter.DatabaseAdapter;

public class AppendixItemFragment extends Fragment {
	public static final String ARG_ITEM_ID = "_id";
	private DatabaseAdapter db;
	private Cursor data;
	private Context mContext;
	private SimpleCursorAdapter dataAdapter;
	private ActionBar actionBar;

	public AppendixItemFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hymn_item, container, false);

        db = new DatabaseAdapter(getActivity());
        db.open();

        // data attributes
        mContext = getActivity();

        String id = (String) getArguments().get(AppendixItemFragment.ARG_ITEM_ID);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            try {
                data = db.getAppendixItem(id);

                String title = data.getString(data
                        .getColumnIndex(DatabaseAdapter.APX_TITLE));

                String no = data.getString(data
                        .getColumnIndex(DatabaseAdapter.APX_ID));

                String scripture = data.getString(data
                        .getColumnIndex(DatabaseAdapter.APX_SCRIPTURE));

                String stanza = data.getString(data
                        .getColumnIndex(DatabaseAdapter.APX_STANZAS));

                // Method using regexp split
                StringBuilder sb1 = new StringBuilder();
                for (String line : stanza.split("\n")) {
                    if (sb1.length() > 0) {
                        sb1.append("<br>");
                    }
                    sb1.append(line);
                }

                String processedStanza = sb1.toString();
                Spanned result = Html.fromHtml(processedStanza);


                String author = data.getString(data
                        .getColumnIndex(DatabaseAdapter.APX_AUTHOR));


                actionBar = getActivity().getActionBar();
                actionBar.setTitle(no + "  " + title);

                ((TextView) rootView.findViewById(R.id.hymn_scripture)).setText(scripture);
                ((TextView) rootView.findViewById(R.id.stanza)).setText(result);
                ((TextView) rootView.findViewById(R.id.author)).setText(author);


            } catch (Exception e) {
                // TODO: handle exception

                e.printStackTrace();

            }

             db.close();
         }


        return rootView;
    }



	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
//		// create new DBAdapter
//		db = new DatabaseAdapter(getActivity());
//		db.open();
//
//		// data attributes
//		mContext = getActivity();
//
//		String id = (String) getArguments().get(
//				AppendixItemFragment.ARG_ITEM_ID);
//
//		if (getArguments().containsKey(ARG_ITEM_ID)) {
//			try {
//				data = db.getAppendixItem(id);
//
//				String title = data.getString(data
//						.getColumnIndex(DatabaseAdapter.APX_TITLE));
//
//				String no = data.getString(data
//						.getColumnIndex(DatabaseAdapter.APX_ID));
//
//				actionBar = getActivity().getActionBar();
//				actionBar.setTitle(no + "  " + title);
//
//				String[] newcolumn = new String[] { DatabaseAdapter.APX_ID,
//
//				DatabaseAdapter.APX_SCRIPTURE, DatabaseAdapter.APX_STANZAS,
//						DatabaseAdapter.APX_AUTHOR };
//
//				// the XML defined views which the data will be bound to
//				int[] to = new int[] { R.id.hymn_no, R.id.hymn_scripture,
//						R.id.stanza, R.id.author
//
//				};
//
//
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//
//			}
//
//		}
//		db.close();
	}
}
