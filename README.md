# libwcttt

WCT³ (WIAI Course Timetabling Tool) is a software that strives to automate the 
timetabling process at the WIAI faculty of the University of Bamberg. It was 
developed by [Nicolas Gross](https://github.com/nicolasgross) as part of his 
bachelor thesis at the Software Technologies Research Group (SWT).

This library bundles functionality that is required in both other modules - WCT³
Core and WCT³ GUI. Moreover, it provides a solid foundation for future work on 
the topic. The library comprises the data model, a binder (parser + emitter) to
store the data as XML files, the implementations of the algorithms as well as
functionality to calculate conflicts and their violations. 


## Dependencies

- Oracle JDK 10
- maven


## Build

1. `cd [PATH_TO_PROJECT_ROOT]`
2. `mvn clean package` 
3. Optional, installs libwcttt in the local maven repository which is required
to build WCT³ Core and WCT³ GUI.  
`mvn install`


## Use

After the library was installed to the local maven repository, it can be used in
a maven project by adding the following dependencies:

```xml
<dependencies>
        ...
        <dependency>
            <groupId>de.nicolasgross.wcttt</groupId>
            <artifactId>libwcttt</artifactId>
            <version>[VERSION]</version>
        </dependency>
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jaxb</groupId>
            <artifactId>jaxb-runtime</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1.1</version>
        </dependency>
        ...
     </dependencies>
```

## License

This software is released under the terms of the GPLv3.
