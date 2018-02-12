import com.cs5004.lab1.Email;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class EmailTest {
  private Email e1;
  private Email e2;
  private Email e3;

  /**
   * Generate 3 Email Object for tests.
   */
  @Before
  public void setUp() {
    e1 = new Email("abc@123.com");
    e2 = new Email("abc@456.com");
    e3 = new Email("def@123.com");
  }

  /**
   * Tests whether getUsername() method in Email class is right or not.
   */
  @Test
  public void testUsername() throws Exception {
    assertEquals(e1.getUsername(), e2.getUsername());
    assertNotEquals(e1.getUsername(), e3.getUsername());
    assertNotEquals(e2.getUsername(), e3.getUsername());
  }

  /**
   * Tests whether getDomain() method in Email class is right or not.
   */
  @Test
  public void testDomain() throws Exception {
    assertNotEquals(e1.getDomain(), e2.getDomain());
    assertEquals(e1.getDomain(), e3.getDomain());
    assertNotEquals(e2.getDomain(), e3.getDomain());
  }

  /**
   * Tests whether toString() method in Email class is the right format or not.
   */
  @Test
  public void testToString() throws Exception {
    assertNotEquals(e1.toString(), e2.toString());
    assertNotEquals(e1.toString(), e3.toString());
    assertNotEquals(e2.toString(), e3.toString());
  }

}