package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@XmlType(propOrder = {"id", "name", "courses"})
public class Curriculum {

	private String id;
	private String name;
	private List<Course> courses = new LinkedList<>();

	public Curriculum() {
		this.id = "";
		this.name = "";
	}

	public Curriculum(String id, String name) {
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

	@XmlElementWrapper(required = true)
	@XmlElement(name = "course")
	@XmlIDREF
	public List<Course> getCourses() {
		return courses;
	}

	private boolean courseIdExists(String id) {
		for (Course course : courses) {
			if (id.equals(course.getId())) {
				return true;
			}
		}
		return false;
	}

	public void addCourse(Course course) throws WctttModelException {
		if (course == null) {
			throw new IllegalArgumentException("Parameter 'course' must not " +
					"be null");
		} else if (courseIdExists(course.getId())) {
			throw new WctttModelException("Id '" + course.getId() + "' is " +
					"already assigned to another course of the curriculum");
		}
		courses.add(course);
	}

	public boolean removeCourse(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("Parameter 'course' must not " +
					"be null");
		}
		return courses.remove(course);
	}

	public void updateCourseId(Course course, String id) throws
			WctttModelException {
		if (course == null || id == null) {
			throw new IllegalArgumentException("Parameters 'course' and 'id' " +
					"must not be null");
		} else if (!courseIdExists(course.getId())) {
			throw new WctttModelException("Course '" + id + "' is not " +
					"assigned to the curriculum");
		} else if (courseIdExists(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to another course of the curriculum");
		}
		course.setId(id);
	}

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
