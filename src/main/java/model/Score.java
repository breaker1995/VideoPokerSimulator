package model;

import java.util.Date;

/**
 * The class that represents a highscore.
 * 
 * @author breaker
 *
 */
public class Score {

	/**
	 * The name of the player who achieved this highscore.
	 */
	private String name;
	/**
	 * The actual highscore.
	 */
	private int score;
	/**
	 * The date when this highscore was achieved.
	 */
	private Date date;

	/**
	 * Constructor that sets the fields with the given parameters.
	 * 
	 * @param name
	 *            The name
	 * @param score
	 *            The score
	 * @param date
	 *            The date
	 */
	public Score(String name, int score, Date date) {
		super();
		this.name = name;
		this.score = score;
		this.date = date;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return score + " by " + name + " on " + date;
	}

}
