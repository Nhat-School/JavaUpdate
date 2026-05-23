package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Contract;
import model.Racer;
import model.Result;
import model.Stage;
import model.Team;
import model.User;

public class ResultDAO extends DAO {

	public ResultDAO() {
		super();
	}

	public ArrayList<Result> getRegisteredRacers(int stageID) {
		ArrayList<Result> results = new ArrayList<Result>();
		String sql = "SELECT r.id AS resultId, r.finishTime, r.lapsCompleted, r.idUser AS userId, " +
				"c.id AS contractId, c.startDate, c.endDate, rc.id AS racerId, rc.driverCode, rc.name AS racerName, rc.nationality, " +
				"t.id AS teamId, t.teamCode, t.name AS teamName, t.brand " +
				"FROM tblContract c " +
				"JOIN tblRacer rc ON c.idRacer = rc.id " +
				"JOIN tblTeam t ON c.idTeam = t.id " +
				"LEFT JOIN tblResult r ON r.idContract = c.id AND r.idStage = ? " +
				"WHERE c.startDate <= (SELECT time FROM tblStage WHERE id = ?) " +
				"AND c.endDate >= (SELECT time FROM tblStage WHERE id = ?) " +
				"ORDER BY rc.driverCode";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, stageID);
			ps.setInt(2, stageID);
			ps.setInt(3, stageID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// Build Racer
				Racer racer = new Racer();
				racer.setId(rs.getInt("racerId"));
				racer.setDriverCode(rs.getString("driverCode"));
				racer.setName(rs.getString("racerName"));
				racer.setNationality(rs.getString("nationality"));

				// Build Team
				Team team = new Team();
				team.setId(rs.getInt("teamId"));
				team.setTeamCode(rs.getString("teamCode"));
				team.setName(rs.getString("teamName"));
				team.setBrand(rs.getString("brand"));

				// Build Contract
				Contract contract = new Contract();
				contract.setId(rs.getInt("contractId"));
				contract.setStartDate(rs.getDate("startDate"));
				contract.setEndDate(rs.getDate("endDate"));
				contract.setRacer(racer);
				contract.setTeam(team);

				// Build Result
				Result result = new Result();
				result.setId(rs.getInt("resultId"));
				if (!rs.wasNull()) {
					result.setFinishTime(rs.getString("finishTime"));
					result.setLapsCompleted(rs.getInt("lapsCompleted"));
					
					int userId = rs.getInt("userId");
					if (!rs.wasNull()) {
						User creator = new User();
						creator.setId(userId);
						result.setUser(creator);
					}
				} else {
					result.setFinishTime("");
				}
				result.setContract(contract);
				
				// Build Stage
				Stage stage = new Stage();
				stage.setId(stageID);
				result.setStage(stage);

				results.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}


	public boolean updateRaceResults(ArrayList<Result> results) {
		boolean success = true;
		try {
			for (Result result : results) {
				if (result.getId() > 0) {
					// Update existing result
					String sql = "UPDATE tblResult SET finishTime = ?, lapsCompleted = ? WHERE id = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, result.getFinishTime());
					ps.setInt(2, result.getLapsCompleted());
					ps.setInt(3, result.getId());
					ps.executeUpdate();
				} else {
					// Insert new result
					String sql = "INSERT INTO tblResult (finishTime, lapsCompleted, idStage, idContract, idUser) VALUES (?, ?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, result.getFinishTime());
					ps.setInt(2, result.getLapsCompleted());
					ps.setInt(3, result.getStage().getId());
					ps.setInt(4, result.getContract().getId());
					ps.setInt(5, result.getUser().getId());
					ps.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}
}
