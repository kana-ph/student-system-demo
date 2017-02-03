package ph.kana.testdemo.model;

public class StudentRecord {

	private static final double PASS_THRESHOLD = 60.0;
	private static final double MIDTERM_WEIGHT = 0.4;
	private static final double FINALS_WEIGHT = 0.6;

	private Student student;
	private Subject subject;
	private double midtermGrade;
	private double finalGrade;

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

	public double getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(double finalGrade) {
		this.finalGrade = finalGrade;
	}

	public double getMidtermGrade() {
		return midtermGrade;
	}

	public void setMidtermGrade(double midtermGrade) {
		this.midtermGrade = midtermGrade;
	}

	public boolean isPassed() {
		return getWeightedAverage() >= PASS_THRESHOLD;
	}

	public double getWeightedAverage() {
		double weightedMidterm = midtermGrade * MIDTERM_WEIGHT;
		double weightedFinals = finalGrade * FINALS_WEIGHT;

		return (weightedMidterm + weightedFinals);
	}
}
