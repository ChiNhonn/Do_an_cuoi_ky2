package model;

public class Securityclearance {
	public int sc_ID;
	public String sc_level;
	public String description;
	
	public Securityclearance() {
		super();
	}

	public Securityclearance(int sc_ID, String sc_level, String description) {
		super();
		this.sc_ID = sc_ID;
		this.sc_level = sc_level;
		this.description = description;
	}

	public int getSc_ID() {
		return sc_ID;
	}

	public void setSc_ID(int sc_ID) {
		this.sc_ID = sc_ID;
	}

	public String getSc_level() {
		return sc_level;
	}

	public void setSc_level(String sc_level) {
		this.sc_level = sc_level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
