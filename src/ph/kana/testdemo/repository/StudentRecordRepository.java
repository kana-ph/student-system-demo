package ph.kana.testdemo.repository;

import ph.kana.testdemo.exception.DataAccessException;
import ph.kana.testdemo.model.Student;
import ph.kana.testdemo.model.StudentRecord;
import ph.kana.testdemo.model.Subject;

public class StudentRecordRepository {

	public StudentRecord findByStudentAndSubject(Student student, Subject subject) throws DataAccessException {
		// SQL stuffs
		return new StudentRecord();
	}

	public StudentRecord save(StudentRecord studentRecord) throws DataAccessException {
		// SQL stuffs
		return studentRecord;
	}
	
}
