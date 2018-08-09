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
	private boolean doubleSession;
	private Period preAssignmentStart;
	private Period preAssignmentEnd;

	public Session() {
		setId("session");
		setName("");
		setTeacher(new Teacher());
		setDoubleSession(false);
		if (this instanceof InternalSession) {
			setPreAssignmentStart(null);
			setPreAssignmentEnd(null);
		} else {
			setPreAssignmentStart(new Period());
			setPreAssignmentStart(new Period());
		}
	}

	public Session(String id, String name, Teacher teacher,
	                       boolean doubleSession, Period preAssignmentStart,
	                       Period preAssignmentEnd) {
		setId(id);
		setName(name);
		setTeacher(teacher);
		setDoubleSession(doubleSession);
		setPreAssignmentStart(preAssignmentStart);
		setPreAssignmentEnd(preAssignmentEnd);
	}

	@XmlElement(required = false)
	protected Period getPreAssignmentStartBinding() {
		return preAssignmentStart;
	}

	private void setPreAssignmentStartBinding(Period preAssignmentStart) {
		setPreAssignmentStart(preAssignmentStart);
	}

	@XmlElement(required = false)
	protected Period getPreAssignmentEndBinding() { // TODO private test
		return preAssignmentEnd;
	}

	private void setPreAssignmentEndBinding(Period preAssignmentEnd) {
		setPreAssignmentEnd(preAssignmentEnd);
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

	@XmlAttribute(required = true)
	public boolean isDoubleSession() {
		return doubleSession;
	}

	public void setDoubleSession(boolean doubleSession) {
		this.doubleSession = doubleSession;
	}

	public Optional<Period> getPreAssignmentStart() {
		return Optional.ofNullable(preAssignmentStart);
	}

	public void setPreAssignmentStart(Period preAssignmentStart) {
		this.preAssignmentStart = preAssignmentStart;
	}

	public Optional<Period> getPreAssignmentEnd() {
		return Optional.ofNullable(preAssignmentEnd);
	}

	public void setPreAssignmentEnd(Period preAssignmentEnd) {
		this.preAssignmentEnd = preAssignmentEnd;
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
				this.doubleSession != other.doubleSession ||
				!Objects.equals(this.preAssignmentStart,
						other.preAssignmentStart) ||
				!Objects.equals(this.preAssignmentEnd,
						other.preAssignmentEnd)) {
			return false;
		}

		return true;
	}
}
