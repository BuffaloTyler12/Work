package com.huntington.cdo.techyouth.rssservice.controller;
//kjkjkjk
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory; 
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

import com.huntington.cdo.techyouth.rssservice.model.Rss;
import com.huntington.cdo.techyouth.rssservice.service.RssService;
import com.huntington.cdo.techyouth.rssservice.service.exception.ServerErrorException;

@RestController
public class RssServiceController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RssServiceController.class);
	private final RssService rssService;
	
	@Autowired
	public RssServiceController(final RssService rssService)
	{
		this.rssService = rssService;
	}
	
	@ApiOperation(value = "Retrieve an RSS feed",
			response = Rss.class)	
	@RequestMapping(value = "/feed", method = RequestMethod.GET, produces = {
			"application/json" })
	public List<Rss> getFeed(String url)
	{
		LOGGER.debug("Received request to fetch feed <" + url + ">");
		return rssService.extractItems(url);
	}
/*
	@ApiOperation(value = "Update a customer")
	@RequestMapping(value = "/customer", method = RequestMethod.PUT, consumes = {
			"application/json" })
	// DSC - also consider PATCH for updates of only sent fields/keys
	public Rss updateCustomer(@RequestBody @Valid final Rss customer)
	{
		LOGGER.debug("Received request to update customer {}", customer);
		try
		{
			Rss customerval = rssService.updateCustomer(customer);
			return customerval;
		}
		catch (RuntimeException rex)
		{
			LOGGER.error(rex.toString());
			throw rex;
		}
	}
	
	@ApiOperation(value = "Delete a single customer by id.")	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable String id)
	{
		LOGGER.debug("Received request to delete customer <" + id + ">");
		rssService.deleteCustomer(id);
	}

	@ApiOperation(value = "Add a new customer.",
			response = Rss.class)
	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = {
			"application/json" })
	public Rss saveCustomer(@RequestBody @Valid final Rss customer)
	{
		LOGGER.debug("Received request to add new customer {}", customer);
		
		return rssService.addCustomer(customer);
	}
	*/

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleServerErrorException(ServerErrorException e)
	{
		return e.getMessage();
	}	

}
