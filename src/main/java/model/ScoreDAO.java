package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Data Access Object, which is used to get data from the remote/local database.
 * <p>
 * Methods get the connection from the {@link ConnectionFactory
 * ConnectionFactory} class.
 *
 * @author breaker
 */
public class ScoreDAO {

	/**
	 * The current highscore's details represented by the {@link Score Score} class.
	 */
	private Score score = new Score("Nobody", 0, new Date(new java.util.Date().getTime()));
	/**
	 * The logger of this class.
	 */
	static Logger LOGGER = LoggerFactory.getLogger(ScoreDAO.class);

	/**
	 * The constructor.
	 * 
	 * It first loads the configuration, and then retrieves the highscore from
	 * the database.
	 */
	public ScoreDAO() {
		Properties prop = new Properties();
		InputStream is = getClass().getClassLoader().getResourceAsStream(
				"config.properties");
		if (is == null) {
			LOGGER.error("config.properties could not be found.");
		}
		try {
			prop.load(is);
		} catch (Exception e) {
			LOGGER.error("config.properties is malformed.", e);
		}

		ConnectionFactory.setConnection(prop.getProperty("URL"),
				prop.getProperty("USER"), prop.getProperty("PASS"));

		Score sc = getHighscore();
		score.setName(sc.getName());
		score.setScore(sc.getScore());
		score.setDate(sc.getDate());
	}

	/**
	 * Retrieves the highscore from the database.
	 * 
	 * @return the highscore from the database, or a default value if there is no highscore yet
	 */
	public static Score getHighscore() {
		Date d = new Date(new java.util.Date().getTime());
		Score sc = new Score("Nobody", 0, d);
		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "SELECT * " + "FROM score;";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				sc = new Score(rs.getString(1), rs.getInt(2), rs.getDate(3));
				LOGGER.trace("Loaded the following highscore: "+sc);
			}
		} catch (SQLException e) {
			try (Connection conn = ConnectionFactory.getConnection()) {
				String query = "CREATE TABLE score " + "( "
						+ "name VARCHAR2(50), " + "score NUMBER(10)," + "dat date"
						+ ");";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.executeUpdate();
				query = "INSERT INTO score (name, score, dat)"
						+ "VALUES (?,?,?);";
				ps = conn.prepareStatement(query);
				ps.setString(1, "Nobody");
				ps.setInt(2, 0);
				ps.setDate(3, d);
				ps.executeUpdate();
				LOGGER.info("Created default score: "+sc);

			} catch (SQLException ex) {
				LOGGER.error("Error loading score or creating default score.", e);
			}
		}
		return sc;
	}

	/**
	 * Returns the score.
	 * 
	 * @return the score
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * Sets the score and the name.
	 * 
	 * @param val
	 *            the score to set
	 * @param name
	 *            the name to set
	 */
	public void setScore(int val, String name) {
		Date d = new Date(new java.util.Date().getTime());
		try (Connection conn = ConnectionFactory.getConnection()) {
			String query = "DELETE FROM score;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			query = "INSERT INTO score (name, score, dat)"
					+ "VALUES (?,?,?);";
			ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setInt(2, val);
			ps.setDate(3, (java.sql.Date) d);
			ps.executeUpdate();

		} catch (SQLException ex) {
			LOGGER.error("Error updating highscore.",ex);
		}
		score.setName(name);
		score.setScore(val);
		score.setDate(d);
		LOGGER.info("New highscore achieved: "+score);
	}
}
