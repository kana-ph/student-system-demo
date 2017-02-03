package ph.kana.testdemo.model;

import java.util.ArrayList;
import java.util.List;
import ph.kana.testdemo.exception.SubjectRequirementException;

public class Subject {

	private Long id;
	private String code;
	private String description;

	private final List<Subject> preRequisites;

	public Subject() {
		preRequisites = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Subject> getPreRequisites() {
		return new ArrayList(preRequisites);
	}

	public void addToPreRequisites(Subject preRequisite) {
		if (preRequisite == null) {
			throw new NullPointerException();
		}
		if (equals(preRequisite)) {
			throw new SubjectRequirementException("Cannot set itself as a pre-requisite");
		}

		preRequisites.add(preRequisite);
	}

	@Override
	public boolean equals(Object otherSubject) {
		if (otherSubject == null) {
			return false;
		}
		if (otherSubject instanceof Subject) {
			return (id.equals(((Subject)otherSubject).id));
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + id.hashCode();
		return hash;
	}
}
