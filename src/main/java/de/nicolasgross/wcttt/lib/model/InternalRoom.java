package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.Optional;

import static de.nicolasgross.wcttt.lib.model.ValidationHelper.validateRoomCapacity;

@XmlType(propOrder = {"id", "name", "capacity", "features", "holderBinding"})
public class InternalRoom extends Room {

	private int capacity;
	private RoomFeatures features;
	private Chair holder;

	/**
	 * Creates a new room with an empty id and name, capacity of 1, no holder
	 * and default features (see {@link RoomFeatures#RoomFeatures()}).
	 */
	public InternalRoom() {
		super();
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
	public InternalRoom(String id, String name, int capacity, Chair holder,
	                    RoomFeatures features) throws WctttModelException {
		super(id, name);
		validateRoomCapacity(capacity);
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		InternalRoom that = (InternalRoom) o;
		return capacity == that.capacity &&
				Objects.equals(features, that.features) &&
				Objects.equals(holder, that.holder);
	}

	@Override
	public int hashCode() {
		return Objects.hash(capacity, features, holder);
	}
}
