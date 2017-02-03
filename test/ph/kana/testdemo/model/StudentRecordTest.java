package ph.kana.testdemo.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class StudentRecordTest {

	@Test
	public void isPassedShouldReturnTrueIfMidtermsAndFinalsPassed() {
		// given
		StudentRecord record = new StudentRecord();
		record.setMidtermGrade(90.0);
		record.setFinalGrade(90.0);

		// when
		boolean passed = record.isPassed();

		// then
		assertTrue(passed);
	}

	@Test
	public void getWeightedAverageShouldComputeGradesAgainstWeights() {
		// given
		StudentRecord record = new StudentRecord();
		record.setMidtermGrade(50.00);
		record.setFinalGrade(90.00);

		double expected = 74.00;

		// when
		double actual = record.getWeightedAverage();

		// then
		assertEquals(expected, actual, 0.00);
	}
}
