package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "softConstraintViolations", "days"})
public class Timetable {

	private int id;
	private int name;
	private double softConstraintViolations;
	private List<TimetableDay> days = new ArrayList<>();

	@XmlAttribute(required = true)
	@XmlID
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	@XmlIDREF
	public List<TimetableDay> getDays() {
		return days;
	}

	public void setDays(List<TimetableDay> days) {
		this.days = days;
	}

}
