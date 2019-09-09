package com.huntington.cdo.techyouth.rssservice.service;

import com.huntington.cdo.techyouth.rssservice.model.Rss;
import com.huntington.cdo.techyouth.rssservice.service.exception.ServerErrorException;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service; 
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class RssServiceImpl implements RssService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RssServiceImpl.class);
	
	@Override
	public List<Rss> extractItems(String link)
	{
		LOGGER.debug("RssServiceImpl::getFeed(): retrieving feed <" + link + ">");
		
		List<Rss> listOfContent = new ArrayList<Rss>(); 
		URL url;
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed;
		try {
			url = new URL(link);
			feed = input.build(new XmlReader(url));
			@SuppressWarnings("unchecked")
			List<SyndEntry> entries = feed.getEntries();
			for (SyndEntry entry : entries) {
				listOfContent.add(new Rss(entry.getTitle(), entry.getLink(), entry.getAuthor(), entry.getPublishedDate()));
			}
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (FeedException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
			return listOfContent;
		
	
		
	
	}
/*
	@Override
	public Rss updateCustomer(Rss customer)
	{
		LOGGER.debug("CustomerServiceImpl::updateCustomer(): updating customer " + customer.toString());
		return customer;
	}

	@Override
	public Rss addCustomer(Rss customer)
	{
		LOGGER.debug("CustomerServiceImpl::addCustomer(): adding customer " + customer.toString());
		return(new Rss("1234", "Dan", "Manson", "danman@invalidemail.com"));
	}

	@Override
	public void deleteCustomer(String customerId)
	{
		LOGGER.debug("CustomerServiceImpl::getCustomer(): deleting customer <" + customerId + ">");
		throw new ServerErrorException("testing exception chain");
	}
	*/

}
