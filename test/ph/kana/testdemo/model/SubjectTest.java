package ph.kana.testdemo.model;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import ph.kana.testdemo.exception.SubjectRequirementException;

public class SubjectTest {

	@Test
	public void setCodeShouldAssignTheSubjectCode() {
		// setCode should assign the subject code
		// given
		Subject subject = new Subject();
		String code = "COMP1013";

		// when
		subject.setCode(code);

		// then
		assertEquals(code, subject.getCode());
	}

	@Test
	public void equalsShouldReturnTrueIfBothIdsAreEqual() {
		// equals should return true if both ids are equal
		// given
		Subject subject1 = new Subject();
		subject1.setId(1L);

		Subject subject2 = new Subject();
		subject2.setId(1L);

		// when
		boolean s1s2 = subject1.equals(subject2);
		boolean s2s1 = subject2.equals(subject1);

		// then
		assertTrue(s1s2);
		assertTrue(s2s1);
	}

	@Test
	public void addToPreRequisitesShouldAddTheGivenSubjectToList() {
		// addToPreRequisites should add the given subject to list
		// given
		Subject subject = new Subject();
		subject.setId(1L);

		Subject preRequisite = new Subject();
		preRequisite.setId(2L);

		// when
		subject.addToPreRequisites(preRequisite);

		// then
		List<Subject> preRequisites = subject.getPreRequisites();
		assertTrue(preRequisites.contains(preRequisite));
	}

	@Test(expected = NullPointerException.class)
	public void addToPreRequisitesShouldThrowNpeIfInputIsNull() {
		// addToPreRequisites should throw NPE if input is null
		// given
		Subject subject = new Subject();
		Subject preRequisite = null;

		// when
		subject.addToPreRequisites(preRequisite);

		// then excepted to throw NullPointerException
	}

	@Test(expected = SubjectRequirementException.class)
	public void addToPreRequisitesShouldThrowSubjectRequirementExceptionIfInputIsEquivalentToSelf() {
		// addToPreRequisites should throw SubjectRequirementException if input is equivalent to self
		// given
		Subject subject = new Subject();
		subject.setId(1L);

		// when
		subject.addToPreRequisites(subject);

		// then excepted to throw SubjectRequirementException
	}
}
