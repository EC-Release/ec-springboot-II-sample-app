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

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
@Component
public class CommandCreator {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	@Qualifier("oauthRestTemplate")
	OAuth2RestTemplate oauthRestTemplate;
	@Value("${security.oauth2.client.accessTokenUri}")
    private String tokenUrl;
    @Value("${security.oauth2.client.clientId}")
    private String clientId;
    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;
    @Value("${ec.tunnelid}")
    private String tid;
    @Value("${ec.agentid}")
    private String aid;
	@Value("${ec.groupid}")
	private String grp;
    @Value("${ec.mode}")
    private String mode;
    @Value("${ec.websocket}")
    private String websocket;
    @Value("${ec.lpt}")
    private String lpt;
    @Value("${ec.healthcheckport}")
    private String healthcheckport;
    @Value("${ec.tokenRefreshDur}")
    private String tokenRefreshDur;
	@Value("${ec.sst}")
	private String sst;
	@Value("${ec.zon}")
	private String zon;
	@Value("${ec.rht}")
	private String rht;
	@Value("${ec.rpt}")
	private String rpt;
    @Autowired
	Properties props;
    public boolean verifyProxyUrl(){
    	try{
    		new URI(props.getProxyUrl());
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }
	public String commandCreate(String environmentType){
		String appliedCommand = CommandsEnum.retreiveCommand(mode).getCommand();
		log.info(String.format("Command Exctracted for %s mode", mode));
		if(verifyProxyUrl() && props.getApplyProxy().equalsIgnoreCase("true")){
			log.info(String.format("Valid Proxy received, applying proxy in EC Command, Proxy: %s", props.getProxyUrl()));
			appliedCommand = appliedCommand.replace("$pxy",props.getProxyUrl().trim());
		}else{
			appliedCommand = appliedCommand.replace("-pxy \"$pxy\"","");
		}

		if(mode.trim().equalsIgnoreCase("server")) {
			appliedCommand = appliedCommand
					.replace("$sst",sst.trim())
					.replace("$zon",zon.trim())
					.replace("$rht",rht.trim())
					.replace("$rpt",rpt.trim());
		} else if(mode.trim().equalsIgnoreCase("client")) {
			appliedCommand = appliedCommand
					.replace("$tid",tid.trim())
					.replace("$lpt",lpt.trim());
		}

		appliedCommand = appliedCommand
					/*Not needed since Auto-refresh is available*/
					//.replace("$tkn", oauthRestTemplate.getAccessToken().toString().trim())
					.replace("$command",environmentType.trim())
					.replace("$oa2",tokenUrl.trim())
					.replace("$hst",websocket.trim())
					.replace("$csc",clientSecret.trim())
					.replace("$cid",clientId.trim())
					.replace("$aid",aid.trim())
					.replace("$grp",grp.trim())
					.replace("$healthcheckport",healthcheckport.trim())
					.replace("$tokenRefreshDur",tokenRefreshDur.trim())
					.replace("$mod",mode.trim());
		log.info(String.format("Command Generated: %s",appliedCommand));
		return appliedCommand;
	}

}
