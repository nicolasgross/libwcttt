package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "teachers"})
public class Chair {

	private String id;
	private String name;
	private List<Teacher> teachers = new LinkedList<>();

	public Chair() {
		this.id = "";
		this.name = "";
	}

	public Chair(String id, String name) {
		if (id == null || name == null) {
			throw new IllegalArgumentException("Parameters must not be null");
		}
		this.id = id;
		this.name = "";
		this.name = name;
	}

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

	/**
	 * Getter for the teachers of a chair. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the teachers of the chair.
	 */
	@XmlElementWrapper
	@XmlElement(name = "teacher", required = true)
	public List<Teacher> getTeachers() {
		return teachers;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Chair)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Chair other = (Chair) obj;
		if (!(this.id.equals(other.id) && this.name.equals(other.name))) {
			return false;
		}

		if (this.teachers.size() != other.teachers.size()) {
			return false;
		} else if (this.teachers != other.teachers) {
			if (!(this.teachers.containsAll(other.teachers))) {
				return false;
			}
		}

		return true;
	}

}
