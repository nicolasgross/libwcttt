package de.nicolasgross.wcttt.lib.util;

public class SessionRoomConflict {

	private int capacityDeviation = 0;
	private boolean fulfillsFeatures = false;

	public int getCapacityDeviation() {
		return capacityDeviation;
	}

	public void setCapacityDeviation(int capacityDeviation) {
		this.capacityDeviation = capacityDeviation;
	}

	public boolean fullfillsFeatures() {
		return fulfillsFeatures;
	}

	public void setFullfillsFeatures(boolean fullfillsFeatures) {
		this.fulfillsFeatures = fullfillsFeatures;
	}
}
