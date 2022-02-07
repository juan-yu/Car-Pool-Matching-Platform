import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


//to be uploaded
public class CasesPanel extends JPanel {
	private ExecSQL execSQL;
	private JButton createACaseButton;
	private ArrayList<JPanel> casePanels;

	CasesPanel() throws SQLException {
		execSQL = new ExecSQL();
		casePanels = new ArrayList<JPanel>();
		createACaseButton = new JButton("Create a case");

	//Sebas code to Update # of Passengers
		execSQL.updateCurrentPeople();

		execSQL.createCasesPanel(this, casePanels);

		class ClickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// From here: show() the page to choose between driver or passenger
				JFrame driverOrPassenger = new JFrame("Please choose one:");
				JPanel choosePanel = new JPanel();
				driverOrPassenger.setSize(500, 400);
				driverOrPassenger.setVisible(true);
				driverOrPassenger.add(new JLabel("You are a:"), BorderLayout.NORTH);
				JButton driverButton = new JButton("driver");
				driverButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						driverOrPassenger.dispose();
						Viewer.cardLayout.show(Viewer.switchPanel, "4");
					}
				});

				JButton passengerButton = new JButton("passenger");
				passengerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						driverOrPassenger.dispose();
						Viewer.cardLayout.show(Viewer.switchPanel, "5");
					}
				});
				choosePanel.add(driverButton);
				choosePanel.add(passengerButton);
				driverOrPassenger.add(choosePanel, BorderLayout.CENTER);
			}
		}
		createACaseButton.addActionListener(new ClickListener());
		add(createACaseButton,BorderLayout.SOUTH);
	}
}
