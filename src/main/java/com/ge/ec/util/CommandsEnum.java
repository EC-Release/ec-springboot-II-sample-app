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

public enum CommandsEnum {
	CLIENT("$command -oa2 \"$oa2\" -hst \"$hst\" -csc \"$csc\" -cid \"$cid\" -aid \"$aid\" -tid \"$tid\" -grp \"$grp\" -mod \"client\" -lpt \"$lpt\" -pxy \"$pxy\" -hca $healthcheckport -dur $tokenRefreshDur -dbg"),
	SERVER("$command -oa2 \"$oa2\" -hst \"$hst\" -csc \"$csc\" -cid \"$cid\" -aid \"$aid\" -grp \"$grp\" -sst \"$sst\" -zon \"$zon\" -rht \"$rht\" -mod \"server\" -rpt \"$rpt\" -pxy \"$pxy\" -hca $healthcheckport -dur $tokenRefreshDur -dbg");
	private String command;

	CommandsEnum(String command) {
		this.setCommand(command);
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public static CommandsEnum retreiveCommand(String agentType){
		if(agentType.equalsIgnoreCase("SERVER"))
			return SERVER;
		else
			return CLIENT;

	}

}
