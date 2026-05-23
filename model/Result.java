package model;

import java.io.Serializable;

public class Result implements Serializable {
	private int id;
	private String finishTime;
	private int lapsCompleted;
	private Stage stage;
	private Contract contract;
	private User user;

	public Result() {
		super();
	}

	public Result(int id, String finishTime, int lapsCompleted, Stage stage, Contract contract, User user) {
		super();
		this.id = id;
		this.finishTime = finishTime;
		this.lapsCompleted = lapsCompleted;
		this.stage = stage;
		this.contract = contract;
		this.user = user;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getFinishTime() { return finishTime; }
	public void setFinishTime(String finishTime) { this.finishTime = finishTime; }

	public int getLapsCompleted() { return lapsCompleted; }
	public void setLapsCompleted(int lapsCompleted) { this.lapsCompleted = lapsCompleted; }

	public Stage getStage() { return stage; }
	public void setStage(Stage stage) { this.stage = stage; }

	public Contract getContract() { return contract; }
	public void setContract(Contract contract) { this.contract = contract; }

	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
}
