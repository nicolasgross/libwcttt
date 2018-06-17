package de.nicolasgross.wcttt.binder;

import de.nicolasgross.wcttt.model.Semester;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class WctttBinder {

	private static final File SCHEMA_FILE =
			new File("src/main/resources/schema.xsd");

	private File xmlFile;
	private JAXBContext context;
	private Validator validator;

	public WctttBinder(String path) throws WctttBinderException {
		if (path == null) {
			throw new IllegalArgumentException("Parameter path must not be " +
					"null");
		}
		this.xmlFile = new File(path);
		try {
			this.context = JAXBContext.newInstance(Semester.class);
			SchemaFactory sf = SchemaFactory.newInstance(
					XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(SCHEMA_FILE);
			this.validator = schema.newValidator();
		} catch (JAXBException e) {
			throw new WctttBinderException(e);
		} catch (SAXException e) {
			// TODO runtime exception?
			throw new WctttBinderException(e);
		}
	}

	public Semester parse() throws WctttBinderException {
		try {
			validator.validate(new StreamSource(xmlFile));
			Unmarshaller um = context.createUnmarshaller();
			return (Semester) um.unmarshal(xmlFile);
		} catch (JAXBException e) {
			throw new WctttBinderException(e);
		} catch (SAXException e) {
			// TODO
			throw new WctttBinderException(e);
		} catch (IOException e) {
			// TODO
			throw new WctttBinderException(e);
		}
	}

	public void write(Semester semester) throws WctttBinderException {
		if (semester == null) {
			throw new IllegalArgumentException("Parameter semester must no be" +
					"null");
		}
		try {
			// TODO use schema
			Marshaller ms = context.createMarshaller();
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(semester, xmlFile);
		} catch (JAXBException e) {
			throw new WctttBinderException(e);
		}
	}

}
