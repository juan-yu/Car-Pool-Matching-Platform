import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class AccountGUI extends JPanel {

	final static int FIELD_WIDTH = 10;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JTextField idField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton signupButton;
	private ImageIcon img;
	private JPanel idPanel;
	private JPanel passwordPanel;
	private JLabel imgLabel;
	private JPanel buttonPanel;
	private ExecSQL conn;

	static String currentUserID;

	public AccountGUI() throws SQLException {
		conn = new ExecSQL();
		createComp();
	}

	private void createComp() {

		img = new ImageIcon("car.png");
		imgLabel = new JLabel(img);
		add(imgLabel);

		// id panel
		idPanel = new JPanel();
		idLabel = new JLabel("Account ID: ");
		idField = new JTextField(FIELD_WIDTH);

		idPanel.add(idLabel);
		idPanel.add(idField);
		add(idPanel);

		// password panel
		passwordPanel = new JPanel();
		passwordLabel = new JLabel("Password: ");
		passwordField = new JPasswordField(FIELD_WIDTH);

		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		add(passwordPanel);

		createButtonPanel();

	}

	public void createloginButton() {
		loginButton = new JButton("Log in");
		class clickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				try {

					boolean result = conn.login(idField.getText(), passwordField.getPassword());
					if (result) {
						// go to the main page
						Viewer.refresh();
						Viewer.cardLayout.show(Viewer.switchPanel, "3");
						Viewer.menuBar.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Please enter the correct account or password", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Connection Error");
				}
			}
		}
		ActionListener listener = new clickListener();
		loginButton.addActionListener(listener);
	}

	public void createsignupButton() {
		signupButton = new JButton("Sign Up");
		class clickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// Go to registration page
				Viewer.cardLayout.show(Viewer.switchPanel, "2");
			}
		}
		ActionListener listener = new clickListener();
		signupButton.addActionListener(listener);

	}

	public void createButtonPanel() {
		createloginButton();
		createsignupButton();

		buttonPanel = new JPanel();

		buttonPanel.add(loginButton);
		buttonPanel.add(signupButton);
		add(buttonPanel);
	}
}
