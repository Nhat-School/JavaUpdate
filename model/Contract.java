package model;

import java.io.Serializable;
import java.util.Date;

public class Contract implements Serializable {
	private int id;
	private Date startDate;
	private Date endDate;
	private Team team;
	private Racer racer;

	public Contract() {
		super();
	}

	public Contract(int id, Date startDate, Date endDate, Team team, Racer racer) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.team = team;
		this.racer = racer;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public Date getStartDate() { return startDate; }
	public void setStartDate(Date startDate) { this.startDate = startDate; }

	public Date getEndDate() { return endDate; }
	public void setEndDate(Date endDate) { this.endDate = endDate; }

	public Team getTeam() { return team; }
	public void setTeam(Team team) { this.team = team; }

	public Racer getRacer() { return racer; }
	public void setRacer(Racer racer) { this.racer = racer; }
}
