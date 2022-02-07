public class Requests {
	private int ID;
	private String requesterID;
	private int caseID;
	private String status;
	private ExecSQL ex;

	public Requests(String requesterID, int caseID) {

		this.ID = ID;
		this.requesterID = requesterID;
		this.caseID = caseID;
		this.status = "Pending";
		this.ex = new ExecSQL();

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getRequesterID() {
		return requesterID;
	}

	public void setRequesterID(String requesterID) {
		this.requesterID = requesterID;
	}

	public int getCaseID() {
		return caseID;
	}

	public void setCaseID(int caseID) {
		this.caseID = caseID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
