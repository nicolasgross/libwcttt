package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"id", "name"})
public class ExternalRoom extends Room {

	public ExternalRoom() {
		super();
	}

	public ExternalRoom(String id, String name) {
		super(id, name);
	}
}
