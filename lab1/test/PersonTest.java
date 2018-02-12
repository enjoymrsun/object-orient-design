import com.cs5004.lab1.Email;
import com.cs5004.lab1.Person;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {
  private Person john;

  /**
   * Generate 1 person for test.
   */
  @Before
  public void setUp() {
    john = new Person("John", "Doe", 1945, "6176667676",
            "algs@neu.edu");
  }

  /**
   * Tests whether getFirst() method in Person class is right or not.
   */
  @Test
  public void testFirst() {
    assertEquals(john.getFirstName(), "John");
  }

  /**
   * Tests whether getSecond() method in Person class is right or not.
   */
  @Test
  public void testSecond() {
    assertEquals(john.getLastName(), "Doe");
  }

  /**
   * Tests whether getYearOfBirth() method in Person class is right or not.
   */
  @Test
  public void testYearOfBirth() {
    assertEquals(john.getYearOfBirth(), 1945);
  }

  /**
   * Tests whether getPhone() method in Person class is right or not.
   */
  @Test
  public void testPhone() {
    assertEquals(john.getPhone(), "6176667676");
    assertNotEquals(john.getPhone(), "6176767676");
  }

  /**
   * Tests whether getEmail() method in Person class is right or not.
   */
  @Test
  public void testEmail() {
    assertEquals(john.getEmail(), (new Email("algs@neu.edu")).toString());
    assertNotEquals(john.getEmail(), (new Email("algs@nwu.edu")).toString());
  }

}