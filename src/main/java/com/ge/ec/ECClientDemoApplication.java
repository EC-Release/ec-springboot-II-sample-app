/**
 *  Copyright (c) 2021 General Electric Company. All rights reserved.
 *
 *  The copyright to the computer software herein is the property of
 *  General Electric Company. The software may be used and/or copied only
 *  with the written permission of General Electric Company or in accordance
 *  with the terms and conditions stipulated in the agreement/contract
 *  under which the software has been supplied.
 *
 *  @author Avneesh Srivastava
 * 
 */
package com.ge.ec;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;

import com.ge.ec.util.ApplicationInitializer;

@SpringBootApplication
public class ECClientDemoApplication {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(ECClientDemoApplication.class)
				.initializers(new ApplicationInitializer())
				.run(args);
		applicationContext.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {

			@Override
			public void onApplicationEvent(ContextClosedEvent event) {
				System.out.println("On Application Shutdown");
			}
		});
	}
	

	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.initializers(new ApplicationInitializer());
		application.sources(ECClientDemoApplication.class);
		return application;
	}
}

