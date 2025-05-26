package model;

public class Agent {
	private int agent_ID;
	private String first_name;
	private String last_name;
	private String addrest;
	private String city;
	private String country;
	private float salary;
	
	public Agent() {
		super();
	}

	public Agent(int agent_ID, String first_name, String last_name, String addrest, String city, String country,
			float salary) {
		super();
		this.agent_ID = agent_ID;
		this.first_name = first_name;
		this.last_name = last_name;
		this.addrest = addrest;
		this.city = city;
		this.country = country;
		this.salary = salary;
	}

	public int getAgent_ID() {
		return agent_ID;
	}

	public void setAgent_ID(int agent_ID) {
		this.agent_ID = agent_ID;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddrest() {
		return addrest;
	}

	public void setAddrest(String addrest) {
		this.addrest = addrest;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	
}
