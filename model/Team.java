package model;

import java.io.Serializable;

public class Team implements Serializable {
	private int id;
	private String teamCode;
	private String name;
	private String brand;

	public Team() {
		super();
	}

	public Team(int id, String teamCode, String name, String brand) {
		super();
		this.id = id;
		this.teamCode = teamCode;
		this.name = name;
		this.brand = brand;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getTeamCode() { return teamCode; }
	public void setTeamCode(String teamCode) { this.teamCode = teamCode; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getBrand() { return brand; }
	public void setBrand(String brand) { this.brand = brand; }
}
