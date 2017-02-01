/*package com.mavenacts.hymnbook.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mavenacts.hymnbook.Hymns;
import com.mavenacts.hymnbook.R;

public class HymnAdapter extends BaseAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	private List<Hymns> hymns;

	public HymnAdapter(Activity activity, List<Hymns> hymns) {
		this.activity = activity;
		this.hymns = hymns;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return hymns.size();
	}

	@Override
	public Object getItem(int location) {
		// TODO Auto-generated method stub
		return hymns.get(location);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.hymn_list_item, null);

		TextView hymn_no = (TextView) convertView.findViewById(R.id.hymn_no);
		TextView hymn_title = (TextView) convertView.findViewById(R.id.title);
		
		 * TextView hymn_content = (TextView) convertView
		 * .findViewById(R.id.hymn_content);
		 

		Hymns hymn_item = hymns.get(position);

		hymn_no.setText(hymn_item.getHymnNo());
		hymn_title.setText(hymn_item.getHymnTitle());

		return convertView;

	}

}
*/