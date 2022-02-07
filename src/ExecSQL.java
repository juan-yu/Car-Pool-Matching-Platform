import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class ExecSQL {
	private static String url;
	private static String username;
	private static String password;
	private static Connection conn;
	protected static Account user;

	public ExecSQL() {
		String server = "jdbc:mysql://140.119.19.73:9306/";
		String database = "TG02";
		String config = "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false";
		url = server + database + config;
		username = "TG02";
		password = "6h2ekf";
		initializing();
	}

	private void initializing() {
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean findTable(String tableName) {
		boolean findTable = false;
		try {
			Statement stat = conn.createStatement();
			String query = "SHOW TABLES LIKE" + tableName;
			ResultSet resultSet = stat.executeQuery(query);
			if (resultSet != null) {
				findTable = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findTable;

	}

	// Amber's code starts
	public boolean addDriverGroup(int lowerPrice, int upperPrice, int numberOfPeople, int totalVacancy,
			String startingPoint, String destination, String carModel, String datAndTime) throws ParseException {
		boolean addGroup = false;
		try {
			Statement stat = conn.createStatement();
			String currentUserID = ExecSQL.user.getID();
			int caseID = uploadADriverCase(lowerPrice, upperPrice, numberOfPeople, totalVacancy, currentUserID,
					currentUserID, startingPoint, destination, carModel, datAndTime);
			String add = "INSERT INTO Groups(AccountID, CaseID) VALUES (" + "'" + currentUserID + "'" + "," + caseID
					+ ")";
			int result = stat.executeUpdate(add);
			if (result == 1) {
				addGroup = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addGroup;
	}

	public boolean addPassengerGroup(int lowerPrice, int upperPrice, int numberOfPeopleNow, String startingPoint,
			String destination, String datAndTime) throws ParseException {
		boolean addGroup = false;
		try {
			Statement stat = conn.createStatement();
			String currentUserID = ExecSQL.user.getID();
			int caseID = uploadAPassengerCase(lowerPrice, upperPrice, numberOfPeopleNow, currentUserID, startingPoint,
					destination, datAndTime);
			String add = "INSERT INTO Groups(AccountID, CaseID) VALUES (" + "'" + currentUserID + "'" + "," + caseID
					+ ")";
			int result = stat.executeUpdate(add);
			if (result == 1) {
				addGroup = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addGroup;
	}

	public boolean addGroup(String requesterID, int caseID) {
		boolean addGroup = false;
		try {
			Statement stat = conn.createStatement();
			String add = "INSERT INTO Groups(AccountID, CaseID) VALUES (" + "'" + requesterID + "'" + "," + caseID
					+ ")";
			int result = stat.executeUpdate(add);
			if (result == 1) {
				addGroup = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addGroup;
	}
	// Amber's code ends
// SEBAS CODE START

//	public ResultSet getAllRequestsforUser() {
//		ResultSet result = null;
//		ResultSet result = null;
//
//		Statement stat = conn.createStatement();
//
//		String r = "'" + requestID + "'";
//
//		String sql = "Select * FROM 'requests' WHERE 'RequestID' = " + r;
//		try {
//			result = stat.executeQuery(sql);
//
//		} catch (SQLException e) {
//
//		}
//
//		return result;
//	}
//	
	public ResultSet requestInfo(String requestID) throws SQLException {

		ResultSet result = null;

		Statement stat = conn.createStatement();

		String r = "'" + requestID + "'";

		String sql = "Select * FROM 'requests' WHERE 'RequestID' = " + r;
		try {
			result = stat.executeQuery(sql);

		} catch (SQLException e) {

		}

		return result;
	}

	public boolean addRequest(Requests request) {
		boolean addRequest = false;
		try {
			String sql = "INSERT INTO Requests (Requests.Requestor_ID,Requests.Case_ID,Requests.Status) VALUES(?,?,?)";
			PreparedStatement stat = conn.prepareStatement(sql);

			stat.setString(1, request.getRequesterID());
			stat.setInt(2, request.getCaseID());
			stat.setString(3, "Undecided");
			/*
			 * W:Should be the same sequence as the table, like this: stat.setInt(1,
			 * request.getCaseID()); stat.setString(2, request.getRequesterID());
			 * stat.setString(3, "Undecided");
			 */

			int result = stat.executeUpdate();
			if (result == 1) {
				addRequest = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addRequest;
	}

	public void deleteRequest(String requestID) {
		boolean addRequest = false;
		try {
			String sql = "DELETE FROM Requests WHERE REQUEST ID =" + requestID;
			Statement stat = conn.createStatement();
			stat.execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean AcceptRequest(String requestID) {

		// For accept Button
		boolean result = false;
		try {
			System.out.println(requestID);
			String sql = String.format("UPDATE Requests SET Status = 'Accepted' Where RequestID = '%s' ", requestID);
			;
			Statement stat = conn.createStatement();
			stat.execute(sql);
			result = true;

//			String sql2 = "INSERT INTO GROUPS (AccountID, Case_ID) VALUES(?,?)";
//			PreparedStatement stat2 = conn.prepareStatement(sql2);

		} catch (SQLException g) {
		}
		return result;
	}

	public boolean RejectRequest(String requestID) {

		// For accept Button
		boolean result = false;
		try {
			String sql = String.format("UPDATE Requests SET status = 'Declined' Where RequestID = '%s' ", requestID);
			Statement stat = conn.createStatement();
			stat.execute(sql);
			result = true;

		} catch (SQLException g) {
		}
		return result;
	}

	public ResultSet getAllRequestID(Account user) throws SQLException {

		user = this.user;
		ResultSet result = null;

		Statement stat = conn.createStatement();

		String sql = "SELECT RequestID,Case_ID,Requests.Status,Requestor_ID FROM `Requests` JOIN Cases ON Requests.Case_ID=Cases.caseID WHERE Cases.creatorID = '"
				+ user.getID() + "'";
		try {
			result = stat.executeQuery(sql);

		} catch (SQLException e) {

		}

		return result;

	}

	public ResultSet getRequestInfo(String requestID) throws SQLException {
		ResultSet result;

		Statement stat = conn.createStatement();

		String sql = String.format("SELECT * FROM Requests WHERE RequestID = '%s'", requestID);
		try {
			result = stat.executeQuery(sql);

		} catch (SQLException e) {
			result = null;
		}

		return result;

	}

	public ResultSet getRequestMadeInfo(String requestID) throws SQLException {
		ResultSet result = null;
		String sql = "SELECT RequestID,Case_ID,Requests.Status,CreatorID FROM Requests JOIN Cases on Requests.Case_ID=Cases.caseID WHERE RequestID = ?";
		PreparedStatement stat = conn.prepareStatement(sql);
		stat.setString(1, requestID);
		result = stat.executeQuery();
		return result;

	}

	public ResultSet getAllMadeRequest(Account user) throws SQLException {

		user = this.user;
		ResultSet result = null;

		Statement stat = conn.createStatement();

		String sql = "SELECT RequestID,Case_ID,Requests.Status,Requestor_ID FROM `Requests` JOIN Cases ON Requests.Case_ID=Cases.caseID WHERE Requests.Requestor_ID = '"
				+ user.getID() + "'";
		try {
			result = stat.executeQuery(sql);

		} catch (SQLException e) {

		}

		return result;

	}

	public void updateCurrentPeople() throws SQLException {
		Statement stat = conn.createStatement();
		String sql = "UPDATE Cases A "
				+ "INNER JOIN (SELECT CaseID,COUNT(*) Groupcount  FROM Groups GROUP BY Groups.CaseID) AS B "
				+ "ON A.caseID = B.CaseID " + "SET A.numberOfPeopleNow =  B.Groupcount";
		stat.execute(sql);
	}

// Sebastian's code ends

//Eva's code starts

	public boolean login(String id, char[] password) throws SQLException {

		String sql = "SELECT * FROM Account WHERE ID = ? AND BINARY(password) = ?";

		PreparedStatement prep = conn.prepareStatement(sql);

		prep.setString(1, id);
		prep.setString(2, String.valueOf(password));

		ResultSet result = prep.executeQuery();
		if (result.next()) {
			user = new Account(id, password);
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAccountID(String id) throws SQLException {
		// check whether an account exist when registering
		// return true if the user name is available; false if not

		String sql = String.format("SELECT * FROM Account WHERE ID = '%s'", id);

		Statement stmt = conn.createStatement();

		ResultSet result = stmt.executeQuery(sql);

		if (result.next()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean createAccount(String id, String name, char[] password) throws SQLException {

		if (checkAccountID(id) == true) {
			try {

				PreparedStatement prep = conn.prepareStatement("INSERT INTO Account VALUES(?,?,?,?,?,?)");

				prep.setString(1, id);
				prep.setString(2, name);
				prep.setString(3, String.valueOf(password));
				prep.setString(4, "");
				prep.setString(5, "");
				prep.setString(6, "");

				prep.execute();
				return true;

			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	// Eva's code ends

	// Wayne's codes start

	int uploadADriverCase(int minPrice, int maxPrice, int numberOfPeopleNow, int totalVacancy, String creatorID,
			String driverName, String startPoint, String destination, String carModel, String dateAndTime)
			throws SQLException, ParseException {
		PreparedStatement pstat = conn.prepareStatement("SELECT * FROM Account WHERE ID=?");
		pstat.setString(1, driverName);
		ResultSet result = pstat.executeQuery();
		result.next();
		String driverID = result.getString(1);
		pstat.close();
		pstat = conn.prepareStatement(
				"INSERT INTO Cases(minPrice,maxPrice,numberOfPeopleNow,totalVacancy,creatorID,driverID,startPoint,destination,carModel,dateTime) VALUES(?,?,?,?,?,?,?,?,?,?)");
		pstat.setInt(1, minPrice);
		pstat.setInt(2, maxPrice);
		pstat.setInt(3, numberOfPeopleNow);
		pstat.setInt(4, totalVacancy);
		pstat.setString(5, creatorID);
		pstat.setString(6, driverID);
		pstat.setString(7, startPoint);
		pstat.setString(8, destination);
		pstat.setString(9, carModel);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date dateTime = sdf.parse(dateAndTime);
		pstat.setTimestamp(10, new Timestamp(dateTime.getTime()));
		pstat.execute();

		Statement stat = conn.createStatement();
		result = stat.executeQuery("SELECT * FROM Cases ORDER BY caseID DESC LIMIT 1");

		result.next();
		int caseID = result.getInt(1);

		return caseID;
	}

	int uploadAPassengerCase(int minPrice, int maxPrice, int numberOfPeopleNow, String creatorID, String startPoint,
			String destination, String dateAndTime) throws SQLException, ParseException {
		System.out.println("numberOfPeopleNow"+numberOfPeopleNow);
		PreparedStatement pstat = conn.prepareStatement(
				"INSERT INTO Cases(minPrice,maxPrice,numberOfPeopleNow,totalVacancy,creatorID,driverID,startPoint,destination,carModel,dateTime) VALUES(?,?,?,?,?,?,?,?,?,?)");
		pstat.setInt(1, minPrice);
		pstat.setInt(2, maxPrice);
		pstat.setInt(3, 5);
		pstat.setInt(4, 0);
		pstat.setString(5, creatorID);
		pstat.setString(6, "No driver yet");
		pstat.setString(7, startPoint);
		pstat.setString(8, destination);
		pstat.setString(9, "No driver yet");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date dateTime = sdf.parse(dateAndTime);
		pstat.setTimestamp(10, new Timestamp(dateTime.getTime()));
		pstat.execute();
		Statement stat = conn.createStatement();
		ResultSet result = stat.executeQuery("SELECT * FROM Cases ORDER BY caseID DESC LIMIT 1");

		result.next();
		int caseID = result.getInt(1);

		return caseID;
	}

	Case downloadAndConstructACase(int caseID) throws SQLException, ParseException {
		Statement stat = conn.createStatement();
		ResultSet aRow = stat.executeQuery("SELECT * FROM Cases WHERE id =" + caseID);
		if (aRow.next()) {
			// Some page displays "Download Succeed"
		} else {
			// Some page displays "Download Fail"
		}
		Case c = new Case(aRow.getInt(1), aRow.getInt(2), aRow.getInt(3), aRow.getInt(4), aRow.getInt(5),
				aRow.getString(6), aRow.getString(7), aRow.getString(8), aRow.getString(9), aRow.getString(10),
				aRow.getTimestamp(11));

		return c;
	}

	void createCasesPanel(JPanel casesPanel, ArrayList<JPanel> casePanels) throws SQLException {
		Statement stat = conn.createStatement();
		ResultSet rowcount = stat.executeQuery("SELECT COUNT(*) As rowcount FROM Cases");
		rowcount.next();
		int rows = rowcount.getInt("rowcount");

		casesPanel.setLayout(new BorderLayout());

		ResultSet resultSet = stat.executeQuery("SELECT * FROM Cases");

		Object[][] cases = new Object[rows][12];
		for (int i = 0; i < cases.length; i++) {
			resultSet.next();
			for (int j = 0; j < cases[i].length; j++) {
				if (j == 10) {
					String time = resultSet.getTimestamp(j + 1).toString().substring(0, 16);
					cases[i][j] = time;
				} else if (j < 11) {
					cases[i][j] = resultSet.getString(j + 1);
				} else {
					cases[i][j] = "join case" + resultSet.getString(1);
				}
			}
		}

		String columnHeaders[] = { "caseID", "min price", "max price", "Number of people now", "Total vacancy",
				"creator ID", "driver ID", "start point", "destination", "car model", "date&time",
				"ask to join(double click)" };
		JTable casesTable = new JTable(cases, columnHeaders);

		class ButtonRenderer extends JButton implements TableCellRenderer {

			public ButtonRenderer() {
				setOpaque(true);
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused,
					int row, int col) {
				// TODO Auto-generated method stub
				setText((obj == null) ? "" : obj.toString());
				return this;
			}

		}

		class ButtonEditor extends DefaultCellEditor {

			protected JButton btn;
			private String lbl;
			private Boolean clicked;

			public ButtonEditor(JTextField txt) {
				super(txt);
				// TODO Auto-generated constructor stub
				btn = new JButton();
				btn.setOpaque(true);
				btn.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						fireEditingStopped();
					}
				});
			}

			public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {

				// SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
				lbl = (obj == null) ? "" : obj.toString();
				btn.setText(lbl);
				clicked = true;
				return btn;
			}

			public Object getCellEditorValue() {

				if (clicked) {
					try {
						String selectedCreator = "";
						String[] words = lbl.split("e");
						PreparedStatement pstat = conn
								.prepareStatement("SELECT * FROM Requests WHERE Case_ID = ? AND Requestor_ID = ?");
						pstat.setString(1, words[1]);
						pstat.setString(2, user.getID());
						ResultSet result = pstat.executeQuery();
						for (int i = 0; i < cases.length; i++) {
							if (cases[i][0].equals(words[1])) {
								selectedCreator = (String) cases[i][5];
								System.out.println("selectedCreator"+selectedCreator);
							}
						}
						if (result.next()) {
							JOptionPane.showMessageDialog(null, "You've already asked to join this case before.",
									"Error", JOptionPane.ERROR_MESSAGE);
						} else if (selectedCreator.equals(user.getID())) {
							JOptionPane.showMessageDialog(null, "You're already in this case.", "Error",
									JOptionPane.ERROR_MESSAGE);
						} else {
							if (addRequest(new Requests(user.getID(), Integer.parseInt(words[1])))) {
								JOptionPane.showMessageDialog(null,
										"Request sent. Please wait for the case creater to accept you");
								new java.util.Timer().schedule(new java.util.TimerTask() {
									@Override
									public void run() {

									}
								}, 5000);
							}
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// SET IT TO FALSE NOW THAT ITS CLICKED
				clicked = false;
				return new String(lbl);
			}

			public boolean stopCellEditing() {

				// SET CLICKED TO FALSE FIRST
				clicked = false;
				return super.stopCellEditing();
			}

			protected void fireEditingStopped() {
				// TODO Auto-generated method stub
				super.fireEditingStopped();
			}
		}

		casesTable.getColumnModel().getColumn(11).setCellRenderer(new ButtonRenderer());
		casesTable.getColumnModel().getColumn(11).setCellEditor(new ButtonEditor(new JTextField()));
		casesTable.setDefaultEditor(Object.class, null);
		JScrollPane pane = new JScrollPane(casesTable);
		casesPanel.add(pane, BorderLayout.CENTER);
	}

	Account searchAccountByID(String AccountID) throws SQLException {
		PreparedStatement pstat = conn.prepareStatement("SELECT * FROM Account WHERE ID = ?");
		pstat.setString(1, AccountID);
		System.out.println(pstat.execute());
		ResultSet result = pstat.executeQuery();
		System.out.println(result.next());
		Account account = new Account(result.getString(1), result.getString(3).toCharArray());
		pstat.close();
		return account;
	}
	// Wayne's codes end
}
