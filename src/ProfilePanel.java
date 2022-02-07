import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
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

public class ProfilePanel extends JPanel {
	private ExecSQL exec;
	private JButton saveBtn;
	private JTextField idField;
	private JTextField nameField;
	private JTextField genderField;
	private JTextField bdfield;
	private JTextArea bioArea;

	public ProfilePanel() throws SQLException {
		exec = new ExecSQL();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width * 4 / 5, screenSize.height * 4 / 5);
		setLayout(null);
		createComp();
	}

	public void createSaveBtn() {
		saveBtn = new JButton("Save");
		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				String name = nameField.getText();
				String gender = genderField.getText();
				String bDay = bdfield.getText();
				String bio = bioArea.getText();
				try {
					if(name.trim().length()==0 || gender.trim().length()==0 || bDay.trim().length()==0 || bio.trim().length()==0) {
						JFrame frame = new JFrame("");
						frame.setSize(400, 300);
						JOptionPane message = new JOptionPane();
						message.showMessageDialog(frame, " Please enter the information correctly!", "Failed", JOptionPane.ERROR_MESSAGE);
					}
					else {
						boolean setName = ExecSQL.user.setName(name);
						boolean setGender = ExecSQL.user.setGender(gender);
						boolean setBday = ExecSQL.user.setBday(bDay);
						boolean setBio = ExecSQL.user.setBio(bio);
						if(setName == true && setGender == true && setBday == true && setBio == true) {
							JFrame frame = new JFrame("");
							frame.setSize(400, 300);
							JOptionPane message = new JOptionPane();
							message.showMessageDialog(frame, "Saved!");
						}
						else {
							JFrame frame = new JFrame("");
							frame.setSize(400, 300);
							JOptionPane message = new JOptionPane();
							message.showMessageDialog(frame, "Operation Failure!", "Failed", JOptionPane.ERROR_MESSAGE);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					JFrame frame = new JFrame("");
					frame.setSize(400, 300);
					JOptionPane message = new JOptionPane();
					message.showMessageDialog(frame, "Operation Failure!", "Failed", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		ActionListener listener = new ButtonListener();
		saveBtn.addActionListener(listener);
	}

	public void createComp() throws SQLException {		
		JLabel profile = new JLabel("Profile");
		profile.setFont(new Font("Verdana", Font.BOLD, 24));
		profile.setHorizontalAlignment(SwingConstants.CENTER);
		profile.setBounds(542, 27, 97, 43);
		add(profile);

		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		ID.setBounds(300, 125, 61, 30);
		add(ID);

		JLabel name = new JLabel("Name");
		name.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		name.setBounds(300, 200, 61, 30);
		add(name);

		JLabel gender = new JLabel("Gender");
		gender.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		gender.setBounds(300, 275, 79, 30);
		add(gender);

		JLabel birthday = new JLabel("Birthday");
		birthday.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		birthday.setBounds(300, 350, 79, 30);
		add(birthday);

		JLabel bio = new JLabel("Bio");
		bio.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		bio.setBounds(300, 425, 40, 30);
		add(bio);

		bdfield = new JTextField(ExecSQL.user.getBday());
		bdfield.setColumns(20);
		bdfield.setBounds(570, 345, 409, 36);
		add(bdfield);

		genderField = new JTextField(ExecSQL.user.getGender());
		genderField.setColumns(20);
		genderField.setBounds(570, 270, 409, 36);
		add(genderField);

		nameField = new JTextField(ExecSQL.user.getName());
		nameField.setColumns(20);
		nameField.setBounds(570, 195, 409, 36);
		add(nameField);

		idField = new JTextField(ExecSQL.user.getID());
		idField.setEditable(false);
		idField.setColumns(20);
		idField.setBounds(570, 120, 409, 36);
		add(idField);

		bioArea = new JTextArea();
		bioArea.setLineWrap(true);
		bioArea.setText(ExecSQL.user.getBio());
		JScrollPane scrollPane = new JScrollPane(bioArea);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(300, 465, 664, 64);
		add(scrollPane);
		createSaveBtn();
		saveBtn.setBounds(519, 610, 117, 29);
		add(saveBtn);
	}

}
