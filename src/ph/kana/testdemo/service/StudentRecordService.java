package ph.kana.testdemo.service;

import java.util.List;
import ph.kana.testdemo.model.Student;
import ph.kana.testdemo.model.StudentRecord;
import ph.kana.testdemo.model.Subject;
import ph.kana.testdemo.repository.StudentRecordRepository;

class StudentRecordService {
	
	private final SubjectService subjectService = new SubjectService();
	private final StudentRecordRepository studentRecordRepository = new StudentRecordRepository();
	
	public void enroll(Student student, List<Subject> subjects) {		
		for (Subject subject : subjects) {
			List<Subject> preRequisites = subjectService.fetchPrerequisites(subject);
			
			for (Subject preRequisite : preRequisites) {
				StudentRecord record = findStudentRecord(student, preRequisite);
				
				if (record != null && record.isPassed()) {
					enrollToSubject(student, subject);
				}
			}
		}
	}
	
	private StudentRecord findStudentRecord(Student student, Subject subject) {
		return studentRecordRepository.findByStudentAndSubject(student, subject);
	}
	
	private StudentRecord enrollToSubject(Student student, Subject subject) {
		StudentRecord enrollRecord = new StudentRecord();
		enrollRecord.setStudent(student);
		enrollRecord.setSubject(subject);
		studentRecordRepository.save(enrollRecord);
		
		return enrollRecord;
	}
}
