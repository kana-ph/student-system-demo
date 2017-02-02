package ph.kana.testdemo.service;

import java.util.List;
import ph.kana.testdemo.exception.DataAccessException;
import ph.kana.testdemo.exception.ServiceException;
import ph.kana.testdemo.model.Student;
import ph.kana.testdemo.model.StudentRecord;
import ph.kana.testdemo.model.Subject;
import ph.kana.testdemo.repository.StudentRecordRepository;

class StudentRecordService {
	
	private final SubjectService subjectService = new SubjectService();
	private final StudentRecordRepository studentRecordRepository = new StudentRecordRepository();
	
	public void enroll(Student student, List<Subject> subjects) throws ServiceException {
		try {
			for (Subject subject : subjects) {
				List<Subject> preRequisites = subjectService.fetchPrerequisites(subject);

				for (Subject preRequisite : preRequisites) {
					StudentRecord record = studentRecordRepository
						.findByStudentAndSubject(student, preRequisite);

					if (record != null && record.isPassed()) {
						enrollToSubject(student, subject);
					}
				}
			}
		} catch (DataAccessException e) {
			throw new ServiceException("Error in enrollment", e);
		}
	}
	
	private StudentRecord enrollToSubject(Student student, Subject subject) throws DataAccessException {
		StudentRecord enrollRecord = new StudentRecord();
		enrollRecord.setStudent(student);
		enrollRecord.setSubject(subject);
		studentRecordRepository.save(enrollRecord);
		
		return enrollRecord;
	}
}
