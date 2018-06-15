package de.nicolasgross.wcttt.intern.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"name", "softConstraintViolations", "days"})
public class Timetable {

	private int name;
	private double softConstraintViolations;
	private List<TimetableDay> days = new ArrayList<>();

	@XmlAttribute(required = true)
	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	@XmlAttribute(required = true)
	public double getSoftConstraintViolations() {
		return softConstraintViolations;
	}

	public void setSoftConstraintViolations(double softConstraintViolations) {
		this.softConstraintViolations = softConstraintViolations;
	}

	@XmlElement(required = true)
	public List<TimetableDay> getDays() {
		return days;
	}

	public void setDays(List<TimetableDay> days) {
		this.days = days;
	}

}
