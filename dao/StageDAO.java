package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Stage;

public class StageDAO extends DAO {

	public StageDAO() {
		super();
	}

	public ArrayList<Stage> getAllStages() {
		ArrayList<Stage> stages = new ArrayList<Stage>();
		String sql = "SELECT id, stageCode, name, numberLaps, location, time, description FROM tblStage ORDER BY time";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Stage stage = new Stage();
				stage.setId(rs.getInt("id"));
				stage.setStageCode(rs.getString("stageCode"));
				stage.setName(rs.getString("name"));
				stage.setNumberLaps(rs.getInt("numberLaps"));
				stage.setLocation(rs.getString("location"));
				stage.setTime(rs.getDate("time"));
				stage.setDescription(rs.getString("description"));
				stages.add(stage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stages;
	}
}
