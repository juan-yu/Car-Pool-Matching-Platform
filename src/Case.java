import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

public class Case {
	private int caseID;
	private int minPrice;
	private int maxPrice;
	private int numberOfPeopleNow;
	private int totalVacancy;
	private String creatorID;
	private String driverID;
	private String startPoint;
	private String destination;
	private String carModel;
	private Timestamp dateAndTime;

	public Case(int id, int minPrice, int maxPrice, int numberOfPeopleNow, int totalVacancy, String creatorID,
			String driverID, String startPoint, String destination, String carModel, Timestamp dateAndTime)
			throws SQLException, ParseException {
		this.caseID = id;
		this.minPrice = minPrice;
		this.numberOfPeopleNow = numberOfPeopleNow;
		this.totalVacancy = totalVacancy;
		this.creatorID = creatorID;
		this.driverID = driverID;
		this.startPoint = startPoint;
		this.destination = destination;
		this.carModel = carModel;
		this.dateAndTime = dateAndTime;
	}

	public int getCaseID() {
		return caseID;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public int getNumberOfPeopleNow() {
		return numberOfPeopleNow;
	}

	public int getTotalVacancy() {
		return totalVacancy;
	}

	public String getCreatorID() {
		return creatorID;
	}

	public String getDriverName() {
		return driverID;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public String getDestination() {
		return destination;
	}

	public String getCarModel() {
		return carModel;
	}

	public Timestamp getDateAndTime() {
		return dateAndTime;
	}

}
