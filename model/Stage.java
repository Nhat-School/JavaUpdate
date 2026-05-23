package model;

import java.io.Serializable;
import java.util.Date;

public class Stage implements Serializable {
	private int id;
	private String stageCode;
	private String name;
	private int numberLaps;
	private String location;
	private Date time;
	private String description;
	private Tournament tournament;

	public Stage() {
		super();
	}

	public Stage(int id, String stageCode, String name, int numberLaps, String location, Date time, String description, Tournament tournament) {
		super();
		this.id = id;
		this.stageCode = stageCode;
		this.name = name;
		this.numberLaps = numberLaps;
		this.location = location;
		this.time = time;
		this.description = description;
		this.tournament = tournament;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getStageCode() { return stageCode; }
	public void setStageCode(String stageCode) { this.stageCode = stageCode; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public int getNumberLaps() { return numberLaps; }
	public void setNumberLaps(int numberLaps) { this.numberLaps = numberLaps; }

	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }

	public Date getTime() { return time; }
	public void setTime(Date time) { this.time = time; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public Tournament getTournament() { return tournament; }
	public void setTournament(Tournament tournament) { this.tournament = tournament; }
	
	@Override
	public String toString() {
		return stageCode + " - " + name + " (" + location + ")";
	}
}
