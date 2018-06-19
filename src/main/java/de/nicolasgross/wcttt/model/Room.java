package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

import static de.nicolasgross.wcttt.model.ValidationHelper.validateRoomCapacity;

/**
 * Represents a room.
 */
@XmlType(propOrder = {"id", "name", "capacity", "features", "holderBinding"})
public class Room {

	private String id;
	private String name;
	private int capacity;
	private RoomFeatures features;
	private Chair holder;

	/**
	 * Creates a new room with id 'room', an empty name, capacity of 1, no
	 * holder and default features (see {@link RoomFeatures#RoomFeatures()}).
	 */
	public Room() {
		this.id = "room";
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
	 * @param capacity the capacity of the room, must be > 0.
	 * @param holder   the holder of the room, null is allowed and indicates
	 *                    that the room has no holder.
	 * @param features the features of the room, must not be null.
	 * @throws WctttModelException if parameters do not adhere to their
	 * respective value ranges.
	 */
	public Room(String id, String name, int capacity, Chair holder,
	            RoomFeatures features) throws WctttModelException {
		if (id == null || name == null || features == null) {
			throw new IllegalArgumentException("Parameters 'id', 'name' and " +
					"'features' must not be null");
		}
		validateRoomCapacity(capacity);
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.holder = holder;
		this.features = features;
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
	 * @throws WctttModelException if the new id is the same as the id of the
	 * holder of this room.
	 */
	public void setId(String id) throws WctttModelException {
		if (id == null) {
			throw new IllegalArgumentException("Parameter 'id' must not be" +
					"null");
		} else if (holder != null && id.equals(holder.getId())) {
			throw new WctttModelException("Id '" + id + "' is already " +
					"assigned to the holder of this room");
		}
		this.id = id;
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
	 * @throws WctttModelException if the new holder has the same ID as the
	 * room.
	 */
	private void setHolderBinding(Chair holder) throws WctttModelException {
		setHolder(holder);
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
	 * @param capacity the new capacity of the room, must be > 0.
	 * @throws WctttModelException if {@code capacity} is <= 0.
	 */
	public void setCapacity(int capacity) throws WctttModelException {
		validateRoomCapacity(capacity);
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
	 * @throws WctttModelException if the new holder has the same id as the
	 * room.
	 */
	public void setHolder(Chair holder) throws WctttModelException {
		if (holder != null) {
			if (this.id.equals(holder.getId())) {
				throw new WctttModelException("Id '" + holder.getId() + "' is " +
						"already assigned to this room");
			}
		}
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
		if (!this.id.equals(other.id) || !this.name.equals(other.name) ||
				this.capacity != other.capacity ||
				!this.features.equals(other.features) ||
				!Objects.equals(this.holder, other.holder)) {
			return false;
		}

		return true;
	}

	// TODO toString

}
