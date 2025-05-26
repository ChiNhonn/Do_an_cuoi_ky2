package model;

public class Teamrel {
	public int agent_ID;
	public int team_ID;
	
	public Teamrel() {
		super();
	}

	public Teamrel(int agent_ID, int team_ID) {
		super();
		this.agent_ID = agent_ID;
		this.team_ID = team_ID;
	}

	public int getAgent_ID() {
		return agent_ID;
	}

	public void setAgent_ID(int agent_ID) {
		this.agent_ID = agent_ID;
	}

	public int getTeam_ID() {
		return team_ID;
	}

	public void setTeam_ID(int team_ID) {
		this.team_ID = team_ID;
	}
}
