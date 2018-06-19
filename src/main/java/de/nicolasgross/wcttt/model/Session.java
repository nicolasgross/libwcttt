package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

import static de.nicolasgross.wcttt.model.ValidationHelper.validateStudents;

@XmlType(propOrder = {"id", "name", "teacher", "students",
		"external", "doubleSession", "preAssignmentBinding",
		"roomRequirements"})
public class Session {

	private String id;
	private String name;
	private Teacher teacher;
	private int students;
	private boolean external;
	private boolean doubleSession;
	private RoomFeatures roomRequirements;
	private Period preAssignment;

	public Session() {
		this.id = "session";
		this.name = "";
		this.teacher = new Teacher();
		this.students = 1;
		this.external = false;
		this.doubleSession = false;
		this.roomRequirements = new RoomFeatures();
	}

	public Session(String id, String name, Teacher teacher, int students,
	               boolean external, boolean doubleSession,
	               RoomFeatures roomRequirements, Period preAssignment) throws
			WctttModelException {
		if (id == null || name == null || teacher == null ||
				roomRequirements == null) {
			throw new IllegalArgumentException("Parameters 'id', 'name', " +
					"'teacher' and 'roomRequirements' must not be null");
		}
		validateStudents(students);
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.students = students;
		this.external = external;
		this.doubleSession = doubleSession;
		this.roomRequirements = roomRequirements;
		this.preAssignment = preAssignment;
	}

	@XmlElement(required = false)
	private Period getPreAssignmentBinding() {
		return preAssignment;
	}

	private void setPreAssignmentBinding(Period preAssignment) throws
			WctttModelException {
		setPreAssignment(preAssignment);
	}

	@XmlAttribute(required = true)
	@XmlID
	public String getId() {
		return id;
	}

	public void setId(String id) throws WctttModelException {
		if (id == null) {
			throw new IllegalArgumentException("Parameter 'id' must not be " +
					"null");
		} else if (teacher.getId().equals(id)) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to the teacher of this session");
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

	public void setTeacher(Teacher teacher) throws WctttModelException {
		if (teacher == null) {
			throw new IllegalArgumentException("Parameter 'teacher' must not " +
					"be null");
		} else if (this.id.equals(teacher.getId())) {
			throw new WctttModelException("Id '" + teacher.getId() + "' is " +
					"already assigned to this session");
		}
		this.teacher = teacher;
	}

	@XmlAttribute(required = true)
	public int getStudents() {
		return students;
	}

	public void setStudents(int students) {
		this.students = students;
	}

	@XmlAttribute(required = true)
	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
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

	public void setPreAssignment(Period preAssignment) throws
			WctttModelException {
		this.preAssignment = preAssignment;
	}

	@XmlElement(required = true)
	public RoomFeatures getRoomRequirements() {
		return roomRequirements;
	}

	public void setRoomRequirements(RoomFeatures roomRequirements) {
		if (roomRequirements == null) {
			throw new IllegalArgumentException("Parameter 'roomRequirements' " +
					"must not be null");
		}
		this.roomRequirements = roomRequirements;
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
				this.students != other.students ||
				this.external != other.external ||
				this.doubleSession != other.doubleSession ||
				!this.roomRequirements.equals(other.roomRequirements) ||
				!Objects.equals(this.preAssignment, other.preAssignment)) {
			return false;
		}

		return true;
	}

	// TODO toString

}
