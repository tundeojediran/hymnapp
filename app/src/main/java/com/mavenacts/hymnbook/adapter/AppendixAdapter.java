/*package com.mavenacts.hymnbook.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mavenacts.hymnbook.Appendix;
import com.mavenacts.hymnbook.R;

public class AppendixAdapter extends BaseAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	private List<Appendix> appendix;

	public AppendixAdapter(Activity activity, List<Appendix> appendix) {
		this.activity = activity;
		this.appendix = appendix;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return appendix.size();
	}

	@Override
	public Object getItem(int location) {
		// TODO Auto-generated method stub
		return appendix.get(location);
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

		TextView apx_no = (TextView) convertView.findViewById(R.id.hymn_no);
		TextView apx_title = (TextView) convertView.findViewById(R.id.title);
		
		 * TextView hymn_content = (TextView) convertView
		 * .findViewById(R.id.hymn_content);
		 

		Appendix apx_item = appendix.get(position);

		apx_no.setText(apx_item.getApxNo());
		apx_title.setText(apx_item.getApxTitle());

		return convertView;

	}
}
*/