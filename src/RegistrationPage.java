import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationPage extends JPanel {

	final static int FIELD_WIDTH = 10;

	private JLabel title, nameLabel, accountIDLabel, passwordLabel, idCheck, nameCheck, passwordCheck;
	private JTextField nameField, accountIDField;
	private JPasswordField passwordField;
	private JPanel namePanel, accountIDPanel, passwordPanel, btnPanel;
	private JButton submitbtn, backtoLoginbtn;
	private ExecSQL exec;
	private GridBagLayout gridBagLayout;

	public RegistrationPage() {
		exec = new ExecSQL();
		gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		createComp();
	}

	public void createComp() {
		// title
		Font f1 = new Font("Helvetica", Font.BOLD, 40);
		title = new JLabel("Registration");
		title.setFont(f1);
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.gridwidth = 5;
		c1.gridheight = 1;
		c1.anchor = GridBagConstraints.CENTER;
		Insets titleInsets = new Insets(0,0,80,0);
		c1.insets = titleInsets;
		add(title, c1);

		//general settings
		Font f2 = new Font("Arial", Font.PLAIN, 20);
		Insets i1 = new Insets(10, 0, 10, 0);
		Insets iNote = new Insets(0,30,0,0);
		
		// Name
		nameLabel = new JLabel("Name");
		nameLabel.setFont(f2);
		nameField = new JTextField(FIELD_WIDTH);
		namePanel = new JPanel();
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 0;
		c2.gridy = 1;
		c2.gridwidth = 5;
		c2.gridheight = 1;
		c2.insets = i1;
		c2.anchor = GridBagConstraints.CENTER;
		add(namePanel, c2);
		
		//NameCheck
		Font f3 = new Font("Helvetica", Font.PLAIN, 12);
		nameCheck = new JLabel("");
		nameCheck.setFont(f3);
		nameCheck.setForeground(Color.red);
		GridBagConstraints c7 = new GridBagConstraints();
		c7.fill = GridBagConstraints.HORIZONTAL;
		c7.gridx = 0;
		c7.gridy = 2;
		c7.gridwidth = 5;
		c7.gridheight = 1;
		c7.insets = iNote;
		c7.anchor = GridBagConstraints.CENTER;
		add(nameCheck, c7);

		// AccountID
		accountIDLabel = new JLabel("Account");
		accountIDLabel.setFont(f2);
		accountIDField = new JTextField(FIELD_WIDTH);
		accountIDPanel = new JPanel();
		accountIDPanel.add(accountIDLabel);
		accountIDPanel.add(accountIDField);
		GridBagConstraints c3 = new GridBagConstraints();
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 0;
		c3.gridy = 3;
		c3.gridwidth = 5;
		c3.gridheight = 1;
		c3.insets = i1;
		c3.anchor = GridBagConstraints.CENTER;
		add(accountIDPanel, c3);

		// AccountID check
		idCheck = new JLabel("");
		idCheck.setFont(f3);
		idCheck.setForeground(Color.red);
		GridBagConstraints c4 = new GridBagConstraints();
		c4.fill = GridBagConstraints.HORIZONTAL;
		c4.gridx = 0;
		c4.gridy = 4;
		c4.gridwidth = 5;
		c4.gridheight = 1;
		c4.insets = iNote;
		c4.anchor = GridBagConstraints.CENTER;
		add(idCheck, c4);

		// Password
		passwordLabel = new JLabel("Password");
		passwordLabel.setFont(f2);
		passwordField = new JPasswordField(FIELD_WIDTH);
		passwordPanel = new JPanel();
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		GridBagConstraints c5 = new GridBagConstraints();
		c5.fill = GridBagConstraints.HORIZONTAL;
		c5.gridx = 0;
		c5.gridy = 5;
		c5.gridwidth = 5;
		c5.gridheight = 1;
		c5.insets = i1;
		c5.anchor = GridBagConstraints.CENTER;
		add(passwordPanel, c5);
		
		//passwordCheck
		passwordCheck = new JLabel("");
		passwordCheck.setFont(f3);
		passwordCheck.setForeground(Color.red);
		GridBagConstraints c8 = new GridBagConstraints();
		c8.fill = GridBagConstraints.HORIZONTAL;
		c8.gridx = 0;
		c8.gridy = 6;
		c8.gridwidth = 5;
		c8.gridheight = 1;
		c8.insets = iNote;
		c8.anchor = GridBagConstraints.CENTER;
		add(passwordCheck, c8);
		
		

		// btms
		submitbtn = new JButton("Submit");
		backtoLoginbtn = new JButton("Back to log in");
		submitbtn();
		backtoLoginbtn();
		
		// backtoLoginbtn.setOpaque(false);
		backtoLoginbtn.setBorderPainted(false);
		backtoLoginbtn.setContentAreaFilled(false);
		backtoLoginbtn.setForeground(Color.blue);
		btnPanel = new JPanel();
		btnPanel.add(submitbtn);
		btnPanel.add(backtoLoginbtn);
		GridBagConstraints c6 = new GridBagConstraints();
		Insets btnInsets = new Insets(30,0,0,0);
		c6.fill = GridBagConstraints.HORIZONTAL;
		c6.gridx = 0;
		c6.gridy = 7;
		c6.gridwidth = 5;
		c6.gridheight = 1;
		c6.insets = btnInsets;
		c6.anchor = GridBagConstraints.CENTER;
		add(btnPanel, c6);
	}

	// submitbtm

	public void submitbtn() {
		class clickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					
					passwordCheck.setText("");
					idCheck.setText("");
					nameCheck.setText("");
					
					String password = String.valueOf(passwordField.getPassword());
						
					if(!accountIDField.getText().equals("") && !password.equals("") && !nameField.getText().equals("")) {
						
						passwordCheck();
						nameCheck();
						idCheck();
						
					
						if(passwordCheck() && nameCheck() && idCheck()) {
							
							if (exec.createAccount(accountIDField.getText(), nameField.getText(), passwordField.getPassword())) {

								JOptionPane.showMessageDialog(null, "Your account is successfully created \n You will be redirected to the login page");
								Viewer.cardLayout.show(Viewer.switchPanel, "1");
								accountIDField.setText("");
								passwordField.setText("");
								nameField.setText("");
				
							}else {
								boolean result = exec.checkAccountID(accountIDField.getText());
								if (result == false) {
									idCheck.setText("The Account ID is taken");
								} else {
									JOptionPane.showMessageDialog(null, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
							
						}
						
						
					}else {
						JOptionPane.showMessageDialog(null, "Please enter all the information", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();

				}
			}
		}
		ActionListener listener = new clickListener();
		submitbtn.addActionListener(listener);
	}

	public void backtoLoginbtn() {
		class clickListener implements ActionListener {
			public void actionPerformed(ActionEvent e1) {
				Viewer.cardLayout.show(Viewer.switchPanel, "1");
			}
		}
		ActionListener listener = new clickListener();
		backtoLoginbtn.addActionListener(listener);
	}
	
	public boolean passwordCheck() {
		for (char i : passwordField.getPassword()) {
			String c = String.valueOf(i);
			if(! c.equals(" ")) {
				i++;
			}else {
				passwordCheck.setText("Empty spaces are not allowed in the password field");
				passwordField.setText("");
				return false;
			}
		}
		if (passwordField.getPassword().length < 8 || passwordField.getPassword().length > 16) {
			passwordCheck.setText("Your password needs to be between 8 ~ 16 character");
			passwordField.setText("");
			return false;

		}else {
			return true;
		}
	}
	
	
	public boolean idCheck() {
		String account = accountIDField.getText();
		for (int i = 0; i < account.length(); i++) {
			char c = account.charAt(i);
			String a = String.valueOf(c);
			if (a.equals(" ")) {
				idCheck.setText("Empty spaces are not allowed in the account field");
				accountIDField.setText("");
				return false;
			}	
		}
		if (accountIDField.getText().length() < 8 || accountIDField.getText().length() > 16) {
			idCheck.setText("Your account needs to be between 8 to 16 character");
			accountIDField.setText("");
			return false;
		}else {
			
			return true;
		}
	}
	
	public boolean nameCheck() {
		if(nameField.getText().length() < 3 || nameField.getText().length()>16) {
			nameCheck.setText("Your name needs to be between 3 to 16 character");
			nameField.setText("");
			return false;
		}else {
			return true;
		}
		
	}
	
	

}
