import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CreateCaseDriverPanel extends JPanel {
	private static int FIELD_WIDTH = 25;
	private ExecSQL exec;
	private JLabel titleLabel;
	private JPanel datePanel;
	private JPanel locationPanel;
	private JPanel destinationPanel;
	private JPanel vacancyPanel;
	private JPanel carModelPanel;
	private JPanel pricePanel;
	private JLabel upperValue;
	private JLabel lowerValue;
	private JPanel butPanel;
	private JComboBox year;
	private JComboBox month;
	private JComboBox day;
	private JComboBox hour;
	private JComboBox minute;
	private JComboBox<String> place;
	private JTextField startingPoint;
	private JTextField vacancy;
	private JTextField carModel;
	private RangeSlider slider;

	public CreateCaseDriverPanel() throws SQLException {
		exec = new ExecSQL();
		createComp();
		display();
	}

	public void createComp() {
		this.setLayout(new GridLayout(8, 1));
		titleLabel = new JLabel("CREATE CASE: DRIVER");
		Font font = new Font("MS Sans Serif", Font.BOLD, 25);
		titleLabel.setFont(font);
		createDatePanel();
		createLocationPanel();
		createDestinationPanel();
		createVacancyPanel();
		createCarModelPanel();
		createPricePanel();
		createButPanel();
		add(titleLabel);
		add(datePanel);
		add(locationPanel);
		add(destinationPanel);
		add(vacancyPanel);
		add(carModelPanel);
		add(pricePanel);
		add(butPanel);
	}

	public int getDay(int year, int month) {
		if (month == 2) {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0)) {
				return 29;
			} else {
				return 28;
			}
		} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else {
			return 30;
		}
	}

	public void createDatePanel() {
		datePanel = new JPanel();
		JLabel label = new JLabel("Date & Time");
		JLabel time = new JLabel(":");
		year = new JComboBox();
		year.addItem("Year");
		for (int i = 2021; i < 2100; i++) {
			year.addItem(i);
		}
		month = new JComboBox();
		month.addItem("Month");
		for (int i = 1; i < 13; i++) {
			month.addItem(i);
		}
		day = new JComboBox();
		day.addItem("Day");
		for (int i = 1; i < 32; i++) {
			day.addItem(i);
		}
		hour = new JComboBox();
		for (int i = 0; i < 24; i++) {
			hour.addItem(i);
		}
		minute = new JComboBox();
		for (int i = 1; i < 60; i++) {
			minute.addItem(i);
		}
		class MonthListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				int days = getDay(Integer.parseInt(year.getSelectedItem().toString()),
						Integer.parseInt(month.getSelectedItem().toString()));
				day.removeAllItems();
				for (int i = 1; i <= days; i++) {
					day.addItem(i);
				}
			}
		}
		ActionListener monthListener = new MonthListener();
		month.addActionListener(monthListener);
		datePanel.add(label);
		datePanel.add(year);
		datePanel.add(month);
		datePanel.add(day);
		datePanel.add(hour);
		datePanel.add(time);
		datePanel.add(minute);
	}

	public void createLocationPanel() {
		locationPanel = new JPanel();
		JLabel label = new JLabel("Pickup Location");
		startingPoint = new JTextField(FIELD_WIDTH);
		locationPanel.add(label);
		locationPanel.add(startingPoint);
	}

	public void createDestinationPanel() {
		destinationPanel = new JPanel();
		JLabel label = new JLabel("Destination");
		place = new JComboBox<String>();
		place.addItem("Keelung");
		place.addItem("Taipei");
		place.addItem("Taoyuan");
		place.addItem("Hsinchu");
		place.addItem("Miaoli");
		place.addItem("Taichung");
		place.addItem("Changhua");
		place.addItem("Yunlin");
		place.addItem("Chiayi");
		place.addItem("Nantou");
		place.addItem("Tainan");
		place.addItem("Kaohsiung");
		place.addItem("Pingtung");
		place.addItem("Yilan");
		place.addItem("Hualien");
		place.addItem("Taitung");
		destinationPanel.add(label);
		destinationPanel.add(place);
	}

	public void createVacancyPanel() {
		vacancyPanel = new JPanel();
		JLabel label = new JLabel("Vacancies Available");
		vacancy = new JTextField(FIELD_WIDTH);
		vacancyPanel.add(label);
		vacancyPanel.add(vacancy);
	}

	public void createCarModelPanel() {
		carModelPanel = new JPanel();
		JLabel label = new JLabel("Car Model");
		carModel = new JTextField(FIELD_WIDTH);
		carModelPanel.add(label);
		carModelPanel.add(carModel);
	}

	public void createPricePanel() {
		pricePanel = new JPanel();
		JLabel label = new JLabel("Price per Person");
		slider = new RangeSlider();
		JPanel sliderValuePanel = new JPanel();
		JLabel upperValueLabel = new JLabel("Upper Value");
		upperValue = new JLabel();
		JLabel lowerValueLabel = new JLabel("Lower Value");
		lowerValue = new JLabel();
		sliderValuePanel.setLayout(new GridLayout(2, 2));
		sliderValuePanel.add(lowerValueLabel);
		sliderValuePanel.add(lowerValue);
		sliderValuePanel.add(upperValueLabel);
		sliderValuePanel.add(upperValue);
		slider.setMinimum(0);
		slider.setMaximum(5000);
		class ClickListener implements ChangeListener {
			public void stateChanged(ChangeEvent e) {
				slider = (RangeSlider) e.getSource();
				lowerValue.setText(String.valueOf(slider.getValue()));
				upperValue.setText(String.valueOf(((RangeSlider) slider).getUpperValue()));
			}
		}
		ChangeListener listener = new ClickListener();
		slider.addChangeListener(listener);
		pricePanel.add(label);
		pricePanel.add(slider);
		pricePanel.add(sliderValuePanel);
	}
	public void display() {
		slider.setValue(0);
		slider.setUpperValue(5000);
		lowerValue.setText(String.valueOf(slider.getValue()));
        upperValue.setText(String.valueOf(slider.getUpperValue()));
	}

	public void createButPanel() {
		butPanel = new JPanel();
		JButton createButton = new JButton("CREATE");
		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				int dYear = Integer.parseInt(year.getSelectedItem().toString());
				int dMonth = Integer.parseInt(month.getSelectedItem().toString());
				int dDay = Integer.parseInt(day.getSelectedItem().toString());
				int dHour = Integer.parseInt(hour.getSelectedItem().toString());
				int dMinute = Integer.parseInt(minute.getSelectedItem().toString());
				String date = dYear + "/";
				if (dMonth < 10) {
					date = date + "0" + dMonth + "/";
				} else {
					date = date + dMonth + "/";
				}
				if (dDay < 10) {
					date = date + "0" + dDay + " ";
				} else {
					date = date + dDay + " ";
				}
				if (dHour < 10) {
					date = date + "0" + dHour + ":";
				} else {
					date = date + dHour + ":";
				}
				if (dMinute < 10) {
					date = date + "0" + dMinute;
				} else {
					date = date + dMinute;
				}

				String start = startingPoint.getText();
				String destination = (String) place.getSelectedItem();
				int vacanciesAvaliable = Integer.parseInt(vacancy.getText());
				String carsModel = carModel.getText();
				int lowPrice = slider.getValue();
				int highPrice = ((RangeSlider) slider).getUpperValue();

				try {
					boolean add = exec.addDriverGroup(lowPrice, highPrice, 0, vacanciesAvaliable, start, destination, carsModel, date);
					if(add==true) {
						JFrame frame = new JFrame("");
						frame.setSize(400, 300);
						JOptionPane message = new JOptionPane();
						message.showMessageDialog(frame, "Case Added!");
					}
					else {
						JFrame frame = new JFrame("");
						frame.setSize(400, 300);
						JOptionPane message = new JOptionPane();
						message.showMessageDialog(frame, "Operation Failure!", "Failed", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JFrame frame = new JFrame("");
					frame.setSize(400, 300);
					JOptionPane message = new JOptionPane();
					message.showMessageDialog(frame, "Operation Failure!", "Failed", JOptionPane.ERROR_MESSAGE);
				}
				// suppose: add case and group, not sure whether succeed or not

				// show main page(card layout)
				try {
					Viewer.refresh();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Viewer.cardLayout.show(Viewer.switchPanel, "3");
			}
		}
		ActionListener listener = new ButtonListener();
		createButton.addActionListener(listener);
		JButton cancelButton = new JButton("CANCEL");
		class CancelListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				// card layout show main page
				try {
					Viewer.refresh();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Viewer.cardLayout.show(Viewer.switchPanel, "3");
			}
		}
		ActionListener cancel = new CancelListener();
		cancelButton.addActionListener(cancel);
		butPanel.add(createButton);
		butPanel.add(cancelButton);
	}

}
