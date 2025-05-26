package model;

public class Skillrel {
	public int agent_ID;
	public int skill_ID;
	
	public Skillrel() {
		super();
	}

	public Skillrel(int agent_ID, int skill_ID) {
		super();
		this.agent_ID = agent_ID;
		this.skill_ID = skill_ID;
	}

	public int getAgent_ID() {
		return agent_ID;
	}

	public void setAgent_ID(int agent_ID) {
		this.agent_ID = agent_ID;
	}

	public int getSkill_ID() {
		return skill_ID;
	}

	public void setSkill_ID(int skill_ID) {
		this.skill_ID = skill_ID;
	}
}
