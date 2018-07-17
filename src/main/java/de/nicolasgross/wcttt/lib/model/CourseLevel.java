package de.nicolasgross.wcttt.lib.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum CourseLevel {
	@XmlEnumValue("Bachelor") Bachelor,
	@XmlEnumValue("Master") Master
}
