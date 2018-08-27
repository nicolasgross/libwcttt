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

1. `cd <path-to-project-root>`
2. `mvn package` 
3. Optional, installs libwcttt in the local maven repository which is required
to build WCT³ Core and WCT³ GUI.  
`mvn install`


## License

This software is released under the terms of the GPLv3.
