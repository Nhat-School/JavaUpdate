package model;

import java.io.Serializable;

public class Contract implements Serializable {
	private int id;
	private Team team;
	private Racer racer;

	public Contract() {}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public Team getTeam() { return team; }
	public void setTeam(Team team) { this.team = team; }

	public Racer getRacer() { return racer; }
	public void setRacer(Racer racer) { this.racer = racer; }
}
