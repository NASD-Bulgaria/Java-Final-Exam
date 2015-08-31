package query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Player;

public class Querys {

	private Connection connection = null;
	private PreparedStatement allUsernames = null;
	private PreparedStatement addPlayer = null;
	private PreparedStatement addPlayerProfile = null;
	private PreparedStatement findPassAndToken = null;
	private PreparedStatement updateBalance = null;
	private PreparedStatement findBalance = null;

	// constructor with no arguments
	public Querys() throws ClassNotFoundException {

		try {
			// start the connection to the database
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/players", "root", "galadriel");
			// initialize the preparedStatements
			allUsernames = connection
					.prepareStatement("Select login_name from player");
			addPlayer = connection
					.prepareStatement("insert into player (login_name, login_password, balance, token) values (?,?,?, ?)");
			addPlayerProfile = connection
					.prepareStatement("insert into player_profile (first_name, last_name, fk_player) values (?,?,?)");
			findPassAndToken = connection
					.prepareStatement("Select login_password, token from player where login_name = ?");
			findBalance = connection
					.prepareStatement("select balance, id from player where token = ?");
			updateBalance = connection
					.prepareStatement("update player set balance = ? where id = ?");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	} // end of the constructor

	// start the method for getting all usernames in the database
	public List<String> allUserNames() {
		List<String> list = new ArrayList<String>();
		ResultSet res = null;
		try {
			res = allUsernames.executeQuery();
			while (res.next()) {
				list.add(res.getString("login_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				res.close();
				
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		return list;
	}// end method AllUserNames()

	// start method for adding player in the database
	public int createPlayer(String username, String password, String token,
			double balance) {
		int result = 0;

		try {
			connection.setAutoCommit(false);
			addPlayer.setString(1, username);
			addPlayer.setString(2, password);
			addPlayer.setDouble(3, balance);
			addPlayer.setString(4, token);
			result = addPlayer.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
			}
		}
		return result;
	}// end method createPlayer

	// start method for adding playerProfile in the database
	public int creatPlayerProfile(String first_name, String last_name,
			int id_profile) {
		int result = 0;

		try {
			connection.setAutoCommit(false);
			addPlayerProfile.setString(1, first_name);
			addPlayerProfile.setString(2, last_name);
			addPlayerProfile.setInt(3, id_profile);
			result = addPlayerProfile.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}// end method createPlayerProfile

	// start method for finding password and token from database by username
	public Player findPassToken(String username) {
		Player player = new Player();
		ResultSet resultSet = null;
		try {
			findPassAndToken.setString(1, username);
			resultSet = findPassAndToken.executeQuery();
			while (resultSet.next()) {
				player = new Player(resultSet.getString("login_password"),
						resultSet.getString("token"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}// end method findPassToken

	// start method for finding balance and id from database by token
	public Player findBalID(String token) {
		Player player = new Player();
		ResultSet res = null;
		try {
			findBalance.setString(1, token);
			res = findBalance.executeQuery();
			while (res.next()) {
				player = new Player(res.getDouble("balance"), res.getInt("id"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
		return player;
	}// end method findBalId

	// start method for updating the balance in database by id
	public int updateBal(int id, double balance) {
		int result = 0;
		try {
			connection.setAutoCommit(false);
			updateBalance.setDouble(1, balance);
			updateBalance.setInt(2, id);
			result = updateBalance.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}// end method updateBal

	// start method for closing the connection to database
	public void close() {
		try {
			connection.close();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}// end method close
}// end class Querys
