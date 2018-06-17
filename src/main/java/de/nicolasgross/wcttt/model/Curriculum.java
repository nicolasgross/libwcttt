package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "courses"})
public class Curriculum {

	private String id = "";
	private String name = "";
	private List<Course> courses = new LinkedList<>();

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

	@XmlElementWrapper(required = true)
	@XmlElement(name = "course")
	@XmlIDREF
	public List<Course> getCourses() {
		return courses;
	}

	// TODO add, delete, update courses

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Curriculum)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Curriculum other = (Curriculum) obj;
		if (!this.id.equals(other.id) || !this.name.equals(other.name)) {
			return false;
		}

		if (this.courses.size() != other.courses.size()) {
			return false;
		} else if (this.courses != other.courses) {
			if (!(this.courses.containsAll(other.courses))) {
				return false;
			}
		}

		return true;
	}

}
