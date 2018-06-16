package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.Optional;

@XmlType(propOrder = {"session", "roomBinding"})
public class TimetableAssignment {

	private Session session = new Session();
	private Room roomBinding = new Room();

	@XmlElement(required = false)
	@XmlIDREF
	private Room getRoomBinding() {
		return roomBinding;
	}

	private void setRoomBinding(Room room) {
		setRoom(room);
	}

	@XmlElement(required = true)
	@XmlIDREF
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Optional<Room> getRoom() {
		return Optional.ofNullable(roomBinding);
	}

	public void setRoom(Room room) {
		this.roomBinding = room;
	}

}
