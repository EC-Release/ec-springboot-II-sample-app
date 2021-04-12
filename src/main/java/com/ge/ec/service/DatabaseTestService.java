/*
 * Copyright (c) 2016 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 *
 * @author Avneesh Srivastava
 *
 */
package com.ge.ec.service;

import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public interface DatabaseTestService {
	JdbcTemplate getPostgreConnection(JSONObject dbDetails);
	JdbcTemplate getOracleConnection(JSONObject dbDetails);
	JdbcTemplate getSQLServerConnection(JSONObject dbDetails);
	DriverManagerDataSource checkDataSource(DriverManagerDataSource ds, JSONObject dbDetails);
}
