package de.nicolasgross.wcttt.lib.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(propOrder = {"assignments"})
public class TimetablePeriod extends Period {

	private final ObservableList<TimetableAssignment> assignments =
			FXCollections.observableArrayList();

	public TimetablePeriod() {
		super();
	}

	public TimetablePeriod(int day, int timeSlot) throws WctttModelException {
		super(day, timeSlot);
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetableAssignment")
	public ObservableList<TimetableAssignment> getAssignments() {
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
					" already included in this period");
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		TimetablePeriod that = (TimetablePeriod) o;
		return Objects.equals(assignments, that.assignments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), assignments);
	}
}
