package com.huntington.cdo.techyouth.rssservice.service;

import java.util.List;


import com.huntington.cdo.techyouth.rssservice.model.Rss;

public interface RssService
{
	List<Rss> extractItems(String link);
	/*
	Rss updateCustomer(Rss customer);
	Rss addCustomer(Rss customer);
	void deleteCustomer(String customerId);
	*/
}
