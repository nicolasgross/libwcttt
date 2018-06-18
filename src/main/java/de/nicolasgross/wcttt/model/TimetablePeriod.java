package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"assignments"})
public class TimetablePeriod extends Period {

	private final List<TimetableAssignment> assignments = new LinkedList<>();

	public TimetablePeriod() {
		super();
	}

	public TimetablePeriod(int day, int timeSlot) throws WctttModelException {
		super(day, timeSlot);
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "assignment")
	public List<TimetableAssignment> getAssignments() {
		return assignments;
	}

	private boolean assignmentExists(TimetableAssignment assignment) {
		for (TimetableAssignment existingAss : assignments) {
			if (assignment.equals(existingAss)) {
				return true;
			}
		}
		return false;
	}

	public void addAssignment(TimetableAssignment assignment) throws
			WctttModelException {
		if (assignment == null) {
			throw new IllegalArgumentException("Parameter 'assignment' must " +
					"not be null");
		} else if (assignmentExists(assignment)) {
			throw new WctttModelException("Assignment '" + assignment + "' is" +
					"already included in this period");
		}
		assignments.add(assignment);
	}

	public boolean removeAssignment(TimetableAssignment assignment) {
		if (assignment == null) {
			throw new IllegalArgumentException("Parameter 'assignment' must " +
					"not be null");
		}
		return assignments.remove(assignment);
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
