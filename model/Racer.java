package model;

import java.io.Serializable;

public class Racer implements Serializable {
	private int id;
	private String driverCode;
	private String name;
	private String nationality;

	public Racer() {}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getDriverCode() { return driverCode; }
	public void setDriverCode(String driverCode) { this.driverCode = driverCode; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getNationality() { return nationality; }
	public void setNationality(String nationality) { this.nationality = nationality; }
}
