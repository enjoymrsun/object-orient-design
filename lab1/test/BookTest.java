import com.cs5004.lab1.Book;
import com.cs5004.lab1.Person;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BookTest {
  private Book b1;
  private Book b2;
  private Book b3;

  /**
   * Generate 2 person and 3 books used for test.
   */
  @Before
  public void setUp() {
    Person p1;
    Person p2;
    p1 = new Person("Jaylen", "Sun", 1997, "6177611564",
            "aba@121.com");
    p2 = new Person("Abraham", "Tatum", 1998, "6177611564",
            "aba@121.com");
    b1 = new Book("Intro to NBA", p1, 89.9f);
    b2 = new Book("Advanced Web Dev", p2, 89.9f);
    b3 = new Book("Advanced Web Dev", p1, 78.9f);
  }

  /**
   * Tests whether getTitle() method in Book class is right or not.
   */
  @Test
  public void testTitle() {
    assertNotEquals(b1.getTitle(), b2.getTitle());
    assertNotEquals(b1.getTitle(), b3.getTitle());
    assertEquals(b2.getTitle(), b3.getTitle());
  }

  /**
   * Tests whether getAuthor() method in Book class is right or not.
   */
  @Test
  public void testAuthor() {
    assertNotEquals(b1.getAuthor(), b2.getAuthor());
    assertEquals(b1.getAuthor(), b3.getAuthor());
    assertNotEquals(b2.getAuthor(), b3.getAuthor());
  }

  /**
   * Tests whether getPrice() method in Book class is right or not.
   */
  @Test
  public void testPrice() {
    assertEquals(b1.getPrice(), b2.getPrice(), 0.0001);
    assertNotEquals(b1.getPrice(), b3.getPrice(), 0.0001);
    assertNotEquals(b2.getPrice(), b3.getPrice(), 0.0001);
  }


}