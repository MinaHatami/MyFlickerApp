package com.minahatami.myflickerapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity implements INotify {

	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initializeListView();
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (adapter.getCount() == 0) {
			// TODO: prepare the data
			DownloadFilesTask task = new DownloadFilesTask(this);
			task.execute(new String[] { "http://www.flickr.com/services/feeds/photos_public.gne?tags=soccer&format=json" });
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// super.onListItemClick(l, v, position, id);

		FlickerFeedItem flickerFeedItem = (FlickerFeedItem) adapter
				.getItem(position);
		String url = flickerFeedItem.getLink();

		Log.i("onListItemClick", "position: " + position + " , url: " + url);

		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		
		// Verify that the intent will resolve to an acivity
		if (i.resolveActivity(getPackageManager()) != null) {
			startActivity(i);
		}
		else{
			Toast.makeText(getApplicationContext(), "Link cannot be opened!", Toast.LENGTH_SHORT).show();
		}
	}

	private void initializeListView() {
		// TODO: get listview
		ListView listView = getListView();

		// TODO: set header or footer of adapter
		LayoutInflater inflater = getLayoutInflater();
		View header = inflater.inflate(R.layout.header, listView, false);
		listView.addHeaderView(header, null, false);

		// TODO: set adapter
		adapter = new MyAdapter(this);

		// TODO: set listview events
		listView.setAdapter(adapter);

	}

	@Override
	public void onNotify(String response) {
		String str = response.substring("jsonFlickrFeed(".length(),
				response.length() - 1);
		Log.i("Get URL", "Downloaded string: " + str);
		try {
			JSONObject jObject = new JSONObject(str);

			JSONArray items = jObject.getJSONArray("items");
			for (int i = 0; i < items.length(); i++) {
				String title = items.getJSONObject(i).getString("title");
				String link = items.getJSONObject(i).getString("link");
				String media = items.getJSONObject(i).getJSONObject("media")
						.getString("m");
				String author = items.getJSONObject(i).getString("author");
				Log.i("title", "title : " + title);

				FlickerFeedItem flickerFeedItem = new FlickerFeedItem(i, title,
						link, media, author);

				adapter.add(flickerFeedItem);

				// Log.i("onNotify", "items: " + i + title);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
