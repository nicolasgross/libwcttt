/*
 * WCT³ (WIAI Course Timetabling Tool) is a software that strives to automate
 * the timetabling process at the WIAI faculty of the University of Bamberg.
 *
 * libwcttt comprises the data model, a binder (parser + emitter) to store the
 * data as XML files as well as functionality to calculate conflicts and their
 * violations.
 *
 * Copyright (C) 2018 Nicolas Gross
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

import static de.nicolasgross.wcttt.lib.model.ValidationHelper.validateRoomCapacity;

@XmlType(propOrder = {"id", "name", "capacity", "features"})
public class InternalRoom extends Room {

	private int capacity;
	private RoomFeatures features;

	/**
	 * Creates a new room with an empty id and name, capacity of 1, no holder
	 * and default features (see {@link RoomFeatures#RoomFeatures()}).
	 */
	public InternalRoom() {
		super();
		this.capacity = ValidationHelper.ROOM_CAPACITY_MIN;
		this.features = new RoomFeatures();
	}

	/**
	 * Creates a new room.
	 *
	 * @param id       the id of the room, must not be null.
	 * @param name     the name of the room, must not be null.
	 * @param capacity the capacity of the room, must be > 0.
	 * @param features the features of the room, must not be null.
	 * @throws WctttModelException if parameters do not adhere to their
	 * respective value ranges.
	 */
	public InternalRoom(String id, String name, int capacity,
	                    RoomFeatures features) throws WctttModelException {
		super(id, name);
		validateRoomCapacity(capacity);
		this.capacity = capacity;
		this.features = features;
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
				Objects.equals(features, that.features);
	}

	@Override
	public int hashCode() {
		return Objects.hash(capacity, features);
	}
}
