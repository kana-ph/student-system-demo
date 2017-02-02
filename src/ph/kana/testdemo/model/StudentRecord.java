package ph.kana.testdemo.model;

public class StudentRecord {
	
	private Student student;
	private Subject subject;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public boolean isPassed() {
		return true;
	}
	
}
