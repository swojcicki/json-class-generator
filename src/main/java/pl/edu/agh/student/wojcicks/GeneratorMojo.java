package pl.edu.agh.student.wojcicks;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 * @phase process-sources
 */
public class GeneratorMojo extends AbstractMojo {

  /**
   * Location of the file.
   *
   * @parameter property="project.build.directory"
   * @required
   */
  private File outputDirectory;

  /**
   * Input file.
   *
   * @parameter property="project.build.directory"
   * @required
   */
  private File inputFile;

  /**
   * Package name.
   *
   * @parameter property="project.build.directory"
   */
  private String packageName;

  public void execute() throws MojoExecutionException {
    File f = outputDirectory;

    if (!f.exists()) {
      boolean mkdirs = f.mkdirs();
    }

    JsonReader jsonReader = new JsonReader(inputFile);
    try {
      jsonReader.execute();
    } catch (IOException e) {
      throw new MojoExecutionException("Error executing reader.", e);
    }
    Map<String, ClassRepresentation> classes = jsonReader.getClasses();
    Set<String> classNames = classes.keySet();
    for (String className : classNames) {
      File touch = new File(f, className + ".java");

      FileWriter w = null;
      try {
        w = new FileWriter(touch);
        ClassRepresentation cr = classes.get(className);
        cr.setPackageName(packageName);
        w.write(cr.toString());
      } catch (IOException e) {
        throw new MojoExecutionException("Error creating file " + touch, e);
      } finally {
        if (w != null) {
          try {
            w.close();
          } catch (IOException e) {
            // ignore
          }
        }
      }
    }


  }

  public void setOutputDirectory(File outputDirectory) {
    this.outputDirectory = outputDirectory;
  }

  public void setInputFile(File inputFile) {
    this.inputFile = inputFile;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }
}
