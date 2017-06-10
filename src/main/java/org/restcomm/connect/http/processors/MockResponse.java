package org.restcomm.connect.http.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class MockResponse implements Processor{

	public void process(Exchange exchange) throws Exception {
		
		exchange.getOut().setBody("Sms Sent...");
	}

}