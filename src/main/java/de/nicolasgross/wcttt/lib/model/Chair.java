package de.nicolasgross.wcttt.lib.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;

@XmlType(propOrder = {"id", "name", "abbreviation", "teachers"})
public class Chair {

	private String id;
	private String name;
	private String abbreviation;
	private final ObservableList<Teacher> teachers =
			FXCollections.observableList(new LinkedList<>());

	public Chair() {
		this.id = "chair";
		this.name = "chair";
		this.abbreviation = "";
	}

	public Chair(String id, String name, String abbreviation) {
		if (id == null || name == null || abbreviation == null) {
			throw new IllegalArgumentException("Parameters 'id', 'name' and " +
					"'abbreviation' must not be null");
		}
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
	}

	@XmlAttribute(required = true)
	@XmlID
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Parameter 'id' must not be " +
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
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	@XmlAttribute(required = true)
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		if (abbreviation == null) {
			throw new IllegalArgumentException("Parameter 'abbreviation' must" +
					" not be null");
		}
		this.abbreviation = abbreviation;
	}

	/**
	 * Getter for the teachers of a chair. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the teachers of the chair.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "teacher")
	public ObservableList<Teacher> getTeachers() {
		return teachers;
	}

	private boolean teacherIdExists(String id) {
		for (Teacher existingTeacher : teachers) {
			if (id.equals(existingTeacher.getId())) {
				return true;
			}
		}
		return false;
	}

	public void addTeacher(Teacher teacher) throws WctttModelException {
		if (teacher == null) {
			throw new IllegalArgumentException("Parameter 'teacher' must not " +
					"be null");
		} else if (teacherIdExists(teacher.getId())) {
			throw new WctttModelException("Id '" + teacher.getId() + "' is " +
					"already assigned to another teacher at the chair");
		}
		teachers.add(teacher);
	}

	public boolean removeTeacher(Teacher teacher) {
		if (teacher == null) {
			throw new IllegalArgumentException("Parameter 'teacher' must not " +
					"be null");
		}
		return teachers.remove(teacher);
	}

	public void updateTeacherId(Teacher teacher, String id) throws
			WctttModelException {
		if (teacher == null || id == null) {
			throw new IllegalArgumentException("Parameters 'teacher' and 'id'" +
					" must not be null");
		} else if (!teacherIdExists(teacher.getId())) {
			throw new WctttModelException("Teacher '" + id + "' is not " +
					"assigned to the chair");
		} else if (teacherIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to another teacher at the chair");
		}
		teacher.setId(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Chair)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Chair other = (Chair) obj;
		if (!(this.id.equals(other.id) && this.name.equals(other.name) &&
				this.abbreviation.equals(other.abbreviation))) {
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

	@Override
	public String toString() {
		return abbreviation + " - " + name;
	}

}
