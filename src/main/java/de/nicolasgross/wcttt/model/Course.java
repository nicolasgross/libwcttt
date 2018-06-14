package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = {"id", "name", "chair", "minNumberOfDays", "lectures", "practicals"})
public class Course {

	private int id;
	private String name;
	private Chair chair;
	private int minNumberOfDays;
	private List<Session> lectures;
	private List<Session> practicals;

	@XmlAttribute(required = true) //TODO required?? test
	@XmlID
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(required = false)
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

	@XmlIDREF
	public List<Session> getLectures() {
		return lectures;
	}

	public void setLectures(List<Session> lectures) {
		this.lectures = lectures;
	}

	@XmlIDREF
	public List<Session> getPracticals() {
		return practicals;
	}

	public void setPracticals(List<Session> practicals) {
		this.practicals = practicals;
	}

}
