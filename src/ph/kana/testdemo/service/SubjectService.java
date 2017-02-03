package ph.kana.testdemo.service;

import java.util.List;
import ph.kana.testdemo.model.Subject;

public class SubjectService {

	public List<Subject> fetchPrerequisites(Subject subject) {
		return subject.getPreRequisites();
	}
}
