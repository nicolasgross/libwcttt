package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"name", "softConstraintViolations", "days"})
public class Timetable {

	private String name = "";
	private double softConstraintViolations;
	private List<TimetableDay> days = new ArrayList<>();

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(required = true)
	public double getSoftConstraintViolations() {
		return softConstraintViolations;
	}

	public void setSoftConstraintViolations(double softConstraintViolations) {
		this.softConstraintViolations = softConstraintViolations;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "timetableDay")
	public List<TimetableDay> getDays() {
		return days;
	}

	public void setDays(List<TimetableDay> days) {
		this.days = days;
	}

}
