package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

import static de.nicolasgross.wcttt.model.ValidationHelper.validateMinNumOfDays;

@XmlType(propOrder = {"id", "name", "abbreviation", "chair", "minNumberOfDays",
		"lectures", "practicals"})
public class Course {

	private String id;
	private String name;
	private String abbreviation;
	private Chair chair;
	private int minNumberOfDays;
	private List<Session> lectures = new LinkedList<>();
	private List<Session> practicals = new LinkedList<>();

	public Course() {
		this.id = "";
		this.name = "";
		this.abbreviation = "";
		this.chair = new Chair();
		this.minNumberOfDays = 1;
	}

	public Course(String id, String name, String abbreviation, Chair chair,
	              int minNumberOfDays) throws WctttModelException {
		if (id == null || name == null || abbreviation == null ||
				chair == null) {
			throw new IllegalArgumentException("Parameters 'id', 'name', " +
					"'abbreviation' and 'chair' must not be null");
		}
		validateMinNumOfDays(minNumberOfDays);
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.chair = chair;
		this.minNumberOfDays = minNumberOfDays;
	}

	@XmlAttribute(required = true)
	@XmlID
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Parameters 'id' must not be " +
					"null");
		}
		this.id = id;
	}

	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not " +
					"be null");
		}
		this.name = name;
	}

	@XmlAttribute(required = true)
	public String getAbbreviation() {
		if (abbreviation == null) {
			throw new IllegalArgumentException("Parameter 'abbreviation' must" +
					" not be null");
		}
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public Chair getChair() {
		return chair;
	}

	public void setChair(Chair chair) {
		if (chair == null) {
			throw new IllegalArgumentException("Parameter 'chair' must not " +
					"be null");
		}
		this.chair = chair;
	}

	@XmlAttribute(required = true)
	public int getMinNumberOfDays() {
		return minNumberOfDays;
	}

	public void setMinNumberOfDays(int minNumberOfDays) throws
			WctttModelException {
		validateMinNumOfDays(minNumberOfDays);
		this.minNumberOfDays = minNumberOfDays;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "lecture")
	public List<Session> getLectures() {
		return lectures;
	}

	@XmlElementWrapper(required = true)
	@XmlElement(name = "practical")
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
