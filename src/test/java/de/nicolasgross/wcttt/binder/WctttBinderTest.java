package de.nicolasgross.wcttt.binder;

import de.nicolasgross.wcttt.model.Semester;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WctttBinderTest {

	@Test
	void emptySemesterWritesAndParses() throws WctttParserException,
			IOException {
		String path = "test-empty-semester.xml";
		WctttBinder binder = new WctttBinder(path);
		Semester semesterWrite = new Semester();
		binder.write(semesterWrite);
		Semester semesterRead = binder.parse();
		assertEquals(semesterWrite, semesterRead);
		//Files.delete(Paths.get(path));
	}

	@Test
	void fullComplexitySemesterWritesAndParses() throws WctttParserException {
		String path = "test-complex-semester.xml";
		WctttBinder binder = new WctttBinder(path);
		Semester semesterWrite = new Semester();



		binder.write(semesterWrite);
	}

}
