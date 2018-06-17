package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"assignments"})
public class TimetablePeriod extends Period {

	private List<TimetableAssignment> assignments = new LinkedList<>();

	@XmlElementWrapper(required = true)
	@XmlElement(name = "assignment")
	public List<TimetableAssignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<TimetableAssignment> assignments) {
		this.assignments = assignments;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TimetablePeriod)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		TimetablePeriod other = (TimetablePeriod) obj;

		if (this.getDay() != other.getDay() ||
				this.getTimeSlot() != other.getTimeSlot()) {
			return false;
		}

		if (this.assignments.size() != other.assignments.size()) {
			return false;
		} else if (this.assignments != other.assignments) {
			if (!(this.assignments.containsAll(other.assignments))) {
				return false;
			}
		}

		return true;
	}

}
