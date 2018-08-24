package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(propOrder = {"projectors", "pcPool", "teacherPc", "docCam"})
public class RoomFeatures implements Comparable<RoomFeatures> {

	private int projectors;
	private boolean pcPool;
	private boolean teacherPc;
	private boolean docCam;

	public RoomFeatures() {
		this.projectors = ValidationHelper.PROJECTORS_MIN;
		this.pcPool = false;
		this.teacherPc = false;
		this.docCam = false;
	}

	public RoomFeatures(int projectors, boolean pcPool, boolean teacherPc,
	                    boolean docCam) throws WctttModelException {
		ValidationHelper.validateProjectors(projectors);
		this.projectors = projectors;
		this.pcPool = pcPool;
		this.teacherPc = teacherPc;
		this.docCam = docCam;
	}

	@XmlAttribute(required = true)
	public int getProjectors() {
		return projectors;
	}

	public void setProjectors(int projectors) throws WctttModelException {
		ValidationHelper.validateProjectors(projectors);
		this.projectors = projectors;
	}

	@XmlAttribute(required = true)
	public boolean isPcPool() {
		return pcPool;
	}

	public void setPcPool(boolean pcPool) {
		this.pcPool = pcPool;
	}

	@XmlAttribute(required = true)
	private boolean getTeacherPc() {
		return hasTeacherPc();
	}

	public boolean hasTeacherPc() {
		return teacherPc;
	}

	public void setTeacherPc(boolean teacherPc) {
		this.teacherPc = teacherPc;
	}

	@XmlAttribute(required = true)
	private boolean getDocCam() {
		return hasDocCam();
	}

	public boolean hasDocCam() {
		return docCam;
	}

	public void setDocCam(boolean docCam) {
		this.docCam = docCam;
	}

	@Override
	public int compareTo(RoomFeatures roomFeatures) {
		if (this.equals(roomFeatures)) {
			return 0;
		} else if (this.projectors >= roomFeatures.projectors &&
				Boolean.compare(this.pcPool, roomFeatures.pcPool) >= 0 &&
				Boolean.compare(this.teacherPc, roomFeatures.teacherPc) >= 0 &&
				Boolean.compare(this.docCam, roomFeatures.docCam) >= 0) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RoomFeatures that = (RoomFeatures) o;
		return projectors == that.projectors &&
				pcPool == that.pcPool &&
				teacherPc == that.teacherPc &&
				docCam == that.docCam;
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectors, pcPool, teacherPc, docCam);
	}
}
