package com.minahatami.myflickerapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private static final int THUMBSIZE = 120;
	private static final String TAG = "MyAdapter";
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
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
			viewHolder.imgView = (ImageView) view.findViewById(R.id.imgView);
			viewHolder.tvAuthor = (TextView) view.findViewById(R.id.tvAuthor);

			// store the holder with the view.
			view.setTag(viewHolder);

		} else {
			// we've just avoided calling findViewById() on resource every time
			// just use the viewHolder
			viewHolder = (ViewHolder) view.getTag();
		}

		FlickerFeedItem flickerFeedItem = (FlickerFeedItem) getItem(position);

		viewHolder.tvTitle.setText(flickerFeedItem.getTitle());
		viewHolder.tvAuthor.setText(flickerFeedItem.getAuthor());

		new DownloadImageTask(viewHolder.imgView).execute(flickerFeedItem.getMedia());
		
		Log.v(TAG, "currentReceipts.getImage(): " + flickerFeedItem.getMedia());

		return view;
	}


	private class ViewHolder {
		TextView tvTitle, tvAuthor;
		ImageView imgView;
	}
}
