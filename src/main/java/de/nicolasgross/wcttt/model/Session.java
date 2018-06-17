package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

@XmlType(propOrder = {"id", "name", "course", "teacher", "students",
		"external", "doubleSession", "preAssignmentBinding",
		"roomRequirements"})
public class Session {

	private String id = "";
	private String name = "";
	private Course course = new Course();
	private Teacher teacher = new Teacher();
	private int students;
	private boolean external;
	private boolean doubleSession;
	private RoomFeatures roomRequirements = new RoomFeatures();
	private Period preAssignment = new Period();

	@XmlElement(required = false)
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
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
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

	public void setPreAssignment(Period preAssignment) {
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
				this.course.equals(other.course) ||
				this.teacher.equals(other.teacher) ||
				this.students == other.students ||
				this.external == other.external ||
				this.doubleSession == other.doubleSession ||
				this.roomRequirements.equals(other.roomRequirements) ||
				Objects.equals(this.preAssignment, other.preAssignment)) {
			return false;
		}

		return super.equals(obj);
	}

}
