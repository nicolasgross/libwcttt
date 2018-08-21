package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

/**
 * Represents the soft constraint weightings.
 */
@XmlType(propOrder = {"s1", "s2", "s3", "s4", "s5", "s6", "s7"})
public class ConstraintWeightings {

	private double s1;
	private double s2;
	private double s3;
	private double s4;
	private double s5;
	private double s6;
	private double s7;

	public ConstraintWeightings() {
		this.s1 = 1.0;
		this.s2 = 1.0;
		this.s3 = 1.0;
		this.s4 = 1.0;
		this.s5 = 1.0;
		this.s6 = 1.0;
		this.s7 = 1.0;
	}

	public ConstraintWeightings(double s1, double s2, double s3, double s4,
	                            double s5, double s6, double s7)
			throws WctttModelException {
		ValidationHelper.validateConstraintWeighting(s1);
		ValidationHelper.validateConstraintWeighting(s2);
		ValidationHelper.validateConstraintWeighting(s3);
		ValidationHelper.validateConstraintWeighting(s4);
		ValidationHelper.validateConstraintWeighting(s5);
		ValidationHelper.validateConstraintWeighting(s6);
		ValidationHelper.validateConstraintWeighting(s7);
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.s5 = s5;
		this.s6 = s6;
		this.s7 = s7;
	}

	@XmlAttribute(required = true)
	public double getS1() {
		return s1;
	}

	public void setS1(double s1) throws WctttModelException {
		ValidationHelper.validateConstraintWeighting(s1);
		this.s1 = s1;
	}

	@XmlAttribute(required = true)
	public double getS2() {
		return s2;
	}

	public void setS2(double s2) throws WctttModelException {
		ValidationHelper.validateConstraintWeighting(s2);
		this.s2 = s2;
	}

	@XmlAttribute(required = true)
	public double getS3() {
		return s3;
	}

	public void setS3(double s3) throws WctttModelException {
		ValidationHelper.validateConstraintWeighting(s3);
		this.s3 = s3;
	}

	@XmlAttribute(required = true)
	public double getS4() {
		return s4;
	}

	public void setS4(double s4) throws WctttModelException {
		ValidationHelper.validateConstraintWeighting(s4);
		this.s4 = s4;
	}

	@XmlAttribute(required = true)
	public double getS5() {
		return s5;
	}

	public void setS5(double s5) throws WctttModelException {
		ValidationHelper.validateConstraintWeighting(s5);
		this.s5 = s5;
	}

	@XmlAttribute(required = true)
	public double getS6() {
		return s6;
	}

	public void setS6(double s6) throws WctttModelException {
		ValidationHelper.validateConstraintWeighting(s6);
		this.s6 = s6;
	}

	@XmlAttribute(required = true)
	public double getS7() {
		return s7;
	}

	public void setS7(double s7) throws WctttModelException {
		ValidationHelper.validateConstraintWeighting(s7);
		this.s7 = s7;
	}

	public double getWeighting(ConstraintType type) {
		switch (type) {
			case s1:
				return getS1();
			case s2:
				return getS2();
			case s3:
				return getS3();
			case s4:
				return getS4();
			case s5:
				return getS5();
			case s6:
				return getS6();
			case s7:
				return getS7();
			default:
				return -1.0;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ConstraintWeightings that = (ConstraintWeightings) o;
		return Double.compare(that.s1, s1) == 0 &&
				Double.compare(that.s2, s2) == 0 &&
				Double.compare(that.s3, s3) == 0 &&
				Double.compare(that.s4, s4) == 0 &&
				Double.compare(that.s5, s5) == 0 &&
				Double.compare(that.s6, s6) == 0 &&
				Double.compare(that.s7, s7) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(s1, s2, s3, s4, s5, s6, s7);
	}
}
