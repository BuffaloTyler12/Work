package com.huntington.cdo.techyouth.rssservice.model;

import java.util.Date;

public class Rss
{
	private String title;
	private String link;
	private String author;
	private Date pubDate;

	
	public Rss()
	{
		
	}
	public Rss(String title, String link, String author, Date pubDate)
	{
		
		this.title = title;
		this.link = link;
		this.author = author;
		this.pubDate = pubDate;
		
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author=author;
	}

	public Date getPubDate()
	{
		return pubDate;
	}

	public void setPubDate(Date pubDate)
	{
		this.pubDate = pubDate;
	}

	@Override
	public String toString()
	{
		return "Customer [title=" + title + ", link=" + link + ", author=" + author + ", pubDate=" + pubDate + "]";
	}
	
	
}
