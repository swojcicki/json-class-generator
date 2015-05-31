package pl.edu.agh.student.wojcicks;

/**
 * Created on: 31.05.15 12:39 <br/>
 *
 * @author Slawomir Wojcicki
 */
public class StringUtils {

  public static String firstUpperCase(String s) {
    StringBuilder sb = new StringBuilder(s.length());
    sb.append(s.substring(0, 1).toUpperCase());
    sb.append(s.substring(1));
    return sb.toString();
  }
}
