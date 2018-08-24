package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(propOrder = {"session", "room"})
public class TimetableAssignment {

	private Session session;
	private Room room;

	public TimetableAssignment() {
		this.session = new InternalSession();
		this.room = new InternalRoom();
	}

	public TimetableAssignment(Session session, Room room) {
		setSession(session);
		setRoom(room);
	}

	@XmlElement(required = true)
	@XmlIDREF
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		if (session == null) {
			throw new IllegalArgumentException("Parameter 'session' must not " +
					"be null");
		}
		this.session = session;
	}

	@XmlElement(required = true)
	@XmlIDREF
	public Room getRoom() {
		return room;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TimetableAssignment that = (TimetableAssignment) o;
		return Objects.equals(session, that.session) &&
				Objects.equals(room, that.room);
	}

	@Override
	public int hashCode() {
		return Objects.hash(session, room);
	}

	public void setRoom(Room room) {
		if (room == null) {
			throw new IllegalArgumentException("Parameter 'room' must not be " +
					"null");
		}
		this.room = room;
	}

	@Override
	public String toString() {
		return session + " to " + room;
	}
}
