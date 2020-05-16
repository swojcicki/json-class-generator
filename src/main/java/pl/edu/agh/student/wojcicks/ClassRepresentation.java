package pl.edu.agh.student.wojcicks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created on: 31.05.15 11:55 <br/>
 *
 * @author Slawomir Wojcicki
 */
public class ClassRepresentation {

  private static final String NEW_LINE = "\n";
  private static final String IND = "  ";
  private String packageName;
  private String className;
  private Map<String, String> fields = new HashMap<>();

  public ClassRepresentation(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Constructor allows non-empty string.");
    }
    this.className = StringUtils.firstUpperCase(name);
  }

  public String put(String fieldName, String fieldType) {
    return fields.put(fieldName, fieldType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    addPackage(sb);
    boolean hasList = false;
    StringBuilder fieldsSt = new StringBuilder();
    StringBuilder setGetSt = new StringBuilder();

    Set<String> fieldNames = fields.keySet();
    for (String fieldName : fieldNames) {
      hasList = checkHasList(hasList, fieldName);

      fieldsSt.append(generateFields(fieldName));
      setGetSt.append(generateGetSet(fieldName));
    }
    addImports(sb, hasList);
    startClass(sb);
    add(sb, fieldsSt);
    add(sb, setGetSt);
    endClass(sb);

    return sb.toString();
  }

  private void add(StringBuilder sb, StringBuilder text) {
    if (text.length() > 0) {
      sb.append(NEW_LINE).append(text);
    }
  }

  private void startClass(StringBuilder sb) {
    sb.append("public class ").append(className).append(" {").append(NEW_LINE);
  }

  private void endClass(StringBuilder sb) {
    sb.append("}");
  }

  private void addImports(StringBuilder sb, boolean hasList) {
    if (hasList) {
      sb.append("import ").append("java.util.List").append(";").append(NEW_LINE);
      sb.append("import ").append("java.util.ArrayList").append(";").append(NEW_LINE);
      sb.append(NEW_LINE);
    }
  }

  private void addPackage(StringBuilder sb) {
    if (packageName != null && !packageName.isEmpty()) {
      sb.append("package ").append(packageName).append(";").append(NEW_LINE);
      sb.append(NEW_LINE);
    }
  }

  private String generateGetSet(String fieldName) {
    String capitalizedFieldName = StringUtils.firstUpperCase(fieldName);
    String fieldType = fields.get(fieldName);
    StringBuilder sb = new StringBuilder();
    sb.append(IND).append("public ").append(fieldType).append(" get").append(capitalizedFieldName).append("() {").append(NEW_LINE);
    sb.append(IND).append(IND).append("return ").append(fieldName).append(";").append(NEW_LINE);
    sb.append(IND).append("}").append(NEW_LINE);
    sb.append(NEW_LINE);
    sb.append(IND).append("public void set").append(capitalizedFieldName).append("(").append(fieldType).append(" ").append(fieldName).append(") {").append(NEW_LINE);
    sb.append(IND).append(IND).append("this.").append(fieldName).append(" = ").append(fieldName).append(";").append(NEW_LINE);
    sb.append(IND).append("}").append(NEW_LINE);
    sb.append(NEW_LINE);
    return sb.toString();
  }

  private String generateFields(String fieldName) {
    String fieldType = fields.get(fieldName);
    StringBuilder sb = new StringBuilder();
    if (fieldType.startsWith("List")) {
      sb.append(IND).append("private ").append(fieldType).append(" ").append(fieldName).append(" = new Array").append(fieldType).append("()").append(";").append(NEW_LINE);
    } else {
      sb.append(IND).append("private ").append(fieldType).append(" ").append(fieldName).append(";").append(NEW_LINE);
    }
    return sb.toString();
  }

  private boolean checkHasList(boolean hasList, String fieldName) {
    String fieldType = fields.get(fieldName);
    if (fieldType.startsWith("List")) {
      hasList = true;
    }
    return hasList;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }
}
