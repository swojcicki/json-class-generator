package pl.edu.agh.student.wojcicks;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created on: 14.05.2020 18:32 <br/>
 *
 * @author root
 */
public class ClassRepresentationTest {

  @Test(expected = IllegalArgumentException.class)
  public void construct_null() {
    new ClassRepresentation(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void construct_empty() {
    new ClassRepresentation("");
  }

  @Test
  public void construct_simpleName() {
    ClassRepresentation classRepresentation = new ClassRepresentation("aaa");
    String actual = classRepresentation.toString();
    String expected = "public class Aaa {\n}";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void construct_simpleName_emptyPackage() {
    // given
    ClassRepresentation classRepresentation = new ClassRepresentation("aaa");
    classRepresentation.setPackageName("");
    // when
    String actual = classRepresentation.toString();
    // then
    String expected = "public class Aaa {\n}";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void construct_simpleName_simplePackage() {
    // given
    ClassRepresentation classRepresentation = new ClassRepresentation("aaa");
    String packageName = "io.github.lib";
    classRepresentation.setPackageName(packageName);
    // when
    String actual = classRepresentation.toString();
    // then
    String expected = "package " + packageName + ";\n\n"
      + "public class Aaa {\n}";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void put_simpleName_typeString() {
    // given
    ClassRepresentation classRepresentation = new ClassRepresentation("aaa");
    classRepresentation.put("name", "String");
    // when
    String actual = classRepresentation.toString();
    // then
    String expected = "public class Aaa {\n\n"
      + "  private String name;\n"
      + "\n  public String getName() {\n    return name;\n  }\n"
      + "\n  public void setName(String name) {\n    this.name = name;\n  }\n"
      + "\n}";
    Assert.assertEquals(expected, actual);
  }

  @Test
  public void putTwo_typeList() {
    // given
    ClassRepresentation classRepresentation = new ClassRepresentation("aaa");
    classRepresentation.put("name", "String");
    classRepresentation.put("books", "List<String>");
    // when
    String actual = classRepresentation.toString();
    // then
    String expected =
      "import java.util.List;\n" +
        "import java.util.ArrayList;\n\n" +
        "public class Aaa {\n\n"
        + "  private List<String> books = new ArrayList<String>();\n"
        + "  private String name;\n"
        + "\n  public List<String> getBooks() {\n    return books;\n  }\n"
        + "\n  public void setBooks(List<String> books) {\n    this.books = books;\n  }\n"
        + "\n  public String getName() {\n    return name;\n  }\n"
        + "\n  public void setName(String name) {\n    this.name = name;\n  }\n"
        + "\n}";
    Assert.assertEquals(expected, actual);
  }
}
