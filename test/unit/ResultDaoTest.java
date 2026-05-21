package test.unit;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import dao.ResultDAO;
import model.Result;

public class ResultDaoTest {
	ResultDAO rd = new ResultDAO();

	@Test
	public void testGetRegisteredRacersException1() {
		// Test case 1: Invalid stage ID
		int stageId = 999;
		ArrayList<Result> list = rd.getRegisteredRacers(stageId);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
	}

	@Test
	public void testGetRegisteredRacersException2() {
		// Test case 2: Negative stage ID
		int stageId = -10;
		ArrayList<Result> list = rd.getRegisteredRacers(stageId);
		Assert.assertNotNull(list);
		Assert.assertEquals(0, list.size());
	}

	@Test
	public void testGetRegisteredRacersStandard1(){
		// Test case 3: Valid stage ID (Bahrain GP - stage 1)
		int stageId = 1;
		ArrayList<Result> list = rd.getRegisteredRacers(stageId);
		Assert.assertNotNull(list);
		Assert.assertEquals(5, list.size());
		// Verify first racer is Charles Leclerc (LEC)
		Assert.assertEquals("LEC", list.get(0).getContract().getRacer().getDriverCode());
	}
}