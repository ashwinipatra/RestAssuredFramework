package com.api.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jdbi.v3.core.Jdbi;

import com.api.constants.Constants;
import com.api.exceptions.FWException;
import com.api.loggers.Log4jLogger;
import com.api.models.Student;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public final class DBUtils {

	private static final HikariDataSource dataSource;
	private static final Log4jLogger logger = new Log4jLogger(DBUtils.class);

	private DBUtils() {

	}

	static {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(Constants.getDbUrl());
		hikariConfig.setUsername(Constants.getDbUserName());
		hikariConfig.setPassword(Constants.getDbUserPassword());

		try {
			dataSource = new HikariDataSource(hikariConfig);
			logger.info("Initializing the db connection: " + dataSource);
		} catch (Exception e) {
			logger.error("Could not initialize connection", e);
			throw new FWException("Could not initialize connection");
		}
	}

	//Create Connection
	public static Connection getConnection() {
		try {
			Connection conn = dataSource.getConnection();
			logger.info("Successfully conntected to database: ");
			return conn;
		} catch (SQLException e) {
			logger.logAndThrow("Error connecting to db, terminating program", e);
			throw new FWException("Error executing the query, Terminating program", e);
		}
	}

	//Execute and store select query
	public static Student getStudent(int id) {
		String query = "SELECT id, student_name from students where id = ?";
		try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Student student = new Student();
			if (rs.next()) {
				student.setId(rs.getInt("id"));
				student.setStudentName("student_name");
			}
			logger.info("Successfully executed query: " + ps.toString().split(":")[1]);
			return student;

		} catch (SQLException e) {
			logger.error("Error executing the query, Terminating program", e);
			throw new FWException("Error executing the query, Terminating program", e);

		}
	}

	//Execute and store insert query
	public static void insertIntoStudent(int id, String name) {

		String query = "INSERT INTO students(id,student_name) values(?,?)";

		try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

			ps.setInt(1, id);
			ps.setString(2, name);

			int rowCount = ps.executeUpdate();
			logger.info("Successfully executed query: " + ps.toString().split(":")[1]);
			logger.info("Interted " + rowCount + " row(s) in the table");

		} catch (SQLException e) {
			logger.error("Error executing the query, Terminating program", e);
			throw new FWException("Error executing the query, Terminating program", e);

		}
	}

	//jdbi wrapper to get List of object
	public static <T> List<T> getResultSet(String query, Class<T> claaz) {

		Jdbi jdbi = Jdbi.create(dataSource);

		return jdbi
				.withHandle(handle -> handle.select(query)
						.mapToBean(claaz)
						.list());

	}
	
	//jdbi wrapper to get single object
	public static <T> T getResult(String query, Class<T> claaz) {

		Jdbi jdbi = Jdbi.create(dataSource);

		return jdbi.withHandle(handle -> handle
				.select(query)
						.mapToBean(claaz).one());

	}

}
