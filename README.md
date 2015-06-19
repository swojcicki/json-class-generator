# json-class-generator
Class generator for JSON

```
git clone https://github.com/swojcicki/json-class-generator.git
cd json-class-generator
mvn clean install
```

Plugin configuration.

```
<plugin>
  <groupId>pl.edu.agh.student.wojcicks</groupId>
  <artifactId>json-class-generator</artifactId>
  <version>0.1</version>
  <configuration>
    <inputFile>${basedir}/src/main/resources/app.json</inputFile>
    <outputDirectory>${basedir}/src/main/java/pl/edu/agh/student/wojcicks/app/json</outputDirectory>
    <packageName>pl.edu.agh.student.wojcicks.app.json</packageName>
  </configuration>
  <executions>
    <execution>
      <goals>
        <goal>touch</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

Running.

```
mvn clean json-class-generator:touch
```
