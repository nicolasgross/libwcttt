package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "chair", "minNumberOfDays", "lectures", "practicals"})
public class Course {

	private String id = "";
	private String name = "";
	private Chair chair = new Chair();
	private int minNumberOfDays;
	private List<Session> lectures = new LinkedList<>();
	private List<Session> practicals = new LinkedList<>();

	@XmlAttribute(required = true)
	@XmlID
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public Chair getChair() {
		return chair;
	}

	public void setChair(Chair chair) {
		this.chair = chair;
	}

	@XmlAttribute(required = true)
	public int getMinNumberOfDays() {
		return minNumberOfDays;
	}

	public void setMinNumberOfDays(int minNumberOfDays) {
		this.minNumberOfDays = minNumberOfDays;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public List<Session> getLectures() {
		return lectures;
	}

	public void setLectures(List<Session> lectures) {
		this.lectures = lectures;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public List<Session> getPracticals() {
		return practicals;
	}

	public void setPracticals(List<Session> practicals) {
		this.practicals = practicals;
	}

}
