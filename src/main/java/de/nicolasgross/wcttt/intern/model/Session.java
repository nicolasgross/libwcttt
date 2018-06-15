package de.nicolasgross.wcttt.intern.model;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = {"id", "name", "course", "teacher", "students", "external", "doubleSession",
		"preAssignment", "roomRequirements"})
public class Session {

	private String id;
	private String name = "";
	private Course course = new Course();
	private Teacher teacher = new Teacher();
	private int students;
	private boolean external;
	private boolean doubleSession;
	private Period preAssignment = new Period();
	private RoomFeatures roomRequirements = new RoomFeatures();

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

	@XmlElement(required = false)
	public Period getPreAssignment() { // TODO optional
		return preAssignment;
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

}
