# Processing 3.5.3 Eclipse/Maven template

This is Processing 3.5.3 template use with Eclipse and Maven. This is quite handy when
you don't want to use the Processing IDE and want to take advantage over the package
management of Maven. 

## How to use

This is how you kick-start an Processing project with this template:

* Clone the repo to your local drive
* Remove the git history from the template, and restart your git history (optional)
* Update project information in the POM file (groupId, artefactId, version, name)

## Known issues

** When using 3D the JRE complains about illegal reflective access **

There is a reflection access violation in the Gluegen library that is reported in newer 
JREs. This might be fixed in a future release, but for now we are stuck with this
warning.

**  Processing Video does not work ** 

The Processing video library depends on some native libraries (no non-jar libraries)
that I so far have not managed to embed into a jar for portability. Once time permits
I might look into this.

## Dependencies

### Current dependendencies

** Maven dependencies **

* Processing 3.5.3 (thanks to [Quil](http://quil.info/))
* iText 2.1.7 (for PDF generation)
* JOGL 2.3.2 (for OpenGL)
* Gluegen 2.3.2 (for C library interoperability)
 
** Local dependencies **

* [Ani 2.7](http://www.looksgood.de/libraries/Ani/) for Animation
* [ControlP5 2.2.5](http://www.sojamo.de/libraries/controlP5) for UI

### Adding dependencies

** Adding Maven dependencies **

To add dependencies through Maven, add them as a dependency as you would normally by adding
them to de dependecies section in the POM file.

** Adding local dependencies **

When you want to add a dependency (jar) that is not hosted on a Maven repository, they can be added 
to the local (file-based) repository. As the jars are added to this repository, it does
add to the filesize of the repository so if there are hosted libraries, that is the preferred
method. 

To add a library to the local Maven repository, execute this maven command:

```console
mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file \
-Dfile={path-to-library}/example-library.jar \
-DgroupId=com.example \
-DartifactId=examplelib \ 
-Dversion=0.0.1-SNAPSHOT \
-Dpackaging=jar \
-DlocalRepositoryPath={path-to-this-repo}/processing-template/local-maven-repo 
```

You can use anything you like for the groupId, artifactId and version, but I try to
stick with something that at least attributes the original creator. 

To add a dependency to the local library, add it as you would a normal dependency,
using the groupId, artifactId and version you supplied when adding to the local
maven repository. In our example:

```xml
<dependency>
	<groupId>com.example</groupId>
	<artifactId>examplelib</artifactId>
	<version>0.0.1-SNAPSHOT</version>
</dependency>
```

** Adding local libraries that have native dependencies **

So far I've not found a way to add jars with native dependencies (such as processing video)
 to this template. If you know of a effective way, let me know.