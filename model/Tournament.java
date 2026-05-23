package model;

import java.io.Serializable;

public class Tournament implements Serializable {
	private int id;
	private String name;
	private int year;
	private Organization organization;

	public Tournament() {
		super();
	}

	public Tournament(int id, String name, int year, Organization organization) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.organization = organization;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getYear() { return year; }
	public void setYear(int year) { this.year = year; }

	public Organization getOrganization() { return organization; }
	public void setOrganization(Organization organization) { this.organization = organization; }
}
