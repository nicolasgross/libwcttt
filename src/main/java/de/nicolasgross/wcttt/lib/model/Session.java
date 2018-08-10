package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

@XmlSeeAlso({InternalSession.class, ExternalSession.class})
@XmlTransient
public abstract class Session {

	private String id;
	private String name;
	private Teacher teacher;
	private Course course;
	private boolean doubleSession;
	private Period preAssignment;

	public Session() {
		setId("session");
		setName("");
		setTeacher(new Teacher());
		setCourse(new Course());
		setDoubleSession(false);
		if (this instanceof InternalSession) {
			setPreAssignment(null);
		} else {
			setPreAssignment(new Period());
		}
	}

	public Session(String id, String name, Teacher teacher, Course course,
	                       boolean doubleSession, Period preAssignment) {
		setId(id);
		setName(name);
		setTeacher(teacher);
		setCourse(course);
		setDoubleSession(doubleSession);
		setPreAssignment(preAssignment);
	}

	@XmlElement(name = "preAssignment", required = false)
	private Period getPreAssignmentBinding() {
		return preAssignment;
	}

	private void setPreAssignmentBinding(Period preAssignment) {
		setPreAssignment(preAssignment);
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

	@XmlElement(required = true)
	@XmlIDREF
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		if (teacher == null) {
			throw new IllegalArgumentException("Parameter 'teacher' must not " +
					"be null");
		}
		this.teacher = teacher;
	}

	@XmlTransient
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("Parameter 'course' must not " +
					"be null");
		}
		this.course = course;
	}

	@XmlAttribute(required = true)
	public boolean isDoubleSession() {
		return doubleSession;
	}

	public void setDoubleSession(boolean doubleSession) {
		this.doubleSession = doubleSession;
	}

	public Optional<Period> getPreAssignment() {
		return Optional.ofNullable(preAssignment);
	}

	public void setPreAssignment(Period preAssignment) {
		this.preAssignment = preAssignment;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Session)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		Session other = (Session) obj;
		if (!this.id.equals(other.id) || !this.name.equals(other.name) ||
				!this.teacher.equals(other.teacher) ||
				!this.course.equals(other.course) ||
				this.doubleSession != other.doubleSession ||
				!Objects.equals(this.preAssignment, other.preAssignment)) {
			return false;
		}

		return true;
	}
}
