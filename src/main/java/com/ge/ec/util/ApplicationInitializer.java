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
package com.ge.ec.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;

public class ApplicationInitializer implements ApplicationContextInitializer<AnnotationConfigEmbeddedWebApplicationContext> {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void initialize(
			AnnotationConfigEmbeddedWebApplicationContext applicationContext) {
		Cloud cloud = getCloud();
		ConfigurableEnvironment appEnvironment = applicationContext
				.getEnvironment();
		if (cloud != null) {
			log.info("Cloud Environment");
			appEnvironment.addActiveProfile("cloud");
			//Do anything in cloud environment
		}else{
			log.info("Local Environment");
			//Do anything in local environment
		}
	}

	private Cloud getCloud() {
		try {
			CloudFactory cloudFactory = new CloudFactory();
			return cloudFactory.getCloud();
		} catch (CloudException ce) {
			return null;
		}
	}

}
