package de.nicolasgross.wcttt.binder;

import de.nicolasgross.wcttt.model.Semester;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class WctttBinder {

	private String path;
	private JAXBContext context;

	public WctttBinder(String path) throws WctttParserException {
		if (path == null) {
			throw new IllegalArgumentException("Parameter path must not be null");
		}
		this.path = path;
		try {
			this.context = JAXBContext.newInstance(Semester.class);
		} catch (JAXBException e) {
			throw new WctttParserException(e);
		}
	}

	public Semester parse() throws WctttParserException {
		try {
			Unmarshaller um = context.createUnmarshaller();
			return (Semester) um.unmarshal(new File(path));
		} catch (JAXBException e) {
			throw new WctttParserException(e);
		}
	}

	public void write(Semester semester) throws WctttParserException {
		if (semester == null) {
			throw new IllegalArgumentException("Parameter semester must no be null");
		}
		try {
			Marshaller ms = context.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(semester, new File(path));
		} catch (JAXBException e) {
			throw new WctttParserException(e);
		}
	}

}
