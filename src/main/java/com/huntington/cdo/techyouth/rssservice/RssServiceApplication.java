package com.huntington.cdo.techyouth.rssservice;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/*
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
*/


import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.huntington.cdo.techyouth.rssservice.model.Rss;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;

@SpringBootApplication
public class RssServiceApplication 
{

	
	public static void main(String[] args) throws Exception {
		
		
		
	 	CredentialsProvider credsProvider = new BasicCredentialsProvider();
	    credsProvider.setCredentials(
	            new AuthScope(AuthScope.ANY),
	            new NTCredentials("H001046", "BuffaloT1214", "L5CG9196P91", "HBIUSERS"));
	    CloseableHttpClient httpclient = HttpClients.custom()
	            .setDefaultCredentialsProvider(credsProvider)
	            .build();
	    try {
	        HttpGet httpget = new HttpGet("https://teams.lb.hban.us/sites/232f6966/d9e90fe7/_layouts/15/listfeed.aspx?List=%7BA5D5B520%2D7E84%2D4D58%2DA17A%2DC09580A4722F%7D&Source=https%3A%2F%2Fteams%2Elb%2Ehban%2Eus%2Fsites%2F232f6966%2Fd9e90fe7%2FService%5FInterfaces%2FForms%2FAllItems%2Easpx");

	        System.out.println("Executing request " + httpget.getRequestLine());
	        CloseableHttpResponse response = httpclient.execute(httpget);
	        try {
	            System.out.println("----------------------------------------");
	            System.out.print("Status line: ");
	            System.out.println(response.getStatusLine());
	            System.out.print("response.getEntity: ");
	            System.out.println(response.getEntity());
	            System.out.print("response.toString(): ");
	            System.out.println(response.toString());
	            
	            //SpringApplication.run(RssServiceApplication.class, args);
	            
	            List<Rss> result  = new ArrayList<Rss>();
	            	 

	            //grabs XML source code and stores it as a string
	            String xEntries = EntityUtils.toString(response.getEntity());
	            //gets rid of any extra characters in front of XML that may cause probs (in the buffer)
	            xEntries= xEntries.trim().replaceFirst("^([\\W]+)<", "<");
	            
	            //document interface represents the entire XML doc
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            //Doc is needed in order to build the string of XML for the SyndFeed
	            Document doc = builder.parse(new InputSource(new StringReader(xEntries)));
	            
	            SyndFeedInput input = new SyndFeedInput();
	            SyndFeed feed = input.build(doc);
	            List<SyndEntry> entries = feed.getEntries();
	            
	            for (SyndEntry entry : entries) {
	            	result.add(new Rss(entry.getTitle(), entry.getLink(), entry.getAuthor(), entry.getPublishedDate()));
	            }
	            for (Rss file : result) {
	            	System.out.println(file.toString());
	            	System.out.println();
	            }
	          
	            EntityUtils.consume(response.getEntity());
	        } finally {
	            response.close();
	        }
	    } catch(Exception e) {
	    	e.printStackTrace();
	    	
	    }
	    
	    finally {
	        httpclient.close();
	    }
	    
	}
}



