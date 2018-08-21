package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(propOrder = {"id", "name", "teacher", "doubleSession",
		"preAssignmentBinding", "students", "roomRequirements"})
public class InternalSession extends Session {

	private int students;
	private RoomFeatures roomRequirements;

	public InternalSession() {
		super();
		this.students = 1;
		this.roomRequirements = new RoomFeatures();
	}

	public InternalSession(String id, String name, Teacher teacher,
	                       Course course, boolean doubleSession,
	                       Period preAssignment, int students,
	                       RoomFeatures roomRequirements)
			throws WctttModelException {
		super(id, name, teacher, course, doubleSession, preAssignment);
		setStudents(students);
		setRoomRequirements(roomRequirements);
	}

	@XmlAttribute(required = true)
	public int getStudents() {
		return students;
	}

	public void setStudents(int students) throws WctttModelException {
		ValidationHelper.validateStudents(students);
		this.students = students;
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		InternalSession that = (InternalSession) o;
		return students == that.students &&
				Objects.equals(roomRequirements, that.roomRequirements);
	}

	@Override
	public int hashCode() {
		return Objects.hash(students, roomRequirements);
	}
}
