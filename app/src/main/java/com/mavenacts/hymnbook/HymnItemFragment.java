package com.mavenacts.hymnbook;

import android.app.ActionBar;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mavenacts.hymnbook.adapter.DatabaseAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HymnItemFragment extends Fragment {

	public static final String ARG_ITEM_ID = "_id";
	private DatabaseAdapter db;
	private Cursor data;
	private Context mContext;
	private SimpleCursorAdapter dataAdapter;
	private ActionBar actionBar;


	public HymnItemFragment() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_hymn_item, container, false);

        db = new DatabaseAdapter(getActivity());
        db.open();

        // data attributes
        mContext = getActivity();

        String id = (String) getArguments().get(HymnItemFragment.ARG_ITEM_ID);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
            try {
                data = db.getHymnItem(id);

                String title = data.getString(data
                        .getColumnIndex(DatabaseAdapter.HYMN_TITLE));

                String no = data.getString(data
                        .getColumnIndex(DatabaseAdapter.HYMN_ID));

                String scripture = data.getString(data
                        .getColumnIndex(DatabaseAdapter.HYMN_SCRIPTURE));

                String stanza = data.getString(data
                        .getColumnIndex(DatabaseAdapter.HYMN_STANZAS));

                //Log.v("stanza",stanza);
                //String pattern =  "(.*Chorus:\n+(.*)\n(.*)2)";
//                String pattern =  "(Chorus:\n(.*\n)+[\\^2]2)";

                StringBuilder sb1 = new StringBuilder();

                // Regex pattern for finding chorus in each hymn stanza
                String pattern =  "(Chorus:\n(.*\n)+\n[\\^2])";
                Pattern p = Pattern.compile(pattern);



                Matcher m = p.matcher(stanza);
                if (m.find()){
                    Log.v("found", m.group());
                    String chorus = m.group().substring(0, m.group().length()-1);
                    Log.v("chorus", chorus);
                    String processedChorus = "<i>"+chorus+"</i>";
                    Log.v("pChorus", processedChorus);
                    String newStanza = stanza.replaceAll("(Chorus:\n(.*\n)+\n[\\^2])", processedChorus.concat("2"));
                    Log.v("stanza",newStanza);

                    for (String line : newStanza.split("\n")) {

                        if (sb1.length() > 0) {
                            sb1.append("<br>");

                        }
                        sb1.append(line);

                    }

                }

                else {
                    Log.v("found", "no match");

                    // Method using regexp split
                    for (String line : stanza.split("\n")) {

                        if (sb1.length() > 0) {
                            sb1.append("<br>");

                        }
                        sb1.append(line);

                    }

                }








               //String c = stanza.split("Chorus=")[1].split("2")[0];

                String processedStanza = sb1.toString();
//                StringBuilder sb2 = new StringBuilder(processedStanza);
//                for (String s : stanza.){
//                    int chorusPosition = sb2.indexOf("Chorus");
//                    int twoPosition = sb2.indexOf("2");
//                }




                Spanned result = Html.fromHtml(processedStanza);


                String author = data.getString(data
                        .getColumnIndex(DatabaseAdapter.HYMN_AUTHOR));


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

	}
}
