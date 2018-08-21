package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Objects;

/**
 * Represents a room.
 */
@XmlSeeAlso({InternalRoom.class, ExternalRoom.class})
@XmlTransient
public abstract class Room {

	private String id;
	private String name;

	/**
	 * Creates a new room with id 'room' and an empty name.
	 */
	public Room() {
		this.id = "room";
		this.name = "room";
	}

	/**
	 * Creates a new room.
	 *
	 * @param id       the id of the room, must not be null.
	 * @param name     the name of the room, must not be null.
	 */
	public Room(String id, String name) {
		setId(id);
		setName(name);
	}


	/**
	 * Getter for the id of a room. Do not manipulate the returned reference.
	 *
	 * @return the id of the room.
	 */
	@XmlAttribute(required = true)
	@XmlID
	public String getId() {
		return id;
	}

	/**
	 * Setter for the id of a room.
	 *
	 * @param id the new id of the room, must not be null.
	 */
	public void setId(String id) {
		if (id == null) {
			throw new IllegalArgumentException("Parameter 'id' must not be" +
					"null");
		}
		this.id = id;
	}

	/**
	 * Getter for the name of a room. Do not manipulate the returned
	 * reference.
	 *
	 * @return the name of the room.
	 */
	@XmlAttribute(required = true)
	public String getName() {
		return name;
	}

	/**
	 * Setter for the name of a room.
	 *
	 * @param name the new name of the room, must not be null.
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Parameter 'name' must not be " +
					"null");
		}
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Room room = (Room) o;
		return Objects.equals(id, room.id) &&
				Objects.equals(name, room.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return name;
	}
}
