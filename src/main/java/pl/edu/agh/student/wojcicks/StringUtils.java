package pl.edu.agh.student.wojcicks;

/**
 * Created on: 31.05.15 12:39 <br/>
 *
 * @author Slawomir Wojcicki
 */
public final class StringUtils {

  protected StringUtils() {
    throw new UnsupportedOperationException();
  }

  public static String firstUpperCase(String text) {
    if (text == null) return null;
    return text.substring(0, 1).toUpperCase() + text.substring(1);
  }
}
