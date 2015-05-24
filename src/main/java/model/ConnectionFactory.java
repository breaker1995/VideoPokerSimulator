package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the connection to the database.
 * <p>
 * Remote connection is to the free web hosting servicer: <a
 * href="http://www.heliohost.org/">Heliohost</a>.
 *
 * @author breaker
 */
public class ConnectionFactory {

	/**
	 * The host URL.
	 */
	private static String DB_URL = "jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g";
	/**
	 * The user.
	 */
	private static String USER = "H_RI3MDM";
	/**
	 * The password.
	 */
	private static String PASS = "kassai";

	/**
	 * Configures the connection to the database with the given values.
	 * 
	 * @param url
	 *            the URL of the database
	 * @param user
	 *            the user
	 * @param pass
	 *            the password
	 */
	public static void setConnection(String url, String user, String pass) {
		DB_URL = url;
		USER = user;
		PASS = pass;
	}

	/**
	 * Creates a connection to the database.
	 *
	 * @return the created connection
	 */
	private static Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Calls the {@link #createConnection() createConnection} method and returns
	 * the {@link Connection Connection}.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		return createConnection();
	}
}