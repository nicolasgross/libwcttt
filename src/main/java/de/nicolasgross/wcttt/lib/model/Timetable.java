package de.nicolasgross.wcttt.lib.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "softConstraintPenalty", "days"})
public class Timetable {

	private String name;
	private double softConstraintPenalty = 0.0;
	private final ObservableList<TimetableDay> days =
			FXCollections.observableArrayList();

	public Timetable() {
		this.name = "timetable";
	}

	public Timetable(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	public Timetable(Timetable timetable) {
		if (timetable == null) {
			throw new IllegalArgumentException("Parameter 'timetable' must " +
					"not be null");
		}
		this.name = timetable.name;
		this.softConstraintPenalty = timetable.softConstraintPenalty;
		for (TimetableDay day : timetable.days) {
			try {
				TimetableDay newDay = new TimetableDay(day.getDay());
				for (TimetablePeriod period : day.getPeriods()) {
					TimetablePeriod newPeriod = new TimetablePeriod(
							period.getDay(), period.getTimeSlot());
					for (TimetableAssignment assignment : period.getAssignments()) {
						TimetableAssignment newAssignment =
								new TimetableAssignment(assignment.getSession(),
										assignment.getRoom());
						newPeriod.addAssignment(newAssignment);
					}
					newDay.addPeriod(newPeriod);
				}
				this.addDay(newDay);
			} catch (WctttModelException e) {
				throw new WctttModelFatalException("Implementation error, " +
						"there is a problem with cloning a timetable", e);
			}
		}
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	@XmlAttribute(required = true)
	public double getSoftConstraintPenalty() {
		return softConstraintPenalty;
	}

	public void setSoftConstraintPenalty(double softConstraintPenalty) {
		this.softConstraintPenalty = softConstraintPenalty;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetableDay")
	public ObservableList<TimetableDay> getDays() {
		return days;
	}

	private boolean dayExists(TimetableDay day) {
		for (TimetableDay existingDay : days) {
			if (day.getDay() == existingDay.getDay()) {
				return true;
			}
		}
		return false;
	}

	public void addDay(TimetableDay day) throws WctttModelException {
		if (day == null) {
			throw new IllegalArgumentException("Parameter 'day' must not " +
					"be null");
		} else if (dayExists(day)) {
			throw new WctttModelException("Day '" + day + "' is already " +
					"included in the timetable");
		}
		days.add(day);
	}

	public boolean removeDay(Timetable day) {
		if (day == null) {
			throw new IllegalArgumentException("Parameter 'day' must not " +
					"be null");
		}
		return days.remove(day);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Timetable)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Timetable other = (Timetable) obj;
		if (!this.name.equals(other.name) ||
				this.softConstraintPenalty !=
						other.softConstraintPenalty) {
			return false;
		}

		if (this.days.size() != other.days.size()) {
			return false;
		} else if (this.days != other.days) {
			if (!(this.days.containsAll(other.days))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
