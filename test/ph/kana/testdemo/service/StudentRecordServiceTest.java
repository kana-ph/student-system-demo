package ph.kana.testdemo.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ph.kana.testdemo.exception.ServiceException;
import ph.kana.testdemo.model.Student;
import ph.kana.testdemo.model.StudentRecord;
import ph.kana.testdemo.model.Subject;
import ph.kana.testdemo.repository.StudentRecordRepository;

public class StudentRecordServiceTest {

	private StudentRecordService service;

	@Mock
	private SubjectService subjectService;
	@Mock
	private StudentRecordRepository studentRecordRepository;

	@BeforeClass
	public static void setupClass() throws Exception {
	}

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);

		service = new StudentRecordService();
		service.setSubjectService(subjectService);
		service.setStudentRecordRepository(studentRecordRepository);
	}

	@Test
	public void enrollShouldSaveStudentRecordIfNoPrerequistesForGivenSubject() throws Exception {
		// enroll should save student record if no pre-requisites for given subject
		// given
		Student student = new Student();
		Subject subject = new Subject();

		given(subjectService.fetchPrerequisites(subject))
			.willReturn(Collections.EMPTY_LIST);

		// when
		service.enroll(student, Arrays.asList(subject));

		// then
		then(studentRecordRepository)
			.should(times(1))
			.save(any(StudentRecord.class));
	}

	@Test
	public void enrollShouldSaveStudentRecordIfPrerequisitedPassed() throws Exception {
		// enroll should save student record if pre-requistes passed
		// given
		Student student = new Student();
		Subject subject = new Subject();
		Subject requiredSubject = new Subject();

		given(subjectService.fetchPrerequisites(subject))
			.willReturn(Arrays.asList(requiredSubject));

		StudentRecord passedPrerequisiteRecord = mock(StudentRecord.class);
		given(passedPrerequisiteRecord.isPassed())
			.willReturn(true);

		given(studentRecordRepository.findByStudentAndSubject(student, requiredSubject))
			.willReturn(passedPrerequisiteRecord);

		// when
		service.enroll(student, Arrays.asList(subject));

		// then
		then(studentRecordRepository)
			.should(times(1))
			.save(any(StudentRecord.class));
	}

	@Test(expected = ServiceException.class)
	public void enrollShouldNotContinueIfASubjectFailsPrerequisiteValidation() throws Exception {
		// enroll should not continue if a subject fails pre-requisite validation
		// given
		Student student = new Student();
		Subject subject = new Subject();
		subject.setId(1L);

		List<Subject> requiredSubjects = Arrays.asList(new Subject(), new Subject());
		requiredSubjects.get(0).setId(2L);
		requiredSubjects.get(1).setId(3L);

		given(subjectService.fetchPrerequisites(subject))
			.willReturn(requiredSubjects);

		StudentRecord passedPrerequisiteRecord = mock(StudentRecord.class);
		given(passedPrerequisiteRecord.isPassed())
			.willReturn(true);
		given(studentRecordRepository.findByStudentAndSubject(student, requiredSubjects.get(0)))
			.willReturn(passedPrerequisiteRecord);

		StudentRecord failedPrerequisiteRecord = mock(StudentRecord.class);
		given(failedPrerequisiteRecord.isPassed())
			.willReturn(false);
		given(studentRecordRepository.findByStudentAndSubject(student, requiredSubjects.get(1)))
			.willReturn(failedPrerequisiteRecord);

		// when
		service.enroll(student, Arrays.asList(subject));

		// then
		then(studentRecordRepository)
			.should(times(0))
			.save(any(StudentRecord.class));
	}

}
