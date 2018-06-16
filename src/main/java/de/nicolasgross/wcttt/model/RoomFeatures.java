package de.nicolasgross.wcttt.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"projectors", "pcPool"})
public class RoomFeatures implements Comparable<RoomFeatures> {

	private int projectors;
	private boolean pcPool;

	public RoomFeatures() {
		this.projectors = 0;
		this.pcPool = false;
	}

	@XmlAttribute(required = true)
	public int getProjectors() {
		return projectors;
	}

	public void setProjectors(int projectors) {
		this.projectors = projectors;
	}

	@XmlAttribute(required = true)
	public boolean isPcPool() {
		return pcPool;
	}

	public void setPcPool(boolean pcPool) {
		this.pcPool = pcPool;
	}

	@Override
	public int compareTo(RoomFeatures roomFeatures) {
		// TODO
		return 0;
	}
}
