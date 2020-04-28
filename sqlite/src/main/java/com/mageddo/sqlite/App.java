package com.mageddo.sqlite;

import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws Throwable {

		final DataSource dc = createDatasource();

		try (final Connection c = dc.getConnection()) {
			executeStm(c, "CREATE TABLE FOO(MSG VARCHAR(255), ACTIVE BOOLEAN)");
			executeStm(c, "INSERT INTO FOO VALUES ('It Works from SQLITE!!!', true)");
			try (var ps = c.prepareStatement("SELECT * FROM FOO ")) {
				try (final ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						System.out.printf("msg=%s, active=%b%n", rs.getString("MSG"), rs.getString("ACTIVE"));
					}
				}
			}
		}
	}

	private static void executeStm(Connection c, String sql) throws SQLException {
		try (final var stm = c.prepareStatement(sql)) {
			stm.executeUpdate();
		}
	}

	private static SQLiteDataSource createDatasource() throws IOException {
		final var dbFile = Files.createTempFile("sqlite", ".db");
		dbFile.toFile().deleteOnExit();
		final var dc = new SQLiteDataSource();
		dc.setUrl("jdbc:sqlite:" + dbFile);
		return dc;
	}
}
