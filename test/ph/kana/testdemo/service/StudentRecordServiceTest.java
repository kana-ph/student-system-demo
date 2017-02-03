package ph.kana.testdemo.service;

import java.util.Arrays;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.BDDMockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
	public void enrollShouldSaveStudentRecordIfPrerequisitedPassed() throws Exception {
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

}
