package com.mageddo.sqlite;

import org.sqlite.SQLiteDataSource;
import org.sqlite.core.NativeDB;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {

	public static void main(String[] args) throws Throwable {

		System.load("/data/Downloads/sqlite-jdbc-3.27.2.1/org/sqlite/native/Linux/x86_64/libsqlitejdbc.so");

//		native synchronized void _open_utf8(byte[] fileUtf8, int openFlags) throws SQLException;
		Method openMethod = NativeDB.class.getDeclaredMethod("_open_utf8", byte[].class, int.class);
		openMethod.setAccessible(true);
		NativeDB nativeDB = new NativeDB("", "", null);
		openMethod.invoke(nativeDB, new byte[256], 5);

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
