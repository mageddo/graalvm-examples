package com.mageddo.sqlite;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {

	public static void main(String[] args) throws Throwable {
		Class.forName("org.sqlite.JDBC");
		final DataSource dc = createDatasource();
		try (final Connection c = dc.getConnection()) {

			try (final PreparedStatement ps = c.prepareStatement("SELECT 'It Works from SQLITE!!!' AS MSG ")) {

				try (final ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						System.out.println(rs.getString("MSG"));
					}
				}

			}

		}
	}

	private static SQLiteDataSource createDatasource() {
		final SQLiteDataSource dc = new SQLiteDataSource();
		dc.setUrl("jdbc:sqlite:/tmp/db01.db");
		return dc;
	}
}
