package com.ge.predix.repository;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.core.BaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PostgresConnection {

	@Autowired
	Environment env;

	public BaseConnection getConnection() {
		final String url = env.getProperty("load.jdbc.uri");
		final Properties props = new Properties();
		props.setProperty("user",env.getProperty("load.jdbc.user"));
		props.setProperty("password",env.getProperty("load.jdbc.password"));
		props.setProperty("ssl","true");
		try {
			final BaseConnection conn = (BaseConnection) DriverManager.getConnection(url, props);
			return conn;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close(BaseConnection connection){
		try {
			if (connection != null && !connection.isClosed()){
				connection.close();
			}
		}catch (final SQLException e) {
			e.printStackTrace();
		}
	}
}
