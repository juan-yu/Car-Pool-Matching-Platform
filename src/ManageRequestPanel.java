import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class ManageRequestPanel extends JPanel {

	ExecSQL exec = new ExecSQL();
	private JTable RequestReceivedtable;
	private JTable RequestMadetable;
	JComboBox comboBox;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public ManageRequestPanel() throws SQLException {
		setLayout(null);

		
		
		System.out.println(exec.user.getName());

		final JTextArea resultArea = new JTextArea();
		resultArea.setBackground(SystemColor.menu);
		resultArea.setForeground(Color.BLUE);
		resultArea.setFont(new Font("Monospaced", Font.ITALIC, 20));
		resultArea.setBounds(10, 436, 426, 51);
		add(resultArea);

//		addRequestIDtoComboBox();

		JLabel lblNewLabel_1 = new JLabel("Requests Received");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 47, 197, 23);
		add(lblNewLabel_1);
		
		RequestReceivedtable = new JTable();
		RequestReceivedtable.setBounds(10, 80, 426, 102);
		add(RequestReceivedtable);
		
		RequestMadetable = new JTable();
		RequestMadetable.setBounds(10, 294, 426, 102);
		add(RequestMadetable);
		
		JLabel lblNewLabel = new JLabel("Requests Made");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 255, 185, 29);
		add(lblNewLabel);
		
		JButton AcceptButton = new JButton("Accept");
		AcceptButton.setBounds(514, 147, 85, 21);
		add(AcceptButton);
		AcceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
					try {
					ResultSet request =	exec.getRequestInfo(comboBox.getSelectedItem().toString());
					
					/////TRY DELETE THIS AFTER
					request.next();
			
					System.out.println(request.getObject(4).toString()+request.getObject(4));
					
					System.out.println(request.getObject(4).toString().equals("Accepted"));
					if( request.getObject(4).toString().equals("Accepted")==false) {
						System.out.println(request.getObject(4));
				String requestor = request.getObject(3).toString();
				int id = Integer.parseInt(request.getObject(2).toString());
				exec.AcceptRequest(comboBox.getSelectedItem().toString());
				 
				
					
				exec.addGroup(requestor, id);
				resultArea.setText("You accepted the request!");
					} else resultArea.setText("Already accepted before");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						resultArea.setText("ERROR");
					}
					
					
					
//				} else {
//					resultArea.setText("Operation Failed");
//				}
				// TODO Auto-generated method stub
				
			}

			
			
		});
		
		JButton RejectButton = new JButton("Reject");
		RejectButton.setBounds(691, 147, 85, 21);
		add(RejectButton);
		RejectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (exec.RejectRequest(comboBox.getSelectedItem().toString())) {
					resultArea.setText("You denied the Request!");
				} else {
					resultArea.setText("Operation Failed");
				}  
				
				
				
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.CYAN);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(514, 31, 289, 162);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Manage Requests");
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_3.setBounds(42, 10, 203, 27);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("RequestID");
		lblNewLabel_2.setBounds(10, 72, 85, 13);
		panel.add(lblNewLabel_2);
		
		 comboBox = new JComboBox();
		 comboBox.setBounds(121, 67, 56, 23);
		 panel.add(comboBox);
		
	
		//add the results
		
		this.ShowResultInfo();
		this.addRequestsMade();
		this.addRequestIDtoComboBox();

	}

	public void addRequestIDtoComboBox() throws SQLException {

		ResultSet requests = exec.getAllRequestID(exec.user);

		while (requests.next()) {
			
			this.comboBox.addItem(requests.getObject(1));
		}

	}
	
//	public void addRequestMadeIDtoComboBox() throws SQLException {
//
//		ResultSet requests = exec.getAllMadeRequestID(exec.user);
//
//		while (requests.next()) {
//			this.RequestMadeBox.addItem(requests.getObject(1));
//		}
//Seba
//	}

	public void ShowResultInfo() throws SQLException {
		
		
		
		//ResultSet requestInfo = exec.getRequestInfo(requestComboBox.getSelectedItem().toString());
		ResultSet requestInfo = exec.getAllRequestID(exec.user);
		
		// Dont know if the title of colums should be here but it works for showing data
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] { { "Request ID", "CaseID", "Status", "Requestor ID" },

				}, new String[] { "Request ID", "Case ID", "Status", "Requestor ID" });
		this.RequestReceivedtable.setModel(model);

		while (requestInfo.next()) {

			
			
			model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
					requestInfo.getString(3), requestInfo.getString(4)

//				

			});

		}
	}
	
	public void addRequestsMade() {
		
		
		ResultSet requestInfo = null;
		
		
		

	//	resultArea.setText("REQUEST MADE INFO");
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] { { "Request ID", "CaseID", "Status", "Requested To" },

				}, new String[] { "Request ID", "Case ID", "Status", "Requested To" });
		this.RequestMadetable.setModel(model);

		try {
			
			requestInfo = exec.getAllMadeRequest(exec.user);
			while (requestInfo.next()) {

				model.addRow(new Object[] { requestInfo.getString(1), requestInfo.getString(2),
						requestInfo.getString(3), requestInfo.getString(4),

//				

				});

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	}
