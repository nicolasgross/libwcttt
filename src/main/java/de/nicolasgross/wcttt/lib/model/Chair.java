package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "teachers"})
public class Chair {

	private String id;
	private String name;
	private final List<Teacher> teachers = new LinkedList<>();

	public Chair() {
		this.id = "chair";
		this.name = "";
	}

	public Chair(String id, String name) {
		if (id == null || name == null) {
			throw new IllegalArgumentException("Parameters 'id' and 'name' " +
					"must not be null");
		}
		this.id = id;
		this.name = name;
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

	/**
	 * Getter for the teachers of a chair. Do not manipulate the returned
	 * references.
	 *
	 * @return the list of the teachers of the chair.
	 */
	@XmlElementWrapper(required = true)
	@XmlElement(name = "teacher")
	public List<Teacher> getTeachers() {
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

	// TODO toString

}