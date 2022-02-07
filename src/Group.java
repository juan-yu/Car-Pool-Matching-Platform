public class Group {
	private String accountID;
	private int caseID;
	private int groupID;

	public Group(String currentUserID, int currentCaseID) {
		accountID = currentUserID;
		caseID = currentCaseID;
	}

	public String getAccountID() {
		return accountID;
	}

	public int getCaseID() {
		return caseID;
	}

	public void setAccountID(String ID) {
		accountID = ID;
	}

	public void setCaseID(int ID) {
		caseID = ID;
	}

}
