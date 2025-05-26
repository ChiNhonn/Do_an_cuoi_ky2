package model;

public class Affiliationrel {
	public int affiliation_ID;
	public int agent_ID;
	public String affiliation_strength;
	public Affiliationrel() {
		super();
	}
	
	public Affiliationrel(int affiliation_ID, int agent_ID, String affiliation_strength) {
		super();
		this.affiliation_ID = affiliation_ID;
		this.agent_ID = agent_ID;
		this.affiliation_strength = affiliation_strength;
	}
	
	public int getAffiliation_ID() {
		return affiliation_ID;
	}
	
	public void setAffiliation_ID(int affiliation_ID) {
		this.affiliation_ID = affiliation_ID;
	}
	
	public int getAgent_ID() {
		return agent_ID;
	}
	
	public void setAgent_ID(int agent_ID) {
		this.agent_ID = agent_ID;
	}
	
	public String getAffiliation_strength() {
		return affiliation_strength;
	}
	
	public void setAffiliation_strength(String affiliation_strength) {
		this.affiliation_strength = affiliation_strength;
	}
	
	
}
