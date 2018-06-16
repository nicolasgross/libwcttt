package de.nicolasgross.wcttt.binder;

import de.nicolasgross.wcttt.model.Semester;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class WctttBinderTest {

	@Test
	void emptySemesterWritesAndParses() throws WctttParserException, IOException {
		String path = "empty-semester.xml";
		Semester semesterWrite = new Semester();
		WctttBinder binder = new WctttBinder(path);
		binder.write(semesterWrite);
		Semester semesterRead = binder.parse();
		//Files.delete(Paths.get(path));
	}

}
