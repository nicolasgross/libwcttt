package de.nicolasgross.wcttt.lib.util;

import de.nicolasgross.wcttt.lib.model.ConstraintType;

import java.util.List;

public class Conflict {

	private List<ConstraintType> softViolations;
	private List<ConstraintType> hardViolations;

	public List<ConstraintType> getSoftViolations() {
		return softViolations;
	}

	public List<ConstraintType> getHardViolations() {
		return hardViolations;
	}
}
