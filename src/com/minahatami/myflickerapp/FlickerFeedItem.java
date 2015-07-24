package com.minahatami.myflickerapp;

public class FlickerFeedItem {
	String title, media, author;
	//JSON Object

	public FlickerFeedItem(String title, String media, String author){
		this.title = title;
		this.media = media;
		this.author = author;
	}
	
	public String getTitle() {
		return title;
	}

	public String getMedia() {
		return media;
	}

	public String getAuthor() {
		return author;
	}
}
