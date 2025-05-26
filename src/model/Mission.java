package model;

public class Mission {
	private int mission_ID;
	private String mission_name;
	private String location;
	private int agent_ID;
	private int access_ID;
	private int team_ID;
	private String mission_status;
	
	public Mission() {
		super();
	}

	public Mission(int mission_ID, String mission_name, String location, int agent_ID, int access_ID, int team_ID,
			String mission_status) {
		super();
		this.mission_ID = mission_ID;
		this.mission_name = mission_name;
		this.location = location;
		this.agent_ID = agent_ID;
		this.access_ID = access_ID;
		this.team_ID = team_ID;
		this.mission_status = mission_status;
	}

	public int getMission_ID() {
		return mission_ID;
	}

	public void setMission_ID(int mission_ID) {
		this.mission_ID = mission_ID;
	}

	public String getMission_name() {
		return mission_name;
	}

	public void setMission_name(String mission_name) {
		this.mission_name = mission_name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getAgent_ID() {
		return agent_ID;
	}

	public void setAgent_ID(int agent_ID) {
		this.agent_ID = agent_ID;
	}

	public int getAccess_ID() {
		return access_ID;
	}

	public void setAccess_ID(int access_ID) {
		this.access_ID = access_ID;
	}

	public int getTeam_ID() {
		return team_ID;
	}

	public void setTeam_ID(int team_ID) {
		this.team_ID = team_ID;
	}

	public String getMission_status() {
		return mission_status;
	}

	public void setMission_status(String mission_status) {
		this.mission_status = mission_status;
	}
	
	
}
