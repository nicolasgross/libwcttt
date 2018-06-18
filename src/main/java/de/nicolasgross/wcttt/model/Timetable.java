package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"name", "violatedHardConstraints",
		"softConstraintsPenalty", "days"})
public class Timetable {

	private String name;
	private int violatedHardConstraints = -1;
	private double softConstraintsPenalty = -1.0;
	private List<TimetableDay> days = new ArrayList<>();

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
	public double getSoftConstraintsPenalty() {
		return softConstraintsPenalty;
	}

	// JAXB only
	private void setSoftConstraintsPenalty(double softConstraintsPenalty) {
		this.softConstraintsPenalty = softConstraintsPenalty;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetableDay")
	public List<TimetableDay> getDays() {
		return days;
	}

	// TODO add, delete, update courses

	public void calcConstraintViolations() {
		// TODO
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
				this.softConstraintsPenalty !=
						other.softConstraintsPenalty) {
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

}
