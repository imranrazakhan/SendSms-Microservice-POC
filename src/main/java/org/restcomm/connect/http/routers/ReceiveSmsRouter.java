package org.restcomm.connect.http.routers;

import org.apache.camel.builder.RouteBuilder;
import org.restcomm.connect.http.processors.MockResponse;
import org.restcomm.connect.http.providers.GsonJaxRsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ReceiveSmsRouter extends RouteBuilder {
	
	@Bean(name = "jsonProvider")
	public GsonJaxRsProvider jsonProvider() {
    	return new GsonJaxRsProvider();
	}
    

	
	@Override
	public void configure() throws Exception {
		
		from("cxfrs://http://0.0.0.0:9191/restcomm/2012-04-24/?resourceClasses=org.restcomm.connect.http.SmsMessages&providers=#jsonProvider")
		.choice()
			.when(header("opertionName").isEqualTo("putSmsMessage"))
				.transform(simple("in put"))
				.log("input message")
			.when(header("opertionName").isEqualTo("getSmsMessage"))
				.transform(simple("get sms"))
		.end()
		.process(new MockResponse());
        //.toD("direct:${header.operationName}");
		
	}

}
