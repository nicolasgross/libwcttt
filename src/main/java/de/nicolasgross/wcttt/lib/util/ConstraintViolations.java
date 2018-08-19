package de.nicolasgross.wcttt.lib.util;

import de.nicolasgross.wcttt.lib.model.ConstraintType;

import java.util.List;

public class ConstraintViolations {

	private List<ConstraintType> violations;
	private boolean hardConstraintViolated = false;
	private boolean softConstraintViolated = false;

	public List<ConstraintType> getViolations() {
		return violations;
	}

	public boolean isHardConstraintViolated() {
		return hardConstraintViolated;
	}

	public void setHardConstraintViolated(boolean hardConstraintViolated) {
		this.hardConstraintViolated = hardConstraintViolated;
	}

	public boolean isSoftConstraintViolated() {
		return softConstraintViolated;
	}

	public void setSoftConstraintViolated(boolean softConstraintViolated) {
		this.softConstraintViolated = softConstraintViolated;
	}
}
