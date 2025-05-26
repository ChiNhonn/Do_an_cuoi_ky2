package model;

public class Team {
	public int team_ID;
	public String team_name;
	public String meeting_frequency;
	
	public Team(int team_ID, String team_name, String meeting_frequency) {
		super();
		this.team_ID = team_ID;
		this.team_name = team_name;
		this.meeting_frequency = meeting_frequency;
	}

	public Team() {
		super();
	}

	public int getTeam_ID() {
		return team_ID;
	}

	public void setTeam_ID(int team_ID) {
		this.team_ID = team_ID;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getMeeting_frequency() {
		return meeting_frequency;
	}

	public void setMeeting_frequency(String meeting_frequency) {
		this.meeting_frequency = meeting_frequency;
	}
}
