package pl.edu.agh.student.wojcicks;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created on: 31.05.15 10:29 <br/>
 *
 * @author Slawomir Wojcicki
 */
public class JsonReader {

  private File f;
  private Map<String, ClassRepresentation> classes;

  public static void main(String[] args) {
    try {
      System.out.println(new File(".").getAbsolutePath());
      JsonReader jsonReader = new JsonReader(new File("./example/example.json"));
      jsonReader.execute();
      for (String key : jsonReader.getClasses().keySet()) {
        System.out.println(jsonReader.getClasses().get(key));
      }
    } catch (IOException e) {
      e.printStackTrace(); // for test purpose
    }
  }

  public JsonReader(File f) {
    this.f = f;
    this.classes = new HashMap<String, ClassRepresentation>();
  }

  public void execute() throws IOException {
    JSONObject jsonObject = new JSONObject(FileUtils.readFileToString(f));
    String className = getClassName(f.getName());

    addClassRepresentation(jsonObject, className);
  }

  private void addClassRepresentation(JSONObject jsonObject, String className) {
    ClassRepresentation cr;
    if (classes.containsKey(className)) {
      cr = classes.get(className);
    } else {
      cr = new ClassRepresentation(className);
    }
    Iterator keys = jsonObject.keys();
    while (keys.hasNext()) {

      String key = (String) keys.next();
      Object value = jsonObject.get(key);
      check(cr, key, value);
    }
    classes.put(className, cr);
  }

  private void check(ClassRepresentation cr, String key, Object value) {
    if (value instanceof String) {
      cr.put(key, "String");
    } else if (value instanceof Double) {
      cr.put(key, "double");
    } else if (value instanceof Integer) {
      cr.put(key, "int");
    } else if (value instanceof Long) {
      cr.put(key, "long");
    } else if (value instanceof Boolean) {
      cr.put(key, "boolean");
    } else if (value instanceof JSONObject) {
      cr.put(key, StringUtils.firstUpperCase(key));
      addClassRepresentation((JSONObject) value, StringUtils.firstUpperCase(key));
    } else if (value instanceof JSONArray) {

      JSONArray ja = (JSONArray) value;
      int l = ja.length();
      String generics = "";
      for (int i = 0; i < l; i++) {
        Object jo = ja.get(i);
        if (jo instanceof JSONObject) {
          generics = "<" + StringUtils.firstUpperCase(key) + ">";
          addClassRepresentation((JSONObject) jo, StringUtils.firstUpperCase(key));
        }
      }
      cr.put(key, "List" + generics);

    }
  }

  private String getClassName(String name) {
    String[] s = name.split("\\.");
    return StringUtils.firstUpperCase(s[0]);
  }

  public Map<String, ClassRepresentation> getClasses() {
    return classes;
  }
}
