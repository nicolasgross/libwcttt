package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(propOrder = {"id", "name", "teacher", "doubleSession",
		"preAssignmentStartBinding", "preAssignmentEndBinding", "room"})
public class ExternalSession extends Session {

	private ExternalRoom room;

	public ExternalSession() {
		super();
		setRoom(new ExternalRoom());
	}

	public ExternalSession(String id, String name, Teacher teacher,
	                       boolean doubleSession, Period preAssignmentStart,
	                       Period preAssignmentEnd, ExternalRoom room) {
		super(id, name, teacher, doubleSession, preAssignmentStart,
				preAssignmentEnd);
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
	public void setPreAssignmentStart(Period preAssignmentStart) {
		if (preAssignmentStart == null) {
			throw new IllegalArgumentException("Parameter " +
					"'preAssignmentStart' must not be null");
		}
		super.setPreAssignmentStart(preAssignmentStart);
	}

	@Override
	public void setPreAssignmentEnd(Period preAssignmentEnd) {
		if (preAssignmentEnd == null) {
			throw new IllegalArgumentException("Parameter 'preAssignmentEnd' " +
					"must not be null");
		}
		super.setPreAssignmentEnd(preAssignmentEnd);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ExternalSession)) {
			return false;
		} else if (obj == this) {
			return true;
		} else if (!super.equals(obj)) {
			return false;
		}

		ExternalSession other = (ExternalSession) obj;
		if (!Objects.equals(this.room, other.room)) {
			return false;
		}

		return true;
	}
}
