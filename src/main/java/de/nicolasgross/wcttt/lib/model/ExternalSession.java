package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(propOrder = {"id", "name", "teacher", "doubleSession",
		"preAssignmentBinding", "room"})
public class ExternalSession extends Session {

	private ExternalRoom room;

	public ExternalSession() {
		super();
		setRoom(new ExternalRoom());
	}

	public ExternalSession(String id, String name, Teacher teacher,
	                       Course course, boolean doubleSession,
	                       Period preAssignment, ExternalRoom room) {
		super(id, name, teacher, course, doubleSession, preAssignment);
		setRoom(room);
	}

	@XmlElement(required = true)
	@XmlIDREF
	public ExternalRoom getRoom() {
		return room;
	}

	public void setRoom(ExternalRoom room) {
		if (room == null) {
			throw new IllegalArgumentException("Parameter 'room' must not be " +
					"null");
		}
		this.room = room;
	}

	@Override
	public void setPreAssignment(Period preAssignment) {
		if (preAssignment == null) {
			throw new IllegalArgumentException("Parameter 'preAssignment' " +
					"must not be null");
		}
		super.setPreAssignment(preAssignment);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		ExternalSession session = (ExternalSession) o;
		return Objects.equals(room, session.room);
	}

	@Override
	public int hashCode() {
		return Objects.hash(room);
	}
}
