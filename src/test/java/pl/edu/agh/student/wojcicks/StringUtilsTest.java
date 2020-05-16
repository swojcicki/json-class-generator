package pl.edu.agh.student.wojcicks;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created on: 14.05.2020 17:53 <br/>
 *
 * @author Slawek Wojcicki
 */
public class StringUtilsTest {

  @Test(expected = UnsupportedOperationException.class)
  public void utilClass() {
    new StringUtils();
  }

  @Test
  public void firstUpperCase_null() {
    Assert.assertEquals(null, StringUtils.firstUpperCase(null));
  }

  @Test
  public void firstUpperCase_fromLower() {
    Assert.assertEquals("Aaa", StringUtils.firstUpperCase("aaa"));
    Assert.assertEquals("AAA", StringUtils.firstUpperCase("aAA"));
  }

  @Test
  public void firstUpperCase_fromUpper() {
    Assert.assertEquals("AAA", StringUtils.firstUpperCase("AAA"));
    Assert.assertEquals("Aaa", StringUtils.firstUpperCase("Aaa"));
  }

  @Test
  public void firstUpperCase_nonLetter() {
    Assert.assertEquals("111", StringUtils.firstUpperCase("111"));
  }
}
