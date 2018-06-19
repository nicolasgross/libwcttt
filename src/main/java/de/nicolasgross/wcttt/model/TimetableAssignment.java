package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

@XmlType(propOrder = {"session", "roomBinding"})
public class TimetableAssignment {

	private Session session;
	private Room room;

	public TimetableAssignment() {
		this.session = new Session();
		this.room = new Room();
	}

	public TimetableAssignment(Session session, Room room) throws
			WctttModelException {
		if (session == null) {
			throw new IllegalArgumentException("Parameter 'session' must not " +
					"be null");
		} else if (room == null && !session.isExternal()) {
			throw new WctttModelException("Parameter 'room' can only be null " +
					"if 'session' is external");
		}
		this.session = session;
		this.room = room;
	}

	@XmlElement(required = false)
	@XmlIDREF
	private Room getRoomBinding() {
		return room;
	}

	private void setRoomBinding(Room room) throws WctttModelException {
		setRoom(room);
	}

	@XmlElement(required = true)
	@XmlIDREF
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) throws WctttModelException {
		if (session == null) {
			throw new IllegalArgumentException("Parameter 'session' must not " +
					"be null");
		} else if (room.getId().equals(session.getId())) {
			throw new WctttModelException("Id '" + session.getId() + "' is " +
					"already assigned to the room of this assignment");
		}
		this.session = session;
	}

	public Optional<Room> getRoom() {
		return Optional.ofNullable(room);
	}

	public void setRoom(Room room) throws WctttModelException {
		if (room == null && !session.isExternal()) {
			throw new WctttModelException("Parameter 'room' can only be null " +
					"if 'session' is external");
		} else if (room == null && session.getId().equals(room.getId())) {
			throw new WctttModelException("Id '" + room.getId() + "' is " +
					"already assigned to the session of this assignment");
		}
		this.room = room;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TimetableAssignment)) {
			return false;
		} else if (obj == this) {
			return true;
		}

		TimetableAssignment other = (TimetableAssignment) obj;
		if (!this.session.equals(other.session) ||
				!Objects.equals(this.room, other.room)) {
			return false;
		}

		return true;
	}

	// TODO toString

}
