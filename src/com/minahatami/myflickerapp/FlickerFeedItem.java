package com.minahatami.myflickerapp;

public class FlickerFeedItem {
	long id;
	String title, link, media, author;
	//JSON Object

	public FlickerFeedItem(long id, String title, String link, String media, String author){
		this.id = id;
		this.title = title;
		this.link = link;
		this.media = media;
		this.author = author;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getLink() {
		return link;
	}

	public String getMedia() {
		return media;
	}

	public String getAuthor() {
		return author;
	}
}
