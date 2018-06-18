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
		this.id = "";
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

	@XmlElement(required = true)
	@XmlIDREF
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) throws WctttModelException {
		if (preAssignment != null) {
			for (Period unavailable : teacher.getUnavailablePeriods()) {
				if (preAssignment.equals(unavailable)) {
					throw new WctttModelException("The pre-assignment " +
							preAssignment + " of this session is within the " +
							"unavailable periods of teacher " + teacher);
				}
			}
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
		if (preAssignment != null) {
			for (Period unavailable : teacher.getUnavailablePeriods()) {
				if (preAssignment.equals(unavailable)) {
					throw new WctttModelException("Teacher " + teacher + " of" +
							" this session is not available at the " +
							"pre-assignment period " + preAssignment);
				}
			}
		}
		this.preAssignment = preAssignment;
	}

	@XmlElement(required = true)
	public RoomFeatures getRoomRequirements() {
		return roomRequirements;
	}

	public void setRoomRequirements(RoomFeatures roomRequirements) {
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

}
