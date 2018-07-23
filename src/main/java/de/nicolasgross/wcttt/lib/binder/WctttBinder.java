package de.nicolasgross.wcttt.lib.binder;

import de.nicolasgross.wcttt.lib.model.Semester;
import de.nicolasgross.wcttt.lib.model.SemesterImpl;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URL;

public class WctttBinder {

	private static final URL SCHEMA_FILE =
			WctttBinder.class.getResource("/schema.xsd");

	private File xmlFile;
	private Schema schema;
	private JAXBContext context;


	public WctttBinder(File file) throws WctttBinderException {
		if (file == null) {
			throw new IllegalArgumentException("Parameter 'file' must not be " +
					"null");
		}
		this.xmlFile = file;
		SchemaFactory sf = SchemaFactory.newInstance(
					XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			this.schema = sf.newSchema(SCHEMA_FILE);
			this.context = JAXBContext.newInstance(
					SemesterImpl.class);
		} catch (JAXBException e) {
			// According to doc, thrown if there are problems with the mappings
			// -> decision for RuntimeException because there must be a serious
			// problem in the implementation
			throw new WctttBinderFatalException("Fatal problem, error in the " +
					"implementation of XML mappings, please contact the " +
					"developers", e);
		} catch (SAXException e) {
			throw new WctttBinderException("Error while parsing the schema " +
					"file", e);
		}
	}

	public File getXmlFile() {
		return xmlFile;
	}

	public Semester parse() throws WctttBinderException {
		try {
			Unmarshaller um = context.createUnmarshaller();
			um.setSchema(schema);
			return (Semester) um.unmarshal(xmlFile);
		} catch (JAXBException e) {
			throw new WctttBinderException("Error while parsing a XML file", e);
		}
	}

	public void write(Semester semester) throws WctttBinderException {
		if (semester == null) {
			throw new IllegalArgumentException("Parameter semester must no be" +
					"null");
		}
		try {
			Marshaller ms = context.createMarshaller();
			ms.setSchema(schema);
			ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			ms.marshal(semester, xmlFile);
		} catch (JAXBException e) {
			throw new WctttBinderException("Error while writing a XML file", e);
		}
	}

}
