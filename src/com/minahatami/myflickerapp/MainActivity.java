package com.minahatami.myflickerapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends ListActivity implements
		DownloadFilesTaskListener {

	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initializeListView();
	}

	@Override
	protected void onStart() {
		super.onStart();

		initializeListView();
	}

	private void initializeListView() {
		// TODO: prepare the data
		DownloadFilesTask task = new DownloadFilesTask(this);
		task.execute(new String[] { "http://www.flickr.com/services/feeds/photos_public.gne?tags=soccer&format=json" });

		// TODO: get listview
		ListView listView = getListView();
		// TODO: set adapter
		adapter = new MyAdapter(this);
		// TODO: set listview events
		listView.setAdapter(adapter);
		// TODO: set header or footer of adapter

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onNotify(String response) {
		// TODO Auto-generated method stub
		// Log.i("Get URL", "Downloaded string: " + response);
		String str = response.substring("jsonFlickrFeed(".length(),
				response.length() - 1);
		Log.i("Get URL", "Downloaded string: " + str);
		try {
			JSONObject jObject = new JSONObject(str);

			JSONArray items = jObject.getJSONArray("items");
			for (int i = 0; i < items.length(); i++) {
				String title = items.getJSONObject(i).getString("title");
				String media = items.getJSONObject(i).getString("media");
				String author = items.getJSONObject(i).getString("author");
				Log.i("title", "title : " + title);
				FlickerFeedItem flickerFeedItems = new FlickerFeedItem(title,
						media, author);
				adapter.add(flickerFeedItems);
				
			}
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
