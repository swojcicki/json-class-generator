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
  private Map<String, String> fields = new HashMap<String, String>();

  public ClassRepresentation(String name) {
    className = name;
  }

  public String put(String key, String value) {
    return fields.put(key, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (packageName != null && !packageName.isEmpty()) {
      sb.append("package ").append(packageName).append(";").append(NEW_LINE);
      sb.append(NEW_LINE);
    }
    boolean hasList = false;
    Set<String> keys = fields.keySet();
    for (String key : keys) {
      if (fields.get(key).contains("List")) {
        hasList = true;
        break;
      }
    }
    if (fields.containsValue("List") || hasList) {
      sb.append("import ").append("java.util.List").append(";").append(NEW_LINE);
      sb.append("import ").append("java.util.ArrayList").append(";").append(NEW_LINE);
      sb.append(NEW_LINE);
    }
    sb.append("public class ").append(className).append(" {").append(NEW_LINE);
    sb.append(NEW_LINE);

    for (String key : keys) {
      if (fields.get(key).startsWith("List")) {
        sb.append(IND).append("private ").append(fields.get(key)).append(" ").append(key).append(" = new Array").append(fields.get(key)).append("()").append(";").append(NEW_LINE);
      } else {
        sb.append(IND).append("private ").append(fields.get(key)).append(" ").append(key).append(";").append(NEW_LINE);
      }
    }
    sb.append(NEW_LINE);
    for (String key : keys) {
      sb.append(IND).append("public ").append(fields.get(key)).append(" get").append(StringUtils.firstUpperCase(key)).append("() {").append(NEW_LINE);
      sb.append(IND).append(IND).append("return ").append(key).append(";").append(NEW_LINE);
      sb.append(IND).append("}").append(NEW_LINE);
      sb.append(NEW_LINE);
      sb.append(IND).append("public void set").append(StringUtils.firstUpperCase(key)).append("(").append(fields.get(key)).append(" ").append(key).append(") {").append(NEW_LINE);
      sb.append(IND).append(IND).append("this.").append(key).append(" = ").append(key).append(";").append(NEW_LINE);
      sb.append(IND).append("}").append(NEW_LINE);
      sb.append(NEW_LINE);
    }
    sb.append("}");

    return sb.toString();
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }
}
