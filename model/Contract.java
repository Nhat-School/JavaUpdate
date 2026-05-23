package model;

import java.io.Serializable;

public class Contract implements Serializable {
	private int id;
	private Team team;
	private Racer racer;

	public Contract() {
		super();
	}

	public Contract(int id, Team team, Racer racer) {
		super();
		this.id = id;
		this.team = team;
		this.racer = racer;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public Team getTeam() { return team; }
	public void setTeam(Team team) { this.team = team; }

	public Racer getRacer() { return racer; }
	public void setRacer(Racer racer) { this.racer = racer; }
}
