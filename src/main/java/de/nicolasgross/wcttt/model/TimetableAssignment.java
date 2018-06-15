package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.Optional;

@XmlType(propOrder = {"session", "room"})
public class TimetableAssignment {

	private Session session = new Session();
	private Room room = new Room();

	@XmlElement(required = true)
	@XmlIDREF
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Optional<Room> getRoom() {
		return Optional.ofNullable(room);
	}

	@XmlElement(required = false)
	@XmlIDREF
	public void setRoom(Room room) {
		this.room = room;
	}

}
