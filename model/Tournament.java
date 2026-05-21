package model;

import java.io.Serializable;

public class Tournament implements Serializable {
	private int id;
	private String name;
	private int year;

	public Tournament() {}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getYear() { return year; }
	public void setYear(int year) { this.year = year; }
}
