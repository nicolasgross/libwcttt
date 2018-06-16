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

	@XmlElement(required = false)
	@XmlIDREF
	public Room getRoom() { // TODO optional
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
