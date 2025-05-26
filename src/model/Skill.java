package model;

public class Skill {
	public int skill_ID;
	public String skill_name;
	
	public Skill() {
		super();
	}

	public Skill(int skill_ID, String skill_name) {
		super();
		this.skill_ID = skill_ID;
		this.skill_name = skill_name;
	}

	public int getSkill_ID() {
		return skill_ID;
	}

	public void setSkill_ID(int skill_ID) {
		this.skill_ID = skill_ID;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
}
