import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Viewer {
	private static ExecSQL sql;
	protected static JFrame frame;
	protected static JPanel switchPanel;
	protected static CardLayout cardLayout;
	private static AccountGUI accountGUI;
	private static RegistrationPage registrationPage;
	private static CasesPanel casesPanel;
	private static CreateCaseDriverPanel createCaseDriverPanel;
	private static CreateCasePassengerPanel createCasePassengerPanel;
	private static ManageRequestPanel manageRequestPanel;
	private static ProfilePanel profilePanel;
	protected static JMenuBar menuBar;
	private static JButton checkRequests, profile, goToCasesPage, logOut, searchID;
	private static JTextField profileSearcher;

	public static void refresh() throws SQLException {
		casesPanel = new CasesPanel();
		manageRequestPanel = new ManageRequestPanel();
		profilePanel = new ProfilePanel();
		switchPanel.add("3", casesPanel);
		switchPanel.add("6", manageRequestPanel);
		switchPanel.add("7", profilePanel);
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		sql = new ExecSQL();
		frame = new JFrame("Car pool");
		accountGUI = new AccountGUI();
		registrationPage = new RegistrationPage();
		createCaseDriverPanel = new CreateCaseDriverPanel();
		createCasePassengerPanel = new CreateCasePassengerPanel();
		menuBar = new JMenuBar();
		searchID = new JButton("Search the user ID");
		searchID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(profileSearcher.getText());
					SearchProfilePanel profileSearched = new SearchProfilePanel(
							sql.searchAccountByID(profileSearcher.getText()));
					JFrame SearchProfileFrame = new JFrame("Profile Searched");
					SearchProfileFrame.setSize(500, 500);
					SearchProfileFrame.getContentPane().add(profileSearched);
					SearchProfileFrame.setVisible(true);
					SearchProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Can't find the account.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		checkRequests = new JButton("check requests");
		checkRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					refresh();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				cardLayout.show(switchPanel, "6");
			}
		});
		profile = new JButton("profile");
		class ProfileListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				try {
					refresh();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cardLayout.show(switchPanel, "7");
			}
		}

		ProfileListener profileListener = new ProfileListener();
		profile.addActionListener(profileListener);
		goToCasesPage = new JButton("Cases page");
		goToCasesPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					refresh();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cardLayout.show(switchPanel, "3");
			}
		});
		logOut = new JButton("log out");
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuBar.setVisible(false);
				ExecSQL.user = null;// need to discuss
				cardLayout.show(switchPanel, "1");
			}
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width * 4 / 5, screenSize.height * 4 / 5);
		cardLayout = new CardLayout();
		switchPanel = new JPanel(cardLayout);
		switchPanel.add("1", accountGUI);
		switchPanel.add("2", registrationPage);

		switchPanel.add("4", createCaseDriverPanel);
		switchPanel.add("5", createCasePassengerPanel);

		menuBar.add(checkRequests);
		menuBar.add(profile);
		menuBar.add(goToCasesPage);
		menuBar.add(logOut);
		menuBar.add(searchID);
		frame.getContentPane().add(switchPanel);
		frame.setJMenuBar(menuBar);
		profileSearcher = new JTextField(16);
		profileSearcher.setForeground(Color.LIGHT_GRAY);
		profileSearcher.setText("\"ENTER USER ID\"");
		profileSearcher.setMaximumSize(new Dimension(150, 30));
		menuBar.add(profileSearcher);
		menuBar.setVisible(false);
		cardLayout.show(switchPanel, "1");

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
