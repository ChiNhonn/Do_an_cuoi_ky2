package model;

public class Language {
	private int language_ID;
	private String language_name;
	
	public Language() {
		super();
	}

	public Language(int language_ID, String language_name) {
		super();
		this.language_ID = language_ID;
		this.language_name = language_name;
	}

	public int getLanguage_ID() {
		return language_ID;
	}

	public void setLanguage_ID(int language_ID) {
		this.language_ID = language_ID;
	}

	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	
}
