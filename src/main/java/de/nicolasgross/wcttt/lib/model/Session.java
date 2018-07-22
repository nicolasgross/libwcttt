package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

@XmlType(propOrder = {"id", "name", "teacher", "students",
		"doubleSession", "preAssignmentBinding", "roomRequirements", "external",
		"externalRoomName"})
public class Session {

	private String id;
	private String name;
	private Teacher teacher;
	private int students;
	private boolean doubleSession;
	private RoomFeatures roomRequirements;
	private Period preAssignment;
	private boolean external;
	private String externalRoomName;

	public Session() {
		this.id = "session";
		this.name = "";
		this.teacher = new Teacher();
		this.students = 1;
		this.doubleSession = false;
		this.roomRequirements = new RoomFeatures();
		this.external = false;
		this.externalRoomName = null;
	}

	public Session(String id, String name, Teacher teacher, int students,
	               boolean doubleSession, RoomFeatures roomRequirements,
	               Period preAssignment, boolean external,
	               String externalRoomName) throws WctttModelException {
		if (id == null || name == null || teacher == null ||
				roomRequirements == null) {
			throw new IllegalArgumentException("Parameters 'id', 'name', " +
					"'teacher' and 'roomRequirements' must not be null");
		} else if (external && externalRoomName == null) {
			throw new IllegalArgumentException("Parameters 'externalRoomName'" +
					"must not be null if 'external' is true");
		}
		ValidationHelper.validateStudents(students);
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.students = students;
		this.doubleSession = doubleSession;
		this.roomRequirements = roomRequirements;
		this.preAssignment = preAssignment;
		this.external = external;
		this.externalRoomName = externalRoomName;
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
	public int getStudents() {
		return students;
	}

	public void setStudents(int students) {
		this.students = students;
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

	@XmlAttribute(required = true)
	public boolean isExternal() {
		return external;
	}

	public void setExternal(boolean external) {
		this.external = external;
	}

	@XmlElement(required = false)
	public String getExternalRoomName() {
		return externalRoomName;
	}

	public void setExternalRoomName(String externalRoomName) {
		this.externalRoomName = externalRoomName;
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
				this.doubleSession != other.doubleSession ||
				!this.roomRequirements.equals(other.roomRequirements) ||
				!Objects.equals(this.preAssignment, other.preAssignment) ||
				this.external != other.external ||
				!Objects.equals(this.externalRoomName, other.externalRoomName)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return name;
	}

}
