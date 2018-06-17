package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "chair", "minNumberOfDays", "lectures",
		"practicals"})
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

	@XmlElementWrapper(required = true)
	@XmlElement(name = "lecture")
	@XmlIDREF
	public List<Session> getLectures() {
		return lectures;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "practical")
	@XmlIDREF
	public List<Session> getPracticals() {
		return practicals;
	}

	// TODO add, delete, update lecture/practical

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Course)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Course other = (Course) obj;
		if (!this.id.equals(other.id) || !this.name.equals(other.name) ||
				!this.chair.equals(other.chair) ||
				this.minNumberOfDays != other.minNumberOfDays) {
			return false;
		}

		if (this.lectures.size() != other.lectures.size()) {
			return false;
		} else if (this.lectures != other.lectures) {
			if (!(this.lectures.containsAll(other.lectures))) {
				return false;
			}
		}

		if (this.practicals.size() != other.practicals.size()) {
			return false;
		} else if (this.practicals != other.practicals) {
			if (!(this.practicals.containsAll(other.practicals))) {
				return false;
			}
		}

		return true;
	}

}
