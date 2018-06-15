package de.nicolasgross.wcttt.intern.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"assignments"})
public class TimetablePeriod extends Period {

	private List<TimetableAssignment> assignments = new LinkedList<>();

	@XmlElement(required = true)
	public List<TimetableAssignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<TimetableAssignment> assignments) {
		this.assignments = assignments;
	}

}
