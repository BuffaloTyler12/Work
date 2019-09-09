package com.huntington.cdo.techyouth.rssservice.service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServerErrorException extends RuntimeException
{
	private static final long serialVersionUID = -7262430813063775857L;

	public ServerErrorException()
	{
		super();
	}
	
	public ServerErrorException(String message)
	{
		super(message);
	}
}
