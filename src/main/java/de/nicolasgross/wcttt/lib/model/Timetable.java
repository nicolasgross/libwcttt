package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"name", "violatedHardConstraints",
		"softConstraintPenalty", "days"})
public class Timetable {

	private String name;
	private int violatedHardConstraints = -1;
	private double softConstraintPenalty = -1.0;
	private final List<TimetableDay> days = new ArrayList<>();

	public Timetable() {
		this.name = "";
	}

	public Timetable(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	@XmlAttribute(required = true)
	public int getViolatedHardConstraints() {
		return violatedHardConstraints;
	}

	// JAXB only
	private void setViolatedHardConstraints(int violatedHardConstraints) {
		this.violatedHardConstraints = violatedHardConstraints;
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

	// JAXB only
	private void setSoftConstraintPenalty(double softConstraintPenalty) {
		this.softConstraintPenalty = softConstraintPenalty;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetableDay")
	public List<TimetableDay> getDays() {
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

	public void calcConstraintViolations(Semester semester) {
		// TODO after checker package
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
