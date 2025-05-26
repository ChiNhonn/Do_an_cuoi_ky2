package model;

public class Affiliation {
	public int affiliation_id;
	public String affiliation_name;
	public String description;
	
	public Affiliation() {
		super();
	}

	public Affiliation(int affiliation_id, String affiliation_name, String description) {
		super();
		this.affiliation_id = affiliation_id;
		this.affiliation_name = affiliation_name;
		this.description = description;
	}

	public int getAffiliation_id() {
		return affiliation_id;
	}

	public void setAffiliation_id(int affiliation_id) {
		this.affiliation_id = affiliation_id;
	}

	public String getAffiliation_name() {
		return affiliation_name;
	}

	public void setAffiliation_name(String affiliation_name) {
		this.affiliation_name = affiliation_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
