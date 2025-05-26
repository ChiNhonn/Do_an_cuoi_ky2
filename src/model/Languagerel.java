package model;

public class Languagerel {
	private int agent_ID;
	private int language_ID;
	
	public Languagerel() {
		super();
	}

	public Languagerel(int agent_ID, int language_ID) {
		super();
		this.agent_ID = agent_ID;
		this.language_ID = language_ID;
	}

	public int getAgent_ID() {
		return agent_ID;
	}

	public void setAgent_ID(int agent_ID) {
		this.agent_ID = agent_ID;
	}

	public int getLanguage_ID() {
		return language_ID;
	}

	public void setLanguage_ID(int language_ID) {
		this.language_ID = language_ID;
	}
}
