import java.sql.*;

import javax.swing.JOptionPane;

public class Account {

	private String id;
	private String url, username, connPassword;
	private Connection conn;

	public Account(String id, char[] password) throws SQLException {
		this.id = id;

		connection();

	}

	public void connection() throws SQLException {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "TG02";
		String chinese = "?useUnicode=yes&characterEncoding=UTF-8";
		url = server + database + chinese;
		username = "TG02";
		connPassword = "6h2ekf";

		conn = DriverManager.getConnection(url, username, connPassword);
	}

	public boolean setName(String name) throws SQLException {

		String sql = "UPDATE Account Set name = ? WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, name);
		prep.setString(2, id);

		int num = prep.executeUpdate();

		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setBio(String bio) throws SQLException {

		String sql = "UPDATE Account Set bio = ? WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, bio);
		prep.setString(2, id);

		int num = prep.executeUpdate();

		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setGender(String gender) throws SQLException {
		String sql = "UPDATE Account Set gender = ? WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, gender);
		prep.setString(2, id);

		int num = prep.executeUpdate();

		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setBday(String bday) throws SQLException {
		String sql = "UPDATE Account Set bday = ? WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, bday);
		prep.setString(2, id);

		int num = prep.executeUpdate();

		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setPassword(String password) throws SQLException {
		String sql = "UPDATE Account Set password = ? WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, password);
		prep.setString(2, id);

		int num = prep.executeUpdate();

		if (num != 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getName() throws SQLException {

		String sql = "SELECT name FROM Account WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, id);

		ResultSet num = prep.executeQuery();
		
		num.next();
		
		String result = num.getString(1);
		return result;
	}

	public String getBio() throws SQLException {

		String sql = "SELECT bio FROM `Account` WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, id);
		
		ResultSet num = prep.executeQuery();
		
		num.next();
		//W: Should add  num.next();  here
		String result = num.getString(1);//W: Should getString(1)
		
		return result;
	}

	public String getGender() throws SQLException {

		String sql = "SELECT gender FROM `Account` WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, id);

		ResultSet num = prep.executeQuery();

		num.next();
		
		String result = num.getString(1);

		return result;

	}

	public String getBday() throws SQLException {

		String sql = "SELECT bday FROM `Account` WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, id);

		ResultSet num = prep.executeQuery();
		
		num.next();
		
		String result = num.getString(1);
		
		return result;
	}

	public String getID() throws SQLException {

		String sql = "SELECT ID FROM `Account` WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, id);

		ResultSet num = prep.executeQuery();

		num.next();
		
		String result = num.getString(1);
		
		return result;
	}

	public String getPassword() throws SQLException {

		String sql = "SELECT password FROM `Account` WHERE ID = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, id);

		ResultSet num = prep.executeQuery();

		num.next();
		
		String result = num.getString(1);

		return result;
	}
}
