package com.minahatami.myflickerapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context mContext;
	List<FlickerFeedItem> items = new ArrayList<FlickerFeedItem>();

	public MyAdapter(Context context) {
		this.mContext = context;
	}

	public void add(FlickerFeedItem item) {
		items.add(item);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		if (position >= items.size() || position < 0) {
			return null;
		}
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return -1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		View view = convertView;

		if (view == null) {

			// inflate the layout
			LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
			view = inflater.inflate(R.layout.activity_flickerlist_items,
					parent, false);

			// well set up the ViewHolder
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) view.findViewById(R.id.tvTitle);
			viewHolder.media = (TextView) view.findViewById(R.id.tvMedia);
			viewHolder.author = (TextView) view.findViewById(R.id.tvAuthor);

			// store the holder with the view.
			view.setTag(viewHolder);

		} else {
			// we've just avoided calling findViewById() on resource every time
			// just use the viewHolder
			viewHolder = (ViewHolder) view.getTag();
		}

		FlickerFeedItem flickerFeedItem = (FlickerFeedItem) getItem(position);

		/*
		 * Log.v(TAG, "view is null: " + (view == null)); Log.v(TAG,
		 * "tvName is null: " + (view.findViewById(R.id.tvName) == null));
		 */

		viewHolder.title.setText(flickerFeedItem.getTitle());
		viewHolder.media.setText(flickerFeedItem.getMedia());
		viewHolder.author.setText(flickerFeedItem.getAuthor());

		return view;
	}

	private class ViewHolder {
		TextView title, media, author;
	}
}
