import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SearchProfilePanel extends JPanel {
	private JTextField idField;
	private JTextField nameField;
	private JTextField genderField;
	private JTextField bdfield;
	private JTextArea bioArea;

	public SearchProfilePanel(Account accountToSearch) throws SQLException {
		setBounds(100, 100, 450, 300);
		setLayout(null);
		createComp();
		idField.setText(accountToSearch.getID());
		nameField.setText(accountToSearch.getName());
		genderField.setText(accountToSearch.getGender());
		bdfield.setText(accountToSearch.getBday());
		bioArea.setText(accountToSearch.getBio());
		idField.setEditable(false);
		nameField.setEditable(false);
		genderField.setEditable(false);
		bdfield.setEditable(false);
		bioArea.setEditable(false);
	}

	public void createComp() throws SQLException {
		JLabel profile = new JLabel("Profile");
		profile.setFont(new Font("Verdana", Font.BOLD, 24));
		profile.setHorizontalAlignment(SwingConstants.CENTER);
		profile.setBounds(144, 6, 153, 30);
		add(profile);

		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		ID.setBounds(28, 40, 61, 16);
		add(ID);

		JLabel name = new JLabel("Name");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		name.setBounds(28, 70, 61, 16);
		add(name);

		JLabel gender = new JLabel("Gender");
		gender.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		gender.setBounds(28, 100, 61, 16);
		add(gender);

		JLabel birthday = new JLabel("Birthday");
		birthday.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		birthday.setBounds(28, 130, 79, 16);
		add(birthday);

		JLabel bio = new JLabel("Bio");
		bio.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		bio.setBounds(28, 160, 40, 16);
		add(bio);

		bdfield = new JTextField(ExecSQL.user.getBday());
		bdfield.setColumns(10);
		bdfield.setBounds(119, 126, 261, 26);
		add(bdfield);

		genderField = new JTextField(ExecSQL.user.getGender());
		genderField.setColumns(10);
		genderField.setBounds(119, 96, 261, 26);
		add(genderField);

		nameField = new JTextField(ExecSQL.user.getName());
		nameField.setColumns(10);
		nameField.setBounds(119, 66, 261, 26);
		add(nameField);

		idField = new JTextField(ExecSQL.user.getID());
		idField.setEditable(false);
		idField.setColumns(10);
		idField.setBounds(119, 36, 261, 26);
		add(idField);

		bioArea = new JTextArea();
		bioArea.setLineWrap(true);
		bioArea.setText(ExecSQL.user.getBio());
		JScrollPane scrollPane = new JScrollPane(bioArea);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(28, 188, 405, 40);
		add(scrollPane);
	}

}
