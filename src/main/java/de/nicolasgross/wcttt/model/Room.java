package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.Optional;

/**
 * Represents a room.
 */
@XmlType(propOrder = {"id", "name", "capacity", "holderBinding", "features"})
public class Room {

	private String id;
	private String name;
	private int capacity;
	private Chair holder;
	private RoomFeatures features;

	/**
	 * Creates a room with an empty name, capacity of 1, no holder and default
	 * features (see {@link RoomFeatures#RoomFeatures()}).
	 */
	public Room() {
		this.id = "";
		this.name = "";
		this.capacity = 1;
		this.holder = null;
		this.features = new RoomFeatures();
	}

	/**
	 * Creates a new room.
	 *
	 * @param id       the id of the room, must not be null.
	 * @param name     the name of the room, must not be null.
	 * @param capacity the capacity of the room, must be larger 0.
	 * @param holder   the holder of the room, null is allowed and indicates
	 *                    that the room has no holder.
	 * @param features the features of the room, must not be null.
	 */
	public Room(String id, String name, int capacity, Chair holder,
	            RoomFeatures features) {
		if (id == null || name == null || features == null) {
			throw new IllegalArgumentException("Parameters 'id', 'name' and " +
					"'features' must not be null");
		} else if (capacity < 1) {
			throw new IllegalArgumentException("Parameter 'capacity' must be" +
					"larger 0");
		}
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.holder = holder;
		this.features = features;
	}

	/**
	 * Getter for the holder of a room. Only used for JAXB bindings, such
	 * that another getter can return Optional.
	 *
	 * @return the holder, null if the room has no holder.
	 */
	@XmlElement(name = "holder", required = false)
	@XmlIDREF
	private Chair getHolderBinding() {
		return holder;
	}

	/**
	 * Setter for the holder of a room. Only used for JAXB bindings, such
	 * that another getter can return Optional.
	 *
	 * @param holder the new holder of the room, null is allowed and
	 *                  indicates that the room has no holder.
	 */
	private void setHolderBinding(Chair holder) {
		setHolder(holder);
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

	/**
	 * Getter for the capacity of a room.
	 *
	 * @return the capacity of the room.
	 */
	@XmlAttribute(required = true)
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Setter for the capacity of a room.
	 *
	 * @param capacity the new capacity of the room, must be larger 0.
	 */
	public void setCapacity(int capacity) {
		if (capacity < 1) {
			throw new IllegalArgumentException("Parameter 'capacity' must be" +
					"larger 0");
		}
		this.capacity = capacity;
	}

	/**
	 * Getter for the holder of a room. Do not manipulate the returned
	 * reference.
	 *
	 * @return an Optional which contains the holder of the room or is empty
	 * if there is no holder.
	 */
	public Optional<Chair> getHolder() {
		return Optional.ofNullable(holder);
	}

	/**
	 * Setter for the holder of a room.
	 *
	 * @param holder the new holder of the room, can be null if there is no
	 *               holder.
	 */
	public void setHolder(Chair holder) {
		this.holder = holder;
	}

	/**
	 * Getter for the features of a room. Do not manipulate the returned
	 * reference.
	 *
	 * @return the features of te room.
	 */
	@XmlElement(required = true)
	public RoomFeatures getFeatures() {
		return features;
	}

	/**
	 * Setter for the features of a room.
	 *
	 * @param features the new features of the room, must not be null.
	 */
	public void setFeatures(RoomFeatures features) {
		if (features == null) {
			throw new IllegalArgumentException("Parameter 'features' must not" +
					" be null");
		}
		this.features = features;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Room)) {
			return false;
		} else if (obj == this) {
			return true;
		}
		Room other = (Room) obj;
		if (!(this.id.equals(other.id) && this.name.equals(other.name) &&
				this.capacity == other.capacity &&
				this.features.equals(other.features))) {
			return false;
		}

		if (!((this.holder == null && other.holder == null) ||
				(this.holder != null && other.holder != null &&
				this.holder.equals(other.holder)))) {
			return false;
		}

		return true;
	}

}