/*
 * Copyright 2017-2018 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mageddo.micronaut.config;

import com.zaxxer.hikari.HikariConfig;
import io.micronaut.context.annotation.EachProperty;
import lombok.Data;

/**
 * Allows the configuration of Hikari JDBC data sources. All properties on
 * {@link HikariConfig} are available to be configured.
 *
 * If the url, driver class, username, or password are missing, sensible defaults
 * will be provided when possible. If no configuration beyond the datasource name
 * is provided, an in memory datastore will be configured based on the available
 * drivers on the classpath.
 *
 * @author James Kleeh
 * @since 1.0
 */
@Data
@EachProperty(value = "datasources", primary = "default")
public class DatasourceConfiguration {

	private String jdbcUrl;
	private String username;
	private String password;
	private String driverClassName;
	private String connectionTestQuery;
	private int initializationFailTimeout;
	private int minimumIdle;
	private int maximumPoolSize;
	private boolean autoCommit;
	private String transactionIsolation;

}
