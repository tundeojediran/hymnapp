package com.mavenacts.hymnbook;//package com.mavenacts.hymnbook;
//
//import android.app.ActionBar;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.support.v4.app.ListFragment;
//import android.support.v4.widget.SimpleCursorAdapter;
//import android.text.Html;
//import android.text.Spannable;
//import android.text.Spanned;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.mavenacts.hymnbook.adapter.DatabaseAdapter;
//
//public class HymnItemFragment extends ListFragment {
//
//	public static final String ARG_ITEM_ID = "_id";
//	private DatabaseAdapter db;
//	private Cursor data;
//	private Context mContext;
//	private SimpleCursorAdapter dataAdapter;
//	private ActionBar actionBar;
//    private Spannable spannable;
//
//	public HymnItemFragment() {
//		// TODO Auto-generated constructor stub
//
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		return inflater.inflate(R.layout.stanza_list, container, false);
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//
//		super.onActivityCreated(savedInstanceState);
//		// create new DBAdapter
//		db = new DatabaseAdapter(getActivity());
//		db.open();
//
//		// data attributes
//		mContext = getActivity();
//
//		String id = (String) getArguments().get(HymnItemFragment.ARG_ITEM_ID);
//
////		if (getArguments().containsKey(ARG_ITEM_ID)) {
//			try {
//				data = db.getHymnItem(id);
//
//				String title = data.getString(data
//						.getColumnIndex(DatabaseAdapter.HYMN_TITLE));
//
//				String no = data.getString(data
//						.getColumnIndex(DatabaseAdapter.HYMN_ID));
//
//                String stanza = data.getString(data
//                        .getColumnIndex(DatabaseAdapter.HYMN_STANZAS));
//                //Spanned result = Html.fromHtml(stanza);
//
//                //((TextView) rootView.findViewById(R.id.trans_txt)).setText(transArray.get(position));
//
//
//
//
//
//				actionBar = getActivity().getActionBar();
//				actionBar.setTitle(no + "  " + title);
//
//
//				String[] newcolumn = new String[] { DatabaseAdapter.HYMN_ID,
//
//				DatabaseAdapter.HYMN_SCRIPTURE, DatabaseAdapter.HYMN_STANZAS,
//						DatabaseAdapter.HYMN_AUTHOR };
//
//				// the XML defined views which the data will be bound to
//				int[] to = new int[] { R.id.hymn_no, R.id.hymn_scripture,
//						R.id.stanza, R.id.author
//
//				};
//
//
//                Log.e("NC", stanza);
//
//				// create the adapter using the cursor pointing to the desired
//				// data
//				// as well as the layout information
//
//
//				dataAdapter = new SimpleCursorAdapter(mContext,
//						R.layout.fragment_hymn_item, data, newcolumn, to, 0);
//
//				setListAdapter(dataAdapter);
//
//			} catch (Exception e) {
//				// TODO: handle exception
//
//				e.printStackTrace();
//
//			}
//
////        TextView view = null;
////        view = (TextView)view.findViewById(R.id.stanza);
////        String formattedText = getString(R.id.stanza);
////        Spanned result1 = Html.fromHtml(formattedText);
////        view.setText(result1);
//
//
////		}
//		db.close();
////            TextView view = null;
////            view = (TextView)view.findViewsWithText(R.id.stanza);
////            String formattedText = getString(R.id.stanza);
////            Spanned result = Html.fromHtml(formattedText);
////            view.setText(result);
//	}
//}
